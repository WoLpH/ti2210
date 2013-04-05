/**
 * 
 */
package org.jpacman.test.framework.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.factory.UndoableGameFactory;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.UndoableGame;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.ui.UndoablePacman;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * @author Yanick van Langeraad
 */
public class UndoableGameTest extends GameTest {

	@Override
	public IGameFactory makeFactory() {
		return new UndoableGameFactory(new UndoablePacman());
	}
	
	protected UndoableGame makePlay(String singleRow) throws FactoryException {
		MapParser p = new MapParser(makeFactory());
		UndoableGame theGame = (UndoableGame) p.parseMap(new String[]{singleRow});
		return theGame;
	}
	
	
	/**
	 * Test the initial state, when the game is just made
	 * @throws FactoryException
	 */
	@Test
	public void testInitial() throws FactoryException{
		UndoableGame g = makePlay("P");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		assertThat(tileAt(g, 0, 0), equalTo(g.getPlayer().getTile()));
		assertEquals(SpriteType.PLAYER, g.getBoard().spriteTypeAt(0, 0));
		assertEquals(0, g.getPlayer().getPoints());
		assertTrue(g.getPlayer().isAlive());
	}
	
	/**
	 * Tests if the Game can Undo under the right circumstances
	 * @throws FactoryException
	 */
	@Test
	public void canUndoTest() throws FactoryException{
		UndoableGame g = makePlay("P");
		assertFalse(g.canUndo());
	}
	
	/**
	 * This one tests canUndo again, and also the canRedo function
	 * @throws FactoryException
	 */
	@Test
	public void canRedoTest() throws FactoryException{
		UndoableGame g = makePlay("P #");
		g.movePlayer(Direction.RIGHT);
		assertTrue(g.canUndo());
		assertFalse(g.canRedo());
		g.undo();
		assertFalse(g.canUndo());
		assertTrue(g.canRedo());
	}
	
	/**
	 * This tests whether really nothing has changed when you try to Undo before moving.
	 * @throws FactoryException
	 */
	@Test
	public void UndoNothing() throws FactoryException{
		UndoableGame g = makePlay("P");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		g.undo();
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
	}
	
	/**
	 * This tests whether you can Undo after only walking against a wall (it shouldn't be able to)
	 * @throws FactoryException
	 */
	@Test
	public void UndoWall_1() throws FactoryException{
		UndoableGame g = makePlay("P#");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		g.movePlayer(Direction.RIGHT);
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		assertFalse(g.canUndo());
	}
	
	/**
	 * This tests whether Undo really ignores walking against a wall
	 * @throws FactoryException
	 */
	@Test
	public void UndoWall_2() throws FactoryException{
		UndoableGame g = makePlay("P #");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		g.movePlayer(Direction.RIGHT);
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(1, 0));
		g.movePlayer(Direction.RIGHT);
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(1, 0));
		g.undo();
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
	}
	
	/**
	 * This tests whether a player will die when walking against a Ghost
	 * And if, after undoing, the player will once again live.
	 * @throws FactoryException
	 */
	@Test
	public void Undo_Player_to_Ghost() throws FactoryException{
		UndoableGame g = makePlay("PG");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		g.movePlayer(Direction.RIGHT);
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(1, 0));
		assertFalse(g.getPlayer().isAlive());
		g.undo();
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		assertTrue(g.getPlayer().isAlive());
	}
	
	/**
	 * Now one big test to show the all functionality, and that it works in succession.
	 * @throws FactoryException
	 */
	@Test
	public void UndoLotsAndRedoLots() throws FactoryException{
		UndoableGame g = makePlay("#. P ... ..G#");
		//Starting at (3,0) with 0 points
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(3, 0));
		assertEquals(0, g.getPlayer().getPoints());
		
		//After moving right twice we should have 10 points at (5,0)
		g.movePlayer(Direction.RIGHT);
		g.movePlayer(Direction.RIGHT);
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(5, 0));
		assertEquals(10, g.getPlayer().getPoints());
		//Undo the last move, we should have no points at (4,0)
		g.undo();
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(4, 0));
		assertEquals(0, g.getPlayer().getPoints());
		//Redo this, we should have 10 points at (5,0)
		g.redo();
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(5, 0));
		assertEquals(10, g.getPlayer().getPoints());
		
		//Move right 6 times, should now be on the spot of the Ghost ((11,0))
		for(int i = 0; i<6; i++)
			g.movePlayer(Direction.RIGHT);

		
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(11, 0));
		//And thus be dead
		assertFalse(g.getPlayer().isAlive());
		//But we should have 50 points
		assertEquals(50, g.getPlayer().getPoints());
		
		//Undo trice
		g.undo();
		g.undo();
		g.undo();
		//We should now be at (8,0) with 30 points, alive and kicking
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(8, 0));
		assertEquals(30, g.getPlayer().getPoints());
		assertTrue(g.getPlayer().isAlive());
		
		//redo twice, we should be at (10,0) with 50 points
		g.redo();
		g.redo();
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(10, 0));
		assertEquals(50, g.getPlayer().getPoints());
		
		//Move left 9 times for the last bit of food
		for(int i = 0; i<9; i++)
			g.movePlayer(Direction.LEFT);
		
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(1, 0));
		assertEquals(60, g.getPlayer().getPoints());
	}
}
