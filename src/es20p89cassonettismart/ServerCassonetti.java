/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es20p89cassonettismart;

import java.io.IOException;
import java.net.*;
import java.nio.*;
import java.time.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FEDERICOCOPES
 */
public class ServerCassonetti extends Thread{
    private ArrayList <TesseraRFID> elenco;
    private DatagramSocket socket;
    
    public ServerCassonetti(int port) throws SocketException {
        elenco = new ArrayList();
        socket = new DatagramSocket(port);
        //socket.setSoTimeout(1000); // 1000ms = 1s
    }
    
    public int generaTessera(){
        TesseraRFID t;
        t = new TesseraRFID(elenco.size()+1);
        elenco.add(t);
        return elenco.size()+1;
    }
    
    public int eliminaTessera(int id){
        for(int i=0; i<elenco.size(); i++){
            if(elenco.get(i).getId() == id){
                elenco.remove(i);
                return 1; // tessera eliminata
            }     
        }
        return -1; // tessera non trovata
    }
    
    public boolean differenzaDate(LocalDateTime d){
        Duration duration = Duration.between(LocalDateTime.now(), d);
        return duration.toHours() > 72;
    }
    
    public int consensoApertura(int id) {
        for (int i = 0; i < elenco.size(); i++) {
            if (elenco.get(i).getId() == id && elenco.get(i).getD() == null) { //tessera trovata
                elenco.get(i).setD(LocalDateTime.now());
                return 1; // apertura autorizzata
            } else {
                if (differenzaDate(elenco.get(i).getD())) {
                    return 1; // apertura autorizzata
                } else {
                    return -1; // usato entro le 72 ore prima
                }
            }
        }
        return -2; // tessera non trovata
    }
    
    public void run(){
        int uscita = 0;
        byte[] buffer = new byte[1024];
        ByteBuffer data;
        DatagramPacket answer, request;
        int scelta, id, risposta = 0; // dati ricevuti e da inviare
        
        while(true){
            try {
                request = new DatagramPacket(buffer, buffer.length);
                // attesa ricezione datagram di richiesta (tempo massimo di attesa: 1s)
                socket.receive(request);
                // incapsulazione del buffer della richiesta in un byte-buffer della dimensione di 2 valori int
                data = ByteBuffer.wrap(buffer, 0, 4);
                // estrazione dei 4 valori float dal byte-buffer
                scelta = data.getInt();
                id = data.getInt();
                switch(scelta){
                    case 1: //creazione tessera
                        id = generaTessera();
                        risposta = 1;
                        break;
                    case 2: //eliminazione tessera
                        risposta = eliminaTessera(id);
                        break;
                    case 3: // consenso apertura
                        risposta = consensoApertura(id);
                        break;
                    case 4: // uscita
                        uscita = 1;
                        risposta = -10;
                        break;
                }
                // incapsulazione del buffer della risposta in un byte-buffer della dimensione di 1 valore int
                data = ByteBuffer.wrap(buffer, 0, 4);
                // inserimento del valore double nel byte-buffer
                data.putInt(risposta);
                data.putInt(id);
                // costruzione del datagram da trasmettere a partire dal contenuto del byte-buffer
                answer = new DatagramPacket( data.array(), 4, request.getAddress(),
                request.getPort());
                socket.send(answer);
                socket.send(answer);
                if(uscita == 1)
                    break;
            } catch (IOException ex) {
                Logger.getLogger(ServerCassonetti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        socket.close(); // chiusura del socket
    }
}
