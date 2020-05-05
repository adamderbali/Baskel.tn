/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reclamation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReclamationCRUD;
import edu.baskel.utils.SessionInfo;
import static java.awt.SystemColor.text;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Affichage_userController implements Initializable {

    @FXML
    private AnchorPane pane_user;
    @FXML
    private TableView<Membre> table_user;
    @FXML
    private TableColumn<Membre, String> Nom_user;
    @FXML
    private TableColumn<Membre, String> prenom_user;
    @FXML
    private TableColumn<Membre, String> email_user;
    @FXML
    private TextField tf_nom_user;
    @FXML
    private TextField tf_prenom_user;
    @FXML
    private TextField tf_email_user;
    @FXML
    private TextField tf_adresse_user;
    @FXML
    private Button btn_rec;
    @FXML
    private Label tf_titre_rec;
    @FXML
    private Label label_aqui;
    @FXML
    private Label label_objet;
    @FXML
    private JFXTextField tf_objet_rec;
    @FXML
    private Label label_desc_rec;
    @FXML
    private JFXTextArea textarea_rec;
    @FXML
    private Label text;
    
    

    /**
     * Initializes the controller class.
     */
    
    
    public void affichage_user(){
        MembreCRUD Mc = new MembreCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Mc.getlistMembre();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        
       Nom_user.setCellValueFactory(new PropertyValueFactory <>("nom_u"));
       prenom_user.setCellValueFactory(new PropertyValueFactory <>("prenom_u"));
       email_user.setCellValueFactory(new PropertyValueFactory <>("email_u"));
       //colDescription.setCellValueFactory(new PropertyValueFactory <>("description_e"));
       //colImage.setCellValueFactory(new PropertyValueFactory <>("image_e"));
       table_user.setItems(obser);
    }
     Membre user; 
     Reclamation reclam;

    @FXML
    void chargerDonnee() {
user = table_user.getSelectionModel().getSelectedItem();
      
        if (user!=null){
            tf_nom_user.setText(user.getNom_u());
            tf_prenom_user.setText(user.getPrenom_u());
            tf_email_user.setText(user.getEmail_u());
            tf_adresse_user.setText(user.getAdresse_u());
            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
    }
    @FXML
    public void send_reclamation(ActionEvent event) throws SQLException {
            //int id_us = SessionInfo.iduser;
            //Membre reclamer;
            Image image = new Image("/images/icons8-ok-128.png",true);
            ReclamationCRUD s_rec =new ReclamationCRUD();
            //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //Reclamation reclamation2 = new Reclamation(2,this.reclam.getId_ur());
            
            //s_rec.BaneExist(2,user.getId_u());
            /*if(s_rec.banexist(2,this.reclam.getId_ur())==true)
            {
                System.out.println("hani lenna");
                Notifications notificationBuilder2 = Notifications.create()
                    .text("votre reclamation deja existe").title("Reclamation").graphic(null).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            System.out.println("Reclamation annulèe ! ");
                        }
                        
                    });
            notificationBuilder2.showError();
            }*/
            if(s_rec.banexist(2,user.getId_u())==false){
            
            Reclamation reclamation=  new Reclamation( textarea_rec.getText(),tf_objet_rec.getText(),2,user.getId_u());
            s_rec.ajouterReclamation(reclamation); 
            MembreCRUD s_mem = new MembreCRUD();
            Membre mem = new Membre(user.getId_u(),user.getNbr_ban_u());
            s_mem.ajouterban(user.getId_u());
            text.setText("");
            Notifications notificationBuilder = Notifications.create()
                    .text("Votre Reclamation a été envoyer avec succés").title("Reclamation").graphic(new ImageView(image)).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            System.out.println("Done ! ");
                        }
                        
                    });
            notificationBuilder.show();
            } 
            else{
                Notifications notificationBuilder2 = Notifications.create()
                    .text("votre reclamation deja existe").title("Reclamation").graphic(null).hideAfter(Duration.seconds(6)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            System.out.println("Reclamation annulèe ! ");
                        }
                        
                    });
            notificationBuilder2.showError();
            }}

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage_user();
    }  
}

