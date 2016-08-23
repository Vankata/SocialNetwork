package wall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import wall.exceptions.WallException;

public class PersonalWall implements IPersonalWall {

	private Queue<Post> posts = new LinkedList<Post>();

	public PersonalWall() {
		
	}

	@Override
	public void addPost(Post post) throws WallException {
		if (post != null) {
			this.posts.offer(post);
		} else {
			throw new WallException("Invalid post! ");
		}
	}

	@Override
	public void removePost(Post post) throws WallException {
		if (post != null && this.posts.contains(post)) {
			this.posts.remove(post);
		} else {
			throw new WallException("Invalid post! ");
		}
	}
	
	
	public Post getPostFromTheWall(Post postToGet) throws WallException{
		if (postToGet == null) {
			throw new WallException("Invalid post");
		}
		ArrayList<Post> postsOnTheWall = new ArrayList<Post>(this.posts);
		int postIndex = postsOnTheWall.indexOf(postToGet);
		
		if (postIndex < 0) {
			throw new WallException("No such post on the wall");
		}
		Post result = postsOnTheWall.get(postIndex);
		
		return result;
	}
}
