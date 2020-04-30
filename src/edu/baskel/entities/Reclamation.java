/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.util.Date;

/**
 *
 * @author Skander
 */
public class Reclamation {
    private String desc_r;
    private int id_rec;
    private Date date_rec;
    private String etat_rec; 

    public Reclamation(String desc_r, Date date_rec, String etat_rec) {
        this.desc_r = desc_r;
        this.date_rec = new Date();
        this.etat_rec = etat_rec;
    }

    public Reclamation() {
    }

   
    public String getDesc_r() {
        return desc_r;
    }

    public void setDesc_r(String desc_r) {
        this.desc_r = desc_r;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public Date getDate_rec() {
        return date_rec;
    }
/////t3ada 
    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

    public String getEtat_rec() {
        return etat_rec;
    }

    public void setEtat_rec(String etat_rec) {
        this.etat_rec = etat_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "desc_r=" + desc_r + ", id_rec=" + id_rec + ", date_rec=" + date_rec + ", etat_rec=" + etat_rec + '}';
    } 
}
