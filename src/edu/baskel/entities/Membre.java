/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import edu.baskel.utils.InputValidation;
import java.sql.Date;

/**
 *
 * @author adam
 */
public class Membre {

    private int id_u;
    private String nom_u;
    private String prenom_u;
    private String adresse_u;
    private String email_u;
    private String sexe_u;
    private Date date_u;
    private String mot_passe_u;
    private String num_tel_u;
    private String image_u;
    private String type_u;
    private int nbr_ban_u;
    private int validation_u;
    private InputValidation u ;

    public int getNbr_ban_u() {
        return nbr_ban_u;
    }

    public void setNbr_ban_u(int nbr_ban_u) {
        this.nbr_ban_u = nbr_ban_u;
    }

    public int getValidation_u() {
        return validation_u;
    }

    public void setValidation_u(int validation_u) {
        this.validation_u = validation_u;
    }

    public Membre(String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u, String image_u) {
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.adresse_u = adresse_u;
        this.email_u = email_u;
        this.sexe_u = sexe_u;
        this.date_u = date_u;
        
        this.mot_passe_u = mot_passe_u;
        this.num_tel_u = num_tel_u;
        this.image_u = image_u;
    }

 

    public Membre(int id_u, String nom_u, String prenom_u, String adresse_u, String email_u, Date date_u, String mot_passe_u, String num_tel_u) {
        this.id_u = id_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.adresse_u = adresse_u;
        this.email_u = email_u;
        this.date_u = date_u;
        this.mot_passe_u = mot_passe_u;
        this.num_tel_u = num_tel_u;
    }

    public Membre() {
    }

    public Membre(String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u) {
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.adresse_u = adresse_u;
        this.email_u = email_u;
        this.sexe_u = sexe_u;
        this.date_u = date_u;
        this.mot_passe_u = mot_passe_u;
        this.num_tel_u = num_tel_u;
    }

    public Membre(int id_u) {
        this.id_u = id_u;
    }
//1
    public Membre(int id_u, String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u, String image_u, String type_u) {
        this.id_u = id_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.adresse_u = adresse_u;
        this.email_u = email_u;
        this.sexe_u = sexe_u;
        this.date_u = date_u;
        this.mot_passe_u = mot_passe_u;
        this.num_tel_u = num_tel_u;
        this.image_u = image_u;
        this.type_u = type_u;
    }

    public Membre(int id_u, int nbr_ban_u) {
        this.id_u = id_u;
        this.nbr_ban_u = nbr_ban_u;
    }

    public Membre(int id_u, String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u, String image_u, String type_u, int nbr_ban_u, int validation_u) {
        this.id_u = id_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.adresse_u = adresse_u;
        this.email_u = email_u;
        this.sexe_u = sexe_u;
        this.date_u = date_u;
        this.mot_passe_u = mot_passe_u;
        this.num_tel_u = num_tel_u;
        this.image_u = image_u;
        this.type_u = type_u;
        this.nbr_ban_u = nbr_ban_u;
        this.validation_u = validation_u;
    }

    public Membre(int id_u, String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u, String image_u) {
        this.id_u = id_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.adresse_u = adresse_u;
        this.email_u = email_u;
        this.sexe_u = sexe_u;
        this.date_u = date_u;
        this.mot_passe_u = mot_passe_u;
        this.num_tel_u = num_tel_u;
        this.image_u = image_u;
    }
    
// constructeur sabrine
    public Membre(int id_u, String nom_u, String prenom_u, String email_u) {
        this.id_u = id_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.email_u = email_u;
    }
    

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getNom_u() {
        return nom_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public String getPrenom_u() {
        return prenom_u;
    }

    public void setPrenom_u(String prenom_u) {
        this.prenom_u = prenom_u;
    }

    public String getAdresse_u() {
        return adresse_u;
    }

    public void setAdresse_u(String adresse_u) {
        this.adresse_u = adresse_u;
    }

    public String getEmail_u() {
        return email_u;
    }

    public void setEmail_u(String email_u) {
        this.email_u = email_u;
    }

    public String getSexe_u() {
        return sexe_u;
    }

    public void setSexe_u(String sexe) {
        this.sexe_u = sexe;
    }

    public Date getDate_u() {
        return date_u;
    }

    public void setDate_u(Date date_u) {
        this.date_u = date_u;
    }

    public String getMot_passe_u() {
        return mot_passe_u;
    }

    public void setMot_passe_u(String mot_passe_u) {

        this.mot_passe_u = mot_passe_u;
    }

    public String getNum_tel_u() {
        return num_tel_u;
    }

    public void setNum_tel_u(String num_tel_u) {
        this.num_tel_u = num_tel_u;
    }

    public String getImage_u() {
        return image_u;
    }

    public void setImage_u(String image_u) {
        this.image_u = image_u;
    }

    public String getType_u() {
        return type_u;
    }

    public void setType_u(String type_u) {
        this.type_u = type_u;
    }

    @Override
    public String toString() {
        return "Membre{" + "id_u=" + id_u + ", nom_u=" + nom_u + ", prenom_u=" + prenom_u + ", adresse_u=" + adresse_u + ", email_u=" + email_u + ", sexe_u=" + sexe_u + ", date_u=" + date_u + ", mot_passe_u=" + mot_passe_u + ", num_tel_u=" + num_tel_u + ", image_u=" + image_u + ", type_u=" + type_u + ", nbr_ban_u=" + nbr_ban_u + ", validation_u=" + validation_u + '}';
    }

    


}
