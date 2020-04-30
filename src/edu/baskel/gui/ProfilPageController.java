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
import static edu.baskel.services.MembreCRUD.adressem;
import static edu.baskel.services.MembreCRUD.datem;
import static edu.baskel.services.MembreCRUD.emailm;
import static edu.baskel.services.MembreCRUD.nomm;
import static edu.baskel.services.MembreCRUD.prenomm;
import static edu.baskel.services.MembreCRUD.telm;
import edu.baskel.utils.ConnectionBD;
import static edu.baskel.utils.SessionInfo.iduser;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProfilPageController implements Initializable {

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
    private Button btnmodifier;

    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet res;

    @FXML
    private JFXDialogLayout dialog;
    @FXML
    private JFXTextField txtpas;
    @FXML
    private Button btnp;
    @FXML
    private Button btnconfirmer;
    @FXML
    private JFXTextField cnvpass;
    @FXML
    private JFXTextField nvpass;
    @FXML
    private JFXDatePicker nvdate;
    @FXML
    private Label lblrmp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        information();
        dialog.setVisible(false);
    }

    public ProfilPageController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    public void information() {

        MembreCRUD mrc = new MembreCRUD();
        mrc.AfficherMembre(iduser);
        profilnom.setText(nomm);
        profilprenom.setText(prenomm);
        profilmail.setText(emailm);
        profiladresse.setText(adressem);
        profilteleph.setText(telm);
        profildate.setText(datem);
    }

    /*
    @FXML
    public void modifierinfo(ActionEvent event) {
        anchroPasse.setVisible(true);
    }
     */
    @FXML
    public void changerInfos(MouseEvent event) {
        dialog.setVisible(true);
    }

    @FXML
    public void chagerInfos2(ActionEvent event) {
        MembreCRUD mrc2 = new MembreCRUD();
        String mot_passep = txtpas.getText();

        if (mrc2.VerificationChgInfo(mot_passep) == false) {
            lblrmp.setTextFill(Color.TOMATO);
            lblrmp.setText("Mot de passe incorrect ,verifiez svp");

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
        if ((nvpass.getText()).equals(cnvpass.getText())) {
            MembreCRUD mrcc = new MembreCRUD();
            Membre m1 = new Membre(profilnom.getText(), profilprenom.getText(), profiladresse.getText(),
                    profilmail.getText(), "homme", nvd, nvpass.getText(), profilteleph.getText());
            mrcc.updateMembre(m1, iduser);
            System.out.println("modifie");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setContentText("Verifier le mot de passe");
            alert.show();
        }
    }

}
