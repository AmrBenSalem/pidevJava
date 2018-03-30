package util;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParserUtils {
    public static JSONObject extractor(Reader in) throws IOException, ParseException{
      
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(in);
 
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
       
    }
}
