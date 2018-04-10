/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import com.jfoenix.controls.JFXDrawer;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.MouseEventHandler;

import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;

import entities.Colocation;
import gui.DashboardCoVoiturageController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ColocationCRUD;
import util.Config;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class AjoutOffreController implements Initializable, MapComponentInitializedListener {

    private GoogleMapView mapView;
    private GoogleMap map;
    private int compteur;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private ComboBox<String> meuble_cb;
    @FXML
    private ComboBox<String> type_cb;

    @FXML
    private Button valider_btn;
    @FXML
    private TextField titre_txt;
    @FXML
    private TextField loyer_txt;
    @FXML
    private TextField description_txt;
    @FXML
    private ComboBox<String> ville_cb;
    @FXML
    private TextField lng_txt;
    @FXML
    private TextField lat_txt;
    @FXML
    private TextField photo_path;
    @FXML
    private TextField photo1_path;
    @FXML
    private TextField photo2_path;

    @FXML
    private Button btnUpload;

    @FXML
    private Pane panel;

    private Colocation colocation = new Colocation();
    private ColocationCRUD colocationCRUD = new ColocationCRUD();
    private List<File> listUpload = new ArrayList<File>();
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;
    
    private GeocodingService geocodingService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            drawerLeft.open();
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        loyer_txt.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        init_node();

        mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);

        panel.getChildren().add(mapView);

        // TODO
    }

    @FXML
    private void Valider(ActionEvent event) {

        boolean test = true;

        if (titre_txt.getText().isEmpty()) {
            test = false;

            showMessage("Champs Titre réquis", "Veuillez saisir titre");
        } else if (loyer_txt.getText().isEmpty()) {
            test = false;

            showMessage("Champs Loyer réquis", "Veuillez saisir loyer");
        } else if (meuble_cb.getValue() == null) {
            test = false;

            showMessage("Champs Meuble réquis", "Veuillez saisir Meuble");
        } else if (description_txt.getText().isEmpty()) {
            test = false;

            showMessage("Champs Description  réquis", "Veuillez saisir Description");
        } else if (type_cb.getValue() == null) {
            test = false;

            showMessage("Champs Type réquis", "Veuillez saisir Type");
        } else if (ville_cb.getValue() == null) {
            test = false;

            showMessage("Champs Ville réquis", "Veuillez saisir Ville");
        } else if (lng_txt.getText().isEmpty()) {
            test = false;

            showMessage("Champs Longitude  réquis", "Veuillez saisir Longitude");
        } else if (lat_txt.getText().isEmpty()) {
            test = false;

            showMessage("Champs Latitude  réquis", "Veuillez saisir Latitude");
        }

        if (test) {
            colocation = new Colocation();
            colocation.setTitre(titre_txt.getText());
            colocation.setNature("Offre");
            colocation.setDescription(description_txt.getText());
            colocation.setLoyer(Float.parseFloat(loyer_txt.getText()));
            colocation.setMeuble(meuble_cb.getValue());

            colocation.setVille(ville_cb.getValue());
            colocation.setUser_id(Config.idUser);
            colocation.setEnable(1);
            colocation.setType(type_cb.getValue());
            colocation.setPhoto(photo_path.getText());
            colocation.setPhoto1(photo1_path.getText());
            colocation.setPhoto2(photo2_path.getText());
            colocation.setX(Double.parseDouble(lat_txt.getText()));
            colocation.setY(Double.parseDouble(lng_txt.getText()));

            colocationCRUD.ajouterColocation(colocation);

            for (File source : listUpload) {
                try {
                    File destination = new File("src/images/colocation/" + source.getName());
                    copyFileUsingStream(source, destination);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutOffreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    private void init_node() {

        type_cb.getItems().addAll("maison", "studio");
        meuble_cb.getItems().addAll("meuble", "partiellement meuble","non meuble");
        ville_cb.getItems().addAll("Tunis", "Nabeul", "Bizerte","Hammamet","Ben arous",
                "Nabeul","Kef","Béja","Jandouba","Gabes","Jendouba","Kairouan","Sousse",
                "tozeur","Monastir","Sfax","Tatouine","Manouba","Medenine");

    }

    @FXML
    public void upload(ActionEvent actionEvent) {

        String f = new File("src/images/colocation").getAbsolutePath();

        Window stage = btnUpload.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {

            if (compteur == 0) {
                photo_path.setText(file.getName());
                compteur++;
                listUpload.add(file);
            } else if (compteur == 1) {
                photo1_path.setText(file.getName());
                compteur++;
                listUpload.add(file);
            } else if (compteur == 2) {
                photo2_path.setText(file.getName());
                compteur++;
                btnUpload.setDisable(true);
                listUpload.add(file);
            }
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
     geocodingService = new GeocodingService();
        mapOptions.center(new LatLong(36.807228, 10.181179))
                // .mapType(MapType.SELF_ILLUM)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        MouseEventHandler mouseClick = new MouseEventHandler() {

            @Override
            public void handle(GMapMouseEvent gmme) {
                map.clearMarkers();
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(gmme.getLatLong())
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);

                map.addMarker(marker);
                
                
                 geocodingService.reverseGeocode(gmme.getLatLong().getLatitude(),
                        gmme.getLatLong().getLongitude(), new GeocodingServiceCallback() {
                    @Override
                    public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
                        ville_cb.setValue(grs[0].getFormattedAddress());

                    }
                });
                
                
                lat_txt.setText(String.valueOf(gmme.getLatLong().getLatitude()));
                lng_txt.setText(String.valueOf(gmme.getLatLong().getLongitude()));

            }
        };

        //Add a marker to the map
        map.addMouseEventHandler(UIEventType.click, mouseClick);

    }

    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }

    private void showMessage(String head, String body) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(head);
        alert.setContentText(body);

        alert.showAndWait();
    }

}

