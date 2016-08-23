package wall;

import wall.exceptions.PhotoException;
import wall.exceptions.PostException;

public class Photo extends Post {
	
	private String pathToThePhoto;

	public Photo(String text, String pathToThePhoto) throws PostException, PhotoException {
		super(text);
	
		if(pathToThePhoto != null && pathToThePhoto.trim().length() > 0){
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
