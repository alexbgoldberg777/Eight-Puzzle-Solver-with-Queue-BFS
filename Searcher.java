package search;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic class used to search for a solution over given nodes and the states which can
 * be reached with them.
 */
public abstract class Searcher<T> {
	protected final SearchProblem<T> searchProblem;
	protected final List<T> visited;
	protected List<T> solution;

	/**
	 * Constructs a Searcher.
	 * 
	 * @param searchProblem the search problem for which this searcher will find and
	 *                      validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
		this.visited = new ArrayList<T>();
	}

	/**
	 * @return a solution to the given problem, which will contain a list of states in
	 * order of how they were traversed.
	 */
	public abstract List<T> findSolution();
}
