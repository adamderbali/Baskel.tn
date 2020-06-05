/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Velo;
import edu.baskel.services.VeloCRUD;
import edu.baskel.utils.AutoAnnee;
import edu.baskel.utils.SessionInfo;
import edu.baskel.utils.validationSaisie;
import edu.baskel.utils.InputValidation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Ajouter_veloController implements Initializable {
    
    
    
     @FXML
    private TextField txtnums;

    @FXML
    private TextField txtmarque;

    @FXML
    private TextField txtmodel;

    @FXML
    private TextField txtprix;

    @FXML
    private TextField txttype;

    @FXML
    private TextField txtann;

    @FXML
    private TextField txtstat;

    @FXML
    private TextField txtim;

    @FXML
    private TextArea txtdesc;

    @FXML
    private Button txtbutajout;
    
      @FXML
    private Button txtannul;
      @FXML
    private Button fileChoose;
      @FXML
    private ImageView img;

    private Image image;
      
     @FXML
    private ChoiceBox<String> statusbox;
    
    Membre m = SessionInfo.getLoggedM();
    
    /* Ajout evenement*/
    @FXML
    void ajouterVelo(ActionEvent event) {

        System.out.println(txtnums.getText());
        System.out.println(txtmarque.getText());
        System.out.println(txtmodel.getText());
        System.out.println(txtprix.getText());
        System.out.println(txtann.getText());
        System.out.println(statusbox.getValue());
        System.out.println(txtim.getText());
        System.out.println(txtdesc.getText());
        
        /* test sur les champs vides ou non*/
        if (((txtnums.getText().isEmpty()) | (txtmarque.getText().isEmpty()) | (txtmodel.getText().isEmpty()) | (txtprix.getText().isEmpty()) | (txttype.getText().isEmpty()) | (txtann.getText().isEmpty()) | (statusbox.getValue() ==null) | (txtim.getText().isEmpty()) | (txtdesc.getText().isEmpty()) )) {
            Alert alertChamps = new validationSaisie().getAlert("Echec", "Veuillez saisir tout les champs");
            alertChamps.showAndWait();

            
        } else {
            
                if((InputValidation.Year(txtann.getText()))== 0){
                    Alert alertChamps = new validationSaisie().getAlert("Echec", "Veuillez saisir une année valide");
            alertChamps.showAndWait();
             }else{
                VeloCRUD Vc = new VeloCRUD();
                Velo v = new Velo(Integer.parseInt(txtnums.getText()), txtmarque.getText(), txtmodel.getText(),Double.parseDouble(txtprix.getText()),txttype.getText(),txtann.getText(),statusbox.getValue(),txtdesc.getText(), txtim.getText());
                System.out.println(v);
                Vc.ajouterVelo(v,m.getId_u());
                Alert alertAdded = new validationSaisie().getAlert("Succés d'ajout", "Vélo ajouté");
                alertAdded.showAndWait();

                txtnums.clear();
                txtmarque.clear();
                
                txtmodel.clear();
                txtprix.clear();
                txttype.clear();
                txtann.clear();
                statusbox.setValue(null);
                txtim.clear();
                img.setImage(null);
                txtdesc.clear();
                //img.setVisible(false);
                try {
             //System.exit(0);
             Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_mes_velos.fxml"));
             Scene redirection_scene = new Scene(redirection_parent);
             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             app_stage.setScene(redirection_scene);
             app_stage.show();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
            Stage stage = (Stage) txtbutajout.getScene().getWindow();
                     // do what you have to do
                    stage.close();

            }
        }

    }
    
    @FXML
    void annulerVelo(ActionEvent event) {
         try {
             //System.exit(0);
             Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_mes_velos.fxml"));
             Scene redirection_scene = new Scene(redirection_parent);
             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             app_stage.setScene(redirection_scene);
             app_stage.show();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
              Stage stage = (Stage) txtannul.getScene().getWindow();
                     // do what you have to do
                    stage.close();
                    
    }
    @FXML

    void telecharger(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);
            System.out.println(photo);
            txtim.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            //photo1 = "C:\\wamp\\www\\Baskel\\images\\" + photo;
            photo1 = "C:\\xampp\\htdocs\\Baskel\\images\\" + photo;
            System.out.println(photo);
            u.CopyImage(photo1, file.toPath().toString());
            img.setImage(image);

        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(txtann, AutoAnnee.getAnnee());
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("A louer", "A vendre");
  //populate the Choicebox;  
       statusbox .setItems(list);
        System.out.println(statusbox.getValue());

    }    
    
}
