/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Evenement;
import edu.baskel.services.ParticipationCrud;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class Annulation_participationController implements Initializable {
    
    
    @FXML
    private AnchorPane anchor1;

    @FXML
    private TableView<Evenement> tableAffichage;

    @FXML
    private TableColumn<Evenement, String> colNom;

    @FXML
    private TableColumn<Evenement,String> colDate;

    @FXML
    private TableColumn<Evenement,String> colLieu;

    @FXML
    private TableColumn<Evenement,String> colDescription;

    @FXML
    private TableColumn<Evenement,String> colImage;

    @FXML
    private JFXButton participer;


    public Annulation_participationController() {
    }

    
    public void affichageEvenementP() {

        ParticipationCrud Pc = new ParticipationCrud();
        ArrayList arrayList;
        arrayList = (ArrayList) Pc.displayByUserP();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        tableAffichage.setItems(obser);

    }
    
    
     @FXML
    void annulerParticipation(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        affichageEvenementP();
      
    }    
    
}
