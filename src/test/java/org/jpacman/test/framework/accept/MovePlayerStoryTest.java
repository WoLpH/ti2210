package org.jpacman.test.framework.accept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.PacmanInteraction;
import org.junit.Before;
import org.junit.Test;

/**
 * Acceptance test suite for player moves, exercising JPacman via the ui.
 * 
 * I want to move my Pacman arround on the board; So that I can eat all food and win the game.
 * 
 * When asserting the results, the key effects are validated only, as expressed in the "then" clause
 * of the BDD scenarios. Rigorous testing of all relevant effects is done in the unit tests.
 * 
 * The test harness from the superclass is reused in this test.
 * 
 * @author Arie van Deursen, TU Delft, Feb 4, 2012
 */
public class MovePlayerStoryTest extends AbstractAcceptanceTest {
	// Emtpy tile on the board next to the player.
	private Tile emptyTile;

	// Food tile on the board next to the player.
	private Tile foodTile;

	// Wall tile on the board next to the player.
	private Tile wallTile;

	// Player tile on the board, the starting point.
	private Tile playerTile;

	// Player tile on the board, the starting point.
	private Tile ghostTile;

	/**
	 * Setup the game, given a simpler map to improve controllability.
	 * 
	 * @throws FactoryException
	 *             When map loading fails.
	 * @throws InterruptedException
	 *             When controllers fail.
	 */
	@Override
	@Before
	public void setUp() throws FactoryException, InterruptedException {
		super.setUp();
		emptyTile = tileAt(1, 0);
		foodTile = tileAt(0, 1);
		playerTile = tileAt(1, 1);
		wallTile = tileAt(1, 2);
		ghostTile = tileAt(2, 1);
	}

	/**
	 * Test that a player can move towards an empty tile.
	 */
	@Test
	public void test_S2_1_PlayerEmpty() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile is empty and move to it */
		assertEquals(null, emptyTile.topSprite());
		movePlayer(Direction.UP);

		/* Check if the player indeed moved and replaced the empty tile */
		assertEquals(emptyTile, getPlayer().getTile());
	}

	/**
	 * Test that a player can move over food.
	 */
	@Test
	public void test_S2_2_PlayerFood() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile contains food and move to it */
		assertEquals(IBoardInspector.SpriteType.FOOD, foodTile.topSprite()
				.getSpriteType());
		movePlayer(Direction.LEFT);

		/* Check if we moved and if we replaced the food */
		assertEquals(foodTile, getPlayer().getTile());
		assertEquals(IBoardInspector.SpriteType.PLAYER, foodTile.topSprite()
				.getSpriteType());

		/* Move away and check if we ate the food */
		movePlayer(Direction.RIGHT);
		assertEquals(null, foodTile.topSprite());
	}

	/**
	 * Test that a player failes to move through walls.
	 */
	@Test
	public void test_S2_3_PlayerFailMove() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile contains a wall and try to move to it */
		assertEquals(IBoardInspector.SpriteType.WALL, wallTile.topSprite()
				.getSpriteType());
		movePlayer(Direction.DOWN);

		/* If we are still at our current position, everything is ok */
		assertEquals(playerTile, getPlayer().getTile());
	}

	/**
	 * Test that a player dies when touching a ghost.
	 */
	@Test
	public void test_S2_4_PlayerDie() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile contains a ghost and try to move to it */
		assertEquals(IBoardInspector.SpriteType.GHOST, ghostTile.topSprite()
				.getSpriteType());
		movePlayer(Direction.RIGHT);

		/*
		 * If there is no player at the player tile and the player is now dead and at the ghost
		 * location, it worked
		 */
		assertEquals(ghostTile, getPlayer().getTile());
		assertEquals(null, playerTile.topSprite());
		assertEquals(getEngine().getCurrentState(),
				PacmanInteraction.MatchState.LOST);
		/* he's dead Jim */
		assertFalse(getPlayer().isAlive());
	}

	/**
	 * Test that a player wins when getting all food.
	 */
	@Test
	public void test_S2_5_PlayerWin() {
		/* initialize the engine */
		getEngine().start();

		/* Move to the food locations */
		movePlayer(Direction.LEFT);
		movePlayer(Direction.RIGHT);
		movePlayer(Direction.UP);
		movePlayer(Direction.RIGHT);
		movePlayer(Direction.RIGHT);

		/* Yes, we are awesome! We've won :D */
		assertEquals(getEngine().getCurrentState(),
				PacmanInteraction.MatchState.WON);
	}

	/**
	 * @param dir
	 *            The direction to move the player to.
	 */
	private void movePlayer(Direction dir) {
		getUI().getGame().movePlayer(dir);
	}
}
