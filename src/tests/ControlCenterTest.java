package tests;

import static org.junit.Assert.*;

import main.ControlCenter;

import org.junit.Test;

import exceptions.InvalidCommandException;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09 
 */
public class ControlCenterTest {
	private static String assertNoError = "No error should have been thrown";

	@Test
	public void testValidCommand() {
		ControlCenter center = new ControlCenter();
		String commandToExecute = "7 7" + String.format("%n") + "4 4 E"
				+ String.format("%n") + "MRMLM" + String.format("%n") + "3 2 E"
				+ String.format("%n") + "MRMLM";
		try {
			center.executeCommand(commandToExecute);
		} catch (InvalidCommandException e) {
			e.printStackTrace();
			fail(assertNoError);
		}
	}

	@Test
	public void testValidCommandBigNumbers() {
		ControlCenter center = new ControlCenter();
		String commandToExecute = "5000 5000" + String.format("%n") + "3000 2512 E"
				+ String.format("%n") + "MRMLM" + String.format("%n") + "4901 953 E"
				+ String.format("%n") + "MRMLM";
		try {
			center.executeCommand(commandToExecute);
		} catch (InvalidCommandException e) {
			e.printStackTrace();
			fail(assertNoError);
		}
	}

	@Test(expected = InvalidCommandException.class)
	public void testInvalidPlateauEmpty() throws InvalidCommandException {
		ControlCenter center = new ControlCenter();

		String commandToExecuteErrorEmpty = "" + String.format("%n") + "4 4 E"
				+ String.format("%n") + "MRMLM" + String.format("%n") + "3 2 E"
				+ String.format("%n") + "MRMLM";

		center.executeCommand(commandToExecuteErrorEmpty);

	}

	@Test(expected = InvalidCommandException.class)
	public void testInvalidPlateauErrorNumber() throws InvalidCommandException {
		ControlCenter center = new ControlCenter();
		String commandToExecuteErrorNumber = "7 7 2" + String.format("%n")
				+ "4 4 E" + String.format("%n") + "MRMLM" + String.format("%n")
				+ "3 2 E" + String.format("%n") + "MRMLM";

		center.executeCommand(commandToExecuteErrorNumber);

	}

	@Test(expected = InvalidCommandException.class)
	public void testInvalidPlateauOneNumber() throws InvalidCommandException {
		ControlCenter center = new ControlCenter();

		String commandToExecuteOneNumber = "5" + String.format("%n") + "4 4 E"
				+ String.format("%n") + "MRMLM" + String.format("%n") + "3 2 E"
				+ String.format("%n") + "MRMLM";

		center.executeCommand(commandToExecuteOneNumber);

	}

	@Test(expected = InvalidCommandException.class)
	public void testInvalidPlateauExecuteLetters() throws InvalidCommandException {
		ControlCenter center = new ControlCenter();

		String commandToExecuteLetters = "S S" + String.format("%n") + "4 4 E"
				+ String.format("%n") + "MRMLM" + String.format("%n") + "3 2 E"
				+ String.format("%n") + "MRMLM";

		center.executeCommand(commandToExecuteLetters);
	}

	/*
	 * Below similar tests can be added to make sure that the rest of the command
	 * is validated properly. This has however been left ut for now and it is
	 * assumed the NASA-tecnichians knows what they do.
	 */

}
