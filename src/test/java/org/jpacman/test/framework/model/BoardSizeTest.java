package org.jpacman.test.framework.model;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class tests the behaviour of positioning with 0 sized boards.
 */
@RunWith(Parameterized.class)
public class BoardSizeTest {
	private final Board board;

	/**
	 * Create a new test case obtaining the tile from starting point (x, y) on a board with the
	 * given width and height.
	 * 
	 * @param width
	 *            Width of the board
	 * @param height
	 *            Height of the board
	 */
	public BoardSizeTest(int width, int height) {
		board = new Board(width, height);
	}

	/**
	 * The actual test case, we expect failure since we make a board that doesn't contain the
	 * position.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testTileAtDirection() {
		board.tileAt(0, 0);
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
				{ 0, 0 }, { 1, 0 }, { 0, 1 }, };
		return Arrays.asList(values);
	}

}
