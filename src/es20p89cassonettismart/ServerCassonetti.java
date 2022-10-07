/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es20p89cassonettismart;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

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
        socket.setSoTimeout(1000); // 1000ms = 1s
    }
    
    public void generaTessera(){
        TesseraRFID t;
        if(elenco.isEmpty())
            t = new TesseraRFID(1);   
        else
            t = new TesseraRFID(elenco.size()+1);
        elenco.add(t);
    }
    
    public int consensoApertura(int id){
        Date d = new Date() {};
        for(int i=0; i<elenco.size(); i++){
            if(elenco.get(i).getId() == id){ //tessera trovata
                if(elenco.get(i).getD() == null){
                    // inserire data
                    return 1; // apertura autorizzata
                }
                // controllo date
                    
            }
        }
        return -2; // tessera non trovata
    }
    
    public void run(){
        
    }
}
