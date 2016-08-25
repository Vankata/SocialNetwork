package user;

import java.util.List;

public interface IUserStatus {

	boolean containsUser(String email);

	//dobavqme go v sistemata pri registraciq
	void addUser(User user);

	//mahame go pri banvane ili iztrivane na profila
	void removeUser(String email);


	//pri tyrsene na chovek s cel dobavqne vyv friends
//	User searchUser(String name, String lastName) throws Exception;


}