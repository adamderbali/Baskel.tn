/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Participation;
import edu.baskel.services.ParticipationCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class DetailsParticipantController implements Initializable {
    
      @FXML
    private TableView<Participation> tableAffichage1;

    @FXML
    private TableColumn<Participation,String> colNom;

    @FXML
    private TableColumn<Participation,String> colPrenom;

    @FXML
    private TableColumn<Participation,String> colEmail;


    @FXML
    private JFXTextField nbreP;

    public DetailsParticipantController() {
    }

      public void affichageParticip(int id_e) { 

    ParticipationCrud Pc = new ParticipationCrud();
        List<Participation> partlst = Pc.displayParticipantList(id_e);
        
        ObservableList obserE;
        obserE = FXCollections.observableArrayList(partlst);
        TableColumn<Participation, String> c2 = new TableColumn<Participation, String>("first");
        //afficher le non du membre dnas la classe alertei
        nbreP.setText(String.valueOf(Pc.nombreParticipation(id_e)));
        colNom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getNom_u()));
        colPrenom.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getPrenom_u()));
        colEmail.setCellValueFactory((p) -> new ReadOnlyStringWrapper(p.getValue().getMbre().getEmail_u()));
        tableAffichage1.setItems(obserE);
    
        
     

 
 
        
       }
         @FXML
          void ok(ActionEvent event) {
             
              Stage stage = (Stage) tableAffichage1.getScene().getWindow();
              stage.close();
              
          }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
   
    }    
    
    
    
}