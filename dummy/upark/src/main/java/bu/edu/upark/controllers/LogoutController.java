package bu.edu.upark.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bu.edu.upark.entities.UserAccount;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	public @ResponseBody UserAccount checkSession(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("username");
		
		UserAccount uc = new UserAccount();
		
		uc.setUsername("");
		
		return uc;
		
	}
}
