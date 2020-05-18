/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.validationSaisie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class ModifierController implements Initializable {
    
     
    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtLieu;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private ImageView img;
    @FXML
    private JFXTextField idE;
    @FXML
    private TextField pathE;
    @FXML
    private JFXTextField txtId;
     Image image;
     Image im;
     
    @FXML
    private Button idValider;
     @FXML
    private JFXButton fermer;
         @FXML
    private Button idEditer;
 
      @FXML
    private JFXButton parcourrir;   
    
    private Stage thisStage;
    
    private final GererController controller1;

    public ModifierController(GererController controller1) {
        this.controller1 = controller1;
        thisStage=new Stage();
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier.fxml"));

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
   
   public void affichageEvent(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        EvenementCRUD Ec = new EvenementCRUD();
        ArrayList arrayList;
        int i = Integer.parseInt(controller1.getIdEvent());
        arrayList = (ArrayList) Ec.displayByEvent(i);
        ObservableList obser;
        obser = FXCollections.observableArrayList(arrayList);
        Evenement ev =(Evenement)arrayList.get(0);
        System.out.println("+++++++++++"+arrayList);
        System.out.println("-------------"+ev);
        txtNom.setText(ev.getNom_e());
        txtLieu.setText(ev.getLieu_e());
        txtDate.setValue(LocalDate.parse(ev.getDate_e(), formatter));
        txtDescription.setText(ev.getDescription_e());
        pathE.setText(ev.getImage_e());
        Image imgE = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + ev.getImage_e());
        img.setImage(imgE);
        img.setVisible(true);
        
   }
   
   @FXML
    void editerModif(ActionEvent event) {
        
         txtNom.setEditable(true);
                  txtLieu.setEditable(true);
                  txtDate.setEditable(true);
                  txtDescription.setEditable(true);
                  pathE.setEditable(true);
                  img.setVisible(true);

    }
   
    @FXML
    void ValiderModif(ActionEvent event) {
        
          String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate.getEditor().getText();
    
       
            if (((txtNom.getText().isEmpty()) || (txtLieu.getText().isEmpty()) || (txtDate.getEditor().getText().isEmpty()) || (txtDescription.getText().isEmpty()) || (txtDate.getEditor().getText().isEmpty()))) {
                validationSaisie.notifInfo("Echec", "Tous les champs doivent etre remplis");

            } else {
                if ((validationSaisie.validDate(txtDate.getEditor().getText())) == true) {
                    System.out.println("------------------");
                    validationSaisie.notifInfo("Date", "La date saisie doit etre au dela de" + date_system);
                } else {

                    EvenementCRUD Ec = new EvenementCRUD();
                     
                    int i = Integer.parseInt(controller1.getIdEvent());
                    Evenement e = new Evenement(i,
                            txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText()
                    );
                    Ec.updateEvenement(e);
                   
                   // txtNom.clear();
                   // txtLieu.clear();
                   // txtDate.setValue(null);
                   // txtDescription.clear();
                   // pathE.clear();
                   // img.setVisible(false);
                  txtNom.setEditable(false);
                  txtLieu.setEditable(false);
                  txtDate.setEditable(false);
                  txtDescription.setEditable(false);
                  pathE.setEditable(false);
                  img.setVisible(true);
                 // idEditer.setVisible(true);
                       
                    Stage stage = (Stage) fermer.getScene().getWindow();
                    stage.close();
                  controller1.actualiser();
                   validationSaisie.notifConfirm("ok", "Evenement Modifié");
                    
                    
                 //   Stage stage = (Stage) idValider.getScene().getWindow();
                 //   stage.close();
                 //   controller1.actualiser();
                   
                }
                
            
        }
        

    }
    
       @FXML
    void retour(ActionEvent event) {
         Stage stage = (Stage) fermer.getScene().getWindow();
                    stage.close();
                    controller1.actualiser();
    }

    
   

    @FXML
    void telecharger(ActionEvent event) throws IOException {
        
             FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);

            pathE.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);

        }

    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(txtLieu, AutoCompleteAdresse.getAdrGov());
        affichageEvent();
        System.out.println("---------------------"+controller1.getIdEvent());
    }    
    
}
