/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;


import edu.baskel.entities.Velo;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hela
 */
public class VeloCRUD {
    Connection cnx;

    public VeloCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    /*ajout*/
    public void ajouterVelo(Velo v){
           try {
               String requete2 ="INSERT INTO velo (num_serie,marque,model,prix_v,type_v,annee_sortie,status_v,num_tel_v,etat_v,description_v,id_u,image_v)"
                       + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
               

               PreparedStatement pst = cnx.prepareStatement(requete2);
               pst.setInt(1,v.getNum_serie());
               pst.setString(2,v.getMarque());
               pst.setString(3,v.getModel());
               pst.setDouble(4,v.getPrix_v());
               pst.setString(5,v.getType_v());
               pst.setString(6,v.getAnnee_sortie());
               pst.setString(7,v.getStatus_v());
               pst.setInt(8,v.getNum_tel_v());
               pst.setString(9,v.getEtat_v());
               pst.setString(10,v.getDescription_v());
               pst.setInt(11,v.getId_u());
               pst.setString(12,v.getImage_v());
               
               
               
           
               pst.executeUpdate();
               System.out.println("Velo ajouté");
           } catch (SQLException ex) {
               System.out.println("Erreur d'insertion");
               System.out.println(ex.getMessage());
           }
    }
    
    /*MAJ*/
    public void modifierVelo(Velo v,int num_serie) {
            
         try {
             String requete2 = "UPDATE velo SET marque=?,model=?,prix_v=?,type_v=?,annee_sortie=?,status_v=?,num_tel_v=?,etat_v=?,description_v=?,image_v=?"
                     +" WHERE num_serie=?";
             PreparedStatement pst2 = cnx.prepareStatement(requete2);
             
             pst2.setString(1,v.getMarque());
             pst2.setString(2,v.getModel());
             pst2.setDouble(3,v.getPrix_v());
             pst2.setString(4,v.getType_v());
             pst2.setString(5,v.getAnnee_sortie());
             pst2.setString(6,v.getStatus_v());
             pst2.setInt(7,v.getNum_tel_v());
             pst2.setString(8,v.getEtat_v());
             pst2.setString(9,v.getDescription_v());
             pst2.setString(10,v.getImage_v());
             pst2.setInt(11,num_serie);
            
             pst2.executeUpdate();
             System.out.println("Velo modifié");
         } catch (SQLException ex) {
             
             System.out.println(ex.getMessage());
         }
            
        }
    /* Suppression */
         public void supprimerVelo(int num_serie){
             
         try {
             String requete1 = "DELETE FROM velo where num_serie=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             pst1.setInt(1,num_serie);
             pst1.executeUpdate();
             System.out.println("Velo supprimé");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
         
         /* Affichage */

        
       public List<Velo> afficherTout(){
       List<Velo> listeVelo = new ArrayList<>();
        try {
            String requete = "SELECT * FROM velo";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Velo v = new Velo();     
                v.setNum_serie(rs.getInt(1));
                v.setMarque(rs.getString("marque"));
                v.setModel(rs.getString("model"));
                v.setPrix_v(rs.getDouble("prix_v"));
                v.setType_v(rs.getString("type_v"));
                v.setAnnee_sortie(rs.getString("annee_sortie"));
                v.setStatus_v(rs.getString("status_v"));
                v.setNum_tel_v(rs.getInt("num_tel_v "));
                v.setEtat_v(rs.getString("etat_v"));
                v.setDescription_v(rs.getString("description_v"));
                v.setImage_v(rs.getString("image_v"));
                
                
             
                listeVelo.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeVelo;
   }
       
       public List<Velo> afficherVeloUser(){
          
           Velo v;
           List<Velo> listeVelo = new ArrayList<>();
        try {
            String requete = "SELECT * FROM velo v, membre m WHERE v.id_u = m.id_u";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                
                v = new Velo();     
                v.setNum_serie(rs.getInt(1));
                v.setMarque(rs.getString("marque"));
                v.setModel(rs.getString("model"));
                v.setPrix_v(rs.getDouble("prix_v"));
                v.setType_v(rs.getString("type_v"));
                v.setAnnee_sortie(rs.getString("annee_sortie"));
                v.setStatus_v(rs.getString("status_v"));
                v.setNum_tel_v(rs.getInt("num_tel_v "));
                v.setEtat_v(rs.getString("etat_v"));
                v.setDescription_v(rs.getString("description_v"));
                v.setImage_v(rs.getString("image_v"));
                v.setId_u(rs.getInt("id_u"));
                
                
             
                listeVelo.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeVelo;
   }
       
        /* Louer velo */
         public void louerOuAcheterVelo(int id_av,int num_serie){
             
         try {
             String requete1 = "UPDATE velo SET etat_v=?,id_av=? WHERE num_serie=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             String etat="Non disponible";
             pst1.setString(1,etat);
             pst1.setInt(2,id_av);
             pst1.setInt(3,num_serie);
             pst1.executeUpdate();
             System.out.println("etat modifié");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
         
         /* gerer dispo velo pour admin*/
         public void gererDispoVelo(int id_av,int num_serie,boolean etat){
             
         try {
             String requete1 = "UPDATE velo SET etat_v=?,id_av=? WHERE num_serie=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             if (etat==false){
             pst1.setString(1,"Non disponible");
             }else{
               pst1.setString(1,"Disponible");  
             }
             pst1.setInt(2,id_av);
             pst1.setInt(3,num_serie);
             pst1.executeUpdate();
             System.out.println("etat modifié");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
       
}
