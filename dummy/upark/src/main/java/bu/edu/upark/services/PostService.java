package bu.edu.upark.services;

import javax.servlet.http.HttpServletRequest;
import bu.edu.upark.entities.ParkingInfo;

public interface PostService {
	public boolean doPost(HttpServletRequest req , ParkingInfo pi);

}
