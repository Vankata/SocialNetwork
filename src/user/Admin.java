package user;

import java.util.Map.Entry;

import user.exceptions.AdminException;
import user.exceptions.UserException;
import wall.Post;
import wall.exceptions.WallException;

public class Admin extends User implements IAdmin {

	// private UserStatus userStatus;

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

		for (Entry<String, User> entry : userStatus.allUsers.entrySet()) {
			entry.getValue().getCommonWall().removePost(post);
		}
	}

	@Override
	public void removeUser(User user) {

	}
}
