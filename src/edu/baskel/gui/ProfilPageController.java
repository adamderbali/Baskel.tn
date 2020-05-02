/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProfilPageController implements Initializable {

    @FXML
    private JFXTextField profilmail, profiladresse, profildate, profilteleph, txtpas;
    @FXML
    private JFXDialogLayout dialog;
    @FXML
    private Button btnp;
    @FXML
    private JFXTextField cnvpass, nvpass, profiltelpro, profiladrlocal, profilnom, profilprenom;
    @FXML
    private JFXDatePicker nvdate;

    private Connection cnx;

    @FXML
    private Button btnmodifier1;
    @FXML
    private Button btnconfirmer1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        informationMembre();
        dialog.setVisible(false);
        nvdate.setVisible(false);

    }

    public ProfilPageController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public void informationMembre() {
        //afficher membre
        MembreCRUD mrc = new MembreCRUD();
        //Membre m = new Membre();
        //m.setId_u(iduser);
        //mrc.AfficherMembre(m);
        Membre l = SessionInfo.getLoggedM();
        mrc.AfficherMembre(l);
        profilnom.setText(l.getNom_u());
        // profilnom.setText(m.getNom_u());
        profilprenom.setText(l.getPrenom_u());
        profilmail.setText(l.getEmail_u());
        profiladresse.setText(l.getAdresse_u());
        profilteleph.setText(l.getNum_tel_u());
        profildate.setText(l.getDate_u().toString());
        //afficher reparateur
        String req = "select * from reparateur where id_u=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, iduser);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                profiltelpro.setText(rs.getString("Num_pro"));
                profiladrlocal.setText(rs.getString("Adresse_loc"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierinfo(ActionEvent event) {
    }

    @FXML
    public void changerInfos(MouseEvent event) {
        dialog.setVisible(true);
    }

    @FXML
    public void chagerInfos2(ActionEvent event) {
        nvdate.setVisible(true);

        MembreCRUD mrc2 = new MembreCRUD();
        String mot_passep = txtpas.getText();

        if (mrc2.VerificationChgInfo(mot_passep) == false) {

        } else {
            dialog.setVisible(false);
            profiladresse.setEditable(true);
            profildate.setEditable(true);
            profilmail.setEditable(true);
            profilnom.setEditable(true);
            profilprenom.setEditable(true);
            profilteleph.setEditable(true);
        }

    }

    /*public boolean Verification2() {
        try {
            String sq1 = "SELECT * FROM membre where mot_passe_u=?";
            String mot_passep = txtpas.getText();
            pst = cnx.prepareStatement(sq1);
            pst.setString(1, mot_passep);
            res = pst.executeQuery();

            if (!res.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Mot de passe incorrectS");
                alert.show();
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return true;

    }*/
    @FXML
    public void confirmermodif(ActionEvent event) {
        Date nvd = java.sql.Date.valueOf(nvdate.getValue());
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
