/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EspaceEtude.Gui.AffficherDocumentsInterfaceController;
import EspaceEtude.entities.Notification;
import EspaceEtude.services.EspaceEtudeService;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class TopMenuController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private MenuButton notifMenuButton;
    @FXML
    private Circle notifCercle;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(new EspaceEtudeService().getUserRole(Session.getIdThisUser()).equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {

        int vu=1;
        EspaceEtudeService dao=new EspaceEtudeService();
        ArrayList<Notification> notifList=new ArrayList<>();
        notifList=dao.getAllnotif();
        for(Notification n : notifList){
            MenuItem mi=new MenuItem();
            Label l=new Label();
            l.setText(n.getMatiere().getLibelle()+" :");
            mi.setGraphic(l);
            mi.setText("Veuillez accepter le documetn");
            notifMenuButton.getItems().add(mi); 
            if(n.getVu()==0){
                vu=0;
                mi.setStyle("-fx-background-color: #FDEFEF;");
            }
            mi.setOnAction((ActionEvent event) -> {   
                try {
                    AffficherDocumentsInterfaceController.setIdMatiere(n.getMatiere());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/AffficherDocumentsInterface.fxml"));
                    // AfficherMatiereInterfaceController display= new AfficherMatiereInterfaceController();
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    ap.getScene().getWindow().hide();
                    stage.show();
                    dao.setNotificationRead(n);
                } catch (IOException ex) {
                    Logger.getLogger(TopMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            });
        }
        if(vu==0){
            notifCercle.setVisible(true);
        }else
            notifCercle.setVisible(false);
    
        }else
           notifCercle.setVisible(false); 
        }    

    @FXML
    private void showList(MouseEvent event) {
    }
    
}
