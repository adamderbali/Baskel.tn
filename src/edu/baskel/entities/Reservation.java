/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Date;

/**
 *
 * @author Hela
 */
public class Reservation {
    private int id_u;
    private int num_serie;
    private String date_res;
    private int nbr_heure;
    private Date date_db_res;
    private int id_res;
    private Membre membre;
    private Velo velo;
    
    public Reservation() {
    }

    public Reservation(int id_u,int num_serie, String date_res, int nbr_heure) {
        this.id_u = id_u;
        this.num_serie = num_serie;
        this.date_res = date_res;
        this.nbr_heure = nbr_heure;
        
    }

    public Reservation(int id_u,int num_serie, String date_res, int nbr_heure, Date date_db_res) {
        this.id_u = id_u;
        this.num_serie = num_serie;
        this.date_res = date_res;
        this.nbr_heure = nbr_heure;
        this.date_db_res = date_db_res;
        
    }
     public Reservation(int id_u,int num_serie, String date_res, int nbr_heure, Date date_db_res,Membre membre) {
        this.id_u = id_u;
        this.num_serie = num_serie;
        this.date_res = date_res;
        this.nbr_heure = nbr_heure;
        this.date_db_res = date_db_res;
        this.membre = membre;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(int num_serie) {
        this.num_serie = num_serie;
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
    

   

    

    public int getNbr_heure() {
        return nbr_heure;
    }

    public void setNbr_heure(int nbr_heure) {
        this.nbr_heure = nbr_heure;
    }

    public String getDate_res() {
        return date_res;
    }

    public void setDate_res(String date_res) {
        this.date_res = date_res;
    }

    public Date getDate_db_res() {
        return date_db_res;
    }

    public void setDate_db_res(Date date_db_res) {
        this.date_db_res = date_db_res;
    }

   

    

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_u=" + id_u + ", num_serie=" + num_serie + ", date_res=" + date_res + ", nbr_heure=" + nbr_heure + ", date_db_res=" + date_db_res + ", id_res=" + id_res + '}';
    }

    

    

    
}
