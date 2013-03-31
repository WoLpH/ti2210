package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoablePlayer extends Player implements IUndoableSprite {

	/**
	 * Create a new undoable player.
	 */
	public UndoablePlayer() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

}
