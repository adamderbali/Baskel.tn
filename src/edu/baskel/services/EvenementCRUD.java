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
import edu.baskel.utils.SessionInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.controlsfx.control.Rating;

/**
 *
 * @author sabri
 */
public class EvenementCRUD {
 Membre ml = SessionInfo.getLoggedM();
    Connection cnx;

    public EvenementCRUD() {

        cnx = ConnectionBD.getInstance().getCnx();
    }

    /* Insert */
    public boolean ajouterEvenement(Evenement e) {

        try {
            String requete = "INSERT INTO evenement (nom_e,lieu_e,date_e,description_e,image_e,nbr_max_e,id_u)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(
                    requete);
            pst.setString(1, e.getNom_e());
            pst.setString(2, e.getLieu_e());
            pst.setString(3, e.getDate_e());
            pst.setString(4, e.getDescription_e());
            pst.setString(5, e.getImage_e());
            pst.setInt(6, e.getNbr_max_e());

            pst.setInt(7, e.getId_u());
            pst.executeUpdate();

            System.out.println("Evenement added!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            System.out.println(ex.getMessage());
            return false;
        }

    }

    /* supprimer evenement*/
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

    /* verfier ken fama evenement nbr_participant < nbr_max_e w nbr_max_e=!0*/
    public boolean verifierParticipant(int id_e) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();
        try {

            String requete = "SELECT * FROM evenement WHERE nbr_participant<nbr_max_e AND id_e=? AND nbr_max_e!=0";

            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_e);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                return true;

            }
            System.out.println("resultat trouvé");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("++++++++++++++++");
            System.out.println("resultat non trouvé");
        }
        return false;

    }

    /* verifier ken fama evenement nbr_max_e =0 ya3ni membre madakhelch nbr eli yheb aih mta3 participant*/
    public boolean verifierNbrMaxE(int id_e) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();
        try {

            String requete = "SELECT * FROM evenement WHERE nbr_max_e=0 AND id_e=?";

            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_e);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                return true;

            }
            System.out.println("resultat trouvé");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("++++++++++++++++");
            System.out.println("resultat non trouvé");
        }
        return false;
    }
    
     public boolean verifierNom(String nom_e) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();
        try {

            String requete = "SELECT * FROM evenement WHERE nom_e=?";

            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, nom_e);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                return true;

            }
            System.out.println("resultat trouvé");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("++++++++++++++++");
            System.out.println("resultat non trouvé");
        }
        return false;
    }

    public boolean verifierS(int nbr_max_e) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();

        if (nbr_max_e == 0) {
            System.out.println("resultat ok");
            return true;
        } else {
            System.out.println("resultat ok");
            return false;
        }

    }

    public boolean verifierSs(int nbr_max_e, int nbr_participant) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();

        if ((nbr_max_e == nbr_participant) && (nbr_max_e != 0)) {
            System.out.println("resultat ok");
            return true;
        } else {
            System.out.println("resultat ok");
            return false;
        }
    }
    
    public boolean listEventParMembre(int id_u,int id_e){
        
          Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();
        try {

            String requete = "SELECT * FROM evenement where id_u=? AND id_e=?";

            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_u);
            pst.setInt(2, id_e);
      

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                return true;

            }
            System.out.println("resultat trouvé");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("++++++++++++++++");
            System.out.println("resultat non trouvé");
        }
        return false;

    }

    public boolean verifierSsS(int nbr_max_e, int nbr_participant) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();

        if (nbr_max_e > nbr_participant) {
            System.out.println("resultat ok");
            return true;
        } else {
            System.out.println("resultat ok");
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
            String requete2 = "UPDATE evenement SET nom_e=?,lieu_e=?,date_e=?,description_e=?,image_e=?,nbr_max_e=?"
                    + " WHERE id_e=?";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);

            pst2.setString(1, e.getNom_e());
            pst2.setString(2, e.getLieu_e());
            pst2.setString(3, e.getDate_e());
            pst2.setString(4, e.getDescription_e());
            pst2.setString(5, e.getImage_e());
            pst2.setInt(6, e.getNbr_max_e());
            pst2.setInt(7, e.getId_e());

            pst2.executeUpdate();
            System.out.println("Evenement modifie");
            return true;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }

    }

    /* update nbr_participant yzid b 1 ki yabda nbr_max_e =!0 */
    public boolean nbrParticipant(int id_e) {
        Evenement e = new Evenement();
        try {
            String requete1 = "UPDATE evenement SET nbr_participant=nbr_participant+1 WHERE id_e=? AND nbr_max_e!=0";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            // pst1.setInt(1,e.getNbr_participant());
            pst1.setInt(1, id_e);

            pst1.executeUpdate();
            System.out.println("nbr participant modifié");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean nbrParticipantDelete(int id_e) {
        Evenement e = new Evenement();
        try {
            String requete1 = "UPDATE evenement SET nbr_participant=nbr_participant-1 WHERE id_e=? AND nbr_max_e!=0";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            // pst1.setInt(1,e.getNbr_participant());
            pst1.setInt(1, id_e);

            pst1.executeUpdate();
            System.out.println("nbr participant modifié");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            String requete = "select * from evenement where( STR_TO_DATE(date_e, '%d/%m/%Y'))> SYSDATE()";
            PreparedStatement pst = cnx.prepareStatement(requete);
            // pst.setInt(1,id_u);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                EvenementCRUD ev = new EvenementCRUD();
                e.setId_e(rs.getInt("id_e"));
                e.setId_u(rs.getInt("id_u"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                e.setNbr_participant(rs.getInt("nbr_participant"));
               
                //  e.setEtat_e(rs.getString("etat_e"));

                if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
                else{
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
                
                
              if(ev.listEventParMembre(ml.getId_u(),e.getId_e())==true){
                     e.setEtat_p(new Label("Vous etes l'organisateur\nde l'evenement"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("++++++++++++++++++ winou si id"+e.getId_u());
                  System.out.println("za3ma id e yodhoher !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+e.getId_e());
                   }
              else{
                if (ev.verifierS(e.getNbr_max_e()) == true) {
                    e.setEtat_p(new Label("La participation à cet\nevenement est illimité"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("Nombre ilimite " + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                } else if (ev.verifierSsS(e.getNbr_max_e(), e.getNbr_participant()) == true) {
                    String reste = String.valueOf((e.getNbr_max_e()) - (e.getNbr_participant()));
                    e.setEtat_p(new Label("Il reste encore:" + reste + " places"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("Reste des place  " + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                } else if (ev.verifierSs(e.getNbr_max_e(), e.getNbr_participant()) == true) {
                    e.setEtat_p(new Label("Evenement saturé"));
                    e.getEtat_p().setTextFill(Color.web("#c4151c"));

                    System.out.println("Nombre atteint" + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                }
              }


                Listevent.add(e);
            }
            System.out.println("--------------+++++++++------------");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
    
     public List<Evenement> displayAllListAdmin() {

        List<Evenement> Listevent = new ArrayList<Evenement>();
        try {
            String requete = "SELECT * FROM evenement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            // pst.setInt(1,id_u);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                EvenementCRUD ev = new EvenementCRUD();
             
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));
         
                e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                
                 
                    
                
               
                Listevent.add(e);
            }
            System.out.println("--------------+++++++++------------");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
 public List<Evenement> displayAllListNonPar(int id_u) {

        List<Evenement> Listevent = new ArrayList<Evenement>();
        try {
             
            String requete = "select * from evenement e  inner join participation p on e.id_e=p.id_e where ( STR_TO_DATE(e.date_e, '%d/%m/%Y'))> SYSDATE() AND p.id_u="+id_u;
            Statement pst = cnx.createStatement();
           
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                EvenementCRUD ev = new EvenementCRUD();
                
                e.setId_e(rs.getInt("id_e"));
                e.setId_u(rs.getInt("id_u"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                e.setNbr_participant(rs.getInt("nbr_participant"));
               
                //  e.setEtat_e(rs.getString("etat_e"));

               if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                } else {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
               
                 if(ev.listEventParMembre(ml.getId_u(),e.getId_e())==true){
                    e.setEtat_p(new Label("Vous etes l'organisateur\nde l'evenement"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("++++++++++++++++++ winou si id"+e.getId_u());
                    
                }
                 else{
               if (ev.verifierS(e.getNbr_max_e()) == true) {
                    e.setEtat_p(new Label("La participation à cet\nevenement est illimité"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("Nombre ilimite " + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                } else if (ev.verifierSsS(e.getNbr_max_e(), e.getNbr_participant()) == true) {
                    String reste = String.valueOf((e.getNbr_max_e()) - (e.getNbr_participant()));
                    e.setEtat_p(new Label("Il reste encore:" + reste + " places"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("Reste des place  " + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                } else if (ev.verifierSs(e.getNbr_max_e(), e.getNbr_participant()) == true) {
                    e.setEtat_p(new Label("Evenement saturé"));
                    e.getEtat_p().setTextFill(Color.web("#c4151c"));

                    System.out.println("Nombre atteint" + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                }
               
                 }
               
               Participation p = new Participation();
                p.setId_u(rs.getInt("id_u"));
                p.setId_e(rs.getInt("id_e"));
                p.setDate_insc(rs.getDate("date_insc"));
                
                e.setPart(p);
                Listevent.add(e);
            }
            System.out.println("--------------+++++++++------------");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
    
 
 
 
 
 
 public List<Evenement> historiqueEvent() {

        List<Evenement> Listevent = new ArrayList<Evenement>();
        try {
             
            String requete = "select * from evenement e inner join membre m on m.id_u=e.id_u where STR_TO_DATE(e.date_e,'%d/%m/%Y')< SYSDATE() ";

            Statement pst = cnx.createStatement();
           
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                EvenementCRUD ev = new EvenementCRUD();
                
                e.setId_e(rs.getInt("id_e"));
                e.setId_u(rs.getInt("id_u"));
                e.setNom_e(rs.getString("nom_e"));
                System.out.println("za3ma nom chnia fih"+e.getNom_e());
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));

                System.out.println("----------------------moyAvis affiché" +ev.moyAvis(e.getId_e()));
                 
                 e.setRa(new Rating((int)ev.moyAvis(e.getId_e())));
                 e.getRa().setRating(ev.moyAvis(e.getId_e()));
                 e.getRa().setMax(5);
                 e.getRa().setUpdateOnHover(true);
                 e.getRa().setPartialRating(true);
                
                 // e.getRa().setPrefHeight(2);
                //  e.getRa().setPrefWidth(2);
                 e.getRa().setDisable(true);
                //  e.setEtat_e(rs.getString("etat_e"));
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));   
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                m.setEmail_u(rs.getString("email_u"));
                m.setImage_u(rs.getString("image_u"));
                 m.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + m.getImage_u())));
                    m.getImage().setFitWidth(220);
                    m.getImage().setFitHeight(110);
                e.setMbre(m);
                Listevent.add(e);
            }
            System.out.println("--------------+++++++++------------");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
 
 
 
 
 
 
 
 
 
 
 
 
 
 public List<Evenement> displayAllListOkPar(int id_u) {

        List<Evenement> Listevent = new ArrayList<Evenement>();
        try {
             
            String requete = "select * from evenement where  ( STR_TO_DATE(date_e,'%d/%m/%Y'))> SYSDATE() AND id_e not in(SELECT id_e from participation where id_u="+id_u+")";
            Statement pst = cnx.createStatement();
           
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                EvenementCRUD ev = new EvenementCRUD();

                e.setId_e(rs.getInt("id_e"));
                e.setId_u(rs.getInt("id_u"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                e.setImage_e(rs.getString("image_e"));
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                e.setNbr_participant(rs.getInt("nbr_participant"));
               
                //  e.setEtat_e(rs.getString("etat_e"));

               if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                } else {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
               
                if(ev.listEventParMembre(ml.getId_u(),e.getId_e())==true){
                     e.setEtat_p(new Label("Vous etes l'organisateur\nde l'evenement"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("++++++++++++++++++ winou si id"+e.getId_u());}
                else{
               if (ev.verifierS(e.getNbr_max_e()) == true) {
                    e.setEtat_p(new Label("La participation à cet\nevenement est illimité"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("Nombre ilimite " + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                } else if (ev.verifierSsS(e.getNbr_max_e(), e.getNbr_participant()) == true) {
                    String reste = String.valueOf((e.getNbr_max_e()) - (e.getNbr_participant()));
                    e.setEtat_p(new Label("Il reste encore:" + reste + " places"));
                    e.getEtat_p().setTextFill(Color.web("#2e856e"));
                    System.out.println("Reste des place  " + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                } else if (ev.verifierSs(e.getNbr_max_e(), e.getNbr_participant()) == true) {
                    e.setEtat_p(new Label("Evenement saturé"));
                    e.getEtat_p().setTextFill(Color.web("#c4151c"));

                    System.out.println("Nombre atteint" + e.getNbr_max_e());
                    System.out.println("Nombre atteint participant" + e.getNbr_participant());
                }
               
                }
               
            /*   Participation p = new Participation();
                p.setId_u(rs.getInt("id_u"));
                p.setId_e(rs.getInt("id_e"));
                p.setDate_insc(rs.getDate("date_insc"));
                
                e.setPart(p);*/
                Listevent.add(e);
            }
            System.out.println("--------------+++++++++------------");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listevent;
    }
    
    
    //select * from evenement where id_u not in (select id_u from participation)
    /*liste des evenement eli creer par user:id_u*/
    public List<Evenement> displayByUser(int id_u) {
//SELECT * FROM evenement ORDER BY STR_TO_DATE(date_e, '%d/%m/%Y')
        List<Evenement> Listeventuser = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement WHERE ( STR_TO_DATE(date_e,'%d/%m/%Y'))> SYSDATE() AND  id_u=" + id_u;
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
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                 if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                } else {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
                Listeventuser.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listeventuser;
    }

     public List<Evenement> displayByUserTrierDate(int id_u) {
//SELECT * FROM evenement ORDER BY STR_TO_DATE(date_e, '%d/%m/%Y')
        List<Evenement> Listeventuser = new ArrayList<>();

        try {
            String requete = "SELECT * FROM evenement WHERE ( STR_TO_DATE(date_e,'%d/%m/%Y'))> SYSDATE()AND id_u="+id_u+" ORDER BY STR_TO_DATE(date_e, '%d/%m/%Y')";
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
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                 if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                } else {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
                Listeventuser.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listeventuser;
    }
    /* Affichage liste des participant  les evenement par user */
    public int nombreEvent() {
        int nb = 0;

        try { 
            String req1 = "SELECT count(*) AS nombreEvent from evenement";
            System.out.println("+++++++++++" + req1);
            PreparedStatement pst = cnx.prepareStatement(req1);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nb = rs.getInt(1);

                System.out.println("----------" + nb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("-------------" + nb);
        return nb;

    }
    
    
    public float moyAvis(int id_e) {
        float moy = 0;

        try { 
            String req1 = "select avg(note_avis) from participation where  IFNULL(note_avis, 0) AND id_e="+id_e;
            System.out.println("+++++++++++" + req1);
            PreparedStatement pst = cnx.prepareStatement(req1);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                moy = rs.getFloat(1);

                System.out.println("----------" + moy);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("-------------" + moy);
        return moy;

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
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                 if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                } else {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
                Listeventuser.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Listeventuser;
    }

    public float pourcentageEvent(int nbr_participant, int nbr_max_e) {
        float pe = 0;
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();
        if (nbr_participant != 0) {
            pe = (nbr_participant * 100) / nbr_max_e;
        }

        return pe;

    }

    public List<Evenement> displayParticipant(int id_u) {

        List<Evenement> ListEventPaticipation = new ArrayList<>();
        EvenementCRUD ev = new EvenementCRUD();
        try {
            String requete = "SELECT * FROM  evenement e JOIN membre m ON m.id_u = e.id_u  WHERE e.id_u =" + id_u;

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {

                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                m.setEmail_u(rs.getString("email_u"));
                System.out.println("1-------------" + rs.getString("nom_u"));

                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setNbr_max_e(rs.getInt("nbr_max_e"));
                e.setNbr_participant(rs.getInt("nbr_participant"));
                System.out.println("za3ma lehne zeda" + e.getNbr_participant());
                String Pourcentage = String.valueOf(ev.pourcentageEvent(e.getNbr_participant(), e.getNbr_max_e()));
                System.out.println("----------------------pourcentage affiché" + ev.pourcentageEvent(e.getNbr_participant(), e.getNbr_max_e()));
                if (e.getNbr_max_e() != 0) {
                    if (ev.pourcentageEvent(e.getNbr_participant(), e.getNbr_max_e()) >= 50) {
                        e.setPourcentage(new Label(Pourcentage + " %"));
                        e.getPourcentage().setTextFill(Color.web("#2e856e"));
                    } else if (ev.pourcentageEvent(e.getNbr_participant(), e.getNbr_max_e()) < 50) {
                        e.setPourcentage(new Label(Pourcentage + " %"));
                        e.getPourcentage().setTextFill(Color.web("#c4151c"));
                    }

                }

                e.setMbre(m);

                ListEventPaticipation.add(e);
                System.out.println("-----------" + ListEventPaticipation);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }

}
