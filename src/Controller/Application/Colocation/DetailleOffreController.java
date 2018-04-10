/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import com.jfoenix.controls.JFXDrawer;
import entities.Colocation;
import entities.User;
import gui.DashboardCoVoiturageController;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.UserCRUD;
import util.Config;
import util.SendEmail;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class DetailleOffreController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private ImageView photo1;
    @FXML
    private ImageView photo2;
    @FXML
    private Label titre;
    @FXML
    private Label prix;
    @FXML
    private Label type;
    @FXML
    private Label desc;
    @FXML
    private Label meuble;
    @FXML
    private Label ville;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea description;
    public static Colocation col = new Colocation();

    /**
     * Initializes the controller class.
     */
    private UserCRUD userCrud;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            drawerLeft.open();
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);

         
            titre.setText(col.getTitre());
            prix.setText(String.valueOf(col.getLoyer()));
            type.setText(col.getType());
            meuble.setText(col.getMeuble());
            ville.setText(col.getVille());
            desc.setText(col.getDescription());

            if (col.getPhoto() != null) {
                File file = new File("src/images/colocation/" + col.getPhoto());
                if (file.exists()) {

                    Image image = new Image(file.toURI().toString(), 100, 100, false, false);
                    photo.setImage(image);
                }
            }

            if (col.getPhoto1() != null) {
                File file = new File("src/images/colocation/" + col.getPhoto1());
                if (file.exists()) {

                    Image image = new Image(file.toURI().toString(), 100, 100, false, false);
                    photo1.setImage(image);
                }
            }

            if (col.getPhoto2() != null) {
                File file = new File("src/images/colocation/" + col.getPhoto2());
                if (file.exists()) {

                    Image image = new Image(file.toURI().toString(), 100, 100, false, false);
                    photo2.setImage(image);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Repondre(ActionEvent event) {

        try {
            userCrud = new UserCRUD();

            User userEmet = userCrud.getUser(Config.idUser);
            User userRecp = userCrud.getUser(col.getUser_id());
            System.out.println(userEmet.getEmail()+"**");
             System.out.println("**"+ userRecp.getEmail());
            SendEmail.envoyer(userEmet.getEmail(), userRecp.getEmail(), sujet.getText(), description.getText());

        } catch (Exception ex) {
            Logger.getLogger(DetailleOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
