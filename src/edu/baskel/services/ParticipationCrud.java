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
import java.util.List;

/**
 *
 * @author sabri
 */
public class ParticipationCrud {

    Connection cnx;

    public ParticipationCrud() {

        cnx = ConnectionBD.getInstance().getCnx();

    }

    public void ajouterParticipation(Participation p) {

        try {
            String req = "INSERT INTO participation (id_u,id_e)"
                    + "VALUES (?,?)";

            PreparedStatement pst = cnx.prepareStatement(req);
            /*    pst.setInt(1,p.getId_user());*/
            pst.setInt(1, p.getId_u());
            pst.setInt(2, p.getId_e());

            pst.executeUpdate();
            System.out.println("Participation added!");

        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            ex.printStackTrace();

        }
    }

    /*

 /* suppression participation*/
    public boolean supprimerParticipationP(Participation p) {

        try {
           
          String requete2 = "DELETE FROM participation where id_e=?";
       
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
         //   pst1.setInt(2, p.getId_u());
            pst1.setInt(1, p.getId_e());

            pst1.executeUpdate();
            System.out.println("Participation annulé!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
    
    
 

      public boolean supprimerParticipationE(int id_e) {

        try {
            String requete2 = "DELETE FROM participation where id_e=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
            pst1.setInt(1,id_e);
            pst1.executeUpdate();
            System.out.println("Tous la participation de ce evenement sont annulées!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
    

    public List<Participation> displayByUser(int id_u) {

        List<Participation> Listparticipation = new ArrayList<Participation>();
        try {
            String requete = "SELECT * FROM evenement e join participation p on e.id_e = p.id_e WHERE p.id_u=" + id_u;
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

    public List<Evenement> displayParticipant(int id_u) {

        List<Evenement> ListEventPaticipation = new ArrayList<>();

        try { String requete = "SELECT * FROM  evenement e JOIN membre m ON m.id_u = e.id_u  WHERE e.id_u =" + id_u;
         
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

            /*    Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));
                System.out.println("1-------------" + rs.getString("id_e"));

              p.setMbre(m);*/
                e.setMbre(m);

                ListEventPaticipation.add(e);
                System.out.println("-----------" + ListEventPaticipation);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }

    public boolean verifierParticipation(int id_u, int id_e) {

        try {

            String requete = "SELECT * FROM participation WHERE id_u=? AND id_e=?";
            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_u);
            pst.setInt(2, id_e);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return false;

            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return true;

    }
    
    
    
    public List<Participation> displayEmailParticipant(int i) {

        List<Participation> ListEmail = new ArrayList<Participation>();
        try {
            MembreCRUD mc = new MembreCRUD();
            SendMail Sm = new SendMail();
            String requete = "SELECT * FROM Participation WHERE id_e=?";
            System.out.println("+++++++++++" + requete);
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,i);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            
              Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                ListEmail.add(p);
                System.out.println("-------------"+ListEmail.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEmail;
  
    }
    
    
      public void eventAnnuler(int id_e) throws Exception {
     
        MembreCRUD mc = new MembreCRUD();
        SendMail Sm = new SendMail();
  
        for (Participation p : displayEmailParticipant(id_e)) {
            
           
                String sq1 = "SELECT * FROM membre WHERE id_u=?";
                PreparedStatement prep = cnx.prepareStatement(sq1);
                prep.setInt(1, p.getId_u());
                ResultSet res = prep.executeQuery();

                if (res.next()) {
           
                    String em = res.getString("email_u");
                    Sm.envoiMail(em);
                    System.out.println(em);
                

                } else {
                    System.out.println("Aucun participant");
                
                }

            } 
   
        }
      
      public int nombreParticipation(int id_e){
              int nb=0;
              
        try { 
            String req1 = "SELECT count(*) AS nombreParticipant From participation WHERE id_e=?";
            System.out.println("+++++++++++" + req1);
            PreparedStatement pst = cnx.prepareStatement(req1);
            pst.setInt(1, id_e);
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
      
      
      
    public List<Participation> displayParticipantList(int id_e) {

        List<Participation> ListEventPaticipation = new ArrayList<>();

        try {
            String requete = "SELECT * FROM participation p JOIN membre m ON m.id_u = p.id_u  WHERE p.id_e="+id_e;


            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {

                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                m.setEmail_u(rs.getString("email_u"));
                System.out.println("1-------------" + rs.getString("nom_u"));

                Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));
                System.out.println("1-------------" + rs.getString("id_e"));

                p.setMbre(m);
              //  p.setEvent(e);

                ListEventPaticipation.add(p);
                System.out.println("-----------" + ListEventPaticipation);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }
    
    

    }
    

    
      
      
  



