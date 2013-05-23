package edu.ucsb.cs56.projects.games.memorycard;

/**
 * @author Ryan Halbrook
 * @version CS56 Spring 2013
   Class representing a level of the memory game including time to complete
   the level, time to flip two cards back over and the size of the grid
 */

public class MemoryGameLevel {

    private int gridSize;
    private int secondsToSolve;
    private int millisecondsToFlip;

    /**
       Constructor to fully initialize the level
       @param gridSize the grid size for this level
       @param secondsToSolve the target time in seconds to complete the level
       @param flipTime the time in milliseconds before two cards flip back over
     */
    public MemoryGameLevel(int gridSize, int secondsToSolve, int flipTime) {
	this.gridSize = gridSize;
	this.secondsToSolve = secondsToSolve;
	this.millisecondsToFlip = flipTime;
    }
    
    /**
       Get the grid size for this level
       @return the number of squares in the grid
     */
    public int getGridSize() { return gridSize; }
  
    /**
       Get the target time for completing the level
       @return the target time in seconds for completing the level
     */
    public int getSecondsToSolve() { return secondsToSolve; }

    /**
       Get the delay time befoe two cards flip back over
       @return the time in milliseconds before the two flipped cars turn back over
     */
    public int getFlipTime() { return millisecondsToFlip; }
}
