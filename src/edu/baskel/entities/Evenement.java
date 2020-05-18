/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Skander
 */
/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
 /*!!!!!*/
public class Evenement {
    
    private ImageView image;
    private int id_e;
    private String nom_e;
    private String lieu_e;
    private String date_e;
    private String description_e;
    private String image_e;
    private int id_u;
  
    private Label etat_e;
    private Membre mbre;
    private Participation part;

  

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, Participation part) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.part = part;
    }

    public void setPart(Participation part) {
        this.part = part;
    }

    public Participation getPart() {
        return part;
    }

    public Label getEtat_e() {
        return etat_e;
    }

    public void setEtat_e(Label etat_e) {
        this.etat_e = etat_e;
    }

   

   
    
    
    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
 
    public Evenement() {
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String etat_e) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.etat_e = new Label(etat_e);
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, String etat) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.etat_e = etat_e;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String description_e) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.description_e = description_e;
    }

  
    
    

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, Membre mbre) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.mbre = mbre;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
    }

    public Evenement(String image_e) {
        this.image_e = image_e;
    }

    public Evenement(String nom_e, String lieu_e, String date_e, String description_e, String image_e) {
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

    public int getId_u() {
        return id_u;
    }

    public Membre getMbre() {
        return mbre;
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

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public void setMbre(Membre mbre) {
        this.mbre = mbre;
    }

    @Override
    public String toString() {
        return "Evenement{" + "image=" + image + ", id_e=" + id_e + ", nom_e=" + nom_e + ", lieu_e=" + lieu_e + ", date_e=" + date_e + ", description_e=" + description_e + ", image_e=" + image_e + ", id_u=" + id_u + ", mbre=" + mbre + '}';
    }

   

}
