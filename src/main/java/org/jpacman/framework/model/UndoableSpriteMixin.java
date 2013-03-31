package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
class UndoableSpriteMixin extends Sprite implements IUndoableSprite {
	private final Sprite sprite;

	/**
	 * @param sprite
	 *            The sprite to use as base
	 */
	UndoableSpriteMixin(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public UndoableTile cloneTile() {
		Tile tile = this.sprite.getTile();
		if (tile != null) {
			return new UndoableTile(tile);
		} else {
			return null;
		}
	}

}
