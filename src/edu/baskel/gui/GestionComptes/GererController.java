/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class GererController implements Initializable {

    Membre ml = SessionInfo.getLoggedM();
    @FXML
    private JFXButton detail;

    @FXML
    private TableView<Evenement> tableAffichage;

    @FXML
    private TableColumn<Evenement, String> colNom;

    @FXML
    private TableColumn<Evenement, String> colLieu;

    @FXML
    private TableColumn<Evenement, String> colDate;

    @FXML
    private TableColumn<Evenement, String> colDescription;

    @FXML
    private TableColumn<Evenement, String> colImage;
    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXButton valider;

    @FXML
    private JFXButton supprimer;
    Image image;

    @FXML
    private TableColumn<Evenement, String> colNombre;

    @FXML
    private JFXTextField search;

    ObservableList obser;
    @FXML
    private JFXCheckBox dateTrie;

    public GererController() {

    }

    Evenement e;

    @FXML
    void chargerDonnee() {
        e = tableAffichage.getSelectionModel().getSelectedItem();

    }

    public Evenement getClickedEvent() {
        return e;
    }

    public String getIdEvent() {
        return String.valueOf(e.getId_e());

    }

    public TableView<Evenement> getTableAffichage() {
        return tableAffichage;
    }

    public void affichageEvenement() {
        EvenementCRUD Ec = new EvenementCRUD();
        Evenement e = new Evenement();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(ml.getId_u());

        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nbr_max_e"));
        colNom.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNom.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        colLieu.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colLieu.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        colDate.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDate.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        
        
        colDate.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDate.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        colDescription.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

      
      
        tableAffichage.setItems(obser);
        /*  displayByUser(m.getId_u()*/

 /*   tableAffichage.setRowFactory( tv -> {
       TableRow<Evenement> row = new TableRow<>();
          row.setOnMouseClicked(event -> {
             if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Evenement rowData = row.getItem();
                //System.out.println("mdkgd"+rowData);
                 System.out.println(rowData.getId_e());
                Parent root;
                 //try {
                      
                     ModifierController controller2 = new ModifierController(this);
                     controller2.showStage();
   
            }
         });
          return row ;
         });*/
    }

    public void affichageEvenementParDate() {
        EvenementCRUD Ec = new EvenementCRUD();
        Evenement e = new Evenement();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUserTrierDate(ml.getId_u());

        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nbr_max_e"));
        tableAffichage.setItems(obser);

    }

    public void actualiser() {
        EvenementCRUD Ec = new EvenementCRUD();
        Evenement e = new Evenement();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(ml.getId_u());

        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nbr_max_e"));
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

        colDescription.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
   
        tableAffichage.setItems(obser);

    }

    @FXML
    private void searchBox(KeyEvent event) {

        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(ml.getId_u());
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

    @FXML
    void ValiderModif(ActionEvent event) {

        if ((tableAffichage.getSelectionModel().getSelectedItem() == null)) {
            validationSaisie.notif("Evenement", "Vous devez selectionner un evenement à modifier");
        } else {
            ModifierController controller2 = new ModifierController(this);
            controller2.showStage();
        }

    }

    @FXML
    void supprimer(ActionEvent event) throws Exception {

        if ((tableAffichage.getSelectionModel().getSelectedItem() == null)) {
            validationSaisie.notif("Evenement", "Vous devez selectionner un evenement à supprimer");
        } else {
            SupprimerEventController controller3 = new SupprimerEventController(this);
            controller3.showStage();
        }

    }

    @FXML
    void detailEvent(ActionEvent event) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("ListParticipationParEventUser.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.setTitle("Details de vos evenements");
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiser();
        affichageEvenement();
    }

    @FXML
    private void dateTrie(ActionEvent event) {

        if (dateTrie.isSelected()) {
            affichageEvenementParDate();
        }
        if (!(dateTrie.isSelected())) {
            affichageEvenement();
        }
    }

}
