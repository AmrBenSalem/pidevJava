/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import entities.CoVoiturage;
import entities.CoVoiturageRequests;
import entities.CoVoiturageSuggestion;
import entities.Session;
import entities.User;
import gui.DashboardCoVoiturageController;
import gui.LoginController;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceCoVoiturage;
import services.ServiceCoVoiturageRequests;
import services.UserCRUD;
import util.Capitals;
import util.TimeAgo;

/**
 * FXML Controller class
 *
 * @author Justpro
 */
public class OffresViewController implements Initializable {

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
    private TableView<CoVoiturage> offresTable;
    private TableColumn<?, ?> userOffresTable;
    private TableColumn<?, ?> departOffresTable;
    private TableColumn<?, ?> destinationOffresTable;
    private TableColumn<?, ?> dateOffresTable;
    private TableColumn<?, ?> etatOffresTable;
    ServiceCoVoiturage serviceCoVoiturage;
    List<CoVoiturage> offresList;
    @FXML
    private Pane testPane;
    public static Pane littlePane;
    public ServiceCoVoiturage cs;
    @FXML
    private AnchorPane vboxAnchorPane;
    @FXML
    private JFXButton redirectButtonCov;
    @FXML
    private JFXButton buttonAddOffre;
    @FXML
    private AnchorPane vboxAnchorPaneSug;
    @FXML
    private VBox testPaneSug;
    public static CoVoiturage covInfo;
    public Capitals c = new Capitals();
    public User user = Session.getUser();
    public UserCRUD SUser = new UserCRUD();
    @FXML
    private JFXTextField searchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cs = new ServiceCoVoiturage();
        try {
            Instance();
        } catch (IOException ex) {
            Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void AddOffreAction(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("AddOffreView.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(page);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void Instance() throws IOException {
        ArrayList<CoVoiturage> listOfOffres = new ArrayList<>();
        try {
            listOfOffres.addAll(cs.GetCovoituragePerType("o"));
        } catch (SQLException ex) {
            Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Pane panee = FXMLLoader.load(getClass().getResource("OffreLine.fxml"));

        vboxAnchorPane.setMinHeight(54 * listOfOffres.size());
        vboxAnchorPane.setMaxHeight(54 * listOfOffres.size());
        vboxAnchorPane.setPrefHeight(54 * listOfOffres.size());

        vboxAnchorPaneSug.setMinHeight(54 * 3);
        vboxAnchorPaneSug.setMaxHeight(54 * 3);
        vboxAnchorPaneSug.setPrefHeight(54 * 3);

        if (listOfOffres.size() > 0)
        {
        for (int k = 0; k < listOfOffres.size(); k++) {
            Pane pane = FXMLLoader.load(getClass().getResource("OffreLine.fxml"));

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

            CoVoiturage offre;
            offre = listOfOffres.get(k);
            userField.setText(String.valueOf(SUser.getUser(offre.getUser()).getUserName()));
            departField.setText(String.valueOf(offre.getDepart()));
            destinationField.setText(String.valueOf(offre.getDestination()));

            dateField.setText(String.valueOf(TimeAgo.toDuration(System.currentTimeMillis() - offre.getUpdated().getTime())));

            ServiceCoVoiturageRequests scor = new ServiceCoVoiturageRequests();
            
            
                
            JFXButton request = new JFXButton("Request");
            request = (JFXButton) pane.getChildren().get(14);
            pane.getChildren().set(14, request);
            request.setOnAction((event) -> {
                CoVoiturageRequests cod = new CoVoiturageRequests(offre.getId(), user.getId(), "a", new Timestamp(System.currentTimeMillis()));
                scor.addRequest(cod);
                Refresh(event);
            });
            


            JFXButton btnInfo = new JFXButton();
            btnInfo = (JFXButton) pane.getChildren().get(0);
            pane.getChildren().set(0, btnInfo);
            btnInfo.setOnAction((event) -> {
                CoVoiturage cov = new CoVoiturage();
                covInfo = cs.readCoVoiturage(offre.getId());
                redirectToInfo(event);
            });

            JFXButton btnUpdatee = new JFXButton();
            btnUpdatee = (JFXButton) pane.getChildren().get(12);
            pane.getChildren().set(12, btnUpdatee);
            btnUpdatee.setOnAction((event) -> {
                CoVoiturage cov = new CoVoiturage();
                cov = cs.readCoVoiturage(offre.getId());
                covInfo = cs.readCoVoiturage(offre.getId());
                redirectToUpdate(event);
            });

            JFXButton btnDelete = new JFXButton();
            btnDelete = (JFXButton) pane.getChildren().get(13);
            pane.getChildren().set(13, btnDelete);
            btnDelete.setOnAction((event) -> {
                try {
                    CoVoiturage cov = new CoVoiturage();
                    cov = cs.readCoVoiturage(offre.getId());
                    cs.deleteCoVoiturage(cov);
                    Refresh(event);
                } catch (SQLException ex) {
                    Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            

            if (offre.getUser() == user.getId()) {
                request.setVisible(false);
                btnDelete.setVisible(true);
                btnUpdatee.setVisible(true);

            } else {
                btnDelete.setVisible(false);
                btnUpdatee.setVisible(false);
                if (scor.hasRequests(user)) {
                    request.setVisible(false);
                } else {
                    request.setVisible(true);
                }

            }

            testPane.getChildren().add(pane);

        }

        ArrayList<CoVoiturageSuggestion> listOfSugg = new ArrayList<>();
   
            
        for (int k = 0; k < listOfOffres.size(); k++) {
            double lat = abs(abs(c.getCapital().getLatitude()) - abs(listOfOffres.get(k).getDepart_lat()));
            double lng = abs(abs(c.getCapital().getLongitude()) - abs(listOfOffres.get(k).getDepart_lng()));
            double value = lat + lng;
            listOfSugg.add(new CoVoiturageSuggestion(listOfOffres.get(k).getId(), Session.getUser().getUserName(), listOfOffres.get(k).getUser(), listOfOffres.get(k).getDepart(), listOfOffres.get(k).getDestination(), value, listOfOffres.get(k).getUpdated()));
        }
        
        Collections.sort(listOfSugg, new CoVoiturageSuggestion());
        int j = 0;
        ServiceCoVoiturageRequests scor = new ServiceCoVoiturageRequests();
        for (int k = 0; k < listOfSugg.size(); k++) {
            j++;
            if (j == 4) {
                break;
            }
            Pane pane = FXMLLoader.load(getClass().getResource("OffreLine.fxml"));

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
            CoVoiturageSuggestion offre;
            offre = listOfSugg.get(k);
            userField.setText(String.valueOf(SUser.getUser(offre.getIdUser()).getUserName()));
            departField.setText(String.valueOf(offre.getDepart()));
            // departField.setMaxSize(3, 3);
            destinationField.setText(String.valueOf(offre.getDestination()));
            //PrettyTime p = new PrettyTime();
            //dateField.setText(String.valueOf(TimeAgo.toDuration(offre.getDate().getTime())));

            dateField.setText(String.valueOf(TimeAgo.toDuration(System.currentTimeMillis() - offre.getUpdated().getTime())));

            //etatField.setText(String.valueOf(offre.getOnetime()));
            JFXButton request = new JFXButton("Request");
            request = (JFXButton) pane.getChildren().get(14);
            pane.getChildren().set(14, request);
            request.setOnAction((event) -> {
                CoVoiturageRequests cod = new CoVoiturageRequests(offre.getId(), user.getId(), "a", new Timestamp(System.currentTimeMillis()));
                scor.addRequest(cod);
                Refresh(event);
            });

            JFXButton btnInfo = new JFXButton();
            btnInfo = (JFXButton) pane.getChildren().get(0);
            pane.getChildren().set(0, btnInfo);
            btnInfo.setOnAction((event) -> {
                CoVoiturage cov = new CoVoiturage();
                covInfo = cs.readCoVoiturage(offre.getId());
                redirectToInfo(event);
            });

            JFXButton btnUpdatee = new JFXButton();
            btnUpdatee = (JFXButton) pane.getChildren().get(12);
            pane.getChildren().set(12, btnUpdatee);
            btnUpdatee.setOnAction((event) -> {
                CoVoiturage cov = new CoVoiturage();
                cov = cs.readCoVoiturage(offre.getId());
                covInfo = cs.readCoVoiturage(offre.getId());
                redirectToUpdate(event);
            });

            JFXButton btnDelete = new JFXButton();
            btnDelete = (JFXButton) pane.getChildren().get(13);
            pane.getChildren().set(13, btnDelete);
            btnDelete.setOnAction((event) -> {
                try {
                    CoVoiturage cov = new CoVoiturage();
                    cov = cs.readCoVoiturage(offre.getId());
                    cs.deleteCoVoiturage(cov);
                    Refresh(event);
                } catch (SQLException ex) {
                    Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            
            if (offre.getIdUser() == user.getId()) {
                request.setVisible(false);
                btnDelete.setVisible(true);
                btnUpdatee.setVisible(true);

            } else {
                testPaneSug.getChildren().add(pane);
                btnDelete.setVisible(false);
                btnUpdatee.setVisible(false);
                if (scor.hasRequests(user)) {
                    request.setVisible(false);
                } else {
                    request.setVisible(true);
                }

            }

            

        }
        } else {
            Pane pane = FXMLLoader.load(getClass().getResource("OffreLine_1.fxml"));
            testPaneSug.getChildren().add(pane);
            Pane paneR = FXMLLoader.load(getClass().getResource("OffreLine_1.fxml"));
            testPane.getChildren().add(paneR);
        }

    }

    public void Refresh(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("OffresView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void redirectToCoVoiturage(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("CoVoiturageView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void redirectToInfo(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("InfoOffreView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    private void redirectToUpdate(ActionEvent event) {
        Parent page = null;
        try {
            page = FXMLLoader.load(getClass().getResource("UpdateOffreView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }




    @FXML
    private void refreshOffres(KeyEvent event) {
        
        ArrayList<CoVoiturage> listOfOffres = new ArrayList<>();
        try {
            listOfOffres.addAll(cs.GetCovoituragePerTypeLike("o",searchTextField.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        testPane.getChildren().clear();
        vboxAnchorPane.setMinHeight(54 * listOfOffres.size());
        vboxAnchorPane.setMaxHeight(54 * listOfOffres.size());
        vboxAnchorPane.setPrefHeight(54 * listOfOffres.size());


//       for (int x = 0; x < panee.getChildren().size() ; x++){
//           System.out.println(x+"  "+panee.getChildren().get(x).toString());
//       }
        if (listOfOffres.size() > 0)
        {
        for (int k = 0; k < listOfOffres.size(); k++) {
            Pane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("OffreLine.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

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
            CoVoiturage offre;
            offre = listOfOffres.get(k);
            userField.setText(String.valueOf(SUser.getUser(offre.getUser()).getUserName()));
            departField.setText(String.valueOf(offre.getDepart()));
            // departField.setMaxSize(3, 3);
            destinationField.setText(String.valueOf(offre.getDestination()));
            //PrettyTime p = new PrettyTime();
            //dateField.setText(String.valueOf(TimeAgo.toDuration(offre.getDate().getTime())));

            dateField.setText(String.valueOf(TimeAgo.toDuration(System.currentTimeMillis() - offre.getUpdated().getTime())));

            ServiceCoVoiturageRequests scor = new ServiceCoVoiturageRequests();
            
            
                
            JFXButton request = new JFXButton("Request");
            request = (JFXButton) pane.getChildren().get(14);
            pane.getChildren().set(14, request);
            request.setOnAction((eventt) -> {
                CoVoiturageRequests cod = new CoVoiturageRequests(offre.getId(), user.getId(), "a", new Timestamp(System.currentTimeMillis()));
                scor.addRequest(cod);
                Refresh(eventt);
            });
            


            JFXButton btnInfo = new JFXButton();
            btnInfo = (JFXButton) pane.getChildren().get(0);
            pane.getChildren().set(0, btnInfo);
            btnInfo.setOnAction((eventt) -> {
                CoVoiturage cov = new CoVoiturage();
                covInfo = cs.readCoVoiturage(offre.getId());
                redirectToInfo(eventt);
            });

            JFXButton btnUpdatee = new JFXButton();
            btnUpdatee = (JFXButton) pane.getChildren().get(12);
            pane.getChildren().set(12, btnUpdatee);
            btnUpdatee.setOnAction((eventt) -> {
                CoVoiturage cov = new CoVoiturage();
                cov = cs.readCoVoiturage(offre.getId());
                covInfo = cs.readCoVoiturage(offre.getId());
                redirectToUpdate(eventt);
            });

            JFXButton btnDelete = new JFXButton();
            btnDelete = (JFXButton) pane.getChildren().get(13);
            pane.getChildren().set(13, btnDelete);
            btnDelete.setOnAction((eventt) -> {
                try {
                    CoVoiturage cov = new CoVoiturage();
                    cov = cs.readCoVoiturage(offre.getId());
                    cs.deleteCoVoiturage(cov);
                    Refresh(eventt);
                } catch (SQLException ex) {
                    Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            

            if (offre.getUser() == user.getId()) {
                request.setVisible(false);
                btnDelete.setVisible(true);
                btnUpdatee.setVisible(true);

            } else {
                btnDelete.setVisible(false);
                btnUpdatee.setVisible(false);
                if (scor.hasRequests(user)) {
                    request.setVisible(false);
                } else {
                    request.setVisible(true);
                }

            }

            testPane.getChildren().add(pane);

        }
        }
    }
}
