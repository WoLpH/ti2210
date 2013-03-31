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
		redoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				redo();
			}
		});
		return redoButton;
	}

	/**
	 * @return A new button to undo the game.
	 */
	private JButton createUndoButton() {
		JButton undoButton = new JButton("Undo");
		undoButton.setEnabled(false);
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		return undoButton;
	}

	@Override
	public UndoablePacmanInteraction getPacmanInteractor() {
		return (UndoablePacmanInteraction) super.getPacmanInteractor();
	}

	@Override
	public void initialize() {
		this.undoButton = createUndoButton();
		this.redoButton = createRedoButton();
		addButton(this.undoButton);
		super.initialize();
		addButton(this.redoButton);
	}

	/**
	 * Redo the last undo'ed move.
	 */
	private void redo() {
		assert invariant();
		getPacmanInteractor().redo();
		assert invariant();
	}

	/**
	 * Undo the last move.
	 */
	public void undo() {
		assert invariant();
		pause();
		// TODO Implement the actual undo
		assert invariant();
	}
}
