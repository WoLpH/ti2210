package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableGhost extends Ghost implements IUndoableSprite {

	private final UndoableSpriteMixin mixin;

	/**
	 * Create a new undoable ghost, not on any tile yet.
	 */
	public UndoableGhost() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

	@Override
	public UndoableTile cloneTile() {
		return this.mixin.cloneTile();
	}

	@Override
	public void moveTo(UndoableGame game, Tile tile) {
		this.mixin.moveTo(game, tile);
	}
}
