/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Membre;
import edu.baskel.services.EnvoiMail;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
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
    Membre l = SessionInfo.loggedM;

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
        if (txtSujet.getText().isEmpty() || txtDescription.getText().isEmpty()) {
            InputValidation.notificationError("Erreur", "Les champs doivent etre tout remplis svp.");

        } else {
            e.envoyerMailAdmin(l.getNom_u() + " " + l.getPrenom_u() + " " + l.getEmail_u()
                    + "///" + "Sujet : " + txtSujet.getText(), txtDescription.getText());
            txtSujet.clear();
            txtDescription.clear();
        }
    }
}
