/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.User;
import services.UserCRUD;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Houbal
 */
public class MdpOublierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML
    private TextField usernameTF;

    @FXML
    private TextField mailTF;
    
    @FXML
    private Button changerB;

    @FXML
    private Button authentifierB;
    @FXML
    private Label loginL;

    @FXML
    void changer(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException {

        UserCRUD us = new UserCRUD();
        if (!us.uniqueUserName(usernameTF.getText()))
        {
        User u=us.recevoirUser(usernameTF.getText(),loginL);
        if (mailTF.getText().equals(u.getEmail()))
        {
            //generer mdp automatiquement
            
            String s=""+u.getId();
            System.out.println(s);
            for (int i=0;i<20;i++)
            {
           Random rand = new Random();
           int a=rand.nextInt(10);
           s=s+a;
            }
            
            System.out.println(s);
            
            us.changerMDP(s,u.getId());
            
            Parent root = FXMLLoader.load(getClass().getResource("/View/authentification.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
        
        Notifications notificiationBuilder = Notifications.create()
                        .title("changer")
                        .text("votre mot de passe a été changer !")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Missing!");
                            }
                        });
                notificiationBuilder.darkStyle();
                notificiationBuilder.showConfirm();
        }
        else
            loginL.setText("* verifier vos informations !");
        } 
                else
            loginL.setText("* verifier vos informations !");
    }

    @FXML
    void retourne(ActionEvent event) throws IOException {

         Parent root = FXMLLoader.load(getClass().getResource("/ja/authentification.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
