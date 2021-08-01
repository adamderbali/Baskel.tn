/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Hela
 */
public class VeloMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("Afficher_Tout_Velo.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Afficher_Reservation_User.fxml"));
       
       //Parent root = FXMLLoader.load(getClass().getResource("Afficher_mes_velos.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("Reserver_velo.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Ajouter_velo.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Afficher_mes_reservations.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        
       
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
