/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Interaction;

import java.sql.Date;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceInteraction;

/**
 *
 * @author bader
 */
public class Ja extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("authentification.fxml"));
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       launch(args);
       /** ServiceInteraction se = new ServiceInteraction();
        se.getByIdObjet(33);
        Interaction i =  se.getByIdObjet(30);
        System.out.println(i);**/
      
         
        

    }

}
