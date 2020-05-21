/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Skander
 */
public class Main_reclam extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Affichage_user.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("ban_user.fxml"));
            //ReclamationController
           //Parent root = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            Scene scene = new Scene(root);
  
            primaryStage.setTitle("recc");
            //primaryStage.setTitle("Banner user");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    
}
    public static void main(String[] args) {
        launch(args);
    }
}
