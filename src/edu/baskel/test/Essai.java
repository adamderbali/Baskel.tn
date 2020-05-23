/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;

import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.MailAttachement;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.services.Qrcode;
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
      
            ParticipationCrud Pc = new ParticipationCrud();
            // System.out.println(Pc.displayByUserP());
            
//  ParticipationCrud pa = new ParticipationCrud();
//   System.out.println(Pc.displayEmailParticipant());
/*c EvenementCRUD ev = new EvenementCRUD();*/
/* Participation p = new Participation(64,4);*/
/*    System.out.println(pa.displayByUserP());*/

//  ParticipationCrud pc = new ParticipationCrud();
//  System.out.println(pc.displayParticipantList(7,113));
// EvenementCRUD ec = new EvenementCRUD();
//  ec.nombreEvent();

//  Qrcode cm = new Qrcode();
//  cm.Create("nesrine", "sabrine2");

        EvenementCRUD ev = new EvenementCRUD();
      //  Evenement e  = new Evenement(147, "sortie 17", "tunis", "25/10/2021", "marahbe", null, 20);
       // ev.nbrParticipant(162);
    
      //  System.out.println(ev.displayAllListU(7));
      //  ev.desactiverCell(7);
    
    ev.displayParticipant(7);
    }

}