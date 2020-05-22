/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Statistique;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skander
 */
public class StatCRUD {

    Connection cnxs;

    public StatCRUD() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }

    /*public void ajouterStat_Interface(Statistique s) throws SQLException {
            String requete = "INSERT INTO statistique (id_interface, id_u) VALUES(?,?)";
            PreparedStatement pst = cnxs.prepareStatement(requete);
            pst.setString(1,s.getId_interface());
            pst.setInt(2,s.getId_u());
            pst.executeUpdate();
}
    public void update_stat_interface(Statistique s){
        
        try {
            String requete = "UPDATE statistique SET id_interface=? , id_u=? , nbr_visite=nbr_visite+1 where id_interface=?";
            PreparedStatement pst = cnxs.prepareStatement(requete);
            pst.setString(1, s.getId_interface());
            pst.setInt(2,s.getId_u());
            pst.setString(3, s.getId_interface());
            //pst.setInt(3,s.getId_u());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();        
        }
        
    }
     public void Stat_methode(Statistique s){
        
        try {
            String reqa4 = "Select * from Statistique where id_u=? and id_interface=? ";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa4);
            pstmt.setInt(1,s.getId_u());
            pstmt.setString(2, s.getId_interface());
            //pstmt.executeQuery(reqa4);
            ResultSet res = pstmt.executeQuery(reqa4);
            if(!res.next())
            {
                ajouterStat_Interface(s);
                System.out.println("insertion de stat   validée");
            }
            else{
                update_stat_interface(s);
                System.out.println("Update de stat   validée");

            }
        } catch (SQLException ex) {
ex.printStackTrace();        }
}*/
    public void ajouterStat_Interface(String id_interface, int id_u) throws SQLException {
        String requete = "INSERT INTO statistique (id_interface, id_u) VALUES(?,?)";
        PreparedStatement pst = cnxs.prepareStatement(requete);
        pst.setString(1, id_interface);
        pst.setInt(2, id_u);
        pst.executeUpdate();
    }

    public boolean verfi_satat(String id_interface, int id_u) throws SQLException {
        String reqa4 = "Select * from Statistique where id_interface='" + id_interface + "' and id_u='" + id_u + "'";
        PreparedStatement pstmt = cnxs.prepareStatement(reqa4);
        //pstmt.setString(id_interface);
        //pstmt.setInt(2,id_u);
        ResultSet res = pstmt.executeQuery(reqa4);
        while (res.next()) {
            update_stat_interface(id_interface, id_u);
            System.out.println("existe");

            return true;
        }
        ajouterStat_Interface(id_interface, id_u);
        System.out.println("not existe");

        return false;
    }

    public void update_stat_interface(String id_interface, int id_u) {

        try {
            String requete = "UPDATE statistique SET  nbr_visite=nbr_visite+1  where id_interface='" + id_interface + "' and id_u='" + id_u + "'";
            PreparedStatement pst = cnxs.prepareStatement(requete);
            //pst.setString(1, s.getId_interface());
            //pst.setInt(2,s.getId_u());
            //pst.setString(3, s.getId_interface());
            //pst.setInt(3,s.getId_u());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void Stat_methode(String id_interface, int id_u) throws SQLException {

        if (verfi_satat(id_interface, id_u) == true) {
            System.out.println("yyyyy");
        } else {
            System.out.println("nnnnnn");
        }

    }

    public double Reclamation_admin_nbr() {
        int n = 0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_rec_admin FROM `statistique` WHERE id_interface='Reclamation_admin' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n = result.getInt("nbr_rec_admin");
            //String sum = result.getString(1);
            System.out.println(n);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public double Reclamation_user_nbr() {
        int n1 = 0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_rec_user FROM `statistique` WHERE id_interface='Reclamation_user' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n1 = result.getInt("nbr_rec_user");
            //String sum = result.getString(1);
            System.out.println(n1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n1;
    }
    public double affichage_user_nbr() {
        int n2 = 0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_aff_user FROM `statistique` WHERE id_interface='Affichage_user' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n2 = result.getInt("nbr_aff_user");
            //String sum = result.getString(1);
            System.out.println(n2);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
    /*public void Stat_methode(Statistique s){
        
        try {
            String reqa4 = "Select * from Statistique where id_u=? and id_interface=? ";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa4);
            pstmt.setInt(1,s.getId_u());
            pstmt.setString(2, s.getId_interface());
            //pstmt.executeQuery(reqa4);
            ResultSet res = pstmt.executeQuery(reqa4);
            if(!res.next())
            {
                ajouterStat_Interface(s);
                System.out.println("insertion de stat   validée");
            }
            else{
                update_stat_interface(s);
                System.out.println("Update de stat   validée");

            }
        } catch (SQLException ex) {
ex.printStackTrace();        }
}*/
}
