/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.covoiturage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import entities.CoVoiturage;
import gui.DashboardCoVoiturageController;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    @FXML
    private Button buttonAddOffre;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            cs = new ServiceCoVoiturage();
        } catch (SQLException ex) {
            Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        stage.setResizable(true);
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
       for (int x = 0; x < panee.getChildren().size() ; x++){
           System.out.println(x+"  "+panee.getChildren().get(x).toString());
       }
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
            pane.getChildren().set(12, dateField);

            Label etatField = new Label();
            etatField.setLayoutX(pane.getChildren().get(9).getLayoutX());
            etatField.setLayoutY(pane.getChildren().get(9).getLayoutY());
            pane.getChildren().set(9, etatField);

            CoVoiturage offre;
            offre = listOfOffres.get(k);
            userField.setText(String.valueOf(offre.getUser()));

            departField.setText(String.valueOf(offre.getDepart()));
            // departField.setMaxSize(3, 3);
            destinationField.setText(String.valueOf(offre.getDestination()));
            //PrettyTime p = new PrettyTime();
            dateField.setText(String.valueOf(offre.getDate()));
            etatField.setText(String.valueOf(offre.getOnetime()));

            JFXButton btn = new JFXButton();
            btn = (JFXButton) pane.getChildren().get(13);
            pane.getChildren().set(13, btn);
            btn.setOnAction((event) -> {
                try {
                    CoVoiturage cov = new CoVoiturage();
                    System.out.println("bbbbbbbb" + offre.getId());
                    cov = cs.readCoVoiturage(offre.getId());
                    cs.deleteCoVoiturage(cov);
                    Refresh(event);
                } catch (SQLException ex) {
                    Logger.getLogger(OffresViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            testPane.getChildren().add(pane);

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
        stage.setResizable(true);
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
        stage.setResizable(true);
        stage.show();
    }
}
