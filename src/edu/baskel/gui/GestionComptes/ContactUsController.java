/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.services.EnvoiMail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ContactUsController implements Initializable {

    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtMP;
    @FXML
    private JFXTextField txtSujet;
    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private JFXButton btnEnvoyer;

    EnvoiMail e = new EnvoiMail();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //mail de la part du membre 
    @FXML
    public void EnvoyerMail(ActionEvent event) {
        e.envoyerMailAdmin("Nom et prenom : " + txtEmail.getText() + "///" + "Adresse email : " + txtMP.getText()
                + "///" + "Sujet : " + txtSujet.getText(), txtDescription.getText());
    }

}
