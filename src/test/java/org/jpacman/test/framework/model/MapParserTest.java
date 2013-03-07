package org.jpacman.test.framework.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Wall;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * test for the factory.MapParser, to run with a mocked Factory.
 **/
public class MapParserTest {

	private IGameFactory f = mock(IGameFactory.class);
	private MapParser mp;
	private Game game;
	private Player p;
	private Wall w;
	private Ghost g;
	private Food nom;

	/**
	 * Here I initialize the map on which I want to test the nice weather
	 * behavior.
	 */
	private String[] map = { "PG.# " };
	private int width = map[0].length();
	private int height = map.length;

	/**
	 * @Before Everything is initialized here, and all the things our mocked
	 *         Factory should are redirected to return the Objects we made
	 *         ourselves.
	 */
	@Before
	public void setup() {
		mp = new MapParser(f);
		game = new Game();
		Board b = new Board(width, height);
		game.setBoard(b);
		p = new Player();
		w = new Wall();
		g = new Ghost();
		nom = new Food();
		Mockito.when(f.makeGame()).thenReturn(game);
		Mockito.when(f.makeBoard(width, height)).thenReturn(b);
		Mockito.when(f.makePlayer()).thenReturn(p);
		Mockito.when(f.makeWall()).thenReturn(w);
		Mockito.when(f.makeGhost()).thenReturn(g);
		Mockito.when(f.makeFood()).thenReturn(nom);
	}

	// Following are nice weather tests.

	/**
	 * @Test Test whether the map is the expected height
	 */
	@Test
	public void testHeight() {
		assertEquals(1, map.length);
	}

	/**
	 * @Test Test whether the map is the expected width
	 */
	@Test
	public void testWidth() {
		assertEquals(5, map[0].length());
	}

	/**
	 * @Test Here we'll test whether the map parses nicely, one for one testing
	 *       whether every sprite type gets added correctly.
	 */
	@Test
	public void testAddPlayer() throws FactoryException {
		Game test = mp.parseMap(map);

		assertEquals(SpriteType.PLAYER, test.getBoard().tileAt(0, 0)
				.topSprite().getSpriteType());
	}

	@Test
	public void testAddGhost() throws FactoryException {
		Game test = mp.parseMap(map);

		assertEquals(SpriteType.GHOST, test.getBoard().tileAt(1, 0).topSprite()
				.getSpriteType());
	}

	@Test
	public void testAddFood() throws FactoryException {
		Game test = mp.parseMap(map);

		assertEquals(SpriteType.FOOD, test.getBoard().tileAt(2, 0).topSprite()
				.getSpriteType());
	}

	@Test
	public void testAddWall() throws FactoryException {
		Game test = mp.parseMap(map);

		assertEquals(SpriteType.WALL, test.getBoard().tileAt(3, 0).topSprite()
				.getSpriteType());
	}

	@Test
	public void testAddEmpty() throws FactoryException {
		Game test = mp.parseMap(map);

		assertEquals(null, test.getBoard().tileAt(4, 0).topSprite());
	}

	// Now following: the bad weather behavior tests!

	/**
	 * @Test Test whether the Parser throws the right exception when we try to
	 *       initialize an empty map.
	 */
	@Test(expected = FactoryException.class)
	public void badMapTest() throws FactoryException {
		String[] badMap = { "" };
		mp.parseMap(badMap);
	}

	@Test(expected = FactoryException.class)
	public void badCharTest() throws FactoryException {
		String[] badMap = { "S" };
		mp.parseMap(badMap);
	}
}
