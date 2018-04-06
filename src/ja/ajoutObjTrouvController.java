/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.ObjetCRUD;

/**
 *
 * @author bader
 */
public class ajoutObjTrouvController implements Initializable {
    
    
    @FXML
    private TextArea Lieux;
  
     
    @FXML
    private TextArea Desc;
    
    
    @FXML
    private DatePicker Dat;
    
       
    @FXML
    private ChoiceBox<String> Typ;
    
    @FXML
    private Button Ajout;
    
    
    @FXML
    private TextField tof;
    
    private File file1;
    @FXML
    private Button Parcourir;
    
    @FXML
    protected void handleButtonAction(ActionEvent event) {
        
        Window owner = Ajout.getScene().getWindow();
        Path path = Paths.get("C:/xampp/htdocs/pidev2/web");
            Path path1 = Paths.get(file1.getAbsolutePath());
            
            try {
                Files.copy(path1, path.resolve(path1.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                
            } 
            catch (IOException ex) {
                Logger.getLogger(ObjetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        Objet o1= new Objet(1,Typ.getValue(),Desc.getText(),Date.valueOf(Dat.getValue()),"Objet Trouvé",Lieux.getText(),tof.getText(),false);
        ObjetCRUD o=new ObjetCRUD();
        o.ajouterObjet(o1);
          
   }
    
    
    
    
    
    
    @FXML
    public void chooseImage(ActionEvent ev)
    {
        Window owner = tof.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        file1 = file.getAbsoluteFile();
        //if (file1.getAbsolutePath().endsWith(".jpg") || file1.getAbsolutePath().endsWith(".jpeg") || file1.getAbsolutePath().endsWith(".png")) {
            System.out.println(file1.getAbsoluteFile().getUsableSpace());
            String imagePath = file.getName();
            tof.setText(imagePath);
       /* } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Echec",
                    "Inserer une image de type PNG , JPG ou JPEG");
            image.setText(null);
        }*/
        
    
    }
    
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
      //Nat.setDisable(false);
      
    }
    
    
    
}
