package main;

import java.time.LocalDateTime;
import java.util.List;

import user.Admin;
import user.Guest;
import user.User;
import user.UserStatus;
import wall.Photo;
import wall.Post;
import wall.exceptions.PostException;

public class Demo {

	public static void main(String[] args) {
		
		try {
		
			
//GERI: da napravim 1 sistema i 1 admin, za da bydat sinhroniziani operaciite!
			Admin admin=new Admin("hfluF77", "admin@abv.bg", "Admin", "Adminov", Guest.getStatus());
			Guest geri= new Guest();
			geri.signIn("geri@abv.bg", "Geri777", "Geri", "Petrova");
			User geriUser=geri.logIn("geri@abv.bg", "Geri777");
			Guest meri= new Guest();
			meri.signIn("meri@abv.bg", "Meri777", "Meri", "Petrova");
			User meriUser=geri.logIn("meri@abv.bg", "Meri777");
			meriUser.postPicture("blblq", "dnes sym qdosana.");
			meriUser.postPicture("nova snimka", "dnes sym shtastliva.");
			List<Post> postsMeri=meriUser.getPersonalWall().getPosts();
				for(Post p: postsMeri){
				System.out.println("Post na meri: ");
				System.out.println(p);
				}
			System.out.println("Az sym Meri i tyrsq tozi profil: "+meriUser.searchUser("Geri", "Petrova"));
//			System.out.println("Az sym Meri i tyrsq tozi profil: "+meriUser.searchUser("Peri", "Petrova"));
			System.out.println(Guest.getStatus().getAllUsers());
			admin.removeUser("geri@abv.bg");
//			Guest.getStatus().removeUser("geri@abv.bg");
			System.out.println(Guest.getStatus().getAllUsers());

//			
//			User vankata = new User("abcd1234", "vancyki@abv.bg", "Ivan", "Kolev", system);
//			User troqn = new User("1234abcd", "bojinkata@abv.bg", "Bojin", "Penchev", system);
////			Post firstPost = new Post("kvo stava be",vankata);
//			System.out.println(firstPost);
//			Photo firstPhoto = new Photo("tova e pyrvata mi snimka", "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg",troqn);
//			System.out.println(firstPhoto);
//			vankata.addFriend(troqn);
//			vankata.sendMessage(troqn, "Mara we");
//			Thread.sleep(3000);
//			troqn.sendMessage(vankata, "kvo iskash be?");
//			Thread.sleep(3000);
//			vankata.sendMessage(troqn, "Tihol, che Spico spi!!!!!");
//			troqn.reviewChat(vankata);
//			vankata.reviewChat(troqn);
		

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
