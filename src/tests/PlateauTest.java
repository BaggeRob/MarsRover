package tests;

import static org.junit.Assert.*;

import models.Plateau;

import org.junit.After;
import org.junit.Test;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class PlateauTest {

	Plateau plateau;

	@After
	public void tearDown() {
		plateau = null;
	}

	@Test
	public void testGetX() {
		plateau = new Plateau(10, 10);
		assertTrue(plateau.getBoundaryX() == 10);
	}

	@Test
	public void testGetY() {
		plateau = new Plateau(15, 20);
		assertTrue(plateau.getBoundaryY() == 20);
	}

	@Test
	public void testSetX() {
		plateau = new Plateau(15, 20);
		assertTrue(plateau.getBoundaryX() == 15);
		plateau.setBoundaryX(25);
		assertTrue(plateau.getBoundaryX() == 25);
	}

	@Test
	public void testSetY() {
		plateau = new Plateau(10, 10);
		assertTrue(plateau.getBoundaryY() == 10);
		plateau.setBoundaryY(125);
		assertTrue(plateau.getBoundaryY() == 125);
	}

	@Test
	public void testPointWithinXSmall() {
		plateau = new Plateau(15, 15);
		assertFalse(plateau.pointWithinPlateau(-1, 10));
	}

	@Test
	public void testPointWithinXBig() {
		plateau = new Plateau(10, 15);
		assertFalse(plateau.pointWithinPlateau(11, 10));
	}

	@Test
	public void testPointWithinYSmall() {
		plateau = new Plateau(5, 15);
		assertFalse(plateau.pointWithinPlateau(3, -1));
	}

	@Test
	public void testPointWithinYBig() {
		plateau = new Plateau(25, 20);
		assertFalse(plateau.pointWithinPlateau(10, 21));
	}

	@Test
	public void testPointWithinValid() {
		plateau = new Plateau(25, 25);
		assertTrue(plateau.pointWithinPlateau(0, 25));
		assertTrue(plateau.pointWithinPlateau(25, 0));
		assertTrue(plateau.pointWithinPlateau(12, 13));
	}

}
