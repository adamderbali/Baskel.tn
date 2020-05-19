/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Membre;
import edu.baskel.services.EnvoiMail;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MotdepasseoubliéController implements Initializable {

    @FXML
    private Button btnenv;
    @FXML
    private TextField txtentermail;
    @FXML
    private TextField txtcode;
    @FXML
    private JFXButton btnverifycode;
    @FXML
    private ImageView Logout;
    @FXML
    private ImageView exit;

    Connection cnx = null;
    PreparedStatement prep = null;
    PreparedStatement prep1 = null;
    ResultSet res = null;
    ResultSet res1 = null;
    private String question;
    private String motdepasse;
    int ran;
    Membre m = SessionInfo.getLoggedM();
    MembreCRUD mc = new MembreCRUD();
    EnvoiMail e = new EnvoiMail();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public MotdepasseoubliéController() {
        cnx = ConnectionBD.getInstance().getCnx();

    }

    //envoyer le mail de reinitilisation mp
    @FXML
    public void envoyerMail(ActionEvent event) {
        if (mc.VerifEmailMpOublié(txtentermail.getText()) == true) {
            e.envoyerMail1(txtentermail.getText());
        } else {
            InputValidation.notificationError("Adresse Email", "verifier votre adresse email");
        }
    }

    //varification code envoyé par mail
    @FXML
    public void verifierCode(ActionEvent event) throws IOException {
        if ((e.verifierCode1(txtcode.getText()) == true)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NouveauMP.fxml"));
            Parent root2 = loader.load();
            NouveauMPController nmp = loader.getController();
            nmp.setTxtm(txtentermail.getText());
            txtentermail.getScene().setRoot(root2);
        } else {
            System.out.println("code erroné");
            InputValidation.notificationError("Code", "verifier le code envoyer");

        }

    }

    //fermer l application
    @FXML
    void Quitter(MouseEvent event) {
        Platform.exit();
        mc.Deconnexion();

    }

    //page d authentification
    @FXML
    void Deconnexion2(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

}
