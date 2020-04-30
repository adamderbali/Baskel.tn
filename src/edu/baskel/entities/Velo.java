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
public class Velo {
    private int  num_serie;
    private String marque;
    private String model;
    private String type_v;
    private Date annee_sortie;
    private String status_v;
    private String etat_v;
    private String description_v;

    public Velo(int num_serie, String marque, String model, String type_v, Date annee_sortie, String status_v, String etat_v, String description_v) {
        this.num_serie = num_serie;
        this.marque = marque;
        this.model = model;
        this.type_v = type_v;
        this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        this.etat_v = etat_v;
        this.description_v = description_v;
    }

    public Velo(String marque, String model, String type_v, Date annee_sortie, String status_v, String etat_v, String description_v) {
        this.marque = marque;
        this.model = model;
        this.type_v = type_v;
        this.annee_sortie = annee_sortie;
        this.status_v = status_v;
        this.etat_v = etat_v;
        this.description_v = description_v;
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

    public String getType_v() {
        return type_v;
    }

    public void setType_v(String type_v) {
        this.type_v = type_v;
    }

    public Date getAnnee_sortie() {
        return annee_sortie;
    }

    public void setAnnee_sortie(Date annee_sortie) {
        this.annee_sortie = annee_sortie;
    }

    public String getStatus_v() {
        return status_v;
    }

    public void setStatus_v(String status_v) {
        this.status_v = status_v;
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

    @Override
    public String toString() {
        return "Velo{" + "num_serie=" + num_serie + ", marque=" + marque + ", model=" + model + ", type_v=" + type_v + ", annee_sortie=" + annee_sortie + ", status_v=" + status_v + ", etat_v=" + etat_v + ", description_v=" + description_v + '}';
    }
    

}