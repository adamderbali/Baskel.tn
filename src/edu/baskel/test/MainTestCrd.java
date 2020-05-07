
package edu.baskel.test;

import edu.baskel.entities.Alerte;
import edu.baskel.entities.Avis;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.AlerteCRUD;
import edu.baskel.services.AvisCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReparateurCRUD;
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
        r.setLocal_nom("velotn");
        r.setLatitude("12222");
        r.setLongitude("1515155115");
        r.setAdresse_lo("tunis centre ville");
        r.setNum_pro("22795555");
        ReparateurCRUD rcrd = new ReparateurCRUD();
        MembreCRUD mcrd = new MembreCRUD();
        mcrd.ajouterMembres2(r);
        rcrd.ajouterReparateur(r);
        Reparateur r2 = new Reparateur();
        r2 = rcrd.getReparateurById(22);
        System.out.println("////////////************************************//////");
        rcrd.getListeReparateur();
        AlerteCRUD altcrd =new AlerteCRUD();
        Alerte a = new Alerte("panne a rades", "1222222", "1222"," marsa");
        a.setId_alert(5);
        altcrd.updateAlert(a);
        a.setMembre(r2);
        //altcrd.ajouterAlert(a);
        System.out.println("////////////************************************//////");
        altcrd.getListeAlerte();
        Membre m = mcrd.AfficherMembreById(1);
        Avis av = new Avis(m, r2);
        av.setDesc_av("test");
        av.setNote_av(4);
        AvisCRUD avcrd = new AvisCRUD();
        avcrd.ajouterAvis(av);
        avcrd.getListeAvis();
        avcrd.getListeAvisByReparateur(r);
        
        
    }
        
}