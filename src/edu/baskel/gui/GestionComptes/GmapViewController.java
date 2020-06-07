/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.GestionComptes;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GmapViewController implements Initializable, MapComponentInitializedListener {

    /**
     * Initializes the controller class.
     */
    @FXML
    private GoogleMapView mapView;

    private GoogleMap Gmap;

    @FXML
    private TextField latitude;

    @FXML
    private TextField longitude;
    @FXML
    private JFXButton longLati;
    public static String ff;
    public static LatLong ll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
/*longLati.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionReparateur.fxml"));
        Parent root2;
            root2 = loader.load();
            InscriptionReparateurController nmp = loader.getController();
        nmp.setTxtadrlocal(latitude.getText());
        latitude.getScene().setRoot(root2);
        } catch (IOException ex) {
            Logger.getLogger(GmapViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
});*/
    }

    @Override
    public void mapInitialized() {

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.817899, 10.1786))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        Gmap = mapView.createMap(mapOptions, false);

        //Add markers to the map
        /* InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
         infoWindowOptions.content("<h2>Fred Wilkie</h2>"
         + "Current Location: Safeway<br>"
         + "ETA: 45 minutes");

         InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
         fredWilkeInfoWindow.open(Gmap, fredWilkieMarker);*/
        Gmap.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            longitude.setText(String.valueOf(ll.getLongitude()));
            latitude.setText(String.valueOf(ll.getLatitude()));
            System.out.println(ff);
            System.out.println("++" + latitude.getText());
            System.out.println("++" + longitude.getText());
            Gmap.clearMarkers();
            LatLong joeSmithLocation = new LatLong(ll.getLatitude(), ll.getLongitude());
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(joeSmithLocation);
            Marker joeSmithMarker = new Marker(markerOptions1);
            Gmap.addMarker(joeSmithMarker);
            
        });

    }
    
    

@FXML
    void longLat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionReparateur.fxml"));
        Parent root2 = loader.load();
        InscriptionReparateurController nmp = loader.getController();
        nmp.setTxtadrlocal2(latitude.getText());
        nmp.setTxtadrlocal1(longitude.getText());
        latitude.getScene().setRoot(root2);
        
            
        
    }


    
    
    

   
}
