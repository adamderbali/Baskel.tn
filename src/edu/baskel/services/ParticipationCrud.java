/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.utils.ConnectionBD;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.ir.ObjectNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sabri
 */
public class ParticipationCrud {

    Connection cnx;

    public ParticipationCrud() {

        cnx = ConnectionBD.getInstance().getCnx();

    }

    public boolean ajouterParticipation(Participation p) {

        try {
            String req = "INSERT INTO participation (id_u,id_e)"
                    + "VALUES (?,?)";

            PreparedStatement pst = cnx.prepareStatement(req);
            /*    pst.setInt(1,p.getId_user());*/
            pst.setInt(1, p.getId_u());
            pst.setInt(2, p.getId_e());

            pst.executeUpdate();
            System.out.println("Participation added!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            ex.printStackTrace();
            return false;
        }
    }

    /*
public void supprimerParticipation(int id_e,int id_u){
             
         try {
             String requete1 = "DELETE FROM participation where id_e=? and id_u=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             pst1.setInt(1,id_e);
             pst1.setInt(2,id_u);
             pst1.executeUpdate();
             System.out.println("Participation annulé!");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
     */
 /* suppression participation*/
    public boolean supprimerParticipationP(Participation p) {

        try {
            String requete2 = "DELETE FROM participation where id_e=? AND id_u=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
            pst1.setInt(1, p.getId_e());
            pst1.setInt(2, p.getId_u());
            pst1.executeUpdate();
            System.out.println("Participation annulé!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }


    /* Affichage participation dans les evenement par user*/
    public List<Object> displayByUserP() {
        /*int id_user*/

        ObjectMapper objMapper = new ObjectMapper();
        List<Object> Listparticipation = new ArrayList<>();
        /*AND*/

 /*   + " id_u=" + id_user;*/
        try {
            String requete = "SELECT* FROM evenement e join participation p on e.id_e = p.id_e";
            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("1-------------" + rs.getString("nom_e"));
//                Evenement e = new Evenement();
//                e.setId_e(rs.getInt("id_e"));
//                e.setNom_e(rs.getString("nom_e"));
//                e.setLieu_e(rs.getString("lieu_e"));
//                e.setDate_e(rs.getString("date_e"));
//                e.setDescription_e(rs.getString("description_e"));
//                e.setImage_e(rs.getString("image_e"));
//               
//                Participation p = new Participation();

//                p.setId_e(rs.getInt("id_e"));  
//                p.setId_u(rs.getInt("id_u"));  
//                p.setDate_insc(rs.getDate("date_insc"));  
                // p.setEvent(e);
                org.codehaus.jackson.node.ObjectNode finalresu = objMapper.createObjectNode();
                finalresu.put("id_e", rs.getInt("id_e"));
                finalresu.put("id_u", rs.getInt("id_u"));
                finalresu.put("date_insc", rs.getDate("date_insc").toString());
                finalresu.put("id_e", rs.getInt("id_e"));
                finalresu.put("nom_e", rs.getString("nom_e"));
                finalresu.put("lieu_e", rs.getString("lieu_e"));
                finalresu.put("date_e", rs.getString("date_e"));
                finalresu.put("description_e", rs.getString("description_e"));
                finalresu.put("image_e", rs.getString("image_e"));

                Listparticipation.add(finalresu);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listparticipation;
    }

    /* Affichage tt les participation*/
    public List<Participation> displayP() {

        List<Participation> Listparticipation = new ArrayList<Participation>();

        try {
            String requete = "SELECT* FROM participation ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);

            while (rs.next()) {

                Participation p = new Participation();
                p.setId_u(rs.getInt("id_u"));
                p.setId_e(rs.getInt("id_e"));
                p.setDate_insc(rs.getDate("date_insc"));
                System.out.println(p.toString());
                Listparticipation.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listparticipation;
    }

    /* Affichage liste des memebre qui ont participé à un evenement par user*/
    public List<Evenement> displayParticipantP() {

        Participation p;
        Membre m;
        Evenement e = null;
        List<Evenement> ListEventPaticipation = new ArrayList<>();
        String requete = "SELECT m.email_u, m.nom_u, m.prenom_u FROM membre m JOIN participation p ON m.id_u=p.id_u JOIN evenement e ON e.id_e=p.id_e";

        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {

                m = new Membre(rs.getInt("id_u"), rs.getString("nom_u"), rs.getString("prenom_u"), rs.getString("adresse_u"), rs.getString("email_u"), rs.getDate("date"
                ), rs.getString("email_u"), rs.getString("email_u"));
                /* System.out.println(m.getId_u());*/
                e = new Evenement(rs.getInt("id_e"), rs.getString("nom_e"), m);
                p = new Participation(rs.getInt("id_e"), rs.getInt("id_u"), rs.getDate("date_insc"), e);
                //    ListEventPaticipation.add(p);

            }
            return ListEventPaticipation;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }

    /* Affichage liste participant */
    public List<Participation> displayByUserPP() {

        List<Participation> Listparticipation = new ArrayList<Participation>();
        try {
            String requete = "SELECT* FROM evenement e join participation p on e.id_e = p.id_e";
            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("1-------------" + rs.getString("nom_e"));
                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));

                Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));

                p.setEvent(e);

                Listparticipation.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listparticipation;
    }

    /*Affichage liste des participation par user*/
    public List<Participation> displayByUser(int id_u) {

        List<Participation> Listparticipation = new ArrayList<Participation>();
        try {
            String requete = "SELECT* FROM evenement e join participation p on e.id_e = p.id_e WHERE p.id_u=" + id_u;
            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("1-------------" + rs.getString("nom_e"));
                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));

                Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));

                p.setEvent(e);

                Listparticipation.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listparticipation;
    }
     public List<Participation> displayParticipant(int id_u) {

        List<Participation> ListEventPaticipation = new ArrayList<>();

        try {
            String requete = "SELECT * FROM  evenement e JOIN participation p ON  e.id_e = p.id_e JOIN membre m ON m.id_u = p.id_u  WHERE e.id_u =" + id_u;
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

          while (rs.next()) {
              
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                m.setEmail_u(rs.getString("email_u"));
                System.out.println("1-------------" + rs.getString("nom_u"));
                
                System.out.println("1-------------" + rs.getString("nom_e"));
                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                
                Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));
                System.out.println("1-------------" + rs.getString("id_e"));
                
                p.setMbre(m);
                p.setEvent(e);

                ListEventPaticipation.add(p);
                System.out.println("-----------"+ListEventPaticipation);

            }
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }
    
    
    
}
