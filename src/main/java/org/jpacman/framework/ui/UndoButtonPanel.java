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
	private PacmanInteraction pacmanInteractor;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2798148329579893037L;

	/**
	 * @return A new button to redo the game.
	 */
	protected JButton createRedoButton() {
		JButton redoButton = new JButton("Redo");
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
	protected JButton createUndoButton() {
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		return undoButton;
	}

	/**
	 * Obtain the handler capable of dealing with button events.
	 * 
	 * @return The pacman interactor.
	 */
	@Override
	public IPacmanInteraction getPacmanInteractor() {
		return this.pacmanInteractor;
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
	public void redo() {
		// TODO Auto-generated method stub

	}

	/**
	 * Undo the last move.
	 */
	public void undo() {
		// TODO Auto-generated method stub

	}

	/**
	 * Set the listener capable of exercising the requested events.
	 * 
	 * @param pi
	 *            The new pacman interactor
	 * @return Itself for fluency.
	 */
	@Override
	public ButtonPanel withInteractor(PacmanInteraction pi) {
		this.pacmanInteractor = pi;
		pi.addObserver(this);
		return this;
	}

}
