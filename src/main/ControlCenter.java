package main;

import java.util.HashMap;

import view.NASAMonitor;

import models.Plateau;
import models.MarsRover;
import exceptions.InvalidActionInCommandException;
import exceptions.InvalidCommandException;
import exceptions.InvalidHeadingException;

/**
 * A class that represents a control station for NASA. This class is above all
 * used to execute commands and connect the MarsRovers with the views where
 * results of commands should be shown.
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 * 
 */
public class ControlCenter {

	NASAMonitor nasaMonitor; //A monitor where results should shown.

	public ControlCenter() {
		super();
		nasaMonitor = new NASAMonitor();

	}

	/**
	 * Executes a command as well as checks if it a valid command to execute.
	 * 
	 * A command should be on the form
	 * 
	 * (line 1) int int 						- Size of plateau
	 * (line 2) int int char				- position of rover one, heading of rover one
	 * (line 3) charcharcharchar..	- command to execute for rover one
	 * (line 4) int int char				- position of rover two, heading of rover two
	 * (line 5) charcharcharchar..	- command to execute for rover one
	 * (line 6 - line #)						- More rovers can be added. Minimum number of rovers = 1, maximum = unlimited.
	 * 
	 * @param command - The command to execute
	 * @throws InvalidCommandException - if the command is Invalid.
	 */
	public void executeCommand(String command) throws InvalidCommandException {

		String[] commandsToExecute;
		if (command != null) {
			commandsToExecute = command.split(String.format("%n")); //Splits the command on carriage-return.
			Plateau plateau = null;
			HashMap<Integer, MarsRover> rovers = new HashMap<Integer, MarsRover>();
			
			// Loops through the different commands(That are already split on carriage-return)
			for (int i = 0; i < commandsToExecute.length; i++) {
				if (i == 0) {
					String[] plateauSize = commandsToExecute[0].split("\\s+"); //Takes the first row of the command and splits it on whitespace
					
					// Creates a new plateau if the command can be used to do so.
					if (validatePlateauCommand(plateauSize)) {
						int sizeX = Integer.parseInt(plateauSize[0]);
						int sizeY = Integer.parseInt(plateauSize[1]);
						plateau = new Plateau(sizeX, sizeY);
					} else {
						throw new InvalidCommandException();
					}

				} else if (i % 2 == 1) { // If the row is uneven the command corresponds to a create rover-command
					
					String[] roverBasePosition = commandsToExecute[i].split("\\s+"); //Takes the row of the command and splits it on whitespace
					
					// Creates a new rover if the command can be used to do so.
					if (validateRoverBasePosition(roverBasePosition)) {
						
						int posX = Integer.parseInt(roverBasePosition[0]);
						int posY = Integer.parseInt(roverBasePosition[1]);
						char heading = roverBasePosition[2].charAt(0);
						
						try {
							MarsRover rover = new MarsRover(posX, posY, heading, plateau);
							rover.addObserver(nasaMonitor); // Adds the monitor as a observer
																							//so that it responds to the actions of the rover.
							rovers.put(i, rover);
						} catch (InvalidHeadingException e) {
							throw new InvalidCommandException();
						}

					} else {
						throw new InvalidCommandException();
					}
				} else { // If the row is even and not 0 the command correspond to a command that should be executed by a rover.
					int roverToCommand = i - 1; // The rover that the command corresponds to is the one that was just created.
					MarsRover rover = rovers.get(roverToCommand);

					String commandForRover = commandsToExecute[i].replaceAll("\\s+", ""); //Removes all the whitespaces from the command.
					try {
						rover.executeCommand(commandForRover);
					} catch (InvalidActionInCommandException e) {
						throw new InvalidCommandException();
					} catch (InvalidHeadingException e) {
						throw new InvalidCommandException();
					}
				}
			}

		}

	}
	
	/*
	 * Validates if a String can be used to create a rover.
	 * A valid string should be on the form int int char
	 */
	private boolean validateRoverBasePosition(String[] roverBasePosition) {
		boolean valid = false;

		if (roverBasePosition.length == 3) {

			try {
				int positionX = Integer.parseInt(roverBasePosition[0]);
				int positionY = Integer.parseInt(roverBasePosition[1]);

				if (positionX > 0 && positionY > 0) {
					valid = true;
				}
			} catch (NumberFormatException e) {
				valid = false;
			}
		}

		return valid;
	}

	/*
	 * Validates if a String can be used to create a plateau.
	 * A valid string should be on the form int int
	 */
	private boolean validatePlateauCommand(String[] plateauSize) {
		boolean valid = false;

		if (plateauSize.length == 2) {
			try {
				int sizeX = Integer.parseInt(plateauSize[0]);
				int sizeY = Integer.parseInt(plateauSize[1]);

				if (sizeX > 0 && sizeY > 0) {
					valid = true;
				}
			} catch (NumberFormatException e) {
				valid = false;
			}
		}

		return valid;
	}
}
