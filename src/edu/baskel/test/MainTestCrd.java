
package edu.baskel.test;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReparateurCRUD;
import java.lang.reflect.Member;
import java.sql.Date;

public class MainTestCrd {
    
   
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        //launch(args);
        System.out.println("hello");
        Reparateur r = new Reparateur();
        r.setNom_u("naili");
        r.setPrenom_u("khaled");
        r.setAdresse_u("rades");
        r.setImage_u("photo.jpg");
        r.setSexe_u("H");
        r.setNum_tel_u("22795868");
        r.setEmail_u("khaled.naili@esprit.tn");
        r.setMot_passe_u("0000");
        r.setDate_u(new Date(2000, 06, 03));
        ReparateurCRUD rcrd = new ReparateurCRUD();
        rcrd.ajouterReparateur(r);
    }
        
}