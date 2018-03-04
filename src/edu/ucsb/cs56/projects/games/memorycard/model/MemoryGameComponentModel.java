package edu.ucsb.cs56.projects.games.memorycard;

import java.awt.*;
import java.awt.event.*; // for ActionListener and ActionEvent
import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Math;
import java.io.*;
import javax.sound.sampled.*;

/**
 * A Swing component for playing the Memory Card Game
 *
 * @author Bryce McGaw and Jonathan Yau (with some of Phill Conrad's code as a basis)
 * @author Ryan Halbrook and Yun Suk Chang
 * @author Mathew Glodack, Christina Morris
 * @author Xiaohe He, Shaoyi Zhang
 * @author Hyemin Yoo
 * @author Annan Zhang, Shihua Lu
 * @version CS56 Winter 2018
 * @see MemoryGrid
 */
public class MemoryGameComponentModel implements ActionListener {
    private MemoryGrid grid;
    private int currentLevel;
    private int totalLevels = 4;
    private MemoryGameLevel[] levels;
    private MemoryGameLevel level = new MemoryGameLevel(36, 100, 2000);

    private int gameCounter = 0;
    private long startTime = 0; // used to calculate the total game time.
    private boolean cheatEnabled = false; // Cheat code related.
    private boolean isOver = false; // Cheat code related.
    private int score = 0;
    private Timer timer; // used to get an event every 250 ms to
    // update the time remaining display
    // For pausing. pausing just stops the timer and the play
    // time is computed as final time minus start time.
    // Therefore, this total pause time is used to
    // adjust the elapsed time to the actual play time.
    private long pauseTime = 0;
    private long pauseStart = 0;
    private MemoryGameComponent MGCcontroller;

    public MemoryGameComponentModel(MemoryGrid game, MemoryGameComponent controller) {
        timer = new Timer(250, this);
        this.grid = game;
        int gridSize = grid.getSize();
        levels = loadLevelSet1();
        startTime = new Date().getTime();
        MGCcontroller = controller;

    }

    public int getGridSize() {
        return grid.getSize();
    }
    
    public int getTotalLevels(){
        return this.totalLevels;
    }
    
    public MemoryGameLevel[] getLevels(){
        return this.levels;
    }
    
    public void addGameCounterByOne(){
        this.gameCounter++;
    }
    
    public int getGameCounter(){
        return this.gameCounter;
    }

    /**
     * Pause just the game timer.
     */
    public void pauseTimer() {
        pauseStart = new Date().getTime();
        timer.stop();

    }
    
    public void setStartTime(long time){
        this.startTime = time;
    }

    /**
     * resume() resumes the game
     */
    public void resume() {
        long currentTime = new Date().getTime();
        pauseTime += currentTime - pauseStart;
        timer = new Timer(1000, this);
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
        pauseStart = new Date().getTime();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getPauseTime() {
        return this.pauseTime;
    }

    /**
     * Loads a basic set of levels for the game
     */
    public MemoryGameLevel[] loadLevelSet1() {
        MemoryGameLevel[] levelSet = new MemoryGameLevel[4];
        levelSet[0] = new MemoryGameLevel(4, 75, 1500);
        levelSet[1] = new MemoryGameLevel(4, 40, 750);
        levelSet[2] = new MemoryGameLevel(16, 300, 1000);
        levelSet[3] = new MemoryGameLevel(16, 150, 500);
        currentLevel = 0;
        level = levelSet[currentLevel];
        return levelSet;
    }

    public MemoryGameLevel[] loadLevelSet2() {
        MemoryGameLevel[] levelSet = new MemoryGameLevel[4];
        levelSet[0] = new MemoryGameLevel(16, 75, 1500);
        levelSet[1] = new MemoryGameLevel(16, 40, 750);
        levelSet[2] = new MemoryGameLevel(36, 300, 1000);
        levelSet[3] = new MemoryGameLevel(36, 150, 500);
        currentLevel = 0;
        level = levelSet[currentLevel];
        return levelSet;
    }

    public MemoryGameLevel[] loadLevelSet3() {
        MemoryGameLevel[] levelSet = new MemoryGameLevel[4];
        levelSet[0] = new MemoryGameLevel(36, 75, 1000);
        levelSet[1] = new MemoryGameLevel(36, 40, 500);
        levelSet[2] = new MemoryGameLevel(64, 300, 3000);
        levelSet[3] = new MemoryGameLevel(64, 150, 1500);
        currentLevel = 0;
        level = levelSet[currentLevel];
        return levelSet;
    }
    
    public MemoryGrid getGrid(){
        return this.grid;
    }
    
    public boolean getCheatEnabled(){
        return this.cheatEnabled;
    }
    
    public Timer getTimer(){
        return this.timer;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public int getCurrentLevel(){
        return this.currentLevel;
    }

    public MemoryGameLevel getLevel(){
        return this.level;
    }

    public long endGame(){
        long finalTime = new Date().getTime();
        long deltaTime = (long) ((finalTime - startTime) / 1000.0) - pauseTime / 1000;
        pauseTime = 0;
        grid.isOver = true;
        if (deltaTime < level.getSecondsToSolve())
            score += (level.getSecondsToSolve() - deltaTime) * ((2 * currentLevel + 1) - (currentLevel + 1) / 2);
        return deltaTime;
    }
    
    public void setScore(int score){
        this.score += score;
    }
    
    public void setCheatEnabled(boolean value){
        this.cheatEnabled = value;
    }
    
    public void setIsOver(boolean value){
        this.isOver = value;
    }
    
    public void newGame(int lvl){
        gameCounter = 0;
        if (currentLevel < levels.length) {
            currentLevel = lvl;
            level = levels[currentLevel];
        }
        int gridSize = level.getGridSize();
        grid = new MemoryGrid(gridSize);
        if (timer != null) timer.stop();
        
    }
    
    public void reset(){
        pauseTime = 0;
        score = 0;
        newGame(currentLevel);
    }
    
    public void setLevels(MemoryGameLevel[] newLevels) {
        levels = newLevels;
    }
    
    /**
     * Callback from the timer. This is used to update the time remaining
     * label and to check if the game has ended as a result of the level
     * time running out.
     */
    public void actionPerformed(ActionEvent e) {
        long finalTime = new Date().getTime();
        long deltaTime = (long) ((finalTime - startTime) / 1000.0);
        long timeRemaining = (long) (level.getSecondsToSolve() - deltaTime - pauseTime / 1000.0);
        
        if (timeRemaining < 0) {
            endGame();
        }
        if (timeRemaining < 0){
            timeRemaining = 0;
        }

        MGCcontroller.updateTimeLabel(timeRemaining / 60, timeRemaining % 60);
        MGCcontroller.updateScore();
        MGCcontroller.updateLevel();
    }

    
}














