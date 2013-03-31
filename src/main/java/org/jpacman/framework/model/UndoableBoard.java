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

	/**
	 * @return All sprites in this board.
	 */
	public HashMap<SpriteType, ArrayList<IUndoableSprite>> getSprites() {
		return this.sprites;
	}

	@Override
	public void put(Sprite s, int x, int y) {
		super.put(s, x, y);
		if (s instanceof IUndoableSprite) {
			this.sprites.get(s.getSpriteType()).add((IUndoableSprite) s);
		} else {
			throw new RuntimeException(
					"Was expecting an IUndoableSprite instance, got "
							+ s.getClass());
		}
	}

}
