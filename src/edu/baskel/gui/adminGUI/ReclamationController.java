/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Reclamation;
import edu.baskel.services.ReclamationCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import static javax.management.Query.value;

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
    @FXML
    private AnchorPane pane_rec;
    @FXML
    private VBox rec_vbox;
    @FXML
    private TableView<Reclamation> recla;
    @FXML
    private Label label_rec;
    @FXML
    private Pane pane2_rec;
   Reclamation recl;
   private ObservableList<String> Choix1;
   private ObservableList<String> Choix2;
    @FXML
    private Button bt_modif;
    @FXML
    private JFXTextField tf_id_rec;
    @FXML
    private JFXTextField tf_valadition;
    @FXML
    private JFXTextField tf_etat;


 
    /**
     * Initializes the controller class.
     */
   
    public void affichage_rec(){
        ReclamationCRUD Mc = new ReclamationCRUD();
        
        
        ArrayList arrayList2;
        arrayList2 = (ArrayList) Mc.displayall_Rec();
        
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList2);
        Choix1 = FXCollections.observableArrayList();
        Choix1.add("NEW");
        Choix1.add("OLD");
        Choix2 = FXCollections.observableArrayList();
        Choix2.add("Valide");
        Choix2.add("Non_Valide");
        T_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        T_id_u.setCellValueFactory(new PropertyValueFactory<>("id_u"));
        T_desc.setCellValueFactory(new PropertyValueFactory<>("desc_r"));
        T_id_ur.setCellValueFactory(new PropertyValueFactory<>("id_ur"));
        T_valida.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
        T_valida.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix2));
        T_valida.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event1){
                
                System.out.println("Value : "+event1.getNewValue());
                tf_valadition.setText(event1.getNewValue());
                

            }
        });
        t_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec2"));
        t_etat.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),Choix1));
        t_etat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation,String> event){
                System.out.println("Value : "+event.getNewValue());
                tf_etat.setText(event.getNewValue());
                String value_v= event.getNewValue();
            }
        });
        recla.setItems(obser);
        /*******/
        
        recla.setEditable(true);
        
        

    }
    
     @FXML
    void chargerDonnee() {
        recl = recla.getSelectionModel().getSelectedItem();

        if (recl != null) {
            tf_valadition.setText(recl.getEtat_rec());
            tf_etat.setText(recl.getEtat_rec2());
            //T_id_u.setText(String.valueOf(recl.getId_u()));
            //T_id_ur.setText(String.valueOf(recl.getId_ur()));
            //T_valida.setText(recl.getEtat_rec());
            //t_etat.setText(recl.getEtat_rec2());
            
            //tf_date_user.set(user.getDate_u());
            //pathE.setText(event.getImage_e());  
        }
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage_rec();
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        ReclamationCRUD Rc = new ReclamationCRUD();
        
        Reclamation c = new Reclamation(tf_valadition.getText(),tf_etat.getText(),recla.getSelectionModel().getSelectedItem().getId_rec());
        Rc.valider_admin(c);
    }

   
    
}
