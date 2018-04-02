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
import com.lynden.gmapsfx.GoogleMapView;
import entities.Event;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class AddEventController implements Initializable {

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
    private JFXTextField titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextField lieu;
    @FXML
    private JFXButton charger;
    @FXML
    private JFXTextField nbmax;
    @FXML
    private JFXComboBox<String> categorie;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;

    ObservableList<String> maincateg = FXCollections.observableArrayList("Randonnée", "Camping", "Anniversaire Club", "Workshop", "Hackathon");
    @FXML
    private GoogleMapView map;
    @FXML
    private JFXButton ajout;
    private String path1;
    @FXML
    private Label lab_erreur;
    @FXML
    private ImageView warning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categorie.setValue("Randonnée");
        categorie.setItems(maincateg);
        lab_erreur.setVisible(false);
        warning.setVisible(false);
    }

    @FXML
    private void charger(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        if (selectedFile != null) {
            path1 = selectedFile.toURI().toURL().toExternalForm();
            charger.setText(path1);
            

        }
    }

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
        ServiceEvent se = new ServiceEvent();
        Event e = new Event();

        if (!(titre.getText().isEmpty())) {
            String masque = "^[a-zA-Z]+$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(titre.getText());
            if (!(controler.matches())) {
                lab_erreur.setText("Titre Invalide");
                lab_erreur.setVisible(true);
                warning.setVisible(true);
                return;
            }

            if ((description.getText().length() < 4)) {
                lab_erreur.setText("Description Invalide");
                lab_erreur.setVisible(true);
                warning.setVisible(true);
                return;
            }
            if (titre.getText().equals("") || charger.getText().equals("") || description.getText().equals("") || datedebut.getValue().equals("") || datefin.getValue().equals("") || lieu.getText().equals("") || nbmax.getText().equals("") || categorie.getSelectionModel().getSelectedItem().equals("")|| charger.getText().equals("")) {
                lab_erreur.setText("veillez remplir tous les champs");
                lab_erreur.setVisible(true);
                warning.setVisible(true);
                return;
            }

            e.setTitre(titre.getText());
            e.setDescription(description.getText());
           
            e.setLieu(lieu.getText());
            e.setCategorie(categorie.getValue());
            e.setX(Integer.parseInt(x.getText()));
            e.setY(Integer.parseInt(y.getText()));
            
            e.setDateDebut((Date) Date.from(datedebut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            e.setDateFin((Date) java.sql.Date.from(datefin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            e.setCategorie(categorie.getSelectionModel().getSelectedItem());
            e.setEnable(0);
            e.setPhoto(charger.getText());
            
            //System.out.println("le champ photo  :  "+charger.getText());
            e.setCreatedAt(Date.valueOf(LocalDateTime.now().toString()));
            se.ajouterEvent(e);
            

        }
    }

}


