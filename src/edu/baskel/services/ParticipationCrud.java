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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

/**
 *
 * @author sabri
 */
public class ParticipationCrud {

    public static Connection cnx;

    public ParticipationCrud() {

        cnx = ConnectionBD.getInstance().getCnx();

    }
/* Ajout participation*/
    public void ajouterParticipation(Participation p) {
        // Evenement e = new Evenement();
        try {
            String req = "INSERT INTO participation (id_u,id_e)"
                    + "VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId_u());
            pst.setInt(2, p.getId_e());
            pst.executeUpdate();
            System.out.println("Participation added!"); 
        

        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            ex.printStackTrace();

        }
    }

      public void ajouterAvisEvent(Participation p) {
        try {
            String req = "UPDATE participation SET note_avis=?"
                    + " WHERE id_e=? AND id_u=?";
          PreparedStatement pst1 = cnx.prepareStatement(req);
            pst1.setInt(1,p.getNote_avis());
            pst1.setInt(2,p.getId_e());
            pst1.setInt(3,p.getId_u());

            pst1.executeUpdate();
            System.out.println("nbr participant modifié");
        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            ex.printStackTrace();

        }
    }

    /*

 /* suppression participation*/
    public void supprimerParticipationP(int id_e,int id_u) {

        try {
           
          String requete2 = "DELETE FROM participation where id_e=? and id_u=?";
       
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
         //   pst1.setInt(2, p.getId_u());
            pst1.setInt(1,id_e);
            pst1.setInt(2,id_u);

            pst1.executeUpdate();
            System.out.println("Participation annulé!");
          
        } catch (SQLException ex) {
            ex.printStackTrace();
         
        }

    }
 /* suppression tous les participation de l'evenement supp*/
      public boolean supprimerParticipationE(int id_e,int id_u) {

        try {
            String requete2 = "DELETE FROM participation where id_e=? AND id_u=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
            pst1.setInt(1,id_e);
            pst1.setInt(2,id_u);
            pst1.executeUpdate();
            System.out.println("Tous la participation de ce evenement sont annulées!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
    
      
       public boolean supprimerParticipationET(int id_e) {

        try {
            String requete2 = "DELETE FROM participation where id_e=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
            pst1.setInt(1,id_e);
           
            pst1.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
/*Affichage liste des eveenements par user*/
    public List<Participation> displayByUser(int id_u) {

        List<Participation> Listparticipation = new ArrayList<Participation>();
        try {
            String requete = "SELECT * FROM evenement e join participation p on e.id_e = p.id_e WHERE( STR_TO_DATE(e.date_e,'%d/%m/%Y'))< SYSDATE() AND p.id_u=" + id_u;
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
                    e.setImage(new ImageView(new Image("images\\veloParDefaut.jpg")));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                } else {
                    e.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    e.getImage().setFitWidth(220);
                    e.getImage().setFitHeight(110);
                }
                e.getImage().setFitWidth(210);
                e.getImage().setFitHeight(110);

                Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));
                 if (e.getImage_e().equals("")) {
                    e.setImage(new ImageView(new Image("images\\veloParDefaut.jpg")));
                    p.getImage().setFitWidth(220);
                    p.getImage().setFitHeight(110);
                } else {
                    p.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));
                    p.getImage().setFitWidth(220);
                    p.getImage().setFitHeight(110);
                }
                 p.setChb(new CheckBox()); 
                 p.setRa(new Rating());

                p.setEvent(e);

                Listparticipation.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listparticipation;
    }
    
    public boolean vosParticipation(int id_u){
        
        boolean vp = false;
       
        try {
            String req="select * from participation where id_u="+id_u;
            PreparedStatement pst =cnx.prepareStatement(req);
          
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return true;
                
            }
            
            else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vp;
        
    }
    public List<Participation> displayByUserEvent(int id_e) {

        List<Participation> Listparticipation = new ArrayList<Participation>();
        try {
            String requete = "SELECT * FROM evenement e join participation p on e.id_e = p.id_e WHERE p.id_e="+id_e;
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
/* Affichage liste des participation par user*/
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

                Evenement e = new Evenement();
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));

                e.setMbre(m);

                ListEventPaticipation.add(e);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }
/*verification est ce que user a deja participé a cet evenement ou non*/
    public boolean verifierParticipation(int id_u, int id_e) {

        try {

            String requete = "SELECT * FROM participation WHERE id_u=? AND id_e=?";
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
     
    public int displayEventParId(String nom_e) {
  int di = 0;
     //   List<Integer> ListNomEvent = new ArrayList<Integer>();
        try {
         
            String requete = "SELECT * FROM evenement WHERE nom_e=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,nom_e);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            
              Evenement e = new Evenement();
                     di = rs.getInt("id_e");
              
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return di;
  
    }
    
    
    
    public List<Participation> displayEventParticipant(int i) {

        List<Participation> ListNomEventPart = new ArrayList<Participation>();
        try {
           
            String requete = "SELECT * FROM Participation WHERE id_u=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,i);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            
              Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                ListNomEventPart.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListNomEventPart;
  
    }
   public List<String> ListNom(int id_u){
       List<String> ListNomEvent = new ArrayList<String>();
       ParticipationCrud pc = new ParticipationCrud();
     
               

                for (Participation p : pc.displayEventParticipant(id_u)) {

                     try {
                         String sq1 = "SELECT  * FROM evenement WHERE STR_TO_DATE(date_e,'%d/%m/%Y')< SYSDATE() and id_e=?";
                         PreparedStatement prep = cnx.prepareStatement(sq1);
                         prep.setInt(1, p.getId_e());
                         ResultSet res = prep.executeQuery();
                         
                         while (res.next()) {
                             
                             String em = res.getString("nom_e");
                           
                             ListNomEvent.add(em);
                         } 
                           
                             
                         } catch (SQLException ex) {
                             ex.printStackTrace();
                     }
                     
                }
        return ListNomEvent;

   }
    
    
    public List<Participation> displayEmailParticipant(int i) {

        List<Participation> ListEmail = new ArrayList<Participation>();
        try {
            MembreCRUD mc = new MembreCRUD();
            SendMail Sm = new SendMail();
            String requete = "SELECT * FROM Participation WHERE id_e=?";
       
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,i);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            
              Participation p = new Participation();

                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                ListEmail.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEmail;
  
    }
 
    /*nombre des participant par evenement*/
    public float pourcentageEvent(int id_e){
        float pe=(float) 0.0;
        
          try { 
            String req1 = "SELECT nbr_participant * 100 / nbr_max_e FROM evenement WHERE id_e=? AND nbr_max_e!=0";
            PreparedStatement pst = cnx.prepareStatement(req1);
            pst.setInt(1, id_e);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
            pe = rs.getInt(1);
            
            } 
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
          return pe;
        
    }
    
      public int nombreParticipation(int id_e){
              int nb=0;
              
        try { 
            String req1 = "SELECT count(*) AS nombreParticipant From participation WHERE id_e=?";
            PreparedStatement pst = cnx.prepareStatement(req1);
            pst.setInt(1, id_e);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
            nb = rs.getInt(1);
            
            } 
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
          return nb;
          
      }
      
      
   /*Affichage list des participant par evenement*/   
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
                m.setImage_u(rs.getString("image_u"));
            
                   
                    m.setImage(new ImageView(new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + m.getImage_u())));
                    m.getImage().setFitWidth(200);
                    m.getImage().setFitHeight(110);
                

               
                  Participation p = new Participation();
                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setDate_insc(rs.getDate("date_insc"));
                p.setMbre(m);

                ListEventPaticipation.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ListEventPaticipation;
    }
   /*Affichage liste des evenement where date evenement - sysdate =1*/ 
    public List<Participation> displayPEmailDay(){
        List<Participation> ListEmailEventDay = new ArrayList<Participation>();
        try {
            
            
            EvenementCRUD ec = new EvenementCRUD();
           
            String requete = "SELECT * from participation p JOIN evenement e on p.id_e=e.id_e where date_e-(DATE_FORMAT(SYSDATE(), '%d/%m/%y')) =1";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
           
            while (rs.next()) {
                Evenement e = new Evenement();
                
                e.setId_e(rs.getInt("id_e"));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getString("date_e"));
                e.setDescription_e(rs.getString("description_e"));
           
                Participation p = new Participation();
                
                p.setId_e(rs.getInt("id_e"));
                p.setId_u(rs.getInt("id_u"));
                p.setEvent(e);
               
                
                ListEmailEventDay.add(p);
                
            }
            
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
           return ListEmailEventDay;
        }
    
    
    /*rappel membre que l evenement aura lien demain*/
     public void rappelEvent() throws Exception {
     
        MembreCRUD mc = new MembreCRUD();
        SendMail Sm = new SendMail();
  
        for (Participation p : displayPEmailDay()) {
            
           
                String sq1 = "SELECT * FROM membre WHERE id_u=?";
                PreparedStatement prep = cnx.prepareStatement(sq1);
                prep.setInt(1, p.getId_u());
                ResultSet res = prep.executeQuery();

                if (res.next()) {
           
                    String em = res.getString("email_u");
                    Sm.envoiMailRappel(em);
                    System.out.println(em);
                

                } else {
                    System.out.println("Aucun participant");
                
                }

            } 
   
        }
    
     
     
     
     

    }
    

    
      
      
  



