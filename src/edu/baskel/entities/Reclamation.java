/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Skander
 */
public class Reclamation {

    private String desc_r;
    private int id_rec;
    private Date date_rec;
    private String etat_rec;
    private String objet_rec;
    private int id_u;//RECLAMATEUR
    private int id_ur;//
    private String etat_rec2;
    private int condition;
    private Membre ACCUSATEUR;
    private Membre Reclamateur;

    public Reclamation(String desc_r, int id_rec, Date date_rec, String etat_rec, String objet_rec, int id_u, int id_ur, String etat_rec2, int condition, Membre membre) {
        this.desc_r = desc_r;
        this.id_rec = id_rec;
        this.date_rec = date_rec;
        this.etat_rec = etat_rec;
        this.objet_rec = objet_rec;
        this.id_u = id_u;
        this.id_ur = id_ur;
        this.etat_rec2 = etat_rec2;
        this.condition = condition;
    }

    public Membre getACCUSATEUR() {
        return ACCUSATEUR;
    }

    public void setACCUSATEUR(Membre ACCUSATEUR) {
        this.ACCUSATEUR = ACCUSATEUR;
    }

    public Membre getReclamateur() {
        return Reclamateur;
    }

    public void setReclamateur(Membre Reclamateur) {
        this.Reclamateur = Reclamateur;
    }

   

    public Reclamation() {
    }

    public Reclamation( String etat_rec,String etat_rec2,int id_rec) {
        this.etat_rec = etat_rec;
        this.etat_rec2 = etat_rec2;
        
        this.id_rec = id_rec;
    }

    public Reclamation(String desc_r, int id_rec, Date date_rec, String etat_rec, String objet_rec, int id_u, int id_ur, String etat_rec2,int condition) {
        this.desc_r = desc_r;
        this.id_rec = id_rec;
        this.date_rec = date_rec;
        this.etat_rec = etat_rec;
        this.objet_rec = objet_rec;
        this.id_u = id_u;
        this.id_ur = id_ur;
        this.etat_rec2 = etat_rec2;
        this.condition=condition;
    }

    public Reclamation(String desc_r, String objet_rec, int id_u,int id_ur) {
        this.desc_r = desc_r;
        this.objet_rec = objet_rec;
        this.id_u = id_u;
        this.id_ur=id_ur;
    }

    /*public Reclamation(String text, String text0, int id_ur, int id_u) {
    }*/
    

    public String getDesc_r() {
        return desc_r;
    }

    public void setDesc_r(String desc_r) {
        this.desc_r = desc_r;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

    public String getEtat_rec() {
        return etat_rec;
    }

    public void setEtat_rec(String etat_rec) {
        this.etat_rec = etat_rec;
    }

    public String getObjet_rec() {
        return objet_rec;
    }

    public void setObjet_rec(String objet_rec) {
        this.objet_rec = objet_rec;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getId_ur() {
        return id_ur;
    }

    public void setId_ur(int id_ur) {
        this.id_ur = id_ur;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    

    public void setEtat_rec2(String etat_rec2) {
        this.etat_rec2 = etat_rec2;
    }
    public String getEtat_rec2() {
        return etat_rec2;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "desc_r=" + desc_r + ", id_rec=" + id_rec + ", date_rec=" + date_rec + ", etat_rec=" + etat_rec + ", objet_rec=" + objet_rec + ", id_u=" + id_u + ", id_ur=" + id_ur + ", etat_rec2=" + etat_rec2 + '}';
    }
    

}
