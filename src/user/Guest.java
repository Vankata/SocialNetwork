package user;

import user.exceptions.InvalidInfoForRegistrationException;
import user.exceptions.UserException;
import user.exceptions.WrongEmailException;
import user.exceptions.WrongPasswordException;

public class Guest {
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	private static final int MIN_LENGTH_FOR_PASSWORD = 6;
	// static- status obsht za vsichki
	private static UserStatus status = new UserStatus();

	// Sign in
	public void signIn(String email, String password, String firstName, String lastName)
			throws InvalidInfoForRegistrationException, UserException {
		if ((isStringValid(email)) && (isEmailValid(email))) {
			if (!status.containsUser(email)) {
				if (isPasswordValid(password)) {
					if ((isStringValid(firstName)) && (isStringValid(lastName))) {
						System.out.println("You sign in successfuly!");
						User user = new User(password, email, firstName, lastName, status);

						status.addUser(user);
					} else
						throw new InvalidInfoForRegistrationException("You entered invalid name!Try again!");
				} else
					throw new InvalidInfoForRegistrationException(
							"You entered invalid password." + " Enter a password with more than 5 symbols,"
									+ " which contains small case, upper case and digit at least once!");
			} else
				throw new InvalidInfoForRegistrationException("An user with this email already exists!");
		} else
			throw new InvalidInfoForRegistrationException("You entered invalid email! Try again!");
	}

	// logvane na potrebitel-> pri pravilno vyvedeni danni vryshta obekta User
	public User logIn(String email, String password) throws WrongPasswordException, WrongEmailException {
		if (status.containsUser(email)) {
			if (status.getAllUsers().get(email).chekPassword(password)) {
				return status.getAllUsers().get(email);
			} else
				throw new WrongPasswordException("You entered wrong password! Try again!");
		} else
			throw new WrongEmailException("You entered invalid email! Try again!");
	}

	static boolean isEmailValid(String email) {
		return email.matches(EMAIL_REGEX);
	}

	static boolean isStringValid(String string) {
		return ((string != null) && (string.trim().length() > 0));
	}

	// 1 malka bukva, 1 glavna bukva, 1 cifra, dyljina-6
	static boolean isPasswordValid(String password) {
		boolean hasSmallCase = false, hasUpperCase = false, hasDigit = false;
		if (password.length() >= MIN_LENGTH_FOR_PASSWORD) {
			for (int symbol = 0; symbol < password.length(); symbol++) {
				if ((password.charAt(symbol) >= 'a') && (password.charAt(symbol) <= 'z')) {
					hasSmallCase = true;
				}
				if ((password.charAt(symbol) >= 'A') && (password.charAt(symbol) <= 'Z')) {
					hasUpperCase = true;
				}
				if ((password.charAt(symbol) >= '0') && (password.charAt(symbol) <= '9')) {
					hasDigit = true;
				}
			}
		}
		return hasDigit && hasSmallCase && hasUpperCase;
	}
}
