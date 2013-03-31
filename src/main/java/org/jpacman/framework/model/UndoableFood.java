package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableFood extends Food implements IUndoableSprite {

	/**
	 * Constructor meets invariant of sub and superclass.
	 */
	public UndoableFood() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

}
