package org.jpacman.framework.factory;

import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.UndoableGame;

/**
 * Identical to {@link DefaultGameFactory} except that it returns an instance of
 * {@link UndoableGame} instead of a Game instance.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class UndoableGameFactory extends DefaultGameFactory {
	private UndoableGame undoableGame;

	@Override
	protected Game getGame() {
		assert this.undoableGame != null;
		return this.undoableGame;
	}

	@Override
	public Game makeGame() {
		this.undoableGame = new UndoableGame();
		return this.undoableGame;
	}
}
