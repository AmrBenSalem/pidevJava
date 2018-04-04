/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import services.ObjetCRUD;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 *
 * @author Jasser
 */
public class AdminObjetNonApprouvésController implements Initializable {

    @FXML
    private TableView<Objet> table;
    @FXML
    private TableColumn<Objet, String> User;
    @FXML
    private TableColumn<Objet, String> Photo;
    @FXML
    private TableColumn<Objet, Date> Date;
    @FXML
    private TableColumn<Objet, String> Type;
    @FXML
    private TableColumn<Objet, String> Description;
    @FXML
    private TableColumn<Objet, String> Lieu;
    @FXML
    private TableColumn<Objet, String> Nature;
    @FXML
    private Button button;
 

    @FXML
    protected void handleEditChoiceAction() {
        Window owner = table.getScene().getWindow();
        ObjetCRUD o = new ObjetCRUD();

        ArrayList arrayList = new ArrayList();
        arrayList.addAll(o.afficherObjet());
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        System.out.println(observableList);
        table.setItems(observableList);
        User.setCellValueFactory(new PropertyValueFactory<>("User"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        Nature.setCellValueFactory(new PropertyValueFactory<>("Nature"));
       /* SiteWeb.setCellValueFactory(new PropertyValueFactory<>("SiteWeb"));
        PageFacebook.setCellValueFactory(new PropertyValueFactory<>("PageFacebook"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        HeureOuverture.setCellValueFactory(new PropertyValueFactory<>("HeureOuverture"));
        HeureFermuture.setCellValueFactory(new PropertyValueFactory<>("HeureFermuture"));
        Image.setCellValueFactory(new PropertyValueFactory<>("Image"));
        Enabled.setCellValueFactory(new PropertyValueFactory<>("Enabled"));*/
        //System.out.println(Image.getCellObservableValue(0).getValue());
        /**table.setRowFactory(tv -> {
            TableRow<Objet> row = new TableRow<>();
            row.setOnMouseClicked(Objet -> {
                if (Objet.getClickCount() == 1 && (!row.isEmpty())) {
                    
                    Objet rowData = row.getItem();
                    ShowPropAdminController.id = rowData.getId();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(nav.Afficher_Prop_Admin));
        try {
            Pane pane = (Pane) loader.load();        
            stage.setTitle("Liste des Propriétés");
            Scene scene = new Scene(pane);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(jController.class.getName()).log(Level.SEVERE, null, ex);
        }

                }
            });
            return row;

        });**/

    }

    @FXML
    protected void handletableAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
    }
    

}