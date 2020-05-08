/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Date;

/**
 *
 * @author dell
 */
public class HistoriqueCnx {

    private int id_u;

    private String date_cnx;

    public HistoriqueCnx() {
    }

    public HistoriqueCnx(int id_u, String date_cnx) {
        this.id_u = id_u;
        this.date_cnx = date_cnx;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getDate_cnx() {
        return date_cnx;
    }

    public void setDate_cnx(String date_cnx) {
        this.date_cnx = date_cnx;
    }

    @Override
    public String toString() {
        return "HistoriqueCnx{" + "id_u=" + id_u + ", date_cnx=" + date_cnx + '}';
    }

   
}
