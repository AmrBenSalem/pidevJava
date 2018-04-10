/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceEtude.Gui;

import EspaceEtude.services.EspaceEtudeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Session;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import util.FileUploader;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AjouterDocumentController implements Initializable {

    @FXML
    private JFXTextField NomFichier;
    @FXML
    private JFXTextField Language;
    @FXML
    private Button openButton;
    @FXML
    private JFXButton ajouterDocumentButton;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label fileName;

    /**
     * Initializes the controller class.
     */
    final FileChooser fileChooser = new FileChooser();
    static File file;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         openButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    configureFileChooser(fileChooser);
                     file = fileChooser.showOpenDialog((Stage)ap.getScene().getWindow());
                    if(file!=null)
                     fileName.setText(file.getName());
                    setFile(file);
                  
                }
            });
    }    

    @FXML
    private void ajouterDocumentAction(MouseEvent event) throws IOException, Exception {
        File file = getFile();
        
                    if(file!=null){
                         // Files.copy(file.toPath(), Paths.get("C:\\xampp\\htdocs\\EspritEntreAide\\web\\Documents\\"+file.toPath().getFileName()), REPLACE_EXISTING);
                        FileUploader.upload(file);
                       //System.out.println(s);
                       EspaceEtudeService service =new EspaceEtudeService();
                         if(getFileExtension(file).equals("pdf")){
                             pdfToImage(file);
                              service.addDocument("/Documents/"+file.getName(), NomFichier.getText(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), getFileExtension(file),0.0, Language.getText(),Session.getIdThisUser(), AffficherDocumentsInterfaceController.getIdMatiere().getId(), "/Documents/"+file.getName(), 0);
                         }else if(getFileExtension(file).equals("jpg") || getFileExtension(file).equals("JPG") || getFileExtension(file).equals("jpeg"))
                                service.addDocument("/Documents/"+file.getName(), NomFichier.getText(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), getFileExtension(file), 0.0, Language.getText(),Session.getIdThisUser(), AffficherDocumentsInterfaceController.getIdMatiere().getId(), "/Documents/"+file.getName(), 0);
                         else
                                   service.addDocument("/Documents/"+file.getName(), NomFichier.getText(), new SimpleDateFormat("dd-MM-yyyy").format(new Date()), getFileExtension(file), 0.0, Language.getText(),Session.getIdThisUser(), AffficherDocumentsInterfaceController.getIdMatiere().getId(), "/Documents/Office03.png", 0);
                         
                          
                   
                    }
                     
        ((Node)(event.getSource())).getScene().getWindow().hide();          
                
    }
     private static void configureFileChooser(
        final FileChooser fileChooser) {      
            fileChooser.setTitle("View Pictures");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    }
     private void pdfToImage(File file) throws IOException {
              PDDocument document = PDDocument.load(file);
            List<PDPage> list = document.getDocumentCatalog().getAllPages();
            System.out.println("Total files to be converted -> "+ list.size());

            String fileName = file.getName().replace(".pdf", "");             
            int pageNumber = 1;
           
                BufferedImage image = list.get(1).convertToImage();
                System.out.println("dkhalt2");
                File outputfile = new File("C:\\xampp\\htdocs\\EspritEntreAide\\web\\Documents\\"+ fileName +"_"+ pageNumber +".png");
                System.out.println("Image Created -> "+ outputfile.getName());
                ImageIO.write(image, "png", outputfile);
                pageNumber++;
            
            document.close();
           
           
    }
     public static void setFile(File file){
         AjouterDocumentController.file=file;
     }
     public static File getFile(){
         return AjouterDocumentController.file;
     }
       private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
       
    
}
