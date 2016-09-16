package wall;

import user.User;
import wall.exceptions.PhotoException;
import wall.exceptions.PostException;

public class Photo extends Post {
	
	private String pathToThePhoto;

	public Photo(String text, String pathToThePhoto, User ownerOfThePhoto ) throws PostException, PhotoException {
		super(text, ownerOfThePhoto);
	
		if(pathToThePhoto != null ){
			this.pathToThePhoto = pathToThePhoto;
		}else{
			throw new PhotoException("Invalid photo! ");
		}
	}

	//Only for test
	@Override
	public String toString() {
		System.out.println(super.toString());
		return "Photo [pathToThePhoto=" + pathToThePhoto + "]";
	}
	//-----------------------
	

}
