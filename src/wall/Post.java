package wall;

import java.awt.image.VolatileImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wall.exceptions.PostException;

public class Post {
	
	private LocalDateTime timeOfThePost;
	private String text;
	private int numberOfLikes;
	private int numberOfComments;
	private Set<String> namesOfUsersLikedThisPost = new HashSet<String>();
	private Map<String, String> comments = new HashMap<String, String>();
	
	public Post(String text) throws PostException {
		
		if(text != null && text.trim().length() > 0){
			this.text = text;
		}else{
			throw new PostException("Invalid text! ");
		}
		
		this.timeOfThePost = LocalDateTime.now();
		this.numberOfLikes = 0;
		this.numberOfComments = 0;
	}
	
	public void addNameOfUserWhoLikedThisPost(String name){
		
		if(name != null && name.trim().length() > 0){
			this.namesOfUsersLikedThisPost.add(name);
		}
	}

	//Only for test
	@Override
	public String toString() {
		return "Post [timeOfThePost=" + timeOfThePost + ", text=" + text + ", numberOfLikes=" + numberOfLikes
				+ ", numberOfComments=" + numberOfComments + "]";
	}
	//----------------------
	
	public LocalDateTime getTimeOfThePost() {
		return timeOfThePost;
	}
	
	public void addLike(String nameOfPersonLikedIt) throws PostException{
		
		if (nameOfPersonLikedIt == null || nameOfPersonLikedIt.trim().length() == 0) {
			throw new PostException("Ivalid person that likes the post");
		}
		
		this.numberOfLikes++;
		namesOfUsersLikedThisPost.add(nameOfPersonLikedIt);
	}
	
	public void addComment(String nameOfPersonWhoCommentedIt, String comment) throws PostException{
		if (nameOfPersonWhoCommentedIt == null || nameOfPersonWhoCommentedIt.trim().length() == 0) {
			throw new PostException("Ivalid person that comments the post! ");
		}
		if (comment == null || comment.trim().length() == 0) {
			throw new PostException("Ivalid comment! ");
		}
		
		this.numberOfComments++;
		this.comments.put(nameOfPersonWhoCommentedIt, comment);
	}
}
