/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.Admin;

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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.baskel.services.EnvoiMail;
import edu.baskel.services.StatCRUD;
import edu.baskel.utils.InputValidation;
import static edu.baskel.utils.SmsTwillo.ACCOUNT_SID;
import static edu.baskel.utils.SmsTwillo.AUTH_TOKEN;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Affichage_userController implements Initializable {

    @FXML
    private TableView<Membre> table_user;
    @FXML
    private TableColumn<Membre, String> Nom_user;
    @FXML
    private TableColumn<Membre, String> prenom_user;
    @FXML
    private TableColumn<Membre, String> email_user;
    @FXML
    private JFXTextField tf_nom_user;
    @FXML
    private JFXTextField tf_email_user;
    @FXML
    private JFXTextField tf_adresse_user;
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

    ObservableList obser;
    @FXML
    private TextField search_user;
    @FXML
    private AnchorPane Reclamation_user;
    Membre ml = SessionInfo.getLoggedM();
    
    @FXML
    private ImageView retour;
    /**
     * Initializes the controller class.
     */
    public void affichage_user() {
        ReclamationCRUD Mc = new ReclamationCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Mc.listUser();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);

        Nom_user.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
        prenom_user.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
        email_user.setCellValueFactory(new PropertyValueFactory<>("email_u"));
        //colDescription.setCellValueFactory(new PropertyValueFactory <>("description_e"));
        //colImage.setCellValueFactory(new PropertyValueFactory <>("image_e"));
        table_user.setItems(obser);
    }
    Membre user;
    Reclamation reclam;

    @FXML
    void chargerDonnee() {
        user = table_user.getSelectionModel().getSelectedItem();

        if (user != null) {
            tf_nom_user.setText(user.getNom_u()+" "+user.getPrenom_u());
            //tf_prenom_user.setText(user.getPrenom_u());
            tf_email_user.setText(user.getEmail_u());
            tf_adresse_user.setText(user.getAdresse_u());
            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
    }

    @FXML
    public void send_reclamation(ActionEvent event) throws SQLException {

        Image image = new Image("/images/icons8-ok-128.png", true);
        ReclamationCRUD s_rec = new ReclamationCRUD();
        if (table_user.getSelectionModel().getSelectedItem() == null) {
            Notifications notificationBuilder = Notifications.create()
                                .text(" Pas de champs selectionée").title("Erreur").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Pas de champs selectionée");
                            }

                        });
                        notificationBuilder.showError();
        }
        else{
            if((textarea_rec.getText().isEmpty())&&(tf_objet_rec.getText().isEmpty()) )
            {
                 Alert alertDesc = new InputValidation().getAlert("Description", "Saisissez Les deux champs Description et l'objet de Reclamation ");
            alertDesc.showAndWait();
            }
        if (textarea_rec.getText().isEmpty()) {
            Alert alertDesc = new InputValidation().getAlert("Description", "Saisissez votre Description de Reclamation ");
            alertDesc.showAndWait();
        } else if (tf_objet_rec.getText().isEmpty()) {
                Alert alertObjet = new InputValidation().getAlert("Objet", "Saisissez votre Objet de Reclamation ");
                alertObjet.showAndWait();
            } else {
                if (s_rec.banexist(ml.getId_u(), user.getId_u()) == false) {
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    DialogPane pane = alert.getDialogPane();
                    pane.setPrefHeight(150.0);
                    alert.setWidth(pane.getWidth());
                    Rectangle2D bounds = Screen.getPrimary().getBounds();
                    alert.setX(bounds.getMaxX() - alert.getWidth());
                    alert.setY(bounds.getMaxY() - pane.getPrefHeight());
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(" Voulez-Vous envoyer cette reclamation ?");
                    alert.setContentText(" Appuyez vous sur OK si vous etes d'accord ");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        Reclamation reclamation = new Reclamation(textarea_rec.getText(), tf_objet_rec.getText(), ml.getId_u(), user.getId_u());
                        s_rec.ajouterReclamation(reclamation);
                        MembreCRUD s_mem = new MembreCRUD();
                        Membre mem = new Membre(user.getId_u(), user.getNbr_rec_u());
                        s_mem.ajouterban(user.getId_u());
                        EnvoiMail em = new EnvoiMail();
                        
                        em.MailReclamationAdmin(ml.getNom_u()+" "+ml.getPrenom_u(),tf_objet_rec.getText());
                        Notifications notificationBuilder = Notifications.create()
                                .text(" Votre Reclamation a été envoyer avec succés").title("Reclamation").graphic(new ImageView(image)).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Done ! ");
                            }

                        });
                        notificationBuilder.show();
                        

                    }
                    if (result.get() == ButtonType.CANCEL) {
                        Notifications notificationBuilder2 = Notifications.create()
                                .text("  Votre réclamation est  annulée ").title("Reclamation").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                System.out.println("Reclamation annulèe !");
                            }

                        });
                        notificationBuilder2.showError();
                    }

                } 
                else {
                    Image image2 = new Image("/images/iconfinder_Error_381599.png", true);
                    Notifications notificationBuilder3 = Notifications.create()
                            .text("Votre Reclamation deja existe ").title("Reclamation").graphic(new ImageView(image2)).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            System.out.println("X !");
                        }

                    });
                    notificationBuilder3.show();
                }
            }
        }  
    }

    @FXML
    private void searchBox(KeyEvent k) {
        ReclamationCRUD Mc = new ReclamationCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Mc.listUser();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);

        FilteredList<Membre> filterData = new FilteredList<>(obser, p -> true);
        search_user.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(x -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (x.getNom_u().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                if (x.getPrenom_u().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                return false;

            });

            SortedList<Membre> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(table_user.comparatorProperty());
            table_user.setItems(sortedList);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichage_user();
            StatCRUD sc  = new StatCRUD();
            sc.Stat_methode("Affichage_user", ml.getId_u());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     @FXML
    private void Goback(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("../GestionComptes/Acceuil.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Acceuil");
        app_stage.show();
    }

}
