/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.entities.Matiere;
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
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class MatiereAdminController implements Initializable {

    private static Section s;
    
    public static Section getSection() {
        return s;
    }

    public static void setSection(Section s) {
        MatiereAdminController.s = s;
    }
    @FXML
    private AnchorPane ap;
    @FXML
    private Label SectionNameLabel;
    @FXML
    private TableView<Matiere> MatiereTableView;
    @FXML
    private TableColumn<Matiere, String> libelle;
    @FXML
    private TableColumn<Matiere, String> coefficient;
    @FXML
    private TableColumn<Matiere, String> type;
    @FXML
    private JFXButton addMatiere;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SectionNameLabel.setText(s.getLibelle());
        TableColumn supprimer = new TableColumn("supprimer");
         
        Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>> cellFactory
                = //
                new Callback<TableColumn<Matiere, String>, TableCell<Matiere, String>>() {
            @Override
            public TableCell call(final TableColumn<Matiere, String> param) {
                final TableCell<Matiere, String> cell = new TableCell<Matiere, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Matiere s = getTableView().getItems().get(getIndex());
                                new EspaceEtudeService().removeMatiere(s);
                                ((Node)(event.getSource())).getScene().getWindow().hide();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/MatiereAdmin.fxml"));
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
        libelle.setCellValueFactory(new PropertyValueFactory<Matiere, String>("libelle"));
        coefficient.setCellValueFactory(new PropertyValueFactory<Matiere, String>("coefficient"));
        type.setCellValueFactory(new PropertyValueFactory<Matiere, String>("type"));
        supprimer.setCellFactory(cellFactory);
        supprimer.setMinWidth(120);
        MatiereTableView.getColumns().add(supprimer);
        MatiereTableView.setItems(new EspaceEtudeService().getAllMatiereBySection(s));
    }    

    @FXML
    private void ajoutMatiereInterface(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EspaceEtude/Gui/AjouterMatiere.fxml"));
         AjouterMatiereController.setS(s);
         Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        
        stage.show();
    }
    
}
