/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ParticipationCrud;

/**
 *
 * @author sabri
 */
public class Essai {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ParticipationCrud Pc = new ParticipationCrud();
        System.out.println(Pc.displayByUserP());
        
 /* Membre m = new Membre(2);*/
 EvenementCRUD ev = new EvenementCRUD();
 Evenement e = new Evenement(1,"sss", "aaaa", "20/02/2013", "bbbb", null,2);
/* ev.ajouterEvenement(e);*/
  
 ev.supprimerEvenement(81);
  
  
     /*  System.out.println(e.getNom_e());*/
 /* Mc.ajouterEvenement(e);*/
 /* Mc.supprimerEvenement(e);*/
/* EvenementCRUD ev = new EvenementCRUD();
  System.out.println(ev.displayByUser(2));*/
    }
    
}
