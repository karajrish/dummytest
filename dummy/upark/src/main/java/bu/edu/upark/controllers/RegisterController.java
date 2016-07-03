package bu.edu.upark.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bu.edu.upark.entities.UserAccount;
import bu.edu.upark.services.RegisterServiceImpl;

@Controller
public class RegisterController {
	@Autowired
	RegisterServiceImpl RegisterService;
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public UserAccount doRegister( HttpServletRequest req ,@RequestBody UserAccount user){	
		UserAccount uc = new UserAccount();
		uc.setUsername("");
		if(RegisterService.doRegister(req, user))
		{
			uc.setUsername(user.getUsername());
			uc.setFirstname(user.getFirstname());
			uc.setLastname(user.getLastname());
		}
	
		return uc;
	}
	

}
