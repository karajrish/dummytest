package bu.edu.upark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bu.edu.upark.entities.Flag;
import bu.edu.upark.entities.ParkingInfo;
import bu.edu.upark.repositories.ParkingInfoDAO;

@Controller
@ComponentScan("bu.edu.upark.repositories")
public class DeleteParkingInfoController {
	@Autowired 
	ParkingInfoDAO pid;
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody Flag doDelete( @RequestParam int infoId){
		ParkingInfo parkingInfo = new ParkingInfo();
		parkingInfo.setInfoId(infoId);
		pid.deleteParkInfo(parkingInfo);
		Flag flag = new Flag();
		flag.setSuccess(true);
		return flag;
	}

}
