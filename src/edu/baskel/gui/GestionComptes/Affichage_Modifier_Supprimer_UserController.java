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
import edu.baskel.entities.Membre;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class Affichage_Modifier_Supprimer_UserController implements Initializable {

   @FXML
    private TableView<Evenement> tableAffichage;

    @FXML
    private TableColumn<Evenement,String> colNom;

    @FXML
    private TableColumn<Evenement,String> colLieu;

    @FXML
    private TableColumn<Evenement,String> colDate;

    @FXML
    private TableColumn<Evenement,String> colDescription;

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
    private JFXTextField filter;


  ObservableList observableList;
    Membre m =SessionInfo.getLoggedM();
    
    public Affichage_Modifier_Supprimer_UserController(){
        ConnectionBD mc = ConnectionBD.getInstance();
        
    }
    
     public void affichageEvenement()
    {
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayByUser(m.getId_u());
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
       
       colNom.setCellValueFactory(new PropertyValueFactory <>("nom_e"));
       colLieu.setCellValueFactory(new PropertyValueFactory <>("lieu_e"));
       colDate.setCellValueFactory(new PropertyValueFactory <>("date_e"));
       colDescription.setCellValueFactory(new PropertyValueFactory <>("description_e"));
       colImage.setCellValueFactory(new PropertyValueFactory <>("image_e"));
       tableAffichage.setItems(obser);
     /*  displayByUser(m.getId_u()*/
    }
 
   Evenement event; 
         @FXML
    void chargerDonnee() {
             
        event = tableAffichage.getSelectionModel().getSelectedItem();
      
        if (event!=null){
            txtNom.setText(event.getNom_e());
            txtLieu.setText(event.getLieu_e());
            txtDescription.setText(event.getDescription_e());
            pathE.setText(event.getImage_e());
           
        }
      
    }
    
    @FXML
    void ValiderModif(ActionEvent event) {
        
        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        String date = txtDate.getEditor().getText();
        
        if (((txtNom.getText().isEmpty()) | (txtLieu.getText().isEmpty()) | (txtDate.getEditor().getText().isEmpty()) | (txtDescription.getText().isEmpty())))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur de modification");
            alert.setContentText("Les champs doivent etre tout remplis svp");
            alert.show();
        } 
        
        else if (txtDate.getEditor().getText().compareTo(date_system)<0) {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);  
            alert2.setTitle("Date erronée");
            alert2.setContentText("La date saisie doit etre superieur à"+date_system);
            alert2.show();
        }
        
        else{
            
        EvenementCRUD Ec = new EvenementCRUD();
    /*  java.util.Date date_util = new java.util.Date();
        java.sql.Date date = new java.sql.Date(date_util.getTime());*/
        Evenement e = new Evenement(tableAffichage.getSelectionModel().getSelectedItem().getId_e(),
                txtNom.getText(),txtLieu.getText(),txtDate.getEditor().getText(),txtDescription.getText(),pathE.getText()
                );
       Ec.updateEvenement(e);
       
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Modification ok");
            alert1.setContentText("Evenement modifié");
           
            alert1.show();
     actualiser();
     
       txtNom.clear();
       txtLieu.clear();
       txtDate.setValue(null);
       txtDescription.clear();
       pathE.clear();
        }
      
    }
    
   private void actualiser() {
       
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayAllList();
      
       observableList = FXCollections.observableArrayList(arrayList);
       tableAffichage.setItems(observableList);
       colNom.setCellValueFactory(new PropertyValueFactory <>("nom_e"));
       colLieu.setCellValueFactory(new PropertyValueFactory <>("lieu_e"));
       colDate.setCellValueFactory(new PropertyValueFactory <>("date_e"));
       colDescription.setCellValueFactory(new PropertyValueFactory <>("description_e"));
       colImage.setCellValueFactory(new PropertyValueFactory <>("image_e"));
      
       

    }
      
   

      
        @FXML
    void supprimer(ActionEvent event) {
        
        EvenementCRUD Ec = new EvenementCRUD();
        System.out.println("22222"+tableAffichage.getSelectionModel().getSelectedItem().getId_e());
        Ec.supprimerEvenement(tableAffichage.getSelectionModel().getSelectedItem());
      tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
      
       Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Supression ok");
            alert1.setContentText("Evenement supprimé");
           
            alert1.show();
       actualiser();
       
       txtNom.clear();
       txtLieu.clear();
       txtDate.setValue(null);
       txtDescription.clear();
       pathE.clear();
      

    }
    

 
    @FXML
    void telechargerPhoto(ActionEvent event) {
        
          FileChooser filechooser = new FileChooser();
                
        filechooser.setTitle("Open file dialog");
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = filechooser.showOpenDialog(stage);
        
        if(file!=null){
            pathE.setText(file.getAbsolutePath());
           System.out.println(""+file.getAbsolutePath());

            image = new Image(file.getAbsoluteFile().toURI().toString(),img.getFitWidth(),img.getFitHeight(),true,true);
            img.setImage(image);
            img.setPreserveRatio(true);
        }  
        

    }
    
    
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageEvenement();
         FileChooser filechooser = new FileChooser();
        
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Allfiles","*.*"),
           new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.gif"),
           new FileChooser.ExtensionFilter("Text File","*.txt"));
        
        
       
        
    }    
    
}
