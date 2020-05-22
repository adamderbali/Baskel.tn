/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Skander
 */
public class Statistique {
    private int id_stat;
    private String id_interface;
    private int id_u;
    private int nbr_visite;

    
    public int getNbr_visite() {
        return nbr_visite;
    }

    public void setNbr_visite(int nbr_visite) {
        this.nbr_visite = nbr_visite;
    }

    public Statistique() {
    }

    public Statistique(int id_stat, String id_interface, int id_u, int nbr_visite) {
        this.id_stat = id_stat;
        this.id_interface = id_interface;
        this.id_u = id_u;
        this.nbr_visite=nbr_visite;
    }

    public int getId_stat() {
        return id_stat;
    }

    public void setId_stat(int id_stat) {
        this.id_stat = id_stat;
    }

    public String getId_interface() {
        return id_interface;
    }

    public void setId_interface(String id_interface) {
        this.id_interface = id_interface;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public Statistique(String id_interface, int id_u) {
        this.id_interface = id_interface;
        this.id_u = id_u;
    }

    @Override
    public String toString() {
        return "Statistique{" + "id_stat=" + id_stat + ", id_interface=" + id_interface + ", id_u=" + id_u + '}';
    }
    
    
}
