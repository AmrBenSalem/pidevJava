/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static gui.LeftMenuController.pageNameLabel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class DashboardCoVoiturageController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private JFXDrawer drawerTop;
    
    public static Pane CoVoituragePaneInit;
    
    @FXML
    private Label pageLabel;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private Pane CoVoiturage1;
    @FXML
    private Button buttonOffres;
    @FXML
    private Button buttonOwnOffres;
    @FXML
    private Button buttonDemandes;
    @FXML
    private Button buttonOwnDemandes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        drawerLeft.open();
      //  pageLabel.setText(String.valueOf(LeftMenuController.pageNameLabel));
       
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }    

    @FXML
    private void DisplayOffresAction(ActionEvent event) {
    }

    @FXML
    private void DisplayOwnOffresAction(ActionEvent event) {
    }

    @FXML
    private void DisplayDemandeAction(ActionEvent event) {
    }

    @FXML
    private void DisplayOwnDemandesAction(ActionEvent event) {
    }

  


    
}
