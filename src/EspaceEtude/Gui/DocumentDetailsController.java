/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Documents;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class DocumentDetailsController implements Initializable {

    @FXML
    private Label documentName;
    @FXML
    private Label documentLanguage;
    @FXML
    private Label documentSize;
    @FXML
    private Label documentType;
   
    private static Documents doc ;

    public static Documents getDoc() {
        return doc;
    }

    public static void setDoc(Documents doc) {
        DocumentDetailsController.doc = doc;
    }
    @FXML
    private ImageView documentImage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            documentName.setText(doc.getLibelle());
            documentLanguage.setText(doc.getLanguage());
            documentSize.setText(doc.getSize().toString());
            System.out.println(doc.getImage());
            File imageFile=new File("C:/xampp/htdocs/EspritEntreAide/web"+doc.getImage());
            Image image = new Image(new FileInputStream(imageFile), 150, 0, true,true);
            documentImage.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocumentDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    /*"C:/xampp/htdocs/EspritEntreAide/web"+doc.getImage()*/
    
}
