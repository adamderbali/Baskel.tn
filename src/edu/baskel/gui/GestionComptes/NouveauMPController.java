/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.InputValidation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class NouveauMPController implements Initializable {

    @FXML
    private PasswordField txtNvMp;

    @FXML
    private PasswordField txtCnvMp;

    @FXML
    private JFXButton btnConfirmation;

    Stage owner;
    Connection cnx = null;
    PreparedStatement pst = null;
    ResultSet res = null;
    @FXML
    private Button btnSidentifier2;
    @FXML
    private JFXTextField txtm;

    public void setTxtm(String txtmail) {
        this.txtm.setText(txtmail);
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public NouveauMPController() {
        cnx = ConnectionBD.getInstance().getCnx();
    }

    @FXML
    public void changerMP(ActionEvent event) {
        if (txtNvMp.getText().equals(txtCnvMp.getText())) {
            MembreCRUD r = new MembreCRUD();
            r.changerMP(txtm.getText(), txtCnvMp.getText());
            Alert alertnum = new InputValidation().getAlert("mot de passe", "Votre mot de passe a été réinitialisé !");
            alertnum.showAndWait();

        } else {
            Alert alertnum = new InputValidation().getAlert("mot de passe", "Verifier vos données !");
            alertnum.showAndWait();
            System.out.println("Erreur ");
        }
    }

    @FXML
    public void backSidentifier2(MouseEvent event) throws IOException {

        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }

}