/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReparateurCRUD;
import edu.baskel.utils.ConnectionBD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXPasswordField txtmotdepasse;
    @FXML
    private JFXPasswordField txtconfirmation;
    @FXML
    private JFXRadioButton chkhomme;
    @FXML
    private JFXRadioButton chkfemme;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXButton btninscription;
    @FXML
    private JFXDatePicker txtnaissance;
    @FXML
    private JFXTextField txttelephone;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private ToggleGroup sexegrp;
    @FXML
    private JFXTextField txttelpro;
    @FXML
    private JFXTextField txtadrlocal;
    @FXML
    private JFXRadioButton btnrep;
    @FXML
    private JFXButton btnimage;

    @FXML
    private JFXTextField txtimage;

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView img;
    private Image image;

    Connection cnx;
    private PreparedStatement prep;
    private ResultSet res;
    @FXML
    private Pane btnreparateur;
    @FXML
    private Button btnprofil;
    @FXML
    private AnchorPane anchroreparateur;

    public InscriptionController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    /*
    public boolean VerificationExistence() {
        try {
            String sq1 = "SELECT * FROM membre where email_u=?";
            String emailV = txtemail.getText();
            prep = cnx.prepareStatement(sq1);
            prep.setString(1, emailV);
            res = prep.executeQuery();
            if (res.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur d'authentification");
                alert.setContentText("Vous etre deja inscris avec cette adresse");
                alert.show();
                return true;
            }
        } catch (Exception ex) {
            
        }
        return false;
    }
     */
    @FXML
    public void InsertData1(ActionEvent event) {
        //MembreCRUD mc1 = new MembreCRUD();
        MembreCRUD mr = new MembreCRUD();

        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String adresse = txtAdresse.getText();
        String email = txtemail.getText();
        Date datenais = java.sql.Date.valueOf(txtnaissance.getValue());
        String tel = txttelephone.getText();
        String sexe = sexeMembre();
        String motdepasse = txtmotdepasse.getText();
        String conmotdepasse = txtconfirmation.getText();
        String telpro = txttelpro.getText();
        String adrloc = txtadrlocal.getText();
        String imge = txtimage.getText();
        Membre m = new Membre(nom, prenom, adresse, email, sexe, datenais, motdepasse, tel, imge);
        if (mr.VerificationExistence(m) == true) {

            if ((nom.isEmpty()) | (prenom.isEmpty()) | (adresse.isEmpty()) | (email.isEmpty()) | (tel.isEmpty()) | (motdepasse.isEmpty()) | (conmotdepasse.isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur d'ajout");
                alert.setContentText("Les champs nom ,prenom,adresse, email, telephone,mot de passe et confirmation doivent etre tout remplis svp");
                alert.show();
            } else {

                if (motdepasse.equals(conmotdepasse)) {
                    mr.AddUser(m);
                    txtNom.clear();
                    txtPrenom.clear();
                    txtnaissance.setValue(null);
                    txtAdresse.clear();
                    txtimage.clear();
                    txtemail.clear();
                    txtconfirmation.clear();
                    txtmotdepasse.clear();
                    txttelephone.clear();
                    System.out.println("utilisateur ajouté");

                } else 
                {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Mot de passe ");
                    alert1.setHeaderText("Information");
                    alert1.setContentText("verifier votre mot de passe");
                    alert1.showAndWait();

                }
            }
        } 
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'authentification");
            alert.setContentText("Vous etre deja inscris avec cette adresse");
            alert.show();
        }
    }

    @FXML
    public void telechargerPhoto(ActionEvent event) throws IOException {

        FileChooser filechooser = new FileChooser();

        filechooser.setTitle("Open file dialog");
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = filechooser.showOpenDialog(stage);

        if (file != null) {
            txtimage.setText(file.getAbsolutePath());
            System.out.println("" + file.getAbsolutePath());

            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);
            img.setImage(image);
            img.setPreserveRatio(true);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        anchroreparateur.setVisible(false);
        // TODO
        FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Allfiles", "*.*"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"));
    }

    public String sexeMembre() {
        String message = "";
        if (chkhomme.isSelected()) {
            message += chkhomme.getText();
        }
        if (chkfemme.isSelected()) {
            message += chkfemme.getText();
        }
        return message;
    }

    public String resultatRep() {
        String msg = "";

        if (btnrep.isSelected()) {

            txttelpro.setVisible(true);
            msg += "unreparateur";
        } else {
            txttelpro.setVisible(true);

            msg += "nonreparateur";
        }
        return msg;
    }

    @FXML
    public void ProfilPage(ActionEvent event) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("ProfilPage.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void afficherrep(ActionEvent event) {
        anchroreparateur.setVisible(true);
    }
}
