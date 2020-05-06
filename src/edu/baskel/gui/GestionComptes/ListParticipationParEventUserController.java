/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import edu.baskel.entities.Participation;
import edu.baskel.services.ParticipationCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class ListParticipationParEventUserController implements Initializable {
    
      @FXML
    private TableView<Participation> tableAffichage;
 @FXML
    private TableColumn<Participation,String> colNom;

    @FXML
    private TableColumn<Participation,String> colPrenom;

    @FXML
    private TableColumn<Participation,String> colEmail;

    @FXML
    private TableColumn<Participation,String> colNomE;

    @FXML
    private TableColumn<Participation,String> colLieuE;

    @FXML
    private TableColumn<Participation,String> colDateE;
    
    
    
    
    public void affichageParticip() {

         ParticipationCrud Pc = new ParticipationCrud();
        List<Participation> partlst = Pc.displayParticipant(3);
        ObservableList obser;
        obser = FXCollections.observableArrayList(partlst);
        TableColumn<Participation, String> c1 = new TableColumn<Participation, String>("first");
        //afficher le non du membre dnas la classe alerte
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getNom_u()));
        colPrenom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getPrenom_u()));
        colEmail.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getEmail_u()));
        colNomE.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getNom_e()));
        colLieuE.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getLieu_e()));
        colDateE.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getEvent().getDate_e()));
        tableAffichage.setItems(obser);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichageParticip();
        
        // TODO
    }    
    
}
  