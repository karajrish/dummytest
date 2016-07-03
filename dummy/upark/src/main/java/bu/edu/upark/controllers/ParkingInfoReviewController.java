package bu.edu.upark.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bu.edu.upark.entities.ParkingInfo;
import bu.edu.upark.entities.ParkingInfoList;
import bu.edu.upark.entities.UserAccount;
import bu.edu.upark.repositories.ParkingInfoDAO;

@Controller
@ComponentScan("bu.edu.upark.repositories")
public class ParkingInfoReviewController {
	@Autowired 
	ParkingInfoDAO pid;
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public @ResponseBody ParkingInfoList doReview(@RequestBody UserAccount useraccount){
		ParkingInfoList pil = new ParkingInfoList();
		List<ParkingInfo> parkingList=pid.findInfobyName(useraccount.getUsername());
		if(parkingList!=null)
		pil.getList().addAll(parkingList);
		return pil;
	}
}
