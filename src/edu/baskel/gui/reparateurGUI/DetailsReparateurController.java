/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import edu.baskel.entities.Reparateur;
import edu.baskel.services.AvisCRUD;
import edu.baskel.services.ReparateurCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailsReparateurController implements Initializable {
    @FXML
    private TextArea nomloc;
    @FXML
    private TextArea nom;
    @FXML
    private TextArea prenom;
    @FXML
    private TextArea adresse;
    @FXML
    private TextArea tel;
    @FXML
    private TextArea email;
    @FXML
    private TextArea note;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReparateurCRUD rpcrd = new ReparateurCRUD();
        Reparateur r = rpcrd.getReparateurById(22);
        AvisCRUD avcrd = new AvisCRUD();
        nomloc.setText(r.getLocal_nom());
        nom.setText(r.getNom_u());
        prenom.setText(r.getPrenom_u());
        adresse.setText(r.getAdresse_lo());
        tel.setText(r.getNum_pro());
        email.setText(r.getEmail_u());
        note.setText(String.valueOf(avcrd.getavgAvisperDep(r)));
        
    }    
    
}
