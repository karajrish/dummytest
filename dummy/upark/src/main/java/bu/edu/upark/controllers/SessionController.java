package bu.edu.upark.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bu.edu.upark.entities.UserAccount;

@Controller
public class SessionController {
	@RequestMapping(value = "/checkSession" , method = RequestMethod.POST)
	public @ResponseBody UserAccount checkSession(HttpServletRequest req) {		
		UserAccount uc = new UserAccount();		
		HttpSession session = req.getSession();		
		uc.setUsername((String)session.getAttribute("username"));
		uc.setFirstname((String) session.getAttribute("firstname")); 
		uc.setLastname((String) session.getAttribute("lastname")); 		
		if (uc.getUsername() == null)
		{
			uc.setUsername("");
		}

	    return uc;
	}		
}
