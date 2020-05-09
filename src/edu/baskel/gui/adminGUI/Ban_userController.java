/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Reclamation;
import edu.baskel.services.ReclamationCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Ban_userController implements Initializable {

    @FXML
    private TableView<Membre> table_user_ban;
    @FXML
    private TableColumn<Membre, Integer> id_user_ban;
    @FXML
    private TableColumn<Membre, String> Nom_user_ban;
    @FXML
    private TableColumn<Membre, String> Prenom_user_ban;
    @FXML
    private TableColumn<Membre, Integer> Nbr_bann_user;
    @FXML
    private TextField tf_nom_user_ban;
    @FXML
    private TextField tf_prenom_user_ban;
    @FXML
    private TextField tf_id_user_ban;
    @FXML
    private TextField tf_nbr_user_ban;
    @FXML
    private Button banner;
    @FXML
    private Button desactiver_banner;
    @FXML
    private ImageView Image_table_ban;
    Membre user ;
    /**
     * Initializes the controller class.
     */
    public void afficherMembre_ban(){
        ReclamationCRUD rc = new ReclamationCRUD();
        ArrayList arrayList1;
        arrayList1 = (ArrayList) rc.getlistMembre_ban();
        ObservableList obser1;
        obser1 = FXCollections.observableArrayList(arrayList1);
       
       id_user_ban.setCellValueFactory(new PropertyValueFactory <>("id_u"));
       Nom_user_ban.setCellValueFactory(new PropertyValueFactory <>("nom_u"));
       Prenom_user_ban.setCellValueFactory(new PropertyValueFactory <>("prenom_u"));
       Nbr_bann_user.setCellValueFactory(new PropertyValueFactory <>("nbr_ban_u"));
       //colDescription.setCellValueFactory(new PropertyValueFactory <>("description_e"));
       //colImage.setCellValueFactory(new PropertyValueFactory <>("image_e"));
       table_user_ban.setItems(obser1);
    }
    
    
    @FXML
    void chargerDonnee_ban() {
    user = table_user_ban.getSelectionModel().getSelectedItem();
      
        if (user!=null){
            tf_nom_user_ban.setText(user.getNom_u());
            tf_prenom_user_ban.setText(user.getPrenom_u());
            tf_nbr_user_ban.setText(String.valueOf(user.getNbr_rec_u()));
            tf_id_user_ban.setText(String.valueOf(user.getId_u()));
            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
    }
    @FXML
    public void bannerUser(ActionEvent event){
        ReclamationCRUD s_rec =new ReclamationCRUD();
        Membre m = new Membre(table_user_ban.getSelectionModel().getSelectedItem().getId_u());
        Reclamation reclamation=  new Reclamation();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Vous voulez Banner ce membre");
        alert.setContentText("Si vous avez d'accord appuez sur OK");
        Optional<ButtonType> result = alert.showAndWait();
              if (result.get() == ButtonType.OK){
                  s_rec.bannerUtilisateur(m);
        Notifications notificationBuilder = Notifications.create()
                    .text("Opération Ban est bien effecutèe ").title("Banner").graphic(null).hideAfter(Duration.seconds(4)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {    
                            System.out.println("Done ! ");
                        }
                    });
            notificationBuilder.showConfirm();
            }   
              if(result.get() == ButtonType.CANCEL) 
              {
                  Notifications notificationBuilder = Notifications.create()
                    .text("Opération Ban est annulée").title("Banner").graphic(null).hideAfter(Duration.seconds(4)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Done ! ");
                        }
                        
                    });
            notificationBuilder.showInformation();
              }
              }
                  
                    
            
        
    @FXML
    public void DisbannerUser(ActionEvent event){
        ReclamationCRUD s_rec =new ReclamationCRUD();
        Membre m = new Membre(table_user_ban.getSelectionModel().getSelectedItem().getId_u());
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Vous voulez Banner ce membre");
        alert.setContentText("Si vous avez d'accord appuez sur OK");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            s_rec.desactiverbannerUtilisateur(m);
             Notifications notificationBuilder = Notifications.create()
                    .text("Désactivation Ban était bien effectuée").title("Banner").graphic(null).hideAfter(Duration.seconds(4)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Done ! ");
                        }    
                    });
            notificationBuilder.showConfirm();
    }
        if (result.get() == ButtonType.CANCEL)
        {
            Notifications notificationBuilder = Notifications.create()
                    .text("Desactivation de ban est annulée").title("Banner").graphic(null).hideAfter(Duration.seconds(4)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Done ! ");
                        }    
                    });
        }
        
        
    }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherMembre_ban();
    }    
    
}
