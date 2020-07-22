/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Ban;
import edu.baskel.entities.Reclamation;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            String reqa2 = "insert into ban (id_u,date_ban) VALUES((Select id_u from membre where id_u='"+id_u+"'),sysdate())";
            Statement pstmt = cnxs.createStatement();
            //pstmt.setInt(1,id_u);
            pstmt.executeUpdate(reqa2);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void updateByID(int id_u){
        try {
            String reqa3 = "UPDATE ban SET ban_num_u=ban_num_u+1 , date_ban=sysdate() where id_u='"+id_u+"'";
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
                System.out.println("insertion de Membre "+id_u+"  validée");
            }
            else{
                updateByID(id_u);
                System.out.println("Update de Membre "+id_u+"  validée");

            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    public void Ban_Suppression(){
        for (Ban b : getlist_ban_supp()) {
            try {
                String sq1 = "delete from membre where id_u in (select id_u from ban where ban_num_u>3)";
                PreparedStatement prep2 = cnxs.prepareStatement(sq1);
                //prep2.setInt(1, m.getId_u());
                prep2.execute();
                System.out.println("la suppression de user a etè bien effectué ");
            } catch (SQLException ex) {
ex.printStackTrace();            }
            }
        
        }
            
        
    
  
       public List<Ban> getlist_ban(){
    List<Ban> list_ban = new ArrayList<>();
        try {
            String rq="select * from ban ";
            Statement st = cnxs.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                Ban b = new Ban();
                b.setDate_ban(rs.getDate("date_ban"));
                b.setId_u(rs.getInt("id_u"));
                b.setId_ban(rs.getInt("id_ban"));
                b.setBan_num_u(rs.getInt("ban_num_u"));
                list_ban.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list_ban;
}
    public List<Ban> getlist_ban_supp(){
    List<Ban> list_ban_supp = new ArrayList<>();
        try {
            String rq="select * from ban where ban_num_u>=3";
            Statement st = cnxs.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                Ban b = new Ban();
                b.setDate_ban(rs.getDate("date_ban"));
                b.setId_u(rs.getInt("id_u"));
                b.setId_ban(rs.getInt("id_ban"));
                b.setBan_num_u(rs.getInt("ban_num_u"));
                list_ban_supp.add(b);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list_ban_supp;
}


public void Reactive_ban(){
    ReclamationCRUD rc = new ReclamationCRUD();
    for(Ban b : getlist_ban())
        
    {   

        LocalDate d = LocalDate.now();
        LocalDate d2 = LocalDate.parse(b.getDate_ban().toString());
        System.out.println(d);
        
        if(d.compareTo(d2)>3)
        {
            rc.desactiverbannerUtilisateur(b.getId_u());
            //System.out.println(d.compareTo(d2));
            System.out.println("Reactive de Compte effectué avc succes "
                    + "");
                    }
        else  {
            //System.out.println(d.compareTo(d2));
            System.out.println("le utilisateur est encore banné ");
            //System.out.println(b.getDate_ban());
        }
    }
    
}

}
