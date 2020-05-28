/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Evenement_adminController implements Initializable {

    @FXML
    private AnchorPane event_admin;
    @FXML
    private TableView<?> tableAffichage;
    private TableColumn<Evenement, String> colNom;
    @FXML
    private TableColumn<Evenement, String> colLieu;
    @FXML
    private TableColumn<Evenement,Date> colDate;
    @FXML
    private TableColumn<Evenement,Date> colDatePar;
    @FXML
    private TableColumn<Evenement, String> colDescription;
    @FXML
    private TableColumn<Evenement, String> colImage;
    @FXML
    private Button btn_supprimer;
    private TableColumn<Evenement, Integer> colID;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void affichageEvent(){
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       Evenement ev = new Evenement(); 
       EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayAllList();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id_e"));
        
        /*Image imgE = new Image("file:/C:\\wamp\\www\\Baskel\\images\\"+ ev.getImage_e());
        img.setImage(imgE);
        img.setVisible(true);
         txtNom.setEditable(false);
                  txtLieu.setEditable(false);
                  txtDate.setEditable(false);
                  txtDescription.setEditable(false);
                  pathE.setEditable(false);*/
                 
        
   }
    
}
