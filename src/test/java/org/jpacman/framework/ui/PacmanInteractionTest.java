package org.jpacman.framework.ui;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.PacmanInteraction.MatchState;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Test the sneak paths within the PacmanInteraction class.
 * 
 * @author Rick van Hattem <Rick.van.Hattem@Fawo.nl>
 */
@RunWith(Theories.class)
public class PacmanInteractionTest {
	/**
	 * A transition from one state to another using a given method. The methods and start sequences
	 * are strings and the function with these names will be executed using reflection.
	 */
	private static class Transition {
		private PacmanInteraction.MatchState state;
		private String[] methods;
		private final String board;
		private final String[] startSequence;

		/**
		 * @param state
		 *            The state to start with
		 * @param methods
		 *            THe method to execute (uses reflection)
		 * @param board
		 *            The filename of the board to load
		 * @param startSequence
		 *            The starting sequence to get to the expected state
		 */
		public Transition(String board, String[] startSequence,
				MatchState state, String[] methods) {
			this.board = board;
			this.startSequence = startSequence;
			this.state = state;
			this.methods = methods;
		}

		@Override
		public String toString() {
			return String.format("<%s:%s>", state, Arrays.toString(methods));
		}

	}

	@DataPoint
	public static final Transition WIN = new Transition("acceptanceMap.txt",
			new String[] { "start", "up", "right", "left", "down", "left" },
			MatchState.WON, new String[] { "start", "stop", "up", "down",
					"left", "right" });
	@DataPoint
	public static final Transition LOST = new Transition("acceptanceMap.txt",
			new String[] { "right" }, MatchState.LOST, new String[] { "start",
					"stop", "up", "down", "left", "right" });
	@DataPoint
	public static final Transition PAUSING = new Transition("simplemap.txt",
			new String[] { "stop" }, MatchState.PAUSING, new String[] { "stop",
					"up", "down", "left", "right" });
	@DataPoint
	public static final Transition PLAYING = new Transition("playing.txt",
			new String[] { "start" }, MatchState.PLAYING, new String[] {
					"start", "up", "down", "left", "right" });

	/**
	 * Testing Event with THeory.
	 * 
	 * @param transition
	 *            The transition with a begin/end state and list of methods to execute
	 * @throws IllegalAccessException
	 *             Required for using reflexion
	 * @throws FactoryException
	 *             Required for using reflexion
	 * @throws NoSuchFieldException
	 *             Required for using reflexion
	 * @throws NoSuchMethodException
	 *             Required for using reflexion
	 * @throws InvocationTargetException
	 *             Required for using reflexion
	 */
	@Theory
	public void testTransitions(Transition transition)
			throws IllegalAccessException, FactoryException,
			NoSuchFieldException, InvocationTargetException,
			NoSuchMethodException {

		/* Execute the test for every method */
		for (String method : transition.methods) {
			testTransition(transition, method);
		}
	}

	/**
	 * Set up a new MainUI with the given board and initialize everything.
	 * 
	 * @param board
	 * @return
	 * @throws FactoryException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private MainUI getUI(String board) throws FactoryException,
			NoSuchFieldException, IllegalAccessException {
		/* Setup the UI with the given board */
		MainUI ui = new MainUI();
		ui.withBoard(board);
		ui.initialize();
		ui.start();
		return ui;
	}

	private PacmanInteraction getPacmanInteraction(String board)
			throws NoSuchFieldException, IllegalAccessException,
			FactoryException {
		MainUI ui = getUI(board);
		PacmanInteraction pi = ui.eventHandler();

		/* If we are not in a PAUSING state, we didn't reflect properly */
		Assert.assertSame(getState(pi), MatchState.PAUSING);

		/* Start the game and check the state again */
		pi.start();
		Assert.assertSame(getState(pi), MatchState.PLAYING);

		return pi;
	}

	private MatchState getState(PacmanInteraction pi)
			throws NoSuchFieldException, IllegalAccessException {
		/* Little reflection hack to make everything accessible ;) */
		return (MatchState) getField(pi, "currentState");
	}

	/**
	 * @param transition
	 *            The transition to test
	 * @param method
	 *            The method to execute for this transition (since the Transition class contains
	 *            multiple methods)
	 * @throws FactoryException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	private void testTransition(Transition transition, String method)
			throws FactoryException, NoSuchFieldException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		PacmanInteraction pi = getPacmanInteraction(transition.board);

		/*
		 * Run a predefined sequence of methods to get to the wanted startup state
		 */
		for (String sequenceMethod : transition.startSequence) {
			PacmanInteraction.class.getDeclaredMethod(sequenceMethod)
					.invoke(pi);
		}

		/* Check if we are in the correct start state */
		Assert.assertSame(getState(pi), transition.state);
		/* Invoke the method using reflection */
		PacmanInteraction.class.getDeclaredMethod(method).invoke(pi);
		/* Check if we are in the correct end state */
		Assert.assertSame(getState(pi), transition.state);

	}

	/**
	 * Use reflection to get the field with the name fieldName from the instance.
	 * 
	 * @param instance
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private Object getField(Object instance, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		Class<? extends Object> klass = instance.getClass();
		Field field = klass.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(instance);
	}
}
