package user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStatus implements IUserStatus {
	Map<String, User> allUsers = new HashMap<String, User>();
	
	@Override
	public boolean containsUser(User user){
		return false;
	}
	
	//dobavqme go v sistemata pri registraciq
	@Override
	public void addUser(User user){
		
	}
	
	//mahame go pri banvane ili iztrivane na profila
	@Override
	public void removeUser(User user){
		
	}
	
	//pri tyrsene na chovek s cel dobavqne vyv friends
	@Override
	public List<User> searchUser(User user){
		return null;
	}
	
	
}
