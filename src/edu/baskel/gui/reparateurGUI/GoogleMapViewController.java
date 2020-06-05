/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import com.lynden.gmapsfx.GoogleMapView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.ReparateurCRUD;
import java.util.List;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GoogleMapViewController implements Initializable, MapComponentInitializedListener {

    /**
     * Initializes the controller class.
     */
    @FXML
    private GoogleMapView mapView;

    private GoogleMap Gmap;
    
    LatLong globallatlong ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
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
            Gmap.clearMarkers();
            LatLong joeSmithLocation = new LatLong(ll.getLatitude(), ll.getLongitude());
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(joeSmithLocation);
            Marker joeSmithMarker = new Marker(markerOptions1);
            this.globallatlong=new LatLong(ll.getLatitude(), ll.getLongitude());
            Gmap.addMarker(joeSmithMarker);
        });
        //show markers on maps
        ReparateurCRUD rcrd = new ReparateurCRUD();
        List<Reparateur> lstrep = rcrd.getListeReparateur();
        
        for (Reparateur r : lstrep){
            LatLong ll = new LatLong(Double.parseDouble(r.getLatitude()), Double.parseDouble(r.getLongitude()));
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(ll);
            Marker marker = new Marker(markerOptions1);
            marker.setTitle(r.getNum_pro());
            Gmap.addMarker(marker);
        }

    }

}
