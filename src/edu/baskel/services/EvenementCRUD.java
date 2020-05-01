/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Evenement;
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
  
     
     public EvenementCRUD()
     {
         cnx = ConnectionBD.getInstance().getCnx();
     }
     /* Insert */
     public void ajouterEvenement(Evenement e){
       
         
         try {
             String requete = "INSERT INTO evenement (nom_e,lieu_e,date_e,description_e,image_e)"
                     + "VALUES (?,?,?,?,?)" ;
             PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setString(1,e.getNom_e());
             pst.setString(2,e.getLieu_e());
             pst.setDate(3, (java.sql.Date) e.getDate_e());
             pst.setString(4,e.getDescription_e());
             pst.setString(5,e.getImage_e());
     
         /*    pst.setString(4,e.getImage_e());*/
           
             pst.executeUpdate();
             System.out.println("Evenement added!");
         } 
         
         catch (SQLException ex) {
              System.err.println("Erreur d'insertion");
             System.out.println(ex.getMessage());
         } 
     }  
       /* Delete */
         public void supprimerEvenement(int id_e){
             
         try {
             String requete1 = "DELETE FROM evenement where id_e=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             pst1.setInt(1,id_e);
             pst1.executeUpdate();
             System.out.println("Evenement supprimé");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
         
      /* Update */
        public void updateEvenement(Evenement e,int id_e) {
            
         try {
             String requete2 = "UPDATE evenement SET nom_e=?,lieu_e=?,date_e=?,description_e=?"
                     +" WHERE id_e=?";
             PreparedStatement pst2 = cnx.prepareStatement(requete2);
             pst2.setInt(5,id_e);
             pst2.setString(1,e.getNom_e());
             pst2.setString(2,e.getLieu_e());
             pst2.setDate(3, (Date) e.getDate_e());
             pst2.setString(4,e.getDescription_e());
            /* pst2.setString(4,e.getImage_e());*/
             pst2.executeUpdate();
             System.out.println("Evenement modifie");
         } catch (SQLException ex) {
             
             System.out.println(ex.getMessage());
         }
            
        }
         
        /* Display */

        
       public List<Evenement> displayAll(){
       List<Evenement> listeEvenement = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Evenement e = new Evenement();     
                e.setId_e(rs.getInt(1));
                e.setNom_e(rs.getString("nom_e"));
                e.setLieu_e(rs.getString("lieu_e"));
                e.setDate_e(rs.getDate("date_e"));
                e.setDescription_e(rs.getString("description_e"));
                
              e.setImage_e(rs.getString("image_e"));
                listeEvenement.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeEvenement;
   }

   
       
      
    
    
    
}
