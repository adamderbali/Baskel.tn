/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import edu.baskel.services.StatCRUD;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class Stat_chartController implements Initializable {

    @FXML
    private PieChart pie_chart;

    /**
     * Initializes the controller class.
     */
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
            data.nameProperty().set(data.getName()+" "+"%"+ data.getPieValue());
        }
        
                
    }    
    
}
