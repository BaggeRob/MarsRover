package assets;

import exceptions.InvalidHeadingException;

/**
 * A class that defines common rules for the MarsRover and the NASA -
 * technicians.
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class CommunicationHelper {

	/**
	 * Takes a char-parameter and decodes it to degrees on the unit-circle
	 * 
	 * @param heading
	 *          - The char that should be decoded
	 * @return A degree-value on the unit-circle
	 * @throws InvalidHeadingException
	 *           - If the heading is not one of the supported values 'E', 'N', 'W'
	 *           or 'S'
	 */
	public static int decodeHeadingToDegrees(char heading)
			throws InvalidHeadingException {
		int headingInDegrees = -1;

		switch (heading) {
		case 'E':
			headingInDegrees = 0;
			break;
		case 'N':
			headingInDegrees = 90;
			break;
		case 'W':
			headingInDegrees = 180;
			break;
		case 'S':
			headingInDegrees = 270;
			break;
		default:
			throw new InvalidHeadingException();
		}
		return headingInDegrees;
	}

	/**
	 * 
	 * @param heading
	 *          - A degree-value that should be encoded to a character.
	 * @return A char that represents one of the directions East(E), North(N),
	 *         West(W) or South(S)
	 * @throws InvalidHeadingException
	 *           - If the heading value cannot be encoded to one of the valid
	 *           directions('E', 'N', 'W' or 'S')
	 */
	public static char encodeHeadingAsChar(int heading)
			throws InvalidHeadingException {
		char headingAsChar;
		switch (heading) {
		case 0:
			headingAsChar = 'E';
			break;
		case 90:
			headingAsChar = 'N';
			break;
		case 180:
			headingAsChar = 'W';
			break;
		case 270:
			headingAsChar = 'S';
			break;
		default:
			throw new InvalidHeadingException();
		}
		return headingAsChar;
	}

}
