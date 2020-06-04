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
import java.awt.Checkbox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

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


    /*  @FXML
    private TableColumn<Participation, String> colAvis;*/
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
    ObservableList obser;
    @FXML
    private JFXButton avis;

    @FXML
    private JFXButton annuler;
    
     @FXML
    private JFXButton donnerAvis;

    public Annulation_participationController() {
    }
   

    public void affichageEvenementP() {

        ParticipationCrud parList = new ParticipationCrud();
        List<Participation> partListU = parList.displayByUser(9);

        obser = FXCollections.observableArrayList(partListU);
        TableColumn<Participation, String> c1 = new TableColumn<Participation, String>("first");
        //afficher le non du membre dnas la classe alerte
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getNom_e()));
        colLieu.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getLieu_e()));
        colDate.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDate_e()));
        colDescription.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDescription_e()));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        // colAction.setCellValueFactory(new PropertyValueFactory<>("chb"));
        colDatePar.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
        // colAvis.setCellValueFactory(new PropertyValueFactory<>("ra"));
         colDescription.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colNom.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colLieu.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colDate.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
      

        tableAffichage.setItems(obser);

        /*    tableAffichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent click) {
                if ((click.getClickCount() == 1)&&(!(tableAffichage.getSelectionModel().getSelectedItem().getChb().isSelected()))){

                    tableAffichage.getSelectionModel().getSelectedItem().getChb().setSelected(true);
                }
                else 
                 tableAffichage.getSelectionModel().getSelectedItem().getChb().setSelected(false);
            
              

            }

        });*/
    }

    @FXML
    void annulerParticipation(ActionEvent event) {

        ParticipationCrud parList = new ParticipationCrud();
        List<Participation> partListU = parList.displayByUser(9);
        Participation pe = new Participation();
        ObservableList<Participation> dataListRemove = FXCollections.observableArrayList();
        obser = FXCollections.observableArrayList(partListU);
        if (partListU.isEmpty()) {
            validationSaisie.notifInfo("Information", "Vous n'avez pas un histoqtique de participation");
            annuler.setDisable(true);
        } else {
            if (tableAffichage.getSelectionModel().getSelectedItem() == null) {
                validationSaisie.notifInfo("Erreur", "Vous devez selectionné une participation à supprimer");
            } else {
                if (validationSaisie.confrimSuppression("Information", "Voulez vous supprimer cette participation")) {
                    for (Participation p : partListU) {

                        if (p.getId_e() != 0) {
                            System.out.println("Marhbeeeeeeeeeeeeeeeeeeeeeeee");

                            dataListRemove.add(p);
                            System.out.println("sousou tahfouna" + p);

                        }

                        parList.supprimerParticipationP(p);
                        obser.removeAll(p);
                        System.out.println("ok--------------------");
                        actualiser();

                    }
                    validationSaisie.notifConfirm("ok", "Participation annulée");
                    actualiser();
                }
            }
            System.out.println("makouchit chay");
        }
    }

    private void actualiser() {

        ParticipationCrud parList = new ParticipationCrud();
        List<Participation> partListU = parList.displayByUser(9);
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
        List<Participation> partListU = parList.displayByUser(9);
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

      @FXML
    void donnerAvis(ActionEvent event) {
        
          //btnAjout.setDisable(true);
        AvisEvenementController controller2 = new AvisEvenementController(this);
        controller2.showStage();
        

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableAffichage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        affichageEvenementP();
        actualiser();

    }

}
