package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
class UndoableSpriteMixin extends Sprite implements IUndoableSprite {

	/**
	 * @param sprite
	 *            The sprite to use as base
	 */
	UndoableSpriteMixin(Sprite sprite) {
		this.sprite = sprite;
	}

}
