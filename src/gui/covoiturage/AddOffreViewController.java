/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
import entities.Adresse;
import entities.CoVoiturage;
import entities.CoVoiturageDays;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import services.ServiceCoVoiturage;
import util.GooglePlacesAPI;
import util.TimeSpinner;

public class AddOffreViewController implements Initializable {

    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    @FXML
    private Pane CoVoiturage1;
    @FXML
    private WebView webView;
    @FXML
    private TextField departTextField;
    @FXML
    private TextField destinationTextField;
    @FXML
    private TextField placeTextField;
    @FXML
    private Pane datePane;
    @FXML
    private DatePicker dateTextField;
    @FXML
    private Pane daysPane;
    @FXML
    private JFXToggleButton quotidiennement;
    @FXML
    private CheckBox lundiButton;
    @FXML
    private CheckBox mardiButton;
    @FXML
    private CheckBox mercrediButton;
    @FXML
    private CheckBox jeudiButton;
    @FXML
    private CheckBox vendrediButton;
    @FXML
    private CheckBox samediButton;
    public boolean check = false;
    @FXML
    private JFXButton redirectButtonCov;
    @FXML
    private JFXButton redirectButtonCov1;
    @FXML
    private ProgressIndicator load;
    @FXML
    private AnchorPane parent;

    static Adresse origin;
    static Adresse destination;

    static double originLat;
    static double originLng;
    static double destLat;
    static double destLng;
    static String onoff = "off";
    static String timee;
    static LocalDateTime dt;
    @FXML
    private JFXButton buttonSubmit;
    @FXML
    private Pane timePane;
    TimeSpinner timeSpinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeSpinner = new TimeSpinner();
        timePane.getChildren().add(timeSpinner);
        drawerLeft.open();
        //  pageLabel.setText(String.valueOf(LeftMenuController.pageNameLabel));

        GridPane container = new GridPane();
        HBox searchBox = new HBox();

        GridPane container2 = new GridPane();
        HBox searchBox2 = new HBox();

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        daysPane.setVisible(false);
        datePane.setVisible(true);

        originLat = 36.77159839999999;
        originLng = 10.2768388;
        destLat = 36.9173042;
        destLng = 10.2852532;
        setParams(originLat, originLng, destLat, destLng, parent);

        container.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        container2.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));

        parent.getChildren().add(container);
        searchBox.getChildren().add(departTextField);
        container.add(searchBox, 0, 0);
        container.setLayoutX(135);
        container.setLayoutY(230);

        parent.getChildren().add(container2);
        searchBox2.getChildren().add(destinationTextField);
        container2.add(searchBox2, 0, 0);
        container2.setLayoutX(135);
        container2.setLayoutY(315);

        departTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (container.getChildren().size() > 1) { // if already contains a drop-down menu -> remove it 
                container.getChildren().remove(1);
            }
            container.add(populateDropDownMenuOrigin(newValue, departTextField), 0, 1); // then add the populated drop-down menu to the second row in the grid pane

        });

        destinationTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (container2.getChildren().size() > 1) { // if already contains a drop-down menu -> remove it 
                container2.getChildren().remove(1);
            }
            container2.add(populateDropDownMenuDest(newValue, destinationTextField), 0, 1); // then add the populated drop-down menu to the second row in the grid pane

        });
        
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        timeSpinner.valueProperty().addListener((obs, oldTime, newTime)
                -> timee=formatter.format(newTime));*/
        DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("hh:mm:ss");
        timeSpinner.valueProperty().addListener((obs, oldTime, newTime)
                -> dt=newTime.atDate(dateTextField.getValue()));
        
    }

    @FXML
    private void onOffAction(ActionEvent event) {
        if (check) {
            onoff = "off";
            daysPane.setVisible(false);
            datePane.setVisible(true);
            check = false;
        } else {
            onoff = "on";
            daysPane.setVisible(true);
            datePane.setVisible(false);
            check = true;
        }
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

    public VBox populateDropDownMenuOrigin(String text, TextField textx) {
        List<Adresse> opt = GooglePlacesAPI.autoCompleteAddress(textx.getText());
        VBox dropDownMenu = new VBox();
        //dropDownMenu.setBackground(new Background(new BackgroundFill(Color.GREEN, null,null))); // colors just for example
        dropDownMenu.setAlignment(Pos.CENTER); // all these are optional and up to you

        for (Adresse option : opt) { // loop through every String in the array
            // if the given text is not empty and doesn't consists of spaces only, as well as it's a part of one (or more) of the options
            String lll = option.getCity() + "," + option.getCountry();
            if (!text.replace(" ", "").isEmpty() && lll.toUpperCase().contains(text.toUpperCase())) {
                Label label = new Label(lll); // create a label and set the text 
                label.setOnMouseClicked((event) -> {
                    //System.out.println(option);
                    //System.out.println("ddeeeefffff");
                    textx.setText(lll);
                    origin = option;
                    originLat = option.getLatitude();
                    originLng = option.getLongitude();
                    dropDownMenu.setVisible(false);

                    setParams(originLat, originLng, destLat, destLng, parent);

                });
                // you can add listener to the label here if you want
                // your user to be able to click on the options in the drop-down menu
                dropDownMenu.getChildren().add(label); // add the label to the VBox
            }

        }

        return dropDownMenu; // at the end return the VBox (i.e. drop-down menu)
    }

    public VBox populateDropDownMenuDest(String text, TextField textx) {
        List<Adresse> opt = GooglePlacesAPI.autoCompleteAddress(textx.getText());
        VBox dropDownMenu = new VBox();
        //dropDownMenu.setBackground(new Background(new BackgroundFill(Color.GREEN, null,null))); // colors just for example
        dropDownMenu.setAlignment(Pos.CENTER); // all these are optional and up to you

        for (Adresse option : opt) { // loop through every String in the array
            // if the given text is not empty and doesn't consists of spaces only, as well as it's a part of one (or more) of the options
            String lll = option.getCity() + "," + option.getCountry();
            if (!text.replace(" ", "").isEmpty() && lll.toUpperCase().contains(text.toUpperCase())) {
                Label label = new Label(lll); // create a label and set the text 
                label.setOnMouseClicked((event) -> {

                    System.out.println(option);
                    textx.setText(lll);
                    destination = option;
                    destLat = option.getLatitude();
                    destLng = option.getLongitude();
                    dropDownMenu.setVisible(false);
                    setParams(originLat, originLng, destLat, destLng, parent);

                });
                // you can add listener to the label here if you want
                // your user to be able to click on the options in the drop-down menu
                dropDownMenu.getChildren().add(label); // add the label to the VBox
            }
        }

        return dropDownMenu; // at the end return the VBox (i.e. drop-down menu)
    }

    @FXML
    private void submitAdd(ActionEvent event) {
        try {

            ServiceCoVoiturage scov = new ServiceCoVoiturage();
            
            Timestamp ts = Timestamp.valueOf(dt);
            System.out.println(ts);
            //Timestamp t = new Timestamp(dateTextField.getValue().getYear(),dateTextField.getValue().getMonthValue(), dateTextField.getValue().getDayOfMonth(),, 0, 0, 0)
            
            
            CoVoiturage cov = new CoVoiturage(5, "o", departTextField.getText(), destinationTextField.getText(),ts, onoff, Integer.parseInt(placeTextField.getText()), origin.getPlaceId(), destination.getPlaceId(), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), origin.getLatitude(), origin.getLongitude());
            if (onoff == "on") {
                String lundi = null;
                String mardi = null;
                String mercredi = null;
                String jeudi = null;
                String vendredi = null;
                String samedi = null;
                if (lundiButton.isSelected()) {
                    lundi = "y";
                }
                if (mardiButton.isSelected()) {
                    mardi = "y";
                }
                if (mercrediButton.isSelected()) {
                    mercredi = "y";
                }
                if (jeudiButton.isSelected()) {
                    jeudi = "y";
                }
                if (vendrediButton.isSelected()) {
                    vendredi = "y";
                }
                if (samediButton.isSelected()) {
                    samedi = "y";
                }
                CoVoiturageDays cod = new CoVoiturageDays(lundi, mardi, mercredi, jeudi, vendredi, samedi, 0);
                scov.addCoVoiturage(cov, cod);
            } else {
                scov.addCoVoiturage(cov);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddOffreViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
