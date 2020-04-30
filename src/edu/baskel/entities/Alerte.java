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
public class Alerte {
    private int id_alert;
    private String description_a;
    private String latitude_a;
    private String longitude_a;
    private Date date_a;

    public Alerte(String description_a, String latitude_a, String longitude_a, Date date_a) {
        this.description_a = description_a;
        this.latitude_a = latitude_a;
        this.longitude_a = longitude_a;
        this.date_a = new Date();
    }

    public int getId_alert() {
        return id_alert;
    }

    public void setId_alert(int id_alert) {
        this.id_alert = id_alert;
    }

    public String getDescription_a() {
        return description_a;
    }

    public void setDescription_a(String description_a) {
        this.description_a = description_a;
    }

    public String getLatitude_a() {
        return latitude_a;
    }

    public void setLatitude_a(String latitude_a) {
        this.latitude_a = latitude_a;
    }

    public String getLongitude_a() {
        return longitude_a;
    }

    public void setLongitude_a(String longitude_a) {
        this.longitude_a = longitude_a;
    }

    public Date getDate_a() {
        return date_a;
    }

    @Override
    public String toString() {
        return "Alerte{" + "id_alert=" + id_alert + ", description_a=" + description_a + ", latitude_a=" + latitude_a + ", longitude_a=" + longitude_a + ", date_a=" + date_a + '}';
    }

   
    }
