package bu.edu.upark.controllers;

import bu.edu.upark.entities.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import bu.edu.upark.services.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import bu.edu.upark.utils.GoogleMapUtils;
import bu.edu.upark.utils.SearchUtils;

@Controller
@ComponentScan("bu.edu.upark.services")
public class PostController {

	@Autowired PostServiceImpl postService;
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public @ResponseBody ParkingInfo saveUserRestful( HttpServletRequest req , @RequestBody ParkingInfo parkInfo )   
	{		
		HttpSession session = req.getSession();
		parkInfo.setUsername((String)session.getAttribute("username"));
		String searchAddr = "";
		if(parkInfo.getAddress2() == null)
		{
			searchAddr = parkInfo.getAddress1() +  " " + parkInfo.getArea();
		}
		else
		{
			searchAddr = parkInfo.getAddress1() + " " + parkInfo.getAddress2() + " " + parkInfo.getArea();		
		}

		try {
			JSONObject object = GoogleMapUtils.getInstance().geocodeByAddress(searchAddr);
			String formatedAddr = SearchUtils.getFormattedAddress(object);
			parkInfo.setAddress1(formatedAddr);
			parkInfo.setAddress2(null);
			double[] location = SearchUtils.getLocation(object);
			parkInfo.setLattitude(location[0]);
			parkInfo.setLongitude(location[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(postService.doPost(req, parkInfo))
		{
			
			return parkInfo;
		}
		else
		{
			
			return null;
		}

	}
	



}
