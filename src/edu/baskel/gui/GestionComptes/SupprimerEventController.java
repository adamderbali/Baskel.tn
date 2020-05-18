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
import edu.baskel.entities.Participation;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.MembreCRUD;
import edu.baskel.services.ParticipationCrud;
import static edu.baskel.services.ParticipationCrud.cnx;
import edu.baskel.services.SendMail;
import edu.baskel.utils.AutoCompleteAdresse;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class SupprimerEventController implements Initializable {
    
    
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
    private TextField pathE;

    @FXML
    private Button idSupprimer;

    @FXML
    private JFXButton parcourrir;

    @FXML
    private JFXButton fermer;

    @FXML
    private Button idEditer;
    
    
    private Stage thisStage;
    
    private final GererController controller1;

    public SupprimerEventController(GererController controller1) {
        this.controller1 = controller1;
        
         thisStage=new Stage();
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerEvent.fxml"));

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
    

    
    @FXML
    void editerModif(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {
        
          Stage stage = (Stage) fermer.getScene().getWindow();
                    stage.close();
                    controller1.actualiser();

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
         txtNom.setEditable(false);
                  txtLieu.setEditable(false);
                  txtDate.setEditable(false);
                  txtDescription.setEditable(false);
                  pathE.setEditable(false);
                 
        
   }

    @FXML
    void supprimer(ActionEvent event) throws Exception {
        
        ParticipationCrud Pc = new ParticipationCrud();
        EvenementCRUD Ec = new EvenementCRUD();

       // System.out.println("22222" + tableAffichage.getSelectionModel().getSelectedItem().getId_e());
              int i = Integer.parseInt(controller1.getIdEvent());
            if (validationSaisie.confrimSuppression("Information", "Voulez vous supprimer cet evenement")) {
                
                Pc.displayEmailParticipant(i);
                MembreCRUD mc = new MembreCRUD();
                SendMail Sm = new SendMail();

                for (Participation p : Pc.displayEmailParticipant(i)) {

                     try {
                         String sq1 = "SELECT * FROM membre WHERE id_u=?";
                         PreparedStatement prep = cnx.prepareStatement(sq1);
                         prep.setInt(1, p.getId_u());
                         ResultSet res = prep.executeQuery();
                         
                         if (res.next()) {
                             
                             String em = res.getString("email_u");
                             Sm.envoiMail(em, "Nous vous informons que l'evenement :" + controller1.getClickedEvent().getNom_e() + " auquel vous avez participé et qui est prévu le :" + controller1.getClickedEvent().getDate_e() + "a été annulé");
                             System.out.println(em);
                         } else {
                             System.out.println("Aucun participant");
                             
                         }} catch (SQLException ex) {
                             ex.printStackTrace();
                     }
                }

                // Pc.eventAnnuler(tableAffichage.getSelectionModel().getSelectedItem().getId_e());
                Ec.supprimerEvenement(controller1.getClickedEvent());
                Pc.supprimerParticipationE(i);
                controller1.getTableAffichage().getItems().removeAll(controller1.getClickedEvent());
                
               

                txtNom.clear();
                txtNom.setEditable(false);
                txtLieu.clear();
                txtLieu.setEditable(false);
                txtDate.setValue(null);
                txtDate.setEditable(false);
                txtDescription.clear();
                txtDescription.setEditable(false);
                img.setVisible(false);
                pathE.clear();
                pathE.setEditable(false);
                idSupprimer.setVisible(false);
               
                
                    Stage stage = (Stage) fermer.getScene().getWindow();
                    stage.close();
                     controller1.actualiser();
                    validationSaisie.notifConfirm("ok", "Evenement supprimer");
            }
               
          
    }
    

    

    @FXML
    void telecharger(ActionEvent event) {
        
        TextFields.bindAutoCompletion(txtLieu, AutoCompleteAdresse.getAdrGov());
        affichageEvent();
        System.out.println("---------------------"+controller1.getIdEvent());

    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       affichageEvent();
    }    
    
}
