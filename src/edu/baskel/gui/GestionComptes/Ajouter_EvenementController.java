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
import edu.baskel.entities.Membre;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Ajouter_EvenementController implements Initializable {

    @FXML
    private AnchorPane fileChooser;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtLieu;

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
    
    Membre m =SessionInfo.getLoggedM();
    
    public Ajouter_EvenementController() {

    }

    /* Ajout evenement*/
    @FXML
    void ajouterEvenement(ActionEvent event) {

        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String date = txtDate.getEditor().getText();
        /* test sur les champs vides ou non*/
        if (((txtNom.getText().isEmpty()) | (txtLieu.getText().isEmpty()) | (txtDate.getEditor().getText().isEmpty()) | (txtDescription.getText().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Les champs doivent etre tout remplis svp");
            alert.show();
        } /* test sur les dates*/ else if (date.compareTo(date_system) < 0) {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Date erronée");
            alert2.setContentText("La date saisie doit etre superieur à" + date_system);
            alert2.show();
        } 
        else {

            EvenementCRUD Ec = new EvenementCRUD();
            
            Evenement e = new Evenement(0, txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.
                    getText(), pathE.getText(),m.getId_u()); 
            Ec.ajouterEvenement(e);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("ajout ok");
            alert1.setContentText("Event added");
            alert1.show();

            txtNom.clear();
            txtLieu.clear();
            txtDate.setValue(null);
            txtDescription.clear();
            pathE.clear();
        }

    }

    /* button parcourrir photo*/
/*
    @FXML
    void telechargerPhoto(ActionEvent event) {

        FileChooser filechooser = new FileChooser();

        filechooser.setTitle("Open file dialog");
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = filechooser.showOpenDialog(stage);

        if (file != null) {
            pathE.setText(file.getAbsolutePath());
            System.out.println("" + file.getAbsolutePath());

            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);
            img.setImage(image);
            img.setPreserveRatio(true);
        }

    }*/
    
    
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
             String photo1 ; 
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);
            
        }
           }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    /*    FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Allfiles", "*.*"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"));*/

    }

}
