/**
 * 
 */
package org.jpacman.test.framework.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.factory.UndoableGameFactory;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.UndoableBoard;
import org.jpacman.framework.model.UndoableFood;
import org.jpacman.framework.model.UndoableGame;
import org.jpacman.framework.model.UndoableGhost;
import org.jpacman.framework.model.UndoablePlayer;
import org.jpacman.framework.model.UndoableWall;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.ui.UndoablePacman;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
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
	
	@Test
	public void testInitial() throws FactoryException{
		UndoableGame g = makePlay("P");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		assertThat(tileAt(g, 0, 0), equalTo(g.getPlayer().getTile()));
		assertEquals(SpriteType.PLAYER, g.getBoard().spriteTypeAt(0, 0));
		assertEquals(0, g.getPlayer().getPoints());
		assertTrue(g.getPlayer().isAlive());
		assertEquals(Direction.LEFT, g.getPlayer().getDirection());
	}
	
	@Test
	public void canUndoTest() throws FactoryException{
		UndoableGame g = makePlay("P");
		assertFalse(g.canUndo());
	}
	
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
	
	
}
