package org.jpacman.framework.model;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoableFood extends Food implements IUndoableSprite {
	private final UndoableSpriteMixin mixin;

	/**
	 * Constructor meets invariant of sub and superclass.
	 */
	public UndoableFood() {
		super();
		this.mixin = new UndoableSpriteMixin(this);
	}

	@Override
	public UndoableTile cloneTile() {
		return this.mixin.cloneTile();
	}

	@Override
	public void moveTo(UndoableGame game, Tile tile) {
		if (getTile() == null && tile != null) {
			game.getPointManager().consumePointsOnBoard(game.getPlayer(),
					-Food.DEFAULT_POINTS);
		} else if (getTile() != null && tile == null) {
			game.getPointManager().consumePointsOnBoard(game.getPlayer(),
					Food.DEFAULT_POINTS);
		}
		this.mixin.moveTo(game, tile);
	}
}
