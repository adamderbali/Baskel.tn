/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import edu.baskel.entities.Reclamation;
import edu.baskel.services.ReclamationCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button bt_reception;
    @FXML
    private Button bt_recp_traitè;
    @FXML
    private Button bt_recp_non_traitè;
    @FXML
    private TableView<Reclamation> t_recla;
    @FXML
    private TableColumn<Reclamation, Integer> T_id_rec;
    @FXML
    private TableColumn<Reclamation, Integer> T_id_u;
    @FXML
    private TableColumn<Reclamation, String> T_desc;
    @FXML
    private TableColumn<Reclamation, Integer> T_id_ur;
    @FXML
    private TableColumn<Reclamation, String> T_valida;
    @FXML
    private TableColumn<Reclamation, String> t_etat;
Reclamation recl;
    
    /**
     * Initializes the controller class.
     */
   
    public void affichage_rec(){
        ReclamationCRUD Mc = new ReclamationCRUD();
        
        Reclamation r;
        
        ArrayList arrayList;
        arrayList = (ArrayList) Mc.displayall_Rec();
        
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        
        T_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        T_id_u.setCellValueFactory(new PropertyValueFactory<>("id_u"));
        T_desc.setCellValueFactory(new PropertyValueFactory<>("desc_r"));
        T_id_ur.setCellValueFactory(new PropertyValueFactory<>("id_ur"));
        T_valida.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
        t_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec2"));

        t_recla.setItems(obser);
       

        

        //t_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec2"));
        /*Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>>() {
            @Override
                public TableCell<Reclamation, Void> call(final TableColumn<Reclamation, Void> param) {
                final TableCell<Reclamation, Void> cell = new TableCell<Reclamation, Void>() {
                    private final Button btn = new Button("Validèr");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Reclamation data = getTableView().getItems().get(getIndex());
                            System.out.println("Data to modify: " + data);
                            Mc.valider_admin(data);
                        });
                    }
                    
                };
                return cell;
            }
        };
        qt_etat.setCellFactory(cellFactory);
        recla.getColumns().add(t_etat);*/
    }
     @FXML
    void chargerDonnee() {
        recl = t_recla.getSelectionModel().getSelectedItem();

        if (recl != null) {
            T_id_rec.setText(String.valueOf(recl.getId_rec()));
            T_id_u.setText(String.valueOf(recl.getId_u()));
            T_id_ur.setText(String.valueOf(recl.getId_ur()));
            T_valida.setText(recl.getEtat_rec());
            t_etat.setText(recl.getEtat_rec2());
            
            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage_rec();
    }    

    @FXML
    private void boite_rec_fct(ActionEvent event) {
    }

    @FXML
    private void rec_trait_fct(ActionEvent event) {
    }

    @FXML
    private void rec_non_trait_fct(ActionEvent event) {
    }
    
}
