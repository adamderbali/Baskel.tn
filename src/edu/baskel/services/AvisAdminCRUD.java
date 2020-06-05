/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Avis_admin;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Skander
 */
public class AvisAdminCRUD {
    
    Connection cnxs;

    public AvisAdminCRUD() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }
    public void insertion_avis(Avis_admin a) throws SQLException{
        String requete = "INSERT INTO avis_admin (note_avis,commentaire_a,id_u) VALUES (?,?,?)";
        PreparedStatement pst = cnxs.prepareStatement(requete);
        pst.setDouble(1, a.getNote());
        pst.setString(2, a.getCommentaire());
        pst.setInt(3, a.getId_u());

        pst.executeUpdate();
    }
     public void update_avis(Avis_admin a) throws SQLException{
        String requete = "UPDATE avis_admin SET  note_avis=? , commentaire_a=? where id_u=?";
        PreparedStatement pst = cnxs.prepareStatement(requete);
        
        pst.setDouble(1, a.getNote());
        pst.setString(2, a.getCommentaire());
        pst.setInt(3, a.getId_u());
        pst.executeUpdate();
    }
     public boolean verfication(int id_u){
        try {
            String reqa4 = "Select * from avis_admin where id_u="+id_u;
            PreparedStatement pstmt = cnxs.prepareStatement(reqa4);
            //pstmt.setInt(1,id_u);
            //pstmt.executeQuery(reqa4);
            ResultSet res = pstmt.executeQuery(reqa4);
            if(!res.next())
            {
                System.out.println("inexiste");
                return false ;
                
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}

    
    

