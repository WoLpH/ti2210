package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
interface IUndoableSprite {
	/**
	 * @return A tile with the same X and Y coordinates as the tile this sprite occupies.
	 */
	UndoableTile cloneTile();

	/**
	 * @param game
	 *            The game to apply any needed changes to.
	 * @param tile
	 *            The tile to move to.
	 */
	void moveTo(UndoableGame game, Tile tile);
}
