/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Alerte;
import edu.baskel.entities.Membre;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AlerteCRUD {

    Connection cnx;

    public AlerteCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public void ajouterAlert(Alerte a) {

        try {
            String requete = "INSERT INTO alerte (id_u, descpription_a, latitude_a, longitude_a, adresse_a, date_a)"
                    + " VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement pst = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, a.getMembre().getId_u());
            pst.setString(2, a.getDescription_a());
            pst.setString(3, a.getLatitude_a());
            pst.setString(4, a.getLongitude_a());
            pst.setString(5, a.getAdresse_a());
            pst.executeUpdate();
            System.out.println("alert ajouter");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAlert(Alerte a) {
        try {
            String requete = "UPDATE alerte SET descpription_a = ?, latitude_a = ?, longitude_a = ?, adresse_a = ? WHERE id_alerte = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, a.getDescription_a());
            pst.setString(2, a.getLatitude_a());
            pst.setString(3, a.getLongitude_a());
            pst.setString(4, a.getAdresse_a());
            pst.setInt(5, a.getId_alert());
            pst.executeUpdate();
            System.out.println("alert updated");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void supprimerAlert(int id) {
        try {
            String requete = "DELETE FROM membre WHERE membre.id_u = ?";
            PreparedStatement pst = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("alert ajouter");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Alerte> getListeAlerte() {
        List<Alerte> listAlerte = new ArrayList<Alerte>();

        try {
            String requete = "SELECT* FROM alerte a join membre m on a.id_u = m.id_u";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Alerte a = new Alerte();
                a.setId_alert(rs.getInt("id_alerte"));
                a.setDescription_a(rs.getString("descpription_a"));
                a.setLatitude_a(rs.getString("latitude_a"));
                a.setLatitude_a(rs.getString("longitude_a"));
                a.setAdresse_a(rs.getString("adresse_a"));
                a.setDate_a(rs.getDate("date_a"));
                
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_alerte"));
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                m.setAdresse_u(rs.getString("adresse_u"));
                m.setEmail_u(rs.getString("email_u"));
                m.setSexe_u(rs.getString("sexe_u"));
                m.setDate_u(rs.getDate("date_u"));
                m.setMot_passe_u(rs.getString("mot_passe_u"));
                m.setNum_tel_u(rs.getString("num_tel_u"));
                m.setImage_u(rs.getString("image_u"));
                m.setType_u(rs.getString("type_u"));
                
                a.setMembre(m);
                System.out.println(a.toString());
                listAlerte.add(a);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listAlerte;

    }

}
