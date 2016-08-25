package user;

import user.exceptions.AdminException;
import wall.Post;
import wall.exceptions.WallException;

public interface IAdmin {

	void removePost(User user, Post post) throws AdminException, WallException;

	void removeUser(String email);

}