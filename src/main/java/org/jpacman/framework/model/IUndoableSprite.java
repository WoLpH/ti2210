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

}
