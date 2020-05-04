/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.utils.ConnectionBD;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class ParticipationCrud {

    Connection cnx;

    public ParticipationCrud() {

        cnx = ConnectionBD.getInstance().getCnx();

    }

    public void ajouterParticipation(Participation p) {

        try {
            String req = "INSERT INTO participation (id_u,id_e)"
                    + "VALUES (?,?)";

            PreparedStatement pst = cnx.prepareStatement(req);
            /*    pst.setInt(1,p.getId_user());*/
            pst.setInt(2, p.getId_u());
            pst.setInt(1, p.getId_e());

            pst.executeUpdate();
            System.out.println("Participation added!");
        } catch (SQLException ex) {
            System.err.println("Erreur d'insertion");
            ex.printStackTrace();
        }
    }

    /*
public void supprimerParticipation(int id_e,int id_u){
             
         try {
             String requete1 = "DELETE FROM participation where id_e=? and id_u=?";
             PreparedStatement pst1 = cnx.prepareStatement(requete1);
             pst1.setInt(1,id_e);
             pst1.setInt(2,id_u);
             pst1.executeUpdate();
             System.out.println("Participation annulé!");
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
  
         }
     */
 /* suppression participation*/
    public void supprimerParticipationP(Participation p) {

        try {
            String requete2 = "DELETE FROM participation where id_e=" + p.getId_e() + "AND id_u=" + p.getId_u();
            PreparedStatement pst1 = cnx.prepareStatement(requete2);
            /*   pst1.setInt(1,p.getId_e());
             pst1.setInt(2,p.getId_u());*/
            pst1.executeUpdate();
            System.out.println("Participation annulé!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    /* Affichage participation dans les evenement par user*/
    public List<Participation> displayByUserP(int id_user) {
        Evenement e;
        Participation p;
        List<Participation> Listparticipation = new ArrayList<>();
        String requete = "SELECT e.nom_e,e.lieu_e,e.description_e FROM participation p INNER evenement e WHERE e.id_e=p.id_e " ; /*AND*/
             /*   + " id_u=" + id_user;*/
        PreparedStatement preparedStatement = null;
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                e= new Evenement(rs.getInt("id_e"), rs.getString("id_nom"), rs.getString("lieu_e"), rs.getString("description_e"));
                p = new Participation(rs.getInt("id_e"), rs.getInt("id_u"), rs.getDate("date_insc"),e);
                
                Listparticipation.add(p);

            }
            return Listparticipation;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Listparticipation;
    }

}
