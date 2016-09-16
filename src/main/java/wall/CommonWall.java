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

	@Override
	protected void update() {

		if (this.posts.size() > CAPACITY_POSTS) {
			this.posts.remove(posts.size() - 1);

		}
	}
	
	public boolean containsPost(Post post){
		if(this.posts.contains(post)){
			return true;
		}
		return false;
	}

}
