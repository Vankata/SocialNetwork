package Main;

import user.Guest;
import user.exception.InvalidInfoForRegistrationException;
import user.exception.WrongEmailException;
import user.exception.WrongPasswordException;

public class Demo {

	public static void main(String[] args) {
		Guest g= new Guest();
		Guest f= new Guest();

//		System.out.println(g.isEmailValid("barilska@abv.bh.coj"));
//		System.out.println(g.isPasswordValid("n6G"));
		try {
			g.signIn("barilska@abbv.bg", "ggggggG8gg", "jjj", "Barilska");
			g.logIn("barilska@abbv.bg", "ggggggG8gg");
		} catch (InvalidInfoForRegistrationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (WrongPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
