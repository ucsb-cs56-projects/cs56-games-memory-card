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
public class MemoryGameComponentController {
    MemoryGameComponent MGCview;
    MemoryGameComponentModel MGCmodel;
    
    public MemoryGameComponentController(MemoryGrid game){
        MGCview = new MemoryGameComponent(game);
        MGCmodel = new MemoryGameComponentModel(game);
    }
    
    public int getGridSize() {
        return MGCmodel.getGridSize();
    }

    public void pauseTimer() {
        MGCmodel.pauseTimer();
    }

    public void resume() {
        MGCmodel.resume();
    }

    public void stopTimer() {
        MGCmodel.stopTimer();
    }

    public long getStartTime() {
        return MGCmodel.getStartTime();
    }

    public long getPauseTime() {
        return MGCmodel.getPauseTime();
    }
    
    public MemoryGrid getGrid(){
        return MGCmodel.getGrid();
    }
    
    public boolean getCheatEnabled(){
        return MGCmodel.getCheatEnabled();
    }
    
    public Timer getTimer(){
        return MGCmodel.getTimer();
    }
    
    public int getScore(){
        return MGCmodel.getScore();
    }
    
    public int getCurrentLevel(){
        return MGCmodel.getCurrentLevel();
    }
    
    public MemoryGameLevel getLevel(){
        return MGCmodel.getLevel();
    }
    
    public long endGame(){
        return MGCmodel.endGame();
    }
    
    public void setScore(int score){
        MGCmodel.setScore(score);
    }
    
    public void setCheatEnabled(boolean value){
        MGCmodel.setCheatEnabled(value);
    }
    
    public void setIsOver(boolean value){
        MGCmodel.setIsOver(value);
    }
    
    public void newGame(int lvl){
        MGCmodel.newGame(lvl);
    }
    
    public void reset(){
        MGCmodel.reset();
    }
    
    public MemoryGameLevel[] loadLevelSet1(){
        return MGCmodel.loadLevelSet1();
    }
    
    public MemoryGameLevel[] loadLevelSet2(){
        return MGCmodel.loadLevelSet2();
    }
    
    public MemoryGameLevel[] loadLevelSet3(){
        return MGCmodel.loadLevelSet3();
    }
    
    public void setLevels(MemoryGameLevel[] newLevels){
        MGCmodel.setLevels(newLevels);
    }
    
    public MemoryGameLevel[] getLevels(){
        return MGCmodel.getLevels();
    }
    
    public int getTotalLevels(){
        return MGCmodel.getTotalLevels();
    }
    
    public void setStartTime(long time){
        MGCmodel.setStartTime(time);
    }
    
    public void addGameCounterByOne(){
        MGCmodel.addGameCounterByOne();
    }
    
    public int getGameCounter(){
        return MGCmodel.getGameCounter();
    }
}
















