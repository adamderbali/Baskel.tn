/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.entities.Alerte;
import edu.baskel.entities.Reparateur;
import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AlerteCRUD {
    Connection cnx;

    public AlerteCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
    public void ajouterAlert(Alerte a){
        
    }
    public void updateAlert(Alerte a){
        
    }
    public void supprimerAlert(int id){
        
    }
    public List<Alerte> getListeAlerte() {
        List<Alerte> listAlerte = new ArrayList<Alerte>();

        try {
            String requete = "SELECT* FROM reparateur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Alerte r = new Alerte();
                
                listAlerte.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listAlerte;

    }
    
    
    
    
}
