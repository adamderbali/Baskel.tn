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
public class Evenement {
    
    private int id_e;
    private String nom_e;
    private String lieu_e;
    private String date_e;
    private String description_e;
    private String image_e;
    private Membre id_user;

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, Membre id_user) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_user = id_user;
    }

    public Evenement(int id_e, String nom_e, Membre id_user) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.id_user = id_user;
    }

    public Evenement(int id_e, String nom_e, String date_e, String description_e, String image_e) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
    }

    public int getId_e() {
        return id_e;
    }

    public String getNom_e() {
        return nom_e;
    }

    public String getLieu_e() {
        return lieu_e;
    }

    public String getDate_e() {
        return date_e;
    }

    public String getDescription_e() {
        return description_e;
    }

    public String getImage_e() {
        return image_e;
    }

    public Membre getId_user() {
        return id_user;
    }

    public void setId_e(int id_e) {
        this.id_e = id_e;
    }

    public void setNom_e(String nom_e) {
        this.nom_e = nom_e;
    }

    public void setLieu_e(String lieu_e) {
        this.lieu_e = lieu_e;
    }

    public void setDate_e(String date_e) {
        this.date_e = date_e;
    }

    public void setDescription_e(String description_e) {
        this.description_e = description_e;
    }

    public void setImage_e(String image_e) {
        this.image_e = image_e;
    }

    public void setId_user(Membre id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_e=" + id_e + ", nom_e=" + nom_e + ", lieu_e=" + lieu_e + ", date_e=" + date_e + ", description_e=" + description_e + ", image_e=" + image_e + ", id_user=" + id_user + '}';
    }

   
}