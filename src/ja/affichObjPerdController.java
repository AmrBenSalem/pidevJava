/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ObjetCRUD;

/**
 *
 * @author bader
 */
public class affichObjPerdController implements Initializable {
    @FXML
    private ListView<Objet> listOP;
    static int num;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Objet> data = FXCollections.observableArrayList();
        ObjetCRUD o = new ObjetCRUD();
        data.addAll(o.affichobjperd());
        listOP.getItems().addAll(data);
        listOP.setCellFactory(new Callback<ListView<Objet>, ListCell<Objet>>() {
            @Override
            public ListCell<Objet> call(ListView<Objet> arg0) {
                return new ListCell<Objet>() {

                    @Override
                    protected void updateItem(Objet item, boolean bln) {
                        super.updateItem(item, bln);

                        if (item != null) {
                            //System.out.println(item);
                            Image image = new Image("http://127.0.0.1/pidev2/web/" + item.getPhoto(), true);
                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(70);
                            imv.setFitWidth(70);
                            Label t = new Label(String.valueOf(item.getId()));
                            t.setStyle("-fx-font-size: 30 arial;");
                            Button suppr = new Button("Supprimer");
                            Button modif = new Button("Modifier");
                            modif.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    num = item.getId();
                                    Parent root;
                                    try {
                                        root = FXMLLoader.load(getClass().getResource("ModifObjPerd.fxml"));
                                        Scene scene = new Scene(root);

                                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        app_stage.setScene(scene);

                                        app_stage.show();
                                    } 
                                    catch (IOException ex) {
                                        Logger.getLogger(affichObjTrouvAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            });
                            HBox vBox = new HBox(imv, t,
                                    new Label(String.valueOf(item.getUser())),
                                    new Label(item.getType()),
                                    new Label(item.getDescription()),
                                    new Label(String.valueOf(item.getDate())),
                                    new Label(item.getLieu()),
                                    modif, suppr);

                            vBox.setSpacing(15);

                            setGraphic(vBox);

                        } else {

                            setText(null);
                            setGraphic(null);

                        }
                    }

                };
            }

        });

    }
    
}
