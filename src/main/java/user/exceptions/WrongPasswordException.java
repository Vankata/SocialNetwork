package user.exceptions;

public class WrongPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5489297625857154378L;

	public WrongPasswordException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WrongPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public WrongPasswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WrongPasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WrongPasswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
