/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Niveau;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AfficherClasseInterfaceController implements Initializable {

   
    
    @FXML
    private ListView<Niveau> list;
    
    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
list.setCellFactory(new Callback<ListView<Niveau>,ListCell<Niveau>>() {
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
        
list.setItems(items);
list.setPrefWidth(400);
list.setPrefHeight(500);
    } 
      
     
        @FXML
    void test(MouseEvent event) throws IOException {
   
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/afficherSectionInterface.fxml"));
             AfficherSectionInterfaceController display= new AfficherSectionInterfaceController();
        if(list.getSelectionModel().getSelectedIndex()==0)
            display.setNiveau("1A");
        else if(list.getSelectionModel().getSelectedIndex()==1)
            display.setNiveau("2A");
        else if(list.getSelectionModel().getSelectedIndex()==2)
            display.setNiveau("2B");
        else if(list.getSelectionModel().getSelectedIndex()==3)
            display.setNiveau("3A");
        else if(list.getSelectionModel().getSelectedIndex()==4)
            display.setNiveau("3B");
        else if(list.getSelectionModel().getSelectedIndex()==5)
            display.setNiveau("4A");
        else if(list.getSelectionModel().getSelectedIndex()==6)
            display.setNiveau("4B");
        else if(list.getSelectionModel().getSelectedIndex()==7)
            display.setNiveau("5A");
        else if(list.getSelectionModel().getSelectedIndex()==8)
            display.setNiveau("5B");
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
           }
    
    
  
    
}
