/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import static edu.baskel.utils.CronJob.jobs;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
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
        //jobs();   //a voir (CronJOb)
               // RunThreadSaily.checkThreadState();

    }
        
}
