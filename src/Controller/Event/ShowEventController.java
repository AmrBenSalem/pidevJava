/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Event;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class ShowEventController implements Initializable {

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
    private JFXTextField nbmax;
    @FXML
    private JFXTextField x;
    @FXML
    private JFXTextField y;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXTextField categorie;
    @FXML
    private JFXButton supprimer;
    @FXML
    private ImageView photo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titre.setEditable(false);
        description.setEditable(false);
        datedebut.setEditable(false);
        datefin.setEditable(false);
        nbmax.setEditable(false);
        x.setEditable(false);
        y.setEditable(false);
        
        Event e = new Event();
        ServiceEvent se = new ServiceEvent();
        e = se.consulterEvent(0);
        titre.setText(e.getTitre());
        description.setText(e.getTitre());
        datedebut.setValue(e.getDateDebut().toLocalDate());
        datefin.setValue(e.getDateFin().toLocalDate());
        nbmax.setText(e.getNb_max()+"");
        x.setText(e.getX()+"");
        y.setText(e.getY()+"");
        
    }    

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
    }

    @FXML
    private void edit(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        ServiceEvent se = new ServiceEvent();
        Event e = se.consulterEvent(2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("supprimer");
        alert.setHeaderText(null);
        alert.setContentText("cliquer sur OK si tu veux supprimer sinon annuler");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            se.supprimerEvent(e.getId());
        }
    }
    
}
