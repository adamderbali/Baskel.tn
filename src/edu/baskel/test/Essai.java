/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import edu.baskel.services.EvenementCRUD;

/**
 *
 * @author sabri
 */
public class Essai {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EvenementCRUD Mc = new EvenementCRUD();
  /*-  Evenement e = new Evenement("test_event","salut","15/04/2020","asllema", null);*/
     /*  System.out.println(e.getNom_e());*/
 /* Mc.ajouterEvenement(e);*/
 /* Mc.supprimerEvenement(e);*/
       System.out.println(Mc.displayParticipant(3)); 
    }
    
}
