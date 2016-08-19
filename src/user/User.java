package user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import chat.Chat;
import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import chat.exceptions.MessageException;
import user.exceptions.UserException;
import wall.CommonWall;
import wall.IPersonalWall;
import wall.PersonalWall;
import wall.Photo;
import wall.Post;

public class User implements IUser {
	// zadyljitelno pri registraciq
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	// -------------------------
	// pri update info ili pri registraciq(izbiratelnpo)
	private String phoneNumber;
	private LocalDate birthdayDate;
	// -------------------------
	// <email, user>
	private Map<String, User> friends = new HashMap<String, User>();
	private Map<User, Chat> chats = new TreeMap<User, Chat>((user1, user2) -> user1.getEmail().compareTo(user2.getEmail()));
	private IPersonalWall personalWall;
	private CommonWall commonWall;
	private Photo profilePicture;

	// ----------------------------------
	private UserStatus userStatus;

	public User(String password, String email, String firstName, String lastName, UserStatus userStatus)
			throws UserException {

		setPassword(password);
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);

		friends = new HashMap<String, User>();
		personalWall = new PersonalWall();
		commonWall = new CommonWall();

	}
	
	private void setEmail(String email) throws UserException {
		if (email == null || email.length() == 0) {
			throw new UserException("You are trying to set an immaginary email");
		} else {
			this.email = email;
		}
	}

	public void setPassword(String password) throws UserException {
		if (password == null || password.length() == 0) {
			throw new UserException("You are trying to set an immaginary password!");
		} else {
			this.password = password;
		}
	}

	public void setFirstName(String firstName) throws UserException {
		if (firstName == null || firstName.length() == 0) {
			throw new UserException("You are trying to set an invalid firstName!");
		} else {
			this.firstName = firstName;
		}

	}

	public void setLastName(String lastName) throws UserException {
		if (lastName == null || lastName.length() == 0) {
			throw new UserException("You are trying to set an invalid lastName!");
		} else {
			this.lastName = lastName;
		}
	}

	public void setPhoneNumber(String phoneNumber) throws UserException {

		if (phoneNumber == null || phoneNumber.length() == 0) {
			throw new UserException("You are trying to set an invalid phoneNumber!");
		} else {
			if (phoneNumber.matches("[0]+[8]+[7-9]{1}[0-9]{7}")) {
				this.phoneNumber = phoneNumber;
			} else {
				throw new UserException("Invalid phone number");
			}

		}

	}

	// Only for test
	@Override
	public String toString() {
		return "User [password=" + password + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", birthdayDate=" + birthdayDate + ", profilePicture="
				+ profilePicture + "]";
	}

	/// ----------------------------------------------------------

	public void setBirthdayDate(LocalDate birthdayDate) throws UserException {
		if (birthdayDate == null) {
			throw new UserException("Invalid input for BirthDay");
		}

		this.birthdayDate = birthdayDate;
	}

	public void setProfilePicture(Photo profilePicture) throws UserException {
		if (profilePicture == null) {
			throw new UserException("Cant set this photo as profile picture");
		}
		this.profilePicture = profilePicture;
	}
	
	public void addNewChat( User friend, Chat newChat) throws UserException{
		
		if(newChat != null){
			this.chats.put(friend, newChat);
		}else{
			throw new UserException("Invalid chat! ");
		}
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
	public void addFriend(User user) throws UserException, ChatBoxException {
		// TODO Auto-generated method stub
		//VANKATA: promenqm malko, realiziram chata2
		if(!this.friends.containsValue(user)){
			this.friends.put(user.getEmail(), user);
		}
		if(!user.hasThisFriend(this)){
			user.addFriend(this);
		}
		Chat chat = new Chat();
		this.addNewChat(user, chat);
		user.addNewChat(this, chat);
	}
	
	//VANKATA dobavqm tozi metod
	public boolean hasThisFriend(User user) throws UserException{
		if(user != null){
			if(this.friends.containsValue(user)){
				return true;
			}
			return false;
		}else{
			throw new UserException("Invalid user! ");
		}
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public void sendMessage(User friend, String message) throws ChatException, MessageException, UserException {
		if(this.friends.containsKey(friend.getEmail())){
			this.getChatbyUser(friend).addMessage(message);
		}else{
			throw new UserException("Invalid friend! ");
		}
		
	}
	
	private Chat getChatbyUser(User friend) throws UserException{
		if(this.chats.containsKey(friend)){
			return this.chats.get(friend);		
		}else{
			throw new UserException("Invalid friend! ");
		}
	}

	@Override
	public void reviewChat(User friend) throws UserException {
		this.getChatbyUser(friend).printChat();
	}


}
