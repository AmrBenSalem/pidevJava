/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class InfoDemandeViewController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    @FXML
    private JFXButton redirectButtonCov;
    @FXML
    private JFXButton redirectButtonCov1;
    @FXML
    private Pane CoVoiturage1;
    @FXML
    private WebView webView;
    @FXML
    private Label labelDepart;
    @FXML
    private Label labelDestination;
    @FXML
    private Label labelQuotidiennement;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelDateD;
    @FXML
    private Label labelPlaceDispo;
    @FXML
    private Pane daysPane;
    @FXML
    private CheckBox lundiButton;
    @FXML
    private CheckBox jeudiButton;
    @FXML
    private CheckBox vendrediButton;
    @FXML
    private CheckBox samediButton;
    @FXML
    private CheckBox mercrediButton;
    @FXML
    private CheckBox mardiButton;
    @FXML
    private ProgressIndicator load;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
    }

    @FXML
    private void redirectToDemandes(ActionEvent event) {
    }
    
}
