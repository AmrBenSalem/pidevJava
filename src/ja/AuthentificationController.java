/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Session;
import entities.User;
import services.UserCRUD;
import util.Validation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import util.BCrypt;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author bader
 */
public class AuthentificationController implements Initializable {

    /**
     * Initializes the controller class.
     */
 

    @FXML
    private TextField userNameTF;

    @FXML
    private PasswordField passewordTF;

    @FXML
    private Button accederB;

    @FXML
    private Hyperlink creerB;
    /*  @FXML
    private JFXButton fbB;*/
    @FXML
    private Hyperlink mdpoublieB;
    @FXML
    private Label loginL;
    static User user;
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         loginL.setText("");
    }

    @FXML
    void creer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ja/creationCompte.fxml"));

        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }

    @FXML
    void mdpoB(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ja/MdpOublier.fxml"));

        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }

    /*  @FXML
    void facebookB(ActionEvent event) {
        String appId = "149916295537077";
        //  String secret="b9844c024e1b2c3a38b06ef5064df7aa";
        String domain = "http://stackoverflow.com/";
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_pages,publish_actions,read_insights,read_page_mailboxes,rsvp_event";

        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while (true) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                driver.quit();
                FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
                User user = fbClient.fetchObject("me", User.class);

                                Session.setIdThisUser(user.getId());
                System.out.println(user);
            } 
    }
    }*/
    @FXML
    void verifier(ActionEvent event) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException {
        String username = userNameTF.getText();
        String password = passewordTF.getText();
         
        UserCRUD a = new UserCRUD();
        if (!a.uniqueUserName(username)) {
            if ((Validation.textValidation(userNameTF,loginL,"* un ou plusieurs champs sont vides !"))) {
                User u = a.recevoirUser(username,loginL);
                  
                System.out.println(password);
                if ((BCrypt.checkpw(password,u.getPassword())) && (u.getEnabled() == 1) && (u.getUserName().equals(username))) {
                    System.out.println("success !");
                    Session.setIdThisUser(u.getId());
                    Session.setUser(u);

                    if (u.getRoles().equals("a:0:{}")) {
                        System.out.println("user");
                        Parent root = FXMLLoader.load(getClass().getResource("/ja/ObjetView.fxml"));

                        Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    } else {
                        System.out.println("admin");
                        Parent root = FXMLLoader.load(getClass().getResource("/ja/Admin.fxml"));

                        Scene scene = new Scene(root);

                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.setScene(scene);

                        app_stage.show();
                    }

                } else if (u.getEnabled() != 1) {
                    System.out.println("disabled");
                } 

            
        } 
        /*String MDPtest=a.recevoirMDPavecUserName(username);
       if ((MDPtest.equals(password))&&(!MDPtest.equals("")))
       {
           
           
           Notifications notificiationBuilder = Notifications.create()
                   .title("validé")
                   .text("done !")
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
       else if (!(MDPtest.equals(password))||(MDPtest.equals("")))
       {
           Notifications notificiationBuilder = Notifications.create()
                   .title("problem d'authentification")
                   .text("Veuillez verifier votre username et password")
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
           
           
           
           
           
       }*/
    }

    }
}
