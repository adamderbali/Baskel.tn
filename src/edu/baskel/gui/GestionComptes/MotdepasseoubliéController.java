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
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
    private Button btnQuitter;

    @FXML
    private Button btnDeconnexion;

    @FXML
    private Button btnSupp;
    @FXML
    private Button btnevenmn;
      @FXML
    private MenuItem itemSedeconnecter;
       @FXML
    private MenuButton menu;


    Connection cnx = null;
    PreparedStatement prep = null;
    PreparedStatement prep1 = null;
    ResultSet res = null;
    ResultSet res1 = null;
    private String question;
    private String motdepasse;
    int ran;
    Membre m = SessionInfo.getLoggedM();

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
    EnvoiMail e = new EnvoiMail();

    @FXML
    public void envoyerMail(ActionEvent event) {
        
        e.envoyerMail1(txtentermail.getText());
    }

    //page d authentification
    @FXML
    public void backSidentifier(MouseEvent event) throws IOException {
        System.out.println(m.getImage_u());
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }
    
    //page...
       @FXML
    void redirectionevenmn(ActionEvent event) throws IOException {
   Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
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
        }

    }

    //supprimer son compte
    @FXML
    void supprimerCompte(ActionEvent event) {
        MembreCRUD mr1 = new MembreCRUD();
        Membre l = SessionInfo.getLoggedM();
        mr1.supprimerMembre(l.getId_u());
    }

    //page d acceuil
    @FXML  
    void SeDeconnecter(ActionEvent event) throws IOException {

        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
        SessionInfo.setIduser(0);
        SessionInfo.setLoggedM(null);
        System.out.println(iduser);

    }

    //fermer l application
    @FXML
    void Quitter(ActionEvent event) {
        Platform.exit();
    }

}
