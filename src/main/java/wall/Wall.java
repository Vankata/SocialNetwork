package wall;


import java.util.LinkedList;
import java.util.List;


import wall.exceptions.WallException;

public abstract class Wall {
	private static final int CAPACITY_POSTS = 20;
	protected  List<Post> posts = new LinkedList<Post>();

	protected void update() {
		if(this.posts.size() > CAPACITY_POSTS){
			this.posts.remove(posts.size() - 1);
		}
	}

	public void addPost(Post post) throws WallException {
		if (post != null) {
			this.posts.add(0,post);
			this.update();
		} else {
			throw new WallException("Invalid post! ");
		}
	}

	public void removePost(Post post) throws WallException {
		if (post != null && this.posts.contains(post)) {
			this.posts.remove(post);
		} else {
			throw new WallException("Invalid post! ");
		}
	}
//	GERI: dobavqm s cel testvane!

	public List<Post> getPosts() {
		return posts;
	}
	
}
