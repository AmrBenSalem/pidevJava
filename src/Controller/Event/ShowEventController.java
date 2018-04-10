/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class ShowEventController implements Initializable {

    @FXML
    private AnchorPane container_ajout;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    @FXML
    private JFXButton redirectButtonCov;
    @FXML
    private Pane CoVoiturage1;
    @FXML
    private Pane datePane;
    @FXML
    private DatePicker dateTextField;
    @FXML
    private Pane daysPane;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField lieu;
    @FXML
    private JFXButton photo;
    @FXML
    private JFXTextField nbmax;
    @FXML
    private JFXComboBox<?> categorie;
    @FXML
    private JFXButton ajout;
    @FXML
    private Label lab_erreur;
    @FXML
    private Label warning;
    @FXML
    private Rating rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rate.setVisible(true);
        rate.setRating(5);
        // TODO
    }    

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
    }

    @FXML
    private void charger(ActionEvent event) {
    }

    @FXML
    private void ajout(ActionEvent event) {
    }

    @FXML
    private void retour_events(ActionEvent event) {
    }
    
}
