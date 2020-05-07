/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import edu.baskel.entities.Alerte;
import edu.baskel.entities.Avis;
import edu.baskel.services.AlerteCRUD;
import edu.baskel.services.AvisCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.adapter.ReadOnlyJavaBeanObjectProperty;
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
public class ListeAvisController implements Initializable {

    @FXML
    private TableView<Avis> tableAffichage;
    @FXML
    private TableColumn<Avis, String> colNom;
    @FXML
    private TableColumn<Avis, String> colNote;
    @FXML
    private TableColumn<Avis, String> colDescription;
    @FXML
    private TableColumn<Avis, Date> colDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.getlistealert();
    }

    public void getlistealert() {
        AvisCRUD altcrd = new AvisCRUD();
        List<Avis> avistlst = altcrd.getListeAvis();
        ObservableList obser = FXCollections.observableArrayList(avistlst);
        //afficher le non du membre dnas la classe alerte
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMembre().getNom_u() + " " + p.getValue().getMembre().getPrenom_u()));
        colNote.setCellValueFactory((a) -> new ReadOnlyStringWrapper(String.valueOf(a.getValue().getNote_av()) ));
        colDate.setCellValueFactory(new PropertyValueFactory<Avis, Date>("date_avis"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Avis, String>("desc_av"));
        tableAffichage.setItems(obser);
    }
}
