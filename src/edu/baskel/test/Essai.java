/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
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
   /*    Membre m = new Membre(2);
  Evenement e = new Evenement("bbbb", "bbbb", "20/02/2013", "bbbb", null,3);
  Mc.ajouterEvenement(e);*/
  
  
     /*  System.out.println(e.getNom_e());*/
 /* Mc.ajouterEvenement(e);*/
 /* Mc.supprimerEvenement(e);*/
     System.out.println(Mc.displayAllList()); 
    }
    
}
