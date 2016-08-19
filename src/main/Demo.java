package main;

import java.time.LocalDateTime;

import user.User;
import user.UserStatus;
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
			
			UserStatus system = new UserStatus();
			User vankata = new User("abcd1234", "vancyki@abv.bg", "Ivan", "Kolev", system);
			User troqn = new User("1234abcd", "bojinkata@abv.bg", "Bojin", "Penchev", system);
			vankata.addFriend(troqn);
			vankata.sendMessage(troqn, "Mara we");
			Thread.sleep(3000);
			troqn.sendMessage(vankata, "kvo iskash be?");
			Thread.sleep(3000);
			vankata.sendMessage(troqn, "Tihol, che Spico spi!!!!!");
			
			troqn.reviewChat(vankata);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
