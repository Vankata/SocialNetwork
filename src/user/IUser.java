package user;

import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import chat.exceptions.MessageException;
import user.exceptions.UserException;
import wall.Post;
import wall.exceptions.PhotoException;
import wall.exceptions.PostException;
import wall.exceptions.WallException;

public interface IUser {
	// mojem da haresvame samo na drugite

	public abstract void likeFriendsPost(String friendName, Post post) throws UserException, WallException, PostException;

	// mojem da kometirame na drugite i na nas
	public abstract void commentPost(Post post, String comment) throws PostException, UserException;

	// mojem da triem samo nashi postove
	// shte proverqvame dali postyt e instanceof Photo
	public abstract void deletePost(Post post) throws UserException, WallException;

	// mojem da reportvame samo postove na priqteli
	//public abstract void reportPost(User friend, Post post);

	// dobavqme snimka na stenata
	public abstract void postPicture(String picturePath) throws UserException, PostException, PhotoException, WallException;

	//dobavqme snimka s text na stenata
	public abstract void postPictureWithText(String pathToThePhoto, String text) throws UserException, PostException, PhotoException, WallException;
	
	//dobavqme text na stenata
	public abstract void postText(String text) throws UserException, PostException, PhotoException, WallException;
	
	// ---------------------------------------
	// proverqvame dali syshtestvuva v userStatus i dali veche go imame
	public abstract void addFriend(User user) throws UserException, ChatBoxException;

	// proverqvame dali go imame v priqteli
	public abstract void removeFirend(User user);

	// pokazva infoto na friend
	public abstract void reviewFriendInfo(User friend);

	// pokazva stenata na friend
	public abstract void reviewFriendWall(User firend);

	// ---------------------------------------
	// trie profila ot userStatus
	public abstract void deleteProfile(String password, String email);

	// izlizame ot accounta, zarejda ni guest stranicata
	public abstract void logout();

	// ---------------------------------------
	// prashtame syobshtenie
	public abstract void sendMessage(User friend, String message) throws ChatException, MessageException, UserException;

	// preglejdame chata s nqkoi
	public abstract void reviewChat(User friend) throws UserException;
}
