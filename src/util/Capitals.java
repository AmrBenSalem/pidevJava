/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import entities.Adresse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Justpro
 */
public class Capitals {

    public Adresse getCapital() {
        File database = new File("C:\\Users\\Justpro\\Documents\\NetBeansProjects\\pidevv\\src\\util\\GeoLite2-City.mmdb");

        try {
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            CityResponse response = dbReader.city(InetAddress.getByName(getIpAdress()));
            String countryName = response.getCountry().getName();
            String cityName = response.getCity().getName();
            String postal = response.getPostal().getCode();
            String state = response.getLeastSpecificSubdivision().getName();
            String preparedURL = "https://restcountries.eu/rest/v1/name/" + countryName;
            String content = HTTPConnector.connect(preparedURL);
            if (content != null) {
                JSONArray jsonObject = JSONParserUtilsArray.extractor(new StringReader(content.toString()));
                //System.out.println(jsonObject);
                JSONObject addressArray = (JSONObject) jsonObject.get(0);
                //System.out.println((String)addressArray.get("name"));
                GooglePlacesAPI gpi = new GooglePlacesAPI();
                return  gpi.getAdress((String) addressArray.get("capital"));
            }
        } catch (IOException ex) {
            //Logger.getLogger(MapViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            //Logger.getLogger(MapViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Capitals.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Logger.getLogger(MapViewController.class.getName()).log(Level.SEVERE, null, ex);

        return null;
    }
    
    public String getIpAdress(){
            try {
                URL whatismyip = new URL("http://checkip.amazonaws.com");
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(
                            whatismyip.openStream()));
                } catch (IOException ex) {
                    // Logger.getLogger(LookUpProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String ip = in.readLine();
                return ip;
            } catch (MalformedURLException ex) {
            Logger.getLogger(Capitals.class.getName()).log(Level.SEVERE, null, ex);
               // Logger.getLogger(LookUpProgram.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(Capitals.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
