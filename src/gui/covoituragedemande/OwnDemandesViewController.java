/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoituragedemande;

import gui.covoiturage.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import entities.CoVoiturage;
import entities.CoVoiturageRequests;
import entities.Session;
import entities.User;
import gui.DashboardCoVoiturageController;
import gui.LoginController;
import static gui.covoituragedemande.DemandesViewController.covInfo;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceCoVoiturage;
import util.TimeSpinner;
import java.util.Date;
import javafx.scene.input.MouseEvent;
import services.ServiceCoVoiturageRequests;
import services.UserCRUD;
import util.TimeAgo;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class OwnDemandesViewController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    @FXML
    private Pane CoVoiturage1;
    private TableView<CoVoiturage> demandesTable;
    private TableColumn<?, ?> userDemandesTable;
    private TableColumn<?, ?> departDemandesTable;
    private TableColumn<?, ?> destinationDemandesTable;
    private TableColumn<?, ?> dateDemandesTable;
    private TableColumn<?, ?> etatDemandesTable;
    ServiceCoVoiturage serviceCoVoiturage;
    List<CoVoiturage> demandesList;
    @FXML
    private Pane testPane;
    public static Pane littlePane;
    public ServiceCoVoiturage cs;
    public ServiceCoVoiturageRequests cr;
    @FXML
    private AnchorPane vboxAnchorPane;
    @FXML
    private JFXButton redirectButtonCov;
    @FXML
    private AnchorPane vboxAnchorPaneSug;
    @FXML
    private VBox testPaneSug;
    public int counter;
    
    public User user = Session.getUser();
    public UserCRUD SUser = new UserCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cs = new ServiceCoVoiturage();
        cr = new ServiceCoVoiturageRequests();
        try {
            Instance();
        } catch (IOException ex) {
            Logger.getLogger(DemandesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        drawerLeft.open();
        //  pageLabel.setText(String.valueOf(LeftMenuController.pageNameLabel));

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Instance() throws IOException {
        ArrayList<CoVoiturage> listOfDemandes = new ArrayList<>();
        try {
            listOfDemandes.addAll(cs.GetOwnCovoituragePerType("d", Session.getUser().getId()));
        } catch (SQLException ex) {
            Logger.getLogger(DemandesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Pane panee = FXMLLoader.load(getClass().getResource("DemandeLine.fxml"));
        if (listOfDemandes.size()>0){
            counter = listOfDemandes.size();
        
//        vboxAnchorPaneSug.setMinHeight(54 * listOfOffres.size());
//        vboxAnchorPaneSug.setMaxHeight(54 * listOfOffres.size());
//        vboxAnchorPaneSug.setPrefHeight(54 * listOfOffres.size());

        

//        for (int x = 0; x < panee.getChildren().size(); x++) {
//            System.out.println(x + "  " + panee.getChildren().get(x).toString());
//        }

        for (int k = 0; k < listOfDemandes.size(); k++) {

            Pane pane = FXMLLoader.load(getClass().getResource("DemandeLine.fxml"));

            Label userField = new Label();
            userField.setLayoutX(pane.getChildren().get(2).getLayoutX());
            userField.setLayoutY(pane.getChildren().get(2).getLayoutY());
            pane.getChildren().set(2, userField);

            Label departField = new Label();
            departField.setLayoutX(pane.getChildren().get(4).getLayoutX());
            departField.setLayoutY(pane.getChildren().get(4).getLayoutY());
            pane.getChildren().set(4, departField);

            Label destinationField = new Label();
            destinationField.setLayoutX(pane.getChildren().get(6).getLayoutX());
            destinationField.setLayoutY(pane.getChildren().get(6).getLayoutY());
            pane.getChildren().set(6, destinationField);

            Label dateField = new Label();
            dateField.setLayoutX(pane.getChildren().get(8).getLayoutX());
            dateField.setLayoutY(pane.getChildren().get(8).getLayoutY());
            pane.getChildren().set(8, dateField);

            /*Label etatField = new Label();
            etatField.setLayoutX(pane.getChildren().get(9).getLayoutX());
            etatField.setLayoutY(pane.getChildren().get(9).getLayoutY());
            pane.getChildren().set(9, etatField);*/
            CoVoiturage demande;
            demande = listOfDemandes.get(k);
            userField.setText(String.valueOf(SUser.getUser(demande.getUser()).getUserName()));
            departField.setText(String.valueOf(demande.getDepart()));
            // departField.setMaxSize(3, 3);
            destinationField.setText(String.valueOf(demande.getDestination()));
            //PrettyTime p = new PrettyTime();
            //dateField.setText(String.valueOf(TimeAgo.toDuration(offre.getDate().getTime())));

            dateField.setText(String.valueOf(TimeAgo.toDuration(System.currentTimeMillis() - demande.getUpdated().getTime())));

            //etatField.setText(String.valueOf(offre.getOnetime()));
            JFXButton btnInfo = new JFXButton();
            btnInfo = (JFXButton) pane.getChildren().get(0);
            pane.getChildren().set(0, btnInfo);
            btnInfo.setOnAction((event) -> {
                CoVoiturage cov = new CoVoiturage();
                covInfo = cs.readCoVoiturage(demande.getId());
                redirectToInfo(event);
            });

            JFXButton request = new JFXButton("Request");
            request = (JFXButton) pane.getChildren().get(14);
            pane.getChildren().set(14, request);
            request.setOnAction((event) -> {
                CoVoiturageRequests cod = new CoVoiturageRequests(demande.getId(), Session.getUser().getId(), "a", new Timestamp(System.currentTimeMillis()));
                ServiceCoVoiturageRequests scor = new ServiceCoVoiturageRequests();
                scor.addRequest(cod);
                //System.out.println("bbbbbbbb" + offre.getId());
                //cov = cs.readCoVoiturage(offre.getId());
                //cs.deleteCoVoiturage(cov);
                Refresh(event);
            });

            JFXButton btnUpdatee = new JFXButton();
            btnUpdatee = (JFXButton) pane.getChildren().get(12);
            pane.getChildren().set(12, btnUpdatee);
            btnUpdatee.setOnAction((event) -> {
                CoVoiturage cov = new CoVoiturage();
                //System.out.println("bbbbbbbb" + offre.getId());
                cov = cs.readCoVoiturage(demande.getId());
                //cs.deleteCoVoiturage(cov);
                Refresh(event);
            });

            JFXButton btnDelete = new JFXButton();
            btnDelete = (JFXButton) pane.getChildren().get(13);
            pane.getChildren().set(13, btnDelete);
            btnDelete.setOnAction((event) -> {
                try {
                    CoVoiturage cov = new CoVoiturage();
                    //System.out.println("bbbbbbbb" + offre.getId());
                    cov = cs.readCoVoiturage(demande.getId());
                    cs.deleteCoVoiturage(cov);
                    Refresh(event);
                } catch (SQLException ex) {
                    Logger.getLogger(DemandesViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            request.setVisible(false);
            btnDelete.setVisible(true);
            btnUpdatee.setVisible(true);

            testPaneSug.getChildren().add(pane);

            ArrayList<CoVoiturageRequests> listOfRequestss = new ArrayList<>();
            try {
                listOfRequestss.addAll(cr.GetOwnCovoiturageRequestsIdc(demande.getId()/* USER */));
                System.out.println(listOfRequestss.size());
                //System.out.println("aaa");
            } catch (SQLException ex) {
                Logger.getLogger(OwnDemandesViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("bbbbbbbbbbbbb");
            if (listOfRequestss.size() > 0) {
                counter = counter + listOfRequestss.size();
                System.out.println("aaaaaaaaaaaaaaaa");
                /////hhhhhhhhhhhhhhhhhhhh
                for (int j = 0; j < listOfRequestss.size(); j++) {

                    Pane paneR = FXMLLoader.load(getClass().getResource("DemandeLineR.fxml"));

                    Label userFieldR = new Label();
                    userFieldR.setLayoutX(paneR.getChildren().get(1).getLayoutX());
                    userFieldR.setLayoutY(paneR.getChildren().get(1).getLayoutY());
                    paneR.getChildren().set(1, userFieldR);

                    Label dateFieldR = new Label();
                    dateFieldR.setLayoutX(paneR.getChildren().get(2).getLayoutX());
                    dateFieldR.setLayoutY(paneR.getChildren().get(2).getLayoutY());
                    paneR.getChildren().set(2, dateFieldR);

                    Label etatFieldR = new Label();
                    etatFieldR.setLayoutX(paneR.getChildren().get(4).getLayoutX());
                    etatFieldR.setLayoutY(paneR.getChildren().get(4).getLayoutY());
                    paneR.getChildren().set(4, etatFieldR);

                    CoVoiturageRequests demandeR;
                    demandeR = listOfRequestss.get(j);

                    userFieldR.setText(String.valueOf(SUser.getUser(demandeR.getUser()).getUserName()));
                    dateFieldR.setText(String.valueOf(TimeAgo.toDuration(System.currentTimeMillis() - demandeR.getCreated().getTime())));

                    if (demandeR.getEtat().equals("a")) {
                        etatFieldR.setText("En attente");

                    } else if (demandeR.getEtat().equals("c")) {
                        etatFieldR.setText("Accepté");
                    } else if (demandeR.getEtat().equals("r")) {
                        etatFieldR.setText("Refusé");
                        //cr.deleteRequestOffre(offreR);
                    }

                    //etatField.setText(String.valueOf(offre.getOnetime()));
                    JFXButton btnUpdateeR = new JFXButton();
                    btnUpdateeR = (JFXButton) paneR.getChildren().get(5);
                    paneR.getChildren().set(5, btnUpdateeR);
                    btnUpdateeR.setOnAction((event) -> {
                        cr.declineRequestOffre(demandeR);
                        Refresh(event);
                    });

                    JFXButton btnDeleteR = new JFXButton();
                    btnDeleteR = (JFXButton) paneR.getChildren().get(6);
                    paneR.getChildren().set(6, btnDeleteR);
                    btnDeleteR.setOnAction((event) -> {
                        cr.acceptRequestOffre(demandeR);
                        Refresh(event);
                    });

                    if (demandeR.getEtat().equals("c")) {
                        btnDeleteR.setVisible(false);
                        btnUpdateeR.setVisible(false);
                        Label annuler = new Label();
                        annuler.setText("Annuler");
                        annuler.setLayoutX(paneR.getChildren().get(5).getLayoutX());
                        annuler.setLayoutY(paneR.getChildren().get(5).getLayoutY());
                        paneR.getChildren().set(5, annuler);

                        annuler.setOnMouseClicked((event) -> {
                            cr.deleteRequestOffre(demandeR);
                            Refresh(event);
                        });

                    } else if (demandeR.getEtat().equals("r")) {
                        btnDeleteR.setVisible(false);
                        btnUpdateeR.setVisible(false);
                    }

                    //request.setVisible(false);
                    testPaneSug.getChildren().add(paneR);

                }
                //hhhhhhhhhhhhhhhhhhhhhhhh

//                testPaneSug.getChildren().add(paneR);
            }

        }
        } else {
            counter = 1;
            Pane pane = FXMLLoader.load(getClass().getResource("DemandeLine_1.fxml"));
            testPaneSug.getChildren().add(pane);
        }
        
        ArrayList<CoVoiturageRequests> listOfRequestss = new ArrayList<>();
        try {
            listOfRequestss.addAll(cr.GetOwnCovoiturageRequests(user.getId()/* USER */));
            //System.out.println("aaa");
        } catch (SQLException ex) {
            Logger.getLogger(OwnDemandesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listOfRequestss.size()>0){
        CoVoiturageRequests demandeR = listOfRequestss.get(0);
        CoVoiturage demande = cs.readCoVoiturage(demandeR.getIdc());
        System.out.println(demandeR);
        Pane pane = FXMLLoader.load(getClass().getResource("DemandeLine.fxml"));

        Label userField = new Label();
        userField.setLayoutX(pane.getChildren().get(2).getLayoutX());
        userField.setLayoutY(pane.getChildren().get(2).getLayoutY());
        pane.getChildren().set(2, userField);

        Label departField = new Label();
        departField.setLayoutX(pane.getChildren().get(4).getLayoutX());
        departField.setLayoutY(pane.getChildren().get(4).getLayoutY());
        pane.getChildren().set(4, departField);

        Label destinationField = new Label();
        destinationField.setLayoutX(pane.getChildren().get(6).getLayoutX());
        destinationField.setLayoutY(pane.getChildren().get(6).getLayoutY());
        pane.getChildren().set(6, destinationField);

        Label dateField = new Label();
        dateField.setLayoutX(pane.getChildren().get(8).getLayoutX());
        dateField.setLayoutY(pane.getChildren().get(8).getLayoutY());
        pane.getChildren().set(8, dateField);

        Label etatFieldR = new Label();
        etatFieldR.setLayoutX(pane.getChildren().get(11).getLayoutX());
        etatFieldR.setLayoutY(pane.getChildren().get(11).getLayoutY());
        pane.getChildren().set(11, etatFieldR);

        /*Label etatField = new Label();
            etatField.setLayoutX(pane.getChildren().get(9).getLayoutX());
            etatField.setLayoutY(pane.getChildren().get(9).getLayoutY());
            pane.getChildren().set(9, etatField);*/
        userField.setText(String.valueOf(SUser.getUser(demande.getUser()).getUserName()));
        departField.setText(String.valueOf(demande.getDepart()));
        // departField.setMaxSize(3, 3);
        destinationField.setText(String.valueOf(demande.getDestination()));
        //PrettyTime p = new PrettyTime();
        //dateField.setText(String.valueOf(TimeAgo.toDuration(offre.getDate().getTime())));

        dateField.setText(String.valueOf(TimeAgo.toDuration(System.currentTimeMillis() - demande.getUpdated().getTime())));

        //etatField.setText(String.valueOf(offre.getOnetime()));
        JFXButton btnInfo = new JFXButton();
        btnInfo = (JFXButton) pane.getChildren().get(0);
        pane.getChildren().set(0, btnInfo);
        btnInfo.setOnAction((event) -> {
            CoVoiturage cov = new CoVoiturage();
            covInfo = cs.readCoVoiturage(demande.getId());
            redirectToInfo(event);
        });

        pane.getChildren().get(14).setVisible(false);
        pane.getChildren().get(13).setVisible(false);
        pane.getChildren().get(12).setVisible(false);

        if (demandeR.getEtat().equals("c") || demandeR.getEtat().equals("a")) {
            Label annuler = new Label();
            annuler.setText("Annuler");
            annuler.setLayoutX(pane.getChildren().get(13).getLayoutX());
            annuler.setLayoutY(pane.getChildren().get(13).getLayoutY());
            pane.getChildren().set(13, annuler);
            annuler.setOnMouseClicked((event) -> {
                cr.deleteRequestOffre(demandeR);
                Refresh(event);
            });

        } 

        if (demandeR.getEtat().equals("a")) {
            etatFieldR.setText("En attente");

        } else if (demandeR.getEtat().equals("c")) {
            etatFieldR.setText("Accepté");
        } else if (demandeR.getEtat().equals("r")) {
            etatFieldR.setText("Refusé");
            cr.deleteRequestOffre(demandeR);
        }

        //testPaneSug.getChildren().add(pane);
        testPane.getChildren().add(pane);
        } else {
            Pane pane = FXMLLoader.load(getClass().getResource("DemandeLine_1.fxml"));
            testPane.getChildren().add(pane);
        }
        
        
        vboxAnchorPaneSug.setMinHeight(54 * counter);
        vboxAnchorPaneSug.setMaxHeight(54 * counter);
        vboxAnchorPaneSug.setPrefHeight(54 * counter);
        
        vboxAnchorPane.setMinHeight(54);
        vboxAnchorPane.setMaxHeight(54);
        vboxAnchorPane.setPrefHeight(54);

    }

    public void Refresh(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("OwnDemandesView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public void Refresh(MouseEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("OwnDemandesView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("/gui/covoiturage/CoVoiturageView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    private void redirectToInfo(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("InfoDemandeView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
}
