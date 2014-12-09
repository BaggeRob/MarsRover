package exceptions;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class InvalidCommandException extends Exception {
	private static String message = "The command is invalid";

	public InvalidCommandException() {
		super(message);
	}
}
