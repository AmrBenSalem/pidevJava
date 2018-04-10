/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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

        System.out.println(btn.getId());
        switch (btn.getId()) {
            case "b1":
                //    DashboardCoVoiturageController.CoVoituragePaneInit.setVisible(false);

                break;

            case "b2":
                Parent page = null;
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
                //  DashboardCoVoiturageController.CoVoituragePaneInit.setVisible(false);
                break;

            case "b4":
                Parent page1 = null; 
                try {
                    page1 = FXMLLoader.load(getClass().getResource("/EspaceEtude/Gui/afficherClasseInterface.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene1 = new Scene(page1);
                Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage1.hide();
                stage1.setScene(scene1);
                stage1.show();
                break;

            case "b5":
            //  DashboardCoVoiturageController.CoVoituragePaneInit.setVisible(false);

        }
//        pageNameLabel.setText(btn.getText());
    }

}
