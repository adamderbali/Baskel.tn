/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Reservation;
import edu.baskel.services.MailReservation;
import edu.baskel.services.ReservationCRUD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Reserver_veloController implements Initializable {
    
    @FXML
    private DatePicker datepick;

    @FXML
    private TextField txtnbrh;

    @FXML
    private Button reservbut;

    @FXML
    private Button annbut;
    
    private Stage thisStage;
    private final Afficher_detail_veloController controller1;

    public Reserver_veloController(Afficher_detail_veloController controller1) {
        this.controller1 = controller1;
         // Create the new stage
        thisStage = new Stage();
        
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reserver_velo.fxml"));

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
    void annuler(ActionEvent event) {
            Stage stage = (Stage) annbut.getScene().getWindow();
    // do what you have to do
    stage.close();
    }

    @FXML
    void reserverVelo(ActionEvent event) {
         String date_system = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String date = datepick.getEditor().getText();
        /* test sur les champs vides ou non*/
        if ((txtnbrh.getText().isEmpty()) | date.isEmpty() ) {
             //validationSaisie.validNotif("Erreur", "Saisie tous les champs");
             Alert alertDtae1 = new validationSaisie().getAlert("Erreur", "Veuillez saisir tout les champs");
                alertDtae1.showAndWait();
            /* test sur les dates*/
         
        }else {
            if ((validationSaisie.validDate(datepick.getEditor().getText())) == true) {

                System.out.println("------------------");
                Alert alertDtae = new validationSaisie().getAlert("date", "La date saisie doit etre superieure à " + date_system);
                alertDtae.showAndWait();
            } else if((InputValidation.Number(txtnbrh.getText()))== 0){
                System.out.println("------------------");
                Alert alertDtae = new validationSaisie().getAlert("Erreur", "Veuillez entrer un nombre d'heures valide!");
                alertDtae.showAndWait();
            }else{
                int nums = Integer.parseInt(controller1.getEnteredText());
                Reservation r = new Reservation();
                r.setId_u(1);
                r.setNum_serie(nums);
                r.setDate_res(date);
                r.setNbr_heure(Integer.parseInt(txtnbrh.getText()));
                System.out.println("res"+r);
                ReservationCRUD rc = new ReservationCRUD();
                rc.ajouterReservation(r);
                Alert alertAdded = new validationSaisie().getAlert("Succés d'ajout", "Réservation ajoutée");
                alertAdded.showAndWait();
                /*****mail*****/
                System.out.println("aaaaaa");
                //String adrmail =  SessionInfo.getLoggedM().getEmail_u();
                System.out.println("bbbbb");
                 MailReservation mr =new MailReservation();
                 System.out.println("ccccc");
                try {
                    System.out.println("dddd");
                    System.out.println(controller1.getEnteredText());
                    System.out.println(r.getDate_res());
                    System.out.println(date_system);
                    System.out.println(r.getNbr_heure());
                     mr.envoyerMail("hela.maroub@esprit.tn","Votre réservation pour le vélo "+controller1.getEnteredText()+" a été effectuée , Voici les informations de votre réservation : \n Date de réservation: "+r.getDate_res()+"\n Nombre d'heures: "+r.getNbr_heure()+"\n Date affectée à la réservation :"+date_system );
                      //Sm.envoiMail(em, "Nous vous informons que l'evenement :" + tableAffichage.getSelectionModel().getSelectedItem().getNom_e() + " de date :" + tableAffichage.getSelectionModel().getSelectedItem().getDate_e() + " que vous avez participé est annulé");
                } catch (Exception ex) {
                    ex.printStackTrace();
                   System.out.println("erreur mail");
                 }
                System.out.println("eeeee");
                 Alert alertAddedm = new validationSaisie().getAlert("Succés d'ajout", "Un mail contenant les informations vous sera envoyé");
                alertAddedm.showAndWait();
                Stage stage = (Stage) reservbut.getScene().getWindow();
                 // do what you have to do
                 stage.close();
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
