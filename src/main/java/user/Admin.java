package user;

import java.util.Map.Entry;

import user.exceptions.AdminException;
import user.exceptions.UserException;
import user.exceptions.UserStatusException;
import wall.Post;
import wall.exceptions.WallException;

public class Admin extends User implements IAdmin {

	private static Admin adminInstance = null;

	private UserStatus status = Guest.getStatus();

	private Admin(String password, String email, String firstName, String lastName, UserStatus status)
			throws UserException {
		super(password, email, firstName, lastName, status);
	}

	public static Admin getInstance(String password, String email, String firstName, String lastName, UserStatus status)
			throws UserException {
		if (adminInstance == null) {
			adminInstance = new Admin(password, email, firstName, lastName, status);
		}
		return adminInstance;

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
	public void removeUser(String email) throws UserStatusException {
		if (isStringValid(email)) {
			status.removeUser(email);
		}

	}

}
