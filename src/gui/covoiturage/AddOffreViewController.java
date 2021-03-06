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
import entities.Session;
import entities.User;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.management.Notification;
import services.ServiceCoVoiturage;
import services.UserCRUD;
import util.Capitals;
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
    private DatePicker dateTextField = new DatePicker(LocalDate.now());
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
    static String origin_place_id;
    static String dest_place_id;

    String onoff = "off";
    String timee;
    static LocalDateTime dt;

    GridPane container = new GridPane();
    HBox searchBox = new HBox();

    GridPane container2 = new GridPane();
    HBox searchBox2 = new HBox();

    Capitals c = new Capitals();
    @FXML
    private JFXButton buttonSubmit;
    @FXML
    private Pane timePane;
    TimeSpinner timeSpinner;
    @FXML
    private Label errorLabel;
    public User user = Session.getUser();
    public UserCRUD SUser = new UserCRUD();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        timeSpinner = new TimeSpinner(LocalTime.now());
        timePane.getChildren().add(timeSpinner);
        drawerLeft.open();
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        daysPane.setVisible(false);
        datePane.setVisible(true);

        originLat = c.getCapital().getLatitude();
        originLng = c.getCapital().getLongitude();
        origin_place_id = c.getCapital().getPlaceId();
        dest_place_id = "ChIJUe3GVHTL4hIRV9NcVrU6O2g";
        destLat = 36.898392;
        destLng = 10.189732;
        setParams(originLat, originLng, destLat, destLng, parent);
        departTextField.setText("Votre emplacement");
        destinationTextField.setText("ESPRIT, Ariana, Tunisie");

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

        DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("hh:mm:ss");
        timeSpinner.valueProperty().addListener((obs, oldTime, newTime)
                -> dt = newTime.atDate(dateTextField.getValue()));

        departTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (container.getChildren().size() > 1) {
                container.getChildren().remove(1);
            }
            container.add(populateDropDownMenuOrigin(newValue, departTextField), 0, 1);

        });

        destinationTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (container2.getChildren().size() > 1) {
                container2.getChildren().remove(1);
            }
            container2.add(populateDropDownMenuDest(newValue, destinationTextField), 0, 1);

        });
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
        stage.setResizable(false);
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
        stage.setResizable(false);
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
        dropDownMenu.setAlignment(Pos.CENTER);

        for (Adresse option : opt) {
            String lll = option.getCity() + "," + option.getCountry();
            if (!text.replace(" ", "").isEmpty() && lll.toUpperCase().contains(text.toUpperCase())) {
                Label label = new Label(lll);
                label.setOnMouseClicked((event) -> {
                    for (int k = 0; k < parent.getChildren().size(); k++) {
                        System.out.println(parent.getChildren().get(k));
                    }
                    textx.setText(lll);
                    origin_place_id = option.getPlaceId();
                    originLat = option.getLatitude();
                    originLng = option.getLongitude();
                    dropDownMenu.getChildren().clear();

                    setParams(originLat, originLng, destLat, destLng, parent);

                });
                dropDownMenu.getChildren().add(label);
            }

        }

        return dropDownMenu;
    }

    public VBox populateDropDownMenuDest(String text, TextField textx) {
        List<Adresse> opt = GooglePlacesAPI.autoCompleteAddress(textx.getText());
        VBox dropDownMenu = new VBox();
        dropDownMenu.setAlignment(Pos.CENTER);

        for (Adresse option : opt) {
            String lll = option.getCity() + "," + option.getCountry();
            if (!text.replace(" ", "").isEmpty() && lll.toUpperCase().contains(text.toUpperCase())) {
                Label label = new Label(lll);

                dropDownMenu.getChildren().add(label);
                label.setOnMouseClicked((event) -> {

                    System.out.println(option);
                    textx.setText(lll);
                    dest_place_id = option.getPlaceId();
                    destLat = option.getLatitude();
                    destLng = option.getLongitude();
                    dropDownMenu.getChildren().clear();
                    setParams(originLat, originLng, destLat, destLng, parent);

                });
            }
        }

        return dropDownMenu;
    }

    @FXML
    private void submitAdd(ActionEvent event) {
        if (departTextField.getText().equals("") || destinationTextField.getText().equals("")) {
            errorLabel.setText("Veuillez remplir les champs de départ et destination");
            errorLabel.setVisible(true);
            return;
        } else if (onoff.equals("off") && dt == null) {
            errorLabel.setText("Veuillez remplir la date");
            errorLabel.setVisible(true);
            return;
        } else if (onoff.equals("on")) {
            if (lundiButton.isSelected() == false) {
                if (mardiButton.isSelected() == false) {
                    if (mercrediButton.isSelected() == false) {
                        if (jeudiButton.isSelected() == false) {
                            if (vendrediButton.isSelected() == false) {
                                if (samediButton.isSelected() == false) {
                                    errorLabel.setText("Veuillez selectionner en moins un jour");
                                    errorLabel.setVisible(true);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else if (Integer.parseInt(placeTextField.getText()) > 6 || Integer.parseInt(placeTextField.getText()) < 0) {
            errorLabel.setText("Veuillez saisir un nombre de place entre 1 et 6");
            errorLabel.setVisible(true);
            return;
        }
        ServiceCoVoiturage scov = new ServiceCoVoiturage();
        Timestamp ts = null;
        if (onoff.equals("off")) {
            ts = Timestamp.valueOf(dt);
        }
        System.out.println(departTextField.getText());
        System.out.println(destinationTextField.getText());
        System.out.println(ts);
        System.out.println(onoff);
        System.out.println(placeTextField.getText());
        System.out.println(origin_place_id);
        System.out.println(dest_place_id);
        System.out.println(originLat);
        System.out.println(originLng);
        if (departTextField.getText().equals("Votre emplacement")) {
            departTextField.setText(c.getCapital().getCity() + "," + c.getCapital().getCountry());
        }
        CoVoiturage cov = new CoVoiturage(Session.getUser().getId(), "o", departTextField.getText(), destinationTextField.getText(), ts, onoff, Integer.parseInt(placeTextField.getText()), origin_place_id, dest_place_id, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), originLat, originLng);
        if ("on".equals(onoff)) {
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
        redirectToOffres(event);
    }

}
