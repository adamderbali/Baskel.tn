/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import edu.baskel.entities.Velo;
import edu.baskel.services.VeloCRUD;
import edu.baskel.utils.AutoAnnee;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.validationSaisie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author Hela
 */
public class ModifSuppVeloController implements Initializable {
       @FXML
    private ImageView Imgvelo;
       @FXML
    private   Image im;

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
              Stage stage = (Stage) annbut.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    @FXML
    void editerVelo(ActionEvent event) {
            
            Velo vedit =new Velo();
            vedit.setNum_serie(Integer.parseInt(lblnums.getText()));
            vedit.setMarque(txtmarq.getText());
            vedit.setModel(txtmod.getText());
            vedit.setPrix_v(Double.parseDouble(txtpr.getText()));
            vedit.setType_v(txttyp.getText());
            vedit.setAnnee_sortie(txtann.getText());
            vedit.setStatus_v(statbox.getValue());
            vedit.setEtat_v(etatbox.getValue());
            vedit.setDescription_v(lbldsc.getText());
            vedit.setImage_v(pathE.getText());
            System.out.println("edit"+vedit);
            if (((statbox.getValue()) ==null) | ((etatbox.getValue())==null) ) {
            Alert alertChamps = new validationSaisie().getAlert("Echec", "Veuillez saisir tout les champs");
            alertChamps.showAndWait();
            }
            else{
                 if((InputValidation.Year(txtann.getText()))== 0){
                    Alert alertChamps = new validationSaisie().getAlert("Echec", "Veuillez saisir une année valide");
            alertChamps.showAndWait();
             }else{
                    VeloCRUD Vc = new VeloCRUD();
                    Vc.modifierVelo(vedit, Integer.parseInt(lblnums.getText()));
                    Alert alertAdded = new validationSaisie().getAlert("Succés de modification", "Vélo modifié");
                    alertAdded.showAndWait();
                    Stage stage = (Stage) editbutt.getScene().getWindow();
                     // do what you have to do
                    stage.close();
                    controller1.afficher();
               
                
            }}
    }

    @FXML
    void supprimervelo(ActionEvent event) {
            if (validationSaisie.confrimSuppression("Information", "Voulez-vous vraiment supprimer ce vélo?")) {
            int nums = Integer.parseInt(controller1.getEnteredText());
            VeloCRUD Vc = new VeloCRUD();
            Vc.supprimerVelo(nums);
            Alert alertAdded = new validationSaisie().getAlert("Succés de suppression", "Vélo supprimé");
            alertAdded.showAndWait();
            try {
             //System.exit(0);
             Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_mes_velos.fxml"));
             Scene redirection_scene = new Scene(redirection_parent);
             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             app_stage.setScene(redirection_scene);
             app_stage.show();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
            Stage stage = (Stage) suppbutt.getScene().getWindow();
                     // do what you have to do
                    stage.close();
            }
    }

    @FXML
     void telecharger(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            im = new Image(file.getAbsoluteFile().toURI().toString(), Imgvelo.getFitWidth(), Imgvelo.getFitHeight(), true, true);
            System.out.println(photo);
            pathE.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            //photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            photo1 = "C:\\xampp\\htdocs\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            Imgvelo.setImage(im);

        }
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
         etatbox .setItems(liste);
        
        lbldsc.setText(v.getDescription_v());
        pathE.setText(v.getImage_v());
        //"C:\\xampp\\htdocs\\Baskel\\images\\" + photo;
       // System.out.println(controller1.getClickedVelo());
         im =new Image("file:\\C:\\xampp\\htdocs\\Baskel\\images\\"+v.getImage_v());
        Imgvelo.setImage(im);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(txtann, AutoAnnee.getAnnee());
        // TODO
        //lblnums.setText(controller1.getEnteredText());
        afficher();
    }    
    
}
