package main;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import user.Admin;
import user.Guest;
import user.User;
import user.UserStatus;
import user.exceptions.InvalidInfoForRegistrationException;
import user.exceptions.UserException;
import user.exceptions.UserStatusException;
import user.exceptions.WrongEmailException;
import user.exceptions.WrongPasswordException;
import wall.Photo;
import wall.Post;
import wall.exceptions.PostException;

public class Demo {

	public static void main(String[] args) throws Exception {

	//	try {

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

//			System.out.println("TESTING SIGNIN, LOGIN AND ADDING A FRIEND");
//			
//			Guest vankata = new Guest();
//			vankata.signIn("vancyki@abv.bg", "Abcde1234", "Ivan", "Kolev");
//			User vankataUser = vankata.logIn("vancyki@abv.bg", "Abcde1234");
//			Guest troqn = new Guest();
//			troqn.signIn("bojinkata@abv.bg", "Meri777", "Boqn", "Enchev");
//			User troqnUser = troqn.logIn("bojinkata@abv.bg", "Meri777");
//			troqnUser.addFriend(vankataUser);
//			
//			System.out.println("TESTING SIGNIN, LOGIN AND ADDING A FRIEND END \n");
//
//			System.out.println("TESTING CHAT");
//			
//			troqnUser.sendMessage(vankataUser, "TROQN: sup mofucka");
//			vankataUser.sendMessage(troqnUser, "VANKATA: nishto be");
//			troqnUser.sendMessage(vankataUser, "TROQN: hahahaah");
//			troqnUser.reviewChat(vankataUser);
//			// troqnUser.reviewChat(troqnUser);
//			vankataUser.reviewChat(troqnUser);
//			
//			System.out.println("TESTING CHAT END\n");
//			
//			System.out.println("TESTING POST CREATION AND POST REVIEW");
//			
//			Post tempPost1 = vankataUser.postPicture("path.to.photo", "dnes sym qdosana.");
//			Post tempPost2 = vankataUser.postPicture("path.to.photo", "dnes sym shtastliva.");
//			List<Post> postsVankata = vankataUser.getPersonalWall().getPosts();
//			for (Post post : postsVankata) {
//				System.out.println("Post na vankata: ");
//				System.out.println(post);
//			}
//			
//			System.out.println("TESTING POST CREATION AND POST REVIEW END\n");
//			
//			System.out.println("TESTING LIKE AND UNLIKE POST");
//			
//			troqnUser.likePost(tempPost1);
//			vankataUser.likePost(tempPost1);
//			//troqnUser.likePost(tempPost1);
//			for (Post post : postsVankata) {
//				System.out.println("Post na vankata: ");
//				System.out.println(post);
//				System.out.println("Names of people who liked it: ");
//				for (int index = 0; index < post.namesOfFriendsLikedThePost().length; index++) {
//					System.out.println(post.namesOfFriendsLikedThePost()[index]);
//				}
//			}
//			
//			System.out.println("TESTING LIKE AND UNLIKE POST END\n");
//			
//			System.out.println("TESTING COMMENT POST");
//			
//			troqnUser.commentPost(tempPost1, "mnogo si debel ve, vanka, otslabvai vednaga");
//			vankataUser.commentPost(tempPost1, "gtfo");
//			for (Post post : postsVankata) {
//				System.out.println("Post na vankata: ");
//				System.out.println(post);
//				System.out.println("Names of people who commented it: ");
//				for (int index = 0; index < post.namesOfFriendsCommentedThePost().length; index++) {
//					System.out.println(post.namesOfFriendsCommentedThePost()[index]);
//				}
//				System.out.println("Comments on the post: ");
//				for (int index = 0; index < post.commentsOnThePost().length; index++) {
//					System.out.println(post.commentsOnThePost()[index]);
//				}
//			}
//			
//			System.out.println("TESTING COMMENT POST END\n");
//				
//			System.out.println("TESTING DELETING POST");
//			
//			//troqnUser.deletePost(tempPost1); - bachka, troqn ne moje da iztriq posta na vankata
//			vankataUser.deletePost(tempPost1); 
//			//raboti no dobavqm nov metod za proverkata v commonwall da ne trie post ako 
//			//ne se sydyrja v stenata, zashtoto ako stanat mnogo postove tam te avtomatiichno
//			//se triqt i v kraina smetka ako se opitame da iztriem star post shte ima exception
//			System.out.println("Posts of vankata after deleting first post: ");
//			for (Post post : postsVankata) {
//				System.out.println("Post na vankata: ");
//				System.out.println(post);
//			}
//			
//			System.out.println("TESTING DELETING POST END\n");
//			
//			System.out.println("TESTING DELETING A FRIEND");
//				
//			System.out.println("Before vankata deletes troqn as a friend: ");
//			System.out.println(vankataUser.hasThisFriend(troqnUser));
//			System.out.println("After vankata deletes troqn as a friend: ");
//			vankataUser.removeFirend(troqnUser);
//			System.out.println(vankataUser.hasThisFriend(troqnUser));
//			vankataUser.addFriend(troqnUser);
//			
//			System.out.println("TESTING DELETING A FRIEND END\n");
//			
//			System.out.println("TESTING REVIEW A FRIEND");
//			
//			troqnUser.setBirthdayDate(LocalDate.of(1997, 5, 25));
//			System.out.println("Vankata reviewing troqn's info: ");
//			System.out.println(vankataUser.reviewFriendInfo(troqnUser));
//			System.out.println("Vankata trying to review it's own info: ");
//			System.out.println(vankataUser.reviewFriendInfo(vankataUser));
//			
//			System.out.println("TESTING REVIEW A FRIEND END\n");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
		
		
		
		Guest vankata = new Guest();
		vankata.signIn("vancyki@abv.bg", "Abcde1234", "Ivan", "Kolev");
		User vankataUser = vankata.logIn("vancyki@abv.bg", "Abcde1234");
		Guest vesko = new Guest();
		vesko.signIn("veskoloshiq@abv.bg", "123dasDWJDO", "Veskoi", "Loshiq");
		User veskoUSer = vesko.logIn("veskoloshiq@abv.bg", "123dasDWJDO");
		veskoUSer.addFriend(vankataUser);
		//vankataUser.deleteProfile("Abcde1234", "vancyki@abv.bg");
		vankataUser.setPhoneNumber("0878788032");
		vankataUser.setBirthdayDate(LocalDate.now());
		
		
		
	}

}
