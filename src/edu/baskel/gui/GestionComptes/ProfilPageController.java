package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;
import edu.baskel.utils.verifEmail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
public class ProfilPageController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField profilnom;
    @FXML
    private JFXTextField profilprenom;
    @FXML
    private JFXTextField profilmail;
    @FXML
    private JFXTextField profiladresse;
    @FXML
    private JFXTextField profildate;
    @FXML
    private JFXTextField profilteleph;
    @FXML
    private JFXTextField txtshow;
    @FXML
    private JFXDatePicker nvdate;
    @FXML
    private Button btnmodifier1;
    @FXML
    private Button btnconfirmer1;
    @FXML
    private ImageView imagev, cc;
    @FXML
    private Button btnimage;
    @FXML
    private TextField thximage;
    @FXML
    private JFXPasswordField nvpass;
    @FXML
    private JFXTextField txtshowc;
    @FXML
    private Label lblprofil;
    @FXML
    private JFXPasswordField cnvpass;
    @FXML
    private JFXPasswordField actuelPass;
    @FXML
    private JFXTextField txtshowActuel;
    @FXML
    private FontAwesomeIconView chkmotdepasseActuel;
    @FXML
    private JFXButton btnchgPass;
    @FXML
    private Pane paneNomProfil;
    @FXML
    private Button btnGenerale;
    @FXML
    private Button btnSécurité;
    @FXML
    private Pane PaneMotpass;
    @FXML
    private Pane panePrincipale;
    @FXML
    private Label lblfaible;
    @FXML
    private FontAwesomeIconView chkmotdepasse;
    @FXML
    private FontAwesomeIconView chkmotdepasse2;
    @FXML
    private Button btnSupprimer;
    @FXML
    private JFXTextField txtEmailVerif;
    /*@FXML
    private ImageView Logout;
    @FXML
    private ImageView exit;*/

    private String photo = null;
    private File file;
    private Image image;
    Connection cnx;
    Membre l = SessionInfo.loggedM;
    MembreCRUD mrc = new MembreCRUD();

    //afficher la photo de profil
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panePrincipale.setVisible(true);
        PaneMotpass.setVisible(false);
        actuelPass.setVisible(true);
        txtshowActuel.setVisible(false);
        txtshow.setVisible(false);
        txtshowc.setVisible(false);
        profildate.setVisible(false);
        thximage.setVisible(false);
        txtEmailVerif.setVisible(false);
        informationMembre();
        TextFields.bindAutoCompletion(profiladresse, AutoCompleteAdresse.getAdrGov());
        //Photo de profil

        if (!l.getImage_u().equals("")) {
            Image imagelog;
            imagelog = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + l.getImage_u());
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = imagev.snapshot(parameters, null);
            imagev.setClip(null);
            imagev.setEffect(new DropShadow(20, Color.BLACK));
            imagev.setImage(imagelog);

        }
        if (l.getImage_u().equals("")) {
            Image defaultt;
            if (l.getSexe_u().equals("Femme")) {
                defaultt = new Image("images\\femme.png");
                imagev.setImage(defaultt);
            }
            if (l.getSexe_u().equals("Homme")) {
                defaultt = new Image("images\\homme.png");
                imagev.setImage(defaultt);
            }
        }
    }

    //connexion
    public ProfilPageController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    //information du membre a afficher ds le profil
    public void informationMembre() {
        lblprofil.setText(l.getNom_u() + " " + l.getPrenom_u());
        profilnom.setText(l.getNom_u());
        profilprenom.setText(l.getPrenom_u());
        profilmail.setText(l.getEmail_u());
        profiladresse.setText(l.getAdresse_u());
        nvdate.setValue(LocalDate.parse(l.getDate_u().toString()));
        profilteleph.setText(l.getNum_tel_u());
        thximage.setText(l.getImage_u());
        txtEmailVerif.setText(l.getEmail_u());
    }

    //charger les information
    @FXML
    public void chagerInfos2(ActionEvent event) {
        profiladresse.setEditable(true);
        profildate.setEditable(true);
        profilmail.setEditable(true);
        profilnom.setEditable(true);
        profilprenom.setEditable(true);
        profilteleph.setEditable(true);
        profildate.setEditable(true);
        nvdate.setEditable(true);

    }

    //voir mp
    @FXML
    public void VisualiserMP(MouseEvent event) {
        txtshow.setText(nvpass.getText());
        nvpass.setVisible(false);
        txtshow.setVisible(true);
    }

    //cacher mp
    @FXML
    public void hideMP(MouseEvent event) {
        nvpass.setVisible(true);
        txtshow.setVisible(false);
    }

    //voir cmp
    @FXML
    public void VisualiserCMP(MouseEvent event) {
        txtshowc.setText(cnvpass.getText());
        cnvpass.setVisible(false);
        txtshowc.setVisible(true);
    }

    //cacher cmp
    @FXML
    public void hideCMP(MouseEvent event) {
        cnvpass.setVisible(true);
        txtshowc.setVisible(false);
    }

    //voir mp actuem
    @FXML
    void VisualiserMPActuel(MouseEvent event) {
        txtshowActuel.setText(actuelPass.getText());
        txtshowActuel.setVisible(true);
        actuelPass.setVisible(false);
    }

    //cacher mp actuel
    @FXML
    void hideMPActuel(MouseEvent event) {
        txtshowActuel.setVisible(false);
        actuelPass.setVisible(true);
    }

    //affichage onglet infos generals
    @FXML
    void afficherOngletGenrale(ActionEvent event) {
        panePrincipale.setVisible(true);
        PaneMotpass.setVisible(false);
        imagev.setVisible(true);
        btnimage.setVisible(true);
        informationMembre();
    }

    //affichage onglet de securité
    @FXML
    void afficherOngletSecurité(ActionEvent event) {
        actuelPass.clear();
        nvpass.clear();
        cnvpass.clear();
        panePrincipale.setVisible(false);
        PaneMotpass.setVisible(true);
        imagev.setVisible(false);
        btnimage.setVisible(false);
    }

    //changer la photo de profil
    @FXML
    public void filephoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        Image imagelog;
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            thximage.setText(photo);
            InputValidation u = new InputValidation();
            System.out.println(photo);
            String photo1;
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            u.CopyImage(photo1, file.toPath().toString());
            imagelog = new Image("file:/" + photo1);
            imagev.setImage(imagelog);
        }
    }

    //valider les champs modifier
    public boolean validerchamps2() {
        String nvd = nvdate.getValue().toString();
        if ((profilnom.getText().isEmpty()) | (profilprenom.getText().isEmpty()) | (profiladresse.getText().isEmpty())
                | (profilmail.getText().isEmpty()) | (profilteleph.getText().isEmpty()) | nvd == null) {
            InputValidation.notificationError("Erreur d'ajout", "Les champs doivent etre tout remplis svp.");

            return false;
        } else {
            return true;
        }
    }

    //verification date
    public boolean verifDate() {
        LocalDate local = LocalDate.now();
        LocalDate d = nvdate.getValue();
        if (d.compareTo(local) > 0) {
            System.out.println("future");
            return false;
        } else {
            System.out.println("passé");

        }

        return true;
    }

    //confirmer les modification et les enregistrés
    @FXML
    public void confirmermodif(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        Date nvd = java.sql.Date.valueOf(nvdate.getValue());
        if (validerchamps2() == true) {
            if (InputValidation.validTextField(profilnom.getText())) {
                InputValidation.notificationError("Nom", "Saisissez votre nom");
            } else {
                if (InputValidation.validTextField(profilprenom.getText())) {
                    InputValidation.notificationError("Prenom", "Saisissez votre Prenom");
                } else {
                    if (!InputValidation.validEmail(profilmail.getText())) {
                        InputValidation.notificationError("Email", "Saisissez une adresse email valide");
                    } else {
                        if (InputValidation.PhoneNumber(profilteleph.getText()) == 0) {
                            InputValidation.notificationError("Numero Telephone", "Saisissez un numero de telephone valide");
                        } else {
                            if (verifDate() == false) {
                                InputValidation.notificationError("Date", "Saisissez une date valide");
                            } else {
                                if ((verifEmail.nb(profilmail.getText())) == false) {
                                    InputValidation.notificationError("Email", "Saisissez une adresse email existante");
                                } else {
                                    if (((!profilmail.getText().equals(txtEmailVerif.getText())) && (mrc.VerificationExistencePArEmail(profilmail.getText()) == false))) {
                                        InputValidation.notificationError("Email", "Un compte est deja créer avec cette adresse email");
                                    } else {

                                        Membre m1 = new Membre(profilnom.getText(), profilprenom.getText(), profiladresse.getText(),
                                                profilmail.getText(), l.getSexe_u(), nvd, profilteleph.getText(), thximage.getText());
                                        m1.setType_u("U");
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Confirmation ");
                                        alert.setHeaderText("Enregistrer les modifications  !?");
                                        alert.setContentText("OK?");
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == ButtonType.OK) {
                                            m1.setId_u(iduser);
                                            mrc.updateMembre(m1);
                                            SessionInfo.loggedM = m1;
                                             informationMembre();
                                            InputValidation.notificationsucces("Modifications", "Modification réussite");

                                        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                                        Scene redirection_scene = new Scene(redirection_parent);
                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        app_stage.setScene(redirection_scene);
                                        app_stage.setTitle("Acceuil");
                                        app_stage.show();
                                        } else {
                                            System.out.println("Rien");
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

    //password strenght
    @FXML
    void passStrength(MouseEvent event) {
        if (!nvpass.getText().isEmpty()) {
            if (InputValidation.calculatePasswordStrength(nvpass.getText()) < 6) {
                lblfaible.setText("faible");
                lblfaible.setTextFill(Color.RED);
            } else if ((InputValidation.calculatePasswordStrength(nvpass.getText()) > 6) && (InputValidation.calculatePasswordStrength(nvpass.getText()) <= 8)) {
                lblfaible.setText("moyen");
                lblfaible.setTextFill(Color.ORANGE);
            } else {
                lblfaible.setText("fort");
                lblfaible.setTextFill(Color.GREEN);
            }
        } else {
            System.out.println("Pas de mot de passe");
        }
    }

    //modifier mot de passe
    @FXML
    void ChangerPass(ActionEvent event) throws NoSuchAlgorithmException {
        if (InputValidation.md5(actuelPass.getText()).equals(l.getMot_passe_u())) {
            if (InputValidation.validPwd(nvpass.getText()) == 0) {
                InputValidation.notificationError("Mot de passe", "Saisissez un mot de passe valide.");

            } else {

                if ((InputValidation.md5(nvpass.getText())).equals(InputValidation.md5(cnvpass.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation ");
                    alert.setHeaderText("Etes vous sur de vouloir enregistrer les modifications  !?");
                    alert.setContentText("OK?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        mrc.changerMP(l.getEmail_u(), InputValidation.md5(nvpass.getText()));
                        actuelPass.clear();
                        nvpass.clear();
                        cnvpass.clear();
                        lblfaible.setText("");
                        InputValidation.notificationsucces("Mot de passe", "Mot de passe modifier avec succées");
                    } else {
                        System.out.println("Rien");
                    }

                } else {
                    InputValidation.notificationError("Mot de passe", "Verifier le mot de passe entré");

                }
            }
        } else {
            InputValidation.notificationError("Mot de passe", "Verifier votre mot de passe.");

        }
    }

    //supprimer son compte
    @FXML
    void supprimerCompte(ActionEvent event) throws IOException {
        MembreCRUD mr1 = new MembreCRUD();
        Membre l = SessionInfo.loggedM;
       
                        mr1.supprimerMembre(l.getId_u());

            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setTitle("S'identifier");
            app_stage.show();
            
            
            InputValidation.notificationsucces("Compte", "Votre compte a été supprimer");
        
    }

}
