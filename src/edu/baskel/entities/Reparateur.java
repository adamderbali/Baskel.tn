/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Date;



/**
 *
 * @author Skander
 */
public class Reparateur extends Membre {
    private String adresse_lo;
    private String local_nom;
    private String num_pro;
    private String latitude;
    private String longitude;

    public Reparateur(String adresse_lo, String num_pro, String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u) {
        super(nom_u, prenom_u, adresse_u, email_u, sexe_u, date_u, mot_passe_u, num_tel_u);
        this.adresse_lo = adresse_lo;
        this.num_pro = num_pro;
    }

    public Reparateur(String adresse_lo, String local_nom, String num_pro, String latitude, String longitude, int id_u, String nom_u, String prenom_u, String adresse_u, String email_u, String sexe_u, Date date_u, String mot_passe_u, String num_tel_u, String image_u, String type_u) {
        super(id_u, nom_u, prenom_u, adresse_u, email_u, sexe_u, date_u, mot_passe_u, num_tel_u, image_u, type_u);
        this.adresse_lo = adresse_lo;
        this.local_nom = local_nom;
        this.num_pro = num_pro;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    

    public Reparateur(String adrloc, String telpro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAdresse_lo() {
        return adresse_lo;
    }

    public void setAdresse_lo(String adresse_lo) {
        this.adresse_lo = adresse_lo;
    }

    public String getLocal_nom() {
        return local_nom;
    }

    public void setLocal_nom(String local_nom) {
        this.local_nom = local_nom;
    }

    public String getNum_pro() {
        return num_pro;
    }

    public void setNum_pro(String num_pro) {
        this.num_pro = num_pro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Reparateur{" + "adresse_lo=" + adresse_lo + ", local_nom=" + local_nom + ", num_pro=" + num_pro + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }    
}
