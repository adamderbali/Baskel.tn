/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Avis {
   private int id_av;
   private int note_av;
   private String desc_av;
   private Timestamp date_avis;
   private Membre membre;
   private Reparateur reparateur;

    public Avis() {
        membre = new Membre();
        reparateur = new Reparateur();
    }

    public Avis(Membre membre, Reparateur reparateur) {
        this.membre = membre;
        this.reparateur = reparateur;
    }

    public int getId_av() {
        return id_av;
    }

    public void setId_av(int id_av) {
        this.id_av = id_av;
    }

    public int getNote_av() {
        return note_av;
    }

    public void setNote_av(int note_av) {
        this.note_av = note_av;
    }

    public String getDesc_av() {
        return desc_av;
    }

    public void setDesc_av(String desc_av) {
        this.desc_av = desc_av;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Reparateur getReparateur() {
        return reparateur;
    }

    public void setReparateur(Reparateur reparateur) {
        this.reparateur = reparateur;
    }

    public Timestamp getDate_avis() {
        return date_avis;
    }

    public void setDate_avis(Timestamp date_avis) {
        this.date_avis = date_avis;
    }
    

    @Override
    public String toString() {
        return "Avis{" + "id_av=" + id_av + ", note_av=" + note_av + ", desc_av=" + desc_av + ", membre=" + membre.toString() + ", reparateur=" + reparateur.toString() + '}';
    }
    
    
   
   
    
}
