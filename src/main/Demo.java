package main;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

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

			/*
			 * //GERI: da napravim 1 sistema i 1 admin, za da bydat
			 * sinhroniziani operaciite! Admin admin=new Admin("hfluF77",
			 * "admin@abv.bg", "Admin", "Adminov", Guest.getStatus()); Guest
			 * geri= new Guest(); geri.signIn("geri@abv.bg", "Geri777", "Geri",
			 * "Petrova"); User geriUser=geri.logIn("geri@abv.bg", "Geri777");
			 * Guest meri= new Guest(); meri.signIn("meri@abv.bg", "Meri777",
			 * "Meri", "Petrova"); User meriUser=geri.logIn("meri@abv.bg",
			 * "Meri777"); meriUser.postPicture("blblq", "dnes sym qdosana.");
			 * meriUser.postPicture("nova snimka", "dnes sym shtastliva.");
			 * List<Post> postsMeri=meriUser.getPersonalWall().getPosts();
			 * for(Post p: postsMeri){ System.out.println("Post na meri: ");
			 * System.out.println(p); }
			 * System.out.println("Az sym Meri i tyrsq tozi profil: "+meriUser.
			 * searchUser("Geri", "Petrova")); //
			 * System.out.println("Az sym Meri i tyrsq tozi profil: "+meriUser.
			 * searchUser("Peri", "Petrova"));
			 * System.out.println(Guest.getStatus().getAllUsers());
			 * admin.removeUser("geri@abv.bg"); //
			 * Guest.getStatus().removeUser("geri@abv.bg");
			 * System.out.println(Guest.getStatus().getAllUsers());
			 */

			Guest vankata = new Guest();
			vankata.signIn("vancyki@abv.bg", "Abcde1234", "Ivan", "Kolev");
			User vankataUser = vankata.logIn("vancyki@abv.bg", "Abcde1234");
			Guest troqn = new Guest();
			troqn.signIn("bojinkata@abv.bg", "Meri777", "Boqn", "Enchev");
			User troqnUser = troqn.logIn("bojinkata@abv.bg", "Meri777");
			troqnUser.addFriend(vankataUser);
			troqnUser.sendMessage(vankataUser, "TROQN: sup mofucka");
			vankataUser.sendMessage(troqnUser, "VANKATA: nishto be");
			troqnUser.sendMessage(vankataUser, "TROQN: hahahaah");

			// tesstvame chata

			troqnUser.reviewChat(vankataUser);
			// troqnUser.reviewChat(troqnUser);
			vankataUser.reviewChat(troqnUser);

			// testvame postovane na stenata

			Post tempPost1 = vankataUser.postPicture("path.to.photo", "dnes sym qdosana.");
			Post tempPost2 = vankataUser.postPicture("path.to.photo", "dnes sym shtastliva.");
			List<Post> postsVankata = vankataUser.getPersonalWall().getPosts();
			for (Post post : postsVankata) {
				System.out.println("Post na meri: ");
				System.out.println(post);
			}

			troqnUser.likePost(tempPost1);
			vankataUser.likePost(tempPost1);
			troqnUser.likePost(tempPost1);
			troqnUser.commentPost(tempPost1, "mnogo si debel ve, vanka, otslabvai vednaga");
			troqnUser.commentPost(tempPost1, "gtfo");
			//troqnUser.deletePost(tempPost2); - ne raboti
			
			for (Post post : postsVankata) {
				System.out.println("Post na meri: ");
				System.out.println(post);
				System.out.println(post.namesOfFriendsLikedThePost().length);
				for (int index = 0; index < post.namesOfFriendsLikedThePost().length; index++) {
					System.out.println(post.namesOfFriendsLikedThePost()[index]);
				}
				
				for (int index = 0; index < post.namesOfFriendsCommentedThePost().length; index++) {
					System.out.println(post.namesOfFriendsCommentedThePost()[index]);
				}
				
				for (int index = 0; index < post.commentsOnThePost().length; index++) {
					System.out.println(post.commentsOnThePost()[index]);
				}

			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
