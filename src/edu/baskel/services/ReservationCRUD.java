/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Reservation;

import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hela
 */
public class ReservationCRUD {
                Connection cnx;

    public ReservationCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    /*ajout*/
    public void ajouterReservation(Reservation r){
           try {
               String requete2 ="INSERT INTO reservation (date_res,nbr_heure,num_serie,id_u)"
                       + "VALUES (?,?,?,?)";
               

               PreparedStatement pst = cnx.prepareStatement(requete2);
               pst.setString(1,r.getDate_r());
               pst.setInt(2,r.getNbr_heure());
               pst.setInt(3,r.getVelo().getNum_serie());
               pst.setInt(4,r.getMembre().getId_u());
               
               
               
               
           
               pst.executeUpdate();
               System.out.println("Reservation ajoutée");
           } catch (SQLException ex) {
               System.out.println("Erreur d'insertion");
               System.out.println(ex.getMessage());
           }
    }
                
                
}
