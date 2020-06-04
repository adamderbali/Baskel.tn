/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.services.EvenementCRUD;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class HistoriqueDesEvenementsController implements Initializable {

    @FXML
    private TableView<Evenement> tableAffichage;

    @FXML
    private TableColumn<Evenement, String> colLieuE;

    @FXML
    private TableColumn<Evenement, String> colDateE;

    @FXML
    private TableColumn<Evenement, String> colNomOrg;

    @FXML
    private TableColumn<Evenement, String> colAvis;
    @FXML
    private TableColumn<Evenement, String> colNomE;

    @FXML
    private JFXButton idRetour;

    @FXML
    private JFXTextField search;

    @FXML
    void retour(ActionEvent event) {

    }

    @FXML
    void searchBox(KeyEvent event) {
        EvenementCRUD ev = new EvenementCRUD();
     List<Evenement> partlst = ev.historiqueEvent();
        ObservableList obser;
        obser = FXCollections.observableArrayList(partlst);
          FilteredList<Evenement> filterData = new FilteredList<>(obser, p -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
             
                 if ((e.getMbre().getNom_u().toLowerCase()+" "+e.getMbre().getPrenom_u().toLowerCase()).indexOf(typedText) != -1) {

                    return true;
                }

              
                 if (e.getLieu_e().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (e.getDate_e().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;

            });

            SortedList<Evenement> sortedList = new SortedList<>(filterData);

            sortedList.comparatorProperty().bind(tableAffichage.comparatorProperty());
            tableAffichage.setItems(sortedList);

        });
    }

    public void affichageEventAvis() {

        EvenementCRUD ev = new EvenementCRUD();

        List<Evenement> partlst = ev.historiqueEvent();
        ObservableList obser;
        obser = FXCollections.observableArrayList(partlst);
        TableColumn<Evenement, String> c1 = new TableColumn<Evenement, String>("first");
        colLieuE.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDateE.setCellValueFactory(new PropertyValueFactory<>("date_e"));   
        colNomE.setCellValueFactory(new PropertyValueFactory<>("nom_e"));    
        colAvis.setCellValueFactory(new PropertyValueFactory<>("ra"));    
        colNomOrg.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getNom_u()+" "+ p.getValue().getMbre().getPrenom_u()));
        tableAffichage.setItems(obser);

        colNomOrg.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNomOrg.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    
        colLieuE.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colLieuE.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        colDateE.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDateE.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        
       
        colNomE.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNomE.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      affichageEventAvis();
    }

}
