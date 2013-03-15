/**
 * 
 */
package org.jpacman.framework.ui;

import org.jpacman.framework.factory.FactoryException;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoablePacman extends MainUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1938866969221252775L;

	@Override
	public MainUI initialize() throws FactoryException {
		withButtonPanel(new UndoButtonPanel());
		return super.initialize();
	}

	/**
	 * Main tarting point of the undoable pacman game.
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
	 * Undo the last move.
	 */
	void undo() {

	}
}
