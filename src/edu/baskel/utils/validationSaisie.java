/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author sabri
 */
public class validationSaisie {
   Connection cnx;
    public validationSaisie() {
        
         cnx = ConnectionBD.getInstance().getCnx();
    }
    
    
    
    public static boolean validTextField(String test) {
        return test.equals("");
    }

       public Alert getAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }
       
     public static boolean validDate(String txtDate)   {
         String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate;
        
        if(date.compareTo(date_system) < 0)
            return true ;
        else 
        {return false;
        
        }
     }
    
     public static void notificationConfiramtion(String title, String message) {
         
      /*   Notifications.create()
              .title(title)
              .text(message).darkStyle().position(Pos.CENTER)
              .showWarning();*/
         Notifications notification = Notifications.create()
                    .title(title)
                    .text(message)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.out.println("Notification");
             }
         });
           notification.darkStyle();     
           notification.showConfirm();

         
     }
   
     public static void notificationErreur(String title, String message) {
         
        Notifications.create()
              .title(title)
              .text(message).darkStyle().position(Pos.CENTER)
         
                 .showWarning();
     /* Image image = new Image(is);*/
       /*  Notifications notification = Notifications.create()
                    .title(title)
                    .text(message)
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_CENTER);
           
           notification.darkStyle();     
           notification.showError();*/

         
     }
     
     public static void notificationInformation(String title, String message) {
         
      /*   Notifications.create()
              .title(title)
              .text(message).darkStyle().position(Pos.CENTER)
              .showWarning();*/
         Notifications notification = Notifications.create()
                    .title(title)
                    .text(message)
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.out.println("Notification");
             }
         });
           notification.darkStyle();
           notification.showInformation();

         
     }
    
     }
