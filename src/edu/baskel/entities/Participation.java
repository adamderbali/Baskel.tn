/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.util.Date;
import java.util.logging.Logger;

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

    public Participation(int id_e, int id_u, Date date_insc, Membre mbre, Evenement event) {
        this.id_e = id_e;
        this.id_u = id_u;
        this.date_insc = date_insc;
        this.mbre = mbre;
        this.event = event;
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

    public Participation(int id_u, int id_e) {
        this.id_u = id_u;
        this.id_e = id_e;
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
        return "Participation{" + "id_u=" + id_u + ", id_e=" + id_e + ", date_insc=" + date_insc + '}';
    }
    
    

}
