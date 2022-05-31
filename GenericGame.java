package search;

import java.util.List;

/**
 * This interface is of a generic search problem that is used as a base for the 
 * Eight Puzzle game.
 * @param <T>
 *
 */
public interface GenericGame<T> {
  /** getInitialState.
   * @return the starting state of a given search problem
   */
  T getInitialState();

  /**
   * @param currentState : current state
   * @return the list of states adjacent to the current state
   */
  List<T> getSuccessors(T currentState);

  /** isGoal.
   * @param state : state
   * @return true iff the given state is the desired end goal state
   */
  boolean isGoal(T state);
}
