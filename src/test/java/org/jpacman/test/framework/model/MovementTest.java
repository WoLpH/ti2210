package org.jpacman.test.framework.model;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.model.Wall;
import org.jpacman.framework.ui.PacmanInteraction;
import org.junit.Test;

public class MovementTest{
	private final Player pacman;
	private final PacmanInteraction pmi;
	private final Wall wall;
	
	public MovementTest(){
		pacman = new Player();
		pmi = new PacmanInteraction();
		wall = new Wall();
	}
	
	@Test
	public void testMovementInUpDirection(){
		Tile t1 = new Tile(1,1);
		Tile t2 = new Tile(1,2);
		pacman.occupy(t1);
		pmi.movePlayer(Direction.UP);
		
		assertEquals(pacman.getTile(),t2);
	}
	
	@Test
	public void testMovementInDownDirection(){
		Tile t1 = new Tile(1,1);
		Tile t2 = new Tile(1,0);
		pacman.occupy(t1);
		pmi.movePlayer(Direction.DOWN);
		
		assertEquals(pacman.getTile(),t2);
	}
	
	@Test
	public void testMovementInRightDirection(){
		Tile t1 = new Tile(1,1);
		Tile t2 = new Tile(2,1);
		pacman.occupy(t1);
		pmi.movePlayer(Direction.RIGHT);
		
		assertEquals(pacman.getTile(),t2);
	}
	
	@Test
	public void testMovementInLeftDirection(){
		Tile t1 = new Tile(1,1);
		Tile t2 = new Tile(0,1);
		pacman.occupy(t1);
		pmi.movePlayer(Direction.LEFT);
		
		assertEquals(pacman.getTile(),t2);
	}
	
	@Test
	public void testMovementAgainstWall(){
		Tile t1 = new Tile(1,1);
		Tile t2 = new Tile(1,2);
		pacman.occupy(t1);
		wall.occupy(t2);
		pmi.movePlayer(Direction.UP);
		
		assertEquals(pacman.getTile(),t1);
	}
	
	@Test
	public void testMovementToFood(){
		Tile t1 = new Tile(1,1);
		Tile t2 = new Tile(1,2);
		Food nom = new Food();
		
		pacman.occupy(t1);
		nom.occupy(t2);
		pmi.movePlayer(Direction.UP);
		
		assertEquals(pacman.getTile(), t2);
		assertEquals(nom.getTile(), not((t2)));
	}
}