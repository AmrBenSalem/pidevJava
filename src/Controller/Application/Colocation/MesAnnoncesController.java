/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Application.Colocation;

import entities.Colocation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ColocationCRUD;
import util.Config;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class MesAnnoncesController implements Initializable {

    private ObservableList<Colocation> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Colocation> tableAnnonce;

    @FXML
    private TableColumn<Colocation, String> colNature;

    @FXML
    private TableColumn<Colocation, String> colTitre;

    @FXML
    private TableColumn<Colocation, String> colType;

    @FXML
    private TableColumn<Colocation, String> colVille;

    @FXML
    private TableColumn<Colocation, Double> colLoyer;
    private Colocation colocation = new Colocation();
    private ColocationCRUD colocationCRUD = new ColocationCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colNature.setCellValueFactory(new PropertyValueFactory<Colocation, String>("nature"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Colocation, String>("titre"));
        colType.setCellValueFactory(new PropertyValueFactory<Colocation, String>("type"));
        colVille.setCellValueFactory(new PropertyValueFactory<Colocation, String>("ville"));
        colLoyer.setCellValueFactory(new PropertyValueFactory<Colocation, Double>("loyer"));

        List<Colocation> listCol = colocationCRUD.afficherColocationByUser(Config.idUser);
        
        System.out.println("***"+listCol.size());
data = FXCollections.observableArrayList(listCol);
tableAnnonce.setItems(data);
        // TODO
    }

}
