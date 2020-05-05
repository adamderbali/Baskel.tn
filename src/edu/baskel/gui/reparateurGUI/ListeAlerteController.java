/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import edu.baskel.entities.Alerte;
import edu.baskel.entities.Membre;
import edu.baskel.services.AlerteCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
public class ListeAlerteController implements Initializable {

    @FXML
    private TableView<Alerte> tableAffichage;
    @FXML
    private TableColumn<Alerte, String> colNom;
    @FXML
    private TableColumn<Alerte, String> colLieu;
    @FXML
    private TableColumn<Alerte, Date> colDate;
    @FXML
    private TableColumn<Alerte, String> colDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AlerteCRUD altcrd = new AlerteCRUD();
        List<Alerte> alertlst = altcrd.getListeAlerte();
        ObservableList obser = FXCollections.observableArrayList(alertlst);
        TableColumn<Alerte, String> c1 = new TableColumn<Alerte, String>("first");
        //afficher le non du membre dnas la classe alerte
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMembre().getNom_u() + " " + p.getValue().getMembre().getPrenom_u()));
        colLieu.setCellValueFactory(new PropertyValueFactory<Alerte, String>("adresse_a"));
        colDate.setCellValueFactory(new PropertyValueFactory<Alerte, Date>("date_a"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Alerte, String>("description_a"));
        tableAffichage.setItems(obser);
    }

}
