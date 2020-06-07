package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Membre;
import edu.baskel.services.EnvoiMail;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.rgb;
import javafx.scene.paint.Paint;
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
    @FXML
    private JFXButton btnConnexion;
    @FXML
    private FontAwesomeIconView chkCmotdepasi;

    private PreparedStatement prep;
    private ResultSet res;
    private String photo = null;
    private Image image;
    Connection cnx;
    EnvoiMail e = new EnvoiMail();

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
        txtimage.setVisible(false);

        TextFields.bindAutoCompletion(txtAdresse, AutoCompleteAdresse.getAdrGov());
    }

// validation champs non vides
    public boolean validerchamps() {
        String dateV = txtnaissance.getEditor().getText();
        if ((txtNom.getText().isEmpty()) && (txtPrenom.getText().isEmpty()) && (txtAdresse.getText().isEmpty())
                && (txtemail.getText().isEmpty()) && (txttelephone.getText().isEmpty()) && (txtmotdepasse.getText().isEmpty())
                && (txtconfirmation.getText().isEmpty()) && (sexeMembre().equals(""))) {
            InputValidation.notificationError("Erreur d'ajout", "Les champs doivent etre tout remplis svp.");
            txtNom.setFocusColor(rgb(255, 0, 0));
            txtNom.setUnFocusColor(rgb(255, 0, 0));
            txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtPrenom.setFocusColor(rgb(255, 0, 0));
            txtPrenom.setUnFocusColor(rgb(255, 0, 0));
            txtPrenom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtAdresse.setFocusColor(rgb(255, 0, 0));
            txtAdresse.setUnFocusColor(rgb(255, 0, 0));
            txtAdresse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtemail.setFocusColor(rgb(255, 0, 0));
            txtemail.setUnFocusColor(rgb(255, 0, 0));
            txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
            txttelephone.setFocusColor(rgb(255, 0, 0));
            txttelephone.setUnFocusColor(rgb(255, 0, 0));
            txttelephone.setStyle("-fx-prompt-text-fill: #C4151C");
            txtmotdepasse.setFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtconfirmation.setFocusColor(rgb(255, 0, 0));
            txtconfirmation.setUnFocusColor(rgb(255, 0, 0));
            txtconfirmation.setStyle("-fx-prompt-text-fill: #C4151C");
            chkhomme.setStyle("-fx-text-fill: #C4151C");
            chkhomme.setSelectedColor(rgb(255, 0, 0));
            chkhomme.setUnSelectedColor(rgb(255, 0, 0));
            chkfemme.setStyle("-fx-text-fill: #C4151C");
            chkfemme.setSelectedColor(rgb(255, 0, 0));
            chkfemme.setUnSelectedColor(rgb(255, 0, 0));
            return false;
        } else if ((txtNom.getText().isEmpty()) | (txtPrenom.getText().isEmpty()) | (txtAdresse.getText().isEmpty())
                | (txtemail.getText().isEmpty()) | (txttelephone.getText().isEmpty()) | (txtmotdepasse.getText().isEmpty())
                | (txtconfirmation.getText().isEmpty()) | (sexeMembre().equals(""))) {
            InputValidation.notificationError("Erreur d'ajout", "Les champs doivent etre tout remplis svp.");

            txtNom.setFocusColor(rgb(255, 0, 0));
            txtNom.setUnFocusColor(rgb(255, 0, 0));
            txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtPrenom.setFocusColor(rgb(255, 0, 0));
            txtPrenom.setUnFocusColor(rgb(255, 0, 0));
            txtPrenom.setStyle("-fx-prompt-text-fill: #C4151C");
            txtAdresse.setFocusColor(rgb(255, 0, 0));
            txtAdresse.setUnFocusColor(rgb(255, 0, 0));
            txtAdresse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtemail.setFocusColor(rgb(255, 0, 0));
            txtemail.setUnFocusColor(rgb(255, 0, 0));
            txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
            txttelephone.setFocusColor(rgb(255, 0, 0));
            txttelephone.setUnFocusColor(rgb(255, 0, 0));
            txttelephone.setStyle("-fx-prompt-text-fill: #C4151C");
            txtmotdepasse.setFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
            txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
            txtconfirmation.setFocusColor(rgb(255, 0, 0));
            txtconfirmation.setUnFocusColor(rgb(255, 0, 0));
            txtconfirmation.setStyle("-fx-prompt-text-fill: #C4151C");
            chkhomme.setStyle("-fx-text-fill: #C4151C");
            chkhomme.setSelectedColor(rgb(255, 0, 0));
            chkhomme.setUnSelectedColor(rgb(255, 0, 0));
            chkfemme.setStyle("-fx-text-fill: #C4151C");
            chkfemme.setSelectedColor(rgb(255, 0, 0));
            chkfemme.setUnSelectedColor(rgb(255, 0, 0));
            Def();
            return false;

        } else {
            return true;
        }
    }

//choisir une photo de profil
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
        txtmotdepasse.setVisible(false);
        txtshowpass.setVisible(true);
    }

    //cacher mot de passe
    @FXML
    public void hideMPI(MouseEvent event) {
        txtmotdepasse.setVisible(true);
        txtshowpass.setVisible(false);
    }

    //visualiser cmp
    @FXML
    public void VisualiserCMP(MouseEvent event) {
        txtshowcpass.setText(txtconfirmation.getText());
        txtconfirmation.setVisible(false);
        txtshowcpass.setVisible(true);

    }

    //cacher Cmp
    @FXML
    public void hideCMP(MouseEvent event) {
        txtconfirmation.setVisible(true);
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

    //verif date 
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
        if (!txtmotdepasse.getText().isEmpty()) {
            if (InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) < 6) {
                lblfaible.setText("Faible");
                lblfaible.setTextFill(Color.RED);
            } else if ((InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) > 6) && (InputValidation.calculatePasswordStrength(txtmotdepasse.getText()) <= 8)) {
                lblfaible.setText("Moyen");
                lblfaible.setTextFill(Color.ORANGE);
            } else {
                lblfaible.setText("Fort");
                lblfaible.setTextFill(Color.GREEN);
            }
        } else {
            System.out.println("Pas de mot de passe");
        }
    }

    //inscription d un simple membre(verif email ds inscip membre seulmn ,rep pas encore) 
    @FXML
    public void InsertData1(ActionEvent event) throws NoSuchAlgorithmException, IOException {
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

        Membre m = new Membre(0,nom, prenom, adresse, email, sexe, datenais, motdepasse, tel, imge,"U");
        if (validerchamps() == true) {
            if (InputValidation.validTextField(txtNom.getText())) {
                InputValidation.notificationError("Nom", "Saisissez votre nom");
                txtNom.setFocusColor(rgb(255, 0, 0));
                txtNom.setUnFocusColor(rgb(255, 0, 0));
                txtNom.setStyle("-fx-prompt-text-fill: #C4151C");
                txtNom.setStyle("-fx-text-inner-color: red;");
                // Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre nom");
                //alertNom.showAndWait();
            } else {
                if (InputValidation.validTextField(txtPrenom.getText())) {
                    InputValidation.notificationError("Prenom", "Saisissez votre prenom");
                    txtPrenom.setFocusColor(rgb(255, 0, 0));
                    txtPrenom.setUnFocusColor(rgb(255, 0, 0));
                    txtPrenom.setStyle("-fx-prompt-text-fill: #C4151C");
                    txtPrenom.setStyle("-fx-text-inner-color: red;");
                    //Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez votre Prenom");
                    //alertPrenom.showAndWait();
                } else {
                    if (!InputValidation.validEmail(txtemail.getText())) {
                        InputValidation.notificationError("Email", "Saisissez une adresse email valide");
                        txtemail.setFocusColor(rgb(255, 0, 0));
                        txtemail.setUnFocusColor(rgb(255, 0, 0));
                        txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
                        txtemail.setStyle("-fx-text-inner-color: red;");

                        //Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email valide");
                        //alertEmail.showAndWait();
                    } else {
                        if (InputValidation.validPwd(txtmotdepasse.getText()) == 0) {
                            InputValidation.notificationError("Mot de passe", "Saisissez un mot de passe valide");
                            txtmotdepasse.setFocusColor(rgb(255, 0, 0));
                            txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
                            txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
                            txtmotdepasse.setStyle("-fx-text-inner-color: red;");

                            //Alert alertnum = new InputValidation().getAlert("Mot de passe", "Saisissez un mot de passe valide");
                            // alertnum.showAndWait();
                        } else {
                            if (InputValidation.PhoneNumber(txttelephone.getText()) == 0) {
                                InputValidation.notificationError("Numero Telephone", "Saisissez un numero de telephone valide");
                                txttelephone.setFocusColor(rgb(255, 0, 0));
                                txttelephone.setUnFocusColor(rgb(255, 0, 0));
                                txttelephone.setStyle("-fx-prompt-text-fill: #C4151C");
                                txttelephone.setStyle("-fx-text-inner-color: red;");
                                //Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                                //alertnum.showAndWait();
                            } else {
                                if (!(chkhomme.isSelected() | (chkfemme.isSelected()))) {
                                    InputValidation.notificationError("sexe", "Saisissez votre sexe");
                                    chkhomme.setStyle("-fx-text-fill: #C4151C");
                                    chkhomme.setSelectedColor(rgb(255, 0, 0));
                                    chkhomme.setUnSelectedColor(rgb(255, 0, 0));
                                    chkfemme.setStyle("-fx-text-fill: #C4151C");
                                    chkfemme.setSelectedColor(rgb(255, 0, 0));
                                    chkfemme.setUnSelectedColor(rgb(255, 0, 0));

                                    //Alert alertnum = new InputValidation().getAlert("sexe", "Saisissez votre sexe");
                                    //alertnum.showAndWait();
                                } else {
                                    if (verifDate() == false) {
                                        InputValidation.notificationError("Date", "Saisissez une date valide");
                                        txtnaissance.setDefaultColor(Color.RED);

                                        //Alert alertnum = new InputValidation().getAlert("Date", "Saisissez une date valide");
                                        //alertnum.showAndWait();
                                        //txtnaissance.setValue(null);
                                    } else {//verif email vrai
                                        if ((verifEmail.nb(txtemail.getText())) == false) {
                                            InputValidation.notificationError("Email", "Saisissez une adresse email existante");
                                            txtemail.setFocusColor(rgb(255, 0, 0));
                                            txtemail.setUnFocusColor(rgb(255, 0, 0));
                                            txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
                                            txtemail.setStyle("-fx-text-inner-color: red;");

                                            //Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email existante");
                                            //alertEmail.showAndWait();
                                        } else {
                                            if (mr.VerificationExistence(m) == true) {

                                                if (motdepasse.equals(conmotdepasse)) {
                                                    mr.AddUser(m);
                                                    e.envoyerMailHistorique(txtemail.getText(), "Bienvenue chez Baskel.tn");

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
                                                    Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                                                    Scene redirection_scene = new Scene(redirection_parent);
                                                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                    app_stage.setScene(redirection_scene);
                                                    app_stage.show();

                                                } else {
                                                    InputValidation.notificationError(" Mot de passe ", "verifier votre mot de passe");
                                                    txtmotdepasse.setFocusColor(rgb(255, 0, 0));
                                                    txtmotdepasse.setUnFocusColor(rgb(255, 0, 0));
                                                    txtmotdepasse.setStyle("-fx-prompt-text-fill: #C4151C");
                                                    txtmotdepasse.setStyle("-fx-text-inner-color: red;");
                                                    txtconfirmation.setFocusColor(rgb(255, 0, 0));
                                                    txtconfirmation.setUnFocusColor(rgb(255, 0, 0));
                                                    txtconfirmation.setStyle("-fx-prompt-text-fill: #C4151C");
                                                    txtconfirmation.setStyle("-fx-text-inner-color: red;");
                                                    // Alert alertnum = new InputValidation().getAlert(" Mot de passe ", "verifier votre mot de passe");
                                                    //alertnum.showAndWait();
                                                }
                                            } else {
                                                InputValidation.notificationError(" Erreur d'inscription", "un compte est deja creer avec cette adresse");
                                                txtemail.setUnFocusColor(rgb(255, 0, 0));
                                                txtemail.setStyle("-fx-prompt-text-fill: #C4151C");
                                                txtemail.setStyle("-fx-text-inner-color: red;");
                                                //Alert alertnum = new InputValidation().getAlert(" Erreur d'inscription", "un compte est deja creer avec cette adresse");
                                                //alertnum.showAndWait();
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

//page de Connexion
    @FXML
    public void SidentifierPage(ActionEvent event) {
        try {
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void DefaultThemeNom(MouseEvent event) {
        txtNom.setStyle("");
        txtNom.setFocusColor(Paint.valueOf("#0096a4"));
        txtNom.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemePrenom(MouseEvent event) {
        txtPrenom.setStyle("");
        txtPrenom.setFocusColor(Paint.valueOf("#0096a4"));
        txtPrenom.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeEmail(MouseEvent event) {
        txtemail.setStyle("");
        txtemail.setFocusColor(Paint.valueOf("#0096a4"));
        txtemail.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeTele(MouseEvent event) {
        txttelephone.setStyle("");
        txttelephone.setFocusColor(Paint.valueOf("#0096a4"));
        txttelephone.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeAdresse(MouseEvent event) {
        txtAdresse.setStyle("");
        txtAdresse.setFocusColor(Paint.valueOf("#0096a4"));
        txtAdresse.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeMotPass(MouseEvent event) {
        txtmotdepasse.setStyle("");
        txtmotdepasse.setFocusColor(Paint.valueOf("#0096a4"));
        txtmotdepasse.setUnFocusColor(Paint.valueOf("#0096a4"));
        txtconfirmation.setStyle("");
        txtconfirmation.setFocusColor(Paint.valueOf("#0096a4"));
        txtconfirmation.setUnFocusColor(Paint.valueOf("#0096a4"));
    }

    @FXML
    public void DefaultThemeSexeH(MouseEvent event) {
        chkhomme.setStyle("-fx-text-fill: #000000");
        chkhomme.setSelectedColor(rgb(0, 150, 164));
        chkhomme.setUnSelectedColor(rgb(0, 0, 0));
        chkfemme.setStyle("-fx-text-fill: #000000");
        chkfemme.setSelectedColor(rgb(0, 0, 0));
        chkfemme.setUnSelectedColor(rgb(0, 0, 0));
    }

    @FXML
    public void DefaultThemeSexeF(MouseEvent event) {
        chkfemme.setStyle("-fx-text-fill: #000000");
        chkfemme.setSelectedColor(rgb(0, 150, 164));
        chkfemme.setUnSelectedColor(rgb(0, 0, 0));
        chkhomme.setStyle("-fx-text-fill: #000000");
        chkhomme.setSelectedColor(rgb(0, 0, 0));
        chkhomme.setUnSelectedColor(rgb(0, 0, 0));
    }

    public void Def() {
        if (!txtNom.getText().isEmpty()) {
            txtNom.setStyle("");
            txtNom.setFocusColor(Paint.valueOf("#0096a4"));
            txtNom.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtPrenom.getText().isEmpty()) {
            txtPrenom.setStyle("");
            txtPrenom.setFocusColor(Paint.valueOf("#0096a4"));
            txtPrenom.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtemail.getText().isEmpty()) {
            txtemail.setStyle("");
            txtemail.setFocusColor(Paint.valueOf("#0096a4"));
            txtemail.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtAdresse.getText().isEmpty()) {
            txtAdresse.setStyle("");
            txtAdresse.setFocusColor(Paint.valueOf("#0096a4"));
            txtAdresse.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txttelephone.getText().isEmpty()) {
            txttelephone.setStyle("");
            txttelephone.setFocusColor(Paint.valueOf("#0096a4"));
            txttelephone.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtmotdepasse.getText().isEmpty()) {
            txtmotdepasse.setStyle("");
            txtmotdepasse.setFocusColor(Paint.valueOf("#0096a4"));
            txtmotdepasse.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (!txtconfirmation.getText().isEmpty()) {
            txtconfirmation.setStyle("");
            txtconfirmation.setFocusColor(Paint.valueOf("#0096a4"));
            txtconfirmation.setUnFocusColor(Paint.valueOf("#0096a4"));
        }
        if (chkhomme.isSelected()) {
            chkhomme.setStyle("-fx-text-fill: #000000");
            chkhomme.setSelectedColor(rgb(0, 150, 164));
            chkhomme.setUnSelectedColor(rgb(0, 0, 0));
            chkfemme.setStyle("-fx-text-fill: #000000");
            chkfemme.setSelectedColor(rgb(0, 0, 0));
            chkfemme.setUnSelectedColor(rgb(0, 0, 0));
        }
        if (chkfemme.isSelected()) {
            chkfemme.setStyle("-fx-text-fill: #000000");
            chkfemme.setSelectedColor(rgb(0, 150, 164));
            chkfemme.setUnSelectedColor(rgb(0, 0, 0));
            chkhomme.setStyle("-fx-text-fill: #000000");
            chkhomme.setSelectedColor(rgb(0, 0, 0));
            chkhomme.setUnSelectedColor(rgb(0, 0, 0));
        }
    }
}
