package search;

import java.util.List;

/**
 * This class is used to solve generic search problems and is imported to
 * solve the Eight Puzzle game.
 * @param <T> - the type of a state within the given search problem
 */
public class Solver<T> {
  private final SearchProblem<T> problem;

  public Solver(SearchProblem<T> problem) {
    this.problem = problem;
  }

  /**
   * @return a solution to a problem that searches with Breadth First
   * Search in the form of a list of states.
   */
  public List<T> solveWithBFS() {
    Searcher<T> s = new QueueBasedBreadthFirstSearcher<T>(problem);
    return s.findSolution();
  }
}
