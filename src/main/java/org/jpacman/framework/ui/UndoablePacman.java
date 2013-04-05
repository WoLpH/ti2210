/**
 * 
 */
package org.jpacman.framework.ui;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.UndoableGameFactory;
import org.jpacman.framework.model.UndoableGame;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoablePacman extends MainUI {
	private UndoButtonPanel buttonPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1938866969221252775L;

	/**
	 * Main starting point of the undoable pacman game.
	 * 
	 * @param args
	 *            Ignored
	 * @throws FactoryException
	 *             If reading game map fails.
	 */
	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

	/**
	 * @return The current button panel
	 */
	public UndoButtonPanel getButtonPanel() {
		return this.buttonPanel;
	}

	@Override
	public UndoableGame getGame() {
		return (UndoableGame) super.getGame();
	}

	@Override
	public MainUI initialize() throws FactoryException {
		withFactory(new UndoableGameFactory(this));
		this.buttonPanel = new UndoButtonPanel();
		withButtonPanel(getButtonPanel());
		withModelInteractor(new UndoablePacmanInteraction());
		return super.initialize();
	}
}