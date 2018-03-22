/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class AjoutColController implements Initializable {
    @FXML
    private ComboBox<String> meuble_cb;
    @FXML
    private ComboBox<String> type_cb;
    @FXML
    private ComboBox<String> nature_cb;
    @FXML
    private Button valider_btn;
    @FXML
    private TextField titre_txt;
    @FXML
    private TextField loyer_txt;
    @FXML
    private TextField description_txt;
    @FXML
    private ComboBox<String> ville_cb;
    @FXML
    private TextField lng_txt;
    @FXML
    private TextField lat_txt;
    @FXML
    private TextField photo_path;
    @FXML
    private TextField photo1_path;
    @FXML
    private TextField photo2_path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
           init_node();
     
        // TODO
    }    

    @FXML
    private void Valider(ActionEvent event) {
    }
    
    
        private void init_node() {
        nature_cb.getItems().addAll("demande","offre");
        type_cb.getItems().addAll("maison","studio");
        meuble_cb.getItems().addAll("meuble","partiellement meuble");
        ville_cb.getItems().addAll("tunis","nabeul","bizerte");
   
}
    
}
