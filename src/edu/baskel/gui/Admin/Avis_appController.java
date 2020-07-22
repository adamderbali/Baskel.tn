/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.Admin;

import com.jfoenix.controls.JFXTextField;
import edu.baskel.entities.Avis_admin;
import edu.baskel.entities.Membre;
import edu.baskel.services.AvisAdminCRUD;
import edu.baskel.services.StatCRUD;
import edu.baskel.utils.SessionInfo;
import static java.awt.Color.red;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Avis_appController implements Initializable {

    @FXML
    private Rating avis_user;
    @FXML
    private TextArea commentaire_user;
    @FXML
    private Button bt_envoie;
    @FXML
    private Label comm_note;
    Membre ml = SessionInfo.getLoggedM();
    @FXML
    private ImageView img_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void btn_envoie() throws SQLException {
        AvisAdminCRUD sc = new AvisAdminCRUD();
        if (commentaire_user.getText().isEmpty()) {
            Notifications notificationBuilder = Notifications.create()
                    .text("Voulez- vous ajouter un Commentaire s'il vous plaît ?").title("Erreur").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }

            });
            notificationBuilder.showError();
        } else {

            if (sc.verfication(ml.getId_u()) == true) {
                Avis_admin aa = new Avis_admin(avis_user.getRating(), commentaire_user.getText(), ml.getId_u());
                sc.update_avis(aa);
                System.out.println("update oki");
                Notifications notificationBuilder = Notifications.create()
                        .text("Merci Pour votre Contribution ").title("Confirmation").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }

                });
                notificationBuilder.showConfirm();

            } else if (sc.verfication(ml.getId_u()) == false) {
                Avis_admin ab = new Avis_admin(avis_user.getRating(), commentaire_user.getText(), ml.getId_u());
                sc.insertion_avis(ab);
                System.out.println("Insertion oki");
                Notifications notificationBuilder = Notifications.create()
                        .text("Merci Pour votre Contribution ").title("Confirmation").graphic(null).hideAfter(Duration.seconds(3)).position(Pos.CENTER).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }

                });
                notificationBuilder.showConfirm();
            }
        }
    }

    void EnvoyerNote(MouseEvent event) {
        System.out.println("Rate" + "   " + ml.getId_u() + "  " + avis_user.getRating());
    }

    @FXML
    private void afficher_rat(MouseEvent event) {
        if (avis_user.getRating() == 1) {
            comm_note.setTextFill(Color.web("#FF0000"));
            comm_note.setText("Mauvaise");
        } else if (avis_user.getRating() == 2) {
            comm_note.setTextFill(Color.web("#FF4900"));
            comm_note.setText("Assez bien");
        } else if (avis_user.getRating() == 3) {
            comm_note.setTextFill(Color.web("#FFCC00"));
            comm_note.setText("Passable");
        } else if (avis_user.getRating() == 4) {
            comm_note.setTextFill(Color.web("#A6F87E"));
            comm_note.setText("Bien");
        } else if (avis_user.getRating() == 5) {
            comm_note.setTextFill(Color.web("#096a09"));
            comm_note.setText("Parfait ! ");
        }
    }

   @FXML
    private void Goback(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("../GestionComptes/Acceuil.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Acceuil");
        app_stage.show();
    }

    

}
