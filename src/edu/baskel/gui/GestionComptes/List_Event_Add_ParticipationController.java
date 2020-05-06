/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.services.SendMail;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import edu.baskel.utils.validationSaisie;

public class List_Event_Add_ParticipationController implements Initializable {

    @FXML
    private AnchorPane anchor1;
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
    private JFXButton participer;
    @FXML
    private AnchorPane rootPaneA;

    @FXML
    private JFXButton btnAjout;
    @FXML
    private AnchorPane rootPane;
    Membre m = SessionInfo.getLoggedM();

    public List_Event_Add_ParticipationController() {

        ConnectionBD mc = ConnectionBD.getInstance();

    }

    /*Affichage les champs dans le table view*/
    public void affichageEvenement() {

        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayAllList();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));
        tableAffichage.setItems(obser);

    }

    /* ajout de participation*/
    @FXML
    void participerEvenement(ActionEvent event) throws Exception {
        
        ParticipationCrud Pc = new ParticipationCrud();
        Participation p = new Participation(tableAffichage.getSelectionModel().getSelectedItem().getId_e(),7);
        if (Pc.verifierParticipation(7,tableAffichage.getSelectionModel().getSelectedItem().getId_e())==0){
             Alert alertParticipation = new validationSaisie().getAlert("Echec", "Vous avez deja particpé a ce evenement");
            alertParticipation.showAndWait();
            actualiser();
        }
        else {
        Pc.ajouterParticipation(p);
        SendMail Sm = new SendMail();
            Sm.envoiMail("sabrine.zekri@esprit.tn");
           Alert alertParticipationValidé = new validationSaisie().getAlert("ok", "Votre participation est confirmé ");
           alertParticipationValidé.showAndWait();
            actualiser();
        }
    }

    
      private void actualiser() {

        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayAllList();
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        tableAffichage.setItems(obser);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));

    }

    @FXML
    void lancerAjout(ActionEvent event) {
/*
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Ajouter_Evenement.fxml"));
            rootPaneA.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageEvenement();
    }

}
