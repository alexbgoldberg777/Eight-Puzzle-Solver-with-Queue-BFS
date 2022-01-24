package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This implementation of the Breadth First Search algorithm utilizes a queue to search for 
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	/**
	 * QueueBasedBreadthFirstSearcher.
	 * 
	 * @param searchProblem : search problem
	 */
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		Queue<T> queue = new LinkedList<T>();
		List<T> states = new ArrayList<T>();
		List<T> predecessors = new ArrayList<T>();
		T start = searchProblem.getInitialState();
		queue.add(start);
		states.add(start);
		predecessors.add(start);
		// visited.add(start);
		T current;
		boolean flag = false;
		List<T> results = new ArrayList<T>();
		while (!queue.isEmpty()) {
			current = queue.remove();
			visited.add(current);
			if (searchProblem.isGoal(current)) {
				flag = true;
				results.add(current);
				break;
			} else {
				for (T neighbor : searchProblem.getSuccessors(current)) {
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
		if (flag) {
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
/*
 * queue, states, predecessors while queue isn't empty, put first node in queue,
 * then take it off and add to visited (look at slides to see example) if this
 * is the goal, break while loop else getsuccessors(currentNode) add all
 * unvisited to queues, states, and predecessors if current node is goal, break
 * loop, make a new results list (path from start to finish), start from goal,
 * add predecessor to results, then add that predecessor to results, continue
 * reverse this list and return
 */
