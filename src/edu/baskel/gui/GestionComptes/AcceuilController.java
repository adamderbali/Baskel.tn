/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.icons525.Icons525View;
import edu.baskel.entities.Membre;
import edu.baskel.services.MembreCRUD;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AcceuilController implements Initializable {

    @FXML
    private MenuButton btnCompte;
    @FXML
    private Button btnReparateur;
    @FXML
    private MenuItem btnSEdeconnecter;
    @FXML
    private MenuItem btnMOnPRofil;
    @FXML
    private AnchorPane ANchorProfil;
    @FXML
    private AnchorPane anchorproincipal;
    @FXML
    private Label lblCntactezNous;
    @FXML
    private Rating rateUs;
    @FXML
    private Text evaluez;
    @FXML
    private JFXButton btnEnvoyer;
    @FXML
    private Button btnforum;
    @FXML
    private ImageView Logout;
    @FXML
    private ImageView exit;
    @FXML
    private Button btnAdmin;
    @FXML
    private MenuButton btnEvenements;
    @FXML
    private MenuItem btnConsulterEv;
    @FXML
    private MenuItem btnGererEv;
    @FXML
    private Button btnRec;
    @FXML
    private Button btnVelo;
    @FXML
    private Icons525View fbIcon;
    @FXML
    private MenuItem btnHistorique;
    @FXML
    private Button btnEvaluez;

    MembreCRUD mc = new MembreCRUD();
    Membre l = SessionInfo.loggedM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("****************" +SessionInfo.loggedM);
        if (mc.TypeUser() == false) {
            btnAdmin.setVisible(true);
        } else {
            btnAdmin.setVisible(false);
        }
    }

    @FXML
    public void Deconnexion(ActionEvent event) {

    }

//afficher page profil a partir de l acceuil
    
    @FXML
    public void Profil(ActionEvent event) throws IOException {
        if ((l.getType_u().equals("U"))) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilPage.fxml"));
            ANchorProfil.getChildren().setAll(pane);
        } else if ((l.getType_u().equals("R"))) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ProfilPageReparateur.fxml"));
            ANchorProfil.getChildren().setAll(pane);
        }
    }

    //se deconnecter a partir de l acceuil
    @FXML
    public void Deco(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) btnCompte).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
        mc.Deconnexion();
        /*
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        ANchorProfil.getChildren().setAll(pane);
         */
    }

    //redirection contact us
    @FXML
    public void RedirectionContactezNs(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("ContactUs.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

    //redirection forum
    @FXML
    void forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ForumFXML.fxml"));
        ANchorProfil.getChildren().setAll(pane);
        /*
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("ForumFXML.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();*/
    }

    //se decconnecter
    @FXML
    void Deconnexion2(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Sidentifier.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
        mc.Deconnexion();
    }

    @FXML
    void RedirectionAdmin(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("../Admin/admin.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

    @FXML
    void REdirectionRec(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("../Admin/Affichage_user.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

    @FXML
    void RedirectionConsulterEV(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Evenements/List_Event_Add_Participation.fxml"));
        ANchorProfil.getChildren().setAll(pane);
    }

    @FXML
    void RedirectionGererEvenemn(ActionEvent event) throws IOException {
       
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Evenements/Gerer.fxml"));
        ANchorProfil.getChildren().setAll(pane);
    }

    @FXML
    void RedirectionVelos(ActionEvent event) throws IOException {
        // AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("/edu.baskel.gui.velo/Afficher_Tout_Velo.fxml"));
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../velo/Afficher_Tout_Velo.fxml"));
        ANchorProfil.getChildren().setAll(pane);

    }

    @FXML
    void PageFB(MouseEvent event) {
        /*    WebView vieww = new WebView();
        final WebEngine web = vieww.getEngine();
        String urlweb = "https://facebook.com/";
        web.load(urlweb);
        ANchorProfil.getChildren().setAll(vieww);*/
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Pictures\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/Baskel395/");
        //driver.manage().window().maximize();

    }

    @FXML
    void RedirectionReparateur(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../reparateurGUI/MenuReparateur.fxml"));
        ANchorProfil.getChildren().setAll(pane);
    }

    @FXML
    void COnsulterAvisEvent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Evenements/Historique des evenements.fxml"));
        ANchorProfil.getChildren().setAll(pane);
    }

    @FXML
    void RediEval(ActionEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("../Admin/avis_app.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.show();
    }

//fermer l application
    @FXML
    void Quitter(MouseEvent event) {
        Platform.exit();
        mc.Deconnexion();

    }

}
