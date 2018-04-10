/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Matiere;
import EspaceEtude.entities.Section;
import EspaceEtude.services.EspaceEtudeService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AfficherMatiereInterfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static Section section;
    @FXML
    private ListView<Matiere> listMatiere;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     listMatiere.setCellFactory(new Callback<ListView<Matiere>,ListCell<Matiere>>() {
    @Override
    public ListCell<Matiere> call(ListView<Matiere> param) {
        ListCell<Matiere> cell=new ListCell<Matiere>(){
           private ImageView imageView = new ImageView();
            
            protected void updateItem(Matiere m, boolean bt1) {
                super.updateItem(m, bt1); //To change body of generated methods, choose Tools | Templates.
                if(m!=null) {
                    imageView.setImage(new Image(getClass().getResource("folder.JPG").toExternalForm()));
                    setText(m.getLibelle());
                    setGraphic(imageView);
                    
                   
                }
                    
            }
            
        };
      return cell;  
    }
    
});
        
        
listMatiere.setItems(getAllMatiere());
listMatiere.setPrefWidth(400);
listMatiere.setPrefHeight(500);
    }  
    public ObservableList<Matiere> getAllMatiere(){
      EspaceEtudeService dao=new EspaceEtudeService();
        System.out.println(section.getId());
        ObservableList<Matiere> list =dao.getAllMatiereBySection(section);
        return list;  
    }
    public void setSectionId(Section section){
        this.section=section;
        System.out.println(section.getId());
    }
     @FXML
    void getAllDocument(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/AffficherDocumentsInterface.fxml"));
         AffficherDocumentsInterfaceController display= new AffficherDocumentsInterfaceController();
         AffficherDocumentsInterfaceController.setIdMatiere(listMatiere.getSelectionModel().getSelectedItem());
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
    }
  
    
}
