package user;

import java.util.Map.Entry;

import user.exceptions.AdminException;
import user.exceptions.UserException;
import wall.Post;
import wall.exceptions.WallException;

public class Admin extends User implements IAdmin {

	 private UserStatus status=Guest.getStatus();

	public Admin(String password, String email, String firstName, String lastName, UserStatus status)
			throws UserException {
		super(password, email, firstName, lastName, status);
	}

	@Override
	public void removePost(User user, Post post) throws AdminException, WallException {
		if (user == null) {
			throw new AdminException("Invalid user! ");
		}

		if (post == null) {
			throw new AdminException("Invalid post! ");
		}

		user.getPersonalWall().removePost(post);

		for (Entry<String, User> entry : status.allUsers.entrySet()) {
			entry.getValue().getCommonWall().removePost(post);
		}
	}

	@Override
	public void removeUser(String email) {
		if ((email != null) && (email.trim().length() > 0)) {
			status.removeUser(email);	
		}
		
	}

}
