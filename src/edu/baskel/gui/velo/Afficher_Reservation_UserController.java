/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Reservation;
import edu.baskel.services.ReservationCRUD;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Afficher_Reservation_UserController implements Initializable {
    @FXML
    private TableView<Reservation> TabAffResv;

    @FXML
    private TableColumn<Reservation, String> Colnums;

    @FXML
    private TableColumn<Reservation, String>Coldater;

    @FXML
    private TableColumn<Reservation, String> Colnbh;

    @FXML
    private TableColumn<Reservation, String> Coldatedeb;

    @FXML
    private TableColumn<Reservation, String> colnom;

    @FXML
    private TableColumn<Reservation, String> colpren;

    @FXML
    private Button back;
    
    Membre m = SessionInfo.getLoggedM();
    @FXML
    void retour(ActionEvent event) {
             try {
             //System.exit(0);
             Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_mes_velos.fxml"));
             Scene redirection_scene = new Scene(redirection_parent);
             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             app_stage.setScene(redirection_scene);
             app_stage.show();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }
    
    public void afficher(){
        ReservationCRUD resrvList = new ReservationCRUD();
        List<Reservation> partListU = resrvList.afficherReservParU(m.getId_u());
        ObservableList obser;
        obser = FXCollections.observableArrayList(partListU);
        TableColumn<Reservation, String> c1 = new TableColumn<Reservation, String>("first");
        
        colnom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMembre().getNom_u()));
        colpren.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMembre().getPrenom_u()));
       
        Colnums.setCellValueFactory(new PropertyValueFactory <>("num_serie"));
       
       Coldater.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date_res"));
      // Coldater.setCellValueFactory(new PropertyValueFactory <>("date_res"));
       Colnbh.setCellValueFactory(new PropertyValueFactory <>("nbr_heure"));
       Coldatedeb.setCellValueFactory(new PropertyValueFactory <>("date_db_res"));
       TabAffResv.setItems(obser);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
    }    
    
}
