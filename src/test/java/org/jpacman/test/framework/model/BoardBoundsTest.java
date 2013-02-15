package org.jpacman.test.framework.model;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class tests the behaviour of positioning out of bounds.
 */
@RunWith(Parameterized.class)
public class BoardBoundsTest {
	private final int startx, starty;
	private final Board board;

	/**
	 * Create a new test case obtaining the tile from starting point (x, y) on a
	 * board with the given width and height.
	 * 
	 * @param x
	 *            X position
	 * @param y
	 *            Y position
	 */
	public BoardBoundsTest(int x, int y) {
		startx = x;
		starty = y;
		board = new Board(1, 1);
	}

	/**
	 * The actual test case, we expect failure since we make a board that
	 * doesn't contain the position.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testTileAtDirection() {
		board.tileAt(startx, starty);
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
				{ -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 },
				{ 1, -1 }, { 1, 0 }, { 1, 1 }, { -1, 0 } };
		return Arrays.asList(values);
	}

}
