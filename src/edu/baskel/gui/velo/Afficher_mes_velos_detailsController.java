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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Afficher_mes_velos_detailsController implements Initializable {
    
     @FXML
    private ImageView Imgvelo;
    
    @FXML
    private Label lblnums;
    
    @FXML
    private Label lblmarq;

    @FXML
    private Label lblmodl;

    @FXML
    private Label lblprx;

    @FXML
    private Label lbltyp;

    @FXML
    private Label lblann;

    @FXML
    private Label lblstt;

    @FXML
    private Label lbletat;

    @FXML
    private TextArea lbldsc;

    
    private Stage thisStage;
    private final Afficher_mes_velosController controller1;
    
      @FXML
    private Button bachbutt;

    @FXML
    private Button gererbut;

    @FXML
    void gererVelo(ActionEvent event) {
           ModifSuppVeloController controller2 = new ModifSuppVeloController(this);
                     controller2.showStage();
    }

    @FXML
    void retour(ActionEvent event) {
        Stage stage = (Stage) bachbutt.getScene().getWindow();
    // do what you have to do
    stage.close();
    controller1.affichageVelo();
        /* try {
             //System.exit(0);
             Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_mes_velos.fxml"));
             Scene redirection_scene = new Scene(redirection_parent);
             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             app_stage.setScene(redirection_scene);
             app_stage.show();
         } catch (IOException ex) {
             ex.printStackTrace();
         }*/
    }  
    public Afficher_mes_velos_detailsController(Afficher_mes_velosController controller1) {
        this.controller1 = controller1;

        // Create the new stage
        thisStage = new Stage();
        
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_mes_velos_details.fxml"));

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
     Velo v;
     public String getEnteredText() {
         System.out.println(lblnums.getText());
       return lblnums.getText() ;
         
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
        lblmarq.setText(v.getMarque());
        lblmodl.setText(v.getModel());
        lblprx.setText(String.valueOf(v.getPrix_v()));
        lbltyp.setText(v.getType_v());
        lblann.setText(v.getAnnee_sortie());
        lblstt.setText(v.getStatus_v());
        lbletat.setText(v.getEtat_v());
        lbldsc.setText(v.getDescription_v());
        
        System.out.println("image"+controller1.getClickedVelo().getImage_v());
        Image im =new Image("file:\\C:\\wamp\\www\\Baskel\\images\\"+controller1.getClickedVelo().getImage_v());
        Imgvelo.setImage(im);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    
    
}
