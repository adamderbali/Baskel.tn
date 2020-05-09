/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import edu.baskel.gui.GestionComptes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.services.SendMail;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class ListUpdDelByUserController implements Initializable {

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
    private JFXTextField search;

   
    ObservableList obser;
    Membre m = SessionInfo.getLoggedM();

    public ListUpdDelByUserController() {
        ConnectionBD mc = ConnectionBD.getInstance();

    }

    public void affichageEvenement() {
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(7);
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        tableAffichage.setItems(obser);
        /*  displayByUser(m.getId_u()*/
    }

    Evenement event;

    @FXML
    void chargerDonnee() {

        event = tableAffichage.getSelectionModel().getSelectedItem();

        if (event != null) {
            txtNom.setText(event.getNom_e());
            txtLieu.setText(event.getLieu_e());
            txtDescription.setText(event.getDescription_e());
            //  txtDate
         //     pathE.setText(event.getImage_e());
            
                img.setImage(image);
                img.setPreserveRatio(true);

            }

        }
    
      @FXML
    private void searchBox(KeyEvent event) {
      
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(7);
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        FilteredList<Evenement> filterData = new FilteredList<>(obser, p -> true);
          search.textProperty().addListener((observable,oldValue,newValue) -> {
              filterData.setPredicate(e ->{
                  
                  if (newValue == null || newValue.isEmpty()){
                      return true;
                  }
                  String typedText = newValue.toLowerCase();
              if   (e.getNom_e().toLowerCase().indexOf(typedText)!= -1) {
                  
                  return true;
              }
                          
                     if   (e.getLieu_e().toLowerCase().indexOf(typedText)!= -1) {
                  
                  return true;
              }     
                    if   (e.getDate_e().toLowerCase().indexOf(typedText)!= -1) {
                  
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
        void ValiderModif(ActionEvent event){

        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String date = txtDate.getEditor().getText();
            /* test sur les champs vides ou non*/

            if (((txtNom.getText().isEmpty()) | (txtLieu.getText().isEmpty()) | (txtDate.getEditor().getText().isEmpty()) | (txtDescription.getText().isEmpty()))) {
                Alert alertChamps = new validationSaisie().getAlert("Verifier la saisie", "Les champs doit etre tout saisie");
                alertChamps.showAndWait();
                /* test sur les dates*/
            } else if ((validationSaisie.validDate(txtDate.getEditor().getText())) == true) {

                System.out.println("------------------");
                Alert alertDtae = new validationSaisie().getAlert("date", "La date saisie doit etre superieur à" + date_system);
                alertDtae.showAndWait();
            } else {

                EvenementCRUD Ec = new EvenementCRUD();
                Evenement e = new Evenement(tableAffichage.getSelectionModel().getSelectedItem().getId_e(),
                        txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText()
                );
                Ec.updateEvenement(e);
                Alert alertAdded = new validationSaisie().getAlert("Modification ok", "Evenement Modifié");
                alertAdded.showAndWait();
                actualiser();

                txtNom.clear();
                txtLieu.clear();
                txtDate.setValue(null);
                txtDescription.clear();
                pathE.clear();
                img.setVisible(false);
            }

        
    }
    
    
    private void actualiser() {

        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(7);

        obser = FXCollections.observableArrayList(arrayList);
        tableAffichage.setItems(obser);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));

    }

    @FXML
    void supprimer(ActionEvent event) throws Exception {
        int i;
        ParticipationCrud Pc = new ParticipationCrud();
        EvenementCRUD Ec = new EvenementCRUD();
      
        System.out.println("22222" + tableAffichage.getSelectionModel().getSelectedItem().getId_e());
        if(validationSaisie.confrimSuppression()){
          
        Pc.eventAnnuler(tableAffichage.getSelectionModel().getSelectedItem().getId_e());
        Ec.supprimerEvenement(tableAffichage.getSelectionModel().getSelectedItem());
        Pc.supprimerParticipationE(tableAffichage.getSelectionModel().getSelectedItem().getId_e());
        tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
        
        Alert alertDelete = new validationSaisie().getAlert("Suppression ok", "Evenement supprimé");
        alertDelete.showAndWait();
        actualiser();     

        txtNom.clear();
        txtLieu.clear();
        txtDate.setValue(null);
        txtDescription.clear();
        pathE.clear();
        }
    }

    @FXML
    void telechargerPhoto(ActionEvent event) {

        FileChooser filechooser = new FileChooser();

        filechooser.setTitle("Open file dialog");
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = filechooser.showOpenDialog(stage);

        if (file != null) {
            pathE.setText(file.getAbsolutePath());
            System.out.println("" + file.getAbsolutePath());

            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);
            img.setImage(image);
            img.setPreserveRatio(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageEvenement();
        FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Allfiles", "*.*"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"));

    }

}
