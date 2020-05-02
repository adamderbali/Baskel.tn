/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Reclamation;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Skander
 */
public class ReclamationCRUD {

    Connection cnxs;

    public ReclamationCRUD() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }

    public List<Reclamation> displayall_Rec() {
        List<Reclamation> listeRecl = new ArrayList<>();

        try {
            String request = "SELECT * FROM reclamation";
            Statement st = cnxs.createStatement();
            ResultSet re = st.executeQuery(request);
            while (re.next()) {
                Reclamation r = new Reclamation();
                r.setId_rec(re.getInt("id_rec"));
                r.setEtat_rec(re.getString("etat_rec"));
                r.setDesc_r(re.getString("desc_r"));
                try {
                    r.setDate_rec(new SimpleDateFormat("dd/MM/yyyy").parse(re.getString("date_rec")));
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
                listeRecl.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeRecl;
    }

    public void ajouterReclamation(Reclamation Reclam) {

        String query = null;
        try {

            //query = "INSERT INTO `reclamation`( `objet_rec`, `desc_rec`, `etat_rec`,`date_rec`, `id_u`,'id_ur') VALUES (?,?,?,?,?);";
            query = "INSERT INTO `reclamation`( `objet_rec`, `desc_r`,id_u ,id_ur) VALUES (?,?,?,?);";
            PreparedStatement pstmt = cnxs.prepareStatement(query);

            pstmt.setString(1, Reclam.getObjet_rec());
            pstmt.setString(2, Reclam.getDesc_r());
            pstmt.setInt(3,Reclam.getId_u());
            pstmt.setInt(4,Reclam.getId_ur());

            //pstmt.setString(3, "non traiter");
           // pstmt.setDate(4, null);
            //pstmt.setInt(5,Reclam.getId_reclameur());
            // pstmt.setSt(6, Reclam.getReclamer().getNom_u());

            pstmt.executeUpdate();

            System.err.println("Insert: OK!");

        } catch (SQLException e) {
            System.out.println("Insert reclamtion : Query error ->" + e.getMessage());
        }

    }
}
