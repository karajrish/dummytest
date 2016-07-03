package bu.edu.upark.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import bu.edu.upark.entities.UserAccount;

@SuppressWarnings("deprecation")
@Repository
public class UserAccountDAOImpl implements UserAccountDAO{

	private Session getCurrentSession(){
		Configuration cfg = new AnnotationConfiguration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
		Session s = sf.openSession();
		return s;
	}
	String queryHQL = "from UserAccount where username =:inputUsername";
 
    public void addUserAccount(UserAccount ua) {
    	Session s =getCurrentSession();
    	s.beginTransaction();
        s.save(ua);
        s.getTransaction().commit();
    }
 
    public void updateUserAccount(UserAccount ua) {
        UserAccount uaupdate = getUserAccount(ua.getUsername());
        uaupdate.setPassword(ua.getPassword());
        uaupdate.setUsername(ua.getUsername());
        Session s =getCurrentSession();
    	s.beginTransaction();
        s.update(uaupdate);
        s.getTransaction().commit();
 
    }
    public UserAccount findUserbyName(String username){
    	Session s =getCurrentSession();
    	s.beginTransaction();
    	Query query = s.createQuery(queryHQL).setString("inputUsername",username);
    	@SuppressWarnings("unchecked")
		List<UserAccount> list= query.list();
    	s.getTransaction().commit();
    	if(list.isEmpty()) return null;
    	return list.get(0);

    }
 
    public UserAccount getUserAccount(String id) {
    	Session s =getCurrentSession();
    	s.beginTransaction();
        UserAccount ua = (UserAccount) s.get(UserAccount.class, id);
        s.getTransaction().commit();
        return ua;
    }
 
    public void deleteAccount(String id) {
        UserAccount ua= getUserAccount(id);

        if (ua!= null){
        	Session s =getCurrentSession();
        	s.beginTransaction();
            s.delete(ua);
            s.getTransaction().commit();
        }
    }
}
