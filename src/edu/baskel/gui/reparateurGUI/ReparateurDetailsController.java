/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReparateurDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField txtNom;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtNom.setText("khaled");
    }
    
}