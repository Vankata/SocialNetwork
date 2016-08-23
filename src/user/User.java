package user;

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
import chat.exceptions.ChatBoxException;
import chat.exceptions.ChatException;
import chat.exceptions.MessageException;
import user.exceptions.UserException;
import wall.CommonWall;
import wall.IPersonalWall;
import wall.PersonalWall;
import wall.Photo;
import wall.Post;
import wall.exceptions.WallException;

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
	private Map<User, Chat> chats = new TreeMap<User, Chat>(
			(user1, user2) -> user1.getEmail().compareTo(user2.getEmail()));
	private PersonalWall personalWall;
	private CommonWall commonWall;
	private Photo profilePicture;

	// ----------------------------------
	private UserStatus userStatus;

	public User(String password, String email, String firstName, String lastName, UserStatus userStatus)
			throws UserException {

		this.setPassword(password);
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setLastName(lastName);

		this.friends = new HashMap<String, User>();
		this.personalWall = new PersonalWall();
		this.commonWall = new CommonWall();

	}

	boolean chekPassword(String password){
		return this.password.equals(password);
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

	public void addNewChat(User friend, Chat newChat) throws UserException {

		if (newChat != null) {
			this.chats.put(friend, newChat);
		} else {
			throw new UserException("Invalid chat! ");
		}
	}

	
	// Should be private and not in the interface/
	// friendsWithThisName() will be in the interface and the friend will be
	// find by name
	private void likePost(User friend, Post post) { // increment the number likes
													// for this post

		/*
		 * Namirame post v postovete na uzyra i mi inkrementirame broq na
		 * laikovete. dobavqme imeto na uzyra koito haresva post v imenata na
		 * horata koito sa go haresali postovete v klas stena sa treeset i nama
		 * random dostyp to tqh. Moje bi trqbwa da gi pazim v arraylist
		 */

	}
	@Override
	public void likeFriendsPost(String friendName, Post post) throws UserException {

		if (post == null /*
							 * validacia za imeto. She izpolzvame metoda za
							 * validaciq na string ot Geri
							 */) {
			throw new UserException("You are trying  to like a post with invalid friend name or post");
		}

		List<User> friendsWithThisName = new ArrayList<User>();

		// Finds all friends that have friendName among their first or last
		// names and adds them in the friendsWithThisName Set
		for (Entry<String, User> entry : friends.entrySet()) {
			if (entry.getValue().getFirstName().equals(friendName)
					|| entry.getValue().getLastName().equals(friendName)) {
				friendsWithThisName.add(entry.getValue());
			}
		}
		// ---------------------------------------------------------------------

		if (friendsWithThisName.size() == 0) {
			// Moje i da ne hvyrlq exception a samo da izkarkva nadpis. Za sega
			// go ostawqm taka za testovi celi
			throw new UserException("U does not have friend with name " + friendName);
		}

		// Multiple matches
		if (friendsWithThisName.size() > 1) {

			for (User friend : friendsWithThisName) {
				System.out.println(friend.getFirstName() + " " + friend.getLastName());
			}
			throw new UserException("U have more than one frined with this name. Please be more Specific");
		}

		// Get index 0, cuz we are sure that only one obj is in the arraylist
		this.likePost(friendsWithThisName.get(0), post);

	}

	@Override
	public void commentPost(User user, Post post, String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePost(Post post) throws UserException {
		if (post == null) {
			throw new UserException("Invalid Post to delete!");
		}

	}

	@Override
	public void reportPost(User friend, Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPost(Post post) throws UserException {
		// if (post == null) {
		// throw new UserException("Invalid post to add");
		// }

		try {
			this.personalWall.addPost(post);
		} catch (WallException e) {
			// TODO Auto-generated catch block
			throw new UserException(e.getMessage(), e);
		}

	}

	@Override
	public void addFriend(User user) throws UserException, ChatBoxException {
		// TODO Auto-generated method stub
		// VANKATA: promenqm malko, realiziram chata2
		if (!this.friends.containsValue(user)) {
			this.friends.put(user.getEmail(), user);
		}
		if (!user.hasThisFriend(this)) {
			user.addFriend(this);
		}
		Chat chat = new Chat();
		this.addNewChat(user, chat);
		user.addNewChat(this, chat);
	}

	// VANKATA dobavqm tozi metod
	//Tyrsim po obekt ot tip user
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

		// Validation

		if (password.equals(this.password) && email.equals(this.email)) {
			this.userStatus.removeUser(this);
			// We need to set all references of this user to null(set this user
			// to null in his friends, friend's list)
		} else {
			System.out.println("Invalid password or email. The profile hasn't been deleted");
		}

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
			this.getChatbyUser(friend).addMessage(message);
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

}
