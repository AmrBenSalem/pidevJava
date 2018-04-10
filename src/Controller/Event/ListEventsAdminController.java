/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Event;

import entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author Liwa
 */
public class ListEventsAdminController implements Initializable {

    @FXML
    private TableView<Event> events;
    @FXML
    private TableColumn<Event, String> titre;
    @FXML
    private TableColumn<Event, String> description;
    @FXML
    private TableColumn<Event, Date> datedebut;
    @FXML
    private TableColumn<Event, Date> datefin;
    @FXML
    private TableColumn<Event, String> lieu;
    @FXML
    private TableColumn<Event, Integer> nbmax;
    @FXML
    private TableColumn<Event, String> categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ServiceEvent se = new ServiceEvent();
        
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(se.selectEvent());
        } catch (SQLException ex) {
            Logger.getLogger(ListEventsAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList observableList = FXCollections.observableList(arrayList);
        events.setItems(observableList);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<Event , String>("Description"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Event , Date>("DateDebut"));
        datefin.setCellValueFactory(new PropertyValueFactory<Event , Date>("DateFin"));
        lieu.setCellValueFactory(new PropertyValueFactory<Event , String>("Lieu"));
        nbmax.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nb_max"));
        categorie.setCellValueFactory(new PropertyValueFactory<Event , String>("categorie"));
        
        
        events.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
 
                    //Navigation nav = new Navigation();
                    Event rowData = row.getItem();
                   // System.out.println(rowData.getUser());
                    ApprouverAdminController.id = row.getItem().getId();
                    //System.out.println(row.getItem().getId());
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Event/ApprouverAdmin.fxml"));
            try {
            Pane pane = (Pane) loader.load();        
            stage.setTitle("Approuver");
            Scene scene = new Scene(pane);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListEventsAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }            
        
        

                }
            });
            return row;

        });
        
    }    
    
}
