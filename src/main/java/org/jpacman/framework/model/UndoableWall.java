package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableWall extends Wall implements IUndoableSprite {
	private final UndoableSpriteMixin mixin;

	/**
	 * Create undoable wall.
	 */
	public UndoableWall() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

	@Override
	public UndoableTile cloneTile() {
		return this.mixin.cloneTile();
	}

}
