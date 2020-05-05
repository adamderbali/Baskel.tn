/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;


import edu.baskel.entities.Alerte;
import edu.baskel.entities.Membre;
import edu.baskel.services.AlerteCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutAlerteController implements Initializable {

    @FXML
    private TextField descalrt;
    @FXML
    private TextField adrss;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
        } catch (Exception e) {
        }

    }

    @FXML
    void lancerAjout(ActionEvent event) {
        AlerteCRUD alrtcrd = new AlerteCRUD();
        Alerte a = new Alerte();
        a.setAdresse_a(adrss.getText());
        a.setLatitude_a("1221");
        a.setLongitude_a("1151");
        a.setDescription_a(descalrt.getText());
        Membre m = new Membre(1);
        a.setMembre(m);
        alrtcrd.ajouterAlert(a);
    }

}
