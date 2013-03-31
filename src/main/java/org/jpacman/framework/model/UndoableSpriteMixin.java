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

	@Override
	public void moveTo(UndoableGame game, Tile newTile) {
		Tile oldTile = this.sprite.getTile();
		if ((oldTile == null && newTile != null)
				|| (oldTile != null && newTile == null) || (oldTile != null)
				&& !oldTile.equals(newTile)) {
			if (this.sprite.getTile() != null) {
				this.sprite.deoccupy();
			}
			if (newTile != null) {
				this.sprite.occupy(newTile);
			}
		}
	}
}
