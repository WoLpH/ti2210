package org.jpacman.framework.ui;

import org.jpacman.framework.model.UndoableGame;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
class UndoablePacmanInteraction extends PacmanInteraction {

	/**
	 * Undo the last move.
	 */
	void undo() {
		updateState();
		UndoableGame game = (UndoableGame) getGame();
		game.undo();
	}
}
