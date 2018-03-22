/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Justpro
 */
public class LoginMain extends Application {
    @Override
    public void start(Stage primaryStage) {
     
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("DashboardCoVoiturage.fxml"));
        } catch (IOException ex) {
            //Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        //primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
       }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
