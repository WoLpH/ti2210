package org.jpacman.framework.model;

import java.util.ArrayList;

import org.jpacman.framework.ui.UndoButtonPanel;
import org.jpacman.framework.ui.UndoablePacman;

/**
 * Special version of the Game which implements and undo function.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class UndoableGame extends Game {
	/*
	 * Since I'd like to have both undo and redo, a deque is not too convenient. The normal iterator
	 * only goes 1 way
	 */
	private ArrayList<GameState> states;
	private int stateIndex;
	private final UndoablePacman undoablePacman;

	/**
	 * Initialize the states history for the games.
	 * 
	 * @param undoablePacman
	 *            The undoable pacman to implement undo/redo.
	 */
	public UndoableGame(UndoablePacman undoablePacman) {
		this.states = new ArrayList<GameState>();
		this.undoablePacman = undoablePacman;
		this.stateIndex = 0;
	}

}
