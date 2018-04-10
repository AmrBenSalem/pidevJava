/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class HomeController implements Initializable {

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private StackPane container;
    @FXML
    private Button b21;
    @FXML
    private ImageView btnevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Node node = null;
            FXMLLoader loader = new FXMLLoader();
        try {
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container.getChildren().clear();
            container.getChildren().add(node);
        // TODO
        
    }    

    @FXML
    private void ChangeViewAction(ActionEvent event) {
    }

    @FXML
    private void event_onClick(ActionEvent event) throws IOException {
       Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container.getChildren().clear();
            container.getChildren().add(node);
    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container.getChildren().clear();
            container.getChildren().add(node);
    }

    @FXML
    private void event_onclick(ActionEvent event) throws IOException {
        Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container.getChildren().clear();
            container.getChildren().add(node);
    }
    
}
