/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.InputValidation;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import edu.baskel.utils.validationSaisie;
import org.controlsfx.control.textfield.TextFields;

public class Ajouter_EvenementController implements Initializable {

    @FXML
    private AnchorPane fileChooser;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private Button fileChoose;

    @FXML
    private TextField pathE;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView img;

    private Image image;

    
    @FXML
    private JFXTextField txtLieu;
  

    public Ajouter_EvenementController() {

    }

    /* Ajout evenement*/
    
   
    @FXML
    void ajouterEvenement(ActionEvent event) {

        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate.getEditor().getText();
        /* test sur les champs vides ou non*/
        if (((txtNom.getText().isEmpty()) | (txtLieu.getText().isEmpty()) | (txtDate.getEditor().getText().isEmpty()) | (txtDescription.getText().isEmpty()))) {
             validationSaisie.notifInfo("Echec", "Tous les champs doivent etre saisis");
       
         
        } else {
            if ((validationSaisie.validDate(txtDate.getEditor().getText())) == true) {

                System.out.println("------------------");
                validationSaisie.notifInfo("Erreur","La date saisie doit etre superieur à" + date_system );

            } else {
              
                EvenementCRUD Ec = new EvenementCRUD();
                Evenement e = new Evenement(0, txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.
                        getText(), pathE.getText());
                Ec.ajouterEvenement(e);  
              
                validationSaisie.notifConfirm("ok", "Evenement ajouté");
                txtNom.clear();
                txtLieu.clear();
                txtDate.setValue(null);
                txtDescription.clear();
                pathE.clear();
                img.setVisible(false);

            }
        }

    }


     @FXML

    void telecharger(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);

            pathE.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(txtLieu, AutoCompleteAdresse.getAdrGov());
    
    }

    }


