/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

/**
 *
 * @author Skander
 */
public class Avis_admin {
    private double note;
    private String commentaire;
    private int id_u;

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

   

    public Avis_admin(double note, String commentaire, int id_u) {
        this.note = note;
        this.commentaire = commentaire;
        this.id_u = id_u;
    }
    public Avis_admin(String commentaire, double note) {
        
        this.commentaire = commentaire;
        this.note = note;
    }

    

    

    public Avis_admin() {
    }

    @Override
    public String toString() {
        return "Avis_admin{" + "note=" + note + ", commentaire=" + commentaire + ", id_u=" + id_u + '}';
    }
    
}
