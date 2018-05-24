/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import com.jfoenix.controls.JFXTextField;
import entities.Event;
import entities.Objet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import services.ObjetCRUD;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author bader
 */
public class affichageObjTrouv1Controller implements Initializable {

    @FXML
    private GridPane gridevent;
    @FXML
    private JFXTextField recherche_input;
    @FXML
    private AnchorPane recherche_pane;
    @FXML
    private AnchorPane container;
    public static String id_Objet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gridevent.getChildren().clear();
        ObjetCRUD oc = new ObjetCRUD();
        List<Objet> Objets = new ArrayList<>();
        Objets = oc.affichobjtrouv();
        int j = 0;
        int k = 0;
        int y = 0;
        ColumnConstraints colConstraint = new ColumnConstraints(120);
        colConstraint.setHalignment(HPos.LEFT);

        RowConstraints rowConstraints = new RowConstraints(130);
        rowConstraints.setValignment(VPos.CENTER);
        for (Objet o : Objets) {

            //  System.out.println("ok !!!!");
            Image i = new Image("http://127.0.0.1/pidev2/web/" + o.getPhoto(), true);
            ImageView imageView = new ImageView();
            imageView.setFitHeight(180);
            imageView.setFitWidth(220);
            imageView.setImage(i);

            //gridaffichage.add(imageView, j, 0);
            Label label = new Label(o.getType());
            label.setContentDisplay(ContentDisplay.TOP);
            label.setGraphic(imageView);
            Label labe = new Label(o.getId()+"");
            System.out.println(o.getId());

            label.setOnMouseClicked((event9) -> {
                System.out.println(o.getId());
                id_Objet=labe.getText();
                
                Node node = null;
                FXMLLoader loader = new FXMLLoader();
                Parent root;
                try {
                    AffichObjTrouvSingle.idob=Integer.parseInt(id_Objet);
                    root = FXMLLoader.load(getClass().getResource("AffichObjTrouvSingle.fxml"));
            Scene scene = new Scene(root);

            Stage app_stage = (Stage) ((Node) event9.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(affichageObjTrouv1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
                container.getChildren().clear();
                container.getChildren().add(node);

            });

            label.setOnMouseExited((event99) -> {

                label.setGraphic(imageView);
            });
            label.setOnMouseEntered((event33) -> {
                Pane show_info = new Pane();

                //  show_info.setOpacity(0.3);
                show_info.setPrefSize(220, 180);

                show_info.setStyle("-fx-background-color: #f7e4d9;");
                ColorAdjust adj = new ColorAdjust(0, -0.9, 0, 0);
                GaussianBlur blur = new GaussianBlur(10);
                adj.setInput(blur);
                // show_info.getParent().setEffect(adj);
                Label H = new Label(o.getType());
                Label label1 = new Label(o.getDate().toString());
                label1.relocate(20, 20);
                Label label2 = new Label(o.getDate().toString() + "");

                Hyperlink hyper = new Hyperlink(label1.getText());
                hyper.relocate(20, 70);
                Label label3 = new Label(o.getDescription());
                label3.relocate(20, 100);
                hyper.setOnMouseClicked((event22) -> {
                    /*   Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           try {
                               node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/ShowEvent.fxml"));
                           } catch (IOException ex) {
                               Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                           }
          //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
           container.getChildren().clear();
            container.getChildren().add(node);  */
                });
                show_info.getChildren().addAll(label1, hyper, label3);
                label.setGraphic(show_info);

            });
            if (j < 5) {

                gridevent.add(label, j, y);
                j++;
            } else {
                j = 0;
                y++;

                gridevent.add(label, j, y);
                j = 1;

            }

        }

        // TODO
    }

    @FXML
    private void recherche_onClick(ActionEvent event) {
        gridevent.getChildren().clear();
        ObjetCRUD oc = new ObjetCRUD();
        List<Objet> Objets = new ArrayList<>();
        Objets = oc.affichobjtrouv();
        int j = 0;
        int k = 0;
        int y = 0;
        ColumnConstraints colConstraint = new ColumnConstraints(120);
        colConstraint.setHalignment(HPos.LEFT);

        RowConstraints rowConstraints = new RowConstraints(130);
        rowConstraints.setValignment(VPos.CENTER);
        for (Objet o : Objets) {

            if (o.getType().toLowerCase().contains(recherche_input.getText().toLowerCase())
                    || o.getDescription().toLowerCase().contains(recherche_input.getText().toLowerCase())) {

                //  System.out.println("ok !!!!");
                Image i = new Image("http://127.0.0.1/pidev2/web/" + o.getPhoto());
                ImageView imageView = new ImageView();
                imageView.setFitHeight(180);
                imageView.setFitWidth(220);
                imageView.setImage(i);

                //gridaffichage.add(imageView, j, 0);
                Label label = new Label(o.getType());
                label.setContentDisplay(ContentDisplay.TOP);
                label.setGraphic(imageView);
                Label labe = new Label(o.getId() + "");

                label.setOnMouseClicked((event9) -> {
                    System.out.println(o.getId() + "clicked");
                    id_Objet = labe.getText();
                    Node node = null;
                    FXMLLoader loader = new FXMLLoader();

                    try {
                        node = (Parent) loader.load(getClass().getResourceAsStream("affichObjTrouv.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(affichageObjTrouv1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
                    container.getChildren().clear();
                    container.getChildren().add(node);

                });

                label.setOnMouseExited((event99) -> {

                    label.setGraphic(imageView);
                });
                label.setOnMouseEntered((event33) -> {
                    Pane show_info = new Pane();

                    //  show_info.setOpacity(0.3);
                    show_info.setPrefSize(220, 180);

                    show_info.setStyle("-fx-background-color: #f7e4d9;");
                    ColorAdjust adj = new ColorAdjust(0, -0.9, 0, 0);
                    GaussianBlur blur = new GaussianBlur(10);
                    adj.setInput(blur);
                    // show_info.getParent().setEffect(adj);
                    Label label1 = new Label(o.getType());
                    label1.relocate(20, 20);
                    Label label2 = new Label(o.getLieu() + "");

                    Hyperlink hyper = new Hyperlink(label2.getText());
                    hyper.relocate(20, 70);
                    Label label3 = new Label(o.getDescription());
                    label3.relocate(20, 100);
                    hyper.setOnMouseClicked((event22) -> {
                        /*   se.clickedPub(label.getText());
                          String domain="http://"+hyper.getText();
                             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
     WebDriver driver =new ChromeDriver();
      driver.get(domain);*/
                    });
                    show_info.getChildren().addAll(label1, hyper, label3);
                    label.setGraphic(show_info);

                });
                if (j < 5) {

                    gridevent.add(label, j, y);
                    j++;
                } else {
                    j = 0;
                    y++;

                    gridevent.add(label, j, y);
                    j = 1;

                }
            }

        }
    }

    @FXML
    private void key_pressed(KeyEvent event) {
        gridevent.getChildren().clear();
        ObjetCRUD se = new ObjetCRUD();
        List<Objet> Objets = new ArrayList<>();
        Objets = se.affichobjtrouv();
        int j = 0;
        int k = 0;
        int y = 0;
        ColumnConstraints colConstraint = new ColumnConstraints(120);
        colConstraint.setHalignment(HPos.LEFT);

        RowConstraints rowConstraints = new RowConstraints(130);
        rowConstraints.setValignment(VPos.CENTER);
        for (Objet o : Objets) {

            if (o.getType().toLowerCase().contains(recherche_input.getText())
                    || o.getDescription().toLowerCase().equals(recherche_input.getText())) {

                //  System.out.println("ok !!!!");
                Image i = new Image("http://127.0.0.1/pidev2/web/" + o.getPhoto());
                ImageView imageView = new ImageView();
                imageView.setFitHeight(180);
                imageView.setFitWidth(220);
                imageView.setImage(i);

                //gridaffichage.add(imageView, j, 0);
                Label label = new Label(o.getType());
                label.setContentDisplay(ContentDisplay.TOP);
                label.setGraphic(imageView);
                Label labe = new Label(o.getId() + "");

                label.setOnMouseClicked((event9) -> {
                    System.out.println(o.getId() + "clicked");
                    id_Objet = labe.getText();
                    Node node = null;
                    FXMLLoader loader = new FXMLLoader();

                    try {
                        node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/AfficheEvent.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(affichageObjTrouv1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //  container.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
                    container.getChildren().clear();
                    container.getChildren().add(node);

                });

                label.setOnMouseExited((event99) -> {

                    label.setGraphic(imageView);
                });
                label.setOnMouseEntered((event33) -> {
                    Pane show_info = new Pane();

                    //  show_info.setOpacity(0.3);
                    show_info.setPrefSize(220, 180);

                    show_info.setStyle("-fx-background-color: #f7e4d9;");
                    ColorAdjust adj = new ColorAdjust(0, -0.9, 0, 0);
                    GaussianBlur blur = new GaussianBlur(10);
                    adj.setInput(blur);
                    // show_info.getParent().setEffect(adj);
                    Label label1 = new Label(o.getType());
                    label1.relocate(20, 20);
                    Label label2 = new Label(o.getLieu() + "");

                    Hyperlink hyper = new Hyperlink(label2.getText());
                    hyper.relocate(20, 70);
                    Label label3 = new Label(o.getDescription());
                    label3.relocate(20, 100);
                    hyper.setOnMouseClicked((event22) -> {
                        /*   se.clickedPub(label.getText());
                          String domain="http://"+hyper.getText();
                             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
     WebDriver driver =new ChromeDriver();
      driver.get(domain);*/
                    });
                    show_info.getChildren().addAll(label1, hyper, label3);
                    label.setGraphic(show_info);

                });
                if (j < 5) {

                    gridevent.add(label, j, y);
                    j++;
                } else {
                    j = 0;
                    y++;

                    gridevent.add(label, j, y);
                    j = 1;

                }
            }

        }
    }

}
