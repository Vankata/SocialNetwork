package wall;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import wall.exceptions.PostException;

public class Post {
	
	private LocalDateTime timeOfThePost;
	private String text;
	private int numberOfLikes;
	private int numberOfComments;
	private Set<String> namesOfUsersLikedThisPost = new HashSet<String>();
	private List<Post> comments = new LinkedList<Post>();
	
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
	
	public void addComment(Post newComment){
		if(newComment != null){
			this.comments.add(newComment);
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
}
