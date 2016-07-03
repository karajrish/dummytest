package bu.edu.upark.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import bu.edu.upark.entities.UserAccount;
import bu.edu.upark.services.LoginServiceImpl;

@Controller
@ComponentScan("bu.edu.upark.services")

public class LoginController {
	@Autowired private LoginServiceImpl loginService;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody UserAccount saveUserRestful( HttpServletRequest req , @RequestBody UserAccount user )   
	{		
		UserAccount uc = new UserAccount();

		uc.setUsername("");
		uc.setFirstname("");
		uc.setLastname("");
		
		if (loginService.doLogin(req, user))
		{
			HttpSession session = req.getSession();
			uc.setUsername((String)session.getAttribute("username"));
			uc.setFirstname((String) session.getAttribute("firstname")); 
			uc.setLastname((String) session.getAttribute("lastname")); 
		}	
		return uc;
	}
}
