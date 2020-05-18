/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Participation;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
    public boolean ajouterEvenement(Evenement e) {

        try {
            String requete = "INSERT INTO evenement (nom_e,lieu_e,date_e,description_e,image_e)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getNom_e());
            pst.setString(2, e.getLieu_e());
            pst.setString(3, e.getDate_e());
            pst.setString(4, e.getDescription_e());
            pst.setString(5, e.getImage_e());
            /*    pst.setInt(6, e.getId_u());*/
            pst.executeUpdate();

            System.out.println("Evenement added!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public boolean supprimerEvenement(Evenement e) {

        try {
            String requete1 = "DELETE FROM `evenement` WHERE `id_e`=" + e.getId_e();
            System.out.println("-11111--sql" + requete1);
            System.out.println("-1111--" + e.getId_e());
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            /* pst1.setInt(1,e.getId_e());*/
            pst1.executeUpdate();
            System.out.println("Evenement supprimé");
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public boolean supprimerEvenement(int id_e) {

        try {
            String requete1 = "DELETE FROM evenement where id_e=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1, id_e);
            pst1.executeUpdate();
            System.out.println("Evenement supprimé");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    /* Update */
    public boolean updateEvenement(Evenement e) {

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
            return true;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }

    }

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
    /* Affichage liste des evenement lkol*/
    public List<Evenement> displayAllList() {

        List<Evenement> Listevent = new ArrayList<Evenement>();
        try {
            String requete = "SELECT * FROM evenement";
            PreparedStatement pst = cnx.prepareStatement(requete);
           // pst.setInt(1,id_u);
            ResultSet rs = pst.executeQuery();
              while (rs.next()) {
                Evenement e = new Evenement();
               
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e")); 
              //  e.setEtat_e(rs.getString("etat_e"));
                e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                e.getImage().setFitWidth(220);
                e.getImage().setFitHeight(110);
             /*   System.out.println("----"+e.getId_e());
                Participation p = new Participation();
                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setEtat_p(rs.getString("etat_p"));
                System.out.println("----"+p.getId_e());
                System.out.println("----"+p.getEtat_p());
                e.setPart(p);*/
               // e.setEtat_e(new Label("vous etes deha participé"));
              //  e.getEtat_e().setTextFill(Color.web("#2e856e"));
                Listevent.add(e);}
                System.out.println("--------------+++++++++------------");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
    
  
    
/*liste des evenement eli creer par user:id_u*/
    public List<Evenement> displayByUser(int id_u) {

        List<Evenement> Listeventuser = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement WHERE id_u=" + id_u;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));
                e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                e.getImage().setFitWidth(220);
                e.getImage().setFitHeight(110);
                Listeventuser.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listeventuser;
    }

    /* Affichage liste des participant deans les evenement par user */
    
        public int nombreEvent(){
              int nb=0;
              
        try { 
            String req1 = "SELECT count(*) AS nombreEvent from evenement";
            System.out.println("+++++++++++" + req1);
            PreparedStatement pst = cnx.prepareStatement(req1);
          
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
            nb = rs.getInt(1);
            
            System.out.println("----------"+nb);
            } 
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
          System.out.println("-------------"+nb);
          return nb;
          
      }
     public List<Evenement> displayByEvent(int id_e) {

        List<Evenement> Listeventuser = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement WHERE id_e=" + id_e;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));
                e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                e.getImage().setFitWidth(200);
                e.getImage().setFitHeight(110);
                Listeventuser.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listeventuser;
    }
  /* liste des evenement lkol ela eli mparticipi fehom user : id_u*/   
      public List<Evenement> displayAllListE(int id_u) {

        List<Evenement> Listevente = new ArrayList<Evenement>();
        try {
            String requete = "select * from evenement where id_e not IN(SELECT id_e from participation where id_u=?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(1,id_u);
            ResultSet rs = pst.executeQuery();
              while (rs.next()) {
                Evenement e = new Evenement();
               
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e")); 
              //  e.setEtat_e(rs.getString("etat_e"));
              e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                e.getImage().setFitWidth(220);
                e.getImage().setFitHeight(110);
                 e.setEtat_e(new Label("vous n'avez pas participé"));
                e.getEtat_e().setTextFill(Color.web("#2e856e"));
                
                   
              //  System.out.println("----"+e.getId_e());
              /*  Participation p = new Participation();
                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setEtat_p(rs.getString("etat_p"));
                System.out.println("----"+p.getId_e());
                System.out.println("----"+p.getEtat_p());
                e.setPart(p);*/
                 Listevente.add(e);}
                System.out.println("--------------+++++++++------------");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevente;
    }
     
    /* Affichage list mta3 les evenements eli mparticipi fehom user (id_u)*/ 
      public List<Evenement> displayAllListU(int id_u) {

        List<Evenement> Listevent = new ArrayList<Evenement>();
        try {
            String requete = "SELECT * from evenement e join participation p on p.id_e=e.id_e and p.id_u="+id_u;
            PreparedStatement pst = cnx.prepareStatement(requete);
           // pst.setInt(1,id_u);
           // pst.setInt(1,id_u);
            ResultSet rs = pst.executeQuery();
              while (rs.next()) {
                Evenement e = new Evenement();
               
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e")); 
                e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                e.getImage().setFitWidth(220);
                e.getImage().setFitHeight(110);
                e.setEtat_e(new Label("vous etes deja un participant"));
                e.getEtat_e().setTextFill(Color.web("#2e856e"));
                
                System.out.println("----"+e.getId_e());
               /* Participation p = new Participation();
                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setEtat_p(rs.getString("etat_p"));
                System.out.println("-----------------++++++++++++++++************////////"+p);
              
             /*   System.out.println("----"+p.getEtat_p());
                e.setPart(p);*/
               
               
                Listevent.add(e);}
                System.out.println("--------------+++++++++****************"+Listevent);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
      
      public boolean desactiverCell(int i){
          boolean a = false;
          Evenement ev = new Evenement();
          EvenementCRUD ec = new EvenementCRUD();
   
                for (Evenement e : ec.displayAllListU(i)) {

         if (e.getEtat_e().getText()=="vous etes deja participé") {
                             
                            return true;
                         } else {
                            return false;
                             
                         }} 
            return a;    
            
      }    
            
      
  
    }
   
       

