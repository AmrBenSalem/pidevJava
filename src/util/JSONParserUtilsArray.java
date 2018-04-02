/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Justpro
 */
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParserUtilsArray {
    public static JSONArray extractor(Reader in) throws IOException, ParseException{
      
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(in);
 
            JSONArray jsonObject = (JSONArray) obj;
            return jsonObject;
       
    }
}
