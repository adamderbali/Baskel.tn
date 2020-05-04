/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private JFXTextField profiladrlocal;

    @FXML
    private JFXTextField profiltelpro;

    @FXML
    private Button btnmodifier1;

    @FXML
    private Button btnconfirmer1;

    @FXML
    private ImageView imagev;

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
    private Text adrloc;

    @FXML
    private Text telpro;

    @FXML
    private FontAwesomeIconView chkmotdepasse;

    @FXML
    private FontAwesomeIconView chkmotdepasse2;

    private String photo = null;
    private File file;
    private Image image;
    Connection cnx;
    Membre l = SessionInfo.getLoggedM();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO M
        
        profildate.setVisible(true);
        chkmotdepasse2.setVisible(false);
        nvdate.setVisible(false);
        cnvpass.setVisible(false);
        txtshow.setVisible(false);
        txtshowc.setVisible(false);
        if (informationReparateur() == false) {
            informationMembre();
            profiladrlocal.setVisible(false);
            profiltelpro.setVisible(false);
            adrloc.setVisible(false);
            telpro.setVisible(false);
        } else {
            informationReparateur();
        }
    }

    public ProfilPageController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public void informationMembre() {

        lblprofil.setText(l.getNom_u() + " " + l.getPrenom_u());
        profilnom.setText(l.getNom_u());
        profilprenom.setText(l.getPrenom_u());
        profilmail.setText(l.getEmail_u());
        profiladresse.setText(l.getAdresse_u());
        nvpass.setText(l.getMot_passe_u());
        profilteleph.setText(l.getNum_tel_u());
        profildate.setText(l.getDate_u().toString());
    }

    public boolean informationReparateur() {

        //afficher reparateur
        String req = "select * from reparateur where id_u=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, iduser);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lblprofil.setText(l.getNom_u() + " " + l.getPrenom_u());
                profilnom.setText(l.getNom_u());
                profilprenom.setText(l.getPrenom_u());
                profilmail.setText(l.getEmail_u());
                profiladresse.setText(l.getAdresse_u());
                nvpass.setText(l.getMot_passe_u());
                profilteleph.setText(l.getNum_tel_u());
                profildate.setText(l.getDate_u().toString());
                profiltelpro.setText(rs.getString("Num_pro"));
                profiladrlocal.setText(rs.getString("Adresse_loc"));
                profiladrlocal.setVisible(true);
                profiltelpro.setVisible(true);
                adrloc.setVisible(true);
                telpro.setVisible(true);
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @FXML
    public void chagerInfos2(ActionEvent event) {
        profildate.setVisible(false);
        nvdate.setVisible(true);
        cnvpass.setVisible(true);
        chkmotdepasse2.setVisible(true);
        profiladresse.setEditable(true);
        profildate.setEditable(true);
        profilmail.setEditable(true);
        profilnom.setEditable(true);
        profilprenom.setEditable(true);
        profilteleph.setEditable(true);

    }

    @FXML
    public void VisualiserMP(MouseEvent event) {
        txtshow.setText(nvpass.getText());
        nvpass.setVisible(false);
        txtshow.setVisible(true);
    }

    @FXML
    public void hideMP(MouseEvent event) {
        nvpass.setVisible(true);
        txtshow.setVisible(false);
    }

    @FXML
    public void VisualiserCMP(MouseEvent event) {
        txtshowc.setText(cnvpass.getText());
        cnvpass.setVisible(false);
        txtshowc.setVisible(true);
    }

    @FXML
    public void hideCMP(MouseEvent event) {
        cnvpass.setVisible(true);
        txtshowc.setVisible(false);
    }

    /*
    @FXML
    public void filephoto(MouseEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        Image imagelog;
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            InputValidation u = new InputValidation();

            System.out.println(photo);
            String photo1;

            photo1 = "C:\\wamp\\www\\Baskel\\" + photo;
            u.CopyImage(photo1, file.toPath().toString());
            image = new Image("file:/" + photo1);
            imagev.setImage(image);
        }

    }
     */
    public boolean validerchamps2() {
        String nvd = nvdate.getValue().toString();
        if ((profilnom.getText().isEmpty()) | (profilprenom.getText().isEmpty()) | (profiladresse.getText().isEmpty())
                | (profilmail.getText().isEmpty()) | (profilteleph.getText().isEmpty()) | (nvpass.getText().isEmpty())
                | (cnvpass.getText().isEmpty()) | nvd == null ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Les champs nom ,prenom,adresse, email, telephone,mot de passe et confirmation doivent etre tout remplis svp");
            alert.show();
                    System.out.println(nvd);

            return false;
        } else {
            return true;
        }
    }

    @FXML
    public void confirmermodif(ActionEvent event) {
        Date nvd = java.sql.Date.valueOf(nvdate.getValue());
        if (validerchamps2() == true) {
            if (InputValidation.validTextField(profilnom.getText())) {
                Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre nom");
                alertNom.showAndWait();
            } else {

                if (InputValidation.validTextField(profilprenom.getText())) {
                    Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez votre Prenom");
                    alertPrenom.showAndWait();
                } else {

                    if (!InputValidation.validEmail(profilmail.getText())) {
                        Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email valide");
                        alertEmail.showAndWait();
                    } else {
                        if (InputValidation.validPwd(nvpass.getText()) == 0) {
                            Alert alertnum = new InputValidation().getAlert("Mot de passe", "Saisissez un mot de passe valide");
                            alertnum.showAndWait();
                        } else {
                            if (InputValidation.PhoneNumber(profilteleph.getText()) == 0) {
                                Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                                alertnum.showAndWait();
                            } else if ((nvpass.getText()).equals(cnvpass.getText())) {
                                MembreCRUD mrcc = new MembreCRUD();
                                Membre m1 = new Membre(profilnom.getText(), profilprenom.getText(), profiladresse.getText(),
                                        profilmail.getText(), "homme", nvd, nvpass.getText(), profilteleph.getText());
                                m1.setId_u(iduser);
                                mrcc.updateMembre(m1);
                                System.out.println("modifie");
                            } else {
                                Alert alertnum = new InputValidation().getAlert(" Erreur mot de passe", "Verifier votre mot de passe");
                                alertnum.showAndWait();

                            }
                        }

                    }
                }
            }
        }
    }
}
