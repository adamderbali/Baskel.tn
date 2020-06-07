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
import com.lynden.gmapsfx.javascript.object.LatLong;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.EnvoiMail;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReparateurCRUD;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.verifEmail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.rgb;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

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
    private JFXTextField txttelpro;
    @FXML
    private JFXTextField txtadrlocal;
    @FXML
    private JFXTextField txtadrlocal1;
    @FXML
    private JFXTextField txtadrlocal2;
    @FXML
    private JFXButton btnConnexion;
    @FXML
    private FontAwesomeIconView chkCmotdepasi;
    @FXML
    private Label lblfaible;
    @FXML
    private Button btnMap;
    @FXML
    private AnchorPane anchorMap;

    Connection cnx;
    private PreparedStatement prep;
    private ResultSet res;
    Image image;
    EnvoiMail e = new EnvoiMail();

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
        txtnaissance.setValue(LocalDate.now());
        txtimage.setVisible(false);

        TextFields.bindAutoCompletion(txtAdresse, AutoCompleteAdresse.getAdrGov());
        TextFields.bindAutoCompletion(txtadrlocal, AutoCompleteAdresse.getAdrGov());
    }

    //verif password strength
    @FXML
    void passStrength(MouseEvent event) {
        if (!txtmotdepasse.getText().isEmpty()) {
            if (InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) < 6) {
                lblfaible.setText("Faible");
                lblfaible.setTextFill(Color.RED);
            } else if ((InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) > 6) && (InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) <= 8)) {
                lblfaible.setText("Moyen");
                lblfaible.setTextFill(Color.ORANGE);
            } else {
                lblfaible.setText("Fort");
                lblfaible.setTextFill(Color.GREEN);
            }
        } else {
            System.out.println("Pase de mot de passe");
        }
    }
// validation champs non vides

    public boolean validerchamps() {
        String dateV = txtnaissance.getEditor().getText();
        if ((txtNom.getText().isEmpty()) && (txtPrenom.getText().isEmpty()) && (txtAdresse.getText().isEmpty())
                && (txtemail.getText().isEmpty()) && (txttelephone.getText().isEmpty()) && (txtmotdepasse.getText().isEmpty())
                && (txtconfirmation.getText().isEmpty()) && (sexeMembre().equals("")) && (txttelpro.getText().isEmpty()) && (txtadrlocal.getText().isEmpty())) {
            InputValidation.notificationError("Erreur d'ajout", "Les champs doivent etre tout remplis svp.");
            txtNom.setFocusColor(rgb(255, 0, 0));
            txtNom.setUnFocusColor(rgb(255, 0, 0));
            txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtPrenom.setFocusColor(rgb(255, 0, 0));
            txtPrenom.setUnFocusColor(rgb(255, 0, 0));
            txtPrenom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtAdresse.setFocusColor(rgb(255, 0, 0));
            txtAdresse.setUnFocusColor(rgb(255, 0, 0));
            txtAdresse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtemail.setFocusColor(rgb(255, 0, 0));
            txtemail.setUnFocusColor(rgb(255, 0, 0));
            txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
            txttelephone.setFocusColor(rgb(255, 0, 0));
            txttelephone.setUnFocusColor(rgb(255, 0, 0));
            txttelephone.setStyle("-fx-prompt-text-fill: #C4151C");
            txtmotdepasse.setFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtconfirmation.setFocusColor(rgb(255, 0, 0));
            txtconfirmation.setUnFocusColor(rgb(255, 0, 0));
            txtconfirmation.setStyle("-fx-prompt-text-fill: #C4151C");
            chkhomme.setStyle("-fx-text-fill: #C4151C");
            chkhomme.setSelectedColor(rgb(255, 0, 0));
            chkhomme.setUnSelectedColor(rgb(255, 0, 0));
            chkfemme.setStyle("-fx-text-fill: #C4151C");
            chkfemme.setSelectedColor(rgb(255, 0, 0));
            chkfemme.setUnSelectedColor(rgb(255, 0, 0));
            txttelpro.setFocusColor(rgb(255, 0, 0));
            txttelpro.setUnFocusColor(rgb(255, 0, 0));
            txttelpro.setStyle("-fx-prompt-text-fill: #C4151C");
            txtadrlocal.setFocusColor(rgb(255, 0, 0));
            txtadrlocal.setUnFocusColor(rgb(255, 0, 0));
            txtadrlocal.setStyle("-fx-prompt-text-fill: #C4151C");
            txtadrlocal1.setFocusColor(rgb(255, 0, 0));
            txtadrlocal1.setUnFocusColor(rgb(255, 0, 0));
            txtadrlocal1.setStyle("-fx-prompt-text-fill: #C4151C");
            txtadrlocal2.setFocusColor(rgb(255, 0, 0));
            txtadrlocal2.setUnFocusColor(rgb(255, 0, 0));
            txtadrlocal2.setStyle("-fx-prompt-text-fill: #C4151C");

            return false;
        } else if ((txtNom.getText().isEmpty()) | (txtPrenom.getText().isEmpty()) | (txtAdresse.getText().isEmpty())
                | (txtemail.getText().isEmpty()) | (txttelephone.getText().isEmpty()) | (txtmotdepasse.getText().isEmpty())
                | (txtconfirmation.getText().isEmpty()) | (sexeMembre().equals("")) | (txttelpro.getText().isEmpty())
                | (txtadrlocal.getText().isEmpty())) {
            
            InputValidation.notificationError("Erreur d'ajout", "Les champs doivent etre tout remplis svp.");

            txtNom.setFocusColor(rgb(255, 0, 0));
            txtNom.setUnFocusColor(rgb(255, 0, 0));
            txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtPrenom.setFocusColor(rgb(255, 0, 0));
            txtPrenom.setUnFocusColor(rgb(255, 0, 0));
            txtPrenom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtAdresse.setFocusColor(rgb(255, 0, 0));
            txtAdresse.setUnFocusColor(rgb(255, 0, 0));
            txtAdresse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtemail.setFocusColor(rgb(255, 0, 0));
            txtemail.setUnFocusColor(rgb(255, 0, 0));
            txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
            txttelephone.setFocusColor(rgb(255, 0, 0));
            txttelephone.setUnFocusColor(rgb(255, 0, 0));
            txttelephone.setStyle("-fx-prompt-text-fill: #C4151C");
            txtmotdepasse.setFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtconfirmation.setFocusColor(rgb(255, 0, 0));
            txtconfirmation.setUnFocusColor(rgb(255, 0, 0));
            txtconfirmation.setStyle("-fx-prompt-text-fill: #C4151C");
            chkhomme.setStyle("-fx-text-fill: #C4151C");
            chkhomme.setSelectedColor(rgb(255, 0, 0));
            chkhomme.setUnSelectedColor(rgb(255, 0, 0));
            chkfemme.setStyle("-fx-text-fill: #C4151C");
            chkfemme.setSelectedColor(rgb(255, 0, 0));
            chkfemme.setUnSelectedColor(rgb(255, 0, 0));
            txttelpro.setFocusColor(rgb(255, 0, 0));
            txttelpro.setUnFocusColor(rgb(255, 0, 0));
            txttelpro.setStyle("-fx-prompt-text-fill: #C4151C");
            txtadrlocal.setFocusColor(rgb(255, 0, 0));
            txtadrlocal.setUnFocusColor(rgb(255, 0, 0));
            txtadrlocal.setStyle("-fx-prompt-text-fill: #C4151C");
            txtadrlocal1.setFocusColor(rgb(255, 0, 0));
            txtadrlocal1.setUnFocusColor(rgb(255, 0, 0));
            txtadrlocal1.setStyle("-fx-prompt-text-fill: #C4151C");
            txtadrlocal2.setFocusColor(rgb(255, 0, 0));
            txtadrlocal2.setUnFocusColor(rgb(255, 0, 0));
            txtadrlocal2.setStyle("-fx-prompt-text-fill: #C4151C");
            Def();
            return false;

        } else {
            return true;
        }
    }

//pour l upload d une photo
    @FXML
    void telechargerPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);

            txtimage.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);

        }
    }

//voir mot de passe
    @FXML
    public void VisualiserMPI(MouseEvent event) {
        txtshowpass.setText(txtmotdepasse.getText());
        txtmotdepasse.setVisible(false);
        txtshowpass.setVisible(true);
    }

    //cacher mot de passe
    @FXML
    public void hideMPI(MouseEvent event) {
        txtmotdepasse.setVisible(true);
        txtshowpass.setVisible(false);
    }

    //visualiser cmp
    @FXML
    public void VisualiserCMP(MouseEvent event) {
        txtshowcpass.setText(txtconfirmation.getText());
        txtconfirmation.setVisible(false);
        txtshowcpass.setVisible(true);

    }

    //cacher cmp 
    @FXML
    public void hideCMP(MouseEvent event) {
        txtconfirmation.setVisible(true);
        txtshowcpass.setVisible(false);

    }

    //chosiir sexe
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

    //validation date
    public boolean verifDate() {
        LocalDate local = LocalDate.now();
        LocalDate d = txtnaissance.getValue();
        System.out.println(d);
        System.out.println(local);
        System.out.println(d.compareTo(local));
        if (d.compareTo(local) > 0) {
            System.out.println("future");
            return false;
        } else {
            System.out.println("passé");

        }

        return true;
    }

    // inscription d un reparateur
    @FXML
    public void insertData(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        MembreCRUD mr = new MembreCRUD();
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String adresse = txtAdresse.getText();
        String email = txtemail.getText();
        Date datenais = java.sql.Date.valueOf(txtnaissance.getValue());
        String tel = txttelephone.getText();
        String sexe = sexeMembre();
        String motdepasse = InputValidation.HshPassword(txtmotdepasse.getText(), "MD5");
        String conmotdepasse = InputValidation.HshPassword(txtconfirmation.getText(), "MD5");
        String telpro = txttelpro.getText();
        String adrloc = txtadrlocal.getText();
        String imge = txtimage.getText();
        String lat = txtadrlocal2.getText();
        String lon = txtadrlocal1.getText();

        Reparateur r = new Reparateur(adrloc, null, telpro, lat, lon, 0, nom, prenom, adresse, email, sexe, datenais, motdepasse, tel, imge, "R");
        if (validerchamps() == true) {
            if (InputValidation.validTextField(txtNom.getText())) {
                InputValidation.notificationError("Nom", "Saisissez votre nom");
                txtNom.setFocusColor(rgb(255, 0, 0));
                txtNom.setUnFocusColor(rgb(255, 0, 0));
                txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                txtNom.setStyle("-fx-text-inner-color: red;");
            } else {

                if (InputValidation.validTextField(txtPrenom.getText())) {
                    InputValidation.notificationError("Prenom", "Saisissez votre prenom");
                    txtPrenom.setFocusColor(rgb(255, 0, 0));
                    txtPrenom.setUnFocusColor(rgb(255, 0, 0));
                    txtPrenom.setStyle("-fx-prompt-text-fill: #C4151C");
                    txtPrenom.setStyle("-fx-text-inner-color: red;");
                } else {

                    if (!InputValidation.validEmail(txtemail.getText())) {
                        InputValidation.notificationError("Email", "Saisissez une adresse email valide");
                        txtemail.setFocusColor(rgb(255, 0, 0));
                        txtemail.setUnFocusColor(rgb(255, 0, 0));
                        txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
                        txtemail.setStyle("-fx-text-inner-color: red;");
                    } else {//verif email vrai

                        if (InputValidation.validPwd(txtmotdepasse.getText()) == 0) {
                            InputValidation.notificationError("Mot de passe", "Saisissez un mot de passe valide");
                            txtmotdepasse.setFocusColor(rgb(255, 0, 0));
                            txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
                            txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
                            txtmotdepasse.setStyle("-fx-text-inner-color: red;");
                        } else {
                            if (InputValidation.PhoneNumber(txttelephone.getText()) == 0) {
                                InputValidation.notificationError("Numero Telephone", "Saisissez un numero de telephone valide");
                                txttelephone.setFocusColor(rgb(255, 0, 0));
                                txttelephone.setUnFocusColor(rgb(255, 0, 0));
                                txttelephone.setStyle("-fx-prompt-text-fill: #C4151C");
                                txttelephone.setStyle("-fx-text-inner-color: red;");
                            } else {
                                if (InputValidation.PhoneNumber(txttelpro.getText()) == 0) {
                                    InputValidation.notificationError("Numero Telephone", "Saisissez un numero de telephone valide");
                                    txttelpro.setFocusColor(rgb(255, 0, 0));
                                    txttelpro.setUnFocusColor(rgb(255, 0, 0));
                                    txttelpro.setStyle("-fx-prompt-text-fill: #C4151C");
                                    txttelpro.setStyle("-fx-text-inner-color: red;");
                                } else {
                                    if (InputValidation.validTextField(txtadrlocal.getText())) {
                                        Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre adresse du locale");
                                        alertNom.showAndWait();
                                    }

                                    if (!(chkhomme.isSelected() | (chkfemme.isSelected()))) {
                                        InputValidation.notificationError("sexe", "Saisissez votre sexe");
                                        chkhomme.setStyle("-fx-text-fill: #C4151C");
                                        chkhomme.setSelectedColor(rgb(255, 0, 0));
                                        chkhomme.setUnSelectedColor(rgb(255, 0, 0));
                                        chkfemme.setStyle("-fx-text-fill: #C4151C");
                                        chkfemme.setSelectedColor(rgb(255, 0, 0));
                                        chkfemme.setUnSelectedColor(rgb(255, 0, 0));
                                    } else {
                                        if (verifDate() == false) {
                                            Alert alertnum = new InputValidation().getAlert("Date", "Saisissez une date valide");
                                            alertnum.showAndWait();
                                            txtnaissance.setValue(null);
                                        } else {
                                            if ((verifEmail.nb(txtemail.getText())) == false) {
                                                InputValidation.notificationError("Email", "Saisissez une adresse email existante");
                                                txtemail.setFocusColor(rgb(255, 0, 0));
                                                txtemail.setUnFocusColor(rgb(255, 0, 0));
                                                txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
                                                txtemail.setStyle("-fx-text-inner-color: red;");
                                            } else {
                                                if (mr.VerificationExistence(r) == true) {

                                                    if (motdepasse.equals(conmotdepasse)) {
                                                        ReparateurCRUD rr = new ReparateurCRUD();
                                                        rr.ajouterReparateur(r);
                                                        e.envoyerMailHistorique(txtemail.getText(), "Bienvenue chez Baskel.tn");
                                                        //mr.adduser2(r);
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

                                                        InputValidation.notificationsucces("Inscription", "Inscription réussite , soyez le bienvenu");
                                                        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                                                        Scene redirection_scene = new Scene(redirection_parent);
                                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                        app_stage.setScene(redirection_scene);
                                                        app_stage.show();

                                                    } else {
                                                        InputValidation.notificationError(" Mot de passe ", "verifier votre mot de passe");
                                                        txtmotdepasse.setFocusColor(rgb(255, 0, 0));
                                                        txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
                                                        txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
                                                        txtmotdepasse.setStyle("-fx-text-inner-color: red;");
                                                        txtconfirmation.setFocusColor(rgb(255, 0, 0));
                                                        txtconfirmation.setUnFocusColor(rgb(255, 0, 0));
                                                        txtconfirmation.setStyle("-fx-prompt-text-fill: #C4151C");
                                                        txtconfirmation.setStyle("-fx-text-inner-color: red;");

                                                    }
                                                } else {
                                                    InputValidation.notificationError(" Erreur d'inscription", "un compte est deja creer avec cette adresse");
                                                    txtemail.setUnFocusColor(rgb(255, 0, 0));
                                                    txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
                                                    txtemail.setStyle("-fx-text-inner-color: red;");
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    //page de Connexion
    @FXML
    public void SidentifierPage(ActionEvent event
    ) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void DefaultThemeNom(MouseEvent event) {
        txtNom.setStyle("");
        txtNom.setFocusColor(Paint.valueOf("#0096a4"));
        txtNom.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemePrenom(MouseEvent event) {
        txtPrenom.setStyle("");
        txtPrenom.setFocusColor(Paint.valueOf("#0096a4"));
        txtPrenom.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeEmail(MouseEvent event) {
        txtemail.setStyle("");
        txtemail.setFocusColor(Paint.valueOf("#0096a4"));
        txtemail.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeTele(MouseEvent event) {
        txttelephone.setStyle("");
        txttelephone.setFocusColor(Paint.valueOf("#0096a4"));
        txttelephone.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeAdresse(MouseEvent event) {
        txtAdresse.setStyle("");
        txtAdresse.setFocusColor(Paint.valueOf("#0096a4"));
        txtAdresse.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeTelPro(MouseEvent event) {
        txttelpro.setStyle("");
        txttelpro.setFocusColor(Paint.valueOf("#0096a4"));
        txttelpro.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeAdrloc(MouseEvent event) {
        txtadrlocal.setStyle("");
        txtadrlocal.setFocusColor(Paint.valueOf("#0096a4"));
        txtadrlocal.setUnFocusColor(Paint.valueOf("#0096a4"));
    }
    
      @FXML
    public void DefaultThemelat(MouseEvent event) {
        txtadrlocal2.setStyle("");
        txtadrlocal2.setFocusColor(Paint.valueOf("#0096a4"));
        txtadrlocal2.setUnFocusColor(Paint.valueOf("#0096a4"));
    }
    
      @FXML
    public void DefaultThemelong(MouseEvent event) {
        txtadrlocal1.setStyle("");
        txtadrlocal1.setFocusColor(Paint.valueOf("#0096a4"));
        txtadrlocal1.setUnFocusColor(Paint.valueOf("#0096a4"));
    }



    @FXML
    public void DefaultThemeMotPass(MouseEvent event) {
        txtmotdepasse.setStyle("");
        txtmotdepasse.setFocusColor(Paint.valueOf("#0096a4"));
        txtmotdepasse.setUnFocusColor(Paint.valueOf("#0096a4"));
        txtconfirmation.setStyle("");
        txtconfirmation.setFocusColor(Paint.valueOf("#0096a4"));
        txtconfirmation.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeSexeH(MouseEvent event) {
        chkhomme.setStyle("-fx-text-fill: #000000");
        chkhomme.setSelectedColor(rgb(0, 150, 164));
        chkhomme.setUnSelectedColor(rgb(0, 0, 0));
        chkfemme.setStyle("-fx-text-fill: #000000");
        chkfemme.setSelectedColor(rgb(0, 0, 0));
        chkfemme.setUnSelectedColor(rgb(0, 0, 0));
    }

    @FXML
    public void DefaultThemeSexeF(MouseEvent event) {
        chkfemme.setStyle("-fx-text-fill: #000000");
        chkfemme.setSelectedColor(rgb(0, 150, 164));
        chkfemme.setUnSelectedColor(rgb(0, 0, 0));
        chkhomme.setStyle("-fx-text-fill: #000000");
        chkhomme.setSelectedColor(rgb(0, 0, 0));
        chkhomme.setUnSelectedColor(rgb(0, 0, 0));
    }

    public void Def() {
        if (!txtNom.getText().isEmpty()) {
            txtNom.setStyle("");
            txtNom.setFocusColor(Paint.valueOf("#0096a4"));
            txtNom.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtPrenom.getText().isEmpty()) {
            txtPrenom.setStyle("");
            txtPrenom.setFocusColor(Paint.valueOf("#0096a4"));
            txtPrenom.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtemail.getText().isEmpty()) {
            txtemail.setStyle("");
            txtemail.setFocusColor(Paint.valueOf("#0096a4"));
            txtemail.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtAdresse.getText().isEmpty()) {
            txtAdresse.setStyle("");
            txtAdresse.setFocusColor(Paint.valueOf("#0096a4"));
            txtAdresse.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txttelephone.getText().isEmpty()) {
            txttelephone.setStyle("");
            txttelephone.setFocusColor(Paint.valueOf("#0096a4"));
            txttelephone.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtmotdepasse.getText().isEmpty()) {
            txtmotdepasse.setStyle("");
            txtmotdepasse.setFocusColor(Paint.valueOf("#0096a4"));
            txtmotdepasse.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtconfirmation.getText().isEmpty()) {
            txtconfirmation.setStyle("");
            txtconfirmation.setFocusColor(Paint.valueOf("#0096a4"));
            txtconfirmation.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (chkhomme.isSelected()) {
            chkhomme.setStyle("-fx-text-fill: #000000");
            chkhomme.setSelectedColor(rgb(0, 150, 164));
            chkhomme.setUnSelectedColor(rgb(0, 0, 0));
            chkfemme.setStyle("-fx-text-fill: #000000");
            chkfemme.setSelectedColor(rgb(0, 0, 0));
            chkfemme.setUnSelectedColor(rgb(0, 0, 0));
        }
        if (chkfemme.isSelected()) {
            chkfemme.setStyle("-fx-text-fill: #000000");
            chkfemme.setSelectedColor(rgb(0, 150, 164));
            chkfemme.setUnSelectedColor(rgb(0, 0, 0));
            chkhomme.setStyle("-fx-text-fill: #000000");
            chkhomme.setSelectedColor(rgb(0, 0, 0));
            chkhomme.setUnSelectedColor(rgb(0, 0, 0));
        }
        if (!txttelpro.getText().isEmpty()) {
            txttelpro.setStyle("");
            txttelpro.setFocusColor(Paint.valueOf("#0096a4"));
            txttelpro.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtadrlocal.getText().isEmpty()) {
            txtadrlocal.setStyle("");
            txtadrlocal.setFocusColor(Paint.valueOf("#0096a4"));
            txtadrlocal.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtadrlocal1.getText().isEmpty()) {
            txtadrlocal1.setStyle("");
            txtadrlocal1.setFocusColor(Paint.valueOf("#0096a4"));
            txtadrlocal1.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtadrlocal2.getText().isEmpty()) {
            txtadrlocal2.setStyle("");
            txtadrlocal2.setFocusColor(Paint.valueOf("#0096a4"));
            txtadrlocal2.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
    }

    @FXML
    void MapGoogle(ActionEvent event) throws IOException {
        /*Parent redirection_parent = FXMLLoader.load(getClass().getResource("GmapView.fxml"));//
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();*/

        //AnchorPane pane = FXMLLoader.load(getClass().getResource("GmapView.fxml"));
        //anchorMap.getChildren().setAll(pane);
        
        /*FXMLLoader fxmll = new FXMLLoader(getClass().getResource("GmapView.fxml"));
        Parent root = (Parent)fxmll.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();*/
        
        /* GmapViewController controller2 = new GmapViewController(this);
         controller2.showStage();*/
           
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GmapView.fxml"));
        Parent root2 = loader.load();
        GmapViewController nmp = loader.getController();
        nmp.setNom(txtNom.getText());
        nmp.setPrenom(txtPrenom.getText());
        nmp.setMail(txtemail.getText());
        nmp.setAdresse(txtAdresse.getText());
        nmp.setPasse(txtmotdepasse.getText());
        nmp.setCpasse(txtconfirmation.getText());
        nmp.setTele(txttelephone.getText());
        nmp.setTelpro(txttelpro.getText());
        System.out.println(txttelpro.getText());
        txtNom.getScene().setRoot(root2);
    }

    public void setTxtadrlocal2(String txtadrlocal) {
        this.txtadrlocal2.setText(txtadrlocal);

    }

    public void setTxtadrlocal1(String txtadrlocal1) {
        this.txtadrlocal1.setText(txtadrlocal1);
    }

    public JFXTextField getTxtNom() {
        return txtNom;
    }

    public void setTxtNom(String txtNom) {
        this.txtNom.setText(txtNom);
    }

    public JFXTextField getTxtemail() {
        return txtemail;
    }

    public void setTxtemail(String txtemail) {
        this.txtemail.setText(txtemail);
    }

    public JFXPasswordField getTxtmotdepasse() {
        return txtmotdepasse;
    }

    public void setTxtmotdepasse(String txtmotdepasse) {
        this.txtmotdepasse.setText(txtmotdepasse);
    }

    public JFXPasswordField getTxtconfirmation() {
        return txtconfirmation;
    }

    public void setTxtconfirmation(String txtconfirmation) {
        this.txtconfirmation.setText(txtconfirmation);
    }

    public JFXTextField getTxtPrenom() {
        return txtPrenom;
    }

    public void setTxtPrenom(String txtPrenom) {
        this.txtPrenom.setText(txtPrenom);
    }

    public JFXTextField getTxttelephone() {
        return txttelephone;
    }

    public void setTxttelephone(String txttelephone) {
        this.txttelephone.setText(txttelephone);
    }

    public JFXTextField getTxttelpro() {
        return txttelpro;
    }

    public void setTxttelpro(String txttelpro) {
        this.txttelpro.setText(txttelpro);    }
   

    public JFXTextField getTxtAdresse() {
        return txtAdresse;
    }

    public void setTxtAdresse(String txtAdresse) {
        this.txtAdresse.setText(txtAdresse);
    }
    
    
    
    

}
