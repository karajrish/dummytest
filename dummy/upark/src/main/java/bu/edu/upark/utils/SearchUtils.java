package bu.edu.upark.utils;

/**
 *
 * This class implements the function to parse the JSON object from google map API and output the longitude
 * and latitude that necessary for searching.
 * 
 *  @author Fanghui Zhang
 *  @version 1.0
 *
 *                                 _oo0oo_
 *                                o8888888o
 *                                88" . "88
 *                                (/ -_- /)
 *                                0\  =  /0
 *                               __/'---'\__
 *                             .' \\/    |//'.
 *                            / \\/// :  |||/ \
 *                           / _//// -:- ////- \
 *                          /   / \\\ -  /// \  \
 *                         |  \_/ ''\---/''  /_/ |
 *                         \   '-\__ '_' __/-'   /
 *                      ____'. .'  /--.--\  '. .'____
 *                   .""  '<  `.___\_</>_/___.'  >'  "".
 * 		    /  / :  '- \'.;'\ _ /';.'/ -'  : \  \
 * 	            \  \  `_.   \_ __\/__ _/    ._'  /  /
 * 	        =====`-.____ .___  \____/   ___. ____.-'=====
 *                                 '=---='
 *
 *         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *                     佛祖保佑              永无Bug
 *
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import net.sf.json.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class SearchUtils {

	/**
 	* Get the ZIPCODE from the JSONObject. If the JSONObject is not the given format, the method would 
 	* probably throw a NullPointerException.
 	* @param  data the JSONObject
 	* @return the zipcode in the string format, null if the parsing fails.
 	*/
	
	static final double parameterForLatitude = 0.0144985946;
	static final double parameterForLongtitue = 0.01256690466 * 1.3 ;
	
	public static String getZipcode(JSONObject data) {
		try {
			JSONArray results = data.getJSONArray("results");
			JSONObject address = results.getJSONObject(0);
			JSONArray address_components = address.getJSONArray("address_components");
			for(int i = 0; i < address_components.size(); i++) {
				JSONObject postal_code = address_components.getJSONObject(i);
				JSONArray types = postal_code.getJSONArray("types");
				if(types.getString(0).equals("postal_code")) {
					return (String) postal_code.get("long_name");
				}
			}
			return "0";			
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Get the formated address from the JSONObject. If the JSONObject is not the given format, the method would 
	 * probably throw a NullPointerException.
	 * @param data the JSONObject
	 * @return the formatted address in the string format, null if the parsing fails.
	 */
	public static String getFormattedAddress(JSONObject data) {
		try {
			JSONArray results = data.getJSONArray("results");
			
			JSONObject address = results.getJSONObject(0);
			String formatted_address = (String) address.get("formatted_address");
			return formatted_address;			
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * Get the southwest bound of the given address.  If the JSONObject is not the given format, the method would 
	 * probably throw a NullPointerException.
	 * @param data the JSONObject
	 * @return the Double array of southwest bound. null if the parsing fails
	 */
	public static double[] getSouthWest(JSONObject data) {
		try {
			double[] southwestresult = new double[2];
			JSONObject viewport = getViewport(data);
			JSONObject southwest = (JSONObject) viewport.get("southwest");
			Double lat = (double)southwest.get("lat");
			Double lng = (double) southwest.get("lng");
			southwestresult[0] = lat;
			southwestresult[1] = lng;
			return southwestresult;
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;		
	}
		
	/**
	 * Get the northeast bound of the given address.  If the JSONObject is not the given format, the method would 
	 * probably throw a NullPointerException.
	 * @param data the JSONObject
	 * @return the Double array of northeast bound. null if the parsing fails
	 */
	public static double[] getNorthEast(JSONObject data) {
		try {
			double[] northeastresult = new double[2];
			JSONObject viewport = getViewport(data);
			JSONObject northeast = (JSONObject) viewport.get("northeast");
			Double lat = (double) northeast.get("lat");
			Double lng = (double) northeast.get("lng");
			northeastresult[0] = lat;
			northeastresult[1] = lng;
			return northeastresult;	
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;		
	}
	
	
	/**
	 * Private method helps to generate the viewport of the given JSONObject
	 * @param data the JSONObject
	 * @return the viewport for parsing the bound, null if the parsing fails.
	 */
	private static JSONObject getViewport(JSONObject data) {
		try {
			JSONArray results = data.getJSONArray("results");
			JSONObject address = results.getJSONObject(0);
			JSONObject geometry = (JSONObject) address.get("geometry");
			JSONObject viewport = (JSONObject) geometry.get("viewport");
			return viewport;	
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Private method helps to generate the location of the given JSONObject
	 * @param data the JSONObject
	 * @return the viewport for parsing the bound, null if the parsing fails.
	 */
	private static JSONObject getLocationObj(JSONObject data) {
		try {
			JSONArray results = data.getJSONArray("results");
			JSONObject address = results.getJSONObject(0);
			JSONObject geometry = (JSONObject) address.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");
			return location;	
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;
	}
	
	public static double[] getLocation(JSONObject data) {
		try {
			double[] result = new double[2];
			JSONObject location = getLocationObj(data);
			Double lat = (double) location.get("lat");
			Double lng = (double) location.get("lng");
			result[0] = lat;
			result[1] = lng;
			return result;	
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * Get the new southwest bound of the location.
	 * @param bound bound google map API returns.
	 * @return the Double array of new southwest bound.
	 */
	public static double[] getNewSouthWest(double[] bound) {
		double[] newSouthWest = new double[2];
		newSouthWest[0] = bound[0] - parameterForLatitude;
		newSouthWest[1] = bound[1] - parameterForLongtitue;
		return newSouthWest;
	}
	
	/**
	 * Get the new northeast bound of the location.
	 * @param bound bound google map API returns.
	 * @return the Double array of new northeast bound.
	 */
	public static double[] getNewNorthEest(double[] bound) {
		double[] newNorthEest = new double[2];
		newNorthEest[0] = bound[0] + parameterForLatitude;
		newNorthEest[1] = bound[1] + parameterForLongtitue;
		return newNorthEest;
	}
	
	public static String ReadFile(String path) {
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}









