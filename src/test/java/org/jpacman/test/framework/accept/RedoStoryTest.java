package org.jpacman.test.framework.accept;

import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.ui.PacmanInteraction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Story test for redoing undo's.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class RedoStoryTest extends UndoStoryTest {

	/**
	 * Redo the last move.
	 */
	protected void redo() {
		getUI().getGame().redo();
		getEngine().start();
	}

	/**
	 * @Test When the Undo function is called after no move is made, the undo must also change
	 *       nothing. This also means the game should not even pause.
	 */
	@Test
	public void test_S4_1_RedoUndoStart() {
		test_S3_1_UndoStart();
		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());
		Assert.assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());

		redo();
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
	public void test_S4_2_RedoUndoMove() {
		test_S3_2_UndoMove();
		Assert.assertEquals(null, getEmptyTile().topSprite());
		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());

		redo();
		Assert.assertEquals(getEmptyTile(), getPlayer().getTile());
	}

	/**
	 * @Test Test the Undo function after eating 'Food', the game should set back a step and the
	 *       Food should still be in place. In this case, the test_S2_2_PlayerFood() (From
	 *       MovePlayerStoryTest.java) moves the player one spot, into a spot with Food When we undo
	 *       that, the empty Tile should again contain Food, and the player in the Start spot.
	 */
	@Test
	public void test_S4_3_RedoUndoFood() {
		test_S3_3_UndoFood();
		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());
		Assert.assertEquals(IBoardInspector.SpriteType.FOOD, getFoodTile()
				.topSprite().getSpriteType());
		Assert.assertEquals(0, getPlayer().getPoints());

		redo();
		Assert.assertEquals(getFoodTile(), getPlayer().getTile());
		Assert.assertNotEquals(0, getPlayer().getPoints());

		redo();
		Assert.assertEquals(getPlayerTile(), getPlayer().getTile());
		Assert.assertNull(getFoodTile().topSprite());
		Assert.assertEquals(Food.DEFAULT_POINTS, getPlayer().getPoints());
	}
}
