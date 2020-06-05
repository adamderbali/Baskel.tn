/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.MailAttachement;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.services.Qrcode;
import edu.baskel.services.StatCRUD;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.log4j.helpers.Loader;

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
    private TableColumn<Evenement, String> actionCol;
    @FXML
    private TableColumn<Evenement, String> colnbrpart;

    @FXML
    private TableColumn<Evenement, String> colDescription;

    @FXML
    private TableColumn<Evenement, String> ColImage;

    @FXML
    private JFXButton btnAjout;
    ObservableList obser;

    ImageView imagev;
    Membre m = SessionInfo.getLoggedM();

    @FXML
    private JFXTextField search;
    ObservableList obser1;
    @FXML
    private JFXButton consulterVosPar;
    Membre ml = SessionInfo.getLoggedM();
    @FXML
    private JFXCheckBox listPar;
    @FXML
    private JFXCheckBox list;

    public List_Event_Add_ParticipationController() {

        ConnectionBD mc = ConnectionBD.getInstance();

    }

    Evenement e;

    void chargerDonnee() {
        e = tableAffichage.getSelectionModel().getSelectedItem();
    }

    public Evenement getClickedEvent() {
        return e;
    }

    public String getIdEvent() {
        return String.valueOf(e.getId_e());

    }
   public void desactiverButtonConsulterPar(){
       ParticipationCrud pc = new ParticipationCrud();
       if (pc.vosParticipation(ml.getId_u())==true){
           consulterVosPar.setDisable(false);
       }
       else {
            consulterVosPar.setDisable(true);
       }
   }
    /*Affichage les champs dans le table view*/
    public void affichageEvenement() throws Exception {

        EvenementCRUD Ec = new EvenementCRUD();

        Qrcode qr = new Qrcode();

        MailAttachement ma = new MailAttachement();
        ParticipationCrud pc = new ParticipationCrud();
        Evenement e = new Evenement();
        ArrayList arrayList;
        // ArrayList arrayList1;

        arrayList = (ArrayList) Ec.displayAllList();

        // arrayList.addAll(arrayList1);
        System.out.println("-------------+++++++++++++------------" + arrayList);

        obser = FXCollections.observableArrayList(arrayList);

        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colnbrpart.setCellValueFactory(new PropertyValueFactory<>("etat_p"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        colDescription.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colNom.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNom.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colLieu.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colLieu.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        ColDate.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(ColDate.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        tableAffichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent click) {
                if ((click.getClickCount() == 1) && (Ec.verifierSs(tableAffichage.getSelectionModel().getSelectedItem().getNbr_max_e(), tableAffichage.getSelectionModel().getSelectedItem().getNbr_participant()) == true)
                        && (pc.verifierParticipation(7, tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == true)) {

                    validationSaisie.notifInfo("ok", "Le nombre maximal de participations a été atteint");

                } else if ((click.getClickCount() == 1) && (Ec.listEventParMembre(ml.getId_u(), tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == true)) {

                    validationSaisie.notifInfo("ok", "Vous etes l'organisateur de l'evenement");

                }
            }

        });

        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactory
                = //
                new Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>>() {
            @Override
            public TableCell call(final TableColumn<Evenement, String> param) {
                final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                    // final Button btn = new Button("Just Do It");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            Evenement ee = getTableView().getItems().get(getIndex());

                            if (pc.verifierParticipation(ml.getId_u(), ee.getId_e()) == true) {
                                final Button btn = new Button("Participer à l'evenement");
                                btn.setStyle("text-fill: #007782");

                                if (Ec.verifierSs(ee.getNbr_max_e(), ee.getNbr_participant()) == true) {
                                    btn.setDisable(true);
                                }
                                if (Ec.listEventParMembre(ml.getId_u(), ee.getId_e()) == true) {
                                    btn.setDisable(true);
                                }
                                btn.setOnAction(event -> {
                                    if (Ec.verifierNbrMaxE(ee.getId_e()) == true) {
                                        Participation p = new Participation(ee.getId_e(), ml.getId_u());

                                        pc.ajouterParticipation(p);
                                        qr.Create("nom= " + ee.getNom_e() + "Date= " + ee.getDate_e(), ee.getNom_e());
                                        try {
                                            ma.envoiMailQrcode(ml.getEmail_u(), ee.getNom_e());
                                        } catch (Exception ex) {
                                            Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        validationSaisie.notifConfirm("ok", "Votre participation à l'evenement" + ee.getNom_e() + " a été bien confirmée. "
                                                + "<br> Vous recevrez ultérieurement un message contenant le QR code de l'événement"
                                                + " <br> auquel vous avez participé.");
                                        System.out.println("okok++++okokok");

                                        actualiser();
                                    } else if (Ec.verifierParticipant(ee.getId_e()) == true) {
                                        Participation p = new Participation(ee.getId_e(), ml.getId_u());

                                        Evenement e = new Evenement();
                                        Ec.nbrParticipant(ee.getId_e());
                                        pc.ajouterParticipation(p);
                                        qr.Create("nom= " + ee.getNom_e() + "Date= " + ee.getDate_e(), ee.getNom_e());
                                        // System.out.println("++++++++++++++mail shih?" + ml.getEmail_u);
                                        try {
                                            validationSaisie.notifInfo("ok", "Mail en cours");
                                            ma.envoiMailQrcode(ml.getEmail_u(), ee.getNom_e());
                                            //  System.out.println("++++++++++++++mail shih?" + ml.getEmail_u());
                                        } catch (Exception ex) {
                                            Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        validationSaisie.notifConfirm("ok", "Votre participation à l'evenement" + ee.getNom_e() + " a été bien confirmée. Vous recevrez ultérieurement un message  contenant le QR code de l'événement auquel vous avez participé.");
                                        System.out.println("okok++++okokok");
                                        actualiser();
                                    } else {
                                        validationSaisie.notifInfo("Information", "Le nombre maximal de participations a été atteint");

                                        //actualiseUpadate();
                                    }

                                });
                                setGraphic(btn);

                                setText(null);
                            } else if (pc.verifierParticipation(ml.getId_u(), ee.getId_e()) == false) {
                                final Button btn = new Button("Annuler participation");
                                if (Ec.listEventParMembre(ml.getId_u(), ee.getId_e()) == true) {
                                    btn.setDisable(true);
                                }
                                btn.setOnAction(event -> {

                                    if (validationSaisie.confrimSuppression("Information", "Voulez vous supprimer cette participation")) {
                                        if (pc.supprimerParticipationE(ee.getId_e()) == true) {
                                            if (Ec.verifierNbrMaxE(ee.getId_e()) == true) {
                                                System.out.println("++++++OK oK");
                                                // tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                                                validationSaisie.notifConfirm("ok", "Participation annulée");
                                                System.out.println("ok--------------------");
                                                actualiser();
                                            } else if (Ec.verifierNbrMaxE(ee.getId_e()) == false) {
                                                Ec.nbrParticipantDelete(ee.getId_e());
                                                System.out.println("++++++OK oK");
                                                //tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                                                validationSaisie.notifConfirm("ok", "Participation annulée");
                                                System.out.println("ok--------------------");
                                                actualiser();
                                            }
                                            actualiser();
                                        } else {
                                            System.out.println("----------erreur");
                                        }

                                    }

                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

        tableAffichage.setItems(obser);
        //  tableAffichage.getColumns().addAll(actionCol);

    }

    /*
        tableAffichage.widthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
            {
               
                
                //Don't show header
                Pane header = (Pane) tableAffichage.lookup("TableHeaderRow");
                if (header.isVisible()){
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                    
                }
            }
        });*/
    @FXML
    void consultarPar(ActionEvent event) {

        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Annulation_participation.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.setTitle("Historique de vos participation");
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

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

    public void actualiser() {

        EvenementCRUD Ec = new EvenementCRUD();

        // ParticipationCrud pc = new ParticipationCrud();
        Evenement e = new Evenement();
        ArrayList arrayList;
        // ArrayList arrayList1;

        arrayList = (ArrayList) Ec.displayAllList();

        // arrayList.addAll(arrayList1);
        System.out.println("-------------+++++++++++++------------" + arrayList);

        obser = FXCollections.observableArrayList(arrayList);

        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));

        colnbrpart.setCellValueFactory(new PropertyValueFactory<>("etat_p"));

        tableAffichage.setItems(obser);
    }

    public JFXButton getBtnAjout() {
        return btnAjout;
    }

    @FXML
    void lancerAjout(ActionEvent event) {
        //btnAjout.setDisable(true);
        Ajouter_EvenementController controller2 = new Ajouter_EvenementController(this);
        
        controller2.showStage();
        
        
        

    }

    public void affichageEvenementP() throws Exception {

        EvenementCRUD Ec = new EvenementCRUD();

        ParticipationCrud pc = new ParticipationCrud();
        Evenement e = new Evenement();
        ArrayList arrayList;
        // ArrayList arrayList1;

        arrayList = (ArrayList) Ec.displayAllListNonPar(ml.getId_u());

        System.out.println("-------------+++++++++++++------------" + arrayList);
        obser = FXCollections.observableArrayList(arrayList);
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colnbrpart.setCellValueFactory(new PropertyValueFactory<>("etat_p"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
         colDescription.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colNom.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNom.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colLieu.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colLieu.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        ColDate.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(ColDate.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        tableAffichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent click) {
                if ((click.getClickCount() == 1) && (Ec.verifierSs(tableAffichage.getSelectionModel().getSelectedItem().getNbr_max_e(), tableAffichage.getSelectionModel().getSelectedItem().getNbr_participant()) == true)
                        && (pc.verifierParticipation(7, tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == true)) {

                    validationSaisie.notifInfo("ok", "Le nombre maximal de participations a été atteint");

                } else if ((click.getClickCount() == 1) && (Ec.listEventParMembre(ml.getId_u(), tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == true)) {

                    validationSaisie.notifInfo("ok", "Vous etes l'organisateur de l'evenement");

                }
            }

        });

        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactory
                = //
                new Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>>() {
            @Override
            public TableCell call(final TableColumn<Evenement, String> param) {
                final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Evenement ee = getTableView().getItems().get(getIndex());
                            final Button btn = new Button("Annuler participation");
                            if (Ec.listEventParMembre(ml.getId_u(), ee.getId_e()) == true) {
                                btn.setDisable(true);
                            }
                            btn.setOnAction(event -> {

                                if (validationSaisie.confrimSuppression("Information", "Voulez vous supprimer cette participation")) {
                                    if (pc.supprimerParticipationE(ee.getId_e()) == true) {
                                        if (Ec.verifierNbrMaxE(ee.getId_e()) == true) {
                                            System.out.println("++++++OK oK");
                                            // tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                                            validationSaisie.notifConfirm("ok", "Participation annulée");
                                            System.out.println("ok--------------------");
                                            actualiserAnnulation();
                                        } else if (Ec.verifierNbrMaxE(ee.getId_e()) == false) {
                                            Ec.nbrParticipantDelete(ee.getId_e());
                                            System.out.println("++++++OK oK");
                                            //tableAffichage.getItems().removeAll(tableAffichage.getSelectionModel().getSelectedItem());
                                            validationSaisie.notifConfirm("ok", "Participation annulée");
                                            System.out.println("ok--------------------");
                                            actualiserAnnulation();
                                        }
                                        actualiserAnnulation();
                                    } else {
                                        System.out.println("----------erreur");
                                        actualiserAnnulation();
                                    }

                                }
                                actualiserAnnulation();
                            });
                            setGraphic(btn);
                            setText(null);
                        }

                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
        tableAffichage.setItems(obser);
        //tableAffichage.getColumns().addAll(actionCol);
    }

    public void affichageEvenementOkP() throws Exception {

        EvenementCRUD Ec = new EvenementCRUD();

        Qrcode qr = new Qrcode();

        MailAttachement ma = new MailAttachement();
        ParticipationCrud pc = new ParticipationCrud();
        Evenement e = new Evenement();
        ArrayList arrayList;
        // ArrayList arrayList1;

        arrayList = (ArrayList) Ec.displayAllListOkPar(ml.getId_u());

        // arrayList.addAll(arrayList1);
        System.out.println("-------------+++++++++++++------------" + arrayList);
        obser = FXCollections.observableArrayList(arrayList);
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));
        colnbrpart.setCellValueFactory(new PropertyValueFactory<>("etat_p"));
          colDescription.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colDescription.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colNom.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colNom.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        colLieu.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(colLieu.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
        ColDate.setCellFactory(tc -> {
            TableCell cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(ColDate.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        tableAffichage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent click) {
                if ((click.getClickCount() == 1) && (Ec.verifierSs(tableAffichage.getSelectionModel().getSelectedItem().getNbr_max_e(), tableAffichage.getSelectionModel().getSelectedItem().getNbr_participant()) == true)
                        && (pc.verifierParticipation(7, tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == true)) {

                    validationSaisie.notifInfo("ok", "Le nombre maximal de participations a été atteint");

                } else if ((click.getClickCount() == 1) && (Ec.listEventParMembre(ml.getId_u(), tableAffichage.getSelectionModel().getSelectedItem().getId_e()) == true)) {

                    validationSaisie.notifInfo("ok", "Vous etes l'organisateur de l'evenement");

                }
            }

        });

        // TableColumn actionCol = new TableColumn("");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactory
                = //
                new Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>>() {
            @Override
            public TableCell call(final TableColumn<Evenement, String> param) {
                final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button btn = new Button("Participer à l'evenement");
                            Evenement ee = getTableView().getItems().get(getIndex());
                            if (Ec.listEventParMembre(ml.getId_u(), ee.getId_e()) == true) {
                                btn.setDisable(true);
                            }

                            if (pc.verifierParticipation(ml.getId_u(), ee.getId_e()) == true) {

                                if (Ec.verifierSs(ee.getNbr_max_e(), ee.getNbr_participant()) == true) {
                                    btn.setDisable(true);
                                }
                                btn.setOnAction(event -> {
                                    if (Ec.verifierNbrMaxE(ee.getId_e()) == true) {
                                        Participation p = new Participation(ee.getId_e(), ml.getId_u());

                                        pc.ajouterParticipation(p);
                                        qr.Create("nom= " + ee.getNom_e() + "Date= " + ee.getDate_e(), ee.getNom_e());
                                        try {
                                            ma.envoiMailQrcode(ml.getEmail_u(), ee.getNom_e());
                                        } catch (Exception ex) {
                                            Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        validationSaisie.notifConfirm("ok", "Votre participation à l'evenement" + ee.getNom_e() + " a été bien confirmée. "
                                                + "<br> Vous recevrez ultérieurement un message contenant le QR code de l'événement"
                                                + " <br> auquel vous avez participé.");
                                        System.out.println("okok++++okokok");

                                        actualiserParticipation();
                                    } else if (Ec.verifierParticipant(ee.getId_e()) == true) {
                                        Participation p = new Participation(ee.getId_e(), ml.getId_u());

                                        Evenement e = new Evenement();
                                        Ec.nbrParticipant(ee.getId_e());
                                        pc.ajouterParticipation(p);
                                        qr.Create("nom= " + ee.getNom_e() + "Date= " + ee.getDate_e(), ee.getNom_e());
                                        // System.out.println("++++++++++++++mail shih?" + ml.getEmail_u);
                                        try {
                                            validationSaisie.notifInfo("ok", "Mail en cours");
                                            ma.envoiMailQrcode(ml.getEmail_u(), ee.getNom_e());
                                            //  System.out.println("++++++++++++++mail shih?" + ml.getEmail_u());
                                        } catch (Exception ex) {
                                            Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        validationSaisie.notifConfirm("ok", "Votre participation à l'evenement" + ee.getNom_e() + " a été bien confirmée. Vous recevrez ultérieurement un message  contenant le QR code de l'événement auquel vous avez participé.");
                                        System.out.println("okok++++okokok");
                                        actualiserParticipation();
                                    } else {
                                        validationSaisie.notifInfo("Information", "Le nombre maximal de participations a été atteint");

                                    }
                                    actualiserParticipation();
                                });
                                setGraphic(btn);

                                setText(null);

                            }
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
        tableAffichage.setItems(obser);
        // tableAffichage.getColumns().addAll(actionCol);

    }

    public void actualiserAnnulation() {
        EvenementCRUD Ec = new EvenementCRUD();
        ParticipationCrud pc = new ParticipationCrud();
        Evenement e = new Evenement();
        ArrayList arrayList;
        // ArrayList arrayList1;

        arrayList = (ArrayList) Ec.displayAllListNonPar(ml.getId_u());

        // arrayList.addAll(arrayList1);
        System.out.println("-------------+++++++++++++------------" + arrayList);

        obser = FXCollections.observableArrayList(arrayList);

        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));

        colnbrpart.setCellValueFactory(new PropertyValueFactory<>("etat_p"));

        tableAffichage.setItems(obser);
    }

    public void actualiserParticipation() {
        EvenementCRUD Ec = new EvenementCRUD();
        ParticipationCrud pc = new ParticipationCrud();
        Evenement e = new Evenement();
        ArrayList arrayList;
        // ArrayList arrayList1;

        arrayList = (ArrayList) Ec.displayAllListOkPar(ml.getId_u());

        // arrayList.addAll(arrayList1);
        System.out.println("-------------+++++++++++++------------" + arrayList);

        obser = FXCollections.observableArrayList(arrayList);

        ColImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_e"));
        colLieu.setCellValueFactory(new PropertyValueFactory<>("lieu_e"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_e"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description_e"));

        colnbrpart.setCellValueFactory(new PropertyValueFactory<>("etat_p"));

        tableAffichage.setItems(obser);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatCRUD sc = new StatCRUD();
desactiverButtonConsulterPar();
        actualiser();
        try {
            affichageEvenement();
        } catch (Exception ex) {
            Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void listPar(ActionEvent event) {

        if (listPar.isSelected()) {
            try {
                affichageEvenementP();
            } catch (Exception ex) {
                Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (list.isSelected()) {
            try {
                affichageEvenementOkP();
            } catch (Exception ex) {
                Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ((!(listPar.isSelected())) && (!(list.isSelected()))) {
            try {
                affichageEvenement();
            } catch (Exception ex) {
                Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ((listPar.isSelected()) && (list.isSelected())) {
            try {
                affichageEvenement();
            } catch (Exception ex) {
                Logger.getLogger(List_Event_Add_ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
