package util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import entities.Adresse;

public class GooglePlacesAPI {
    
    private static final String URLString = "https://maps.googleapis.com/maps/api/place/#&key=";
    private static final String KEY = "AIzaSyA8w507O9U90-M_IIeytsa4weIBO_yAjhI"; //"AIzaSyBwYcSUSj2uRzDIMclaDjzGE3eoHQur64Y"; 
    
    public static List<Adresse> autoCompleteAddress(String input){
        try {
            //&types=(regions)
            input = input.replace(" ", "+");
            String preparedURL = URLString.replace("#", "autocomplete/json?input="+input+"&language=fr")+KEY;
            String content = HTTPConnector.connect(preparedURL);
            if(content != null){
                JSONObject jsonObject = null;
                try {
                    jsonObject = JSONParserUtils.extractor(new StringReader(content.toString()));
                } catch (ParseException ex) {
                    Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                JSONArray addressArray = (JSONArray) jsonObject.get("predictions");
                List<Adresse> addresses = new ArrayList<>();
                for(int i=0; i<addressArray.size(); i++){
                    Adresse address = new Adresse();
                    
                    JSONObject jsonAddress = (JSONObject) addressArray.get(i);
                    address.setPlaceId((String)jsonAddress.get("place_id"));
                    
                    JSONArray addressTerms = (JSONArray)jsonAddress.get("terms");
                    address.setCity((String)((JSONObject)addressTerms.get(0)).get("value"));
                    address.setCountry((String)((JSONObject)addressTerms.get(addressTerms.size()-1)).get("value"));
                    try {
                        address = getPlaceDetails(address);
                    } catch (ParseException ex) {
                        Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    addresses.add(address);
                    
                }
                return addresses;
            }
            return null;
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Adresse getAdress(String input){
        try {
            input = input.replace(" ", "+");
            String preparedURL = URLString.replace("#", "autocomplete/json?input="+input+"&language=fr")+KEY;
            String content = HTTPConnector.connect(preparedURL);
            if(content != null){
                JSONObject jsonObject = null;
                try {
                    jsonObject = JSONParserUtils.extractor(new StringReader(content.toString()));
                } catch (ParseException ex) {
                    Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                JSONArray addressArray = (JSONArray) jsonObject.get("predictions");
                List<Adresse> addresses = new ArrayList<>();
               
                    Adresse address = new Adresse();
                    
                    JSONObject jsonAddress = (JSONObject) addressArray.get(0);
                    address.setPlaceId((String)jsonAddress.get("place_id"));
                    
                    JSONArray addressTerms = (JSONArray)jsonAddress.get("terms");
                    address.setCity((String)((JSONObject)addressTerms.get(0)).get("value"));
                    address.setCountry((String)((JSONObject)addressTerms.get(addressTerms.size()-1)).get("value"));
                    try {
                        address = getPlaceDetails(address);
                    } catch (ParseException ex) {
                        Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   return address;

            }
            return null;
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Adresse getPlaceDetails(Adresse address) throws IOException, ParseException{
        String preparedURL = URLString.replace("#", "details/json?placeid="+address.getPlaceId()+"&language=fr")+KEY;
        String content = HTTPConnector.connect(preparedURL);
        if(content!=null){
            JSONObject jsonObject = JSONParserUtils.extractor(new StringReader(content.toString()));
            JSONObject jsonAddress = (JSONObject) jsonObject.get("result");
            JSONObject jsonLocation = (JSONObject) ((JSONObject)jsonAddress.get("geometry")).get("location");
            address.setLatitude((double)jsonLocation.get("lat"));
            address.setLongitude((double)jsonLocation.get("lng"));
            return address;
        }
        return null;
    }
    
    
    public static Image getPhoto(String photoRef, int maxWidth){
        try {
            String preparedURL = URLString.replace("#", "photo?maxwidth="+maxWidth+"&photoreference="+photoRef)+KEY;
            URL url = new URL(preparedURL);
            BufferedImage bufferedImage = ImageIO.read(url);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return image;
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
