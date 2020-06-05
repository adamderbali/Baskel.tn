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
import edu.baskel.utils.AutoCompleteAdresse;

import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import edu.baskel.utils.validationSaisie;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.rgb;
import org.controlsfx.control.textfield.TextFields;

public class Ajouter_EvenementController implements Initializable {

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
    Image image;
    @FXML
    private JFXTextField txtNombre;

    @FXML
    private TextField pathE;

    @FXML
    private JFXButton fileChoose;

    @FXML
    private ImageView img;

    @FXML
    private JFXButton nom;

    @FXML
    private JFXButton lieu;
    @FXML
    private JFXButton idRetour;

    @FXML
    private JFXButton description;

    @FXML
    private JFXButton date;

    private Stage thisStage;

    Membre ml = SessionInfo.getLoggedM();

    private final List_Event_Add_ParticipationController controller1;

    public Ajouter_EvenementController(List_Event_Add_ParticipationController controller1) {
        this.controller1 = controller1;
        thisStage = new Stage();
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajouter_Evenement.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Ajout evenement");

            // Setup the window/stage
            //thisStage.setTitle("Passing Controllers Example - Layout2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }


    /* Ajout evenement*/
    @FXML
    void ajouterEvenement(ActionEvent event) {
       
        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate.getEditor().getText();
        /* test sur les champs vides ou non*/
        if ((txtNom.getText().isEmpty()) && (txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty()) && (txtDescription.getText().isEmpty())) {
            validationSaisie.notifInfo("Echec", "Tous les champs doivent etre saisis");

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

            /*  nom.setTextFill(rgb(255, 0, 0));
            lieu.setTextFill(rgb(255, 0, 0));
            description.setTextFill(rgb(255, 0, 0));
            datee.setTextFill(rgb(255, 0, 0));*/
        } else {
            if ((txtDescription.getText().isEmpty()) && (txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                validationSaisie.notifInfo("Echec", "Saisir le nom,le lieu et la date de l'evenement");
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
                    validationSaisie.notifInfo("Echec", "Saisir le nom,la description et le lieu de l'evenement");
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
                        validationSaisie.notifInfo("Echec", "Saisir le nom,le lieu et la date de l'evenement");
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
                            validationSaisie.notifInfo("Echec", "Saisir le nom,la description et la date de l'evenement");
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
                                validationSaisie.notifInfo("Echec", "Saisir le nom et le lieu de l'evenement");
                                txtNom.setFocusColor(rgb(255, 0, 0));
                                txtNom.setUnFocusColor(rgb(255, 0, 0));
                                txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                                txtLieu.setFocusColor(rgb(255, 0, 0));
                                txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");

                            } else {
                                if ((txtNom.getText().isEmpty()) && (txtDescription.getText().isEmpty())) {
                                    validationSaisie.notifInfo("Echec", "Saisir le nom et la description de l'evenement");
                                    txtNom.setFocusColor(rgb(255, 0, 0));
                                    txtNom.setUnFocusColor(rgb(255, 0, 0));
                                    txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                                    txtDescription.setFocusColor(rgb(255, 0, 0));
                                    txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                    txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");

                                } else {

                                    if ((txtNom.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                                        validationSaisie.notifInfo("Echec", "Saisir le nom et la date de l'evenement");
                                        txtNom.setFocusColor(rgb(255, 0, 0));
                                        txtNom.setUnFocusColor(rgb(255, 0, 0));
                                        txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                                        txtDate.setDefaultColor(Color.RED);
                                        txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                    } else {

                                        if ((txtLieu.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                                            validationSaisie.notifInfo("Echec", "Saisir le lieu et la date de l'evenement");

                                            txtLieu.setFocusColor(rgb(255, 0, 0));
                                            txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                            txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                                            txtDate.setDefaultColor(Color.RED);
                                            txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                        } else {
                                            if ((txtLieu.getText().isEmpty()) && (txtDescription.getText().isEmpty())) {
                                                validationSaisie.notifInfo("Echec", "Saisir le lieu et la description de l'evenement");

                                                txtLieu.setFocusColor(rgb(255, 0, 0));
                                                txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                                txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");
                                                txtDescription.setFocusColor(rgb(255, 0, 0));
                                                txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                                txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");

                                            } else {

                                                if ((txtDescription.getText().isEmpty()) && (txtDate.getEditor().getText().isEmpty())) {
                                                    validationSaisie.notifInfo("Echec", "Saisir la description et la date de l'evenement");
                                                    txtNom.setFocusColor(rgb(255, 0, 0));

                                                    txtDescription.setFocusColor(rgb(255, 0, 0));
                                                    txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                                    txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");
                                                    txtDate.setDefaultColor(Color.RED);
                                                    txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                                } else {
                                                    if (validationSaisie.validTextField(txtNom.getText())) {
                                                        validationSaisie.notifInfo("Echec", "Saisir le nom de l'evenement");
                                                        txtNom.setFocusColor(rgb(255, 0, 0));
                                                        txtNom.setUnFocusColor(rgb(255, 0, 0));
                                                        txtNom.setStyle("-fx-prompt-text-fill: #C4151C");

                                                    } else {
                                                        if (validationSaisie.validTextField(txtLieu.getText())) {

                                                            System.out.println("------------------");
                                                            validationSaisie.notifInfo("Echec", "Saisir le lieu de l'evenement ");

                                                            txtLieu.setFocusColor(rgb(255, 0, 0));
                                                            txtLieu.setUnFocusColor(rgb(255, 0, 0));
                                                            txtLieu.setStyle("-fx-prompt-text-fill: #C4151C");

                                                        } else {

                                                            if (validationSaisie.validTextField(txtDescription.getText())) {

                                                                System.out.println("------------------");
                                                                validationSaisie.notifInfo("Echec", "Saisir la description de l'evenement");
                                                                txtNom.setFocusColor(rgb(255, 0, 0));

                                                                txtDescription.setFocusColor(rgb(255, 0, 0));
                                                                txtDescription.setUnFocusColor(rgb(255, 0, 0));
                                                                txtDescription.setStyle("-fx-prompt-text-fill: #C4151C");

                                                            } else {
                                                                if ((validationSaisie.validTextField(txtDate.getEditor().getText()))) {
                                                                    System.out.println("------------------");
                                                                    validationSaisie.notifInfo("Echec", "Saisir la date de l'evenement");
                                                                    txtNom.setFocusColor(rgb(255, 0, 0));

                                                                    txtDate.setDefaultColor(Color.RED);
                                                                    txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                                                } else {
                                                                    if ((validationSaisie.validDate(txtDate.getEditor().getText())) == true) {
                                                                        System.out.println("------------------");
                                                                        validationSaisie.notifInfo("Date", "La date saisie doit etre au dela de" + date_system);
                                                                        txtDate.setDefaultColor(rgb(255, 0, 0));
                                                                        txtDate.setStyle("-fx-prompt-text-fill: #C4151C");
                                                                    } else {
                                                                        EvenementCRUD Ev = new EvenementCRUD();
                                                                        if (Ev.verifierNom(txtNom.getText()) == true) {
                                                                            System.out.println("+++++++++za3ma mch 9a3ed yodkhel lehna jemla???" + txtNom.getText());
                                                                            txtNom.setFocusColor(rgb(255, 0, 0));
                                                                            txtNom.setUnFocusColor(rgb(255, 0, 0));
                                                                            txtNom.setStyle("-fx-text-fill: #C4151C");

                                                                            validationSaisie.notifInfo("Erreur", "Le nom de l'evenement existe deja");
                                                                        } else {
                                                                            if(txtNombre.getText().equals("")){
                                                                               EvenementCRUD Ec = new EvenementCRUD();
                                                                            System.out.println("za3ma nombre chnia ya3ti trah:"+txtNombre.getText());
                                                                            Evenement e = new Evenement(0, txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.
                                                                                    getText(), pathE.getText(), ml.getId_u(),0);
                                                                            Ec.ajouterEvenement(e); 
                                                                            }
                                                                            else{
                                                                            EvenementCRUD Ec = new EvenementCRUD();
                                                                            System.out.println("za3ma nombre chnia ya3ti trah:"+txtNombre.getText());
                                                                            Evenement e = new Evenement(0, txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.
                                                                                    getText(), pathE.getText(), ml.getId_u(), Integer.parseInt(txtNombre.getText()));
                                                                            Ec.ajouterEvenement(e);}
                                                                            txtNom.clear();
                                                                            txtLieu.clear();
                                                                            txtDate.setValue(null);
                                                                            txtDescription.clear();
                                                                            pathE.clear();
                                                                            txtNombre.clear();
                                                                            img.setVisible(false);
                                                                            Stage stage = (Stage) idRetour.getScene().getWindow();
                                                                            stage.close();

                                                                            controller1.actualiser();
                                                                            validationSaisie.notifConfirm("ok", "Evenement ajouté");
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
    void date(MouseEvent event) {

        txtDate.setDefaultColor(rgb(0, 150, 164));
        txtDate.setStyle("-fx-prompt-text-fill: #000000");

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

    @FXML
    void retour(ActionEvent event) {
        Stage stage = (Stage) idRetour.getScene().getWindow();
        stage.close();
        //  controller1.actualiser();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(txtLieu, AutoCompleteAdresse.getAdrGov());

    }

}
