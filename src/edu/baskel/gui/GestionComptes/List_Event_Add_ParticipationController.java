/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.google.zxing.qrcode.encoder.QRCode;
import com.jfoenix.controls.JFXButton;

import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;

import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.MailAttachement;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.services.Qrcode;
import edu.baskel.services.SendMail;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import edu.baskel.utils.validationSaisie;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

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
    private TableColumn<Evenement, String> ColDate;

    @FXML
    private TableColumn<Evenement, String> colDescription;

    @FXML
    private TableColumn<Evenement, ImageView> ColImage;

    @FXML
    private JFXButton participer;

    @FXML
    private JFXButton btnAjout;
    ObservableList obser;

    ImageView imagev;
    Membre m = SessionInfo.getLoggedM();
    @FXML
    private TextField search;

    public List_Event_Add_ParticipationController() {

        ConnectionBD mc = ConnectionBD.getInstance();

    }

    /*Affichage les champs dans le table view*/
    public void affichageEvenement() {

        EvenementCRUD Ec = new EvenementCRUD();
        Evenement e = new Evenement();
        ArrayList arrayList;
        arrayList = (ArrayList) Ec.displayAllList();
        //ImageView imageEvent = new ImageView(new Image(this.getClass().getResourceAsStream("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())));

        //  ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(e.getImage_e())));
        //  PropertyValueFactory<Evenement,ImageView> photo = new PropertyValueFactory<> (("file:/C:\\wamp\\www\\Baskel\\images\\" + e.getImage_e())) ;
        obser = FXCollections.observableArrayList(arrayList);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
      //  ImageView eventPhoto = new ImageView(new Image(this.getClass().getResourceAsStream(e.getImage_e())));
        
         
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));

        tableAffichage.setItems(obser);

    }

    @FXML
    private void searchBox(KeyEvent event) {
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
    void participerEvenement(ActionEvent event) throws Exception {
       
        ParticipationCrud Pc = new ParticipationCrud();
        Qrcode qr = new Qrcode();
        MailAttachement ma = new MailAttachement();
        Participation p = new Participation(tableAffichage.getSelectionModel().getSelectedItem().getId_e(), 7);
        if (Pc.verifierParticipation(7, tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == false) {
            System.out.println();
            validationSaisie.notifInfo("Information", "Vous avez deja particpé a ce evenement");
            actualiser();
        } else {
            Pc.ajouterParticipation(p);
            qr.Create("nom= "+tableAffichage.getSelectionModel().getSelectedItem().getNom_e()+"Date= "+tableAffichage.getSelectionModel().getSelectedItem().getDate_e(), tableAffichage.getSelectionModel().getSelectedItem().getNom_e());
            ma.envoiMailQrcode("sabrine.zekri@esprit.tn", tableAffichage.getSelectionModel().getSelectedItem().getNom_e());
            validationSaisie.notifConfirm("ok", "Votre participation est confirmé et vous allez reçevoir un mail qui contient QrCode de votre participation");
            System.out.println("okok++++okokok");
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
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_e"));

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
