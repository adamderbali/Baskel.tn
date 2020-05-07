/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

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
     
 
 
     }
