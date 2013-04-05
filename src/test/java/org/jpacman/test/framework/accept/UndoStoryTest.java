package org.jpacman.test.framework.accept;

import static org.junit.Assert.*;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.UndoablePacman;
import org.junit.Before;
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
	
	
	@Override
	@Before
	public void setUp() throws FactoryException, InterruptedException{
		super.setUp();
		emptyTile = tileAt(1, 0);
		foodTile = tileAt(0, 1);
		playerTile = tileAt(1, 1);
		wallTile = tileAt(1, 2);
		ghostTile = tileAt(2, 1);
	}
	

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
	 * @Test
	 * Test the Undo function after a move, make sure the player sets back a step.
	 * In this case, the test_S2_1_PlayerEmpty() (From MovePlayerStoryTest.java) moves the player one spot
	 * When we undo that, the empty Tile should again be empty, and the player in the Start spot.
	 */
	@Test 
	public void test_undo_Move(){
		test_S2_1_PlayerEmpty(); 
		assertEquals(emptyTile, getPlayer().getTile());
		getUI().getGame().undo();
		assertEquals(null, emptyTile.topSprite());
		assertEquals(playerTile, getPlayer().getTile());
	}
	
	/**
	 * @Test
	 * Test the Undo function after eating 'Food', the game should set back a step and the Food should still be in place.
	 * In this case, the test_S2_2_PlayerFood() (From MovePlayerStoryTest.java) moves the player one spot, into a spot with Food
	 * When we undo that, the empty Tile should again contain Food, and the player in the Start spot.
	 */
	@Test
	public void test_undo_Food(){
		test_S2_2_PlayerFood();
		getUI().getGame().undo();
		assertEquals(foodTile, getPlayer().getTile());
		
		getUI().getGame().undo();
		assertEquals(playerTile, getPlayer().getTile());
		Assert.assertEquals(IBoardInspector.SpriteType.FOOD, this.foodTile
				.topSprite().getSpriteType());
	}
	
	/**
	 * @Test
	 * When the Undo function is called after no move is made, the undo must also change nothing.
	 * This also means the game should not even pause.
	 */
	@Test
	public void test_undo_Start(){
		getEngine().start();
		
		assertEquals(playerTile, getPlayer().getTile());
		
		getUI().getGame().undo();
		
		assertEquals(playerTile, getPlayer().getTile());
		assertEquals(PacmanInteraction.MatchState.PLAYING, getEngine()
				.getCurrentState());
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
