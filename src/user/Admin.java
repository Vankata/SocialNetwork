package user;

import wall.Post;

public class Admin extends User implements IAdmin {
	
	//private UserStatus userStatus;
	
	public Admin(String password, String email, String firstName, String lastName) {
		super(password, email, firstName, lastName);
	}

	@Override
	public void removePost(User user, Post post){
		
	}

	@Override
	public void removeUser(User user){
		
	}
}
