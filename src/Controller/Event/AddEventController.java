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
import entities.Session;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.ServiceEvent;
import sun.tools.jar.resources.jar;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class AddEventController implements Initializable {

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
    private JFXComboBox<String> categorie;
    
    @FXML
    private JFXButton ajout;
    @FXML
    private Label lab_erreur;
    @FXML
    private Label warning;
    private String path1;
    public String pathString;
    
    ObservableList<String> maincateg = FXCollections.observableArrayList("Randonnée", "Camping", "Anniversaire Club", "Workshop", "Hackathon");
    @FXML
    private AnchorPane container_ajout;
    @FXML
    private ImageView image;

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
        titre.setText(path1);
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
        Image img = new Image("file:" + file.getPath());

        Path pth = file.toPath();

        File resourcesDirectory = new File("src/assets/" + file.getName());
        Files.deleteIfExists(resourcesDirectory.toPath());
        
        File symfonyDirectory = new File("C:/wamp64/www/pidev2/web/"+file.getName());
         Files.deleteIfExists(symfonyDirectory.toPath());
        Files.copy(pth, symfonyDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        Files.copy(pth, resourcesDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        
        image.setImage(img);
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        ServiceEvent se = new ServiceEvent();
        Event e = new Event();

        if (!(titre.getText().isEmpty())) {
            
            if(Session.getThisTimestamp().after(Date.valueOf(datedebut.getValue())) || Date.valueOf(datedebut.getValue()).after(Date.valueOf(datefin.getValue()))  )  {
                lab_erreur.setText("Les dates sont invalides");
                lab_erreur.setVisible(true);
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
            
            
            e.setDateDebut(Date.valueOf(datedebut.getValue()));
            e.setDateFin(Date.valueOf(datefin.getValue()));
            e.setCreatedAt(Session.getDateThisDay());
            e.setCategorie(categorie.getSelectionModel().getSelectedItem());
            e.setEnable(0);
            e.setIduser(Session.getIdThisUser());
            e.setPhoto(pathString);
            e.setNb_max(Integer.parseInt(nbmax.getText()));
            System.out.println(Session.getThisTimestamp()+"----------------------"+e.getDateDebut()+"-----------------------"+e.getDateFin());
            
            
            //System.out.println("le champ photo  :  "+charger.getText());

            try {
                se.ajouterEvent(e);
            } catch (SQLException ex) {
                Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
             Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container_ajout.getChildren().clear();
            container_ajout.getChildren().add(node);
    
}

    @FXML
    private void retour_events(ActionEvent event) throws IOException {
           Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container_ajout.getChildren().clear();
            container_ajout.getChildren().add(node);
    }
}
