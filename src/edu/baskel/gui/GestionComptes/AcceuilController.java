/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
    private Circle Circle;

    MembreCRUD mc = new MembreCRUD();
    Membre l = SessionInfo.getLoggedM();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*if(l.getImage_u() != null){
        Circle.setStroke(Color.SEAGREEN);
        Image imagelog;
        imagelog = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + l.getImage_u());
        Circle.setFill(new ImagePattern(imagelog));
        }else{
            Image defaut = new Image("file:/C:\\wamp\\www\\Baskel\\images\\profil.jpg" );
        }*/
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

}
