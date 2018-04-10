/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class ApprouverAdminController implements Initializable {

    @FXML
    private Label labnom;
    @FXML
    private Label titre;
    @FXML
    private Label labdatedebut;
    @FXML
    private Label labdatefin;
    @FXML
    private ImageView imv;
    @FXML
    private Label lablieu;
    @FXML
    private Label labnbrplace;
    @FXML
    private Label labcategorie;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton btn;
    
    public static int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceEvent ev = new ServiceEvent();
        Event e;
        
        e = ev.consulterEvent(id);
        labnom.setText(e.getTitre());
        lablieu.setText(e.getLieu());
        labcategorie.setText(e.getCategorie());
        Image i = new Image("file:" + e.getPhoto());
        imv.setFitHeight(180);
        imv.setFitWidth(220);
        imv.setImage(i);
        //System.out.println(e);
        labdatedebut.setText(e.getDateDebut().toString());
        labdatefin.setText(e.getDateFin().toString());
        labnbrplace.setText(e.getNb_max()+"");
        titre.setText(e.getTitre());
        description.setText(e.getDescription());
        
        
        
    }    

    @FXML
    private void approuver(ActionEvent event) throws SQLException, IOException {
        
        ServiceEvent se  = new ServiceEvent();
        se.approuverEvent(id);
        //btn.setVisible(false);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Event/ListEventAdmin.fxml"));
            
            Pane pane = (Pane) loader.load();        
            stage.setTitle("Approuver");
            Scene scene = new Scene(pane);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
                  
        
        
    }
    
}
