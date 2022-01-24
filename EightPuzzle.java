package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle. The spaces in an
 * 8-puzzle are indexed as follows: 0 | 1 | 2 --+---+--- 3 | 4 | 5 --+---+--- 6
 * | 7 | 8 The puzzle contains the eight numbers 1-8, and an empty space. If we
 * represent the empty space as 0, then the puzzle is solved when the values in
 * the puzzle are as follows: 1 | 2 | 3 --+---+--- 4 | 5 | 6 --+---+--- 7 | 8 |
 * 0 That is, when the space at index 0 contains value 1, the space at index 1
 * contains value 2, and so on. From any given state, you can swap the empty
 * space with a space adjacent to it (that is, above, below, left, or right of
 * it, without wrapping around). For example, if the empty space is at index 2,
 * you may swap it with the value at index 1 or 5, but not any other index. Only
 * half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle for details.
 * 
 * 
 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	List<Integer> values;

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values. The
	 * values are indexed as described above, and should contain exactly the nine
	 * integers from 0 to 8.
	 * 
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
	public List<Integer> getInitialState() {
		// TODO
		return this.values;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
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
	public boolean isGoal(List<Integer> state) {
		// TODO
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

	/**
	 * supporting main method.
	 */
	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}

	public List<Integer> swap(List<Integer> originalList, int index1, int index2) {
		List<Integer> newList = new ArrayList<Integer>(originalList);
		Integer temp = originalList.get(index1);
		newList.set(index1, originalList.get(index2));
		newList.set(index2, temp);
		return newList;
	}
}
