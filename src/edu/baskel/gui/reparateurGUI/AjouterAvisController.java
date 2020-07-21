/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import edu.baskel.entities.Avis;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.AvisCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterAvisController implements Initializable {
    @FXML
    private Rating rate;
    @FXML
    private Label msg;
    @FXML
    private TextField descr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            msg.setText("Rating : "+newValue);
        });
    }    

    @FXML
    private void lancerAjoutAvis(ActionEvent event) {
        Avis av = new Avis();
        Reparateur R = new Reparateur();
        R.setId_u(4);
        Membre m = new Membre(4);
        AvisCRUD avcrd = new AvisCRUD();
        av.setReparateur(R);
        av.setMembre(m);
        av.setNote_av((int) rate.getRating());
        av.setDesc_av(descr.getText());
        System.out.println(av.toString());
        avcrd.ajouterAvis(av);
    }
    
}
