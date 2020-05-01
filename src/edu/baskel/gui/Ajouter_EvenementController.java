/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.ConnectionBD;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Ajouter_EvenementController implements Initializable {

    @FXML
    private AnchorPane fileChooser;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtLieu;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private Button fileChoose;

    @FXML
    private TextField pathE;

    public Ajouter_EvenementController() {


    }

    @FXML
    void ajouterEvenement(ActionEvent event) {

        /* String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));*/
        if (((txtNom.getText().isEmpty()) | (txtLieu.getText().isEmpty()) | (txtDate.getEditor().getText().isEmpty()) | (txtDescription.getText().isEmpty()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Les champs doivent etre tout remplis svp");
            alert.show();
        } else if ((LocalDate.now().toString().compareTo(txtDate.getEditor().getText()) > 0)) {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Date erronée");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            alert2.setContentText("La date saisie doit etre superieur à" + dtf.format(now));
            alert2.show();
        } else {

            try {
                EvenementCRUD Ec = new EvenementCRUD();
                Date date1 = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(txtDate.getEditor().getText());
                Evenement e = new Evenement(txtNom.getText(), txtLieu.getText(), date1, txtDescription.
                        getText(), pathE.getText());
                Ec.ajouterEvenement(e);

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("ajout ok");
                alert1.setContentText("Event added");
                alert1.show();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        txtNom.clear();
        txtLieu.clear();
        txtDate.setValue(null);
        txtDescription.clear();
        pathE.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private ChronoLocalDate LocalDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
