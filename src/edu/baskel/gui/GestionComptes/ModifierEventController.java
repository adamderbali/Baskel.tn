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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    
    public ModifierEventController() {
    }

    
    public void charger(int id_e,String nom_e,String lieu_e,String date_e,String description_e,String pathe){
      
        
        txtNom.setText(nom_e);
        txtLieu.setText(lieu_e);
        txtDescription.setText(description_e);
        pathE.setText(pathe);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtDate.setValue(LocalDate.parse(date_e, formatter));
       Image imgE = new Image("file:/C:\\wamp\\www\\Baskel\\images\\" + pathe);
            img.setImage(imgE);
           
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
                    
                    ListUpdDelByUserController ld = new ListUpdDelByUserController();
                    EvenementCRUD Ec = new EvenementCRUD();
                   FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("ListUpdDelByUser.fxml"));

                try {

                    Loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ListParticipationParEventUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ListUpdDelByUserController listUpdDelByUserController = Loader.getController();

                    Evenement e = new Evenement(ld.getTableAffichage().getSelectionModel().getSelectedItem().getId_e(),
                            txtNom.getText(), txtLieu.getText(), txtDate.getEditor().getText(), txtDescription.getText(), pathE.getText()
                    );
                    
                    Ec.updateEvenement(e);
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
