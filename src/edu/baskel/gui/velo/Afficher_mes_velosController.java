/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.velo;

import edu.baskel.entities.Velo;
import edu.baskel.services.VeloCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hela
 */
public class Afficher_mes_velosController implements Initializable {
    
    
     @FXML
    private TableView<Velo> TabAffVelo;
@FXML
    private TableColumn<Velo,String> ColImage;

    @FXML
    private TableColumn<Velo,String> Colnums;

    @FXML
    private TableColumn<Velo,String> Colmarque;

    @FXML
    private TableColumn<Velo,String> Colmodel;

    @FXML
    private TableColumn<Velo,String> Colprix;
    
    @FXML
    private TableColumn<Velo,String> Coltype;
    
    @FXML
    private TableColumn<Velo,String> ColStatut;
    
    @FXML
    private TableColumn<Velo,Button> ColButt;
    
    @FXML
    private AnchorPane detailWin;
     @FXML
    private Button butTout;

     @FXML
    private Button resbut;

     @FXML
    private Button allresbv;
     
     @FXML
    private Button ajbut;

    @FXML
    void ajouterVelo(ActionEvent event) {
        try {
              Parent redirection_parent = FXMLLoader.load(getClass().getResource("Ajouter_velo.fxml"));
              Scene redirection_scene = new Scene(redirection_parent);
              Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              app_stage.setScene(redirection_scene);
              app_stage.show();
               Stage stage = (Stage) ajbut.getScene().getWindow();
                // do what you have to do
                 stage.close();
              //System.out.println("hello from my bikes");
          } catch (IOException ex) {
              ex.printStackTrace();
          }
    }
     @FXML
    void consultertouteReservation(ActionEvent event) {
         try {
              Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_Reservation_User.fxml"));
              Scene redirection_scene = new Scene(redirection_parent);
              Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              app_stage.setScene(redirection_scene);
              app_stage.show();
              //System.out.println("hello from my bikes");
          } catch (IOException ex) {
              ex.printStackTrace();
          }
    }
    @FXML
    void consulterReservation(ActionEvent event) {
             try {
              Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_mes_reservations.fxml"));
              Scene redirection_scene = new Scene(redirection_parent);
              Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              app_stage.setScene(redirection_scene);
              app_stage.show();
              //System.out.println("hello from my bikes");
          } catch (IOException ex) {
              ex.printStackTrace();
          }
    }

    @FXML
    void consultertout(ActionEvent event) {
            try {
              Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_Tout_Velo.fxml"));
              Scene redirection_scene = new Scene(redirection_parent);
              Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              app_stage.setScene(redirection_scene);
              app_stage.show();
              //System.out.println("hello from my bikes");
          } catch (IOException ex) {
              ex.printStackTrace();
          }
    }

    public Afficher_mes_velosController() {
    }
    
    
    
   
    
    Velo v;
    @FXML
    void chargerDonnee() {
             v = TabAffVelo.getSelectionModel().getSelectedItem();
             
             System.out.println(v);
             
             
             
    }
    public Velo getClickedVelo() {
       return v;
    }
    public String getEnteredText() {
       return String.valueOf(v.getNum_serie());
    }
    
    public void affichageVelo() {
       // TableColumn buttn = new TableColumn("");
        //TabAffVelo.getColumns().add(buttn);
        System.out.println("hellooo");
        VeloCRUD Vc =new VeloCRUD();
        ArrayList av;
        av=(ArrayList) Vc.afficherVeloUser(1);
        ObservableList o;
        o= FXCollections.observableArrayList(av);
        
        
        
        /*Callback<TableColumn<Velo, String>, TableCell<Velo, String>> cellFactory;
        ColImage.setCellValueFactory(new PropertyValueFactory<Velo, String>("image_v"));
        cellFactory = new Callback<TableColumn<Velo, String>, TableCell<Velo, String>>() {
            @Override
             public TableCell<Velo, String> call(TableColumn<Velo, String> param) {
                                                        
             ImageView imageview = new ImageView();                      
             return new TableCell<Velo, String>() {
                     @Override
                      public void updateItem(String item, boolean empty) {
                      //super.updateItem(item, empty);
                      if (item != "") {
                        Image img=null;
                        Velo v =getTableView().getItems().get(getIndex());
                        img=new Image(item);
                        imageview.setImage(img);
                      }
                    
                     setGraphic(imageview);
                       } 
             };
                     }
               };
        
               ColImage.setCellFactory(cellFactory);*/
                
                
                
        
      
      
       //ColImage.setCellValueFactory(new PropertyValueFactory<Velo, String>("image_v"));
       Colnums.setCellValueFactory(new PropertyValueFactory <>("num_serie"));
       Colmarque.setCellValueFactory(new PropertyValueFactory <>("marque"));
       Colmodel.setCellValueFactory(new PropertyValueFactory <>("model"));
       Colprix.setCellValueFactory(new PropertyValueFactory <>("prix_v"));
       Coltype.setCellValueFactory(new PropertyValueFactory <>("type_v"));
       ColStatut.setCellValueFactory(new PropertyValueFactory <>("status_v"));
       //ColButt.setCellValueFactory(new PropertyValueFactory <>("button"));
       TabAffVelo.setItems(o);
       TabAffVelo.setRowFactory( tv -> {
       TableRow<Velo> row = new TableRow<>();
          row.setOnMouseClicked(event -> {
             if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Velo rowData = row.getItem();
                //System.out.println("mdkgd"+rowData);
                 System.out.println(rowData.getNum_serie());
                Parent root;
                 //try {
                      
                     Afficher_mes_velos_detailsController controller2 = new Afficher_mes_velos_detailsController(this);
                     controller2.showStage();
                     
                     /*System.out.println(rowData);
                     Parent redirection_parent = FXMLLoader.load(getClass().getResource("Afficher_detail.fxml"));
                     Scene redirection_scene = new Scene(redirection_parent);
                     Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     app_stage.setScene(redirection_scene);
                     app_stage.show();*/
                 /*} catch (IOException ex) {
                     System.out.println("error");
                     ex.printStackTrace();
                 }*/
                
        
       
        
      
            }
         });
          return row ;
         });
        
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hiii");
        affichageVelo();
    }    
    
}
