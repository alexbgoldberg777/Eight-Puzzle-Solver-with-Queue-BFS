# Eight-Puzzle-Solver-with-Queue-BFS
This program plays and solves the Eight-Puzzle game (more information here: https://en.wikipedia.org/wiki/15_puzzle). In this game, a 3x3 grid has eight tiles labeled 1-8, with a tile labeled 0 representing an empty space. Tiles are swapped continuously with the empty space in order to get the tiles in numerical order, with the empty space at the bottom-right of the grid. In this implementation, the tiles are an array list, with the solution being the numbers 1-8, 0 in that order in the returned array list. EightPuzzle.java is where the game can be played. To play, run the main method of that file after changing the numbers in EightPuzzle e to any of your liking.

The game relies on an implementation of Breadth First Search that uses a queue in order to traverse the possible moves that can be made given the current state of the grid (found in QueueBasedBreadthFirstSearcher.java). SearchProblem.java and Searcher.java are interfaces that the main game and search code use as a base to perform the game's actions. Solver.java contains the code that uses the search file to find the solution to the game.


**Dependencies**

GenericGame.java: java.util.List \
Searcher.java: java.util.ArrayList, java.util.List \
BFSQueue.java: java.util.ArrayList, java.util.Collections, java.util.LinkedList, java.util.List, java.util.Queue, Searcher.java \
Solver.java: java.util.List, BFSQueue.java \
EightPuzzle.java: java.util.Arraylist, java.util.Arrays, java.util.Collections, java.util.List, GenericGame.java, Solver.java
