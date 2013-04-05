/**
 * 
 */
package org.jpacman.test.framework.model;

import org.hamcrest.CoreMatchers;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.UndoableGameFactory;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.model.UndoableGame;
import org.jpacman.framework.ui.UndoablePacman;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableGameTest extends GameTest {
	@Override
	public IGameFactory makeFactory() {
		return new UndoableGameFactory(new UndoablePacman());
	}

	@Override
	protected UndoableGame makePlay(String singleRow) throws FactoryException {
		return (UndoableGame) super.makePlay(singleRow);
	}

	/**
	 * Test what happens if the ghost moves into the player and that an undo reverts it all.
	 * 
	 * @throws FactoryException
	 *             Never.
	 */
	@Test
	public void testC4a_GhostMovesToPlayer() throws FactoryException {
		UndoableGame game = makePlay("PG#");
		Ghost ghost = (Ghost) game.getBoard().spriteAt(1, 0);
		Player player = game.getPlayer();

		game.moveGhost(ghost, Direction.LEFT);
		Assert.assertFalse("Move kills player", player.isAlive());

		Tile newTile = ghost.getTile();
		Assert.assertThat(tileAt(game, 0, 0), CoreMatchers.equalTo(newTile));

		/* Ghosts don't create new states so undo changes nothing */
		game.undo();
		Assert.assertFalse("Undo changes nothing", player.isAlive());
		Assert.assertThat(tileAt(game, 0, 0),
				CoreMatchers.equalTo(ghost.getTile()));
	}

	/**
	 * Test what happens if the ghost moves into the player and that an undo reverts it all.
	 * 
	 * @throws FactoryException
	 *             Never.
	 */
	@Test
	public void testC4b_GhostMovesToPlayer() throws FactoryException {
		UndoableGame game = makePlay("P.G");
		Ghost ghost = (Ghost) game.getBoard().spriteAt(2, 0);
		Player player = game.getPlayer();

		game.movePlayer(Direction.RIGHT);
		game.moveGhost(ghost, Direction.LEFT);
		Assert.assertFalse("Move kills player", player.isAlive());

		Tile newTile = ghost.getTile();
		Assert.assertThat(tileAt(game, 1, 0), CoreMatchers.equalTo(newTile));

		/* Ghosts don't create new states so undo changes nothing */
		game.undo();
		Assert.assertTrue("Player revives", player.isAlive());
		Assert.assertThat(tileAt(game, 2, 0),
				CoreMatchers.equalTo(ghost.getTile()));
		Assert.assertThat(tileAt(game, 0, 0),
				CoreMatchers.equalTo(player.getTile()));
	}

	/**
	 * Test that a player indeed consumes food if he enters food and that an undo reverts it all.
	 * 
	 * @throws FactoryException
	 *             Never.
	 */
	@Override
	@Test
	public void testC5_PlayerMovesToFood() throws FactoryException {
		UndoableGame game = makePlay("P.#");
		Food food = (Food) game.getBoard().spriteAt(1, 0);
		Player player = game.getPlayer();

		game.movePlayer(Direction.RIGHT);

		Tile newTile = tileAt(game, 1, 0);
		Assert.assertEquals("Food added", food.getPoints(), player.getPoints());
		Assert.assertEquals("Player moved", newTile.topSprite(), player);
		Assert.assertFalse("Food gone", newTile.containsSprite(food));

		game.undo();

		Tile originalTile = tileAt(game, 0, 0);
		Assert.assertEquals("Food removed", 0, player.getPoints());
		Assert.assertEquals("Player moved back", originalTile.topSprite(),
				player);
		Assert.assertTrue("Food back", newTile.containsSprite(food));
	}

	/**
	 * Test situation that player moves to a food cell and that an undo reverts it all.
	 * 
	 * @throws FactoryException
	 *             Can't happen.
	 */
	@Override
	@Test
	public void testC6_GhostMovesToFood() throws FactoryException {
		UndoableGame game = makePlay("G.#");
		Ghost theGhost = (Ghost) game.getBoard().spriteAt(0, 0);

		game.moveGhost(theGhost, Direction.RIGHT);
		Assert.assertEquals("Ghost moved", tileAt(game, 1, 0),
				theGhost.getTile());

		game.moveGhost(theGhost, Direction.LEFT);
		Assert.assertEquals(SpriteType.FOOD, game.getBoard().spriteTypeAt(1, 0));

		/* We expect no change, ghosts don't create new states upon movement */
		Assert.assertEquals("Ghost stayed at the same position",
				tileAt(game, 0, 0), theGhost.getTile());
		Assert.assertEquals(SpriteType.FOOD, game.getBoard().spriteTypeAt(1, 0));
	}

}
