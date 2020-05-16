/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Reservation;
import edu.baskel.services.ReservationCRUD;
import edu.baskel.services.VeloCRUD;
import edu.baskel.utils.PDFdoc;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    
     @FXML
    private Button butmodif;

    @FXML
    private Button butann;

    @FXML
    private Button back;
    
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
     Stage stage = (Stage) back.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    
    @FXML
    void annulerReservation(ActionEvent event) {
            if(TabAffResv.getSelectionModel().getSelectedItem() == null){
               Alert alertChamps = new validationSaisie().getAlert("Echec", "Veuillez selectionner une réservation");
            alertChamps.showAndWait();
           }else{
                if (validationSaisie.confrimSuppression("Information", "Voulez-vous vraiment supprimer cette réservation?")) {
               int id_res =TabAffResv.getSelectionModel().getSelectedItem().getId_res() ;
               ReservationCRUD rc = new ReservationCRUD();
                rc.annulerReservation(id_res);
                 Alert alertAdded = new validationSaisie().getAlert("Succés d'annulation", "Réservation annulée");
                alertAdded.showAndWait();
                this.afficherReservation();
                }
           }
    }

    @FXML
    void modifierReservation(ActionEvent event) {
           if(TabAffResv.getSelectionModel().getSelectedItem() == null){
               Alert alertChamps = new validationSaisie().getAlert("Echec", "Veuillez selectionner une réservation");
            alertChamps.showAndWait();
           }else{
               ModifierReservationController controller2 = new ModifierReservationController(this);
                     controller2.showStage();
           }
    }
    
    public Reservation getReservation(){
        return TabAffResv.getSelectionModel().getSelectedItem();
    }
    void afficherReservation(){
         ReservationCRUD Rc =new ReservationCRUD();
        ArrayList av;
         av=(ArrayList) Rc.afficherReservation(1);
        ObservableList o;
        o= FXCollections.observableArrayList(av);
        //Reservation r =(Reservation)av.get(0);
        System.out.println("res"+av.toString());
        System.out.println("res"+listToString(av));
        PDFdoc.PDFwriter(listToString(av));
        System.out.println("55"+o);
        Colnums.setCellValueFactory(new PropertyValueFactory <>("num_serie"));
       
       Coldater.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date_res"));
      // Coldater.setCellValueFactory(new PropertyValueFactory <>("date_res"));
       Colnbh.setCellValueFactory(new PropertyValueFactory <>("nbr_heure"));
       Coldatedeb.setCellValueFactory(new PropertyValueFactory <>("date_db_res"));
       Colid.setCellValueFactory(new PropertyValueFactory <>("id_res"));
       
       //ColButt.setCellValueFactory(new PropertyValueFactory <>("button"));
       TabAffResv.setItems(o);
    }
    /**
     * Initializes the controller class.
     */
    public static String listToString(ArrayList o) {
    String result = "";
    for (int i = 0; i < o.size(); i++) {
        result += "\n\n" + o.get(i);
    }
    return result;
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherReservation();
    }    
    
}
