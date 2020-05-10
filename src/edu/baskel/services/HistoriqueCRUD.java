/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.HistoriqueCnx;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class HistoriqueCRUD {

    public static Connection cnx;

    EnvoiMail e = new EnvoiMail();

    public HistoriqueCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    //ajouter un historique 
    public void ajouterHistorique(int id) {
        try {
            String datee = java.time.LocalDate.now().toString();
            String requete = "INSERT INTO historique (id_u, date_cnx)"
                    + " VALUES (?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, datee);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //liste d historiques
    public List<HistoriqueCnx> getlistHistorique() {
        List<HistoriqueCnx> listeHistorique = new ArrayList<>();

        try {
            String requete = "SELECT* FROM historique";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                HistoriqueCnx h = new HistoriqueCnx();
                h.setId_u(rs.getInt(1));
                h.setDate_cnx(rs.getString(2));

                listeHistorique.add(h);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeHistorique;

    }

    //update d un historique
    public void UpdateLastCnx(int id) {
        String datee = java.time.LocalDate.now().toString();

        try {
            String req = "UPDATE historique SET date_cnx=? where id_u=?";
            PreparedStatement p = cnx.prepareStatement(req);
            p.setString(1, datee);
            p.setInt(2, id);
            p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //check et envoie du mail
    public  void LastCnx() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        MembreCRUD mc = new MembreCRUD();
        EnvoiMail e = new EnvoiMail();

        for (HistoriqueCnx h : getlistHistorique()) {
            LocalDate loc = LocalDate.now();
            LocalDate l = LocalDate.parse(h.getDate_cnx());
            if (loc.compareTo(l) >= 7) {
                String sq1 = "SELECT * FROM membre where id_u=?";
                PreparedStatement prep = cnx.prepareStatement(sq1);
                prep.setInt(1, h.getId_u());
                ResultSet res = prep.executeQuery();

                if (res.next()) {

                    String em = res.getString("email_u");
                    e.envoyerMailHistorique(em);
                    System.out.println(em);
                    System.out.println(loc.compareTo(l));

                } else {
                    System.out.println("2");
                }

            } else {
                System.out.println("Membre actif");
            }
        }

    }
/*
    public static void main(String[] args) throws Exception {
        HistoriqueCRUD h = new HistoriqueCRUD();
        // h.ajouterHistorique(23);
        //h.LastCnx();
        //    System.out.println(h.getlistHistorique());
        h.LastCnx();

    }*/
}
