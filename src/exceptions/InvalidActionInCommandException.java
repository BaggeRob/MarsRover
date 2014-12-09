package exceptions;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09 
 */
public class InvalidActionInCommandException extends Exception {

	public InvalidActionInCommandException(String InvalidAction) {
		super("The specified action '" + InvalidAction
				+ "' is not one of the supported 'L', 'R' or 'M'.");
	}
}
