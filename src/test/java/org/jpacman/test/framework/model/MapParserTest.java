package org.jpacman.test.framework.model;
import static org.junit.Assert.assertEquals;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Tile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *  test for the factory.MapParser, to run with mocks  
 **/
@RunWith(MockitoJUnitRunner.class)
public class MapParserTest{
	private IGameFactory f;
	private MapParser mp;
	
	public MapParserTest(){
		mp = new MapParser(f);
	}
	 	
	@Test
	public void parseMapTest() throws FactoryException{
		String[] a = {"P...#"};
		mp.parseMap(a);
		
		assertEquals(SpriteType.PLAYER, mp.theBoard.tileAt(0,0).topSprite().getSpriteType());
	}
}