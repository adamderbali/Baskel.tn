/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import com.gluonhq.impl.charm.a.b.b.m;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReclamationCRUD;
import edu.baskel.entities.Reclamation;
import edu.baskel.services.BanCRUD;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabri
 */
public class Essai {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ParticipationCrud Pc = new ParticipationCrud();
            // System.out.println(Pc.displayByUserP());
            
//  ParticipationCrud pa = new ParticipationCrud();
//   System.out.println(Pc.displayEmailParticipant());
/*c EvenementCRUD ev = new EvenementCRUD();*/
/* Participation p = new Participation(64,4);*/
/*    System.out.println(pa.displayByUserP());*/
ReclamationCRUD m = new ReclamationCRUD();
BanCRUD b= new BanCRUD();
//m.Banner_user();
b.bannereExiste(6);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
    }
    
    
}
}
