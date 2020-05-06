/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Membre;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            pstmt.setInt(3, Reclam.getId_u());
            pstmt.setInt(4, Reclam.getId_ur());

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

    /*public boolean BaneExist(int id_u, int id_ur) throws SQLException {
        int Valeur = 0;
        //String reqa = "select COUNT(id_rec) FROM `reclamation` WHERE id_u=? and id_ur=? ;";
        PreparedStatement pstmt = cnxs.prepareStatement(reqa);
        pstmt.setInt(1,id_u);
        pstmt.setInt(2,id_ur);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery(reqa);
        while(rs.next()){ 
        System.out.println("hani lenna 3");
        Valeur = (rs.getInt(1));
        //pstmt.executeQuery();
    }
        System.out.println(Valeur);

        if (Valeur != 0) {
            return true;
        } else {
            return false;
        }

}*/
public boolean banexist (int id_u , int id_ur){
        try {
            String reqa = "select * FROM reclamation WHERE id_u= '"+id_u+"' and id_ur= '"+id_ur+"'";
            Statement pstmt = cnxs.createStatement();
            
            //pstmt.setInt(2,id_ur);
            ResultSet  rs=pstmt.executeQuery(reqa);
            while(rs.next())
            {
                System.out.println("reclam existe");
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
return false;
}
public void bannerUtilisateur(Membre m)
{
        try {
            String reqa1 = "UPDATE membre SET validation_u=0 WHERE nbr_ban_u>=3 and id_u=? ";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa1);
            pstmt.setInt(1,m.getId_u());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}
public List<Membre> getlistMembre_ban() {
        List<Membre> listeMembre_ban = new ArrayList<>();

        try {
            String requete = "SELECT * FROM membre where nbr_ban_u>=3";
            Statement st = cnxs.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                //m.setAdresse_u(rs.getString(4));
                //m.setEmail_u(rs.getString(5));
                //m.setSexe_u(rs.getString(6));
                //m.setDate_u(rs.getDate(7));
                //m.setMot_passe_u(rs.getString(8));
                //m.setNum_tel_u(rs.getString(9));
                //m.setImage_u(rs.getString(10));
                //m.setType_u(rs.getString(11));
                m.setNbr_ban_u(rs.getInt("nbr_ban_u"));
                //m.setValidation_u(rs.getInt());
                listeMembre_ban.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeMembre_ban;

    }
public void desactiverbannerUtilisateur(Membre m)
{
        try {
            String reqa1 = "UPDATE membre SET validation_u=1 WHERE id_u=? ";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa1);
            pstmt.setInt(1,m.getId_u());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}


    
}
