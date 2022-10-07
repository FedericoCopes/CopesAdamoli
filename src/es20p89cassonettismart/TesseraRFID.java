/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es20p89cassonettismart;

import java.util.Date;

/**
 *
 * @author FEDERICOCOPES
 */
public class TesseraRFID {
    int id; // identificativo numerico
    Date d; // ultima apertura
    
    public TesseraRFID(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
    
}
