/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import impl.org.controlsfx.skin.DecorationPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AdminController implements Initializable {

    @FXML
    private ImageView img_stat;
    @FXML
    private ImageView reclama;
    @FXML
    private ImageView velo;
    @FXML
    private ImageView statis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void event(MouseEvent event) throws IOException {
        
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Evenement_admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Evenement_admin");
        app_stage.show();
    }

    @FXML
    private void rec_admin(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Reclamation_admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Reclamation admin");
        app_stage.show();
    }

    @FXML
    private void gest_velo(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Statistique_interface.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Statistique Interface");
        app_stage.show();
        
    }
    private void Stat(KeyEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Reclamation_admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Reclamation admin");
        app_stage.show();
    }
    private void Rec_admin(KeyEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Reclamation_admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Reclamation admin");
        app_stage.show();
    }

    @FXML
    private void Goback(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Acceuil");
        app_stage.show();
    }

    @FXML
    private void event(KeyEvent event) throws IOException { 
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Evenement_admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Evenement_admin");
        app_stage.show();
    }
    }
    
