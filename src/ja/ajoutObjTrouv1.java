/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Objet;
import entities.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ObjetCRUD;
import services.UserCRUD;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author bader
 */
public class ajoutObjTrouv1 implements Initializable {

    @FXML
    private JFXDrawer drawerTop;
    @FXML
    private Label pageLabel;
    @FXML
    private JFXButton redirectButtonObj;
    @FXML
    private Pane ObjetPerd;
    @FXML
    private Pane datePane;
    @FXML
    private DatePicker dateTextField;
    @FXML
    private Pane daysPane;
    @FXML
    private JFXDatePicker Dat;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField lieu;
    @FXML
    private JFXButton photo;
    @FXML
    private JFXComboBox<String> Typ;
    @FXML
    private JFXButton ajout;
    @FXML
    private Label lab_erreur;
    @FXML
    private Label warning;
    @FXML
    private JFXTextField tof;
    @FXML
    private ImageView Pho;

    File file1;
    ObservableList<String> maincateg = FXCollections.observableArrayList("Ordinateur", "Chargeur", "Telephone", "Papier", "CIN", "Autres");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Typ.setValue("Ordinateur");
        Typ.setItems(maincateg);
        warning.setText("Invalide");
        lab_erreur.setText("Invalide");
        lab_erreur.setVisible(false);
        warning.setVisible(false);
        // TODO
    }

    @FXML
    private void redirectToObjets(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ObjetView.fxml"));
        Scene scene = new Scene(root);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }

    @FXML
    private void charger(ActionEvent event) {
        Window owner = tof.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        file1 = file.getAbsoluteFile();
        //if (file1.getAbsolutePath().endsWith(".jpg") || file1.getAbsolutePath().endsWith(".jpeg") || file1.getAbsolutePath().endsWith(".png")) {
        System.out.println(file1.getAbsoluteFile().getUsableSpace());

        String imagePath = file.getName();
        tof.setText(imagePath);
    }

    @FXML
    private void ajout(ActionEvent event) {
        boolean saisie = true;
        Window owner = ajout.getScene().getWindow();
        Path path = Paths.get("C:/xampp/htdocs/pidev2/web");
        
        Path path1 = Paths.get(file1.getAbsolutePath());

        try {
            Files.copy(path1, path.resolve(path1.getFileName()), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            Logger.getLogger(ObjetViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            //System.out.println(Session.getThisTimestamp()+"-------------date--------");
            if (Session.getThisTimestamp().before(Date.valueOf(Dat.getValue())) ) {
                lab_erreur.setText("La date est Invalide");
                lab_erreur.setVisible(true);
                return;
            }
           if ((description.getText().length() < 4)) {
                warning.setText("Description Invalide");
                lab_erreur.setVisible(true);
                warning.setVisible(true);
                return;
            }
            if (lieu.getText().equals("") || tof.getText().equals("") || description.getText().isEmpty() || Dat.getValue().equals("") || lieu.getText().equals("")) {
                lab_erreur.setText("veuillez remplir tous les champs");
                lab_erreur.setVisible(true);
                warning.setVisible(true);
                return;
            }

            Objet o1 = new Objet(Session.getIdThisUser(), Typ.getValue(), description.getText(), Date.valueOf(Dat.getValue()), "Objet Trouvé", lieu.getText(), tof.getText(), false);
            ObjetCRUD o = new ObjetCRUD();
            o.ajouterObjet(o1);
        
    }
}
