package bu.edu.upark.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {
	
	@RequestMapping("/")
	public String home(HttpServletRequest req) {			
	    return "index.html";
	}
	
	@RequestMapping(value = "/session" , method = RequestMethod.GET)
	public String testSession(HttpServletRequest req) {	
		HttpSession session = req.getSession();	
		System.out.println(session.getAttribute("username"));	
	    return "index.html";
	}
}
