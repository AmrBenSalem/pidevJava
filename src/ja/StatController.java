/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja;

import entities.Objet;
import entities.Session;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.ObjetCRUD;

/**
 * FXML Controller class
 *
 * @author Abderhim
 */
public class StatController implements Initializable {

    /**
     * Initializes the controller class.
     */
        static int localid=1;
   @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private NumberAxis moyenne_note;

    @FXML
    private CategoryAxis adresse;

    @FXML
    private LineChart<String,Double> LineChart ;

           

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            
            
        // TODO
        
        LineChart.setTitle("Nombre d'Objet");
adresse.setLabel("Adresse Local");
adresse.setMaxHeight(5);

moyenne_note.setLabel("Note/5");
XYChart.Series dataSeries1 = new XYChart.Series();
//XYChart.Series dataSeries2 = new XYChart.Series();

                     ArrayList<Objet> ob= new ArrayList<Objet>();

        ObservableList<Objet> list= FXCollections.observableArrayList();



        
       ObjetCRUD oc = new ObjetCRUD();
       Objet o = new Objet();
           
          
             
            
                  
          
            try {
                list=(ObservableList<Objet>)oc.getByIdUserr(localid);
            } catch (SQLException ex) {
                Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        System.out.println( list.size());
           LineChart.getData().clear();
           for (Objet a: list) {
             
             //dataSeries1.getData().add(new XYChart.Data( a.getLieu(),a.getId()));
             dataSeries1.getData().add(new XYChart.Data( "sejil", 50));

           //  dataSeries2.getData().add(new XYChart.Data( a.getLieu()+" "+a.getDescription()+" "+a.getNature(),a.getId()));
LineChart.getData().addAll(dataSeries1);

               
           }   
           
           
          
           }

       }
       
       
        
        
        
        
        
        
        
      
    
