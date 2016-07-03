package bu.edu.upark.repositories;

import java.util.List;

import bu.edu.upark.entities.ParkingInfo;
public interface ParkingInfoDAO {
	public void addParkInfo(ParkingInfo pi);
    public void updateParkInfo(ParkingInfo pi);
    public List<ParkingInfo> findInfobyName(String username);
    public ParkingInfo getParkInfo(String id);
    public void deleteParkInfo(ParkingInfo pi);
    public List<ParkingInfo> findAll();
}
