package exceptions;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class InvalidHeadingException extends Exception {
	private static String message = "The specified heading is not one of the suported 'E', 'N', 'W' or 'S'.";

	public InvalidHeadingException() {
		super(message);
	}

}
