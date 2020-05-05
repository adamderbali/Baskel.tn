/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import edu.baskel.entities.Alerte;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.ReparateurCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeReparateurController implements Initializable {

    @FXML
    private TableView<Reparateur> tableAffichage;
    @FXML
    private TableColumn<Reparateur, String> colNom;
    @FXML
    private TableColumn<Reparateur, String> colAdresse;
    @FXML
    private TableColumn<Reparateur, String> colTelephone;
    @FXML
    private TableColumn<Reparateur, String> colRep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReparateurCRUD rcrd = new ReparateurCRUD();
        List<Reparateur> lstrep = rcrd.getListeReparateur();
        ObservableList obser = FXCollections.observableArrayList(lstrep);
        colNom.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("local_nom"));
        colRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("nom_u"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("adresse_lo"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("num_pro"));
        tableAffichage.setItems(obser);
    }

}
