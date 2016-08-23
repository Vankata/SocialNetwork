package wall;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import user.User;
import wall.exceptions.PostException;

public class Post {

///	private static int postId = 0;
	private LocalDateTime timeOfThePost;
	private String text;
	private int numberOfLikes;
	private int numberOfComments;
//	private final int id;
	private User ownerOfThePost;
	private Set<String> namesOfUsersLikedThisPost = new HashSet<String>();

	// key->PostId value->comment
	 private List<Post> comments = new ArrayList<Post>();

	public Post(String text, User ownerOfThePost) throws PostException {

		if (ownerOfThePost == null) {
			throw new PostException("Invalid post owner");
		}

		this.ownerOfThePost = ownerOfThePost;

		if (text != null && text.trim().length() > 0) {
			this.text = text;
		} else {
			throw new PostException("Invalid text! ");
		}

		this.timeOfThePost = LocalDateTime.now();
		this.numberOfLikes = 0;
		this.numberOfComments = 0;
		//this.id = postId++;
	}

	public void addNameOfUserWhoLikedThisPost(String name) {

		if (name != null && name.trim().length() > 0) {
			this.namesOfUsersLikedThisPost.add(name);
		}
	}

	// Only for test
	@Override
	public String toString() {
		return "Post [timeOfThePost=" + timeOfThePost + ", text=" + text + ", numberOfLikes=" + numberOfLikes
				+ ", numberOfComments=" + numberOfComments + "]";
	}
	// ----------------------

	public LocalDateTime getTimeOfThePost() {
		return timeOfThePost;
	}

	public void addLike(String nameOfPersonLikedIt) throws PostException {

		if (nameOfPersonLikedIt == null || nameOfPersonLikedIt.trim().length() == 0) {
			throw new PostException("Ivalid person that likes the post");
		}

		this.numberOfLikes++;
		this.namesOfUsersLikedThisPost.add(nameOfPersonLikedIt);
	}

	public void addComment(User userThatLikeThePost, String comment) throws PostException {
		if (userThatLikeThePost == null ) {
			throw new PostException("Ivalid person that comments the post! ");
		}
		if (comment == null || comment.trim().length() == 0) {
			throw new PostException("Ivalid comment! ");
		}

		this.numberOfComments++;
		this.comments.add(new Post(comment,userThatLikeThePost));
	}
	
	public String[] namesOfFriendsLikedThePost() {
		
		String[] names = new String[this.comments.size()];
		int nameNumber = 0;
		
		for (Post comment : comments) {
			names[nameNumber++] = comment.ownerOfThePost.getFirstName() + " " + comment.ownerOfThePost.getLastName();
		}
		return names;
		
	}
	
}
