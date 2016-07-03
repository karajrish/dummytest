package bu.edu.upark.controllers;

import java.util.List;
import bu.edu.upark.entities.*;
import javax.servlet.http.HttpServletRequest;
import bu.edu.upark.repositories.ParkingInfoDAO;
import bu.edu.upark.services.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import bu.edu.upark.utils.*;

@Controller
@ComponentScan("bu.edu.upark.repositories")
public class SearchController {
	SearchService searchService;
	String userinput;
	double lat1;
	double lat2;

	double lng1;
	double lng2;
	double[] NorthEast = new double[2];
	double[] SouthWest = new double[2];
	double[] NewNorthEast = new double[2];
	double[] NewSouthWest = new double[2];

	@Autowired
	ParkingInfoDAO pid;

	@RequestMapping(value = "/search", method = RequestMethod.POST)

	public @ResponseBody ParkingInfoList saveUserRestful(

			HttpServletRequest req, @RequestBody UserInput userInput) {		
		ParkingInfoList al = new ParkingInfoList();
		List<ParkingInfo> results = pid.findAll();

		userinput = userInput.getAddress();
		JSONObject bean;
		try {
			bean = GoogleMapUtils.getInstance().geocodeByAddress(userinput);
			NorthEast = SearchUtils.getNorthEast(bean);
			SouthWest = SearchUtils.getSouthWest(bean);
			NewNorthEast = SearchUtils.getNewNorthEest(NorthEast);
			NewSouthWest = SearchUtils.getNewSouthWest(SouthWest);		
			lat1 = NewSouthWest[0];
			lat2 = NewNorthEast[0];
			lng1 = NewNorthEast[1];
			lng2 = NewSouthWest[1];			
			for (ParkingInfo result : results) {
				if (result.getLattitude() <= lat2
						&& result.getLattitude() >= lat1
						&& result.getLongitude() <= lng1
						&& result.getLongitude() >= lng2) {
					al.getList().add(result);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
}
