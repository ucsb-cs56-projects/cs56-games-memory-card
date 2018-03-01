package edu.ucsb.cs56.projects.games.memorycard;
import java.awt.Component;
import java.io.*;
import java.awt.Font;
import javax.swing.JTextArea;
/**
 *@author Annan Zhang, Shihua Lu
 *@version CS56 Winter 2018
 */


public class HighScoreBoardController{
    HighScoreBoardView view = new HighScoreBoardView();
    
    public void setBoard(int[] scores, String[] names){
        view.setBoard(scores, names);
        
    }
    
    public JTextArea getBoard(){
        return view.getBoard();
    }

    
}

