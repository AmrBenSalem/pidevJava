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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class ModifierEventController implements Initializable {

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
    @FXML
    private ImageView image;
    
    public String pathString;
    
    ObservableList<String> maincateg = FXCollections.observableArrayList("Randonnée", "Camping", "Anniversaire Club", "Workshop", "Hackathon");
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ServiceEvent se = new ServiceEvent();
        Event e=se.consulterEvent(Integer.parseInt(ListEventController.id_event));
        titre.setText(e.getTitre());
        
        categorie.setItems(maincateg);
        description.setText(e.getDescription());
        datedebut.setValue(e.getDateDebut().toLocalDate());
        datefin.setValue(e.getDateFin().toLocalDate());
        lieu.setText(e.getLieu());
        nbmax.setText(e.getNb_max()+"");
        categorie.setValue(e.getCategorie());
         Image i = new Image("file:" + e.getPhoto());
         image.setImage(i);
         pathString = e.getPhoto();
        // TODO
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
        File symfonyDirectory = new File("C:/wamp64/www/pidev2/web/"+file.getName());
        Files.deleteIfExists(resourcesDirectory.toPath());
        Files.deleteIfExists(symfonyDirectory.toPath());
        
        Files.copy(pth, symfonyDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        Files.copy(pth, resourcesDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        
        image.setImage(img);
    }

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
         ServiceEvent se = new ServiceEvent();
         Event e=se.consulterEvent(Integer.parseInt(ListEventController.id_event));
         
     
        e.setTitre(titre.getText());
        e.setDescription(description.getText());
        e.setDateDebut(Date.valueOf(datedebut.getValue()));
        e.setDateFin(Date.valueOf(datefin.getValue()));
        e.setLieu(lieu.getText());
        e.setNb_max(Integer.parseInt(nbmax.getText()));
        e.setCategorie(categorie.getValue());
        e.setPhoto(pathString);
        e.setCreatedAt(Date.valueOf(datedebut.getValue()));
        se.updateEvent(e, Integer.parseInt(ListEventController.id_event));
        
                         Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           
                        try {
                            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/AfficheEvent.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
           container.getChildren().clear();
            container.getChildren().add(node);
        
    }
    
}
