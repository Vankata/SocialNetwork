package wall;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.print.attribute.standard.RequestingUserName;

import wall.exceptions.WallException;

public abstract class Wall {
	
	private Set<Post> posts = new TreeSet<Post>((o1, o2) -> o1.getTimeOfThePost().compareTo(o2.getTimeOfThePost()));
	//komperatori
	
	public Wall(){
		
	}
	
    public void addPost(Post post) throws WallException{
    	if(post != null){
    		this.posts.add(post);
    	}else{
    		throw new WallException("Invalid post! ");
    	}
	}
	
	public void removePost(Post post) throws WallException{
		if(post != null && this.posts.contains(post)){
			this.posts.remove(post);
		}else{
			throw new WallException("Invalid post! ");
		}
	}
}
