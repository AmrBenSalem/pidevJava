/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import entities.Interaction;
import entities.Objet;
import entities.Session;
import entities.Traitafter;
import entities.User;
import static ja.AdminAffichageObjetSingle.id;
//import static ja.affichObjTrouvAction.num;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ObjetCRUD;
import services.ServiceInteraction;
import services.ServiceTraitAfter;
import services.UserCRUD;

/**
 *
 * @author bader
 */
public class AffichObjTrouvSingle implements Initializable {

    @FXML
    private Label labtype;
    @FXML
    private Label labuser;
    @FXML
    private Label lablieu;

    @FXML
    private ImageView imv;
    @FXML
    private Label labdate;

    @FXML
    private Label numeroUser;

    @FXML
    private JFXTextArea description;

    static int idob = Integer.parseInt(affichageObjTrouv1Controller.id_Objet);
    @FXML
    private JFXButton btn1;
    @FXML
    private Label userlab;
    @FXML
    private Label typelab;
    @FXML
    private Label lieulab;
    @FXML
    private Label datelab;
    @FXML
    private JFXButton partager;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton reclam;
    @FXML
    private JFXButton suppreclam;

    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userlab.setText("User");
        ServiceInteraction it = new ServiceInteraction();

        typelab.setText("Type");
        lieulab.setText("Lieu");
        datelab.setText("Date");
        numeroUser.setText("");
        supprimer.setVisible(false);
        modifier.setVisible(false);
        reclam.setVisible(false);
        suppreclam.setVisible(false);
        Interaction interr = new Interaction();
        interr = it.getByIdObjet(idob);

        ObjetCRUD ev = new ObjetCRUD();
        Objet e;
        User u;
        UserCRUD uc = new UserCRUD();
        User h = uc.getUser(interr.getUser());
        try {
            User us = uc.getByID(Session.getIdThisUser());
        } catch (SQLException ex) {
            Logger.getLogger(AffichObjTrouvSingle.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            e = ev.getByID(idob);
            lablieu.setText(e.getLieu());
            Image img = new Image("http://127.0.0.1/pidev2/web/" + e.getPhoto(), true);
            imv.setImage(img);
            imv.setVisible(true);
            labtype.setText(e.getType());

            // System.out.println(e);
            labuser.setText(uc.getByID(e.getUser()).getUserName());
            // System.out.println(uc.getByID(e.getUser()));
            description.setText(e.getDescription());
            description.setEditable(false);
            labdate.setText(e.getDate().toString());

            if (Session.getIdThisUser() == e.getUser()) {
                supprimer.setVisible(true);
                modifier.setVisible(true);
                if (it.getByIdObjet(e.getId()).getId() != 0){
                    
                numeroUser.setText("L'objet trouvé a été perdu par : " + h.getUserName() + " Numéro téléphone : " + h.getTelephone());
                }
            } else {
                if (it.getByIdObjet(e.getId()).getId() == 0) {
                    reclam.setVisible(true);
                } else {
                    if ((Session.getIdThisUser()) == it.getByIdObjet(e.getId()).getUser()) {
                        suppreclam.setVisible(true);
                    } else {
                        numeroUser.setText("L'objet trouvé a été perdu par : " + h.getUserName() + " Numéro téléphone : " + h.getTelephone());
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {

        Parent root;
        ModifObjTrouvController.a = idob;
        // System.out.println("l objet est "+idob);
        root = FXMLLoader.load(getClass().getResource("modifObjTrouv1.fxml"));
        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ObjetCRUD oc = new ObjetCRUD();
        Objet o = new Objet();

        Parent root;
        try {
            oc.supprimerObjet(o, idob);

            root = FXMLLoader.load(getClass().getResource("affichageObjTrouv1View.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void partager(ActionEvent event) throws SQLException {
        Objet e = new Objet();
        ObjetCRUD ev = new ObjetCRUD();
        e = ev.getByID(idob);
        String token = "EAACEdEose0cBADhYFQUThZCmMjq80bMa8pDKFE5SJ240OSLod8eEZConDtTPibBmKhMu0bfBHUsQ01CfMJoCXvx5Ph7cciFieQoiPl7HhAjEX1J9RZBQUdGdJNzwCjvNwPeiZBVFHZBEt14vQYYnIBLwEqjrEsqp7MOjP11wufSe2Kr15ZBIWqTZA1rhid3z652UZBarfQbZCYQIZCDweoHApR";
        FacebookClient fb = new DefaultFacebookClient(token);
        FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", e.getNature() + e.getDescription() + e.getType()));
        System.out.println("fb.com" + r.getId());

    }

    @FXML
    private void reclamer(ActionEvent event) throws IOException, SQLException {
        UserCRUD u = new UserCRUD();
        User us = u.getByID(Session.getIdThisUser());
            
        Parent root;
         
            ServiceInteraction it = new ServiceInteraction();
            System.out.println("here");
            Interaction a = new Interaction(Session.getIdThisUser(), idob, us.getUserName() + "est celui qui a perdu l'objet");
            it.ajouterinteraction(a);
            System.out.println("hello");
            String msg = "est le propriétaire de cet objet";
            System.out.println(msg);
            Interaction i = new Interaction(Session.getIdThisUser(), idob, msg);
            System.out.println("lenna");
            System.out.println(i);
            root = FXMLLoader.load(getClass().getResource("affichageObjTrouv1View.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
     
    }

    @FXML
    private void supprimerreclam(ActionEvent event) {
        idob = Integer.parseInt(affichageObjTrouv1Controller.id_Objet);
        Parent root;
        try {
            ServiceInteraction s = new ServiceInteraction();

            Interaction a = s.getByIdObjet(idob);
            s.supprimerinteraction(a.getId());

            root = FXMLLoader.load(getClass().getResource("affichageObjTrouv1View.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(affichageObjTrouv1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("affichageObjTrouv1View.fxml"));
        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);
    }

}
