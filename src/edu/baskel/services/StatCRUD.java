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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Skander
 */
public class StatCRUD {

    Connection cnxs;

    public StatCRUD() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }

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
        int t=0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_rec_admin FROM `statistique` WHERE id_interface='Reclamation_admin' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n = result.getInt("nbr_rec_admin");
            //String sum = result.getString(1);
            System.out.println(n);
            PreparedStatement statement2 = cnxs.prepareStatement("SELECT SUM(nbr_visite) as total FROM `statistique`");
            ResultSet result2 = statement2.executeQuery();
            result2.next();
            t = result2.getInt("total");
            n=(n*100)/t;
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public double Reclamation_user_nbr() {
        int n = 0;
        int t=0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_rec_user FROM `statistique` WHERE id_interface='Reclamation_user' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n = result.getInt("nbr_rec_user");
            //String sum = result.getString(1);
            System.out.println(n);
            PreparedStatement statement2 = cnxs.prepareStatement("SELECT SUM(nbr_visite) as total FROM `statistique`");
            ResultSet result2 = statement2.executeQuery();
            result2.next();
            t = result2.getInt("total");
            n=(n*100)/t;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public double affichage_user_nbr() {
        int n2 = 0;
        int t=0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_aff_user FROM `statistique` WHERE id_interface='Affichage_user' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n2 = result.getInt("nbr_aff_user");
            //String sum = result.getString(1);
            System.out.println(n2);
            PreparedStatement statement2 = cnxs.prepareStatement("SELECT SUM(nbr_visite) as total FROM `statistique`");
            ResultSet result2 = statement2.executeQuery();
            result2.next();
            t = result2.getInt("total");
            n2=(n2*100)/t;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
    public double Acceuil() {
        int n2 = 0;
        int t=0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_aff_user FROM `statistique` WHERE id_interface='Acceuil' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n2 = result.getInt("nbr_aff_user");
            //String sum = result.getString(1);
            System.out.println(n2);
            PreparedStatement statement2 = cnxs.prepareStatement("SELECT SUM(nbr_visite) as total FROM `statistique`");
            ResultSet result2 = statement2.executeQuery();
            result2.next();
            t = result2.getInt("total");
            n2=(n2*100)/t;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
    
      public double contact() {
        int n2 = 0;
        int t=0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_aff_user FROM `statistique` WHERE id_interface='contact' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n2 = result.getInt("nbr_aff_user");
            //String sum = result.getString(1);
            System.out.println(n2);
            PreparedStatement statement2 = cnxs.prepareStatement("SELECT SUM(nbr_visite) as total FROM `statistique`");
            ResultSet result2 = statement2.executeQuery();
            result2.next();
            t = result2.getInt("total");
            n2=(n2*100)/t;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
      public double profil_membre() {
        int n2 = 0;
        int t=0;
        try {
            PreparedStatement statement = cnxs.prepareStatement("SELECT SUM(nbr_visite) as nbr_aff_user FROM `statistique` WHERE id_interface='Profil membre' ");
            ResultSet result = statement.executeQuery();
            result.next();
            n2 = result.getInt("nbr_aff_user");
            //String sum = result.getString(1);
            System.out.println(n2);
            PreparedStatement statement2 = cnxs.prepareStatement("SELECT SUM(nbr_visite) as total FROM `statistique`");
            ResultSet result2 = statement2.executeQuery();
            result2.next();
            t = result2.getInt("total");
            n2=(n2*100)/t;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
      
    }
   

