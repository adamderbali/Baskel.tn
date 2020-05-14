/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import com.gluonhq.impl.charm.a.b.b.m;
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
import java.time.LocalDate;
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
                r.setId_u(re.getInt("id_u"));
                r.setObjet_rec(re.getString("objet_rec"));
                r.setDate_rec(re.getDate("date_rec"));
                r.setDesc_r(re.getString("desc_r"));
                r.setId_ur(re.getInt("id_ur"));
                r.setEtat_rec(re.getString("etat_rec"));
                r.setEtat_rec2(re.getString("etat_rec2"));
                
                
                /*try {
                    r.setDate_rec(new SimpleDateFormat("dd/MM/yyyy").parse(re.getString("date_rec")));
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }*/
                listeRecl.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeRecl;
    }
    public List<Reclamation> displayall_Rec_valide() {
        List<Reclamation> listeRec2 = new ArrayList<>();

        try {
            String request = "SELECT * FROM reclamation where etat_rec='Valide' and cond=1 ";
            Statement st = cnxs.createStatement();
            ResultSet re = st.executeQuery(request);
            while (re.next()) {
                Reclamation r = new Reclamation();
                r.setId_rec(re.getInt("id_rec"));
                r.setId_u(re.getInt("id_u"));
                r.setObjet_rec(re.getString("objet_rec"));
                r.setDate_rec(re.getDate("date_rec"));
                r.setDesc_r(re.getString("desc_r"));
                r.setId_ur(re.getInt("id_ur"));
                r.setEtat_rec(re.getString("etat_rec"));
                r.setEtat_rec2(re.getString("etat_rec2"));
                r.setCondition(re.getInt("cond"));
                
                
                /*try {
                    r.setDate_rec(new SimpleDateFormat("dd/MM/yyyy").parse(re.getString("date_rec")));
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }*/
                listeRec2.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeRec2;
    }
    public void update_admin(){
        for(Reclamation rc:displayall_Rec_valide())
        {
            ajouterban2(rc.getId_ur());
            System.out.println(rc.getId_ur());
            updateban(rc.getId_rec());
            
    }
    }
     public void ajouterban2(int id) {
        String query = null;
        try {

            query = "UPDATE membre SET nbr_ban_u=nbr_ban_u+1 WHERE id_u=?;";
            PreparedStatement pstmt = cnxs.prepareStatement(query);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.err.println("Insert: OK!");

        } catch (SQLException e) {
            System.out.println("Insert reclamtion : Query error ->" + e.getMessage());
        }
     }
     public void updateban(int id) {
        String query = null;
        try {

            query = "update Reclamation set cond=0 WHERE id_rec=?;";
            PreparedStatement pstmt = cnxs.prepareStatement(query);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.err.println("update : OK!");

        } catch (SQLException e) {
            System.out.println("update reclamtion : Query error ->" + e.getMessage());
        }
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
public void valider_admin(Reclamation rec){
    try {
            String reqa1 = "UPDATE Reclamation SET etat_rec2=? , etat_rec=? WHERE id_rec=? ";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa1);
            pstmt.setString(1,rec.getEtat_rec2());
            pstmt.setString(2,rec.getEtat_rec());
            pstmt.setInt(3,rec.getId_rec());
            pstmt.executeUpdate();
            System.out.println("Modification Done ! ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
            String requete = "SELECT id_u FROM membre where nbr_ban_u>=3 and type_u='U' ";
            Statement st = cnxs.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Membre m = new Membre();
                m.setId_u(rs.getInt("id_u"));
                //m.setNom_u(rs.getString("nom_u"));
                //m.setPrenom_u(rs.getString("prenom_u"));
                //m.setAdresse_u(rs.getString(4));
                //m.setEmail_u(rs.getString(5));
                //m.setSexe_u(rs.getString(6));
                //m.setDate_u(rs.getDate(7));
                //m.setMot_passe_u(rs.getString(8));
                //m.setNum_tel_u(rs.getString(9));
                //m.setImage_u(rs.getString(10));
                //m.setType_u(rs.getString(11));
                //m.setNbr_ban_u(rs.getInt("nbr_ban_u"));
                //m.setValidation_u(rs.getInt());
                listeMembre_ban.add(m);
            }
            /*for (Membre m : getlistMembre_ban()) {
                String sq1 = "UPDATE membre SET validation_u=0";
                PreparedStatement prep2 = cnxs.prepareStatement(sq1);
                //prep2.setInt(1, m.getId_u());
                prep2.executeUpdate();
            }*/

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeMembre_ban;

    }
public List<Membre> listUser() {
        List<Membre> listeMembre = new ArrayList<>();

        try {
            String requete = "SELECT* FROM membre where type_u='U' or type_u='R'";
            Statement st = cnxs.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Membre m = new Membre();
                m.setId_u(rs.getInt(1));
                m.setNom_u(rs.getString(2));
                m.setPrenom_u(rs.getString(3));
                m.setAdresse_u(rs.getString(4));
                m.setEmail_u(rs.getString(5));
                m.setSexe_u(rs.getString(6));
                m.setDate_u(rs.getDate(7));
                m.setMot_passe_u(rs.getString(8));
                m.setNum_tel_u(rs.getString(9));
                m.setImage_u(rs.getString(10));
                m.setType_u(rs.getString(11));
                m.setValidation_u(rs.getInt(12));
                m.setNbr_ban_u(rs.getInt(13));
                listeMembre.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeMembre;

    }
public void Banner_user() throws Exception {
      
        MembreCRUD mc = new MembreCRUD();
        
        BanCRUD bn = new BanCRUD();
        for (Membre m : getlistMembre_ban()) {
                String sq1 = "UPDATE membre SET validation_u=0 where nbr_ban_u>=3 ";
                PreparedStatement prep2 = cnxs.prepareStatement(sq1);
                //prep2.setInt(1, m.getId_u());
                prep2.executeUpdate();
                bn.bannereExiste(m.getId_u());
                System.out.println("c bon");
            }
        
        }
 

public void desactiverbannerUtilisateur(int id_u)
{
        try {
            String reqa1 = "UPDATE membre SET validation_u=1 , nbr_ban_u=0 WHERE id_u=? ";
            PreparedStatement pstmt = cnxs.prepareStatement(reqa1);
            //pstmt.setInt(1,m.getId_u());
            //pstmt.setInt(1,m.getId_u());

            pstmt.setInt(1,id_u);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
}
 


 
    
}



 
 
    


