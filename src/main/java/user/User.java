package user;

import java.io.BufferedWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import chat.Chat;
import chat.Message;
import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import chat.exceptions.MessageException;
import db.ChatDAO;
import db.PostDAO;
import db.UserDAO;
import user.exceptions.UserException;
import user.exceptions.UserStatusException;
import wall.CommonWall;
import wall.PersonalWall;
import wall.Photo;
import wall.Post;
import wall.Wall;
import wall.exceptions.PhotoException;
import wall.exceptions.PostException;
import wall.exceptions.WallException;

public class User implements IUser {
	// zadyljitelno pri registraciq
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private int userID;
	// -------------------------
	// pri update info ili pri registraciq(izbiratelnpo)
	private String phoneNumber;
	private LocalDate birthdayDate;
	// -------------------------
	// <email, user>
	private Map<String, User> friends = new HashMap<String, User>();
	private Map<User, Chat> chats = new TreeMap<User, Chat>(
			(user1, user2) -> user1.getEmail().compareTo(user2.getEmail()));
	private PersonalWall personalWall;
	private CommonWall commonWall;
	private Photo profilePicture;

	// ----------------------------------
	protected UserStatus status;

	public User(String password, String email, String firstName, String lastName, UserStatus status)
			throws UserException {

		this.setPassword(password);
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		
		if(status != null){
			this.status = status;
		}else{
			throw new UserException("Invalid user status! ");
		}

		this.friends = new HashMap<String, User>();
		this.personalWall = new PersonalWall();
		this.commonWall = new CommonWall();
	}

	protected boolean chekPassword(String password) {
		return this.password.equals(password);
	}

	private void setEmail(String email) throws UserException {
		if (email == null || email.length() == 0) {
			throw new UserException("You are trying to set an immaginary email");
		} else {
			this.email = email;
		}
	}

	private void setPassword(String password) throws UserException {
		if (password == null || password.length() == 0) {
			throw new UserException("You are trying to set an immaginary password!");
		} else {
			this.password = password;
		}
	}

	private void setFirstName(String firstName) throws UserException {
		if (firstName == null || firstName.length() == 0) {
			throw new UserException("You are trying to set an invalid firstName!");
		} else {
			this.firstName = firstName;
		}

	}

	private void setLastName(String lastName) throws UserException {
		if (lastName == null || lastName.length() == 0) {
			throw new UserException("You are trying to set an invalid lastName!");
		} else {
			this.lastName = lastName;
		}
	}

	//DAO method done
	public void setPhoneNumber(String phoneNumber) throws UserException {

		if (phoneNumber == null || phoneNumber.length() == 0) {
			throw new UserException("You are trying to set an invalid phoneNumber!");
		} else {
			if (phoneNumber.matches("[0]+[8]+[7-9]{1}[0-9]{7}")) {
				this.phoneNumber = phoneNumber;
				new UserDAO().setPhoneNumber(this, phoneNumber);
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

	
	//DAO method done
	public void setBirthdayDate(LocalDate birthdayDate) throws UserException {
		if (birthdayDate == null) {
			throw new UserException("Invalid input for BirthDay");
		}

		this.birthdayDate = birthdayDate;
		new UserDAO().setBirthdayDate(this, birthdayDate);
	}

	public void setProfilePicture(Photo profilePicture) throws UserException {
		if (profilePicture == null) {
			throw new UserException("Cant set this photo as profile picture");
		}
		this.profilePicture = profilePicture;
	}

	public void addNewChat(User friend, Chat newChat) throws UserException {

		if (newChat != null) {
			this.chats.put(friend, newChat);
		
		} else {
			throw new UserException("Invalid chat! ");
		}
	}

	public void likePost(Post post) throws WallException, PostException, UserException {

		if (post != null) {
			post.addLike(this.getFirstName() + " " + this.getLastName());
			new PostDAO().liketPost(this, post);
//			new PostDAO().addLike(post,this);
		} else {
			throw new UserException("Invalid post! ");
		}
	}

	/*
	 * @Override public void likeFriendsPost(String friendName, Post post)
	 * throws UserException, WallException, PostException {
	 * 
	 * if (post == null || !isStringValid(friendName)) { throw new
	 * UserException(
	 * "You are trying  to like a post with invalid friend name or post" ); }
	 * 
	 * List<User> friendsWithThisName = new ArrayList<User>();
	 * 
	 * // Finds all friends that have friendName among their first or last //
	 * names and adds them in the friendsWithThisName Set for (Entry<String,
	 * User> entry : friends.entrySet()) { if
	 * (entry.getValue().getFirstName().equals(friendName) ||
	 * entry.getValue().getLastName().equals(friendName)) {
	 * friendsWithThisName.add(entry.getValue()); } } //
	 * ---------------------------------------------------------------------
	 * 
	 * if (friendsWithThisName.size() == 0) { // Moje i da ne hvyrlq exception a
	 * samo da izkarkva nadpis. Za sega // go ostawqm taka za testovi celi throw
	 * new UserException("U does not have friend with name " + friendName); }
	 * 
	 * // Multiple matches if (friendsWithThisName.size() > 1) {
	 * 
	 * for (User friend : friendsWithThisName) {
	 * System.out.println(friend.getFirstName() + " " + friend.getLastName()); }
	 * throw new UserException(
	 * "U have more than one frined with this name. Please be more Specific" );
	 * }
	 * 
	 * // Get index 0, cuz we are sure that only one obj is in the arraylist
	 * this.likePost(friendsWithThisName.get(0), post);
	 * 
	 * }
	 * 
	 */

	@Override
	public void commentPost(Post post, String comment) throws PostException, UserException {

		if (post != null) {
			post.addComment(this, comment);
			new PostDAO().commentPost(this, post, comment);
		} else {
			throw new UserException("Invalid post! ");
		}

	}

	@Override
	public void deletePost(Post post) throws UserException, WallException {
		if (post == null) {
			throw new UserException("Invalid Post to delete!");
		}

		this.personalWall.removePost(post);
		if(this.commonWall.containsPost(post)){
			this.commonWall.removePost(post);
		}
	}

	// MAHAME GO HAHAHAAHAHHAHAHAAH
	/*
	 * @Override public void reportPost(User friend, Post post) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	public Photo postPicture(String pathToThePhoto, String text)
			throws UserException, PostException, PhotoException, WallException {

		if (pathToThePhoto != null && pathToThePhoto.trim().length() > 0 && text != null && text.trim().length() > 0) {
			Photo newPhoto = new Photo(text, pathToThePhoto, this);
			this.personalWall.addPost(newPhoto);
			this.commonWall.addPost(newPhoto);
			return newPhoto;
		} else {
			throw new UserException("Invalid picture! ");
		}

	}
	
	public Post post(String text) throws UserException, PostException, WallException {
		
		if (text == null && text.trim().length() == 0) {
			throw new UserException("Invalid Post! ");
		}
		
		Post post = new Post(text, this);
		this.personalWall.addPost(post);
		this.commonWall.addPost(post);
		System.out.println(email + " " + userID);
		new PostDAO().addPost(this, post);
		return post;
	}

	@Override
	public void addFriend(User user) throws UserException, ChatBoxException {
		// TODO Auto-generated method stub
		// VANKATA: promenqm malko, realiziram chata2
		if (!this.hasThisFriend(user)) {
			this.friends.put(user.getEmail(), user);
		}

		Chat chat = new Chat();
		
		if (!user.hasThisFriend(this)) {
			user.addFriend(this);
			new UserDAO().addNewFriendDB(user, this);
			new ChatDAO().addNewChatDB(chat, user, this);
		}

		this.addNewChat(user, chat);
		user.addNewChat(this, chat);
	}

	// VANKATA dobavqm tozi metod
	// Tyrsim po obekt ot tip user
	public boolean hasThisFriend(User user) throws UserException {
		if (user != null) {
			if (this.friends.containsKey(user.getEmail())) {
				return true;
			}
			return false;
		} else {
			throw new UserException("Invalid user! ");
		}
	}

	@Override
	public String removeFirend(User friend) throws UserException {

		if (friend == null) {
			throw new UserException("How can I remove Peter Pan?");
		}

		if (this.hasThisFriend(friend)) {
			this.friends.remove(friend.getEmail());
			return friend.getFirstName() + " " + friend.getLastName() + " has been removed!";

		} else {
			return friend.getFirstName() + " " + friend.getLastName() + " is not your friend!";
		}

	}

	@Override
	public String reviewFriendInfo(User friend) throws UserException {

		if (friend == null) {
			throw new UserException("How can I show info of  Peter Pan?");
		}

		if (this.hasThisFriend(friend)) {
			return friend.getFirstName() + " " + friend.getLastName() + " " + friend.getEmail() + " "
					+ friend.birthdayDate;

		} else {
			return friend.getFirstName() + " " + friend.getLastName() + " is not your friend!";
		}

	}

	@Override
	public PersonalWall reviewFriendWall(String name, String lastName) throws Exception {
		User searchedUser = this.searchUser(name, lastName);
		if ((searchedUser != null) && (this.friends.containsKey(searchedUser.getEmail()))) {
			return searchedUser.getPersonalWall();
		} else
			throw new UserException("Invalid names. You do not have this friend.");
	}

	public CommonWall getCommonWall() {
		return commonWall;
	}

	@Override
	public void deleteProfile(String password, String email) throws UserStatusException, UserException {

		// Validation

		if (password.equals(this.password) && email.equals(this.email)) {
			this.status.removeUser(email);
			// We need to set all references of this user to null(set this user
			// to null in his friends, friend's list)
			
			new UserDAO().deleteUser(this);

			
		} else {
			System.out.println("Invalid password or email. The profile hasn't been deleted");
		}

	}

	boolean isStringValid(String string) {
		return ((string != null) && (string.trim().length() > 0));
	}

	@Override
	public void logout() {
		System.out.println(this.firstName + " " + this.lastName + "logged out");

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
		if (this.hasThisFriend(friend)) {
			Message message1 = this.getChatbyUser(friend).addMessage(message);
			new ChatDAO().addNewMessage(friend.getChatbyUser(this), this.getUserID(), message1);
			
		} else {
			throw new UserException("Invalid friend! ");
		}

	}

	private Chat getChatbyUser(User friend) throws UserException {
		if (this.chats.containsKey(friend)) {
			return this.chats.get(friend);
		} else {
			throw new UserException("Invalid friend! ");
		}
	}

	@Override
	public void reviewChat(User friend) throws UserException {
		this.getChatbyUser(friend).printChat();
	}

	public PersonalWall getPersonalWall() {

		return this.personalWall;
	}

	// GERI: dobavqm metod:
	public User searchUser(String name, String lastName) throws UserException {
		if ((isStringValid(name)) && (isStringValid(lastName))) {
			for (String key : status.getAllUsers().keySet()) {
				if ((status.getAllUsers().get(key).getFirstName().equals(name))
						&& (status.getAllUsers().get(key).getLastName().equals(lastName))) {
					String email = status.getAllUsers().get(key).getEmail();
					return status.getAllUsers().get(email);
				}
			}
		}
		throw new UserException("There's no user with this name!");
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getBirthdayDate() {
		return birthdayDate;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}
}
