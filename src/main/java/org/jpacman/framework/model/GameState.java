package org.jpacman.framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
		UndoableBoard board = game.getBoard();
		for (Entry<SpriteType, ArrayList<UndoableTile>> entry : this.tiles
				.entrySet()) {
			SpriteType spriteType = entry.getKey();
			ArrayList<IUndoableSprite> sprites = board.getSprites().get(
					spriteType);
			Iterator<UndoableTile> tiles = entry.getValue().iterator();

			for (IUndoableSprite sprite : sprites) {
				sprite.moveTo(game, board.tileAt(tiles.next()));
			}
		}
	}
}
