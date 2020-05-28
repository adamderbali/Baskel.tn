/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.util.Date;
import javafx.scene.control.Button;
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
    private int nbr_max_e;
    private Label etat_e;
    private Membre mbre;
    private Participation part;
    private int nbr_participant;
     private Label etat_p;
     private Label pourcentage;
     private Button btn;

  

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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e, Label etat_e, int nbr_participant, Label etat_p, Label pourcentage, Button btn) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
        this.etat_e = etat_e;
        this.nbr_participant = nbr_participant;
        this.etat_p = etat_p;
        this.pourcentage = pourcentage;
        this.btn = btn;
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e, Membre mbre, int nbr_participant, Label etat_p, Label pourcentage, Button btn) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
        this.mbre = mbre;
        this.nbr_participant = nbr_participant;
        this.etat_p = etat_p;
        this.pourcentage = pourcentage;
        this.btn = btn;
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int nbr_max_e, Label etat_e, int nbr_participant, Label etat_p) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.nbr_max_e = nbr_max_e;
        this.etat_e = etat_e;
        this.nbr_participant = nbr_participant;
        this.etat_p = etat_p;
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e, Label etat_e, int nbr_participant, Label etat_p) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
        this.etat_e = etat_e;
        this.nbr_participant = nbr_participant;
        this.etat_p = etat_p;
    }

    public Label getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Label pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    

    public Label getEtat_p() {
        return etat_p;
    }

    public void setEtat_p(Label etat_p) {
        this.etat_p = etat_p;
    }
    

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int nbr_max_e, Label etat_e, int nbr_participant,Label etat_p) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.nbr_max_e = nbr_max_e;
        this.etat_e = etat_e;
        this.nbr_participant = nbr_participant;
        this.etat_p = etat_p;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
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

    public int getNbr_max_e() {
        return nbr_max_e;
    }

    public void setNbr_max_e(int nbr_max_e) {
        this.nbr_max_e = nbr_max_e;
    }

 

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e, Label etat_e,Label etat_p) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
        this.etat_e = etat_e;
        this.etat_p = etat_p;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
    }
    
     public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int nbr_max_e) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
     
        this.nbr_max_e = nbr_max_e;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, Label etat_e, Label etat_p) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.etat_e = etat_e;
        this.etat_p = etat_p;
    }

   

   
    
    
    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
 
    public Evenement() {
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String etat_e,String etat_p) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.etat_e = new Label(etat_e);
        this.etat_p = new Label(etat_p);
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, String etat_e,String etat_p) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.etat_e = new Label(etat_e);
        this.etat_p = new Label(etat_p);
    }
    
     public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, String etat_e,String etat_p,String pourcentage) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.etat_e = new Label(etat_e);
        this.etat_p = new Label(etat_p);
        this.pourcentage = new Label();
    }

    public Evenement(ImageView image, int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e, Label etat_e, Membre mbre, Participation part, int nbr_participant, Label etat_p, Label pourcentage) {
        this.image = image;
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
        this.etat_e = etat_e;
        this.mbre = mbre;
        this.part = part;
        this.nbr_participant = nbr_participant;
        this.etat_p = etat_p;
        this.pourcentage = pourcentage;
    }

    public Evenement(int id_e, String nom_e, String lieu_e, String date_e, String description_e, String image_e, int id_u, int nbr_max_e, int nbr_participant, Label pourcentage) {
        this.id_e = id_e;
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.id_u = id_u;
        this.nbr_max_e = nbr_max_e;
        this.nbr_participant = nbr_participant;
        this.pourcentage = pourcentage;
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

    public Evenement(String nom_e, String lieu_e, String date_e, String description_e, String image_e, int nbr_max_e) {
        this.nom_e = nom_e;
        this.lieu_e = lieu_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.image_e = image_e;
        this.nbr_max_e = nbr_max_e;
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
        return "Evenement{" + "image=" + image + ", id_e=" + id_e + ", nom_e=" + nom_e + ", lieu_e=" + lieu_e + ", date_e=" + date_e + ", description_e=" + description_e + ", image_e=" + image_e + ", id_u=" + id_u + ", nbr_max_e=" + nbr_max_e + ", etat_e=" + etat_e + ", mbre=" + mbre + ", part=" + part + '}';
    }

   

}
