package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This implementation of the Breadth First Search algorithm utilizes a queue to search for 
 * successive states to solve an Eight Puzzle game.
 */
public class BFSQueue<T> extends Searcher<T> {

	/**
	 * @param searchProblem : search problem
	 */
	public BFSQueue(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}
	
	/** 
	* This findSolution method returns the goal of a BFS search. Specifically, it utilizes
	* the Eight Puzzle's methods to find the goal of a game of Eight Puzzle and returns the
	* necessary swaps for solving the game.
	*/
	@Override
	public List<T> findSolution() {
		Queue<T> queue = new LinkedList<T>(); //These initializations include a queue to temporarily store non-fully-explored states,
		List<T> states = new ArrayList<T>();  //a list of explored states of the game, a list of predecessors to discovered nodes/tiles,
		List<T> predecessors = new ArrayList<T>(); //the starting state, the currently-explored state, a flag for if the current state
		T start = searchProblem.getInitialState(); //is the solution, and the list of resulting states of the search.
		queue.add(start);
		states.add(start);
		predecessors.add(start);
		T current;
		boolean flag = false;
		List<T> results = new ArrayList<T>();
		while (!queue.isEmpty()) { //Performs a traversal in Breadth First Search order to find the goal state.
			current = queue.remove();
			visited.add(current);
			if (searchProblem.isGoal(current)) { //If the current state is goal, stop searching.
				flag = true;
				results.add(current);
				break;
			} else { 			     //Adds all states to the appropriate lists for if they have been discovered or fully-explored
				for (T neighbor : searchProblem.getSuccessors(current)) { //yet according to the standard BFS algorithm.
					if (!visited.contains(neighbor)) {
						if (!states.contains(neighbor)) {
							states.add(neighbor);
							predecessors.add(neighbor);
						}
						predecessors.set(states.indexOf(neighbor), current);
						queue.add(neighbor);
					}
				}
			}
		}
		if (flag) { //If the goal has been found, the list of predecessors is used to place the states in order of the steps to reach the goal.
			T current1 = results.get(0);
			while (!current1.equals(searchProblem.getInitialState())) {
				T predecessor = predecessors.get(states.indexOf(current1));
				results.add(predecessor);
				current1 = predecessor;
			}
			Collections.reverse(results);
		}
		return results;
	}
}
