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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        LatLong joeSmithLocation = new LatLong(36.837899, 10.1586);
        LatLong joshAndersonLocation = new LatLong(36.857898, 10.165817);
        LatLong bobUnderwoodLocation = new LatLong(36.89599, 10.174817);
        LatLong tomChoiceLocation = new LatLong(36.717899, 10.184867);
        LatLong fredWilkieLocation = new LatLong(36.827899, 10.195227);

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
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);

        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);

        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker = new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);

        Gmap.addMarker(joeSmithMarker);
        Gmap.addMarker(joshAndersonMarker);
        Gmap.addMarker(bobUnderwoodMarker);
        Gmap.addMarker(tomChoiceMarker);
        Gmap.addMarker(fredWilkieMarker);

        /* InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
         infoWindowOptions.content("<h2>Fred Wilkie</h2>"
         + "Current Location: Safeway<br>"
         + "ETA: 45 minutes");

         InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
         fredWilkeInfoWindow.open(Gmap, fredWilkieMarker);*/
            Gmap.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
        LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
                System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
    });
 
    }

}
