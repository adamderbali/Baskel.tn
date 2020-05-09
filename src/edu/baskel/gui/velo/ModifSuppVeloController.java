/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Velo;
import edu.baskel.services.VeloCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Hela
 */
public class ModifSuppVeloController implements Initializable {
       @FXML
    private ImageView Imgvelo;

    @FXML
    private Label lblnums;

    @FXML
    private TextArea lbldsc;

    @FXML
    private TextField txtmarq;

    @FXML
    private TextField txtmod;

    @FXML
    private TextField txtpr;

    @FXML
    private TextField txttyp;

    @FXML
    private TextField txtann;

    @FXML
    private ChoiceBox<String> statbox;

    @FXML
    private ChoiceBox<String> etatbox;

    @FXML
    private Button fileChoose;

    @FXML
    private Button editbutt;

    @FXML
    private Button suppbutt;

    @FXML
    private Button annbut;

    @FXML
    private TextField pathE;

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void editerVelo(ActionEvent event) {

    }

    @FXML
    void supprimervelo(ActionEvent event) {

    }

    @FXML
    void telecharger(ActionEvent event) {

    }
    
    private Stage thisStage;
     private final Afficher_mes_velos_detailsController controller1;
     
    
   
    public ModifSuppVeloController(Afficher_mes_velos_detailsController controller1) {
        this.controller1 = controller1;

        // Create the new stage
        thisStage = new Stage();
        
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifSuppVelo.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            //thisStage.setTitle("Passing Controllers Example - Layout2");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showStage() {
        thisStage.showAndWait();
    }
     public void afficher(){
        VeloCRUD vc = new VeloCRUD();
        ArrayList av;
        int nums = Integer.parseInt(controller1.getEnteredText());
        av=(ArrayList) vc.afficherDetail(nums);
        ObservableList o;
        o= FXCollections.observableArrayList(av);
        
        //System.out.println("av"+av);
        //System.out.println("tstg"+av.get(0));
        Velo v =(Velo)av.get(0);
        //System.out.println(v.getAnnee_sortie());
       //ColAnnee.setCellValueFactory(new PropertyValueFactory <>("annee_sortie"));
       //ColEtat.setCellValueFactory(new PropertyValueFactory <>("etat_v"));
       //ColDesc.setCellValueFactory(new PropertyValueFactory <>("description_v"));
       
      // TableDetail.setItems(o);
        
        lblnums.setText(controller1.getEnteredText());
        txtmarq.setText(v.getMarque());
        txtmod.setText(v.getModel());
        txtpr.setText(String.valueOf(v.getPrix_v()));
        txttyp.setText(v.getType_v());
        txtann.setText(v.getAnnee_sortie());
        
         ObservableList<String> list = FXCollections.observableArrayList();
         list.addAll("A louer", "A vendre");
         //populate the Choicebox;  
         statbox .setItems(list);
         
         ObservableList<String> liste = FXCollections.observableArrayList();
         liste.addAll("Disponible", "Non Disponible");
         //populate the Choicebox;  
         etatbox .setItems(list);
        
        lbldsc.setText(v.getDescription_v());
        
       // System.out.println(controller1.getClickedVelo());
        Image im =new Image(v.getImage_v());
        Imgvelo.setImage(im);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //lblnums.setText(controller1.getEnteredText());
        afficher();
    }    
    
}
