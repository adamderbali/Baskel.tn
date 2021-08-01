/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import edu.baskel.gui.GestionComptes.Afficher_Tout_VeloController;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Velo;
import edu.baskel.services.MailReservation;
import edu.baskel.services.VeloCRUD;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Afficher_detail_veloController implements Initializable {

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
  
     @FXML
    private Button bachbutt;
    
    private Stage thisStage;
    private final Afficher_Tout_VeloController controller1;
     
     @FXML
    private Button resbut;
     Membre m = SessionInfo.getLoggedM();
       
    @FXML
    private Button achatbut;

    @FXML
    void acheterVelo(ActionEvent event) {
           int nums = Integer.parseInt(controller1.getEnteredText());
            VeloCRUD vc = new VeloCRUD();
            System.out.println("etat"+lbletat.getText());
            if(lbletat.getText().equals("Non Disponible")){
                Alert alertAdded = new validationSaisie().getAlert("Erreur", "Ce vélo n'est pas disponible");
            alertAdded.showAndWait();
            }else{
                vc.louerOuAcheterVelo(m.getId_u(), nums);
            Alert alertAdded = new validationSaisie().getAlert("Succés", "Ce vélo est réservé pour votre achat");
            alertAdded.showAndWait();
            }
            Stage stage = (Stage) achatbut.getScene().getWindow();
            // do what you have to do
            stage.close();
    }
    @FXML
    void reserverVélo(ActionEvent event) {
            Reserver_veloController controller2 = new Reserver_veloController(this);
                     controller2.showStage();
            
    }
    Velo v;
     public String getEnteredText() {
         System.out.println(lblnums.getText());
       return lblnums.getText() ;
         
    }
     
    @FXML
    void retour(ActionEvent event) {
        Stage stage = (Stage) bachbutt.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    public Afficher_detail_veloController(Afficher_Tout_VeloController controller1) {
        this.controller1 = controller1;

        // Create the new stage
        thisStage = new Stage();
        
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_detail_velo.fxml"));

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
        lblmarq.setText(v.getMarque());
        lblmodl.setText(v.getModel());
        lblprx.setText(String.valueOf(v.getPrix_v()));
        lbltyp.setText(v.getType_v());
        lblann.setText(v.getAnnee_sortie());
        lblstt.setText(v.getStatus_v());
        if (lblstt.getText().equals("A louer")){
            resbut.setVisible(true);
            achatbut.setVisible(false);
        }else{
            resbut.setVisible(false);
            achatbut.setVisible(true);
        }
        lbletat.setText(v.getEtat_v());
        lbldsc.setText(v.getDescription_v());
        
        System.out.println(controller1.getClickedVelo());
        Image im =new Image("file:\\C:\\wamp\\www\\Baskel\\images\\"+controller1.getClickedVelo().getImage_v());
        Imgvelo.setImage(im);
    }
    
    
    
    
    
     @FXML
    void exit(ActionEvent event) throws IOException {
            //System.exit(0);
            /*Afficher_detail_velo.getInstance().getStage().hide();
            EspritEntreAideMainFXML.getInstance().ChangeScene(new Scene(FXMLLoader.load(getClass().getResource("ResAccuielFXML.fxml"))));
            EspritEntreAideMainFXML.getInstance().getStage().show();*/
            Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_Tout_Velo.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.show();
    }
    
    
    
    
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
    }    
    
}
