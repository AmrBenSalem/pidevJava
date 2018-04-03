/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import ja.*;
import com.jfoenix.controls.JFXDrawer;
import gui.DashboardCoVoiturageController;
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
public class ObjetViewController implements Initializable {

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
    private Button ajoutobjperd;
    @FXML
    private Button affichobjperd;
    @FXML
    private Button ajoutobjtrouv;
    @FXML
    private Button affichobjtrouv;

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
    private void ajoutObjTrouvAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("ajoutObjTrouv.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

@FXML
    private void affichObjTrouvAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("affichObjTrouvView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
    @FXML
    private void ajoutObjPerdAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("ajoutObjPerd.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

        @FXML
    private void affichObjPerdAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("affichObjPerdView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
 
    
}
