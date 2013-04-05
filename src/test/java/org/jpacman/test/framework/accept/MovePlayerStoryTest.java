package org.jpacman.test.framework.accept;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.PacmanInteraction;
import org.junit.Assert;
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
	 * @param dir
	 *            The direction to move the player to.
	 */
	protected void movePlayer(Direction dir) {
		getUI().getGame().movePlayer(dir);
	}

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
		this.setEmptyTile(tileAt(1, 0));
		this.setFoodTile(tileAt(0, 1));
		this.setPlayerTile(tileAt(1, 1));
		this.setWallTile(tileAt(1, 2));
		this.setGhostTile(tileAt(2, 1));
	}

	/**
	 * Test that a player can move towards an empty tile.
	 */
	@Test
	public void test_S2_1_PlayerEmpty() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile is empty and move to it */
		Assert.assertEquals(null, this.getEmptyTile().topSprite());
		movePlayer(Direction.UP);

		/* Check if the player indeed moved and replaced the empty tile */
		Assert.assertEquals(this.getEmptyTile(), getPlayer().getTile());
	}

	/**
	 * Test that a player can move over food.
	 */
	@Test
	public void test_S2_2_PlayerFood() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile contains food and move to it */
		Assert.assertEquals(IBoardInspector.SpriteType.FOOD, this.getFoodTile()
				.topSprite().getSpriteType());
		movePlayer(Direction.LEFT);

		/* Check if we moved and if we replaced the food */
		Assert.assertEquals(this.getFoodTile(), getPlayer().getTile());
		Assert.assertEquals(IBoardInspector.SpriteType.PLAYER, this.getFoodTile()
				.topSprite().getSpriteType());

		/* Move away and check if we ate the food */
		movePlayer(Direction.RIGHT);
		Assert.assertEquals(null, this.getFoodTile().topSprite());
	}

	/**
	 * Test that a player failes to move through walls.
	 */
	@Test
	public void test_S2_3_PlayerFailMove() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile contains a wall and try to move to it */
		Assert.assertEquals(IBoardInspector.SpriteType.WALL, this.getWallTile()
				.topSprite().getSpriteType());
		movePlayer(Direction.DOWN);

		/* If we are still at our current position, everything is ok */
		Assert.assertEquals(this.getPlayerTile(), getPlayer().getTile());
	}

	/**
	 * Test that a player dies when touching a ghost.
	 */
	@Test
	public void test_S2_4_PlayerDie() {
		/* initialize the engine */
		getEngine().start();

		/* Check if the tile contains a ghost and try to move to it */
		Assert.assertEquals(IBoardInspector.SpriteType.GHOST, this.getGhostTile()
				.topSprite().getSpriteType());
		movePlayer(Direction.RIGHT);

		/*
		 * If there is no player at the player tile and the player is now dead and at the ghost
		 * location, it worked
		 */
		Assert.assertEquals(this.getGhostTile(), getPlayer().getTile());
		Assert.assertEquals(null, this.getPlayerTile().topSprite());
		Assert.assertEquals(getEngine().getCurrentState(),
				PacmanInteraction.MatchState.LOST);
		/* he's dead Jim */
		Assert.assertFalse(getPlayer().isAlive());
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
		Assert.assertEquals(getEngine().getCurrentState(),
				PacmanInteraction.MatchState.WON);
	}

	/**
	 * @return the emptyTile
	 */
	public Tile getEmptyTile() {
		return emptyTile;
	}

	/**
	 * @param emptyTile the emptyTile to set
	 */
	public void setEmptyTile(Tile emptyTile) {
		this.emptyTile = emptyTile;
	}

	/**
	 * @return the foodTile
	 */
	public Tile getFoodTile() {
		return foodTile;
	}

	/**
	 * @param foodTile the foodTile to set
	 */
	public void setFoodTile(Tile foodTile) {
		this.foodTile = foodTile;
	}

	/**
	 * @return the playerTile
	 */
	public Tile getPlayerTile() {
		return playerTile;
	}

	/**
	 * @param playerTile the playerTile to set
	 */
	public void setPlayerTile(Tile playerTile) {
		this.playerTile = playerTile;
	}

	/**
	 * @return the wallTile
	 */
	public Tile getWallTile() {
		return wallTile;
	}

	/**
	 * @param wallTile the wallTile to set
	 */
	public void setWallTile(Tile wallTile) {
		this.wallTile = wallTile;
	}

	/**
	 * @return the ghostTile
	 */
	public Tile getGhostTile() {
		return ghostTile;
	}

	/**
	 * @param ghostTile the ghostTile to set
	 */
	public void setGhostTile(Tile ghostTile) {
		this.ghostTile = ghostTile;
	}
}
