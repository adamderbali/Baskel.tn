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
public class Piece {
    private int id_p;
    private String nom_p;
    private float prix_p;
    private String image_p;

    public Piece() {
    }

    public Piece(int id_p, String nom_p, float prix_p, String image_p) {
        this.id_p = id_p;
        this.nom_p = nom_p;
        this.prix_p = prix_p;
        this.image_p = image_p;
    }

    public Piece(String nom_p, float prix_p, String image_p) {
        this.nom_p = nom_p;
        this.prix_p = prix_p;
        this.image_p = image_p;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public float getPrix_p() {
        return prix_p;
    }

    public void setPrix_p(float prix_p) {
        this.prix_p = prix_p;
    }

    public String getImage_p() {
        return image_p;
    }

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }

    @Override
    public String toString() {
        return "Piece{" + "id_p=" + id_p + ", nom_p=" + nom_p + ", prix_p=" + prix_p + ", image_p=" + image_p + '}';
    }
    
    
    
}
