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
public class Reservation {
    private Membre membre;
    private Velo velo;
    private Date date_r;
    private int nbr_heure;
    private Date date_db_r;
    private int id_res;

    public Reservation() {
    }

    public Reservation(Membre membre, Velo velo, Date date_r, int nbr_heure, Date date_db_r) {
        this.membre = membre;
        this.velo = velo;
        this.date_r = date_r;
        this.nbr_heure = nbr_heure;
        this.date_db_r = date_db_r;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }

    public Date getDate_r() {
        return date_r;
    }

    public void setDate_r(Date date_r) {
        this.date_r = date_r;
    }

    public int getNbr_heure() {
        return nbr_heure;
    }

    public void setNbr_heure(int nbr_heure) {
        this.nbr_heure = nbr_heure;
    }

    public Date getDate_db_r() {
        return date_db_r;
    }

    public void setDate_db_r(Date date_db_r) {
        this.date_db_r = date_db_r;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    @Override
    public String toString() {
        return "Reservation{" + "membre=" + membre + ", velo=" + velo + ", date_r=" + date_r + ", nbr_heure=" + nbr_heure + ", date_db_r=" + date_db_r + ", id_res=" + id_res + '}';
    }

    
}
