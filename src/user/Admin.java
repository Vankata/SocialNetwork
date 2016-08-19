package user;

import user.exceptions.UserException;
import wall.Post;

public class Admin extends User implements IAdmin {
	
	//private UserStatus userStatus;
	
	public Admin(String password, String email, String firstName, String lastName, UserStatus status) throws UserException {
		super(password, email, firstName, lastName, status);
	}

	@Override
	public void removePost(User user, Post post){
		
	}

	@Override
	public void removeUser(User user){
		
	}
}
