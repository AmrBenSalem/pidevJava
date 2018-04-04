/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import javafx.collections.FXCollections;
import java.net.MalformedURLException;
import javafx.collections.ObservableList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class AddEventController implements Initializable {

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
    private Pane CoVoiturage1;
    @FXML
    private WebView webView;
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
    private JFXComboBox<String> categorie;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXButton ajout;
    @FXML
    private Label lab_erreur;
    @FXML
    private Label warning;
    private String path1;
    public String pathString;
    
    ObservableList<String> maincateg = FXCollections.observableArrayList("Randonnée", "Camping", "Anniversaire Club", "Workshop", "Hackathon");

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
    private void redirectToCoVoiturage(ActionEvent event) {
    }

    @FXML
    private void charger(ActionEvent event) throws IOException{
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour votre Evénement");
        Window stage = null;
        File file;
        file = fileChooser.showOpenDialog(stage);
        pathString = "src/assets/" + file.getName();
        Image image = new Image("file:" + file.getPath());

        Path pth = file.toPath();

        File resourcesDirectory = new File("src/assets/" + file.getName());
        Files.deleteIfExists(resourcesDirectory.toPath());
        File symfonyDirectory = new File("C:/wamp64/www/pidev2/web/"+file.getName());
        Files.copy(pth, symfonyDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        Files.copy(pth, resourcesDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        
    }

    @FXML
    private void ajout(ActionEvent event) {
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
            if (titre.getText().equals("") || photo.getText().equals("") || description.getText().equals("") || datedebut.getValue().equals("") || datefin.getValue().equals("") || lieu.getText().equals("") || nbmax.getText().equals("") || categorie.getSelectionModel().getSelectedItem().equals("")|| photo.getText().equals("")) {
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
            
            e.setDateDebut(Date.valueOf(datedebut.getValue()));
            e.setDateFin(Date.valueOf(datefin.getValue()));
            e.setCreatedAt(Date.valueOf(datedebut.getValue()));
            e.setCategorie(categorie.getSelectionModel().getSelectedItem());
            e.setEnable(0);
            e.setIduser(2);
            e.setPhoto(pathString);
            
            //System.out.println("le champ photo  :  "+charger.getText());

            try {
                se.ajouterEvent(e);
            } catch (SQLException ex) {
                Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
}
