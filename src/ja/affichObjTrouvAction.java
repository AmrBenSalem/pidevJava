/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import services.ObjetCRUD;

/**
 *
 * @author bader
 */
public class affichObjTrouvAction implements Initializable {

    @FXML
    private ListView<Objet> listOT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Objet> data=FXCollections.observableArrayList();
        ObjetCRUD o = new ObjetCRUD();
        data.addAll(o.affichobjtrouv());
        
        listOT.getItems().addAll(data);
        listOT.setCellFactory(new Callback<ListView<Objet>,ListCell<Objet>>() {
            @Override
            public ListCell<Objet> call(ListView<Objet> param) {
                return  new ListCell<Objet>();
               
            }
        });
                }}