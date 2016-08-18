package user;

import wall.Post;

public interface IAdmin {

	void removePost(User user, Post post);

	void removeUser(User user);

}