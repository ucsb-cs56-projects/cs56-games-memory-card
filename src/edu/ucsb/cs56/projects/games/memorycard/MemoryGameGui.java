package edu.ucsb.cs56.projects.games.memorycard;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Font;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.ComponentOrientation;
import java.io.*;
/**
*
* @author Bryce McGaw and Jonathan Yau
* @author Ryan Halbrook and Yun Suk Chang
* @author Mathew Glodack, Christina Morris
* @author Julio Maldonado, Shelby Elgood
* @version CS56 Winter 2015
* Edited Professor Phill Conrad's code from Lab06
*/
public class MemoryGameGui {

    static final int WINDOW_SIZE = 500;

    static JFrame frame = new JFrame("Memory Card Game");
    static MemoryGrid grid = new MemoryGrid(16);
    static MemoryGameComponent mgc = new MemoryGameComponent(grid);
    static JButton reset = new JButton("Reset");
    static JButton pause = new JButton("Pause");
    static JLabel label = new JLabel("Time Remaining: 1 minute, 15 seconds");
    static JLabel score = new JLabel("Score: 0");
    static JFrame instruction = new JFrame("Instruction");
    static JTextArea text = new JTextArea(15,25);
    static JButton highscore = new JButton("High Score");
    static HighScoreBoard board = new HighScoreBoard();
    /** main method to open JFrame 
     *
     */
    
    public static void main (String[] args) {

	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	instruction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	ActionListener pauseListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    mgc.pauseB();
		}
	};
	ActionListener highscoreListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JFrame scoreboard = new JFrame("High Score Board");
			scoreboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JTextArea txt = board.getBoard();
			txt.setLineWrap(true);
			txt.setEditable(false);
			JScrollPane scroller2 = new JScrollPane(txt);
			scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scoreboard.add(scroller2);
	
			scoreboard.getContentPane().add(txt);
		        scoreboard.setSize(350,350);
			Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
			scoreboard.setLocation((int)(screenSize2.getWidth()/2 - scoreboard.getSize().getWidth()/2), (int)(screenSize2.getHeight()/2 - scoreboard.getSize().getHeight()/2));
			scoreboard.setVisible(true);
			
		}
	};
	ActionListener resetListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    mgc.pauseTimer();
		    JOptionPane popup = new JOptionPane("Warning!");
	            Object[] options= {"Start Over","Cancel"};
	
	            int selection=popup.showOptionDialog(
	                    null,
	                    "Do you want to restart this level?\nYou will lose all of your points!",
	                    "Warning!",
       		             JOptionPane.YES_NO_OPTION,
        	            JOptionPane.WARNING_MESSAGE, null,
        	            options, options[0]);

        	    if(selection==JOptionPane.YES_OPTION){
        	           	 mgc.reset();
		    }
		    else
			mgc.resume();
		}
	};
	highscore.addActionListener(highscoreListener);
	pause.addActionListener(pauseListener);
	reset.addActionListener(resetListener);
	frame.getContentPane().add(mgc);
	JPanel p = new JPanel(new BorderLayout());
	p.add(BorderLayout.WEST,label);
	JPanel p2 = new JPanel(new BorderLayout());
	p.add(BorderLayout.EAST,p2);
	p2.add(BorderLayout.WEST,pause);
	p2.add(BorderLayout.EAST, reset);

	frame.getContentPane().add(BorderLayout.SOUTH,p);

	JPanel scorePanel = new JPanel(new BorderLayout());
//	scorePanel.setLayout(new BoxLayout(scorePanel,BoxLayout.Y_AXIS));
	score.setAlignmentX(Component.CENTER_ALIGNMENT);
	scorePanel.add(BorderLayout.CENTER,score);
	JPanel sp2 = new JPanel(new BorderLayout());
	highscore.setAlignmentX(Component.RIGHT_ALIGNMENT);
	sp2.add(BorderLayout.EAST,highscore);
	scorePanel.add(BorderLayout.EAST,sp2);
	
	frame.getContentPane().add(BorderLayout.NORTH,scorePanel);

	mgc.setMainFrame(frame);
	mgc.setLabel(label);
	mgc.setPauseButton(pause);
	mgc.setScoreLabel(score);
	mgc.setHighScoreBoard(board);
	// to make sure that grids go left to right
	frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
	frame.setVisible(true);

	JPanel panel = new JPanel();
	text.setLineWrap(true);
	text.setEditable(false);
	JScrollPane scroller = new JScrollPane(text);
	scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	panel.add(scroller);

	//start game button on instructions frame	
	JButton startGame = new JButton("Start Game");
	JPanel ng = new JPanel(new BorderLayout());
      	ng.add(BorderLayout.CENTER,startGame);
      	instruction.getContentPane().add(BorderLayout.SOUTH,ng);

        ActionListener startGameListener = new ActionListener(){
                public void actionPerformed(ActionEvent e){
			instruction.dispatchEvent(new WindowEvent(instruction, WindowEvent.WINDOW_CLOSING));    
        	}
        };
        startGame.addActionListener(startGameListener);


	instruction.getContentPane().add(panel);
        instruction.setSize(350,350);
	addInstruction();
	instruction.setLocation((int)(screenSize.getWidth()/2 - instruction.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - instruction.getSize().getHeight()/2));
	instruction.setVisible(true);
	
    }

    /**
     *Adds instruction to the screen
     @return Displays instructions text file on the GUI.
     */
    public static void addInstruction(){
	File file = new File("instructions.txt");
	try {
	    BufferedReader br = new BufferedReader(
						   new InputStreamReader(
									 new FileInputStream(file)));
	    String line;
	    while((line = br.readLine()) != null){
		text.append(line+"\n");
	    }
	    br.close();
	} catch	(IOException e) {
	    e.printStackTrace();
	}
    }
}
