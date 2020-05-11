package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ReparateurCRUD;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.verifEmail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXPasswordField txtmotdepasse;
    @FXML
    private JFXPasswordField txtconfirmation;
    @FXML
    private JFXRadioButton chkhomme;
    @FXML
    private JFXRadioButton chkfemme;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXButton btninscription;
    @FXML
    private JFXDatePicker txtnaissance;
    @FXML
    private JFXTextField txttelephone;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private ToggleGroup sexegrp;
    @FXML
    private JFXButton btnimage;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView img;
    private Image image;
    @FXML
    private Button btnprofil;
    @FXML
    private Pane btnreparateur;
    @FXML
    private FontAwesomeIconView chkmotdepasi;
    @FXML
    private JFXTextField txtshowpass;
    @FXML
    private JFXTextField txtshowcpass;
    @FXML
    private TextField auto;
    @FXML
    private Label lblfaible;

    String photo = null;
    Connection cnx;
    private PreparedStatement prep;
    private ResultSet res;

    public InscriptionController() {
        cnx = ConnectionBD.getInstance().getCnx();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtmotdepasse.setVisible(true);
        txtconfirmation.setVisible(true);
        txtshowpass.setVisible(false);
        txtshowcpass.setVisible(false);
        txtnaissance.setValue(LocalDate.now());

        TextFields.bindAutoCompletion(txtAdresse, AutoCompleteAdresse.getAdrGov());
    }

// validation champs non vides
    public boolean validerchamps() {
        if ((txtNom.getText().isEmpty()) | (txtPrenom.getText().isEmpty()) | (txtAdresse.getText().isEmpty())
                | (txtemail.getText().isEmpty()) | (txttelephone.getText().isEmpty()) | (txtmotdepasse.getText().isEmpty())
                | (txtconfirmation.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Les champs nom ,prenom,adresse, email, telephone,mot de passe et confirmation doivent etre tout remplis svp");
            alert.show();
            return false;
        } else {
            return true;
        }
    }

//choidir une photo de profil
    @FXML
    void File(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);

            txtimage.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);

        }
    }

//voir mot de passe
    @FXML
    public void VisualiserMPI(MouseEvent event) {
        txtshowpass.setText(txtmotdepasse.getText());
        txtshowcpass.setText(txtconfirmation.getText());
        txtmotdepasse.setVisible(false);
        txtconfirmation.setVisible(false);
        txtshowpass.setVisible(true);
        txtshowcpass.setVisible(true);
    }

    //cacher mot de passe
    @FXML
    public void hideMPI(MouseEvent event) {
        txtmotdepasse.setVisible(true);
        txtconfirmation.setVisible(true);
        txtshowpass.setVisible(false);
        txtshowcpass.setVisible(false);
    }

    //choisir sexe
    public String sexeMembre() {
        String message = "";
        if (chkhomme.isSelected()) {
            message += chkhomme.getText();
        }
        if (chkfemme.isSelected()) {
            message += chkfemme.getText();
        }
        return message;
    }

    public boolean verifDate() {
        LocalDate local = LocalDate.now();
        LocalDate d = txtnaissance.getValue();
        System.out.println(d);
        System.out.println(local);
        System.out.println(d.compareTo(local));
        if (d.compareTo(local) > 0) {
            System.out.println("future");
            return false;
        } else {
            System.out.println("passé");

        }

        return true;
    }

    //verif password strength
    @FXML
    void passStrength(MouseEvent event) {

        if (InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) < 6) {
            lblfaible.setText("faible");
            lblfaible.setTextFill(Color.RED);
        } else if ((InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) > 6) && (InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) <= 6)) {
            lblfaible.setText("fmoyen");
            lblfaible.setTextFill(Color.ORANGE);
        } else {
            lblfaible.setText("fort");
            lblfaible.setTextFill(Color.GREEN);
        }
    }

    //inscription d un simple membre(verif email ds inscip membre seulmn ,rep pas encore) 
    @FXML
    public void InsertData1(ActionEvent event) throws NoSuchAlgorithmException {
        MembreCRUD mr = new MembreCRUD();
        ReparateurCRUD rc = new ReparateurCRUD();

        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String adresse = txtAdresse.getText();
        String email = txtemail.getText();
        Date datenais = java.sql.Date.valueOf(txtnaissance.getValue());
        String tel = txttelephone.getText();
        String sexe = sexeMembre();
        String motdepasse = InputValidation.HshPassword(txtmotdepasse.getText(), "MD5");
        String conmotdepasse = InputValidation.HshPassword(txtconfirmation.getText(), "MD5");
        String imge = txtimage.getText();

        Membre m = new Membre(nom, prenom, adresse, email, sexe, datenais, motdepasse, tel, imge);
        if (validerchamps() == true) {
            if (InputValidation.validTextField(txtNom.getText())) {
                Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre nom");
                alertNom.showAndWait();
            } else {
                if (InputValidation.validTextField(txtPrenom.getText())) {
                    Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez votre Prenom");
                    alertPrenom.showAndWait();
                } else {
                    if (!InputValidation.validEmail(txtemail.getText())) {
                        Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email valide");
                        alertEmail.showAndWait();
                    } else {//verif email vrai
                        if ((verifEmail.check(txtemail.getText())) == false) {
                            Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email existante");
                            alertEmail.showAndWait();
                        } else {
                            if (InputValidation.validPwd(txtmotdepasse.getText()) == 0) {
                                Alert alertnum = new InputValidation().getAlert("Mot de passe", "Saisissez un mot de passe valide");
                                alertnum.showAndWait();
                            } else {
                                if (InputValidation.PhoneNumber(txttelephone.getText()) == 0) {
                                    Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                                    alertnum.showAndWait();
                                } else {
                                    if (!(chkhomme.isSelected() | (chkfemme.isSelected()))) {
                                        Alert alertnum = new InputValidation().getAlert("sexe", "Saisissez votre sexe");
                                        alertnum.showAndWait();
                                    } else {
                                        if (verifDate() == false) {
                                            Alert alertnum = new InputValidation().getAlert("Date", "Saisissez une date valide");
                                            alertnum.showAndWait();
                                            txtnaissance.setValue(null);
                                        } else {
                                            if (mr.VerificationExistence(m) == true) {

                                                if (motdepasse.equals(conmotdepasse)) {
                                                    mr.AddUser(m);
                                                    txtNom.clear();
                                                    txtPrenom.clear();
                                                    txtnaissance.setValue(null);
                                                    txtAdresse.clear();
                                                    txtimage.clear();
                                                    txtemail.clear();
                                                    txtconfirmation.clear();
                                                    txtmotdepasse.clear();
                                                    txttelephone.clear();
                                                    chkhomme.setSelected(false);
                                                    chkfemme.setSelected(false);
                                                    System.out.println("utilisateur ajouté");
                                                    InputValidation.notificationsucces("Inscription", "Inscription réussite , soyez le bienvenu");

                                                } else {
                                                    Alert alertnum = new InputValidation().getAlert(" Mot de passe ", "verifier votre mot de passe");
                                                    alertnum.showAndWait();

                                                }
                                            } else {
                                                Alert alertnum = new InputValidation().getAlert(" Erreur d'inscription", "un compte est deja creer avec cette adresse");
                                                alertnum.showAndWait();

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

//page de profil
    @FXML
    public void ProfilPage(ActionEvent event) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("ProfilPage.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
