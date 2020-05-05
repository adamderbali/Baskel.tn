package edu.baskel.test;


/**
 *
 * @author ASUS
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Guitestmain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/reparateurGUI/ListeAlerte.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("../gui/evenementGUI/Affichage_List_Evenement_Ajout_Participation.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();

    }

   
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        launch(args);
        
        
    }
        
}
