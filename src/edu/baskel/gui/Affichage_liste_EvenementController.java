/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.ConnectionBD;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Affichage_liste_EvenementController implements Initializable {
    
        @FXML
    private TableView<Evenement> tableAffichage;
@FXML
    private TableColumn<Evenement,String> colNom;

    @FXML
    private TableColumn<Evenement,String> colLieu;

    @FXML
    private TableColumn<Evenement,String> colDate;

    @FXML
    private TableColumn<Evenement,String> colDescription;

    @FXML
    private TableColumn<Evenement,String> colImage;

    @FXML
    private JFXButton participer;
    
    public Affichage_liste_EvenementController(){
        
    }

    
    public void affichageEvenement()
    {
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayAll();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        
       colNom.setCellValueFactory(new PropertyValueFactory <>("nom_e"));
       colLieu.setCellValueFactory(new PropertyValueFactory <>("lieu_e"));
       colDate.setCellValueFactory(new PropertyValueFactory <>("date_e"));
       colDescription.setCellValueFactory(new PropertyValueFactory <>("description_e"));
       colImage.setCellValueFactory(new PropertyValueFactory <>("image_e"));
       tableAffichage.setItems(obser);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void participerEvenement(ActionEvent event) {

    }

  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageEvenement();
    }    
    
}
