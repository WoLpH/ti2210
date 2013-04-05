/**
 * 
 */
package org.jpacman.test.framework.model;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.UndoableGame;
import org.jpacman.framework.model.UndoablePlayer;
import org.jpacman.framework.ui.UndoablePacman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoablePlayerTest {
	private UndoablePacman pacman;

	/**
	 * @throws FactoryException
	 *             Never happens
	 */
	@Before
	public void setUp() throws FactoryException {
		this.pacman = new UndoablePacman();
		this.pacman.initialize();
	}

	/**
	 * Test method for the undoable player moveTo method.
	 */
	@Test
	public final void testMoveTo() {
		UndoableGame game = this.pacman.getGame();
		UndoablePlayer player = (UndoablePlayer) game.getPlayer();
		player.moveTo(game, game.getBoard().tileAt(0, 0));
		Assert.assertTrue(player.isAlive());
		player.moveTo(game, null);
		Assert.assertFalse(player.isAlive());
	}

}
