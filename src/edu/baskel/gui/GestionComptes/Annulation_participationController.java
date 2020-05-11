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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private TableColumn<Participation, String> colDate;

    @FXML
    private TableColumn<Participation, String> colLieu;

    @FXML
    private TableColumn<Participation, String> colDescription;

    @FXML
    private TableColumn<Participation, String> colImage;

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
        //   colImage.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getImage_e()));
        tableAffichage.setItems(obser);

    }

    @FXML
    void annulerParticipation(ActionEvent event) {

        ParticipationCrud Pc = new ParticipationCrud();
        System.out.println("22222" + tableAffichage.getSelectionModel().getSelectedItem().getId_e());
        if (validationSaisie.confrimSuppression("Information", "Voulez vous supprimer cette participation")) {
            if (Pc.supprimerParticipationP(tableAffichage.getSelectionModel().getSelectedItem()) == true)
            {    System.out.println("++++++OK oK");
                tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                validationSaisie.notifConfirm("ok", "Participation annulée");
                actualiser();
                System.out.println("ok--------------------");
            }
            else {
                    System.out.println("----------erreur");
                    }

        }
        
       
    }

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
        //   colImage.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getImage_e()));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        affichageEvenementP();

    }

}
