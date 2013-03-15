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
	 * Set the listener capable of exercising the requested events.
	 * 
	 * @param pi
	 *            The new pacman interactor
	 * @return Itself for fluency.
	 */
	public ButtonPanel withInteractor(PacmanInteraction pi) {
		pacmanInteractor = pi;
		pi.addObserver(this);
		return this;
	}

	/**
	 * Obtain the handler capable of dealing with button events.
	 * 
	 * @return The pacman interactor.
	 */
	public IPacmanInteraction getPacmanInteractor() {
		return pacmanInteractor;
	}

	@Override
	public void initialize() {
		undoButton = createUndoButton();
		redoButton = createRedoButton();
		addButton(undoButton);
		super.initialize();
		addButton(redoButton);
	}

	/**
	 * @return A new button to undo the game.
	 */
	protected JButton createUndoButton() {
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: implement the undo method
			}
		});
		return undoButton;
	}

	
	/**
	 * @return A new button to redo the game.
	 */
	protected JButton createRedoButton() {
		JButton redoButton = new JButton("Redo");
		redoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: implement the redo method
			}
		});
		return redoButton;
	}

}
