/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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

    public static boolean validDate(String txtDate) {
        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate;

        if (date.compareTo(date_system) < 0) {
            return true;
        } else {
            return false;

        }
    }

    public static boolean confrimSuppression(String msg, String mes) {

        boolean clear = false;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("");
        alert.setTitle(msg);
        alert.setHeaderText(mes);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            clear = true;
        }
        return clear;
    }

    public static boolean confrimAnnulation() {

        boolean clear = false;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("");
        alert.setTitle("Annulation participation");
        alert.setHeaderText("Voulez vraiment annuler votre particpation");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            clear = true;
        }
        return clear;
    }

    public static void notif(String mes, String message) {
        Notifications notificationBuilder = Notifications.create()
                .title(mes)
                .text(message).darkStyle()
                //.graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
              //  .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked");
                    }
                });
        //notificationBuilder.darkStyle();
        notificationBuilder.showError();

    }

    public static void notifInfo(String mes, String message) {
        Notifications notificationBuilder = Notifications.create()
                .title(mes)
                .text(message).darkStyle()
                //.graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
              //  .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked");
                    }
                });
        //notificationBuilder.darkStyle();
        notificationBuilder.showInformation();

    }

    public static void notifConfirm(String mes, String message) {
        Notifications notificationBuilder = Notifications.create()
                .title(mes)
                .text(message).darkStyle()
                //.graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
              //  .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked");
                    }
                });
        //notificationBuilder.darkStyle();
        notificationBuilder.showConfirm();

    }

    public static boolean verifiNumberPart(String nombre) {
          
        try {
            
            int i = Integer.parseInt(nombre);
            System.out.println("C'est un entier");
            return true;
           
        } catch (Exception e) {
            System.out.println("Je ne suis pas un entier, et alors ca te derange ?");
        }
        
    
             return false;
    }
    
      public static int validNumberParticipant(String ch) {
        int i = 0;
        {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }
      
        public static boolean validNumberParticipantU(String ch) {
          int i;
        {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return true;
            }
        }
        return true;
    }
}
