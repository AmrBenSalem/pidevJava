/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoituragedemande;

import gui.covoiturage.*;
import com.jfoenix.controls.JFXButton;
import entities.CoVoiturage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.ServiceCoVoiturage;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class DemandeLineController implements Initializable {

    @FXML
    public Pane redPane;
    @FXML
    public Label userTextField;
    @FXML
    public Label departTextField;
    @FXML
    public Label destinationTextField;
    @FXML
    public Label dateTextField;
    @FXML
    public Label etatTextField;
    ServiceCoVoiturage cs;
    @FXML
    private JFXButton buttonDelete;
    @FXML
    private JFXButton buttonUpdate;
    @FXML
    private JFXButton req;
   

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DemandesViewController.littlePane = redPane;
        
        cs = new ServiceCoVoiturage();
    }

    

    @FXML
    public void DeleteAction(ActionEvent event)  {
        
    }

    @FXML
    public void InfoAction(ActionEvent event) {
        System.out.println("try of info action");
    }

    @FXML
    public void UpdateAction(ActionEvent event) {
        System.out.println("try of update action");
    }

    

    public void setUserTextField(String userTextField) {
        this.userTextField.setText(userTextField); 
    }

    public void setDepartTextField(String departTextField) {
        this.departTextField.setText(departTextField);
    }

    public void setDestinationTextField(String destinationTextField) {
        this.destinationTextField.setText(destinationTextField);
    }

    public void setDateTextField(String dateTextField) {
        this.dateTextField.setText(dateTextField);
    }

    public void setEtatTextField(String etatTextField) {
        this.etatTextField.setText(etatTextField);
    }

    public Pane getRedPane() {
        return redPane;
    }

}
