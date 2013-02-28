package org.jpacman.test.framework.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Essentially the same as the BoardBoundsTest, but uses different methods.
 * Didn't see we had to test the withinBorders method aswell, kind of pointless
 * to test the same thing twice I guess. Although... as they say, measure twice,
 * cut once.
 */
@RunWith(Parameterized.class)
public class WithinBordersTest {
	private final int destx, desty;
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
	public WithinBordersTest(int x, int y) {
		destx = x;
		desty = y;
		board = new Board(1, 1);
	}

	/**
	 * The actual test case, we expect failure since we make a board that
	 * doesn't contain the position.
	 */
	@Test
	public void testTileAtPosition() {
		assertFalse(board.withinBorders(destx, desty));
	}

	/**
	 * Also test the result when we expect it to succeed Kind of pointless to
	 * test this in all cases, but creating an entirely different test seems
	 * pointless too and using a parameterized class is nice. This test succeeds
	 * in near-zero time anyway.
	 */
	@Test
	public void testValidTile() {
		assertTrue(board.withinBorders(0, 0));
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
