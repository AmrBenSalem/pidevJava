/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import static Controller.Event.ListEventController.id_event;
import com.jfoenix.controls.JFXTextField;
import entities.Event;
import entities.Session;
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
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class UserEventsController implements Initializable {

    @FXML
    private JFXTextField recherche_input;
    @FXML
    private GridPane gridevent;
    @FXML
    private AnchorPane container;
    @FXML
    private AnchorPane recherche_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gridevent.getChildren().clear();
            ServiceEvent se = new ServiceEvent();
            List<Event> events = new ArrayList<>();
        try {
            events= se.listEventsUser(Session.getIdThisUser());
        } catch (SQLException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            int j = 0;
            int k = 0;
            int y=0;
            ColumnConstraints colConstraint = new ColumnConstraints(120);
            colConstraint.setHalignment(HPos.LEFT);

            RowConstraints rowConstraints = new RowConstraints(130);
            rowConstraints.setValignment(VPos.CENTER);
            for (Event e : events) {
          
                
             
                  //  System.out.println("ok !!!!");

                    Image i = new Image("file:" + e.getPhoto());
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(180);
                    imageView.setFitWidth(220);
                    imageView.setImage(i);

                    //gridaffichage.add(imageView, j, 0);
                    Label label = new Label(e.getTitre());
                    label.setContentDisplay(ContentDisplay.TOP);
                     label.setGraphic(imageView);
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
        Label label1 =new Label(e.getTitre());
        label1.relocate(20, 20);
         Label label2 =new Label(e.getNb_max()+"");
                        
                      Hyperlink hyper =new Hyperlink(label2.getText());
                       hyper.relocate(20, 70);
                         Label label3 =new Label(e.getDescription());
                         label3.relocate(20, 100);
                         hyper.setOnMouseClicked((event22) -> {
                          /*   se.clickedPub(label.getText());
                          String domain="http://"+hyper.getText();
                             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
     WebDriver driver =new ChromeDriver();
      driver.get(domain);*/
                         });
                    show_info.getChildren().addAll(label1,hyper,label3);
       label.setGraphic(show_info);
         
                  });
                    if (j < 5 ) {
                        
                        gridevent.add(label, j, y);
                        j++;
                    } else {
                        j=0;
                        y++;
                        
                        gridevent.add(label, j, y);
                        j=1;
                       
                    }

                
            }
    }    

    @FXML
    private void addEvent(ActionEvent event) {
    }

    @FXML
    private void key_pressed(KeyEvent event) {
        gridevent.getChildren().clear();
            ServiceEvent se = new ServiceEvent();
            List<Event> events = new ArrayList<>();
        try {
            events= se.listEventsUser(Session.getIdThisUser());
        } catch (SQLException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            int j = 0;
            int k = 0;
            int y=0;
            ColumnConstraints colConstraint = new ColumnConstraints(120);
            colConstraint.setHalignment(HPos.LEFT);

            RowConstraints rowConstraints = new RowConstraints(130);
            rowConstraints.setValignment(VPos.CENTER);
            for (Event e : events) {
            
                if (e.getTitre().toLowerCase().contains(recherche_input.getText())
                        || e.getDescription().toLowerCase().equals(recherche_input.getText())) {
                
             
                  //  System.out.println("ok !!!!");

                    Image i = new Image("file:" + e.getPhoto());
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(180);
                    imageView.setFitWidth(220);
                    imageView.setImage(i);

                    //gridaffichage.add(imageView, j, 0);
                    Label label = new Label(e.getTitre());
                    label.setContentDisplay(ContentDisplay.TOP);
                     label.setGraphic(imageView);
                     Label labe = new Label(e.getId()+"");
                     
                                   label.setOnMouseClicked((event9) -> {
                         System.out.println(e.getId()+"clicked");
                         id_event=labe.getText();
                         Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           
                        try {
                            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/AfficheEvent.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
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
        Label label1 =new Label(e.getTitre());
        label1.relocate(20, 20);
         Label label2 =new Label(e.getNb_max()+"");
                        
                      Hyperlink hyper =new Hyperlink(label2.getText());
                       hyper.relocate(20, 70);
                         Label label3 =new Label(e.getDescription());
                         label3.relocate(20, 100);
                         hyper.setOnMouseClicked((event22) -> {
                          /*   se.clickedPub(label.getText());
                          String domain="http://"+hyper.getText();
                             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
     WebDriver driver =new ChromeDriver();
      driver.get(domain);*/
                         });
                    show_info.getChildren().addAll(label1,hyper,label3);
       label.setGraphic(show_info);
         
                  });
                    if (j < 5 ) {
                        
                        gridevent.add(label, j, y);
                        j++;
                    } else {
                        j=0;
                        y++;
                        
                        gridevent.add(label, j, y);
                        j=1;
                       
                    }
                }
                
            }
    }

    @FXML
    private void recherche_onClick(ActionEvent event) {
        gridevent.getChildren().clear();
        ServiceEvent se = new ServiceEvent();
        List<Event> events = new ArrayList<>();
        try {
            events = se.listEventsUser(Session.getIdThisUser());
        } catch (SQLException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int j = 0;
        int k = 0;
        int y = 0;
        ColumnConstraints colConstraint = new ColumnConstraints(120);
        colConstraint.setHalignment(HPos.LEFT);

        RowConstraints rowConstraints = new RowConstraints(130);
        rowConstraints.setValignment(VPos.CENTER);
        for (Event e : events) {

            if (e.getTitre().toLowerCase().contains(recherche_input.getText().toLowerCase())
                    || e.getDescription().toLowerCase().contains(recherche_input.getText().toLowerCase())) {
                
             
                  //  System.out.println("ok !!!!");

                    Image i = new Image("file:" + e.getPhoto());
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(180);
                    imageView.setFitWidth(220);
                    imageView.setImage(i);

                    //gridaffichage.add(imageView, j, 0);
                    Label label = new Label(e.getTitre());
                    label.setContentDisplay(ContentDisplay.TOP);
                     label.setGraphic(imageView);
                      Label labe = new Label(e.getId()+"");
                     
                                                 label.setOnMouseClicked((event9) -> {
                         System.out.println(e.getId()+"clicked");
                         id_event=labe.getText();
                         Node node = null;
            FXMLLoader loader = new FXMLLoader();
                           
                        try {
                            node = (Parent) loader.load(getClass().getResourceAsStream("/Views/Event/AfficheEvent.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
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
        Label label1 =new Label(e.getTitre());
        label1.relocate(20, 20);
         Label label2 =new Label(e.getNb_max()+"");
                        
                      Hyperlink hyper =new Hyperlink(label2.getText());
                       hyper.relocate(20, 70);
                         Label label3 =new Label(e.getDescription());
                         label3.relocate(20, 100);
                         hyper.setOnMouseClicked((event22) -> {
                          /*   se.clickedPub(label.getText());
                          String domain="http://"+hyper.getText();
                             System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
     WebDriver driver =new ChromeDriver();
      driver.get(domain);*/
                         });
                    show_info.getChildren().addAll(label1,hyper,label3);
       label.setGraphic(show_info);
         
                  });
                    if (j < 5 ) {
                        
                        gridevent.add(label, j, y);
                        j++;
                    } else {
                        j=0;
                        y++;
                        
                        gridevent.add(label, j, y);
                        j=1;
                       
                    }
                }
                
            }
    }
    
}
