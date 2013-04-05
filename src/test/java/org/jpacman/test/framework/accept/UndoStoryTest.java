package org.jpacman.test.framework.accept;

import static org.junit.Assert.*;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.UndoablePacman;
import org.junit.Before;
import org.junit.Test;

/**
 * Story test for the undoable pacman.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * @author Yanick van Langeraaf
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
	protected UndoablePacman getUI() {
		return this.pacman;
	}

	@Override
	public MainUI makeUI() {
		this.pacman = new UndoablePacman();
		return this.pacman;
	}

	
	@Test
	public void testUI(){
		getEngine().start();
		makeUI();
		getUI().getGame().movePlayer(Direction.UP);
	}
	/*
	
	@Test 
	public void test_undo_1(){
		makeUI();
		getEngine().start();
		
		assertEquals(null, emptyTile.topSprite());
		assertEquals(playerTile, getPlayer().getTile());
		
		movePlayer(Direction.UP);
		
		getUI().getGame().undo();
		
		assertEquals(playerTile, getPlayer().getTile());
	}
	
	
	@Test
	public void test_undo_Food(){
		makeUI();
		getEngine().start();

		assertEquals(IBoardInspector.SpriteType.FOOD, foodTile.topSprite()
				.getSpriteType());
		movePlayer(Direction.LEFT);
		
		assertEquals(foodTile, getPlayer().getTile());
		assertEquals(IBoardInspector.SpriteType.PLAYER, foodTile.topSprite()
				.getSpriteType());

		getUI().getGame().undo();
		
		assertEquals(IBoardInspector.SpriteType.FOOD, foodTile.topSprite()
				.getSpriteType());
		
		assertEquals(playerTile, getPlayer().getTile());
	}
	*/
	
	private void movePlayer(Direction dir) {
		getUI().getGame().movePlayer(dir);
	}
}
