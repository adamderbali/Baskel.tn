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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class EvenementCRUD {

    Connection cnx;

    public EvenementCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    /* Insert */
    public void ajouterEvenement(Evenement e) {

        try {
            String requete = "INSERT INTO evenement (nom_e,lieu_e,date_e,description_e,image_e)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getNom_e());
            pst.setString(2, e.getLieu_e());
            pst.setString(3, e.getDate_e());
            pst.setString(4, e.getDescription_e());
            pst.setString(5, e.getImage_e());
            pst.setInt(6, e.getId_user().getId_u());
            pst.executeUpdate();
            System.out.println("Evenement added!");
        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerEvenement(Evenement e) {

        try {
            String requete1 = "DELETE FROM `evenement` WHERE `id_e`=" + e.getId_e();
            System.out.println("-11111--sql" + requete1);
            System.out.println("-1111--" + e.getId_e());
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            /* pst1.setInt(1,e.getId_e());*/
            pst1.executeUpdate();
            System.out.println("Evenement supprimé");
        } catch (SQLException ex) {
        }

    }

    /* Delete */

 /*  public void supprimerEvenement(Evenement e){
             
         try {
             String requete1 = "DELETE FROM evenement where id_e=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             pst1.setInt(1,e.getId_e());
             pst1.executeUpdate();
             System.out.println("Evenement supprimé");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }*/
    public void supprimerEvenement(int id_e) {

        try {
            String requete1 = "DELETE FROM evenement where id_e=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1, id_e);
            pst1.executeUpdate();
            System.out.println("Evenement supprimé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /* Update */
    public void updateEvenement(Evenement e) {

        try {
            String requete2 = "UPDATE evenement SET nom_e=?,lieu_e=?,date_e=?,description_e=?,image_e=?"
                    + " WHERE id_e=?";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);

            pst2.setString(1, e.getNom_e());
            pst2.setString(2, e.getLieu_e());
            pst2.setString(3, e.getDate_e());
            pst2.setString(4, e.getDescription_e());
            pst2.setString(5, e.getImage_e());
            pst2.setInt(6, e.getId_e());

            pst2.executeUpdate();
            System.out.println("Evenement modifie");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

    }

    /* 
        public void updateEvenement(Evenement e,int id_e) {
            
         try {
             String requete2 = "UPDATE evenement SET nom_e=?,lieu_e=?,date_e=?,description_e=?"
                     +" WHERE id_e=?";
             PreparedStatement pst2 = cnx.prepareStatement(requete2);
             pst2.setInt(5,id_e);
             pst2.setString(1,e.getNom_e());
             pst2.setString(2,e.getLieu_e());
             pst2.setString(3,e.getDate_e());
             pst2.setString(4,e.getDescription_e());
         
             pst2.setString(4,e.getImage_e()):
             pst2.executeUpdate();
             System.out.println("Evenement modifie");
         } catch (SQLException ex) {
             
             System.out.println(ex.getMessage());
         }
            
        }
     */
 /* Suppression Auto Admin
        public void supprimerEventAuto(){
        try {
            String requeste = "DELETE FROM evenement where SYSDATE()>date_e";
            Statement st = cnx.createStatement();
            st.execute(requeste);
            System.out.println("evenement est suprrime");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        
    }*/
 /*    public List<Evenement> displayByUser(int id_user){
       
            Evenement e;
            List<Evenement> Listevent = new ArrayList<>();
            String requete = "SELECT * FROM evenement WHERE id_u="+id_user;
            PreparedStatement preparedStatement = null;
           try {  
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
                e= new Evenement(rs.getInt("id_e"),rs.getString("nom_e"), rs.getString("lieu_e"),rs.getString("date_e"),
                        rs.getString("description_e"),rs.getString("image_e"));
             Listevent.add(e);
                
            }
           return Listevent;
        }
        
            catch (SQLException ex) {
            ex.printStackTrace();
        }
   
        
       return Listevent; 
}*/
    public List<Evenement> displayAllList() {

        Evenement e;
        List<Evenement> Listevent = new ArrayList<>();
        String requete = "SELECT * FROM evenement";

        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                e = new Evenement(rs.getInt("id_e"), rs.getString("nom_e"), rs.getString("lieu_e"), rs.getString("date_e"),
                        rs.getString("description_e"), rs.getString("image_e"));
                Listevent.add(e);

            }
            return Listevent;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listevent;
    }

    public List<Evenement> displayByUser(int id_user) {

        Evenement e;
        List<Evenement> Listeventuser = new ArrayList<>();
        String requete = "SELECT * FROM evenement WHERE id_u=" + id_user;

        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                e = new Evenement(rs.getInt("id_e"), rs.getString("nom_e"), rs.getString("lieu_e"), rs.getString("date_e"),
                        rs.getString("description_e"), rs.getString("image_e"));
                Listeventuser.add(e);

            }
            return Listeventuser;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listeventuser;
    }

    /* Affichage liste des participant deans les evenement par user */
    public List<Evenement> displayParticipant(int id_u) {

        Participation p;
        Membre m;
        Evenement e = null;
        List<Evenement> ListEventPaticipation = new ArrayList<>();
        String requete = "SELECT evenement.nom_e,membre.email_u, membre.nom_u,membre.prenom_u FROM evenement INNER JOIN participation on evenement.id_e=participation.id_e INNER JOIN membre  ON membre.id_u=participation.id_u"
                + "AND evenement.id_u='" + id_u + "'";

        try {
            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                m = new Membre(rs.getInt("id_u"), rs.getString("nom_u"), rs.getString("prenom_u"), rs.getString("adresse_u"), rs.getString("email_u"), rs.getDate("date"
                ), rs.getString("email_u"), rs.getString("email_u") );
                /* System.out.println(m.getId_u());*/
                 e = new Evenement(rs.getInt("id_e"), rs.getString("nom_e"), m);
                ListEventPaticipation.add(e);

            }
            return ListEventPaticipation;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }

}
