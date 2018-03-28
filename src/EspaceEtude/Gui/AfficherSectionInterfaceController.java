/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Section;
import EspaceEtude.services.EspaceEtudeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AfficherSectionInterfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
        private ListView<Section> listSection;
        public static String niveau;
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listSection.setCellFactory(new Callback<ListView<Section>,ListCell<Section>>() {
    @Override
    public ListCell<Section> call(ListView<Section> param) {
        ListCell<Section> cell=new ListCell<Section>(){
           private ImageView imageView = new ImageView();
            
            protected void updateItem(Section s, boolean bt1) {
                super.updateItem(s, bt1); //To change body of generated methods, choose Tools | Templates.
                if(s!=null) {
                    imageView.setImage(new Image(getClass().getResource("folder.JPG").toExternalForm()));
                    setText(s.getLibelle());
                    setGraphic(imageView);
                    
                   
                }
                    
            }
            
        };
      return cell;  
    }
    
});
        
        
listSection.setItems(getSectionAll());
listSection.setPrefWidth(400);
listSection.setPrefHeight(500);
    }    
    public ObservableList<Section> getSectionAll(){
        EspaceEtudeService dao=new EspaceEtudeService();
        System.out.println(niveau);
        ObservableList<Section> list =dao.getAllSectionByNiveau(niveau);
        return list;

    }
    
    public static void setNiveau(String niveau){
        System.out.println(niveau);
        AfficherSectionInterfaceController.niveau=niveau;
    }
}
