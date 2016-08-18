package user;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import chat.ChatBox;
import wall.CommonWall;
import wall.IPersonalWall;
import wall.Photo;
import wall.Post;

public class User implements IUser{
	//zadyljitelno pri registraciq
	private char[] password;
	private String email;
	private String firstName;
	private String lastName;
	//-------------------------
	//pri update info ili pri registraciq(izbiratelnpo)
	private String phoneNumber;
	private LocalDate birthdayDate;
	//-------------------------
	//<email, user>
	private Map<String, User> friends = new HashMap<String, User>();
	private ChatBox chatBox;
	private IPersonalWall personalWall;
	private CommonWall commonWall;
	private Photo profilePicture;
	//-----------------------------------
	private UserStatus userStatus;
	
	public User(char[] password, String email, String firstName, String lastName) {
		super();
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	//smename imenata na update
	public void setPassword(char[] password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setBirthdayDate(LocalDate birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public void setProfilePicture(Photo profilePicture) {
		this.profilePicture = profilePicture;
	}
	public ChatBox getChatBox() {
		return chatBox;
	}


	@Override
	public void likePost(User friend, Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void commentPost(User user, Post post, String comment) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deletePost(Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reportPost(User friend, Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addPost(Post post) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addFriend(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeFirend(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reviewFriendInfo(User friend) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reviewFriendWall(User firend) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteProfile(String password, String email) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

/*
	@Override
	public void sendMessage(User friend, String message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reviewChat(User friend) {
		// TODO Auto-generated method stub
		
	}
*/

}
