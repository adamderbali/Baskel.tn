/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.util.Date;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

/**
 *
 * @author Skander
 */
public class Participation {
    private int id_e;
    private int id_u;
    private Date date_insc;
    private Membre mbre;
    private Evenement event;
     private ImageView image;
     private CheckBox chb;
     private Rating ra;
     private int note_avis;
 
    public Participation(int id_e, int id_u, Date date_insc, Membre mbre, Evenement event) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.mbre = mbre;
        this.event = event;
    }

    public Participation(int id_e, int id_u, Date date_insc, int note_avis) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.note_avis = note_avis;
    }

    public Participation(int id_e, int id_u, int note_avis) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.note_avis = note_avis;
    }

    public int getNote_avis() {
        return note_avis;
    }

    public void setNote_avis(int note_avis) {
        this.note_avis = note_avis;
    }

    public Rating getRa() {
        return ra;
    }

    public void setRa(Rating ra) {
        this.ra = ra;
    }

    public Participation(int id_e, int id_u, Date date_insc, Evenement event, ImageView image, Rating ra) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.event = event;
        this.image = image;
        this.ra = ra;
    }

    public Participation(int id_u, Evenement event, int note_avis) {
        this.id_u = id_u;
        this.event = event;
        this.note_avis = note_avis;
    }

    public Participation(int id_e, int id_u, Date date_insc, Evenement event, ImageView image, CheckBox chb, Rating ra) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.event = event;
        this.image = image;
        this.chb = chb;
        this.ra = ra;
    }

    public CheckBox getChb() {
        return chb;
    }

    public void setChb(CheckBox chb) {
        this.chb = chb;
    }

    public Participation() {
    }

    public Participation(int id_e, int id_u, Date date_insc, ImageView image, CheckBox chb) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.image = image;
        this.chb = chb;
    }



    public Participation(int id_e, int id_u, Date date_insc, Evenement event) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.event = event;
    }

    public Participation(int id_e, int id_u, Date date_insc, Membre mbre) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.mbre = mbre;
    }

    public Participation(int id_e, int id_u) {
        this.id_e = id_e;
        this.id_u = id_u;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Participation(int id_e, int id_u, Date date_insc, Membre mbre, Evenement event, ImageView image) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.mbre = mbre;
        this.event = event;
        this.image = image;
    }

    public Participation(int id_e, int id_u, Date date_insc, ImageView image) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.image = image;
    }

    public Participation(int id_u, Date date_insc, Membre mbre, Evenement event, ImageView image) {
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.mbre = mbre;
        this.event = event;
        this.image = image;
    }


    public Participation(int id_u, int id_e, Date date_insc) {
        this.id_u = id_u;
        this.id_e = id_e;
        this.date_insc = date_insc;
    }

    public Participation(int id_e) {
        this.id_e = id_e;
    }

    public Membre getMbre() {
        return mbre;
    }

    public Evenement getEvent() {
        return event;
    }

    public Participation(int id_u, Date date_insc) {
        this.id_u = id_u;
        this.date_insc = date_insc;
    }

    public void setMbre(Membre mbre) {
        this.mbre = mbre;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public int getId_u() {
        return id_u;
    }

    public int getId_e() {
        return id_e;
    }

    public Date getDate_insc() {
        return date_insc;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public void setId_e(int id_e) {
        this.id_e = id_e;
    }

    public void setDate_insc(Date date_insc) {
        this.date_insc = date_insc;
    }

    @Override
    public String toString() {
        return "Participation{" + "id_e=" + id_e + ", id_u=" + id_u + ", date_insc=" + date_insc + ", mbre=" + mbre + ", event=" + event + ", image=" + image + '}';
    }

   

  
   
    
    

}
