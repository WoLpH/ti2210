/**
 * 
 */
package org.jpacman.framework.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableBoard extends Board {
	private final HashMap<SpriteType, ArrayList<IUndoableSprite>> sprites;

	/**
	 * Create a new board.
	 * 
	 * @param w
	 *            Width of the board
	 * @param h
	 *            Height of the board
	 */
	public UndoableBoard(int w, int h) {
		super(w, h);
		this.sprites = new HashMap<SpriteType, ArrayList<IUndoableSprite>>();
		for (SpriteType spriteType : SpriteType.values()) {
			this.sprites.put(spriteType, new ArrayList<IUndoableSprite>());
		}
	}

}
