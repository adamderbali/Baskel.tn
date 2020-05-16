/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AcceuilController implements Initializable {

    @FXML
    private MenuButton btnCompte;
    @FXML
    private MenuItem btnSEdeconnecter;
    @FXML
    private MenuItem btnMOnPRofil;
    @FXML
    private AnchorPane ANchorProfil;
    @FXML
    private AnchorPane anchorproincipal;
    @FXML
    private Label lblCntactezNous;
    @FXML
    private Rating rateUs;
    @FXML
    private Text evaluez;
    @FXML
    private JFXButton btnEnvoyer;
    @FXML
    private Button btnforum;
    @FXML
    private ImageView Logout;
    @FXML
    private ImageView exit;

    MembreCRUD mc = new MembreCRUD();
    Membre l = SessionInfo.getLoggedM();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rateUs.setVisible(false);
        btnEnvoyer.setVisible(false);
    }

    @FXML
    public void Deconnexion(ActionEvent event) {

    }

//afficher page profil a partir de l acceuil
    @FXML
    public void Profil(ActionEvent event) throws IOException {
        if (mc.VerifReparateur() == false) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilPage.fxml"));
            ANchorProfil.getChildren().setAll(pane);
        } else if (mc.VerifReparateur() == true) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilPageReparateur.fxml"));
            ANchorProfil.getChildren().setAll(pane);
        }
    }

    //se deconnecter a partir de l acceuil
    @FXML
    public void Deco(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        ANchorProfil.getChildren().setAll(pane);
    }

    @FXML
    void AfficherStars(MouseEvent event) {
        rateUs.setVisible(true);
        btnEnvoyer.setVisible(true);
    }

    @FXML
    void EnvoyerNote(MouseEvent event) {
        System.out.println("Rate" + "   " + l.getId_u() + "  " + rateUs.getRating());
    }

    @FXML
    void HideRating(MouseEvent event) {
        rateUs.setVisible(false);
        btnEnvoyer.setVisible(false);
    }

    @FXML
    public void RedirectionContactezNs(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("ContactUs.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

    @FXML
    void forum(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("ForumFXML.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

    @FXML
    void Deconnexion2(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
        mc.Deconnexion();
    }
    
    //fermer l application
    @FXML
    void Quitter(MouseEvent event) {
        Platform.exit();
        mc.Deconnexion();

    }

}
