/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Participation;
import edu.baskel.services.ParticipationCrud;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.rgb;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

public class AvisEvenementController implements Initializable {

    private Stage thisStage;

    private final Annulation_participationController controller1;

    public AvisEvenementController(Annulation_participationController controller1) {
        this.controller1 = controller1;
        thisStage = new Stage();
        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisEvenement.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Exprimez votre avis");

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
    private JFXButton idAvis;
    @FXML
    private Rating rate;

    @FXML
    private Label msg;
Membre ml = SessionInfo.getLoggedM();
    @FXML
    private JFXComboBox<String> comobox;
    ObservableList obser;

    public void comoboxNom() {

        ParticipationCrud parList = new ParticipationCrud();
        List<String> partListU = parList.ListNom(9);
        Participation p = new Participation();
        obser = FXCollections.observableArrayList(partListU);
        comobox.setItems(obser);
        String c = comobox.getValue();

    }

    @FXML
    void ajouterAvis(ActionEvent event) {
       
        ParticipationCrud parList = new ParticipationCrud();
        String c = comobox.getValue();
        if(c==null){
            validationSaisie.notifInfo("Erreur", "Vous devez selectionner un evenement");
             comobox.setFocusColor(rgb(255, 0, 0));
                comobox.setUnFocusColor(rgb(255, 0, 0));
                comobox.setStyle("-fx-prompt-text-fill: #C4151C");
        }
        System.out.println("++++++" + c);
        int d = parList.displayEventParId(c);
        System.out.println("+++++++++++++++++++++++++++++++za3ma chnia id" + d);

        int r = (int) rate.getRating();
        ParticipationCrud pc = new ParticipationCrud();
        Participation p = new Participation (d,ml.getId_u(),(int)rate.getRating());
        System.out.println(p.toString());
        pc.ajouterAvisEvent(p);
      
        
    }
    
    
    @FXML
    void comobox(MouseEvent event) {

        comobox.setFocusColor(rgb(0, 150, 164));
        comobox.setUnFocusColor(rgb(77, 77, 77));
        comobox.setStyle("-fx-prompt-text-fill: #000000");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comoboxNom();
       /*  rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            msg.setText("Rating : " + newValue);
        });*/

    }

}
