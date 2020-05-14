/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Evenement;
import edu.baskel.gui.velo.Afficher_mes_velosController;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.text.TableView;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class ModifierEventController implements Initializable {
    
    
    
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

    
    
   
    public void charger(String nom_e,String lieu_e,String date_e,String description_e,String pathe){
      
        
        txtNom.setText(nom_e);
        txtLieu.setText(lieu_e);
        txtDescription.setText(description_e);
        pathE.setText(pathe);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtDate.setValue(LocalDate.parse(date_e, formatter));
       Image imgE = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + pathe);
            img.setImage(imgE);
           
    }
    
    //private final ListUpdDelByUserController controller1;
         private Stage thisStage;
     private final ListUpdDelByUserController controller1;
     public ModifierEventController(ListUpdDelByUserController controller1) {
        this.controller1 = controller1;

        // Create the new stage
        thisStage = new Stage();
        
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUpdDelByUser.fxml"));

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
    @FXML
    void ValiderModif(ActionEvent event) {
        
        
        String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = txtDate.getEditor().getText();
        /* test sur les champs vides ou non*/
       
            if (((txtNom.getText().isEmpty()) || (txtLieu.getText().isEmpty()) || (txtDate.getEditor().getText().isEmpty()) || (txtDescription.getText().isEmpty()) || (txtDate.getEditor().getText().isEmpty()))) {
                validationSaisie.notifInfo("Echec", "Tous les champs doivent etre saisis");

            } else {
                if ((validationSaisie.validDate(txtDate.getEditor().getText())) == true) {

                    System.out.println("------------------");
                    validationSaisie.notifInfo("Date", "La date saisie doit etre superieur à" + date_system);
                } else {
                    
                 //   ListUpdDelByUserController ld = new ListUpdDelByUserController();
                    EvenementCRUD Ec = new EvenementCRUD();
             
                        ListUpdDelByUserController ld= new ListUpdDelByUserController();
                      //  Evenement e = new Evenement(ld.getClickedEvenement().getId_e(),
                         //   txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText()
                  //  );
                    
                  //  Ec.updateEvenement(e);
                    validationSaisie.notifConfirm("ok", "Evenement Modifié");
                    

                    txtNom.clear();
                    txtLieu.clear();
                    txtDate.setValue(null);
                    txtDescription.clear();
                    pathE.clear();
                    img.setVisible(false);
                }
            }
        }

    




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
