package org.jpacman.test.framework.model;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class tests the behaviour of positioning with negative sized boards.
 */
@RunWith(Parameterized.class)
public class InvalidBoardSizeTest {
	private final int width, height;

	/**
	 * Create a new test case with the given (invalid) width/height combination.
	 * 
	 * @param width
	 *            Width of the board
	 * @param height
	 *            Height of the board
	 */
	public InvalidBoardSizeTest(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * The actual test case, we expect failure since we make a board that doesn't could never exist.
	 */
	@Test(expected = NegativeArraySizeException.class)
	public void testTileAtDirection() {
		new Board(width, height);
	}

	/**
	 * List of (width, height, x, y) data points.
	 * 
	 * @return Test data to be fed to constructor.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] values = new Object[][] {
				// Simply pick any point that's out of bounds
				{ -1, 0 }, { -1, -1 }, { 0, -1 }, { -1, 1 }, { 1, -1 }, };
		return Arrays.asList(values);
	}

}
