package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableGhost extends Ghost implements IUndoableSprite {

	/**
	 * Create a new undoable ghost, not on any tile yet.
	 */
	public UndoableGhost() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

}
