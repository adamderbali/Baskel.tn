/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Reservation;
import edu.baskel.services.ReservationCRUD;
import edu.baskel.services.VeloCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Afficher_mes_reservationsController implements Initializable {
     @FXML
    private TableView<Reservation> TabAffResv;

    @FXML
    private TableColumn<Reservation, String> Colnums;

    @FXML
    private TableColumn<Reservation, String> Coldater;

    @FXML
    private TableColumn<Reservation, String> Colnbh;
    
    @FXML
    private TableColumn<Reservation, String>Colid;

    @FXML
    private TableColumn<Reservation, Date> Coldatedeb;
    
    void afficherReservation(){
         ReservationCRUD Rc =new ReservationCRUD();
        ArrayList av;
         av=(ArrayList) Rc.afficherReservation(1);
        ObservableList o;
        o= FXCollections.observableArrayList(av);
        //Reservation r =(Reservation)av.get(0);
        Colnums.setCellValueFactory(new PropertyValueFactory <>("num_serie"));
       
       Coldater.setCellValueFactory(new PropertyValueFactory <>("date_res"));
       Colnbh.setCellValueFactory(new PropertyValueFactory <>("nbr_heure"));
       Coldatedeb.setCellValueFactory(new PropertyValueFactory <>("date_db_res"));
       Colid.setCellValueFactory(new PropertyValueFactory <>("id_res"));
       
       //ColButt.setCellValueFactory(new PropertyValueFactory <>("button"));
       TabAffResv.setItems(o);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherReservation();
    }    
    
}
