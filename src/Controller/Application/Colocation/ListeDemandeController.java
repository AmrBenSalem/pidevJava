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
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import services.ColocationCRUD;
import util.Config;

/**
 * FXML Controller class
 *
 * @author douha
 */
public class ListeDemandeController implements Initializable {
    @FXML
    private ListView<Colocation> listView;
    private ColocationCRUD colocationCRUD= new ColocationCRUD();
 private ObservableList<Colocation> data = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          List<Colocation> listCol = colocationCRUD.afficherColocationDemande();

        data = FXCollections.observableArrayList(listCol);
         listView.setItems(data);
        listView.setCellFactory(new Callback<ListView<Colocation>, javafx.scene.control.ListCell<Colocation>>()
        {
            @Override
            public ListCell<Colocation> call(ListView<Colocation> listView)
            {
                return new ListViewCell();
            }
        });
    }
    
    
    
    
    public class ListViewCell extends ListCell<Colocation>
{
    @Override
    public void updateItem(Colocation string, boolean empty)
    {
        super.updateItem(string,empty);
        if(string != null)
        {
            ListItemController data = new ListItemController();
            data.setInfo(string.getTitre(),String.valueOf(string.getLoyer()),string.getPhoto());
            setGraphic(data.getPanel());
        }
    }
}
    
}
