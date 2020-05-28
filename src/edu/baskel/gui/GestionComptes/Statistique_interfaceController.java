/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import impl.org.controlsfx.skin.DecorationPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Statistique_interfaceController implements Initializable {

    @FXML
    private AnchorPane Stat_pan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    @FXML
    private void Top_user(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("user_stat.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Membres actif");
        

        app_stage.show();
    }

    @FXML
    private void Top_interface(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Stat_chart.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Interface");

        app_stage.show();
    }
/*@FXML
    private void Top_user(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("user_stat.fxml"));
        Stat_pan.getChildren().setAll(pane);
    }
@FXML
    private void Top_interfacee(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Stat_chart.fxml"));
        Stat_pan.getChildren().setAll(pane);
    }*/

   /* @FXML
    private void Top_user(KeyEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("user_stat.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Membres actif");

        app_stage.show();
    }*/

    /*@FXML
    private void Goback(ActionEvent event) {
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Stat_pan.getChildren().setAll(pane);
        } catch (IOException ex) {
ex.printStackTrace();        }
    }    
@FXML
    private void Goback(KeyEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.show();
        Stat_pan.getChildren().setAll(pane);
        } */
    

    @FXML
    private void Goback(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Admin");
        app_stage.show();
    }

   /* @FXML
    private void Top_interface(KeyEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Stat_chart.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Interface");

        app_stage.show();
    }*/
  
   
}
