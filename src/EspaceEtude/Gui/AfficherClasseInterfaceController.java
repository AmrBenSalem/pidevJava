/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Niveau;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AfficherClasseInterfaceController implements Initializable {

    @FXML
    private ImageView avatar;
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
    private GridPane tilePaneFolder;
    @FXML
    private JFXListView<Niveau> listViewNiveau;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       listViewNiveau.setCellFactory(new Callback<ListView<Niveau>,ListCell<Niveau>>() {
    @Override
    public ListCell<Niveau> call(ListView<Niveau> param) {
        ListCell<Niveau> cell=new ListCell<Niveau>(){
           private ImageView imageView = new ImageView();
            
            protected void updateItem(Niveau n, boolean bt1) {
                super.updateItem(n, bt1); //To change body of generated methods, choose Tools | Templates.
                if(n!=null) {
                    imageView.setImage(new Image(getClass().getResource("folder.JPG").toExternalForm()));
                    setText(n.name());
                    setGraphic(imageView);
                    
                   
                }
                    
            }
            
        };
      return cell;  
    }
    
});
        ObservableList<Niveau> items=FXCollections.observableArrayList(Niveau.values());
        
listViewNiveau.setItems(items);
listViewNiveau.setPrefWidth(200);
listViewNiveau.setPrefHeight(300);
    }    

    @FXML
    private void showList(MouseEvent event) {
    }

    @FXML
    private void ChangeViewAction(ActionEvent event) {
    }
    
}
