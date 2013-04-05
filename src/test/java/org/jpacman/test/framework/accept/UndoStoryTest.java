package org.jpacman.test.framework.accept;

import org.jpacman.framework.ui.PacmanInteraction;
import org.jpacman.framework.ui.UndoablePacman;
import org.jpacman.framework.ui.UndoablePacmanInteraction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Story test for the undoable pacman.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class UndoStoryTest extends MovePlayerStoryTest {
	private UndoablePacman pacman;

	@Override
	protected UndoablePacmanInteraction getEngine() {
		return (UndoablePacmanInteraction) super.getEngine();
	}

	@Override
	protected UndoablePacman getUI() {
		return this.pacman;
	}

	@Override
	public UndoablePacman makeUI() {
		this.pacman = new UndoablePacman();
		return this.pacman;
	}

	/**
	 * Test that a player is playing again after winning and undoing.
	 */
	@Test
	public void test_S2_6_PlayerWinUndo() {
		test_S2_5_PlayerWin();
		getUI().getGame().undo();
		getEngine().start();

		/* Undoing the winning? */
		Assert.assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());
	}

	/**
	 * Test that a player is playing again after winning and undoing.
	 */
	@Test
	public void test_S2_7_PlayerLoseUndo() {
		test_S2_4_PlayerDie();
		getUI().getGame().undo();
		getEngine().start();

		/* Undoing the winning? */
		Assert.assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());
	}

}
