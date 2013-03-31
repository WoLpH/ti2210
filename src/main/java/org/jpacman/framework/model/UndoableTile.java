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

	/**
	 * @param obj
	 *            The tile to compare to
	 * @return True if the coordinates of the two tiles are identical
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tile) {
			Tile tile = (Tile) obj;
			return getX() == tile.getX() && getY() == tile.getY();
		} else {
			return false;
		}
	}
}
