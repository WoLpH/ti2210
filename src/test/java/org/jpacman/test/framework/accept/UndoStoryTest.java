package org.jpacman.test.framework.accept;

import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.ui.PacmanInteraction;
import org.jpacman.framework.ui.UndoablePacman;
import org.jpacman.framework.ui.UndoablePacmanInteraction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Story test for the undoable pacman.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * @author Yanick van Langeraad
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

	/**
	 * @Test Test the Undo function after eating 'Food', the game should set back a step and the
	 *       Food should still be in place. In this case, the test_S2_2_PlayerFood() (From
	 *       MovePlayerStoryTest.java) moves the player one spot, into a spot with Food When we undo
	 *       that, the empty Tile should again contain Food, and the player in the Start spot.
	 */
	@Test
	public void test_undo_Food() {
		test_S2_2_PlayerFood();
		getUI().getGame().undo();
		Assert.assertEquals(this.foodTile, getPlayer().getTile());

		getUI().getGame().undo();
		Assert.assertEquals(this.playerTile, getPlayer().getTile());
		Assert.assertEquals(IBoardInspector.SpriteType.FOOD, this.foodTile
				.topSprite().getSpriteType());
	}

	/**
	 * @Test Test the Undo function after a move, make sure the player sets back a step. In this
	 *       case, the test_S2_1_PlayerEmpty() (From MovePlayerStoryTest.java) moves the player one
	 *       spot When we undo that, the empty Tile should again be empty, and the player in the
	 *       Start spot.
	 */
	@Test
	public void test_undo_Move() {
		test_S2_1_PlayerEmpty();
		Assert.assertEquals(this.emptyTile, getPlayer().getTile());
		getUI().getGame().undo();
		Assert.assertEquals(null, this.emptyTile.topSprite());
		Assert.assertEquals(this.playerTile, getPlayer().getTile());
	}

	/**
	 * @Test When the Undo function is called after no move is made, the undo must also change
	 *       nothing. This also means the game should not even pause.
	 */
	@Test
	public void test_undo_Start() {
		getEngine().start();

		Assert.assertEquals(this.playerTile, getPlayer().getTile());

		getUI().getGame().undo();

		Assert.assertEquals(this.playerTile, getPlayer().getTile());
		Assert.assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());
	}

}
