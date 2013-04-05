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
	 * @Test When the Undo function is called after no move is made, the undo must also change
	 *       nothing. This also means the game should not even pause.
	 */
	@Test
	public void test_S3_1_UndoStart() {
		getEngine().start();

		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());

		getUI().getGame().undo();

		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());
		Assert.assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());
	}

	/**
	 * @Test Test the Undo function after a move, make sure the player sets back a step. In this
	 *       case, the test_S2_1_PlayerEmpty() (From MovePlayerStoryTest.java) moves the player one
	 *       spot When we undo that, the empty Tile should again be empty, and the player in the
	 *       Start spot.
	 */
	@Test
	public void test_S3_2_UndoMove() {
		test_S2_1_PlayerEmpty();
		Assert.assertEquals(getEmptyTile(), getPlayer().getTile());
		getUI().getGame().undo();
		Assert.assertEquals(null, getEmptyTile().topSprite());
		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());
	}

	/**
	 * @Test Test the Undo function after eating 'Food', the game should set back a step and the
	 *       Food should still be in place. In this case, the test_S2_2_PlayerFood() (From
	 *       MovePlayerStoryTest.java) moves the player one spot, into a spot with Food When we undo
	 *       that, the empty Tile should again contain Food, and the player in the Start spot.
	 */
	@Test
	public void test_S3_3_UndoFood() {
		test_S2_2_PlayerFood();
		getUI().getGame().undo();
		Assert.assertEquals(getFoodTile(), getPlayer().getTile());
		Assert.assertNotEquals(0, getPlayer().getPoints());

		getUI().getGame().undo();
		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());
		Assert.assertEquals(IBoardInspector.SpriteType.FOOD, getFoodTile()
				.topSprite().getSpriteType());
		Assert.assertEquals(0, getPlayer().getPoints());

	}

	/**
	 * Test that a player is playing again after winning and undoing.
	 */
	@Test
	public void test_S3_4_UndoLost() {
		test_S2_4_PlayerDie();
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
	public void test_S3_5_UndoWon() {
		test_S2_5_PlayerWin();
		getUI().getGame().undo();
		getEngine().start();

		/* Undoing the winning? */
		Assert.assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());
	}

}
