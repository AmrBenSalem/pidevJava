/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Documents;
import EspaceEtude.services.EspaceEtudeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class DocumentUpdateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static Documents doc ;  

    public static Documents getDoc() {
        return doc;
    }

    public static void setDoc(Documents doc) {
        DocumentUpdateController.doc = doc;
    }
    @FXML
    private JFXTextArea nomFichier;
    @FXML
    private JFXTextArea language;
    @FXML
    private JFXButton modif;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateDoc(MouseEvent event) throws IOException {
        Documents doc=DocumentUpdateController.getDoc();
        if(!language.getText().equals(""))
            doc.setLanguage(language.getText());
        if(!nomFichier.getText().equals(""))
            doc.setLibelle(nomFichier.getText());
        EspaceEtudeService ees=new EspaceEtudeService();
        ees.modifierDocument(doc);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/AffficherDocumentsInterface.fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
