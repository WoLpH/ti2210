package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoablePlayer extends Player implements IUndoableSprite {

	private final UndoableSpriteMixin mixin;

	/**
	 * Create a new undoable player.
	 */
	public UndoablePlayer() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

	@Override
	public UndoableTile cloneTile() {
		return this.mixin.cloneTile();
	}

}
