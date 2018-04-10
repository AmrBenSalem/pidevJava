/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class LeftMenuController implements Initializable {

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    public static Label pageNameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangeViewAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
Parent Objet = null;
Parent page = null;
        System.out.println(btn.getId());
        switch (btn.getId()) {
            case "b1":
                //    DashboardCoVoiturageController.CoVoituragePaneInit.setVisible(false);

                break;

            case "b2":
                
                try {
                 page = FXMLLoader.load(getClass().getResource("covoiturage/CovoiturageView.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
                break;

            case "b3":
                
                try {
                 page = FXMLLoader.load(getClass().getResource("ja/ObjetView.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene Objetscene = new Scene(Objet);
                Stage Objetstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Objetstage.hide();
                Objetstage.setScene(Objetscene);
                Objetstage.show();
                break;

            case "b4":
//                DashboardCoVoiturageController.CoVoituragePaneInit.setVisible(false);
                
                try {
                 page = FXMLLoader.load(getClass().getResource("/Controller/Application/Colocation/acceuilCol.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene Objetscenee = new Scene(page);
                Stage Objetstagee = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Objetstagee.hide();
                Objetstagee.setScene(Objetscenee);
                Objetstagee.show();
               

                break;

            case "b5":
            //  DashboardCoVoiturageController.CoVoituragePaneInit.setVisible(false);

        }
//        pageNameLabel.setText(btn.getText());
    }

}
