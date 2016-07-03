package bu.edu.upark.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import bu.edu.upark.entities.UserAccount;
import bu.edu.upark.repositories.UserAccountDAO;

@Service
@ComponentScan("bu.edu.upark.repositories")
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private UserAccountDAO uad;

	@Override
	public boolean doRegister(HttpServletRequest req, UserAccount useraccount) {
		UserAccount ua = uad.findUserbyName(useraccount.getUsername());
		if (ua == null) {
			uad.addUserAccount(useraccount);
			req.getSession().setMaxInactiveInterval(60 * 30);
			req.getSession().setAttribute("username", useraccount.getUsername());
			req.getSession().setAttribute("lastname", useraccount.getLastname());
			req.getSession().setAttribute("firstname", useraccount.getFirstname());
			req.getSession().setAttribute("password", useraccount.getPassword());
			return true;
		}
		return false;
	}

}
