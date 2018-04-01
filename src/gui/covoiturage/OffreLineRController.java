/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class OffreLineRController implements Initializable {

    @FXML
    private Pane redPane;
    @FXML
    private Label userTextField;
    @FXML
    private Label etatTextField;
    @FXML
    private JFXButton buttonUpdate;
    @FXML
    private JFXButton buttonDelete;
    @FXML
    private Label dateTextField;
    @FXML
    private Label demandeText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
