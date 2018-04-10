/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import com.jfoenix.controls.JFXDrawer;
import entities.Colocation;
import gui.DashboardCoVoiturageController;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ColocationCRUD;
import util.Config;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class MesDemandesController implements Initializable {

    private ObservableList<Colocation> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Colocation> tableAnnonce;

    @FXML
    private TableColumn<Colocation, String> colNature;

    @FXML
    private TableColumn<Colocation, String> colTitre;
 @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private TableColumn<Colocation, String> colType;

    @FXML
    private TableColumn<Colocation, String> colVille;

    @FXML
    private TableColumn<Colocation, Double> colLoyer;
    private Colocation colocation = new Colocation();
    private ColocationCRUD colocationCRUD = new ColocationCRUD();
    @FXML
    private TableColumn<Colocation, Colocation> colDelete;
    @FXML
    private TableColumn<Colocation, Colocation> colEdit;

    public static Colocation col = new Colocation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 try {
              drawerLeft.open();
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colNature.setCellValueFactory(new PropertyValueFactory<Colocation, String>("nature"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Colocation, String>("titre"));
        colType.setCellValueFactory(new PropertyValueFactory<Colocation, String>("type"));
        colVille.setCellValueFactory(new PropertyValueFactory<Colocation, String>("ville"));
        colLoyer.setCellValueFactory(new PropertyValueFactory<Colocation, Double>("loyer"));

        List<Colocation> listCol = colocationCRUD.afficherDemandeByUser(Config.idUser);

        data = FXCollections.observableArrayList(listCol);
        tableAnnonce.setItems(data);
        // TODO

        colDelete.setCellFactory(new Callback<TableColumn<Colocation, Colocation>, TableCell<Colocation, Colocation>>() {

            @Override
            public TableCell<Colocation, Colocation> call(TableColumn<Colocation, Colocation> param) {

                final TableCell<Colocation, Colocation> cell = new TableCell<Colocation, Colocation>() {

                    final Button btn = new Button("Supprimer");

                    public void updateItem(Colocation item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText("Look, a Confirmation Dialog");
                                alert.setContentText("Are you ok with this?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    Colocation colocation = getTableView().getItems().get(getIndex());

                                    colocationCRUD.supprimerColocation(colocation.getId());
                                    //pour charger la liste
                                    List<Colocation> listCol = colocationCRUD.afficherDemandeByUser(Config.idUser);

                                    data = FXCollections.observableArrayList(listCol);
                                    tableAnnonce.setItems(data);
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        colEdit.setCellFactory(new Callback<TableColumn<Colocation, Colocation>, TableCell<Colocation, Colocation>>() {

            @Override
            public TableCell<Colocation, Colocation> call(TableColumn<Colocation, Colocation> param) {

                final TableCell<Colocation, Colocation> cell = new TableCell<Colocation, Colocation>() {

                    final Button btn = new Button("Editer");

                    public void updateItem(Colocation item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                try {
                                    col = getTableView().getItems().get(getIndex());
                                    Parent page = FXMLLoader.load(getClass().getResource("EditCol.fxml"));
                                    Scene scene = new Scene(page);
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    stage.hide();
                                    stage.setScene(scene);
                                    stage.show();

                                } catch (IOException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
    }

}
