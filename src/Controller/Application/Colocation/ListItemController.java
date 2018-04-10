/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class ListItemController {

    @FXML
    private AnchorPane panel;
    @FXML
    private ImageView photo;
    @FXML
    private Label titre;
    @FXML
    private Label prix;

    public ListItemController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/Controller/Application/Colocation/ListItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    public void setInfo(String title, String price, String path) {
        titre.setText(title);
        prix.setText(price);
        
        File file = new File("src/images/colocation/"+path);
        if (file.exists()) {
          
            Image image = new Image(file.toURI().toString(),100, 100, false, false);
            photo.setImage(image);
        }
    }

    public AnchorPane getPanel() {
        return panel;
    }

}
