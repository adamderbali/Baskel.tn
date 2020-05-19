/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.HistoriqueCnx;
import edu.baskel.entities.Membre;
import edu.baskel.utils.ConnectionBD;
import static java.lang.Integer.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class HistoriqueCRUD {

    public static Connection cnx;

    EnvoiMail e = new EnvoiMail();
    MembreCRUD mc = new MembreCRUD();

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

    /*
    //check et envoie du mail
    public void LastCnx() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

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

    }*/
    //check et envoie du mail
    public void LastCnx2() throws Exception {

        LocalDate loc = LocalDate.now();

        for (HistoriqueCnx h : getlistHistorique()) {
            Membre m = mc.AfficherMembreById(h.getId_u());
            String d = h.getDate_cnx();
            LocalDate l = LocalDate.parse(d);
            Period diff = Period.between(l, loc);
            int année = valueOf(diff.getYears());
            int mois = valueOf(diff.getMonths());
            int jours = valueOf(diff.getDays());
            String year = String.valueOf(diff.getYears());
            String month = String.valueOf(diff.getMonths());
            String day = String.valueOf(diff.getDays());

            if (jours >= 7 || mois >= 1 || année >= 1) {

                String em = m.getEmail_u();
                if (jours != 0 && mois != 0 && année != 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya :  " + year + "  années" + " , " + month + "  mois" + "  et  " + day + "  jours");
                }
                if (jours != 0 && mois != 0 && année == 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya :" + month + "  mois" + "  et  " + day + "  jours");
                }
                if (jours != 0 && mois == 0 && année == 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya :" + day + "  jours");
                }
                if (jours == 0 && mois != 0 && année == 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya :" + month + "  mois");
                }
                if (jours == 0 && mois != 0 && année != 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya : " + year + "  années" + " et " + month + "  mois");
                }
                if (jours != 0 && mois != 0 && année == 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya : " + month + "  mois"+ "  et  " + day + "  jours");
                }
                if (jours == 0 && mois == 0 && année != 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya : " + year + "  années");
                }
                if (jours == 0 && mois != 0 && année != 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya : " + year + "  années"+ " et " + month + "  mois");
                }
                if (jours != 0 && mois == 0 && année != 0) {
                    e.envoyerMailHistorique(em, "Votre derniere connexion date d il ya : " + year + "  années" + "  et  " + day + "  jours");
                }
                System.out.println(em);

            } else {
                System.out.println("Membre actif");
            }
        }

    }

    
}
