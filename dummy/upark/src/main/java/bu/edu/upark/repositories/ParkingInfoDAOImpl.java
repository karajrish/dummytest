package bu.edu.upark.repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import bu.edu.upark.entities.ParkingInfo;

@SuppressWarnings("deprecation")
@Repository
public class ParkingInfoDAOImpl  implements ParkingInfoDAO{
	
	
	String findByUsername = "from ParkingInfo where username =:inputUsername";
	
	String findAllHQL = "from ParkingInfo";
	public void addParkInfo(ParkingInfo pi)
	{
		Session s =getCurrentSession();
    	s.beginTransaction();
        s.save(pi);
        s.getTransaction().commit();
		
	}
	
    public void updateParkInfo(ParkingInfo pi)
    {
    	
    }
    
    public List<ParkingInfo> findAll()
    {
    	Session s = getCurrentSession();
		s.beginTransaction();
		Query query = s.createQuery(findAllHQL);
		@SuppressWarnings("unchecked")
		List<ParkingInfo> list= query.list();
		s.getTransaction().commit();
		
		return list;   	
    }
    
    public List<ParkingInfo> findInfobyName(String username)
	{
    	Session s = getCurrentSession();
		s.beginTransaction();
		Query query = s.createQuery(findByUsername).setString("inputUsername",username);
		@SuppressWarnings("unchecked")
		List<ParkingInfo> list= query.list();
		s.getTransaction().commit();
		if(list.isEmpty()) return null;
		return list;
    }
    
    public ParkingInfo getParkInfo(String id)
    {
    	return null;
    }
    
    public void deleteParkInfo(ParkingInfo pi)
    {
    	Session s =getCurrentSession();
    	s.beginTransaction();
        s.delete(pi);
        s.getTransaction().commit();
    }

    
    private Session getCurrentSession(){
		Configuration cfg = new AnnotationConfiguration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
		Session s = sf.openSession();
		return s;
	}
}
