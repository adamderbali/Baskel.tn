/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.gui.reparateurGUI;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import edu.baskel.entities.Alerte;
import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.services.AlerteCRUD;
import edu.baskel.services.MailAlerte;
import edu.baskel.services.MailReservation;
import edu.baskel.services.ReparateurCRUD;
import edu.baskel.utils.SessionInfo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutAlerteController implements Initializable, MapComponentInitializedListener {

    @FXML
    private TextField descalrt;
    @FXML
    private TextField adrss;

    private GoogleMap Gmap;
    @FXML
    private GoogleMapView mapView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

        } catch (Exception e) {
        }

    }

    @FXML
    void lancerAjout(ActionEvent event) {
        AlerteCRUD alrtcrd = new AlerteCRUD();
        Alerte a = new Alerte();
        a.setAdresse_a(adrss.getText());
        a.setLatitude_a("1221");
        a.setLongitude_a("1151");
        a.setDescription_a(descalrt.getText());
        Membre m = SessionInfo.loggedM;
        a.setMembre(m);
        ReparateurCRUD rcrd = new ReparateurCRUD();
        List<Reparateur> lstrep = rcrd.getListeReparateur();
        for (Reparateur r : lstrep) {
            MailAlerte mr = new MailAlerte();
            try {
                mr.envoyerMail(r.getEmail_u(), "une alerte à " + a.getAdresse_a() + "par "+ m.getNom_u());
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @Override
    public void mapInitialized() {

        LatLong joeSmithLocation = new LatLong(36.8009, 10.1786);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.8009, 10.1786))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        Gmap = mapView.createMap(mapOptions);

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

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes");

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(Gmap, fredWilkieMarker);

    }

}
