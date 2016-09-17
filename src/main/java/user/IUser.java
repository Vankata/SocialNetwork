package user;

import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import chat.exceptions.MessageException;
import user.exceptions.UserException;
import user.exceptions.UserStatusException;
import wall.PersonalWall;
import wall.Photo;
import wall.Post;
import wall.exceptions.PhotoException;
import wall.exceptions.PostException;
import wall.exceptions.WallException;

public interface IUser {

	public abstract void likePost(Post post) throws UserException, WallException, PostException;

	public abstract void commentPost(Post post, String comment) throws PostException, UserException;

	public abstract void deletePost(Post post) throws UserException, WallException;

	public abstract Photo postPicture(String pathToThePhoto, String text)
			throws UserException, PostException, PhotoException, WallException;

	public abstract void addFriend(User user) throws UserException, ChatBoxException;

	public abstract String removeFirend(User user) throws UserException;

	public abstract String reviewFriendInfo(User friend) throws UserException;

	public abstract PersonalWall reviewFriendWall(String name, String lastName) throws Exception;

	public User searchUser(String name, String lastName) throws UserException;

	// ---------------------------------------
	public abstract void deleteProfile(String password, String email) throws UserStatusException, UserException;

	public abstract void logout();

	// ---------------------------------------
	public abstract void sendMessage(User friend, String message) throws ChatException, MessageException, UserException;

	public abstract void reviewChat(User friend) throws UserException;
}