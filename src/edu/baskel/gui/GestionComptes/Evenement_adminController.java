/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import static edu.baskel.services.ParticipationCrud.cnx;
import edu.baskel.services.ReclamationCRUD;
import edu.baskel.utils.ConnectionBD;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Evenement_adminController implements Initializable {

    @FXML
    private AnchorPane event_admin;
    @FXML
    private TableView<Evenement> tableAffichage;
    @FXML
    private TableColumn<Evenement, String> colNom;
    @FXML
    private TableColumn<Evenement, String> colLieu;
    private TableColumn<Evenement, Date> colDate;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TableColumn<Evenement, Integer> colID;

    @FXML
    private TableColumn<Evenement, String> colDesc;
    Evenement e = new Evenement();
    @FXML
    private JFXTextField tf_ide;
    Connection cnxs;
    @FXML
    private ImageView retour;
    @FXML
    private TableColumn<?, ?> ColImage;

    public void conxstat() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageEvent();

    }

    @FXML
    void chargerDonnee() {
        e = tableAffichage.getSelectionModel().getSelectedItem();

        if (e != null) {
            tf_ide.setText(String.valueOf(e.getId_e()));
            //T_id_u.setText(String.valueOf(recl.getId_u()));
            //T_id_ur.setText(String.valueOf(recl.getId_ur()));
            //T_valida.setText(recl.getEtat_rec());
            //t_etat.setText(recl.getEtat_rec2());

            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
    }

    public void affichageEvent() {
        Evenement ev = new Evenement();
        EvenementCRUD Ec = new EvenementCRUD();
        ReclamationCRUD RC = new ReclamationCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) RC.EventAllListAdmin();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        colID.setCellValueFactory(new PropertyValueFactory<>("id_e"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description_e"));

        tableAffichage.setItems(obser);
        tableAffichage.setEditable(true);
        System.out.println(tf_ide.getText());

    }

    public void supprimerEvenement(int id) {
        try {
            conxstat();
            String requete1 = "DELETE FROM evenement WHERE `id_e`=" + e.getId_e() + "";
            PreparedStatement pst1 = cnxs.prepareStatement(requete1);
            /* pst1.setInt(1,e.getId_e());*/
            pst1.executeUpdate();
            System.out.println("Evenement supprimé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void Goback(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Admin");
        app_stage.show();
    }

    @FXML
    private void supprimerevent(ActionEvent event) {
        if (tableAffichage.getSelectionModel().getSelectedItem() == null) {
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
        Image image = new Image("/images/icons8-ok-128.png", true);
        int n = tableAffichage.getSelectionModel().getSelectedItem().getId_e();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        DialogPane pane = alert.getDialogPane();
        pane.setPrefHeight(150.0);
        alert.setWidth(pane.getWidth());
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        alert.setX(bounds.getMaxX() - alert.getWidth());
        alert.setY(bounds.getMaxY() - pane.getPrefHeight());
        
        alert.setTitle("Confirmation");
        alert.setHeaderText(" voulez-Vous Supprimer ce evenement?");
        alert.setContentText(" Appuyez-vous sur OK si vous êtes d'accord ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            supprimerEvenement(n);
            Notifications notificationBuilder = Notifications.create()
                    .text(" Votre Suppression a été effectuée").title("Evenement").graphic(new ImageView(image)).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    System.out.println("Done ! ");
                }

            });
            notificationBuilder.show();

        }
        if (result.get() == ButtonType.CANCEL) {
            Notifications notificationBuilder2 = Notifications.create()
                    .text("la Suppersion de l'evenement est annulée ").title("Evenement").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    System.out.println("Evenement annulée");
                }

            });
            notificationBuilder2.showError();
        }

    }
    }
}
