/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.Admin;

import edu.baskel.services.StatCRUD;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Stat_chartController implements Initializable {

    @FXML
    private PieChart pie_chart;
    @FXML
    private AnchorPane pan_chart;

    /**
     * Initializes the controller class.
     */
    Font font = new Font("InfoErrataMsg", Font.BOLD, 60);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        StatCRUD sc = new StatCRUD();
        PieChart pc = new PieChart();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Reclamation_admin",sc.Reclamation_admin_nbr()),
                        new PieChart.Data("Reclamation_user",sc.Reclamation_user_nbr()),
                        new PieChart.Data("Affichage_user",sc.affichage_user_nbr())               
                );
        pie_chart.setData(pieChartData);
        
        for(PieChart.Data data : pie_chart.getData())
        {
            data.nameProperty().set(data.getName()+" "+"%"+data.getPieValue());
        }
        
                
    }
    /*@FXML
    public void goback(KeyEvent event){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Statistique_interface.fxml"));
            pan_chart.getChildren().setAll(pane);
        } catch (IOException ex) {
ex.printStackTrace();        }
    }*/
   
  

    @FXML
    private void Goback(MouseEvent event) throws IOException {
        Parent redirection_parent = FXMLLoader.load(getClass().getResource("Statistique_interface.fxml"));
        Scene redirection_scene = new Scene(redirection_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(redirection_scene);
        app_stage.setTitle("Statistique Interface");
        app_stage.show();
    }

   
    
}
