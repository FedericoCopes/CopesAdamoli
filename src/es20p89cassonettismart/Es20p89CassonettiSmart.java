/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es20p89cassonettismart;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FEDERICOCOPES
 */
public class Es20p89CassonettiSmart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerCassonetti server = new ServerCassonetti(12345);
            server.start();
        } catch (SocketException ex) {
            Logger.getLogger(Es20p89CassonettiSmart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
