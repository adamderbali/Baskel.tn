/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.Evenements;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ParticipationCrud;
import static edu.baskel.services.ParticipationCrud.cnx;
import edu.baskel.services.SendMail;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.rgb;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class ModifierController implements Initializable {

    Membre ml = SessionInfo.getLoggedM();

    Image image;
    Image im;
    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtLieu;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private TextField pathE;

    @FXML
    private JFXButton fileChoose;

    @FXML
    private JFXButton fermer;

    @FXML
    private JFXButton idValider;

    @FXML
    private ImageView img;

    @FXML
    private JFXButton nom;

    @FXML
    private JFXButton lieu;

    @FXML
    private JFXButton description;

    @FXML
    private JFXButton date;

    private Stage thisStage;

    private final GererController controller1;

    public ModifierController(GererController controller1) {
        this.controller1 = controller1;
        thisStage = new Stage();
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Modifier evenement");
            // Setup the window/stage
            //thisStage.setTitle("Passing Controllers Example - Layout2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    public void affichageEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        int i = Integer.parseInt(controller1.getIdEvent());
        arrayList = (ArrayList) Ec.displayByEvent(i);
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        Evenement ev = (Evenement) arrayList.get(0);
        txtNom.setText(ev.getNom_e());
        txtLieu.setText(ev.getLieu_e());
        txtDate.setValue(LocalDate.parse(ev.getDate_e(), formatter));
        txtDescription.setText(ev.getDescription_e());
        pathE.setText(ev.getImage_e());
        if (ev.getNbr_max_e() == 0) {
            txtNombre.setText("");
        } else {
            txtNombre.setText(String.valueOf(ev.getNbr_max_e()));
        }
        Image imgE = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + ev.getImage_e());
        img.setImage(imgE);
        img.setVisible(true);

    }

    @FXML
    void ValiderModif(ActionEvent event) throws Exception {
        ParticipationCrud Pc = new ParticipationCrud();
        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate.getEditor().getText();
        /* test sur les champs vides ou non*/
        if ((txtNom.getText().isEmpty()) && (txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty()) && (txtDescription.getText().isEmpty())) {
            validationSaisie.notif("Echec", "Tous les champs doivent etre saisis");
            txtNom.setFocusColor(rgb(255, 0, 0));
            txtNom.setUnFocusColor(rgb(255, 0, 0));
            txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtLieu.setFocusColor(rgb(255, 0, 0));
            txtLieu.setUnFocusColor(rgb(255, 0, 0));
            txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
            txtDescription.setFocusColor(rgb(255, 0, 0));
            txtDescription.setUnFocusColor(rgb(255, 0, 0));
            txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");
            txtDate.setDefaultColor(Color.RED);
            txtDate.setStyle("-fx-prompt-text-fill: #C4151C");

        } else {
            if ((txtDescription.getText().isEmpty()) && (txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                validationSaisie.notif("Echec", "Saisir le nom,le lieu et la date de l'evenement");
                txtDescription.setFocusColor(rgb(255, 0, 0));
                txtDescription.setUnFocusColor(rgb(255, 0, 0));
                txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");
                txtLieu.setFocusColor(rgb(255, 0, 0));
                txtLieu.setUnFocusColor(rgb(255, 0, 0));
                txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                txtDate.setDefaultColor(Color.RED);
                txtDate.setStyle("-fx-prompt-text-fill: #C4151C");

            } else {
                if ((txtDescription.getText().isEmpty()) && (txtLieu.getText().isEmpty()) && (txtNom.getText().isEmpty())) {
                    validationSaisie.notif("Echec", "Saisir le nom,la description et le lieu de l'evenement");
                    txtDescription.setFocusColor(rgb(255, 0, 0));
                    txtDescription.setUnFocusColor(rgb(255, 0, 0));
                    txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");
                    txtLieu.setFocusColor(rgb(255, 0, 0));
                    txtLieu.setUnFocusColor(rgb(255, 0, 0));
                    txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                    txtNom.setFocusColor(rgb(255, 0, 0));
                    txtNom.setUnFocusColor(rgb(255, 0, 0));
                    txtNom.setStyle("-fx-prompt-text-fill: #C4151C");

                } else {
                    if ((txtNom.getText().isEmpty()) && (txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                        validationSaisie.notif("Echec", "Saisir le nom,le lieu et la date de l'evenement");
                        txtNom.setFocusColor(rgb(255, 0, 0));
                        txtNom.setUnFocusColor(rgb(255, 0, 0));
                        txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                        txtLieu.setFocusColor(rgb(255, 0, 0));
                        txtLieu.setUnFocusColor(rgb(255, 0, 0));
                        txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                        txtDate.setDefaultColor(Color.RED);
                        txtDate.setStyle("-fx-prompt-text-fill: #C4151C");

                    } else {
                        if ((txtNom.getText().isEmpty()) && (txtDescription.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                            validationSaisie.notif("Echec", "Saisir le nom,la description et la date de l'evenement");
                            txtNom.setFocusColor(rgb(255, 0, 0));
                            txtNom.setUnFocusColor(rgb(255, 0, 0));
                            txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                            txtDescription.setFocusColor(rgb(255, 0, 0));
                            txtDescription.setUnFocusColor(rgb(255, 0, 0));
                            txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");
                            txtDate.setDefaultColor(Color.RED);
                            txtDate.setStyle("-fx-prompt-text-fill: #C4151C");

                        } else {
                            if ((txtNom.getText().isEmpty()) && (txtLieu.getText().isEmpty())) {
                                validationSaisie.notif("Echec", "Saisir le nom et le lieu de l'evenement");
                                txtNom.setFocusColor(rgb(255, 0, 0));
                                txtNom.setUnFocusColor(rgb(255, 0, 0));
                                txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                                txtLieu.setFocusColor(rgb(255, 0, 0));
                                txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");

                            } else {
                                if ((txtNom.getText().isEmpty()) && (txtDescription.getText().isEmpty())) {
                                    validationSaisie.notif("Echec", "Saisir le nom et la description de l'evenement");
                                    txtNom.setFocusColor(rgb(255, 0, 0));
                                    txtNom.setUnFocusColor(rgb(255, 0, 0));
                                    txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                                    txtDescription.setFocusColor(rgb(255, 0, 0));
                                    txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                    txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");

                                } else {

                                    if ((txtNom.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                                        validationSaisie.notif("Echec", "Saisir le nom et la date de l'evenement");
                                        txtNom.setFocusColor(rgb(255, 0, 0));
                                        txtNom.setUnFocusColor(rgb(255, 0, 0));
                                        txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                                        txtDate.setDefaultColor(Color.RED);
                                        txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                    } else {

                                        if ((txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                                            validationSaisie.notif("Echec", "Saisir le lieu et la date de l'evenement");

                                            txtLieu.setFocusColor(rgb(255, 0, 0));
                                            txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                            txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                                            txtDate.setDefaultColor(Color.RED);
                                            txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                        } else {
                                            if ((txtLieu.getText().isEmpty()) && (txtDescription.getText().isEmpty())) {
                                                validationSaisie.notif("Echec", "Saisir le lieu et la description de l'evenement");

                                                txtLieu.setFocusColor(rgb(255, 0, 0));
                                                txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                                txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                                                txtDescription.setFocusColor(rgb(255, 0, 0));
                                                txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                                txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");

                                            } else {

                                                if ((txtDescription.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                                                    validationSaisie.notif("Echec", "Saisir la description et la date de l'evenement");
                                                    txtNom.setFocusColor(rgb(255, 0, 0));

                                                    txtDescription.setFocusColor(rgb(255, 0, 0));
                                                    txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                                    txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");
                                                    txtDate.setDefaultColor(Color.RED);
                                                    txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                                } else {
                                                    if (validationSaisie.validTextField(txtNom.getText())) {
                                                        validationSaisie.notif("Echec", "Saisir le nom de l'evenement");
                                                        txtNom.setFocusColor(rgb(255, 0, 0));
                                                        txtNom.setUnFocusColor(rgb(255, 0, 0));
                                                        txtNom.setStyle("-fx-prompt-text-fill: #C4151C");

                                                    } else {
                                                        if (validationSaisie.validTextField(txtLieu.getText())) {

                                                            validationSaisie.notif("Echec", "Saisir le lieu de l'evenement ");
                                                            txtNom.setFocusColor(rgb(255, 0, 0));

                                                            txtLieu.setFocusColor(rgb(255, 0, 0));
                                                            txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                                            txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");

                                                        } else {

                                                            if (validationSaisie.validTextField(txtDescription.getText())) {

                                                                validationSaisie.notif("Echec", "Saisir la description de l'evenement");
                                                                txtNom.setFocusColor(rgb(255, 0, 0));

                                                                txtDescription.setFocusColor(rgb(255, 0, 0));
                                                                txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                                                txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");

                                                            } else {
                                                                if ((validationSaisie.validTextField(txtDate.getEditor().getText()))) {
                                                                    validationSaisie.notif("Echec", "Saisir la date de l'evenement");
                                                                    txtNom.setFocusColor(rgb(255, 0, 0));

                                                                    txtDate.setDefaultColor(Color.RED);
                                                                    txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                                                } else {
                                                                    if (validationSaisie.validDate(txtDate.getEditor().getText())) {
                                                                        validationSaisie.notif("Erreur", "La date saisie doit être au delà de" + date_system);
                                                                        txtDate.setDefaultColor(rgb(255, 0, 0));
                                                                        txtDate.setStyle("-fx-prompt-text-fill: #C4151C");

                                                                    } else {

                                                                        if (((InputValidation.Number(txtNombre.getText())) == 0) && (!(txtNombre.getText().equals("")))) {
                                                                            validationSaisie.notif("Nombre des participants", "saisir un entier");

                                                                            txtNombre.setFocusColor(rgb(255, 0, 0));
                                                                            txtNombre.setUnFocusColor(rgb(255, 0, 0));
                                                                            txtNombre.setStyle("-fx-prompt-text-fill: #C4151C");
                                                                        } else {
                                                                            EvenementCRUD Ev = new EvenementCRUD();
                                                                            int ii = Integer.parseInt(controller1.getIdEvent());
                                                                            if (Ev.verifierNomUpdate(txtNom.getText(), ii) == true) {
                                                                                txtNom.setFocusColor(rgb(255, 0, 0));
                                                                                txtNom.setUnFocusColor(rgb(255, 0, 0));
                                                                                txtNom.setStyle("-fx-text-fill: #C4151C");

                                                                                validationSaisie.notif("Erreur", "Le nom de l'evenement existe deja");
                                                                            } else {
                                                                                if (validationSaisie.confrimSuppression("Information", "Voulez vous modifier cet evenement")) {
                                                                                    EvenementCRUD Ec = new EvenementCRUD();
                                                                                    if (txtDate.getEditor().getText().equals(controller1.getClickedEvent().getDate_e())) {
                                                                                        if (txtNombre.getText().isEmpty()) {
                                                                                            int i = Integer.parseInt(controller1.getIdEvent());
                                                                                            Evenement e = new Evenement(i,
                                                                                                    txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText(), 0, 0
                                                                                            );

                                                                                            Ec.updateEvenement(e);

                                                                                            txtNom.setEditable(false);
                                                                                            txtLieu.setEditable(false);
                                                                                            txtDate.setEditable(false);
                                                                                            txtDescription.setEditable(false);
                                                                                            pathE.setEditable(false);
                                                                                            txtNombre.setEditable(false);
                                                                                            img.setVisible(true);

                                                                                            Stage stage = (Stage) fermer.getScene().getWindow();
                                                                                            stage.close();
                                                                                            controller1.actualiser();
                                                                                            validationSaisie.notifConfirm("ok", "Evenement Modifié");
                                                                                        } else {
                                                                                            int i = Integer.parseInt(controller1.getIdEvent());
                                                                                            int nbrPart = Pc.nombreParticipation(i);
                                                                                            if (nbrPart > Integer.parseInt(txtNombre.getText())) {
                                                                                                validationSaisie.notif("Erreur", "Vous devez saisir un nombre superieur à " + nbrPart + "car le nombre de participants est  " + nbrPart);
                                                                                            } else {
                                                                                                Evenement e = new Evenement(i,
                                                                                                        txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText(), Integer.parseInt(txtNombre.getText()), nbrPart
                                                                                                );

                                                                                                Ec.updateEvenement(e);

                                                                                                txtNom.setEditable(false);
                                                                                                txtLieu.setEditable(false);
                                                                                                txtDate.setEditable(false);
                                                                                                txtDescription.setEditable(false);
                                                                                                pathE.setEditable(false);
                                                                                                txtNombre.setEditable(false);
                                                                                                img.setVisible(true);
                                                                                                Stage stage = (Stage) fermer.getScene().getWindow();
                                                                                                stage.close();
                                                                                                controller1.actualiser();
                                                                                                validationSaisie.notifConfirm("ok", "Evenement Modifié");
                                                                                            }

                                                                                        }

                                                                                    } else {
                                                                                        if (txtNombre.getText().isEmpty()) {
                                                                                            int i = Integer.parseInt(controller1.getIdEvent());
                                                                                            Evenement e = new Evenement(i,
                                                                                                    txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText(), 0, 0
                                                                                            );
                                                                                            Ec.updateEvenement(e);
                                                                                            Pc.displayEmailParticipant(i);
                                                                                            MembreCRUD mc = new MembreCRUD();
                                                                                            SendMail Sm = new SendMail();

                                                                                            for (Participation p : Pc.displayEmailParticipant(i)) {

                                                                                                try {
                                                                                                    String sq1 = "SELECT * FROM membre WHERE id_u=?";
                                                                                                    PreparedStatement prep = cnx.prepareStatement(sq1);
                                                                                                    prep.setInt(1, p.getId_u());
                                                                                                    ResultSet res = prep.executeQuery();

                                                                                                    if (res.next()) {
                                                                                                        String em = res.getString("email_u");
                                                                                                        Sm.envoiMailModification(em, "Nous vous informons que la date de l'evenement :" + controller1.getClickedEvent().getNom_e() + " auquel vous avez participé a été modifiée.La nouvelle date est fixée pour " + txtDate.getEditor().getText());
                                                                                                        System.out.println(em);
                                                                                                    } else {
                                                                                                        System.out.println("Aucun participant");

                                                                                                    }
                                                                                                } catch (SQLException ex) {
                                                                                                    ex.printStackTrace();
                                                                                                }
                                                                                            }

                                                                                            txtNom.setEditable(false);
                                                                                            txtLieu.setEditable(false);
                                                                                            txtDate.setEditable(false);
                                                                                            txtDescription.setEditable(false);
                                                                                            pathE.setEditable(false);
                                                                                            txtNombre.setEditable(false);
                                                                                            img.setVisible(true);
                                                                                            Stage stage = (Stage) fermer.getScene().getWindow();
                                                                                            stage.close();
                                                                                            controller1.actualiser();
                                                                                            validationSaisie.notifConfirm("ok", "Evenement Modifié");
                                                                                            System.out.println("Mch nafes date matbadel chay");

                                                                                        } else {

                                                                                            int i = Integer.parseInt(controller1.getIdEvent());
                                                                                            int nbrPart = Pc.nombreParticipation(i);
                                                                                            if (nbrPart > Integer.parseInt(txtNombre.getText())) {
                                                                                                validationSaisie.notif("Erreur", "Vous devez saisir un nombre superieur à " + nbrPart + "  car le nombre de participant est  " + nbrPart);
                                                                                            } else {
                                                                                              
                                                                                                Evenement e = new Evenement(i,
                                                                                                        txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText(), Integer.parseInt(txtNombre.getText()), nbrPart);

                                                                                                Ec.updateEvenement(e);
                                                                                                Pc.displayEmailParticipant(i);
                                                                                                MembreCRUD mc = new MembreCRUD();
                                                                                                SendMail Sm = new SendMail();

                                                                                                for (Participation p : Pc.displayEmailParticipant(i)) {

                                                                                                    try {
                                                                                                        String sq1 = "SELECT * FROM membre WHERE id_u=?";
                                                                                                        PreparedStatement prep = cnx.prepareStatement(sq1);
                                                                                                        prep.setInt(1, p.getId_u());
                                                                                                        ResultSet res = prep.executeQuery();

                                                                                                        if (res.next()) {
                                                                                                            String em = res.getString("email_u");
                                                                                                            Sm.envoiMailModification(em, "Nous vous informons que la date de l'evenement :" + controller1.getClickedEvent().getNom_e() + " auquel vous avez participé a été modifiée.La nouvelle date est fixée pour " + txtDate.getEditor().getText());
                                                                                                            System.out.println(em);
                                                                                                        } else {
                                                                                                            System.out.println("Aucun participant");

                                                                                                        }
                                                                                                    } catch (SQLException ex) {
                                                                                                        ex.printStackTrace();
                                                                                                    }
                                                                                                }

                                                                                                txtNom.setEditable(false);
                                                                                                txtLieu.setEditable(false);
                                                                                                txtDate.setEditable(false);
                                                                                                txtDescription.setEditable(false);
                                                                                                pathE.setEditable(false);
                                                                                                txtNombre.setEditable(false);
                                                                                                img.setVisible(true);
                                                                                                // idEditer.setVisible(true);

                                                                                                Stage stage = (Stage) fermer.getScene().getWindow();
                                                                                                stage.close();
                                                                                                controller1.actualiser();
                                                                                                validationSaisie.notifConfirm("ok", "Evenement Modifié");
                                                                           

                                                                                            }//   controller1.actualiser();
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @FXML
    void description(MouseEvent event) {

        txtDescription.setFocusColor(rgb(0, 150, 164));
        txtDescription.setUnFocusColor(rgb(77, 77, 77));
        txtDescription.setStyle("-fx-prompt-text-fill: #000000");

    }

    @FXML
    void lieu(MouseEvent event) {

        txtLieu.setFocusColor(rgb(0, 150, 164));
        txtLieu.setUnFocusColor(rgb(77, 77, 77));
        txtLieu.setStyle("-fx-prompt-text-fill: #000000");

    }

    @FXML
    void nom(MouseEvent event) {

        txtNom.setFocusColor(rgb(0, 150, 164));
        txtNom.setUnFocusColor(rgb(77, 77, 77));
        txtNom.setStyle("-fx-prompt-text-fill: #000000");

    }

    @FXML
    void nombre(MouseEvent event) {

        txtNombre.setFocusColor(rgb(0, 150, 164));
        txtNombre.setUnFocusColor(rgb(77, 77, 77));
        txtNombre.setStyle("-fx-prompt-text-fill: #000000");

    }

    @FXML
    void date(MouseEvent event) {

        txtDate.setDefaultColor(rgb(0, 150, 164));
        txtDate.setStyle("-fx-prompt-text-fill: #000000");

    }

    @FXML
    void retour(ActionEvent event) {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();
        controller1.actualiser();
    }

    @FXML
    void telecharger(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);

            pathE.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(txtLieu, AutoCompleteAdresse.getAdrGov());

        affichageEvent();
 
    }

}
