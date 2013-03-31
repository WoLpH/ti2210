package org.jpacman.framework.factory;

import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.UndoableBoard;
import org.jpacman.framework.model.UndoableFood;
import org.jpacman.framework.model.UndoableGame;
import org.jpacman.framework.model.UndoableGhost;
import org.jpacman.framework.model.UndoablePlayer;
import org.jpacman.framework.model.UndoableWall;
import org.jpacman.framework.model.Wall;
import org.jpacman.framework.ui.UndoablePacman;

/**
 * Identical to {@link DefaultGameFactory} except that it returns an instance of
 * {@link UndoableGame} instead of a Game instance and an instance of {@link UndoableBoard} instead
 * of Board.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class UndoableGameFactory extends DefaultGameFactory {
	private UndoableGame undoableGame;
	private final UndoablePacman undoablePacman;

	/**
	 * Create a new UndoableGameFactory.
	 * 
	 * @param undoablePacman
	 *            UndoablePacman link for callbacks from undo/redo buttons.
	 */
	public UndoableGameFactory(UndoablePacman undoablePacman) {
		this.undoablePacman = undoablePacman;
	}

	@Override
	protected Game getGame() {
		assert this.undoableGame != null;
		return this.undoableGame;
	}


	@Override
	public UndoableFood makeFood() {
		assert getGame() != null;
		UndoableFood food = new UndoableFood();
		getGame().addFood(food);
		return food;
	}

	@Override
	public Game makeGame() {
		this.undoableGame = new UndoableGame();
		return this.undoableGame;
	}

	@Override
	public Ghost makeGhost() {
		assert getGame() != null;
		UndoableGhost ghost = new UndoableGhost();
		getGame().addGhost(ghost);
		return ghost;
	}

}
