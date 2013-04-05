package org.jpacman.framework.ui;

import org.jpacman.framework.model.UndoableGame;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoablePacmanInteraction extends PacmanInteraction {
	/**
	 * Redo the last undoed move.
	 */
	void redo() {
		updateState();
		UndoableGame game = (UndoableGame) getGame();
		game.redo();
		updateState();
	}

	/**
	 * Undo the last move.
	 */
	void undo() {
		updateState();
		UndoableGame game = (UndoableGame) getGame();
		game.undo();
	}
}
