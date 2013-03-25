package org.jpacman.test.framework.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Tile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class tests the behaviour of positioning out of bounds.
 */
@RunWith(Parameterized.class)
public class BoardInvalidMovesTest {
	private final Direction direction;
	private final Board board;

	/**
	 * Create a new test case obtaining the tile from starting point (x, y) on a board with the
	 * given width and height.
	 * 
	 * @param direction
	 *            Direction to move in
	 */
	public BoardInvalidMovesTest(Direction direction) {
		this.direction = direction;
		board = new Board(1, 1);
	}

	/**
	 * The actual test case, we expect failure since we make a board that doesn't contain the
	 * position.
	 */
	@Test
	public void testTileAtDirection() {
		Tile source = board.tileAt(0, 0);
		Tile destination = board.tileAtDirection(source, direction);
		assertEquals(source, destination);
	}

	/**
	 * List of (width, height, x, y) data points.
	 * 
	 * @return Test data to be fed to constructor.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] values = new Object[][] { { Direction.UP },
				{ Direction.DOWN }, { Direction.LEFT }, { Direction.RIGHT } };
		return Arrays.asList(values);
	}
}
