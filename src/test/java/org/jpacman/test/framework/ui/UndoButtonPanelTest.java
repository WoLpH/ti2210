package org.jpacman.test.framework.ui;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.UndoButtonPanel;
import org.jpacman.framework.ui.UndoablePacman;
import org.jpacman.framework.ui.UndoablePacmanInteraction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
@RunWith(MockitoJUnitRunner.class)
public class UndoButtonPanelTest {
	private UndoablePacman pacman;
	private UndoButtonPanel buttonPanel;

	/**
	 * Set up the undoable pacman interface.
	 * 
	 * @throws FactoryException
	 *             Shouldn't happen
	 */
	@Before
	public void setUp() throws FactoryException {
		this.pacman = new UndoablePacman();
		this.pacman.initialize();
		this.buttonPanel = this.pacman.getButtonPanel();
	}

	/**
	 * Testing the adding of null buttons.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddButton() {
		try {
			this.buttonPanel.addButton(null);
		} catch (AssertionError e) {
			throw new NullPointerException();
		}
	}

	/**
	 * Test the pacman interaction getter.
	 */
	@Test
	public void testPacmanInteractor() {
		Assert.assertEquals(this.buttonPanel.getPacmanInteractor().getClass(),
				UndoablePacmanInteraction.class);
	}

	/**
	 * Test the redo button.
	 */
	@Test
	public void testRedoButton() {
		this.buttonPanel.toggleRedo(false);
		this.buttonPanel.toggleRedo(true);
		this.buttonPanel.redo();
		this.buttonPanel.getRedoButton().doClick();
	}

	/**
	 * Test the start/stop buttons in enabled/disabled state.
	 */
	@Test
	public void testStartStopButtons() {
		this.buttonPanel.undo();
		this.buttonPanel.start();
		this.buttonPanel.undo();
		this.buttonPanel.pause();
		this.buttonPanel.undo();
	}

	/**
	 * Test the undo button.
	 */
	@Test
	public void testUndoButton() {
		this.buttonPanel.toggleUndo(false);
		this.buttonPanel.toggleUndo(true);
		this.buttonPanel.undo();
		this.buttonPanel.getUndoButton().doClick();
	}
}
