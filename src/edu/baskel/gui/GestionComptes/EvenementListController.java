/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class EvenementListController implements Initializable {

    @FXML
    private Button btnTousEvent;
    @FXML
    private Button btnEventPar;
    @FXML
    private Button btnEvent;
    @FXML
    private BorderPane borderPane;

    
    
    @FXML
    private void tousEvent(MouseEvent event) {
        load("List_Event_Add_Participation");
    }

    @FXML
    private void parEvent(MouseEvent event) {
          load("List_Event_Add_Participation");
    }

    @FXML
    private void event(MouseEvent event) {
    }
    
    private void load(String evenement){
        Parent root = null;
        try {
           root= FXMLLoader.load(getClass().getResource(evenement+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EvenementListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        borderPane.setCenter(root);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
