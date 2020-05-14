/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Forum1;
import edu.baskel.entities.Membre;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ForumCRUD {

    Connection cnx;
    Membre m = SessionInfo.getLoggedM();

    public ForumCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public void ajouterForum(Forum1 f) {
        String req = "INSERT INTO forum (id_f, id_u ,text, date_f , image_uf) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, f.getId_f());
            pst.setInt(2, f.getId_u());
            pst.setString(3, f.getText());
            pst.setString(4, f.getDate_f());
            pst.setString(5, f.getImage_uf());
            pst.executeUpdate();
            System.out.println("forum ajouter");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void supprimerForum(int id) {
        try {
            String requete = "DELETE FROM forum WHERE id_f=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("forum supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateForum(Forum1 f, int id) {
        try {
            String requete = "UPDATE forum SET valid_f=? WHERE id_f=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, f.getValid_f());
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("personne modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Forum1> displayAll() {
        List<Forum1> listeForum = new ArrayList<>(); //lezemha hné bech ywalli ychouf return

        try {
            String requete = "SELECT* FROM forum";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Forum1 p = new Forum1();
                p.setId_f(rs.getInt(1));
                p.setId_u(rs.getInt(2));
                p.setValid_f(rs.getInt(3));
                p.setText(rs.getString(4));
                p.setDate_f(rs.getString(5));
                p.setImage_uf(rs.getString(6));
                listeForum.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeForum;

    }

    public List<Forum1> displayAllText() {
        List<Forum1> listeText = new ArrayList<>(); //lezemha hné bech ywalli ychouf return

        try {
            String requete = "SELECT* FROM forum";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Forum1 p = new Forum1();
                p.setId_f(rs.getInt(1));
                p.setId_u(rs.getInt(2));
                p.setText(rs.getString(4));
                p.setDate_f(rs.getString(5));
                listeText.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeText;

    }
    
    public  boolean signalerforum (Forum1 f){
             String Requette = "UPDATE `forum` SET `Valid_f` = '0' WHERE `forum`.`id_f` = ?;";
              try {
             
                PreparedStatement pst = cnx.prepareStatement(Requette);
                pst.setInt(1, f.getId_f());
                pst.executeUpdate();
                return true; 
            } catch (SQLException ex) {
                ex.getMessage();
                return false;
            }
    }
}
