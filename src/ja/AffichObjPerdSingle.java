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
import static ja.AffichObjTrouvSingle.idob;
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
public class AffichObjPerdSingle implements Initializable {

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

    static int idob=Integer.parseInt(affichageObjPerdController1.id_Objet) ;
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
    private AnchorPane container;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label numerouser;

   @Override
    public void initialize(URL location, ResourceBundle resources) {
        userlab.setText("User");
        numerouser.setText("");
        typelab.setText("Type");
        lieulab.setText("Lieu");
        datelab.setText("Date");
        supprimer.setVisible(false);
        modifier.setVisible(false);
        reclam.setVisible(false);
        suppreclam.setVisible(false);
      
        ObjetCRUD ev = new ObjetCRUD();
        Objet e;
         ServiceInteraction it=new ServiceInteraction();
        Interaction interr= new Interaction();
        System.out.println(idob);
        interr = it.getByIdObjet(idob);
        
       
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

            // System.out.println(e);
            labuser.setText(uc.getByID(e.getUser()).getUserName());
            // System.out.println(uc.getByID(e.getUser()));
            description.setText(e.getDescription());
            description.setEditable(false);
            labdate.setText(e.getDate().toString());
            if(Session.getIdThisUser()==e.getUser()){
                System.out.println("dkhalt");
                supprimer.setVisible(true);
                modifier.setVisible(true);
               //numerouser.setText("L'objet perdu a été trouvé par : " + h.getUserName() + " Numéro téléphone : " + h.getTelephone());
                            
            }
            else
            {
                if (it.getByIdObjet(e.getId()).getId() == 0)
                { reclam.setVisible(true);
                  if (it.getByIdObjet(e.getId()).getId() != 0){
                    
 numerouser.setText("L'objet perdu a été trouvé par : " + h.getUserName() + " Numéro téléphone : " + h.getTelephone()); }}
                else
                {
                    if((Session.getIdThisUser()) == it.getByIdObjet(e.getId()).getUser()) 
                    {
                        suppreclam.setVisible(true);
                    }
                    else
                    {
                         numerouser.setText("L'objet perdu a été trouvé par : " + h.getUserName() + " Numéro téléphone : " + h.getTelephone());
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Parent root;
         ModifObjPerdController.b=idob;
        root = FXMLLoader.load(getClass().getResource("modifObjPerd1.fxml"));
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

            root = FXMLLoader.load(getClass().getResource("affichageObjPerdView.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminAffichageObjetSingle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void partager(ActionEvent event) {
        String token = "EAACEdEose0cBACBjrZAlnk4wCHJh579Vw16tKTGGCsnzDk9Np4yAdSvjhmoe5V3GNrmllixpqzKEkuua6JJKtBJ85p3ihe3daWrq1PFZCl4ajFzq4o0XnexYHUTLVZCCihE426jFW9SJAjXItvtTMCssMUG4V4z95FaLn0MVAJ8R6poMvZCixC3M5akuOuSUw807lqxPlDzP1wZA7x4jk";
        FacebookClient fb = new DefaultFacebookClient(token);
        FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", "Suuuuuuuuuuuuuuuu :D"));
        System.out.println("fb.com" + r.getId());

    }

    @FXML
    private void reclamer(ActionEvent event) throws SQLException, IOException {
  
            UserCRUD u = new UserCRUD();
            User useer = u.getByID(Session.getIdThisUser());
            Parent root;
       
            ServiceInteraction i = new ServiceInteraction();
            //System.out.println("l'utilisateur connecté est "+Session.getUser().getNom());
            System.out.println("l'id de l'utilisateur connecté est"+Session.getIdThisUser());
            System.out.println("l'id de l'objet est"+idob);
            Interaction inter = new Interaction(Session.getIdThisUser(), idob, useer.getUserName() + " est le propriétaire de Cet Objet");
            i.ajouterinteraction(inter);
          
            root = FXMLLoader.load(getClass().getResource("affichageObjPerdView.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
      
    }

    @FXML
    private void supprimerreclam(ActionEvent event) {
        
        Parent root;
        try {
            ServiceInteraction s = new ServiceInteraction();

            Interaction a = s.getByIdObjet(idob);
            s.supprimerinteraction(a.getId());

            root = FXMLLoader.load(getClass().getResource("affichageObjPerdView.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(affichageObjPerdController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     

    @FXML
    private void retour(ActionEvent event) throws IOException {
         
             Parent root;   
             root = FXMLLoader.load(getClass().getResource("affichageObjPerdView.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);
    }
    
    
}
