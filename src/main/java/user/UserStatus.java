package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import db.DBConnection;
import db.UserDAO;

import java.util.Set;

import user.exceptions.UserException;
import user.exceptions.UserStatusException;
import wall.Post;

public class UserStatus implements IUserStatus {
	private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users";
	private static UserStatus instance = null;
	Map<String, User> allUsers;

	private UserStatus() throws UserException {
		allUsers = new HashMap<String, User>();
		
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS_SQL);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				 
				String email = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String phoneNumber = rs.getString(6);
				
				User user = new User(password, email, firstName, lastName, this);
				//user.setPhoneNumber(phoneNumber);
				
				this.allUsers.put(email, user);
			}
			

		} catch (Exception e) {
			throw new UserException("User cannot be delete now, please try again later.", e);
		}
	}

	public static UserStatus getInstance() throws UserException {

		if (instance == null) {
			instance = new UserStatus();
		}

		return instance;
	}

	@Override
	public boolean containsUser(String email) {
		
		for (User user : allUsers.values()) {
			System.out.println(user.getEmail() + " " + user.getPassword());
		}
		return allUsers.containsKey(email);

	}

	// dobavqme go v sistemata pri registraciq

	@Override
	public void addUser(User user) throws UserException {
		allUsers.put(user.getEmail(), user);
		new UserDAO().registerUser(user);
	}

	// mahame go pri banvane ili iztrivane na profila
	@Override
	public void removeUser(String email) throws UserStatusException {
		if ((email != null) && (email.trim().length() > 0)) {
			if (allUsers.containsKey(email)) {
				System.out.println("You deleted your profile successfuly, " + allUsers.get(email).getFirstName() + " "
						+ allUsers.get(email).getLastName() + "!");
				allUsers.remove(email);
			}
		} else
			throw new UserStatusException("Invalid email!");

	}

	public Map<String, User> getAllUsers() {
		return allUsers;
	}

//	public void setAllUsers(Map<String, User> allUsers) {
//		this.allUsers = allUsers;
//	}

}
