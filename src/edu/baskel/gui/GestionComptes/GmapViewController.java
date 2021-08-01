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
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField mail;
    @FXML
    private TextField tele;
    @FXML
    private TextField passe;
    @FXML
    private TextField Cpasse;
    @FXML
    private TextField telpro;
    
    public static String ff;
    public static LatLong ll;
   

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

       // Gmap = mapView.createMap(mapOptions, false);

       
        Gmap.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            longitude.setText(String.valueOf(ll.getLongitude()));
            latitude.setText(String.valueOf(ll.getLatitude()));
            System.out.println("++" + latitude.getText());
            System.out.println("++" + longitude.getText());
           // Gmap.clearMarkers();
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
        nmp.setTxtNom(nom.getText());
        nmp.setTxtPrenom(prenom.getText());
        nmp.setTxtemail(mail.getText());
        nmp.setTxtAdresse(adresse.getText());
        nmp.setTxtmotdepasse(passe.getText());
        nmp.setTxtconfirmation(Cpasse.getText());
        nmp.setTxttelephone(tele.getText());
        nmp.setTxttelpro(telpro.getText());
        System.out.println(telpro.getText());
        latitude.getScene().setRoot(root2);
        

    }
    

    public TextField getLatitude() {
        return latitude;
    }

    public TextField getLongitude() {
        return longitude;
    }

    public void setLatitude(TextField latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(TextField longitude) {
        this.longitude = longitude;
    }

   
    public TextField getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public TextField getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.setText(prenom);
    }

    public TextField getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse.setText(adresse);
    }

    public TextField getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.setText(mail);
    }

    public TextField getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele.setText(tele);
    }
    public TextField getTelpro() {
        return telpro;
    }

    public void setTelpro(String telrpo) {
        this.telpro.setText(telrpo);
    }

    public TextField getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe.setText(passe); 
    }

    public TextField getCpasse() {
        return Cpasse;
    }

    public void setCpasse(String Cpasse) {
        this.Cpasse.setText(Cpasse);
    }
    
    

}
