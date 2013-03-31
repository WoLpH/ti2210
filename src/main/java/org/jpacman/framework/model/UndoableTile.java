package org.jpacman.framework.model;

/**
 * Undoable TIle which can easily copy an existing tile.
 * 
 * @author Rick
 */
class UndoableTile extends Tile {
	/**
	 * @param tile
	 *            Tile to copy the x and y coordinates from.
	 */
	UndoableTile(Tile tile) {
		super(tile.getX(), tile.getY());
	}

}
