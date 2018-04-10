/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import com.jfoenix.controls.JFXDrawer;
import com.sun.javafx.scene.control.skin.LabeledText;
import entities.Colocation;
import gui.DashboardCoVoiturageController;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
public class ListeOffreController implements Initializable {

    @FXML
    private ListView<Colocation> listView;
    private ColocationCRUD colocationCRUD = new ColocationCRUD();
    private ObservableList<Colocation> data = FXCollections.observableArrayList();
    @FXML
    private JFXDrawer drawerLeft;
    @FXML
    private Pane CoVoiturage;
    @FXML
    private JFXDrawer drawerTop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            drawerLeft.open();
            VBox box = FXMLLoader.load(getClass().getResource("/gui/LeftMenu.fxml"));
            drawerLeft.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(DashboardCoVoiturageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Colocation> listCol = colocationCRUD.afficherColocationOffre();

        data = FXCollections.observableArrayList(listCol);
        listView.setItems(data);
        listView.setCellFactory(new Callback<ListView<Colocation>, javafx.scene.control.ListCell<Colocation>>() {
            @Override
            public ListCell<Colocation> call(ListView<Colocation> listView) {
                return new ListViewCell();
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    DetailleOffreController.col = listView.getSelectionModel().getSelectedItem();

                    Parent page = null;
                    try {
                        page = FXMLLoader.load(getClass().getResource("detailleOffre.fxml"));
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
        });
    }

    public class ListViewCell extends ListCell<Colocation> {

        @Override
        public void updateItem(Colocation string, boolean empty) {
            super.updateItem(string, empty);
            if (string != null) {
                ListItemController data = new ListItemController();
                data.setInfo(string.getTitre(), String.valueOf(string.getLoyer()), string.getPhoto());
                setGraphic(data.getPanel());
            }
        }
    }

}
