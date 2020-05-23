/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.utils.validationSaisie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    private TableColumn<Participation, String> colDate;

    @FXML
    private TableColumn<Participation, String> colLieu;

    @FXML
    private TableColumn<Participation, String> colDescription;

    @FXML
    private TableColumn<Participation, String> colImage;
     @FXML
    private TableColumn<Participation, String> colDatePar;
      @FXML
    private TextField search;
    

    @FXML
    private JFXButton participer;

    public Annulation_participationController() {
    }

    public void affichageEvenementP() {

        ParticipationCrud parList = new ParticipationCrud();
        List<Participation> partListU = parList.displayByUser(7);
        ObservableList obser;
        obser = FXCollections.observableArrayList(partListU);
        TableColumn<Participation, String> c1 = new TableColumn<Participation, String>("first");
        //afficher le non du membre dnas la classe alerte
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getNom_e()));
        colLieu.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getLieu_e()));
        colDate.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDate_e()));
        colDescription.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDescription_e()));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colDatePar.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
        tableAffichage.setItems(obser);

    }

    @FXML
    void annulerParticipation(ActionEvent event) {
        Evenement e = new Evenement();
        EvenementCRUD ev = new EvenementCRUD();
               
        ParticipationCrud Pc = new ParticipationCrud();
    //    System.out.println("22222" + tableAffichage.getSelectionModel().getSelectedItem().getId_e());
          if (tableAffichage.getSelectionModel().getSelectedItem() == null) {
            validationSaisie.notif("Participation", "Vous devez selectionner un evenement");}
          else{
        if (validationSaisie.confrimSuppression("Information", "Voulez vous supprimer cette participation")) {
            if (Pc.supprimerParticipationP(tableAffichage.getSelectionModel().getSelectedItem()) == true)
            {   
              if(ev.verifierNbrMaxE(tableAffichage.getSelectionModel().getSelectedItem().getId_e())==true)  {
                System.out.println("++++++OK oK");
                tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                validationSaisie.notifConfirm("ok", "Participation annulée");
                System.out.println("ok--------------------");
                actualiser();
            }
              else if(ev.verifierNbrMaxE(tableAffichage.getSelectionModel().getSelectedItem().getId_e())==false){
                 ev.nbrParticipantDelete(tableAffichage.getSelectionModel().getSelectedItem().getId_e());
                System.out.println("++++++OK oK");
                tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                validationSaisie.notifConfirm("ok", "Participation annulée");
                System.out.println("ok--------------------");
                actualiser();
              }
              actualiser();
        }
                    
              else {
                    System.out.println("----------erreur");
                    }
            
          }
       
    }}

    private void actualiser() {
        
        ParticipationCrud parList = new ParticipationCrud();
        List<Participation> partListU = parList.displayByUser(7);
        ObservableList obser;
        obser = FXCollections.observableArrayList(partListU);
        TableColumn<Participation, String> c1 = new TableColumn<Participation, String>("first");
        //afficher le non du membre dnas la classe alerte
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getNom_e()));
        colLieu.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getLieu_e()));
        colDate.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDate_e()));
        colDescription.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDescription_e()));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colDatePar.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
        tableAffichage.setItems(obser);

    }
    
    @FXML
    private void searchBox(KeyEvent event) {
        ParticipationCrud parList = new ParticipationCrud();
        List<Participation> partListU = parList.displayByUser(7);
        ObservableList obser;
        obser = FXCollections.observableArrayList(partListU);
        FilteredList<Participation> filterData = new FilteredList<>(obser, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (e.getEvent().getNom_e().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                if (e.getEvent().getDate_e().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (e.getEvent().getLieu_e().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;

            });

            SortedList<Participation> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableAffichage.comparatorProperty());
            tableAffichage.setItems(sortedList);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        affichageEvenementP();
        actualiser();

    }

}
