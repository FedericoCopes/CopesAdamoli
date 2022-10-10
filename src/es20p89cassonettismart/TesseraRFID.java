/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es20p89cassonettismart;

import java.time.LocalDateTime;

/**
 *
 * @author FEDERICOCOPES
 */
public class TesseraRFID {
    int id; // identificativo numerico
    LocalDateTime d; // ultima apertura
    
    public TesseraRFID(int id, LocalDateTime d){
        this.id = id;
        this.d = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getD() {
        return d;
    }

    public void setD(LocalDateTime d) {
        this.d = d;
    }
    
}
