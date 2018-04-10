/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Niveau;
import EspaceEtude.services.EspaceEtudeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
public class AjouterSectionController implements Initializable {

    @FXML
    private JFXTextField sectionName;
    @FXML
    private JFXButton SectionAddButon;
    @FXML
    private JFXComboBox<Niveau> sectionNiveau;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SectionAddButon.setStyle("-fx-background-color: #6BC3B0;");
        sectionNiveau.getItems().addAll(Niveau.values());
    }    

    @FXML
    private void ajouterSection(MouseEvent event) throws IOException {
        EspaceEtudeService s=new EspaceEtudeService();
        s.addSection(sectionName.getText(), sectionNiveau.getValue().name());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/SectionAdmin.fxml"));
            // AfficherMatiereInterfaceController display= new AfficherMatiereInterfaceController();
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
    }
    
}
