package bu.edu.upark.services;

import javax.servlet.http.HttpServletRequest;
import bu.edu.upark.entities.UserAccount;

 interface LoginService {
	public boolean doLogin(HttpServletRequest req,UserAccount userAccount);
	public void printSession(HttpServletRequest req);

}
