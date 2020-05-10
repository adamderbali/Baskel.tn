/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Reservation;
import edu.baskel.entities.Velo;

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
 * @author Hela
 */
public class ReservationCRUD {
                Connection cnx;

    public ReservationCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
     /* Affichage */

        
       public List<Reservation> afficherReservation(int id_u){
       List<Reservation> listeReservation = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reservation WHERE id_u ="+id_u;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setId_res(rs.getInt(1));
                r.setDate_r(rs.getString("date_res"));
                r.setNbr_heure(rs.getInt("nbr_heure"));
                r.setDate_db_r(rs.getDate("date_db_res"));
                r.setNum_serie(rs.getInt("num_serie"));
                r.setId_u(rs.getInt("id_u"));
                
                
                System.out.println(r);
             
                listeReservation.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeReservation;
   }
    /*ajout*/
    public void ajouterReservation(Reservation r){
           try {
               String requete2 ="INSERT INTO reservation (date_res,nbr_heure,num_serie,id_u)"
                       + "VALUES (?,?,?,?)";
               

               PreparedStatement pst = cnx.prepareStatement(requete2);
               pst.setString(1,r.getDate_r());
               pst.setInt(2,r.getNbr_heure());
               pst.setInt(3,r.getNum_serie());
               pst.setInt(4,r.getId_u());
               
               
               
               
           
               pst.executeUpdate();
               System.out.println("Reservation ajoutée");
           } catch (SQLException ex) {
               System.out.println("Erreur d'insertion");
               System.out.println(ex.getMessage());
           }
    }
                
          /*MAJ*/
    public void modifierReservation(Reservation r,int id_res) {
            
         try {
             String requete2 = "UPDATE reservation SET date_res=?,nbr_heure=?"
                     +" WHERE id_res=?";
             PreparedStatement pst2 = cnx.prepareStatement(requete2);
             
             pst2.setString(1,r.getDate_r());
               pst2.setInt(2,r.getNbr_heure());
               pst2.setInt(3,r.getId_res());
              
            
             pst2.executeUpdate();
             System.out.println("Reservation modifiée");
         } catch (SQLException ex) {
             
             System.out.println(ex.getMessage());
         }
    }
         /* Suppression */
         public void annulerReservation(int id_res){
             
         try {
             String requete1 = "DELETE FROM reservation where id_res=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             pst1.setInt(1,id_res);
             pst1.executeUpdate();
             System.out.println("Reservation annulée");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
            
             
}
