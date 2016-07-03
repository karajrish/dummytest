package bu.edu.upark.utils;
/**
 * This class implements parsing the google api,print the data we want.
 * @author fangh
 * @since 1.0
 *
 */

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLEncoder;  
import net.sf.json.JSONObject;   
import org.apache.log4j.Logger;  

public final class GoogleMapUtils {
	public static final int ADDRESS = 1;  
    public static final int LATLNG = 2;  
    private final String GOOGLEAPIURL="http://maps.googleapis.com/maps/api/geocode/json?language=en&sensor=true";  
    private static Logger log = Logger.getLogger(GoogleMapUtils.class.getName());  
    private int type ;//1-->address 2-->latlng  
    public int getType() {  
        return type;  
    }  
  
    /**
     * Set the type, 1----> address, 2---->latlng
     * @param type
     */
    public void setType(int type) {  
        this.type = type;  
    }  
    
    private static GoogleMapUtils instance;  
      
    public static GoogleMapUtils getInstance() {  
        if(instance == null){  
            instance = new GoogleMapUtils();  
        }  
        return instance;  
    }    
 
    /**
     * This method implements to get the JSONObject from google api.
     * @param address the user's input
     * @return the JSONObject 
     * @throws Exception 
     */
    public JSONObject geocodeByAddress(String address) throws Exception{  
        if(address == null || address.equals("")){  
            return null;  
        }  
        log.info("geocode By Address : "+address);  
        log.info("Start geocode");  
        BufferedReader in= null;  
        HttpURLConnection httpConn = null;  
        try {  
            log.info("Start open url");  
            String urlPath = GOOGLEAPIURL+"&address="+URLEncoder.encode(address,"UTF-8");;  
            if(this.getType() == LATLNG){  
                urlPath = GOOGLEAPIURL+"&latlng="+address;  
            }  
            log.info("url : "+urlPath);  
            URL url = new URL(urlPath);  
            httpConn = (HttpURLConnection) url.openConnection();   
            log.info("End open url");  
            httpConn.setDoInput(true);     
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));     
            String line;  
            String result="";  
            while ((line = in.readLine()) != null) {     
                result += line;     
            }     
            in.close();   
            JSONObject jsonObject = JSONObject.fromObject( result );  
            log.info("End geocode");  
            return jsonObject;  
        } catch (MalformedURLException e) {  
            log.error(e);  
            throw e;  
        } catch (IOException e) {  
            log.error(e);  
            throw e;  
        } finally {  
            if (in != null) {  
                try {  
                    in.close();  
                } catch (IOException e) {  
                    log.error(e);  
                    throw e;  
                }  
            }  
            if (httpConn != null) {  
                httpConn.disconnect();  
            }  
        }  
    }  

}
