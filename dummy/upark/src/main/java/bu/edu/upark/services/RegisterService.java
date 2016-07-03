package bu.edu.upark.services;

import javax.servlet.http.HttpServletRequest;

import bu.edu.upark.entities.UserAccount;

public interface RegisterService {
	public boolean doRegister(HttpServletRequest req,UserAccount useraccount);
}
