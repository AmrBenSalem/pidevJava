/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import services.ObjetCRUD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Session;
import static entities.Session.user;
import entities.User;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author Bader
 */
public class AdminAffichageObjetSingle implements Initializable {

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
    private JFXTextArea description;

    static int id;
    @FXML
    private JFXButton btn;
    @FXML
    private Label labnom;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userlab.setText("User");

        typelab.setText("Type");
        lieulab.setText("Lieu");
        datelab.setText("Date");

        ObjetCRUD ev = new ObjetCRUD();
        Objet e;
        User u;
        UserCRUD uc = new UserCRUD();
        try {
            e = ev.getByID(id);
            System.out.println(id);
            labtype.setText(e.getType());
            // labNature.setText(e.getNature());
            //  labDescription.setText(e.getDescription());
            lablieu.setText(e.getLieu());
            Image img = new Image("http://127.0.0.1/pidev2/web/" + e.getPhoto(), true);
            imv.setImage(img);
            imv.setVisible(true);

            // System.out.println(e);
            labuser.setText(uc.getByID(e.getUser()).getUserName());
            // System.out.println(uc.getByID(e.getUser()));
            description.setText(e.getDescription());
            description.setEditable(false);
            labdate.setText(e.getDate().toString());
            /* labdatefin.setText(e.getDateFin().toString());
        labnbrplace.setText(e.getNombre()+"");
        labpropriete.setText(e.getPropriete()+"");
        description.setText(e.getDescription());*/
        } catch (SQLException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*  public void approuver (ActionEvent event){
    
        Window owner1 = btn.getScene().getWindow();
        ObjetCRUD ev = new ObjetCRUD();
        
        try {
            Objet o = ev.getByID(id);
            ev.(1);
            System.out.println("approuvééééééé!!!");
          
        } catch (SQLException ex) {
            Logger.getLogger(EventsAffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    @FXML
    private void approuver(ActionEvent event) {
        ObjetCRUD oc = new ObjetCRUD();
        Objet o = new Objet();
        
        Parent root;
        try {
            oc.approuver(AdminAffichageObjetSingle.id);

            root = FXMLLoader.load(getClass().getResource("AdminObjetNonApprouvés.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } 
        catch (IOException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ObjetCRUD oc = new ObjetCRUD();
        Objet o = new Objet();

        Parent root;
        try {
            oc.supprimerObjet(o, AdminAffichageObjetSingle.id);

            root = FXMLLoader.load(getClass().getResource("AdminObjetNonApprouvés.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } 
        catch (IOException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root;    
        root = FXMLLoader.load(getClass().getResource("AdminObjetNonApprouvés.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
    }

}
