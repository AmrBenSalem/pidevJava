/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class ViewEventController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel1;
    @FXML
    private Pane CoVoiturage1;
    @FXML
    private ImageView image;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton participer;
    @FXML
    private JFXTextField nbmax;
    @FXML
    private GoogleMapView map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titre.setEditable(false);
        description.setEditable(false);
        nbmax.setEditable(false);
        
        Event e = new Event();
        ServiceEvent se = new ServiceEvent();
        
        
    }    
    
}
