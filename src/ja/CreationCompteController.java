/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.User;
import services.UserCRUD;
import util.Validation;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.security.*;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.BCrypt;

/**
 * FXML Controller class
 *
 * @author Houbal
 */
public class CreationCompteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      sexe.setItems(FXCollections.observableArrayList(
      new String("H"),
      new String("F")   
      ));

    }

    public boolean controleSaisie() throws IOException, SQLException {
        boolean saisie = true;
        UserCRUD us = new UserCRUD();

       

       

        if (!Validation.textalphabet(nomTF, nomL, "* le nom de doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.textalphabet(prenomTF, prenomL, "* le prenom de doit contenir que des lettre")) {
            saisie = false;
        }
        if (!Validation.textValidation(nomTF, nomL, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(prenomTF, prenomL, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(usernameTF, usernameL, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(mailTF, mailL, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(passwordTF, passwordL, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(confirmTF, cpasswordL, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!us.uniqueMail(mailTF.getText())) {
            mailL.setText("* le e-mail est déjà utilisé");
            saisie = false;
        }
        if (!us.uniqueUserName(usernameTF.getText())) {
            usernameL.setText("* le username est déjà utilisé");
            saisie = false;
        }
         if (!passwordTF.getText().equals(confirmTF.getText())) {
            cpasswordL.setText("* vous devez confirmer le mot de passe");
            saisie = false;
        }
          if (Validation.texMail(mailTF, mailL, "* la forme de mail est invalide")) {

            /*String validationString = null;
            if (!mailTF.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")) {
                saisie = false;

            }

            mailL.setText("* la forme de mail est invalide");
            System.out.println(saisie);*/
            return saisie;
        }
        return saisie;
    }

    @FXML
    private TextField usernameTF;

    @FXML
    private TextField mailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private Button ajouterB;

    @FXML
    private PasswordField confirmTF;
    @FXML
    private Label mailL;

    @FXML
    private Label nomL;

    @FXML
    private Label prenomL;
    @FXML
    private Label usernameL;

    @FXML
    private Label passwordL;

    @FXML
    private Label cpasswordL;
    @FXML
    private Button loginB;
    
    @FXML
    private DatePicker dateNaissance;
    
    @FXML
    private ChoiceBox sexe;
    
    @FXML
     private TextField telephone;
    
     @FXML
    private Label datenaissanceL;
     
      @FXML
    private Label sexeL;
      
       @FXML
    private Label telephoneL;
       
       @FXML
    private Label classeL;
       
       @FXML
       
    private TextField classe;
    
   
    
    

    @FXML
    void ajouter(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException, SQLException {

        Validation v = new Validation();
        
            UserCRUD a = new UserCRUD();
            if (this.controleSaisie()) {
                
                User u1= new User(usernameTF.getText(), Date.valueOf(dateNaissance.getValue()), (String) sexe.getValue(), classe.getText(), telephone.getText(), mailTF.getText(), BCrypt.hashpw(passwordTF.getText(),BCrypt.gensalt(13)),  nomTF.getText(), prenomTF.getText());

                //User u = new User(usernameTF.getText(), mailTF.getText(), a.MD5(passwordTF.getText()), nomTF.getText(), prenomTF.getText());
                a.ajouterUser(u1);

                Notifications notificiationBuilder = Notifications.create()
                        .title("créer")
                        .text("votre compte a été créer !")
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
                //notificiationBuilder.showConfirm();
                login(event);
            }

      
    }
    
       @FXML
    void login(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/ja/authentification.fxml"));
         
        Scene scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        
        app_stage.setScene(scene);
        
        app_stage.show();
        
    }
    
 

}
