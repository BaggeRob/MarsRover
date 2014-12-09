package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.InvalidHeadingException;

import assets.CommunicationHelper;

/**
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class CommunicationHelperTest {

	@Test
	public void testEncodeHeadingValid() {

		try {
			assertEquals("Heading not correct, should be 0", 0,
					CommunicationHelper.decodeHeadingToDegrees('E'));
			assertEquals("Heading not correct, should be 90", 90,
					CommunicationHelper.decodeHeadingToDegrees('N'));
			assertEquals("Heading not correct, should be 180", 180,
					CommunicationHelper.decodeHeadingToDegrees('W'));
			assertEquals("Heading not correct, should be 270", 270,
					CommunicationHelper.decodeHeadingToDegrees('S'));
		} catch (InvalidHeadingException e) {
			fail("No error should be thrown");
		}

	}

	@Test(expected = InvalidHeadingException.class)
	public void testEncodeHeadingInvalid() throws InvalidHeadingException {
		int i = CommunicationHelper.decodeHeadingToDegrees('K');
		System.out.println(i);
	}

	@Test
	public void testDecodeHeadingValid() {
		try {
			assertEquals("Heading not correct, should be 'W'", 'W',
					CommunicationHelper.encodeHeadingAsChar(180));
			assertEquals("Heading not correct, should be 'S'", 'S',
					CommunicationHelper.encodeHeadingAsChar(270));
			assertEquals("Heading not correct, should be 'E'", 'E',
					CommunicationHelper.encodeHeadingAsChar(0));
			assertEquals("Heading not correct, should be 'N'", 'N',
					CommunicationHelper.encodeHeadingAsChar(90));
		} catch (InvalidHeadingException e) {
			fail("No error should be thrown");
		}
	}

	@Test(expected = InvalidHeadingException.class)
	public void testDecodeHeadingInvalid() throws InvalidHeadingException {
		char c = CommunicationHelper.encodeHeadingAsChar(45);
		System.out.println(c);
	}

}
