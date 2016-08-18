package main;

import java.time.LocalDateTime;

import wall.Photo;
import wall.Post;
import wall.exceptions.PostException;

public class Demo {

	public static void main(String[] args) {
		
		try {
			Post firstPost = new Post("kvo stava be");
			System.out.println(firstPost);
			Photo firstPhoto = new Photo("tova e pyrvata mi snimka", "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg");
			System.out.println(firstPhoto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
