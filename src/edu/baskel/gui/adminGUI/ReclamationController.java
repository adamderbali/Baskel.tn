/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Reclamation;
import edu.baskel.entities.Statistique;
import edu.baskel.services.ReclamationCRUD;
import edu.baskel.services.StatCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javafx.util.converter.DefaultStringConverter;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author Skander
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button bt_reception;
    @FXML
    private Button bt_recp_traitè;
    @FXML
    private Button bt_recp_non_traitè;
    @FXML
    private TableColumn<Reclamation, Integer> T_id_rec;
    @FXML
    private TableColumn<Reclamation, String> T_id_u;
    @FXML
    private TableColumn<Reclamation, String> T_desc;
    @FXML
    private TableColumn<Reclamation, String> T_id_ur;
    @FXML
    private TableColumn<Reclamation, String> T_valida;
    @FXML
    private TableColumn<Reclamation, String> t_etat;
    @FXML
    private VBox rec_vbox;
    @FXML
    private TableView<Reclamation> recla;
    @FXML
    private Label label_rec;
    @FXML
    private Pane pane2_rec;
   Reclamation recl;
   private ObservableList<String> Choix1;
   private ObservableList<String> Choix2;
    @FXML
    private Button bt_modif;
    @FXML
    private JFXTextField tf_valadition;
    @FXML
    private JFXTextField tf_etat;
    @FXML
    private AnchorPane Reception_admin;
    @FXML
    private JFXTextField tf_interface;


 
    /**
     * Initializes the controller class.
     */
   
    public void affichage_rec(){
        ReclamationCRUD Mc = new ReclamationCRUD();
        
        
        ArrayList arrayList2;
        arrayList2 = (ArrayList) Mc.displayall_Rec();
        
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList2);
        Choix1 = FXCollections.observableArrayList();
        Choix1.add("NEW");
        Choix1.add("OLD");
        Choix2 = FXCollections.observableArrayList();
        Choix2.add("Valide");
        Choix2.add("Non_Valide");
        T_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        T_id_u.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getReclamateur().getNom_u() + " " + p.getValue().getReclamateur().getPrenom_u()));
        T_desc.setCellValueFactory(new PropertyValueFactory<>("desc_r"));
        T_id_ur.setCellValueFactory((a) -> new ReadOnlyStringWrapper(a.getValue().getACCUSATEUR().getNom_u() + " " + a.getValue().getACCUSATEUR().getPrenom_u()));
        T_valida.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
        T_valida.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix2));
        T_valida.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event1){
                
                System.out.println("Value : "+event1.getNewValue());
                tf_valadition.setText(event1.getNewValue());
                

            }
        });
        t_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec2"));
        t_etat.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix1));
        t_etat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event){
                System.out.println("Value : "+event.getNewValue());
                tf_etat.setText(event.getNewValue());
                String value_v= event.getNewValue();
            }
        });
        recla.setItems(obser);
        /*******/
        
        recla.setEditable(true);
        
        

    }
    
     @FXML
    void chargerDonnee() {
        recl = recla.getSelectionModel().getSelectedItem();

        if (recl != null) {
            tf_valadition.setText(recl.getEtat_rec());
            tf_etat.setText(recl.getEtat_rec2());
            //T_id_u.setText(String.valueOf(recl.getId_u()));
            //T_id_ur.setText(String.valueOf(recl.getId_ur()));
            //T_valida.setText(recl.getEtat_rec());
            //t_etat.setText(recl.getEtat_rec2());
            
            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
    }
    public void affichage_rec_trait(){
        ReclamationCRUD Mc = new ReclamationCRUD();
        
        
        ArrayList arrayList2;
        arrayList2 = (ArrayList) Mc.Reclamation_traitè();
        
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList2);
        /*Choix1 = FXCollections.observableArrayList();
        Choix1.add("NEW");
        Choix1.add("OLD");
        Choix2 = FXCollections.observableArrayList();
        Choix2.add("Valide");
        Choix2.add("Non_Valide");*/
        T_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        T_id_u.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getReclamateur().getNom_u() + " " + p.getValue().getReclamateur().getPrenom_u()));
        T_desc.setCellValueFactory(new PropertyValueFactory<>("desc_r"));
        T_id_ur.setCellValueFactory((a) -> new ReadOnlyStringWrapper(a.getValue().getACCUSATEUR().getNom_u() + " " + a.getValue().getACCUSATEUR().getPrenom_u()));
        /*T_valida.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
        T_valida.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix2));
        T_valida.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event1){
                
                System.out.println("Value : "+event1.getNewValue());
                tf_valadition.setText(event1.getNewValue());
                

            }
        });
        t_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec2"));
        t_etat.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix1));
        t_etat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event){
                System.out.println("Value : "+event.getNewValue());
                tf_etat.setText(event.getNewValue());
                String value_v= event.getNewValue();
            }
        });*/
        recla.setItems(obser);
        /*******/
        
        recla.setEditable(true);
        
        

    }
    public void affichage_rec_non_trait(){
        ReclamationCRUD Mc = new ReclamationCRUD();
        
        
        ArrayList arrayList2;
        arrayList2 = (ArrayList) Mc.Reclamation_non_traitè();
        
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList2);
        /*Choix1 = FXCollections.observableArrayList();
        Choix1.add("NEW");
        Choix1.add("OLD");
        Choix2 = FXCollections.observableArrayList();
        Choix2.add("Valide");
        Choix2.add("Non_Valide");*/
        T_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        T_id_u.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getReclamateur().getNom_u() + " " + p.getValue().getReclamateur().getPrenom_u()));
        T_desc.setCellValueFactory(new PropertyValueFactory<>("desc_r"));
        T_id_ur.setCellValueFactory((a) -> new ReadOnlyStringWrapper(a.getValue().getACCUSATEUR().getNom_u() + " " + a.getValue().getACCUSATEUR().getPrenom_u()));
        /*T_valida.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
        T_valida.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix2));
        T_valida.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event1){
                
                System.out.println("Value : "+event1.getNewValue());
                tf_valadition.setText(event1.getNewValue());
                

            }
        });
        t_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec2"));
        t_etat.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix1));
        t_etat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event){
                System.out.println("Value : "+event.getNewValue());
                tf_etat.setText(event.getNewValue());
                String value_v= event.getNewValue();
            }
        });*/
        recla.setItems(obser);
        /*******/
        recla.setEditable(true);
    }
     @FXML
    private void boite_rec_fct(ActionEvent event) {
        affichage_rec();
    }

    @FXML
    private void rec_trait_fct(ActionEvent event) {
        affichage_rec_trait();
    }

    @FXML
    private void rec_non_trait_fct(ActionEvent event) {
        affichage_rec_non_trait();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichage_rec();
            StatCRUD sc  = new StatCRUD();
            sc.Stat_methode("Reclamation_admin", 13);
        } catch (SQLException ex) {
ex.printStackTrace();        }
       
        
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        Image image = new Image("/images/icons8-ok-128.png", true);
        ReclamationCRUD Rc = new ReclamationCRUD();
        if (recla.getSelectionModel().getSelectedItem() == null) {
            Notifications notificationBuilder = Notifications.create()
                                .text(" Pas de champs selectionée").title("Erreur").graphic(null).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Pas de champs selectionée");
                            }

                        });
                        notificationBuilder.showError();
        }
        else{
           Reclamation c = new Reclamation(tf_valadition.getText(),tf_etat.getText(),recla.getSelectionModel().getSelectedItem().getId_rec());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(" Vous voulez effecuter cette Modification ?");
                    alert.setContentText(" Appuyez vous sur OK si vous etes d'accord ");
                    Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK)
        {
            Rc.valider_admin(c);
        Notifications notificationBuilder2 = Notifications.create()
                                .text(" Votre Modification a été effectuée").title("Modification").graphic(new ImageView(image)).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Done ! ");
                            }

                        });
                        notificationBuilder2.show();
                        Rc.valider_admin(c);
        }
        if(result.get() == ButtonType.CANCEL){
            Notifications notificationBuilder3 = Notifications.create()
                                .text("  Votre Modification est annulée ").title("Modification").graphic(null).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Modification annulèe !");
                            }

                        });
                        notificationBuilder3.showInformation();
                    }
        }
    }
            
        }
    
                        
        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(" Vous voulez envoyer cette reclamation ?");
                    alert.setContentText(" Appuyez vous sur OK si vous etes d'accord ");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
        Rc.valider_admin(c);
        Notifications notificationBuilder = Notifications.create()
                                .text(" Votre Modification a été effectuée").title("Modification").graphic(new ImageView(image)).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Done ! ");
                            }

                        });
                        notificationBuilder.show();
                    if (result.get() == ButtonType.CANCEL){
                        Notifications notificationBuilder2 = Notifications.create()
                                .text("  Votre Modification est annulée ").title("Modification").graphic(null).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Modification annulèe !");
                            }

                        });
                        notificationBuilder2.showInformation();
                    }
                    }*/
    

   
    


