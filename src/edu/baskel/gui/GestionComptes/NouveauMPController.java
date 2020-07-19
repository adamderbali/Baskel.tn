/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class NouveauMPController implements Initializable {

    @FXML
    private JFXTextField txtshowpass;
    @FXML
    private JFXTextField txtshowcpass;
    @FXML
    private JFXPasswordField txtNvMp;
    @FXML
    private JFXPasswordField txtCnvMp;
    @FXML
    private FontAwesomeIconView chkmotdepasi;
    @FXML
    private FontAwesomeIconView chkCmotdepasi;
    @FXML
    private Label lblfaible;
    @FXML
    private JFXButton btnConfirmation;
    @FXML
    private JFXTextField txtm;
    @FXML
    private ImageView Logout;
    @FXML
    private ImageView exit;

    Stage owner;
    Connection cnx = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    MembreCRUD r = new MembreCRUD();

    public void setTxtm(String txtmail) {
        this.txtm.setText(txtmail);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtm.setVisible(false);
        txtshowpass.setVisible(false);
        txtshowcpass.setVisible(false);
    }

    //cnx base de données
    public NouveauMPController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    //verifier la correspondance et reinitialiser le mot de passe
    @FXML
    public void changerMP(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        if (InputValidation.md5(txtNvMp.getText()).equals(InputValidation.md5(txtCnvMp.getText()))) {
            r.changerMP(txtm.getText(), InputValidation.md5(txtNvMp.getText()));
            InputValidation.notificationsucces("Mot de passe", "Votre mot de passe a été réinitialisé !");
            //SessionInfo.loggedM = 
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.show();
            /*Alert alertnum = new InputValidation().getAlert("mot de passe", "Votre mot de passe a été réinitialisé !");
            alertnum.showAndWait();*/
        } else {
            InputValidation.notificationError("Mot de passe", "Verifier vos données !");
            //Alert alertnum = new InputValidation().getAlert("mot de passe", "Verifier vos données !");
            //alertnum.showAndWait();
            System.out.println("Erreur ");
        }
    }

    //voir mp
    @FXML
    public void VisualiserMP(MouseEvent event) {
        txtshowpass.setText(txtNvMp.getText());
        txtNvMp.setVisible(false);
        txtshowpass.setVisible(true);
    }

    //cacher mp
    @FXML
    public void hideMP(MouseEvent event) {
        txtNvMp.setVisible(true);
        txtshowpass.setVisible(false);
    }

    //voir cmp
    @FXML
    public void VisualiserCMP(MouseEvent event) {
        txtshowcpass.setText(txtCnvMp.getText());
        txtCnvMp.setVisible(false);
        txtshowcpass.setVisible(true);
    }

    //cacher cmp
    @FXML
    public void hideCMP(MouseEvent event) {
        txtCnvMp.setVisible(true);
        txtshowcpass.setVisible(false);
    }

    //verif password strength
    @FXML
    void passStrength(MouseEvent event) {
        if (!txtNvMp.getText().isEmpty()) {
            if (InputValidation.calculatePasswordStrength(txtNvMp.getText()) < 6) {
                lblfaible.setText("faible");
                lblfaible.setTextFill(Color.RED);
            } else if ((InputValidation.calculatePasswordStrength(txtNvMp.getText()) > 6) && (InputValidation.calculatePasswordStrength(txtNvMp.getText()) <= 8)) {
                lblfaible.setText("fmoyen");
                lblfaible.setTextFill(Color.ORANGE);
            } else {
                lblfaible.setText("fort");
                lblfaible.setTextFill(Color.GREEN);
            }
        } else {
            System.out.println("Pas de mot de passe");
        }
    }

    // page d authentification
    @FXML
    void Deconnexion2(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
        r.Deconnexion();
    }

    //fermer l application
    @FXML
    void Quitter(MouseEvent event) {
        Platform.exit();
        r.Deconnexion();

    }

}
