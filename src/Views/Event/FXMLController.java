/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Event;

import Views.Event.EmbeddedWebView;
import services.ServiceEvent;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Jasser
 */
public class FXMLController implements Initializable {
    @FXML
    private Button gomap;
    @FXML
    private Button longlatmap;
    @FXML
    private JFXTextField loginput;
    @FXML
    private JFXTextField latinput;
    @FXML
    private EmbeddedWebView embeddedWebView;
    
    static String latetude;
    static String longitude;
    double lat;
    double lon;
    /**
     * Initializes the controller class.
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        ServiceEvent se =  new ServiceEvent();
        //se.setLatLong(latinput.getText(), loginput.getText(), 82);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gomap.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    lat = Double.parseDouble(latinput.getText());
                    lon = Double.parseDouble(loginput.getText());

                    
                    embeddedWebView.execstring("" +
                        "window.lat = " + lat + ";" +
                        "window.lon = " + lon + ";" +
                        "document.goToLocation(window.lat, window.lon);"
                    );
                }
            });
         longlatmap.setOnAction(e->{
         embeddedWebView.execstring("tes();");
         latinput.setText(latetude);
         loginput.setText(longitude);
         
         });
    }    
    
}
