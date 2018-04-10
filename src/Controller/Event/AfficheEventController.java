/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Avis;
import entities.Event;
import entities.Session;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import services.ServiceAvis;
import services.ServiceEvent;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class AfficheEventController implements Initializable {

    @FXML
    private AnchorPane container_ajout;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    @FXML
    private JFXButton redirectButtonCov;
    @FXML
    private Pane CoVoiturage1;
    @FXML
    private Pane datePane;
    @FXML
    private DatePicker dateTextField;
    @FXML
    private Pane daysPane;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXDatePicker datefin;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField lieu;
    @FXML
    private JFXTextField nbmax;
    @FXML
    private JFXComboBox<String> categorie;
    @FXML
    private ImageView image_show;
    @FXML
    private JFXButton edit_event;
    @FXML
    private JFXButton delete_event;
    @FXML
    private JFXButton participer;
    @FXML
    private JFXButton annul_participate;
    @FXML
    private Rating rate;
    @FXML
    private JFXButton avis;
    @FXML
    private JFXListView list_rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rate.setVisible(false);
        avis.setVisible(false);
        delete_event.setVisible(false);
        edit_event.setVisible(false);
        participer.setVisible(false);
        annul_participate.setVisible(false);
        ServiceEvent se = new ServiceEvent();
        ServiceAvis sa = new ServiceAvis();
        titre.setEditable(false);
        description.setEditable(false);
        datedebut.setEditable(false);
        datefin.setEditable(false);
        lieu.setEditable(false);
        nbmax.setEditable(false);
        categorie.setEditable(false);
        
        
        //Date today = (Date) Calendar.getInstance().getTime();
        Event e=se.consulterEvent(Integer.parseInt(ListEventController.id_event));
        //System.out.println(today+"   "+e.getDateFin());
        Avis a = sa.consulterParticipations(e, Session.getIdThisUser());
        if(e.getIduser()==Session.getIdThisUser()){
            delete_event.setVisible(true);
            edit_event.setVisible(true);
        }
        
        if(e.getIduser()!=Session.getIdThisUser()&&a.getIduser()!=Session.getIdThisUser()  ){
            if(Session.getThisTimestamp().before(e.getDateDebut())){
            participer.setVisible(true);
            }
        }
        if(e.getIduser()!=Session.getIdThisUser()&&a.getIduser()==Session.getIdThisUser()  ){
            if(Session.getThisTimestamp().before(e.getDateDebut())){
            annul_participate.setVisible(true);
            }
        }
        
        if(e.getIduser()!=Session.getIdThisUser()&&a.getIduser()==Session.getIdThisUser() && a.getAvis()==0   ){
            if(Session.getThisTimestamp().after(e.getDateFin())){
            rate.setVisible(true);
            //avis.setVisible(true);
            annul_participate.setVisible(false);
            }
        }
        
        titre.setText(e.getTitre());
        description.setText(e.getDescription());
        datedebut.setValue(e.getDateDebut().toLocalDate());
        datefin.setValue(e.getDateFin().toLocalDate());
        lieu.setText(e.getLieu());
        nbmax.setText(e.getNb_max()+"");
        categorie.setValue(e.getCategorie());
         Image i = new Image("file:" + e.getPhoto());
         image_show.setImage(i);
         
         
                ArrayList<Avis> ar=sa.consulterAvis(e.getId());
                if(ar.size()==0){
                    list_rate.setVisible(false);
                }
                ObservableList<Avis> observableList1 = FXCollections.observableArrayList(ar);
                list_rate.setItems(observableList1);
                list_rate.setCellFactory(new Callback<JFXListView<Avis>, ListCell<Avis>>() {

            @Override
            public ListCell<Avis> call(JFXListView<Avis> arg0) {
                return new ListCell<Avis>() {

                    @Override
                    protected void updateItem(Avis item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {

                            UserCRUD us = new UserCRUD();
                            User user = null;
                            try {
                                user = us.getByID(item.getIduser());
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Rating t = new Rating();
                            t.setRating(item.getAvis());
                            //t.setStyle("-fx-font-size: 25 arial;");
                            //t.setFill(Color.web("#ff214f"));
                            VBox vBox = new VBox(t,
                                    //new Timestamp(item.s),
                                    new Text("Nom : "+user.getNom()+" Prénom : "+user.getPrenom())
                                    //new HBox(bt, bt1)
                            );
                            vBox.setSpacing(4);
                            
                            

                            HBox hBox = new HBox( vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        } else {

                            setText(null);
                            setGraphic(null);

                        }
                    }

                };
            }

        });
            
         
         
        // TODO
    }    

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
    }



    @FXML
    private void retour_events(ActionEvent event) throws IOException {
        Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container_ajout.getChildren().clear();
            container_ajout.getChildren().add(node);
        /*Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           
                        try {
                            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container_ajout.getChildren().clear();
            container_ajout.getChildren().add(node);*/
    }

    @FXML
    private void modifier_event(ActionEvent event) {
              Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           
                        try {
                            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ModifierEvent.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container_ajout.getChildren().clear();
            container_ajout.getChildren().add(node);
    }

    @FXML
    private void supp_event(ActionEvent event) throws SQLException {
        ServiceEvent se = new ServiceEvent();
        se.supprimerEvent(Integer.parseInt(ListEventController.id_event));
        
              Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           
                        try {
                            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ListEvent.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                           
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            container_ajout.getChildren().clear();
            container_ajout.getChildren().add(node);
    }

    @FXML
    private void participer(ActionEvent event) throws SQLException {
        
        ServiceEvent se = new ServiceEvent();
        Event e=se.consulterEvent(Integer.parseInt(ListEventController.id_event));
        if(e.getNb_max()!=0){
            ServiceAvis sa = new ServiceAvis();
            sa.ajouterParticipation(0, e.getId(), Session.getIdThisUser());
            e.setNb_max(e.getNb_max()-1);
            se.updateEvent(e, e.getId());
            System.out.println(e.getNb_max());
            nbmax.setText(e.getNb_max()+"");
            participer.setVisible(false);
            annul_participate.setVisible(true);
        }
        
        
    }

    @FXML
    private void annuler_participation(ActionEvent event) throws SQLException {
        
        ServiceEvent se = new ServiceEvent();
        Event e=se.consulterEvent(Integer.parseInt(ListEventController.id_event));
        
            ServiceAvis sa = new ServiceAvis();
            sa.annulerParticipation( e.getId(), Session.getIdThisUser());
            e.setNb_max(e.getNb_max()+1);
            se.updateEvent(e, e.getId());
            System.out.println(e.getNb_max());
            nbmax.setText(e.getNb_max()+"");
            participer.setVisible(true);
            annul_participate.setVisible(false);
        
    }

    @FXML
    private void rating(ActionEvent event) {
        
        ServiceEvent se = new ServiceEvent();
        Event e=se.consulterEvent(Integer.parseInt(ListEventController.id_event));
        ServiceAvis sa = new ServiceAvis();
        sa.addAvis((int) rate.getRating(), Session.getIdThisUser(), e.getId());
        System.out.println("---------------------Avis Ajouté------------------");
        
    }
    
}
    
