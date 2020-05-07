/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Avis;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class AvisCRUD {

    Connection cnx;

    public AvisCRUD() {
        this.cnx = ConnectionBD.getInstance().getCnx();
    }

    public void ajouterAvis(Avis a) {
        try {
            String requete = "INSERT INTO avis (id_u, id_r, note, commentaire, date_avis)"
                    + " VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a.getMembre().getId_u());
            pst.setInt(2, a.getReparateur().getId_u());
            pst.setInt(3, a.getNote_av());
            pst.setString(4, a.getDesc_av());
            pst.executeUpdate();
            System.out.println("avis ajouter");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getnbrAvisperDep(Reparateur r) {
        int n = 0;
        try {

            String requete = "SELECT count(*) as nbravis FROM Avis where id_r = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId_u());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                n = rs.getInt("nbravis");
            }

        } catch (SQLException ex) {
        }
        System.out.println("nbr avis :" + n);
        return n;
    }

    public Float getavgAvisperDep(Reparateur r) {
        Float n = null;
        try {

            String requete = "SELECT avg(note) as avgavis FROM Avis where id_r = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setFloat(1, r.getId_u());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                n = rs.getFloat("avgavis");
            }

        } catch (SQLException ex) {
        }
        System.out.println("avg avis :" + n);
        return n;
    }

    public List<Avis> getListeAvis() {
        List<Avis> listeavis = new ArrayList<Avis>();

        try {
            String requete = "SELECT a.*, m.* , r.*,"
                    + "mr.id_u as id_ur, mr.nom_u as nom_ur,mr.prenom_u as prenom_ur,mr.adresse_u as adresse_ur,"
                    + "mr.email_u as email_ur,mr.sexe_u as sexe_ur,mr.date_u as date_ur,mr.mot_passe_u as mot_passe_ur,"
                    + "mr.num_tel_u as num_tel_ur,mr.image_u as image_ur,mr.type_u as image_ur,mr.type_u as type_ur"
                    + " FROM Avis a join membre m on a.id_u = m.id_u join reparateur r on a.id_r=r.id_u"
                    + " join membre mr on r.id_u = mr.id_u ";
            PreparedStatement pst = cnx.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
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
                System.out.println(m.toString());

                Reparateur r = new Reparateur();
                r.setId_u(rs.getInt("id_ur"));
                r.setNom_u(rs.getString("nom_ur"));
                r.setPrenom_u(rs.getString("prenom_ur"));
                r.setAdresse_u(rs.getString("adresse_ur"));
                r.setEmail_u(rs.getString("email_ur"));
                r.setSexe_u(rs.getString("sexe_ur"));
                r.setDate_u(rs.getDate("date_ur"));
                r.setMot_passe_u(rs.getString("mot_passe_ur"));
                r.setNum_tel_u(rs.getString("num_tel_ur"));
                r.setImage_u(rs.getString("image_ur"));
                r.setType_u(rs.getString("type_ur"));
                r.setAdresse_lo(rs.getString("type_ur"));
                r.setLocal_nom(rs.getString("nom_local"));
                r.setNum_pro(rs.getString("num_pro"));
                r.setLatitude(rs.getString("latitude"));
                r.setLongitude(rs.getString("longitude"));
                System.out.println(r.toString());
                Avis av = new Avis(m, r);
                av.setId_av(rs.getInt("id_ur"));
                av.setDesc_av(rs.getString("commentaire"));
                av.setNote_av(rs.getInt("note"));
                av.setDate_avis(rs.getTimestamp("date_avis"));
                System.out.println(av.toString());

                listeavis.add(av);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeavis;

    }

    public List<Avis> getListeAvisByReparateur(Reparateur rep) {
        List<Avis> listeavis = new ArrayList<Avis>();

        try {
            String requete = "SELECT a.*, m.* , r.*,"
                    + "mr.id_u as id_ur, mr.nom_u as nom_ur,mr.prenom_u as prenom_ur,mr.adresse_u as adresse_ur,"
                    + "mr.email_u as email_ur,mr.sexe_u as sexe_ur,mr.date_u as date_ur,mr.mot_passe_u as mot_passe_ur,"
                    + "mr.num_tel_u as num_tel_ur,mr.image_u as image_ur,mr.type_u as image_ur,mr.type_u as type_ur"
                    + " FROM Avis a join membre m on a.id_u = m.id_u join reparateur r on a.id_r=r.id_u"
                    + " join membre mr on r.id_u = mr.id_u where a.id_r=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, rep.getId_u());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
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
                System.out.println(m.toString());

                Reparateur r = new Reparateur();
                r.setId_u(rs.getInt("id_ur"));
                r.setNom_u(rs.getString("nom_ur"));
                r.setPrenom_u(rs.getString("prenom_ur"));
                r.setAdresse_u(rs.getString("adresse_ur"));
                r.setEmail_u(rs.getString("email_ur"));
                r.setSexe_u(rs.getString("sexe_ur"));
                r.setDate_u(rs.getDate("date_ur"));
                r.setMot_passe_u(rs.getString("mot_passe_ur"));
                r.setNum_tel_u(rs.getString("num_tel_ur"));
                r.setImage_u(rs.getString("image_ur"));
                r.setType_u(rs.getString("type_ur"));
                r.setAdresse_lo(rs.getString("type_ur"));
                r.setLocal_nom(rs.getString("nom_local"));
                r.setNum_pro(rs.getString("num_pro"));
                r.setLatitude(rs.getString("latitude"));
                r.setLongitude(rs.getString("longitude"));
                System.out.println(r.toString());
                Avis av = new Avis(m, r);
                av.setId_av(rs.getInt("id_ur"));
                av.setDesc_av(rs.getString("commentaire"));
                av.setNote_av(rs.getInt("note"));
                System.out.println(av.toString());

                listeavis.add(av);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeavis;

    }

}
