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
    private String date_r;
    private int nbr_heure;
    private Date date_db_r;
    private int id_res;

    public Reservation() {
    }

    public Reservation(int id_u,int num_serie, String date_r, int nbr_heure) {
        this.id_u = id_u;
        this.num_serie = num_serie;
        this.date_r = date_r;
        this.nbr_heure = nbr_heure;
        
    }

    public Reservation(int id_u,int num_serie, String date_r, int nbr_heure, Date date_db_r) {
        this.id_u = id_u;
        this.num_serie = num_serie;
        this.date_r = date_r;
        this.nbr_heure = nbr_heure;
        this.date_db_r = date_db_r;
        
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
    

   

    public String getDate_r() {
        return date_r;
    }

    public void setDate_r(String date_r) {
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
        return "Reservation{" + "id_u=" + id_u + ", num_serie=" + num_serie + ", date_r=" + date_r + ", nbr_heure=" + nbr_heure + ", date_db_r=" + date_db_r + ", id_res=" + id_res + '}';
    }

    

    
}
