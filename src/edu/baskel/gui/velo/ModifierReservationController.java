/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Reservation;
import edu.baskel.services.ReservationCRUD;
import edu.baskel.utils.InputValidation;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class ModifierReservationController implements Initializable {
    @FXML
    private DatePicker datepick;

    @FXML
    private TextField txtnbrh;

    @FXML
    private Button modifbut;

    @FXML
    private Button annbut;

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) annbut.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
    @FXML
    void modifierReservation(ActionEvent event) {
            String date_system = controller1.getReservation().getDate_res();
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
                int id_res = controller1.getReservation().getId_res();
                Reservation r = new Reservation();
                //r.setId_u(controller1.getReservation().getId_u());
               // r.setNum_serie(controller1.getReservation().getNum_serie());
                System.out.println("date"+date);
                r.setDate_res(date);
                r.setNbr_heure(Integer.parseInt(txtnbrh.getText()));
                System.out.println("res"+r);
                ReservationCRUD rc = new ReservationCRUD();
                rc.modifierReservation(r, id_res);
                 //Alert alertAdded = new validationSaisie().getAlert("Succés de modification", "Réservation modifiée");
               // alertAdded.showAndWait();
                Notifications notificationBuilder = Notifications.create()
                .title("Modification")
                .text("Votre réservation a été modifiée!")
                        .graphic(null)
                //.graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked");
                    }
                });
        //notificationBuilder.darkStyle();
                System.out.println("khsdfkjsd");
        notificationBuilder.showError();
                System.out.println("sfsd");
                Stage stage = (Stage) modifbut.getScene().getWindow();
                 // do what you have to do
                 stage.close();
                 controller1.afficherReservation();
    }
        }}
    private Stage thisStage;
     private final Afficher_mes_reservationsController controller1;
     
    
   
    public ModifierReservationController(Afficher_mes_reservationsController controller1) {
        this.controller1 = controller1;

        // Create the new stage
        thisStage = new Stage();
        
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReservation.fxml"));

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
        System.out.println("ksd"+controller1.getReservation());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        datepick.setValue(LocalDate.parse(controller1.getReservation().getDate_res(), formatter));
        
        txtnbrh.setText(String.valueOf(controller1.getReservation().getNbr_heure()));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
    }    
    
}
