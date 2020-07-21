/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.Admin;

import edu.baskel.entities.Membre;
import edu.baskel.utils.ConnectionBD;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private ImageView back;
    Membre ml = SessionInfo.getLoggedM();
    @FXML
    private Label titre;
   

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
            String query = "select nom_u , prenom_u ,nbr_visite from membre m inner join statistique s on m.Id_u=s.id_u order by nbr_visite desc limit 4";
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            ResultSet rs = cnxs.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(1)+" "+rs.getString(2),rs.getDouble(3)));
            }
            series.setName("Membres Baskel.tn");
            bar_chart.getData().add(series);
            bar_chart.setTitle("Membres Plus actifs");
            

        } catch (SQLException ex) {
ex.printStackTrace();        }

    }
 
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

    
