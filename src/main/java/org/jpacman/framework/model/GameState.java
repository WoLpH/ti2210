package org.jpacman.framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.jpacman.framework.model.IBoardInspector.SpriteType;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
class GameState {
	private final HashMap<SpriteType, ArrayList<UndoableTile>> tiles;

	/**
	 * Construct GameState from Board.
	 * 
	 * @param board
	 *            THe board that has to be cloned
	 */
	GameState(UndoableBoard board) {
		this.tiles = new HashMap<IBoardInspector.SpriteType, ArrayList<UndoableTile>>();

		for (SpriteType spriteType : SpriteType.values()) {
			ArrayList<IUndoableSprite> sprites = board.getSprites().get(
					spriteType);
			this.tiles.put(spriteType, new ArrayList<UndoableTile>());
			for (IUndoableSprite sprite : sprites) {
				this.tiles.get(spriteType).add(sprite.cloneTile());
			}
		}
	}

	/**
	 * @param game
	 *            Apply this state to the given game
	 */
	void restoreTo(UndoableGame game) {
		/*
		 * Always resurrect the player just in case. It's impossible to add points when the player
		 * is dead and the player sprite will reset the alive status anyhow.
		 */
		game.getPlayer().resurrect();

		for (SpriteType spriteType : SpriteType.values()) {
			/*
			 * Skip the player, we have to process this last since we cannot process food if the
			 * player is dead
			 */
			if (spriteType != SpriteType.PLAYER) {
				restoreTo(game, spriteType);
			}
		}

		restoreTo(game, SpriteType.PLAYER);
	}

	/**
	 * @param game
	 *            Apply this state to the given game
	 * @param spriteType
	 *            The sprite type to restore
	 */
	void restoreTo(UndoableGame game, SpriteType spriteType) {
		UndoableBoard board = game.getBoard();
		/* Match all tiles in our sprite collection with the tiles in the given state */
		Iterator<UndoableTile> tiles = this.tiles.get(spriteType).iterator();
		for (IUndoableSprite sprite : board.getSprites().get(spriteType)) {
			sprite.moveTo(game, board.tileAt(tiles.next()));
		}
	}
}
