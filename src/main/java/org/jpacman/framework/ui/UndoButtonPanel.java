/**
 * 
 */
package org.jpacman.framework.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 * 
 */
public class UndoButtonPanel extends ButtonPanel {
	/**
	 * Action listener for redo button.
	 * 
	 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
	 */
	private final class RedoActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			redo();
		}
	}

	/**
	 * Action listener for undo button.
	 * 
	 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
	 */
	private final class UndoActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			undo();
		}
	}

	private JButton undoButton;
	private JButton redoButton;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2798148329579893037L;

	/**
	 * @return A new button to redo the game.
	 */
	private JButton createRedoButton() {
		JButton redoButton = new JButton("Redo");
		redoButton.setEnabled(false);
		redoButton.addActionListener(new RedoActionListener());
		return redoButton;
	}

	/**
	 * @return A new button to undo the game.
	 */
	private JButton createUndoButton() {
		JButton undoButton = new JButton("Undo");
		undoButton.setEnabled(false);
		undoButton.addActionListener(new UndoActionListener());
		return undoButton;
	}

	@Override
	public UndoablePacmanInteraction getPacmanInteractor() {
		return (UndoablePacmanInteraction) super.getPacmanInteractor();
	}

	/**
	 * @return the redoButton
	 */
	public JButton getRedoButton() {
		return this.redoButton;
	}

	/**
	 * @return the undoButton
	 */
	public JButton getUndoButton() {
		return this.undoButton;
	}

	@Override
	public void initialize() {
		setUndoButton(createUndoButton());
		setRedoButton(createRedoButton());
		addButton(getUndoButton());
		super.initialize();
		addButton(getRedoButton());
	}

	/**
	 * Redo the last undo'ed move.
	 */
	public void redo() {
		getPacmanInteractor().redo();
	}

	/**
	 * @param redoButton
	 *            the redoButton to set
	 */
	public void setRedoButton(JButton redoButton) {
		this.redoButton = redoButton;
	}

	/**
	 * @param undoButton
	 *            the undoButton to set
	 */
	public void setUndoButton(JButton undoButton) {
		this.undoButton = undoButton;
	}

	/**
	 * @param enable
	 *            Whether to enable the redo button
	 */
	public void toggleRedo(boolean enable) {
		getRedoButton().setEnabled(enable);
	}

	/**
	 * @param enable
	 *            Whether to enable the undo button
	 */
	public void toggleUndo(boolean enable) {
		getUndoButton().setEnabled(enable);
	}

	/**
	 * Undo the last move.
	 */
	public void undo() {
		getPacmanInteractor().undo();
	}
}
