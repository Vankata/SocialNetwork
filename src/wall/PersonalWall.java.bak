package wall;

import java.util.Set;
import java.util.TreeSet;

import wall.exceptions.WallException;

public class PersonalWall implements IPersonalWall {

	private Set<Post> posts = new TreeSet<Post>();

	public PersonalWall() {
		
	}
	
	

	@Override
	public void addPost(Post post) throws WallException {
		if (post != null) {
			this.posts.add(post);
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
}
