/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reclamation;
import edu.baskel.services.ReclamationCRUD;
import edu.baskel.utils.SessionInfo;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Envoie_ReclamationController implements Initializable {

    @FXML
    private AnchorPane pane_rec;
    @FXML
    private Label tf_titre_rec;
    @FXML
    private Label label_objet;
    @FXML
    private JFXTextField tf_objet_rec;
    @FXML
    private Label label_desc_rec;
    @FXML
    private JFXTextArea textarea_rec;
    @FXML
    private Button btn_rec;
    @FXML
    private Label label_aqui;
    @FXML
    private JFXTextField tf_nom_reclamer;
    @FXML
    private Label text;
    @FXML
    private Tooltip tp_envoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void send_reclamation(ActionEvent event) {
            //int id_us = SessionInfo.iduser;
            //Membre reclamer;
            ReclamationCRUD s_rec =new ReclamationCRUD();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Reclamation reclamation=  new Reclamation( textarea_rec.getText(),tf_objet_rec.getText(),2,1);
            s_rec.ajouterReclamation(reclamation); //a modifier  
            text.setText("Votre Reclamation a été envoyer avec succés ");
            
    }

    @FXML
    private void send_reclamation(WindowEvent event) {
    }
}
