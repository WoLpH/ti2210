package org.jpacman.test.framework.model;

import org.jpacman.framework.model.Tile;
import org.jpacman.framework.model.UndoableTile;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing the undoable tile.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class UndoableTileTest {

	/**
	 * Test the Undoable tile equals method.
	 */
	@Test
	public void testEquals() {
		UndoableTile a = new UndoableTile(new Tile(0, 0));
		UndoableTile b = new UndoableTile(new Tile(0, 0));
		UndoableTile c = new UndoableTile(new Tile(0, 1));
		Assert.assertEquals(a, b);
		Assert.assertNotEquals(a, c);
		Assert.assertNotEquals(a, null);
	}

	/**
	 * Test the Undoable tile hash method.
	 */
	@Test
	public void testHash() {
		UndoableTile a = new UndoableTile(new Tile(0, 0));
		UndoableTile b = new UndoableTile(new Tile(0, 0));
		Assert.assertEquals(a.hashCode(), a.hashCode());
		Assert.assertNotEquals(a.hashCode(), b.hashCode());
	}

}
