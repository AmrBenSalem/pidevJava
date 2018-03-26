/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class OffreLineController implements Initializable {

    @FXML
    public Pane redPane;
    @FXML
    private Label userTextField;
    @FXML
    private Label departTextField;
    @FXML
    private Label destinationTextField;
    @FXML
    private Label dateTextField;
    @FXML
    private Label etatTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void InfoAction(MouseEvent event) {
    }

    @FXML
    private void DeleteAction(MouseEvent event) {
    }

    @FXML
    private void UpdateAction(MouseEvent event) {
    }
    
}
