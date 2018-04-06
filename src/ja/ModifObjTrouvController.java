/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.ObjetCRUD;

/**
 * FXML Controller class
 *
 * @author bader
 */
public class ModifObjTrouvController implements Initializable {

    @FXML
    private ChoiceBox<String> Typ;
    @FXML
    private DatePicker Dat;
    @FXML
    private TextArea Desc;
    @FXML
    private TextArea Lieux;
    @FXML
    private Button Ajout;
    @FXML
    private TextField tof;
    @FXML
    private Button Parcourir;
        
    private File file1;
    Objet o;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Typ.setItems(FXCollections.observableArrayList(
                new String("Ordinateur"),
                new String("Chargeur"),
                new String("Telephone"),
                new String("Papier"),
                new String("CIN"),
                new String("Autres")
        ));
        ObjetCRUD oc = new ObjetCRUD();
        
        try {
            o = oc.getByID(affichObjTrouvAction.num);
            tof.setText(o.getPhoto());
        Lieux.setText(o.getLieu());
        
        Dat.getEditor().setText(String.valueOf(o.getDate()));
        Desc.setText(o.getDescription());
        } catch (SQLException ex) {
            Logger.getLogger(ModifObjTrouvController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        ObjetCRUD oc=new ObjetCRUD();   
        //System.out.println(o);
        //Objet ob=new Objet(o.getUser()o.getNature() tof.getText(),o.getEnable());
        o.setType(Typ.getValue());
        o.setDate(Date.valueOf(Dat.getValue()));
        o.setDescription(Desc.getText());
        o.setLieu(Lieux.getText());
        o.setPhoto(tof.getText());
        //System.out.println("here");
        oc.modifierObjet(o,affichObjTrouvAction.num );
    }

    @FXML
    private void chooseImage(ActionEvent event) {
            Window owner = tof.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        file1 = file.getAbsoluteFile();
        //if (file1.getAbsolutePath().endsWith(".jpg") || file1.getAbsolutePath().endsWith(".jpeg") || file1.getAbsolutePath().endsWith(".png")) {
            System.out.println(file1.getAbsoluteFile().getUsableSpace());
            String imagePath = file.getName();
            tof.setText(imagePath);
    }

}
