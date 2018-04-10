/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Section;
import EspaceEtude.services.EspaceEtudeService;
import com.jfoenix.controls.JFXButton;
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
public class AjouterMatiereController implements Initializable {
    
    private static Section s;

    public static Section getS() {
        return s;
    }

    public static void setS(Section s) {
        AjouterMatiereController.s = s;
    }
    @FXML
    private JFXTextField MatiereName;
    @FXML
    private JFXButton MatiereAddButon;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField coefficient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         MatiereAddButon.setStyle("-fx-background-color: #6BC3B0;");
    }    

    @FXML
    private void ajouterMatiere(MouseEvent event) throws IOException {
         EspaceEtudeService s=new EspaceEtudeService();
        s.addMatiere(MatiereName.getText(), coefficient.getText(), type.getText(),AjouterMatiereController.getS());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/SectionAdmin.fxml"));
            // AfficherMatiereInterfaceController display= new AfficherMatiereInterfaceController();
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
    }
    
}
