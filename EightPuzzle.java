package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import SearchProblem;
import Solver;

/**
 * This class represents the game of Eight Puzzle. In a 3x3 grid, eight tiles are 
 * labeled with numbers 1-8, with the ninth labeled with 0 representing an empty space. 
 * Tiles are swapped continuously in order to get the tiles in numerical order, with 
 * the empty space at the end. In this implementation, the tiles are an array list, 
 * with the solution being the numbers 1-8, 0 in that order in the returned array list.
 * More information about a similar 5x5 game can be found here: https://en.wikipedia.org/wiki/15_puzzle
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	List<Integer> values;

	/**
	 * The following constructor creates a new instance of an Eight Puzzle game with 
	 * a list of numbers 0-8 as the input. Exceptions are thrown if the input is of 
	 * the wrong size or does not contain the correct numbers.
	 * @param startingValues the starting values, 0 -- 8
	 * @throws IllegalArgumentException if startingValues is invalid
	 */
	public EightPuzzle(List<Integer> startingValues) {
		if (startingValues.size() != 9) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < 9; i++) {
			if (!startingValues.contains(i)) {
				throw new IllegalArgumentException();
			}
		}
		values = startingValues;
	}

	@Override
	public List<Integer> getInitialState() { //returns the initial array of values that were input into the constructor
		return this.values;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) { //returns a list of all possible states that can be found by swapping one tile
		List<Integer> cur = currentState;
		List<List<Integer>> successors = new ArrayList<List<Integer>>();
		int blank = currentState.indexOf(0);
		if (blank > 2) {
			successors.add(swap(cur, blank, blank - 3));
		}
		if (blank < 6) {
			successors.add(swap(cur, blank, blank + 3));
		}
		if (blank % 3 > 0) {
			successors.add(swap(cur, blank, blank - 1));
		}
		if (blank % 3 < 2) {
			successors.add(swap(cur, blank, blank + 1));
		}
		return successors;
	}

	@Override
	public boolean isGoal(List<Integer> state) { //checks if a current state is the final goal of [1, 2, ..., 8, 0]
		List<Integer> goal = new ArrayList<Integer>();
		for (int i = 1; i <= 8; i++) {
			goal.add(i);
		}
		goal.add(0);
		if (state.equals(goal)) {
			return true;
		}
		return false;
	}
	
	public List<Integer> swap(List<Integer> originalList, int index1, int index2) { //switches a tile with the empty space and returns the new state of the game
		List<Integer> newList = new ArrayList<Integer>(originalList);
		Integer temp = originalList.get(index1);
		newList.set(index1, originalList.get(index2));
		newList.set(index2, temp);
		return newList;
	}

	/**
	 * This main method plays one game of Eight Puzzle. New numbers can be typed into the input list to test different games.
	 */
	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}

}
