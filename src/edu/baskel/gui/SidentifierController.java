/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXButton;
import edu.baskel.services.FacebookLog;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class SidentifierController implements Initializable {
//test git 2020

    @FXML
    private TextField txtutilisateur;
    @FXML
    private PasswordField txtmotdepasse;
    @FXML
    private Button btnconnexion;
    @FXML
    private Label lblreponse;
    @FXML
    private JFXButton btnsinscrire;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private Label lblpassoublié;
    @FXML
    private Button btnfb;

    Preferences preferences;
    private int idu;
    Connection cnx = null;
    PreparedStatement prep = null;
    ResultSet res = null;

    public SidentifierController() {
        cnx = ConnectionBD.getInstance().getCnx();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferences = Preferences.userNodeForPackage(SidentifierController.class);
        if (preferences != null) {
            if (preferences.get("txtutilisateur", null) != null && !preferences.get("txtutilisateur", null).isEmpty()) {
                txtutilisateur.setText(preferences.get("txtutilisateur", null));
                txtmotdepasse.setText(preferences.get("txtmotdepasse", null));

            }
        }
    }

    /*
    public int Verification() {
        try {
            String sq1 = "SELECT * FROM membre where email_u=? AND mot_passe_u=?";
            String email = txtutilisateur.getText();
            String mot_passe = txtmotdepasse.getText();
            prep = cnx.prepareStatement(sq1);
            prep.setString(1, email);
            prep.setString(2, mot_passe);

            res = prep.executeQuery();

            if (!res.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur d'authentification");
                alert.setContentText("Erreur ,veuillez  verifier vos données");
                alert.show();
            } else {
                lblreponse.setTextFill(Color.GREEN);
                lblreponse.setText("OK!");
                System.out.println("authentification ok!");
                iduser = res.getInt("id_u");
                System.out.println(iduser);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return iduser;

    }
     */
    @FXML
    public void loggin1(ActionEvent event) {
        MembreCRUD mr = new MembreCRUD();
        if (mr.Verification(txtutilisateur.getText(), txtmotdepasse.getText()) != null) {

            if (rememberMe.isSelected()) {

                preferences.put("txtutilisateur", txtutilisateur.getText());
                preferences.put("txtmotdepasse", txtmotdepasse.getText());

            } else {
                preferences.put("txtutilisateur", "");
                preferences.put("txtmotdepasse", "");

            }
            Stage stage = (Stage) rememberMe.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("motdepasseoublié.fxml"));

            } catch (IOException ex) {
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.setIconified(false);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
            SessionInfo.getInstance(iduser);
            SessionInfo.getLoggedM();
            System.out.println(SessionInfo.getInstance(iduser));
        } else {
            Alert alertnum = new InputValidation().getAlert(" Erreur d'authentification", "veuillez  verifier vos données");
            alertnum.showAndWait();
           
        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {

    }

    @FXML
    public void RedirectionRegistration(ActionEvent event) throws IOException {

        Parent redirection_parent = FXMLLoader.load(getClass().getResource("InscriptionMembre.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }

    @FXML
    public void motdepasseOubliéid(MouseEvent event) throws IOException {

        Parent redirection_parent = FXMLLoader.load(getClass().getResource("motdepasseoublié.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }

    @FXML
    public void FacebooLogin(ActionEvent event) {
        FacebookLog fl = new FacebookLog();
        fl.fb();
    }

}
