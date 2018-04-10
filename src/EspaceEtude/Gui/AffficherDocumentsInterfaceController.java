/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Documents;
import EspaceEtude.entities.Matiere;
import EspaceEtude.services.EspaceEtudeService;
import entities.Session;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AffficherDocumentsInterfaceController implements Initializable {

    @FXML
    private Button addButton;
    private Desktop desktop = Desktop.getDesktop();
    public static Matiere idMatiere;

    public static Matiere getIdMatiere() {
        return idMatiere;
    }

    public static void setIdMatiere(Matiere idMatiere) {
        AffficherDocumentsInterfaceController.idMatiere = idMatiere;
    }
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane anchorPane;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScrollPane root = new ScrollPane();
        TilePane tile = new TilePane();
        root.setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);
        ArrayList<Documents> docList=new ArrayList<>();
        if(new EspaceEtudeService().getUserRole(Session.getIdThisUser()).equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {
            docList=new EspaceEtudeService().getAllDocumentsInValid(AffficherDocumentsInterfaceController.getIdMatiere().getId());

        } else {
            docList=new EspaceEtudeService().getAllDocumentsValid(AffficherDocumentsInterfaceController.getIdMatiere().getId());
        }
        for(Documents doc : docList){
            ImageView imageView;
                Label l=new Label();
                VBox p=new VBox();
                imageView = createImageView(new File("C:/xampp/htdocs/EspritEntreAide/web"+doc.getImage()),doc);
                l.setText(doc.getLibelle());
                imageView.setFitHeight(120);
                imageView.setFitWidth(120);
                p.getChildren().addAll(imageView,l);
                tile.getChildren().addAll(p);
        }
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        root.setFitToWidth(true);
        root.setContent(tile);
        pane.getChildren().add(root);
    }    

    @FXML
    private void startCrud(MouseEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/ajouterDocument.fxml"));
            // AfficherMatiereInterfaceController display= new AfficherMatiereInterfaceController();
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        //((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
    }
     
    
    private ImageView createImageView(final File imageFile,Documents doc) {
        // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
        // The last two arguments are: preserveRatio, and use smooth (slower)
        // resizing

       ImageView imageView = null;
        try {
            final ContextMenu cm = new ContextMenu();
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            final ImageView iv=imageView;
            
            if(new EspaceEtudeService().getUserRole(Session.getIdThisUser()).equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")){
             cm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println((((MenuItem)event.getTarget()).getText()));
            if((((MenuItem)event.getTarget()).getText()).equals("voir detail")){
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/DocumentDetails.fxml"));
                                DocumentDetailsController.setDoc(doc);
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                               
                                
                                stage.show();       
                            } catch (IOException ex) {
                                Logger.getLogger(AffficherDocumentsInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
            }else if((((MenuItem)event.getTarget()).getText()).equals("refuse fichier")){
                new EspaceEtudeService().supprimerDocument(doc.getId());
            }else{
                try {
                    new EspaceEtudeService().AccepterDocument(doc.getId());
                    anchorPane.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/AffficherDocumentsInterface.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AffficherDocumentsInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                            
            }
        });
              MenuItem menuItem1 = new MenuItem("voir detail");
            MenuItem menuItem2 = new MenuItem("refuse fichier");
            MenuItem menuItem3 = new MenuItem("accepter fichier");
            cm.getItems().addAll(menuItem1, menuItem2, menuItem3);
           iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    cm.show(iv, e.getScreenX(), e.getScreenY());
                } else {
                    openFile(new File("C:/xampp/htdocs/EspritEntreAide/web"+doc.getPath()));
                }
            }
        });  
              
            }
            else{
                if(doc.getUser()== Session.getIdThisUser()){
            cm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println((((MenuItem)event.getTarget()).getText()));
            if((((MenuItem)event.getTarget()).getText()).equals("voir detail")){
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/DocumentDetails.fxml"));
                                DocumentDetailsController.setDoc(doc);
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                               
                                
                                stage.show();       
                            } catch (IOException ex) {
                                Logger.getLogger(AffficherDocumentsInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
            }else if((((MenuItem)event.getTarget()).getText()).equals("supprimer fichier")){
                new EspaceEtudeService().supprimerDocument(doc.getId());
            }else{
               try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/DocumentUpdate.fxml"));
                                DocumentUpdateController.setDoc(doc);
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                anchorPane.getScene().getWindow().hide();
                             
                                stage.show();       
                            } catch (IOException ex) {
                                Logger.getLogger(AffficherDocumentsInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }     
            }
                            
            }
        });
              MenuItem menuItem1 = new MenuItem("voir detail");
            MenuItem menuItem2 = new MenuItem("supprimer fichier");
            MenuItem menuItem3 = new MenuItem("modifier fichier");
            cm.getItems().addAll(menuItem1, menuItem2, menuItem3);
           iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    cm.show(iv, e.getScreenX(), e.getScreenY());
                } else {
                    openFile(new File("C:/xampp/htdocs/EspritEntreAide/web"+doc.getPath()));
                }
            }
        });  
           
            }
            else{
            cm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println((((MenuItem)event.getTarget()).getText()));
            if((((MenuItem)event.getTarget()).getText()).equals("voir detail")){
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/DocumentDetails.fxml"));
                                DocumentDetailsController.setDoc(doc);
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                               
                                
                                stage.show();       
                            } catch (IOException ex) {
                                Logger.getLogger(AffficherDocumentsInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
            }
            }
            });
                MenuItem menuItem1 = new MenuItem("voir detail");
                cm.getItems().addAll(menuItem1);
                
                 iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    cm.show(iv, e.getScreenX(), e.getScreenY());
                } else {
                   openFile(new File("C:/xampp/htdocs/EspritEntreAide/web"+doc.getPath()));
                }
            }
        });  
            }
            }
  
        } catch (FileNotFoundException ex) {
        }
        return imageView;
    }
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                Test2.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }

}
