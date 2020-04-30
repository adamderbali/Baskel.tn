/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class ReparateurCRUD {

    Connection cnx;

    public ReparateurCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public Reparateur ajouteraparateue(Reparateur r) {
        try {
            String requete = "INSERT INTO membre (nom_u, prenom_u, adresse_u,email_u,sexe_u,date_u, mot_passe_u,num_tel_u)"
                    + "VALUES(?,?,?,?,?,?,?,?)";

            String requete2 = "INSERT INTO reparateur (adresse_loc,num_pro)"
                    + "VALUES(?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            PreparedStatement pst2 = cnx.prepareStatement(requete2);
            pst.setString(1, r.getNom_u());
            pst.setString(2, r.getPrenom_u());
            pst.setString(3, r.getAdresse_u());
            pst.setString(4, r.getEmail_u());
            pst.setString(5, r.getSexe_u());
            pst.setDate(6, r.getDate_u());
            pst.setString(7, r.getMot_passe_u());
            pst.setString(8, r.getNum_tel_u());
            pst2.setString(1, r.getAdresse_lo());
            pst2.setString(2, r.getNum_pro());

            pst.executeUpdate();
            pst2.executeUpdate();
            System.out.println("person added");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return r;
    }

}
