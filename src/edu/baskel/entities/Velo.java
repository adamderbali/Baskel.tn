/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Date;
import javafx.scene.control.Button;

/**
 *
 * @author Hela
 */
public class Velo {
    private int  num_serie;
    private String marque;
    private String model;
    private double prix_v;
    private String type_v;
    private String annee_sortie;
    private String status_v;
    private int num_tel_v;
    private String etat_v;
    private String description_v;
    private int id_u;
    private String image_v;
    private int id_av;
    private Button button;

    public Velo(int num_serie, String marque, String model,double prix_v, String type_v, String annee_sortie, String status_v, int num_tel_v, String etat_v, String description_v, int id_u,String image_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.prix_v= prix_v;
        this.type_v = type_v;
        this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        this.num_tel_v = num_tel_v;
        this.etat_v = etat_v;
        this.description_v = description_v;
        this.id_u = id_u;
        this.image_v=image_v;
    }

    /*public Velo(int num_serie, String marque, String model, double prix_v, String type_v, String status_v, String image_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.prix_v = prix_v;
        this.type_v = type_v;
        //this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        //this.description_v = description_v;
        this.image_v = image_v;
        this.button= new Button("Voir détails");
        
    }*/
    
     public Velo(int num_serie, String marque, String model, double prix_v, String type_v, String status_v, String image_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.prix_v = prix_v;
        this.type_v = type_v;
        this.status_v = status_v;
        this.image_v = image_v;
    }

    public Velo(int num_serie, String marque, String model, double prix_v, String type_v, String annee_sortie, String status_v, String description_v, String image_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.prix_v = prix_v;
        this.type_v = type_v;
        this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        this.description_v = description_v;
        this.image_v = image_v;
        //this.button= new Button("Voir détails");
    }

    public Velo(int num_serie, String marque, String model, double prix_v, String type_v, String annee_sortie, String status_v, String etat_v, String description_v, String image_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.prix_v = prix_v;
        this.type_v = type_v;
        this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        this.etat_v = etat_v;
        this.description_v = description_v;
        this.image_v = image_v;
    }
     public Velo(int num_serie, String marque, String model, double prix_v, String type_v, String annee_sortie, String status_v, String etat_v, String description_v,int id_u, String image_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.prix_v = prix_v;
        this.type_v = type_v;
        this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        this.etat_v = etat_v;
        this.description_v = description_v;
        this.id_u=id_u;
        this.image_v = image_v;
    }
    
    
    public Velo() {
    }

    public int getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(int num_serie) {
        this.num_serie = num_serie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrix_v() {
        return prix_v;
    }

    public void setPrix_v(double prix_v) {
        this.prix_v = prix_v;
    }
    
    
    public String getType_v() {
        return type_v;
    }

    public void setType_v(String type_v) {
        this.type_v = type_v;
    }

    public String getAnnee_sortie() {
        return annee_sortie;
    }

    public void setAnnee_sortie(String annee_sortie) {
        this.annee_sortie = annee_sortie;
    }

    public String getStatus_v() {
        return status_v;
    }

    public void setStatus_v(String status_v) {
        this.status_v = status_v;
    }

    public int getNum_tel_v() {
        return num_tel_v;
    }

    public void setNum_tel_v(int num_tel_v) {
        this.num_tel_v = num_tel_v;
    }

    public String getEtat_v() {
        return etat_v;
    }

    public void setEtat_v(String etat_v) {
        this.etat_v = etat_v;
    }

    public String getDescription_v() {
        return description_v;
    }

    public void setDescription_v(String description_v) {
        this.description_v = description_v;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    

    public String getImage_v() {
        return image_v;
    }

    public void setImage_v(String image_v) {
        this.image_v = image_v;
    }

    public int getId_av() {
        return id_av;
    }

    public void setId_av(int id_av) {
        this.id_av = id_av;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
     
    
    
    @Override
    public String toString() {
        return "Velo{" + "num_serie=" + num_serie + ", marque=" + marque + ", model=" + model + ", prix_v=" + prix_v + ", type_v=" + type_v + ", annee_sortie=" + annee_sortie + ", status_v=" + status_v + ", num_tel_v=" + num_tel_v + ", etat_v=" + etat_v + ", description_v=" + description_v + ", id_u=" + id_u + ", image_v=" + image_v + ", id_av=" + id_av + '}';
    }

    

    

    
    
    
    
    
    

    
    

}
