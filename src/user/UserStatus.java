package user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import user.exceptions.UserStatusException;
import wall.Post;

public class UserStatus implements IUserStatus {
	static Map<String, User> allUsers = new HashMap<String, User>();

	@Override
	public boolean containsUser(String email) {
		return allUsers.containsKey(email);

	}

	// dobavqme go v sistemata pri registraciq

	@Override
	public void addUser(User user) {
		allUsers.put(user.getEmail(), user);
	}

	// mahame go pri banvane ili iztrivane na profila
	@Override
	public void removeUser(String email) {

		if (allUsers.containsKey(email)) {
			System.out.println("You deleted your profile successfuly, " + allUsers.get(email).getFirstName() + " "
					+ allUsers.get(email).getLastName() + "!");
			allUsers.remove(email);

		}

	}

	// pri tyrsene na chovek s cel dobavqne vyv friends;
//	GERI: ne znam zashto tozi metod e tuk, a ne v User, razpisah go, ima go i tuk i v User
//	@Override
//	public User searchUser(String name, String lastName) throws Exception {
//
//		for(String key: allUsers.keySet()){
//			if((allUsers.get(key).getFirstName().equals(name))&&(allUsers.get(key).getLastName().equals(lastName))){
//				String email=allUsers.get(key).getEmail();
//				return allUsers.get(email);
//			}
//		}
//		throw new Exception("There's no user with this name!");
//	}

	// Tova da se opravi!!!!vryshtane na kolekciq

	public Map<String, User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(Map<String, User> allUsers) {
		this.allUsers = allUsers;
	}

}
