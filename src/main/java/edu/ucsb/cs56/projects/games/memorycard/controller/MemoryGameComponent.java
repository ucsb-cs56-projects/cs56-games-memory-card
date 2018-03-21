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
public class MemoryGameComponent {
    //private MemoryGameComponent MGCview;
    private MemoryGameComponentModel MGCmodel;
    private MemoryGameComponentView MGCview;
    
    public MemoryGameComponent(MemoryGrid game){
        this.MGCmodel = new MemoryGameComponentModel(game,this);
        this.MGCview = new MemoryGameComponentView(this);
    }


    public MemoryGameComponentView getView(){
        return MGCview;
    }
    
    
    /**
     * Pause the timer and the game by showing a dialog box,
     * preventing the user from playing the game until they resume.
     */
    public void pauseGame() {
        MGCmodel.pauseTimer();
        if (MGCview.pauseGame() == true){
            resume();
        }
    }



    /**
     * Play sound effect after flipping
     */    
    public void playFlipSound() {
        MGCview.playFlipSound();
    }

    /**
     * Play sound effect if two cards matched
     */
    public void playMatchSound() {
        MGCview.playMatchSound();
    }


    /**
     * Play sound effect after winning the game
     */
    public void playWinSound() {
        MGCview.playWinSound();
    }


    /**
     * Play background music
     */
    public void playMusic() {
        MGCview.playMusic();
    }

        /**
     * Stop background music
     */
    public void stopMusic() {
        MGCview.stopMusic();
    }


    /**
     * Resume the game timer. In other words, recalculate total pause
     * time and start the timer.
     */
    public void pauseB() {
        stopTimer();
        if (MGCview.pauseB() == true){
            resume();
        }
    }

    public void buildTiles(){
        MGCview.buildTiles(MGCmodel.getGridSize());
    }

    /**
     * Updates Time Label with two parameters
     *
     * @param minutes
     * @param seconds
     */
    
    public void updateTimeLabel(long minutes, long seconds) {
        MGCview.updateTimeLabel(minutes,seconds);
    }


    public void updateScore() {
        MGCview.updateScore(getScore());
    }


    public void updateLevel() {
        MGCview.updateLevel(getTotalLevels(), getCurrentLevel());
    }

    /**
     * setLabel()
     *
     * @param label sets the timeLabel
     */
    public void setLabel(JLabel label) {
        MGCview.setLabel(label);
    }


    public void setMainFrame(JFrame f) {
        MGCview.setMainFrame(f);
    }


    public void setScoreLabel(JLabel l) {
        MGCview.setScoreLabel(l);
    }

    public void setLevelLabel(JLabel l) {
        MGCview.setLevelLabel(l);
    }

    public void setHighScoreBoard(HighScoreBoard h) {
        MGCview.setHighScoreBoard(h);
    }

    /**
     * setPauseButton
     *
     * @param b sets the pauseButton
     */
    public void setPauseButton(JButton b) {
        MGCview.setPauseButton(b);
    }

    /**
     * setMusicButton
     *
     * @param b sets the musicButton
     */
    public void setMusicButton(JButton b) {
        MGCview.setMusicButton(b);
    }


    public void pauseTimer() {
        this.MGCmodel.pauseTimer();
    }

    public int getGridSize() {
        return this.MGCmodel.getGridSize();
    }

    public void resume() {
        this.MGCmodel.resume();
    }

    public void stopTimer() {
        this.MGCmodel.stopTimer();
    }

    public long getStartTime() {
        return this.MGCmodel.getStartTime();
    }

    public long getPauseTime() {
        return this.MGCmodel.getPauseTime();
    }
    
    public MemoryGrid getGrid(){
        return this.MGCmodel.getGrid();
    }
    
    public boolean getCheatEnabled(){
        return this.MGCmodel.getCheatEnabled();
    }
    
    public Timer getTimer(){
        return this.MGCmodel.getTimer();
    }
    
    public int getScore(){
        return this.MGCmodel.getScore();
    }
    
    public int getCurrentLevel(){
        return this.MGCmodel.getCurrentLevel();
    }
    
    public MemoryGameLevel getLevel(){
        return this.MGCmodel.getLevel();
    }
    
    public long endGame(){
        return this.MGCmodel.endGame();
    }

    public void viewEndGame(){
        this.MGCview.endGame();
    }
    
    public void setScore(int score){
        this.MGCmodel.setScore(score);
    }
    
    public void setCheatEnabled(boolean value){
        this.MGCmodel.setCheatEnabled(value);
    }
    
    public void setIsOver(boolean value){
        this.MGCmodel.setIsOver(value);
    }
    
    public void newGame(int lvl){
        this.MGCmodel.newGame(lvl);
        this.MGCview.newGame();
    }
    
    public void reset(){
        this.MGCmodel.reset();
        this.MGCview.reset();
    }
    
    public MemoryGameLevel[] loadLevelSet1(){
        return this.MGCmodel.loadLevelSet1();
    }
    
    public MemoryGameLevel[] loadLevelSet2(){
        return this.MGCmodel.loadLevelSet2();
    }
    
    public MemoryGameLevel[] loadLevelSet3(){
        return this.MGCmodel.loadLevelSet3();
    }
    
    public void setLevels(MemoryGameLevel[] newLevels){
        this.MGCmodel.setLevels(newLevels);
    }
    
    public MemoryGameLevel[] getLevels(){
        return this.MGCmodel.getLevels();
    }
    
    public int getTotalLevels(){
        return this.MGCmodel.getTotalLevels();
    }
    
    public void setStartTime(long time){
        this.MGCmodel.setStartTime(time);
    }
    
    public void addGameCounterByOne(){
        this.MGCmodel.addGameCounterByOne();
    }
    
    public int getGameCounter(){
        return this.MGCmodel.getGameCounter();
    }
}
















