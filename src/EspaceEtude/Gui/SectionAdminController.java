/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Niveau;
import EspaceEtude.entities.Section;
import EspaceEtude.services.EspaceEtudeService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class SectionAdminController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private VBox vbox=new VBox(9);
    @FXML
    private JFXButton NewSection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
        for (Niveau n : Niveau.values()){
         VBox vb=new VBox();
         vb.setMinSize(645, 150);
         Pane p =new Pane();
         Label l=new Label();
         l.setText(n.name());
         TableView <Section> table=new TableView();
        
         TableColumn libelle = new TableColumn("libelle");
         libelle.setCellValueFactory(new PropertyValueFactory<Section, String>("libelle"));
         TableColumn niveau = new TableColumn("niveau");
         niveau.setCellValueFactory(new PropertyValueFactory<Section, String>("niveau"));
         TableColumn supprimer = new TableColumn("supprimer");
         table.getStylesheets().add("/EspaceEtude/gui/table.css");
        Callback<TableColumn<Section, String>, TableCell<Section, String>> cellFactory
                = //
                new Callback<TableColumn<Section, String>, TableCell<Section, String>>() {
            @Override
            public TableCell call(final TableColumn<Section, String> param) {
                final TableCell<Section, String> cell = new TableCell<Section, String>() {

                     Button btn = new Button("supprimer");
                    //btn.setStyle("-fx-background-color: transparent;");
                     
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Section s = getTableView().getItems().get(getIndex());
                                new EspaceEtudeService().removeSection(s);
                                ((Node)(event.getSource())).getScene().getWindow().hide();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/SectionAdmin.fxml"));
                                Parent root1;
                                try {
                                    root1 = (Parent) fxmlLoader.load();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root1));
                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(SectionAdminController.class.getName()).log(Level.SEVERE, null, ex);
                                }
        
        
        
        
        
                                
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        supprimer.setCellFactory(cellFactory);
        TableColumn m = new TableColumn("voir matiere");
         
        Callback<TableColumn<Section, String>, TableCell<Section, String>> cellFactory1
                = //
                new Callback<TableColumn<Section, String>, TableCell<Section, String>>() {
            @Override
            public TableCell call(final TableColumn<Section, String> param) {
                final TableCell<Section, String> cell = new TableCell<Section, String>() {

                    final Button btn1 = new Button("voir Matiere");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction(event -> {
                                Section s = getTableView().getItems().get(getIndex());
                                try{
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/MatiereAdmin.fxml"));
                                MatiereAdminController ma=new MatiereAdminController();
                                MatiereAdminController.setSection(s);
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                ((Node)(event.getSource())).getScene().getWindow().hide();
                                stage.show();
                                } catch (IOException ex) {
                                Logger.getLogger(MatiereAdminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            });
                            setGraphic(btn1);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        m.setCellFactory(cellFactory1);
        
        
        table.getColumns().addAll(libelle,niveau,supprimer,m);
       
          
        table.setItems(new EspaceEtudeService().getAllSectionByNiveau(n.name()));
        libelle.setMinWidth(350);
        niveau.setMinWidth(110);
         vb.getChildren().add(l);
         vb.getChildren().add(table);
         vbox.getChildren().add(vb);
       }
       
        
       
    }

    @FXML
    private void GetAddView(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/ajouterSection.fxml"));
            // AfficherMatiereInterfaceController display= new AfficherMatiereInterfaceController();
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
    }
    
    
}
