package org.jpacman.test.framework.accept;

import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.UndoablePacman;

/**
 * Story test for the undoable pacman.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
public class UndoStoryTest extends MovePlayerStoryTest {
	private UndoablePacman pacman;

	@Override
	protected MainUI getUI() {
		return this.pacman;
	}

	@Override
	public MainUI makeUI() {
		this.pacman = new UndoablePacman();
		return this.pacman;
	}
}
