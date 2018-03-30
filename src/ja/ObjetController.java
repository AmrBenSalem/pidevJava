/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import services.ObjetCRUD;

/**
 *
 * @author bader
 */
public class ObjetController implements Initializable {

    @FXML
    private Label UserID;

    @FXML
    private Label Photo;

    @FXML
    private Label Lieu;

    @FXML
    private Label Nature;

    @FXML
    private Label Type;

    @FXML
    private Label Dateee;

    @FXML
    private Label Description;

    @FXML
    private TextArea UID;
    
    @FXML
    private TextArea Lieux;
  
     
    @FXML
    private TextArea Desc;
    
    @FXML
    private ImageView Pho;
    
    @FXML
    private DatePicker Dat;
    
    @FXML
    private ChoiceBox<String> Nat;
    
    @FXML
    private ChoiceBox<String> Typ;
    
    @FXML
    private Button Ajout;
    
    @FXML 
    protected void handleButtonAction(ActionEvent event) {
        
        Window owner = Ajout.getScene().getWindow();
        
        Objet o1= new Objet(1,Typ.getValue(),Desc.getText(),Date.valueOf(Dat.getValue()),Nat.getValue(),Lieux.getText(),"pas",false);
        ObjetCRUD o=new ObjetCRUD();
        o.ajouterObjet(o1);
          
   }
  
/*
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Typ.setItems(FXCollections.observableArrayList(
      new String("Ordinateur"),
      new String("Chargeur"),
      new String("Telephone"),
      new String("Papier"),
      new String("CIN"),
      new String("Autres")     
      ));
      //Typ.isFocused();
      
      
      Nat.setItems(FXCollections.observableArrayList(
      new String("Objet Perdu"),
      new String("Objet Trouvé") 
      ));
      //Nat.setDisable(false);
      
    }

}
