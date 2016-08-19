package wall;

import java.util.Set;
import java.util.TreeSet;

import wall.exceptions.WallException;

public abstract class Wall {
	
	private Set<Post> posts = new TreeSet<Post>();
	
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
