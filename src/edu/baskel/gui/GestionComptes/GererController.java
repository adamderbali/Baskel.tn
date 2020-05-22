/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class GererController implements Initializable {

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
    private JFXButton fileChoose;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtLieu;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private ImageView img;

    @FXML
    private TextField pathE;

    @FXML
    private JFXButton valider;

    @FXML
    private JFXButton supprimer;
    Image image;

    @FXML
    private JFXButton modif;

    @FXML
    private ImageView imageV;
    
      @FXML
    private TableColumn<Evenement, String> colNombre;

    @FXML
    private JFXTextField search;

    ObservableList obser;

    public GererController() {

    }

    Evenement e;

    @FXML
    void chargerDonnee() {
        e = tableAffichage.getSelectionModel().getSelectedItem();

        /*   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         Image imgE;  
        if (e != null) {
            txtNom.setText(e.getNom_e());
          //  txtNom.setVisible(false);
            txtLieu.setText(e.getLieu_e());
         //   txtLieu.setVisible(false);
            txtDescription.setText(e.getDescription_e());
          //  txtDescription.setVisible(false);
            txtDate.setValue(LocalDate.parse(e.getDate_e(), formatter));
          //  txtDate.setVisible(false);
            pathE.setText(e.getImage_e());
           // pathE.setVisible(false);
             
            imgE = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e());
            img.setImage(imgE);
         //   img.setVisible(false);
            
             
             System.out.println(e);
             
   
        }*/
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
        arrayList = (ArrayList) Ec.displayByUser(7);

        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nbr_max_e"));
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

    public void actualiser() {
        EvenementCRUD Ec = new EvenementCRUD();
        Evenement e = new Evenement();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(7);

        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nbr_max_e"));
        tableAffichage.setItems(obser);

    }

    @FXML
    private void searchBox(KeyEvent event) {

        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(7);
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
            validationSaisie.notif("Evenement", "Vous devez selectionné un evenement à modifier");
        } else {
            ModifierController controller2 = new ModifierController(this);
            controller2.showStage();
        }

    }

    @FXML
    void supprimer(ActionEvent event) throws Exception {

        if ((tableAffichage.getSelectionModel().getSelectedItem() == null)) {
            validationSaisie.notif("Evenement", "Vous devez selectionné un evenement à supprimer");
        } else {
            SupprimerEventController controller3 = new SupprimerEventController(this);
            controller3.showStage();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiser();
        affichageEvenement();
    }

}
