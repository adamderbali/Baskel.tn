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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ReparateurCRUD {

    Connection cnx;

    public ReparateurCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public void ajouterReparateur(Reparateur r) {
        try {
            String requete = "INSERT INTO membre (nom_u, prenom_u, adresse_u,email_u,sexe_u,date_u, mot_passe_u,num_tel_u)"
                    + "VALUES(?,?,?,?,?,?,?,?)";

            String requete2 = "INSERT INTO reparateur (id_u,adresse_loc, nom_local,num_pro,latitude,longitude)"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, r.getNom_u());
            pst.setString(2, r.getPrenom_u());
            pst.setString(3, r.getAdresse_u());
            pst.setString(4, r.getEmail_u());
            pst.setString(5, r.getSexe_u());
            pst.setDate(6, r.getDate_u());
            pst.setString(7, r.getMot_passe_u());
            pst.setString(8, r.getNum_tel_u());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                //get las id inserted for member
                int last_inserted_id = rs.getInt(1);
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                pst2.setInt(1, last_inserted_id);
                pst2.setString(2, r.getAdresse_lo());
                pst2.setString(3, r.getLocal_nom());
                pst2.setString(4, r.getNum_pro());
                pst2.setString(5, r.getLatitude());
                pst2.setString(6, r.getLongitude());
                pst2.executeUpdate();
                System.out.println("Person added!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateReparateur(Reparateur r) {
        try {
            //Updete memebre
            MembreCRUD mcrd = new MembreCRUD();
            mcrd.updateMembre(r);
            //Update Reparateur
            String requete = "UPDATE reparateur SET adresse_loc=?, nom_local=?, num_pro=?,latitude=?, longitude=? WHERE id_u=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, r.getAdresse_lo());
            pst.setString(2, r.getLocal_nom());
            pst.setString(3, r.getNum_pro());
            pst.setString(4, r.getLatitude());
            pst.setString(5, r.getLongitude());
            pst.setInt(5, r.getId_u());
            pst.executeUpdate();
            System.out.println("Person added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerReparateur(int id) {
        //work with onDelete Cascade
        MembreCRUD mcrd = new MembreCRUD();
        mcrd.supprimerMembre(id);
    }

    public List<Reparateur> getListeReparateur() {
        List<Reparateur> listeMembre = new ArrayList<Reparateur>();

        try {
            String requete = "SELECT * FROM `reparateur` r join membre m on r.id_u = m.id_u";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reparateur r = new Reparateur();
                r.setId_u(rs.getInt(1));
                r.setAdresse_lo(rs.getString(2));
                r.setLocal_nom(rs.getString(3));
                r.setNum_pro(rs.getString(4));
                r.setLatitude(rs.getString(5));
                r.setLongitude(rs.getString(6));
                r.setNom_u(rs.getString(8));
                r.setPrenom_u(rs.getString(9));
                r.setAdresse_u(rs.getString(10));
                r.setEmail_u(rs.getString(11));
                r.setSexe_u(rs.getString(12));
                r.setDate_u(rs.getDate(13));
                r.setMot_passe_u(rs.getString(14));
                r.setNum_tel_u(rs.getString(15));
                r.setImage_u(rs.getString(16));
                r.setType_u(rs.getString(17));
                System.out.println(r.toString());
                listeMembre.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeMembre;

    }

    //retour un reparateur en passant l'id du reparateur
    public Reparateur getReparateurById(int id) {
        Reparateur r = new Reparateur();
        try {
            String requete = "SELECT * FROM `reparateur` r join membre m on r.id_u = m.id_u where r.id_u= ?";
            PreparedStatement st = cnx.prepareStatement(requete);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                r.setId_u(rs.getInt(1));
                r.setAdresse_lo(rs.getString(2));
                r.setLocal_nom(rs.getString(3));
                r.setNum_pro(rs.getString(4));
                r.setLatitude(rs.getString(5));
                r.setLongitude(rs.getString(6));
                r.setNom_u(rs.getString(8));
                r.setPrenom_u(rs.getString(9));
                r.setAdresse_u(rs.getString(10));
                r.setEmail_u(rs.getString(11));
                r.setSexe_u(rs.getString(12));
                r.setDate_u(rs.getDate(13));
                r.setMot_passe_u(rs.getString(14));
                r.setNum_tel_u(rs.getString(15));
                r.setImage_u(rs.getString(16));
                r.setType_u(rs.getString(17));
                System.out.println(r.toString());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return r;
    }

}
