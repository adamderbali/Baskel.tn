/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.adminGUI;

import edu.baskel.entities.Membre;
import edu.baskel.utils.ConnectionBD;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.baskel.utils.ConnectionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class User_statController implements Initializable {

    Connection cnxs;
    @FXML
    private BarChart<String, Double> bar_chart;
    @FXML
    private AnchorPane anch_stat;
    @FXML
    private NumberAxis number;
    @FXML
    private CategoryAxis ID_user;
   

    public void conxstat() {
        cnxs = ConnectionBD.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadchart();
    }

    private void loadchart() {
        
        try {
            conxstat();
            String query = "select nom_u , prenom_u , nbr_visite from membre m inner join statistique s on m.Id_u=s.id_u order by nbr_visite desc limit 10";
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            ResultSet rs = cnxs.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(1)+" "+rs.getString(2),rs.getDouble(3)));
            }
            bar_chart.getData().add(series);

        } catch (SQLException ex) {
ex.printStackTrace();        }

    }
}
