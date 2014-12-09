package executable;

import exceptions.InvalidCommandException;
import main.ControlCenter;

/**
 * A class that runs the example data send in the Mars Rover problem description
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class RoverRunner {

	public static void main(String[] args) {

		ControlCenter center = new ControlCenter();

		String command = "5 5" + String.format("%n") + "1 2 N"
				+ String.format("%n") + "LMLMLMLMM" + String.format("%n") + "3 3 E"
				+ String.format("%n") + "MMRMMRMRRM" + String.format("%n");

		try {
			center.executeCommand(command);
		} catch (InvalidCommandException e) {
			System.out.println("Invalid Command");
			e.printStackTrace();
		}
	}

}
