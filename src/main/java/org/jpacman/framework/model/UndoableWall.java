package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableWall extends Wall implements IUndoableSprite {

	/**
	 * Create undoable wall.
	 */
	public UndoableWall() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

}
