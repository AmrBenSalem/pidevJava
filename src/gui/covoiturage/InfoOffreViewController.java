/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import entities.Adresse;
import entities.CoVoiturage;
import entities.CoVoiturageDays;
import gui.DashboardCoVoiturageController;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import services.ServiceCoVoiturageDays;
import util.GooglePlacesAPI;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class InfoOffreViewController implements Initializable {

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
    @FXML
    private JFXButton buttonSubmit;
    @FXML
    private Label labelPlaceDispo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawerLeft.open();
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CoVoiturage cov = OffresViewController.covInfo;
        
        for (int x = 0; x < CoVoiturage1.getChildren().size() ; x++){
           System.out.println(x+"  "+CoVoiturage1.getChildren().get(x).toString());
        }
        
        labelDepart.setText(cov.getDepart());
        labelDestination.setText(cov.getDestination());
        if (cov.getOnetime().equals("on")){
            labelQuotidiennement.setText("Non");
            labelDate.setText("Les jours : ");
            ServiceCoVoiturageDays scod = new ServiceCoVoiturageDays();
            System.out.println(cov);
            CoVoiturageDays cod = scod.GetCovoiturageDays(cov);
            String days = "" ;
            if (cod.getLundi() != null){
                days=days+" Lundi";
            }
            if (cod.getMardi() != null){
                days=days+" Mardi";
            } 
            if (cod.getMercredi() != null){
                days=days+" Mercredi";
            } 
            if (cod.getJeudi() != null){
                days=days+" Jeudi";
            }
            if (cod.getVendredi() != null){
                days=days+" Vendredi";
            } 
            if (cod.getSamedi() != null){
                days=days+" Samedi";
            }
            labelDateD.setText(days);
            labelPlaceDispo.setText( String.valueOf(cov.getPlacedisponibles()));
            
        } else {
            labelDateD.setText( String.valueOf(cov.getDate()));
        }
        
        
        List<Adresse> opt = GooglePlacesAPI.autoCompleteAddress(cov.getDestination());
        setParams(cov.getDepart_lat(), cov.getDepart_lng(),opt.get(0).getLatitude(), opt.get(0).getLongitude(), parent);
        
        
    }   
    
    public void setParams(double originLat, double originLng, double destLat, double destLng, AnchorPane parent) {

        this.parent = parent;
        WebEngine engine = webView.getEngine();
        engine.load(getClass().getResource("/util/MapsView.html").toString());

        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    engine.executeScript("initialize(" + originLat + ", " + originLng + ", " + destLat + ", " + destLng + ")");
                    load.setVisible(false);

                }
            }
        });
    }

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("CoVoiturageView.fxml"));
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
    private void redirectToOffres(ActionEvent event) {
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
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    private void submitAdd(ActionEvent event) {
    }
    
}
