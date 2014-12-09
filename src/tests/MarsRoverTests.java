package tests;

import static org.junit.Assert.*;

import models.MarsRover;
import models.Plateau;

import org.junit.After;
import org.junit.Test;

import exceptions.InvalidActionInCommandException;
import exceptions.InvalidHeadingException;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09 
 */
public class MarsRoverTests {
	private static String assertNoError = "No error should have been thrown";
	private static String wrongHeading = "Wrong heading, should have been ";
	private static String wrongPositionX = "Wrong position, X should be ";
	private static String wrongPositionY = "Wrong position, Y should be ";

	MarsRover rover;

	@After
	public void tearDown() {
		rover = null;
	}

	@Test
	public void testCreateValidMarsRover() {
		try {
			rover = new MarsRover(3, 2, 'E', null);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		assertNotNull(rover);
		assertEquals("X Not correct, should be 3", 3, rover.getPositionX());
		assertEquals("Y Not correct, should be 2", 2, rover.getPositionY());
		try {
			assertEquals("Heading not correct, should be 'E'", 'E',
					rover.getHeading());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
	}

	@Test(expected = InvalidHeadingException.class)
	public void createInvalidMarsRover() throws InvalidHeadingException {
		rover = new MarsRover(5, 7, 'J', null);
	}

	@Test
	public void testTurnLeft() {
		try {
			rover = new MarsRover(10, 10, 'S', null);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		try {
			assertEquals(wrongHeading + 'S', 'S', rover.getHeading());
			rover.turnLeft();
			assertEquals(wrongHeading + 'E', 'E', rover.getHeading());
			rover.turnLeft();
			assertEquals(wrongHeading + 'N', 'N', rover.getHeading());
			rover.turnLeft();
			assertEquals(wrongHeading + 'W', 'W', rover.getHeading());
			rover.turnLeft();
			assertEquals(wrongHeading + 'S', 'S', rover.getHeading());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
	}

	@Test
	public void testTurnRight() {
		try {
			rover = new MarsRover(-3, -2, 'W', null);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			assertEquals(wrongHeading + 'W', 'W', rover.getHeading());
			rover.turnRight();
			assertEquals(wrongHeading + 'N', 'N', rover.getHeading());
			rover.turnRight();
			assertEquals(wrongHeading + 'E', 'E', rover.getHeading());
			rover.turnRight();
			assertEquals(wrongHeading + 'S', 'S', rover.getHeading());
			rover.turnRight();
			assertEquals(wrongHeading + 'W', 'W', rover.getHeading());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
	}

	@Test
	public void testMove() {
		try {
			rover = new MarsRover(4, 1, 'E', null);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		rover.move();
		assertEquals(wrongPositionX + 5, 5, rover.getPositionX());
		assertEquals(wrongPositionY + 1, 1, rover.getPositionY());
		rover.move();
		assertEquals(wrongPositionX + 6, 6, rover.getPositionX());
		assertEquals(wrongPositionY + 1, 1, rover.getPositionY());
	}

	@Test
	public void testMoveEast() {
		try {
			rover = new MarsRover(3, 3, 'E', null);
			assertTrue(rover.getHeading() == 'E');
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		rover.move();
		assertEquals(wrongPositionX + 4, 4, rover.getPositionX());
		assertEquals(wrongPositionY + 3, 3, rover.getPositionY());
	}

	@Test
	public void testMoveNorth() {
		try {
			rover = new MarsRover(5, 2, 'N', null);
			assertTrue(rover.getHeading() == 'N');
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		rover.move();
		assertEquals(wrongPositionX + 5, 5, rover.getPositionX());
		assertEquals(wrongPositionY + 3, 3, rover.getPositionY());
	}

	@Test
	public void testMoveWest() {
		try {
			rover = new MarsRover(2, 7, 'W', null);
			assertTrue(rover.getHeading() == 'W');
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		rover.move();
		assertEquals(wrongPositionX + 1, 1, rover.getPositionX());
		assertEquals(wrongPositionY + 7, 7, rover.getPositionY());
	}

	@Test
	public void testMoveSouth() {
		try {
			rover = new MarsRover(48, 139, 'S', null);
			assertTrue(rover.getHeading() == 'S');
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		rover.move();
		assertEquals(wrongPositionX + 48, 48, rover.getPositionX());
		assertEquals(wrongPositionY + 138, 138, rover.getPositionY());
	}

	@Test
	public void testMoveAndTurn() {
		try {
			rover = new MarsRover(4, 1, 'E', null);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		rover.turnLeft(); // Changing heading to North
		rover.move(); // Moving to 4,2
		rover.move(); // Moving to 4,3
		rover.turnRight(); // Changing heading to East
		rover.move(); // Moving to 5,3
		rover.turnRight(); // Changing heading to South
		rover.turnRight(); // Changing heading to West
		rover.move(); // Moving to 4,3

		assertEquals(wrongPositionX + 4, 4, rover.getPositionX());
		assertEquals(wrongPositionY + 3, 3, rover.getPositionY());
	}

	@Test
	public void testEmptyCommand() {
		try {
			rover = new MarsRover(4, 1, 'E', null);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			rover.executeCommand("");
		} catch (InvalidActionInCommandException e1) {
			// TODO Auto-generated catch block
			fail(assertNoError);
		} catch (InvalidHeadingException e1) {
			// TODO Auto-generated catch block
			fail(assertNoError);
		}

		try {
			assertEquals(wrongHeading + 'E', 'E', rover.getHeading());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		assertEquals(wrongPositionX + 4, 4, rover.getPositionX());
		assertEquals(wrongPositionY + 1, 1, rover.getPositionY());
	}

	@Test
	public void testCommandTurnRight() {
		try {
			rover = new MarsRover(3, 3, 'W', new Plateau(5, 5));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			rover.executeCommand("R");
		} catch (InvalidActionInCommandException e1) {
			// TODO Auto-generated catch block
			fail(assertNoError);
		} catch (InvalidHeadingException e1) {
			// TODO Auto-generated catch block
			fail(assertNoError);
		}

		try {
			assertEquals(wrongHeading + 'N', 'N', rover.getHeading());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		assertEquals(wrongPositionX + 3, 3, rover.getPositionX());
		assertEquals(wrongPositionY + 3, 3, rover.getPositionY());
	}

	@Test
	public void testCommandTurnLeft() {
		try {
			rover = new MarsRover(3, 3, 'S', new Plateau(5, 5));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			rover.executeCommand("L");
		} catch (InvalidActionInCommandException e1) {
			// TODO Auto-generated catch block
			fail(assertNoError);
		} catch (InvalidHeadingException e1) {
			// TODO Auto-generated catch block
			fail(assertNoError);
		}

		try {
			assertEquals(wrongHeading + 'E', 'E', rover.getHeading());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
		assertEquals(wrongPositionX + 3, 3, rover.getPositionX());
		assertEquals(wrongPositionY + 3, 3, rover.getPositionY());
	}

	@Test
	public void testCommandMove() {
		try {
			rover = new MarsRover(5, 5, 'S', new Plateau(5, 5));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			rover.executeCommand("M");
			assertEquals(wrongHeading + 'S', 'S', rover.getHeading());
		} catch (InvalidActionInCommandException e1) {
			fail(assertNoError);
		} catch (InvalidHeadingException e1) {
			fail(assertNoError);
		}

		assertEquals(wrongPositionX + 5, 5, rover.getPositionX());
		assertEquals(wrongPositionY + 4, 4, rover.getPositionY());
	}

	@Test
	public void testCommandSeveralActions() {
		try {
			rover = new MarsRover(5, 5, 'W', new Plateau(5, 5));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			rover.executeCommand("MLMMRRMLMMR");// (start)55W -> (M)45W ->
			// (L)45S -> (MM)43S -> (RR)43N
			// -> (M)44N -> (L)44W ->
			// (MM)24W -> (R)24N(Stop)
			assertEquals(wrongHeading + 'N', 'N', rover.getHeading());
		} catch (InvalidActionInCommandException e) {
			fail(assertNoError);
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		assertEquals(wrongPositionX + 2, 2, rover.getPositionX());
		assertEquals(wrongPositionY + 4, 4, rover.getPositionY());
	}

	@Test(expected = InvalidActionInCommandException.class)
	public void testCommandWithErrors() throws InvalidActionInCommandException,
			InvalidHeadingException {
		try {
			rover = new MarsRover(5, 5, 'W', new Plateau(5, 5));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		rover.executeCommand("M(/&(/&LMKMJRRMLMWMR");
	}

	@Test
	public void testDriveOffPlateau() {
		try {
			rover = new MarsRover(0, 0, 'S', new Plateau(5, 5));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		try {
			rover.executeCommand("MLMM");
		} catch (InvalidActionInCommandException e) {
			fail(assertNoError + " " + e.getMessage());
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}

		assertTrue(rover.getPositionX() == 0);
		assertTrue(rover.getPositionY() == -1);
	}

	@Test
	public void testGetPositionAndLocationAfterCreate() {
		try {
			rover = new MarsRover(5, 5, 'W', new Plateau(5, 5));
			assertTrue(rover.getPositionAndLocation().equals("5 5 W"));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		}
	}

	@Test
	public void testGetPositionAndLocationAfterAction() {
		try {
			rover = new MarsRover(5, 5, 'E', new Plateau(10, 10));
			rover.executeCommand("MMRMMR");

			assertTrue(rover.getPositionAndLocation().equals("7 3 W"));
		} catch (InvalidHeadingException e) {
			fail(assertNoError);
		} catch (InvalidActionInCommandException e) {
			fail(assertNoError);
		}
	}

}
