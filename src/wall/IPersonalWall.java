package wall;

import wall.exceptions.WallException;

public interface IPersonalWall {

	void addPost(Post post) throws WallException;

	void removePost(Post post) throws WallException;

}