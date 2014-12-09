package models;

import java.util.Observable;

import assets.CommunicationHelper;
import exceptions.InvalidActionInCommandException;
import exceptions.InvalidHeadingException;

/**
 * MarsRover is a logical representation of a NASA MarsRover. It executes
 * commands in the form of Strings that are sent from a owning class.
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 * 
 */
public class MarsRover extends Observable {

	private int currentPositionX; // Tracks the current position at the X-axis of
																// the plateau.
	private int currentPositionY; // Tracks the current position at the Y-axis of
																// the plateau.
	private int currentHeading; // Tracks which direction the rover is currently
															// heading
	private Plateau plateau; // The plateau where the rover "lives"

	public MarsRover(int startPositionX, int startPositionY, char heading,
			Plateau plateau) throws InvalidHeadingException {
		super();
		setPositionX(startPositionX);
		setPositionY(startPositionY);
		setHeading(heading);
		this.plateau = plateau;
	}

	/**
	 * 
	 * @return the X-position in the plateau.
	 */
	public int getPositionX() {
		return currentPositionX;
	}

	/**
	 * 
	 * @param newPositionX
	 *          - The X position currentPositonX should be updated to.
	 */
	public void setPositionX(int newPositionX) {
		currentPositionX = newPositionX;
	}

	/**
	 * 
	 * @return the Y-position in the plateau.
	 */
	public int getPositionY() {
		return currentPositionY;
	}

	/**
	 * 
	 * @param newPositionY
	 *          - The Y position currentPositonX should be updated to.
	 */
	public void setPositionY(int newPositionY) {
		currentPositionY = newPositionY;
	}

	/**
	 * 
	 * @return The heading as a char ('E', 'N', 'W', 'S')
	 * @throws InvalidHeadingException
	 *           - If the currentHeading can't be encoded to one of the valid
	 *           char-values
	 */
	public char getHeading() throws InvalidHeadingException {
		return CommunicationHelper.encodeHeadingAsChar(currentHeading);
	}

	/**
	 * 
	 * @param newHeading
	 *          - The new heading currentHeading should be updated to.
	 * @throws InvalidHeadingException
	 *           - If the heading can't be encoded to one of the valid
	 *           char-values.
	 */
	public void setHeading(char newHeading) throws InvalidHeadingException {
		currentHeading = CommunicationHelper.decodeHeadingToDegrees(newHeading);
	}

	/**
	 * Changes the heading 90 degrees to the left.
	 */
	public void turnLeft() {
		switch (currentHeading) {
		case 0:
			this.currentHeading = 90;
			break;
		case 90:
			this.currentHeading = 180;
			break;
		case 180:
			this.currentHeading = 270;
			break;
		case 270:
			this.currentHeading = 0;
			break;
		}

	}

	/**
	 * Changes the heading 90 degrees to the right.
	 */
	public void turnRight() {
		switch (currentHeading) {
		case 0:
			this.currentHeading = 270;
			break;
		case 90:
			this.currentHeading = 0;
			break;
		case 180:
			this.currentHeading = 90;
			break;
		case 270:
			this.currentHeading = 180;
			break;
		}

	}

	/**
	 * Moves 1 step in the heading direction.
	 */
	public void move() {
		switch (currentHeading) {
		case 0:
			currentPositionX = currentPositionX + 1;
			break;
		case 90:
			currentPositionY = currentPositionY + 1;
			break;
		case 180:
			currentPositionX = currentPositionX - 1;
			break;
		case 270:
			currentPositionY = currentPositionY - 1;
			break;
		}
	}

	/**
	 * 
	 * @param command
	 *          - The command to execute
	 * @throws InvalidActionInCommandException
	 *           - If there are actions in the command which are not supported by
	 *           the rover.
	 * @throws InvalidHeadingException
	 *           - If for any reason one of the commands would trigger this
	 *           exception.
	 */
	public void executeCommand(String command)
			throws InvalidActionInCommandException, InvalidHeadingException {
		clearChanged();

		for (int i = 0; i < command.length(); i++) {
			char action = command.charAt(i);

			// validateActionInCommand(action);
			executeActionInCommand(action);
			setChanged();
			if (!plateau.pointWithinPlateau(getPositionX(), getPositionY())) {
				notifyObservers("Mayday, mayday! I drove off the plateau, self-imploding in 10 seconds...");
				break;
			}
		}
		notifyObservers(getPositionAndLocation());
	}

	/**
	 * 
	 * @param actionInCommand
	 *          - A one letter command to execute.
	 * @return true if an command is executed
	 * @throws InvalidActionInCommandException
	 *           - If @param actionInCommand is not 'L', 'R' or 'M'
	 */
	private boolean executeActionInCommand(char actionInCommand)
			throws InvalidActionInCommandException {
		boolean executed;
		switch (actionInCommand) {
		case 'L':
			turnLeft();
			executed = true;
			break;
		case 'R':
			turnRight();
			executed = true;
			break;
		case 'M':
			move();
			executed = true;
			break;
		default:
			throw new InvalidActionInCommandException(String.valueOf(actionInCommand));
		}
		return executed;
	}

	/**
	 * 
	 * @return A String that represents the rover's position within the plateau as
	 *         well as the heading.
	 * @throws InvalidHeadingException
	 *           - If the heading cannot be encoded properly.
	 */
	public String getPositionAndLocation() throws InvalidHeadingException {
		String positionAndLocation = String.valueOf(currentPositionX) + " "
				+ String.valueOf(currentPositionY) + " "
				+ CommunicationHelper.encodeHeadingAsChar(this.currentHeading);
		return positionAndLocation;
	}
}
