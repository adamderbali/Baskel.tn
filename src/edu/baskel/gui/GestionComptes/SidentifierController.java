/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.services.FacebookLog;
import edu.baskel.services.HistoriqueCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.PDF;
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
    private JFXTextField txtshowmp;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private Label lblpassoublié;
    @FXML
    private Button btnfb;
    @FXML
    private FontAwesomeIconView chkmotdepasse;
    @FXML
    private JFXButton btnsinscrire1;
    @FXML
    private Label CDutil;

    Preferences preferences;
    private int idu;
    Connection cnx = null;
    PreparedStatement prep = null;
    ResultSet res = null;
    HistoriqueCRUD hh = new HistoriqueCRUD();

    public SidentifierController() {
        cnx = ConnectionBD.getInstance().getCnx();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtshowmp.setVisible(false);
        txtmotdepasse.setVisible(true);
        preferences = Preferences.userNodeForPackage(SidentifierController.class);
        if (preferences != null) {
            if (preferences.get("txtutilisateur", null) != null && !preferences.get("txtutilisateur", null).isEmpty()) {
                txtutilisateur.setText(preferences.get("txtutilisateur", null));
                txtmotdepasse.setText(preferences.get("txtmotdepasse", null));

            }
        }
    }

// visualiser le mot de passe
    @FXML
    public void VisualiserMP(MouseEvent event) {
        txtshowmp.setText(txtmotdepasse.getText());
        txtmotdepasse.setVisible(false);
        txtshowmp.setVisible(true);
    }

// cacher le mot de passe
    @FXML
    public void hideMP(MouseEvent event) {
        txtshowmp.setText(txtmotdepasse.getText());
        txtmotdepasse.setVisible(true);
        txtshowmp.setVisible(false);
    }

//se connecter et se souvenir de moi
    @FXML
    public void loggin1(ActionEvent event) throws DocumentException {
        MembreCRUD mr = new MembreCRUD();
        if (mr.Verification(txtutilisateur.getText(), txtmotdepasse.getText()) != null) {
            if (mr.ValidationBan(txtutilisateur.getText()) != 0) {

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
                hh.UpdateLastCnx(iduser);

                // System.out.println(SessionInfo.getInstance(iduser));
            } else {
                //InputValidation.notificationError("Erreur d'authentification", "Vous Etes banni a cause de reclamation");

                Alert alertn = new InputValidation().getAlert(" Erreur d'authentification", "vous etes banni s");
                alertn.showAndWait();
            }
        } else {
            //InputValidation.notificationError("Erreur d'authentification", "veuillez  verifier vos données");

            Alert alertnum = new InputValidation().getAlert(" Erreur d'authentification", "veuillez  verifier vos données");
            alertnum.showAndWait();

        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    //Lire COnd d utilisations + mail historique***********************
    @FXML
    void LireCondUtil(MouseEvent event) throws Exception {
        PDF.pdfRead();
        //hh.LastCnx();
        //hh.UpdateLastCnx(iduser);

    }

//redirection pasge d inscription membre
    @FXML
    public void RedirectionRegistration(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("InscriptionMembre.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }

//redirection page inscription membre
    @FXML
    public void RedirectionRegistrationRep(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("InscriptionReparateur.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }

//page mot de passe oublié
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
