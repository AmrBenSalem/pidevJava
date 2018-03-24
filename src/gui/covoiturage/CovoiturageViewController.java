/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXDrawer;
import gui.DashboardCoVoiturageController;
import static gui.DashboardCoVoiturageController.CoVoituragePaneInit;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class CovoiturageViewController implements Initializable {

    @FXML
    private Pane CoVoiturage;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    public static Pane CoVoiturageP;
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
        CoVoiturageP=CoVoiturage;
        drawerLeft.open();
      //  pageLabel.setText(String.valueOf(LeftMenuController.pageNameLabel));
       
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }    

    @FXML
    private void DisplayOffresAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("OffresView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DisplayOwnOffresAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("OwnOffresView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DisplayDemandeAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("DemandesView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DisplayOwnDemandesAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("OwnDemandesView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
    
}
