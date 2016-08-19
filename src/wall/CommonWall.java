package wall;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.print.attribute.standard.RequestingUserName;

import org.omg.CORBA.PolicyListHelper;

import user.User;
import user.UserStatus;
import wall.exceptions.WallException;

public class CommonWall extends Wall {

	private static final int CAPACITY_POSTS = 30;
	private Queue<Post> posts = new LinkedList<Post>();

	private void update() {
		if(this.posts.size() > CAPACITY_POSTS){
			this.posts.poll();
		}
	}

	public void addPost(Post post) throws WallException {
		if (post != null) {
			this.posts.add(post);
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

}
