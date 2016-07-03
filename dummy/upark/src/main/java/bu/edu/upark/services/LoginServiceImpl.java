package bu.edu.upark.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import bu.edu.upark.repositories.UserAccountDAO;
import bu.edu.upark.entities.UserAccount;

@Service
@ComponentScan("bu.edu.upark.repositories")
public class LoginServiceImpl implements LoginService{
	   @Autowired
	   private UserAccountDAO uad;
       public boolean doLogin(HttpServletRequest req,UserAccount userAccount){
    	   
    	   UserAccount ua = uad.findUserbyName(userAccount.getUsername());
 	   
    	   if(ua!=null&&ua.getPassword().equals(userAccount.getPassword())){
    		  System.out.println("OK");
    		  req.getSession().setMaxInactiveInterval(60*30);
    		  req.getSession().setAttribute("username", ua.getUsername());
    		  req.getSession().setAttribute("lastname", ua.getLastname());
    		  req.getSession().setAttribute("firstname", ua.getFirstname());
    		  req.getSession().setAttribute("password", ua.getPassword());
    		   return true;
    	   }
    	   else{
    		   System.out.println("Wrong");
    		   return  false;
    	   }
    	   
       }
        
       public void printSession(HttpServletRequest req)
       {
    	   HttpSession session = req.getSession();
    	   String username = (String) session.getAttribute("username");
   		
   			System.out.println(username);
       }

	

}
