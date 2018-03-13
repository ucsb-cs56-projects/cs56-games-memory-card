package edu.ucsb.cs56.projects.games.memorycard;
import java.awt.Component;
import java.io.*;
import java.awt.Font;
import javax.swing.JTextArea;
/**
 *@author Annan Zhang, Shihua Lu
 *@version CS56 Winter 2018
 */


public class HighScoreBoardView{
	private JTextArea board;
    
    public void setBoard(int[] scores, String[] names){
        board = new JTextArea(15,25);
        board.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
        for(int i=0;i<10;i++){
            if(scores[i]==0)
                break;
            board.append((i+1)+". "+names[i]+scores[i]+"\n");
            
            
        }
    }
    
    public JTextArea getBoard(){
        return board;
        
    }


}

