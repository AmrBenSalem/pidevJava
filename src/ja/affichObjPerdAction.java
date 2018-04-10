/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import com.jfoenix.controls.JFXDrawer;
import entities.Interaction;
import entities.Objet;
import entities.Session;
import entities.Traitafter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ObjetCRUD;
import services.ServiceInteraction;
import services.ServiceTraitAfter;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import gui.DashboardCoVoiturageController;
import javafx.scene.control.Alert;

/**
 *
 * @author bader
 */
public class affichObjPerdAction implements Initializable {
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private ListView<Objet> listOT;
    static int num;
    Objet ob;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          /*  drawerLeft.open();
           
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        ObservableList<Objet> data = FXCollections.observableArrayList();
        ObjetCRUD o = new ObjetCRUD();
        ServiceInteraction i = new ServiceInteraction();
        ServiceTraitAfter se = new ServiceTraitAfter();
        data.addAll(o.affichobjperd());
        listOT.getItems().addAll(data);
        listOT.setCellFactory(new Callback<ListView<Objet>, ListCell<Objet>>() {
            @Override
            public ListCell<Objet> call(ListView<Objet> arg0) {
                return new ListCell<Objet>() {

                    @Override
                    protected void updateItem(Objet item, boolean bln) {
                        super.updateItem(item, bln);

                        if (item != null) {
                            // int a=item.getId();
                            //System.out.println(item);
                            Image image = new Image("http://127.0.0.1/pidev2/web/" + item.getPhoto(), true);
                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(70);
                            imv.setFitWidth(70);
                            Label t = new Label(String.valueOf(item.getId()));
                            t.setStyle("-fx-font-size: 30 arial;");
                            Button partager = new Button("Partager");
                            Button suppr = new Button("Supprimer");
                            Button modif = new Button("Modifier");
                            Button reclam = new Button("Réclamation");
                            Button signaler = new Button("Signaler");
                            Button suppreclam = new Button("Annuler ma réclamation");
                            partager.setVisible(true);
                            suppr.setVisible(false);
                            modif.setVisible(false);
                            reclam.setVisible(false);
                            suppreclam.setVisible(false);
                            signaler.setVisible(false);
                            modif.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    num = item.getId();
                                    Parent root;
                                    try {

                                        root = FXMLLoader.load(getClass().getResource("modifObjPerd1.fxml"));
                                        Scene scene = new Scene(root);

                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        app_stage.setScene(scene);

                                        app_stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            });
                            partager.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                   // showMessageDialog(null, "Event shared with success");
                                    String token = "EAACEdEose0cBAMpgAk83WZAeZAnp6S5KYBxaZCXyjYVjbPuNzS9nXAk4zHRzOzontgF5SB9co1ZCPuiZAGV4ZACNgZCaIgfbuv2hZAAZAapKD43KldrwazkdSlxdoj2oiyXLXT97NUHLQaUQxC49MlER4MYXNpWWZBeZB0oyQZAxD41pWBQKcYOIFKzmSqN5BNJGmiy9qpcRqlsL1mQpiT5ANZAzz9D1mYmy2hZBgZD";
                                    FacebookClient fb = new DefaultFacebookClient(token);
                                    FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", "Suuuuuuuuuuuuuuuu :D"));
                                    System.out.println("fb.com" + r.getId());

                                    /**
                                     * Alert alert = new
                                     * Alert(Alert.AlertType.INFORMATION);
                                     * alert.setTitle("Information Dialog");
                                     * alert.setHeaderText(null);
                                     * alert.setContentText("publication
                                     * partagée sur facebook avec succés!");
                                    alert.show();*
                                     */
                                }
                            });
                            signaler.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    num = item.getId();
                                    Parent root;

                                    try {
                                        try {
                                            Traitafter ta = new Traitafter(Session.getIdThisUser(), Session.getUser().getNom() + " signale cette réclamation ", i.getByIdObjet(num).getId());
                                            se.ajouterTraitAfter(ta);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                         
                                        root = FXMLLoader.load(getClass().getResource("affichObjPerd-1.fxml"));
                                        Scene scene = new Scene(root);

                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        app_stage.setScene(scene);

                                        app_stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    //To change body of generated methods, choose Tools | Templates.
                                    //To change body of generated methods, choose Tools | Templates.
                                }
                            });
                            //supprimer objet
                            suppr.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    num = item.getId();

                                    Parent root;
                                    try {
                                        o.supprimerObjet(ob, num);

                                        root = FXMLLoader.load(getClass().getResource("affichObjPerd-1.fxml"));
                                        Scene scene = new Scene(root);

                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        app_stage.setScene(scene);

                                        app_stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }//To change body of generated methods, choose Tools | Templates.//To change body of generated methods, choose Tools | Templates.
                                }
                            });
                            //ajouter interaction
                            reclam.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    num = item.getId(); //To change body of generated methods, choose Tools | Templates.

                                    Parent root;
                                    try {
                                        Interaction inter = new Interaction(Session.getIdThisUser(), num, Session.getUser().getNom() + " est le propriétaire de Cet Objet");
                                        try {
                                            i.ajouterinteraction(inter);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        root = FXMLLoader.load(getClass().getResource("affichObjPerd-1.fxml"));
                                        Scene scene = new Scene(root);

                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        app_stage.setScene(scene);

                                        app_stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //Supprimer Interaction
                            suppreclam.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    num = item.getId();
                                    Parent root;
                                    try {
                                        ServiceInteraction s = new ServiceInteraction();

                                        Interaction a = s.getByIdObjet(num);
                                        s.supprimerinteraction(a.getId());

                                        root = FXMLLoader.load(getClass().getResource("affichObjPerd-1.fxml"));
                                        Scene scene = new Scene(root);

                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        app_stage.setScene(scene);

                                        app_stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(affichObjPerdAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            //Utilisateur Connecté=Objet.userid

                            if (item.getUser() == Session.getIdThisUser()) {
                                suppr.setVisible(true);
                                modif.setVisible(true);
                                HBox vBox = new HBox(imv, t,
                                        new Label(String.valueOf(item.getUser())),
                                        new Label(item.getType()),
                                        new Label(item.getDescription()),
                                        new Label(String.valueOf(item.getDate())),
                                        new Label(item.getLieu()),
                                        modif, suppr, partager);
                                vBox.setSpacing(15);

                                setGraphic(vBox);
                            } else {

                                //System.out.println(i.getByIdObjet(item.getId()));
                                //Utilisateur connecté!=Objet.userid+aucune interaction sur l'objet
                                //Utilisateur connecté!=Objet.userid+aucune interaction sur l'objet(ajouter réclamation)
                                if (i.getByIdObjet(item.getId()).getId() == 0) {

                                    reclam.setVisible(true);
                                    HBox vBox = new HBox(imv, t,
                                            new Label(String.valueOf(item.getUser())),
                                            new Label(item.getType()),
                                            new Label(item.getDescription()),
                                            new Label(String.valueOf(item.getDate())),
                                            new Label(item.getLieu()),
                                            reclam, partager);
                                    vBox.setSpacing(15);

                                    setGraphic(vBox);
                                } //System.out.println(i.getByIdObjet(item.getId()).getUser());
                                //Utilisateur connecté!=Objet.userid+aucune interaction sur l'objet(supprimer réclamation)
                                else {

                                    if ((Session.getIdThisUser()) == i.getByIdObjet(item.getId()).getUser()) {
                                        suppreclam.setVisible(true);
                                        HBox vBox = new HBox(imv, t,
                                                new Label(String.valueOf(item.getUser())),
                                                new Label(item.getType()),
                                                new Label(item.getDescription()),
                                                new Label(String.valueOf(item.getDate())),
                                                new Label(item.getLieu()),
                                                suppreclam, partager);
                                        vBox.setSpacing(15);
                                        setGraphic(vBox);
                                    } else {
                                        signaler.setVisible(true);
                                        HBox vBox = new HBox(imv, t,
                                                new Label(String.valueOf(item.getUser())),
                                                new Label(item.getType()),
                                                new Label(item.getDescription()),
                                                new Label(String.valueOf(item.getDate())),
                                                new Label(item.getLieu()),
                                                signaler, partager);
                                        vBox.setSpacing(15);
                                        setGraphic(vBox);

                                    }

                                }

                                //Utitry {
                                //if (item.getUser() != Session.getIdThisUser() && (Session.getIdThisUser()) != i.getByIdObjet(item.getId()).getUser() && i.getByIdObjet(item.getId()) != null) {
                                //Signaler La réclamation
                            }

                        } else {

                            setText(null);
                            setGraphic(null);

                        }
                    }

                };
            }

        }
        );

    }

}
