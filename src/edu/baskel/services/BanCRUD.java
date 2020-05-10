/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skander
 */
public class BanCRUD {
    Connection cnxs;

    public BanCRUD() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }
    public void insertionall(){
        try {
            String reqa1 = "insert into ban (id_u) (Select id_u from membre)";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa1);
            pstmt.executeQuery(reqa1);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
    }
    public void insertionByID(int id_u){
        try {
            String reqa2 = "insert into ban (id_u) (Select id_u from membre where id_u='"+id_u+"')";
            Statement pstmt = cnxs.createStatement();
            //pstmt.setInt(1,id_u);
            pstmt.executeUpdate(reqa2);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void updateByID(int id_u){
        try {
            String reqa3 = "UPDATE ban SET ban_num_u=ban_num_u+1 where id_u='"+id_u+"'";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa3);
            //pstmt.setInt(1,id);
            pstmt.executeUpdate(reqa3);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void bannereExiste(int id_u){
        try {
            String reqa4 = "Select * from ban where id_u='"+id_u+"'";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa4);
            //pstmt.setInt(1,id_u);
            //pstmt.executeQuery(reqa4);
            ResultSet res = pstmt.executeQuery(reqa4);
            if(!res.next())
            {
                insertionByID(id_u);
                System.out.println("insertion validée");
            }
            else{
                updateByID(id_u);
                System.out.println("update validée");

            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
