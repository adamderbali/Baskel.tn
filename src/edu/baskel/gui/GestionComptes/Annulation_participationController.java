/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Participation;
import edu.baskel.services.ParticipationCrud;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private TableView<Participation> tableAffichage;

    @FXML
    private TableColumn<Participation, String> colNom;

    @FXML
    private TableColumn<Participation,String> colDate;

    @FXML
    private TableColumn<Participation,String> colLieu;

    @FXML
    private TableColumn<Participation,String> colDescription;

    @FXML
    private TableColumn<Participation,String> colImage;

    @FXML
    private JFXButton participer;


    public Annulation_participationController() {
    }

    
    public void affichageEvenementP() {

        ParticipationCrud Pc = new ParticipationCrud();
        ArrayList arrayList;
        arrayList = (ArrayList) Pc.displayByUserP();
        ObservableList obser;
        System.out.println("-------22222---------"+arrayList);
         obser = FXCollections.observableArrayList(arrayList);
       
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
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
