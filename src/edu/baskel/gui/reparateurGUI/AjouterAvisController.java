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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    @FXML
    private ImageView retour;

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

    @FXML
    private void Goback(MouseEvent event)throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("../reparateurGUI/MenuReparateur.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Acceuil");
        app_stage.show();
    }
}
