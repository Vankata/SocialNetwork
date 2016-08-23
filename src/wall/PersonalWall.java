package wall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import wall.exceptions.WallException;

public class PersonalWall extends Wall {

	public Post getPostFromTheWall(Post postToGet) throws WallException {
		if (postToGet == null) {
			throw new WallException("Invalid post");
		}
		// We are using  arraylist because we need random access and the post is Likedlist with slow random access
		ArrayList<Post> postsOnTheWall = new ArrayList<Post>(this.posts);
		int postIndex = postsOnTheWall.indexOf(postToGet);

		if (postIndex < 0) {
			throw new WallException("No such post on the wall");
		}
		Post result = postsOnTheWall.get(postIndex);

		return result;
	}
}
