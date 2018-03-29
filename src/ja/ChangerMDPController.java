/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

//import Entity.Session;

import entities.User;
import services.UserCRUD;
import util.Validation;
import entities.Session;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import util.BCrypt;

/**
 * FXML Controller class
 *
 * @author bader
 */
public class ChangerMDPController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    
    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmNewPassword;

    @FXML
    private Button changePassword;
    @FXML
    private Button desactiverB;
      @FXML
    private Label mdpL;

    @FXML
    private Label mailL;

    @FXML
    private Button mailB;
    
    @FXML
    private TextField mailTF;

    @FXML
    private TextField newmailTF;


    @FXML
    void changePSW(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
      UserCRUD u=new UserCRUD();
      User user=new User();
      
      if (BCrypt.checkpw(oldPassword.getText(),u.recevoirMDPavecId(Session.getIdThisUser())))
      {
          System.out.println("verifier");
          if (newPassword.getText().equals(confirmNewPassword.getText()))
          {
              u.changerMDP(newPassword.getText(),Session.getIdThisUser());
          }
      }
      else 
      {
          mdpL.setText("* verifier votre mot de passe !");
      }
    }
    
     @FXML
    void mailChanger(ActionEvent event) throws SQLException {
        UserCRUD us=new UserCRUD();
     if (Validation.texMail(newmailTF, mailL,"* verifier la forme de votre e-mail"))
     {
         if(mailTF.getText().equals(us.recevoirMailAvecId(Session.getIdThisUser())))
         {
             us.changerMail(newmailTF.getText(),Session.getIdThisUser());
         }
         else
             mailL.setText("* verifier vos information");
     }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
}
