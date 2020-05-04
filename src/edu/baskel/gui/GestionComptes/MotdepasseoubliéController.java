/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import edu.baskel.entities.Membre;
import edu.baskel.services.EnvoiMail;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MotdepasseoubliéController implements Initializable {

    @FXML
    private Button btnenv;

    @FXML
    private TextField txtentermail;

    @FXML
    private TextField txtcode;

    @FXML
    private JFXButton btnverifycode;

    @FXML
    private Button btnQuitter;

    @FXML
    private Button btnDeconnexion;

    @FXML
    private Button btnSupp;
    @FXML
    private Button btnevenmn;


    Connection cnx = null;
    PreparedStatement prep = null;
    PreparedStatement prep1 = null;
    ResultSet res = null;
    ResultSet res1 = null;
    private String question;
    private String motdepasse;
    int ran;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public MotdepasseoubliéController() {
        cnx = ConnectionBD.getInstance().getCnx();

    }
    EnvoiMail e = new EnvoiMail();

    @FXML
    public void envoyerMail(ActionEvent event) {

        e.envoyerMail1(txtentermail.getText());
        /* try {
            Random rann = new Random();
            ran = rann.nextInt(999999);
            System.out.println("Préparation de l envoie du mail");
            String host = "smtp.gmail.com";
            String user = "derbaliadam@gmail.com";
            String pass = "gomugomuNOADAM";
            String to = txtentermail.getText();
            String subject = "reseting password";
            String message = "Votre code de reinitalisation est " + " " + ran;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");

        } catch (Exception ex) {

        }*/
    }

    @FXML
    public void backSidentifier(MouseEvent event) throws IOException {

        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();

    }
       @FXML
    void redirectionevenmn(ActionEvent event) throws IOException {
   Parent redirection_parent = FXMLLoader.load(getClass().getResource("Affichage_Modifier_Supprimer_User.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

    @FXML
    public void verifierCode(ActionEvent event) throws IOException {
        if ((e.verifierCode1(txtcode.getText()) == true)) /*if (Integer.valueOf(txtcode.getText()) == ran) */ {
           /* Parent redirection_parent = FXMLLoader.load(getClass().getResource("NouveauMP.fxml"));
            Scene redirection_scene = new Scene(redirection_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(redirection_scene);
            app_stage.setAlwaysOnTop(false);
            app_stage.show();*/
           FXMLLoader loader = new FXMLLoader(getClass().getResource("NouveauMP.fxml"));
           Parent root2 = loader.load();
           NouveauMPController nmp = loader.getController();
           nmp.setTxtm(txtentermail.getText());
           txtentermail.getScene().setRoot(root2);
        } else {
            System.out.println("code erroné");
        }

    }

    @FXML
    void supprimerCompte(ActionEvent event) {
        MembreCRUD mr1 = new MembreCRUD();
        Membre l = SessionInfo.getLoggedM();// a voir bel iduser wela bel loggedm
        mr1.supprimerMembre(l.getId_u());//iduser);
    }

    @FXML  //page d acceuil
    void SeDeconnecter(ActionEvent event) throws IOException {

        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
        SessionInfo.setIduser(0);
        SessionInfo.setLoggedM(null);
        System.out.println(iduser);

    }

    @FXML//page d acceuil
    void Quitter(ActionEvent event) {
        Platform.exit();
    }

}
