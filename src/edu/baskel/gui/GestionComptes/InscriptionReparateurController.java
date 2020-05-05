/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.InputValidation;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class InscriptionReparateurController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Pane btnreparateur;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXPasswordField txtmotdepasse;
    @FXML
    private JFXPasswordField txtconfirmation;
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
    private JFXRadioButton chkhomme;
    @FXML
    private ToggleGroup sexegrp;
    @FXML
    private JFXRadioButton chkfemme;
    @FXML
    private JFXButton btnimage;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private ImageView img;
    @FXML
    private FontAwesomeIconView chkmotdepasi;
    @FXML
    private JFXTextField txtshowpass;
    @FXML
    private JFXTextField txtshowcpass;
    @FXML
    private Button btnprofil;
    @FXML
    private JFXTextField txttelpro;
    @FXML
    private JFXTextField txtadrlocal;
    Connection cnx;
    private PreparedStatement prep;
    private ResultSet res;
    Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtmotdepasse.setVisible(true);
        txtconfirmation.setVisible(true);
        txtshowpass.setVisible(false);
        txtshowcpass.setVisible(false);

        FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Allfiles", "*.*"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"));
    }

    // inscription d un reparateur
    @FXML
    public void insertData(ActionEvent event) {
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

        Reparateur r = new Reparateur(adrloc, telpro, nom, prenom, adresse, email, sexe, datenais, motdepasse, tel);
        if (InputValidation.validTextField(txtNom.getText())) {
            Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre nom");
            alertNom.showAndWait();
        } else {

            if (InputValidation.validTextField(txtPrenom.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez votre Prenom");
                alertPrenom.showAndWait();
            } else {

                if (!InputValidation.validEmail(txtemail.getText())) {
                    Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email valide");
                    alertEmail.showAndWait();
                } else {
                    if (InputValidation.validPwd(txtmotdepasse.getText()) == 0) {
                        Alert alertnum = new InputValidation().getAlert("Numero TelephoneMot de passe", "Saisissez un mot de passe valide");
                        alertnum.showAndWait();
                    } else {
                        if (InputValidation.PhoneNumber(txttelephone.getText()) == 0) {
                            Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                            alertnum.showAndWait();
                        } else {
                            if (InputValidation.PhoneNumber(txttelpro.getText()) == 0) {
                                Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                                alertnum.showAndWait();
                            }
                            if (!(chkhomme.isSelected() | (chkfemme.isSelected()))) {
                                Alert alertnum = new InputValidation().getAlert("sexe", "Saisissez votre sexe");
                                alertnum.showAndWait();
                            } else {
                                if (mr.VerificationExistence(r) == true) {

                                    if (motdepasse.equals(conmotdepasse)) {
                                        mr.adduser2(r);
                                        txtNom.clear();
                                        txtPrenom.clear();
                                        txtnaissance.setValue(null);
                                        txtAdresse.clear();
                                        txtimage.clear();
                                        txtemail.clear();
                                        txtconfirmation.clear();
                                        txtmotdepasse.clear();
                                        txttelephone.clear();
                                        chkhomme.setSelected(false);
                                        chkfemme.setSelected(false);
                                        txttelpro.clear();
                                        txtadrlocal.clear();
                                        System.out.println("Reparateur ajouté");

                                    } else {
                                        Alert alertnum = new InputValidation().getAlert(" Mot de passe ", "verifier votre mot de passe");
                                        alertnum.showAndWait();

                                    }
                                } else {
                                    Alert alertnum = new InputValidation().getAlert(" Erreur d'inscription", "un compte est deja creer avec cette adresse");
                                    alertnum.showAndWait();
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    
    public boolean validerchamps() {
        if ((txtNom.getText().isEmpty()) | (txtPrenom.getText().isEmpty()) | (txtAdresse.getText().isEmpty())
                | (txtemail.getText().isEmpty()) | (txttelephone.getText().isEmpty()) | (txtmotdepasse.getText().isEmpty())
                | (txtconfirmation.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Les champs nom ,prenom,adresse, email, telephone,mot de passe et confirmation doivent etre tout remplis svp");
            alert.show();
            return false;
        } else {
            return true;
        }
    }

//pour l upload d une photo
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

   

    @FXML
    public void VisualiserMPI(MouseEvent event) {
        txtshowpass.setText(txtmotdepasse.getText());
        txtshowcpass.setText(txtconfirmation.getText());
        txtmotdepasse.setVisible(false);
        txtconfirmation.setVisible(false);
        txtshowpass.setVisible(true);
        txtshowcpass.setVisible(true);
    }

    @FXML
    public void hideMPI(MouseEvent event) {
        txtmotdepasse.setVisible(true);
        txtconfirmation.setVisible(true);
        txtshowpass.setVisible(false);
        txtshowcpass.setVisible(false);
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

    @FXML
    public void ProfilPage(ActionEvent event) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("ProfilPage.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}