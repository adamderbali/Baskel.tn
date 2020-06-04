/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.Node;
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

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class ListParticipationParEventUserController implements Initializable {
   Membre ml = SessionInfo.getLoggedM();
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<Evenement> tableAffichage;
  /*  @FXML
    private TableColumn<Participation, String> colNom;

    @FXML
    private TableColumn<Participation, String> colPrenom;

    @FXML
    private TableColumn<Participation, String> colEmail;*/
    
    
    @FXML
    private JFXCheckBox listeP;

    @FXML
    private JFXCheckBox listeE;


    @FXML
    private TableColumn<Evenement, String> colNomE;

    @FXML
    private TableColumn<Evenement, String> colLieuE;

    @FXML
    private TableColumn<Evenement, String> colDateE;
      @FXML
    private TableColumn<Evenement, String> colPourcentage;
      
        @FXML
    private JFXButton idRetour;

   

    public void affichageParticip() {

      //  ParticipationCrud Pc = new ParticipationCrud();
        EvenementCRUD ev = new EvenementCRUD();
     //   List<Evenement> partlst = Pc.displayParticipant(7);
     List<Evenement> partlst = ev.displayParticipant(ml.getId_u());
        ObservableList obser;
        obser = FXCollections.observableArrayList(partlst);
      //  TableColumn<Evenement, String> c1 = new TableColumn<Evenement, String>("first");
        colNomE.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieuE.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDateE.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colPourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
        /*colNomE.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getNom_e()));
        colLieuE.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getLieu_e()));
        colDateE.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDate_e()));*/
        tableAffichage.setItems(obser);
        
        colNomE.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNomE.widthProperty());
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

        tableAffichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
             
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2){
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("detailsParticipant.fxml"));

                try {

                    Loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ListParticipationParEventUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                DetailsParticipantController detailParticipantController = Loader.getController();

                detailParticipantController.affichageParticip(tableAffichage.getSelectionModel().getSelectedItem().getId_e());

                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
                }
            }

        });

    }
    
     @FXML
    void retour(ActionEvent event) {
        
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Gerer.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
    
    
    @FXML
    void searchBox(KeyEvent event) {
        EvenementCRUD ev = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) ev.displayParticipant(ml.getId_u());
       
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
         
        FilteredList<Evenement> filterData = new FilteredList<>(obser, p -> true);
       
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (e.getNom_e().toLowerCase().indexOf(typedText) != -1) {

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageParticip();

        // TODO
    }

}
