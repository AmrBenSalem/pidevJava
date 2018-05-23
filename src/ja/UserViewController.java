/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Session;
import entities.User;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author bader
 */
public class UserViewController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField date;
    @FXML
    private TextField sexe;
    @FXML
    private TextField telephone;
    @FXML
    private TextField classe;
    @FXML
    private Hyperlink changermdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserCRUD uc=new UserCRUD();
        User u = new User();
        try {
            u=uc.getByID(Session.getIdThisUser());
             username.setText(u.getUserName());
             nom.setText(u.getNom());
             prenom.setText(u.getPrenom());
             date.setText(u.getDateNaissance().toString());
             sexe.setText(u.getSexe());
             telephone.setText(u.getTelephone());
             classe.setText(u.getClasse());
        } catch (SQLException ex) {
            Logger.getLogger(UserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @FXML
    public void changermdp(ActionEvent event) throws IOException{
        Parent root;
         
        root = FXMLLoader.load(getClass().getResource("changerMDP.fxml"));
        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();        
    }
 
          
    
}
