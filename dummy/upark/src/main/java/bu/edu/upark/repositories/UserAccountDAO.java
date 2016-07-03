package bu.edu.upark.repositories;

import bu.edu.upark.entities.UserAccount;

public interface UserAccountDAO {
    public void addUserAccount(UserAccount ua);
    public void updateUserAccount(UserAccount ua);
    public UserAccount findUserbyName(String username);
    public UserAccount getUserAccount(String id);
    public void deleteAccount(String id);
}
