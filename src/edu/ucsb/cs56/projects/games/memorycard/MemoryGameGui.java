package edu.ucsb.cs56.projects.games.memorycard;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
* @version CS56 Spring 2013
* Edited Professor Phill Conrad's code from Lab06
*/
public class MemoryGameGui {
    
    static final int WINDOW_SIZE = 500;
    
    static JFrame frame = new JFrame("Memory Card Game");
    static MemoryGrid grid = new MemoryGrid(16);
    static MemoryGameComponent mgc = new MemoryGameComponent(grid);
    static JButton reset = new JButton("Reset");
    static JButton pause = new JButton("Pause");
    static JButton music = new JButton("Music Off");
    static JLabel label = new JLabel("Time Remaining: 1 minute, 15 seconds");
    static JLabel score = new JLabel("Score: 0 ");
    static JLabel level = new JLabel("Level: 1/4 ");
    static JFrame instruction = new JFrame("Instruction");
    static JTextArea text = new JTextArea(15,25);
    static JButton highscore = new JButton("High Score");
    static HighScoreBoard board = new HighScoreBoard();
    static JButton start = new JButton("Start!");
    static JButton binstruction = new JButton("instruction");
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static ActionListener highscoreListener = new ActionListener(){
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
    static ActionListener instructionListener = new ActionListener(){
	    public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
		text.setLineWrap(true);
		text.setEditable(false);
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroller);
		
		instruction.getContentPane().add(panel);
		instruction.setSize(350,350);
		addInstruction();
		instruction.setLocation((int)(screenSize.getWidth()/2 - instruction.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - instruction.getSize().getHeight()/2));
		instruction.setVisible(true);
	    }
	};
    
    /** main method to open JFrame 
     *
     */
    
    public static void main (String[] args) {
	
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	instruction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	ActionListener startListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    frame.getContentPane().removeAll();
		    go();
		}
	    };
	
	start.addActionListener(startListener);
	highscore.addActionListener(highscoreListener);
	binstruction.addActionListener(instructionListener);
	
	frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
	JPanel p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	
	Font bigFont = new Font("serif", Font.BOLD, 65);
	start.setFont(bigFont);
	highscore.setFont(bigFont);
	binstruction.setFont(bigFont);
	
	highscore.setAlignmentX(Component.CENTER_ALIGNMENT);
	start.setAlignmentX(Component.CENTER_ALIGNMENT);
	binstruction.setAlignmentX(Component.CENTER_ALIGNMENT);

	p.add(highscore);
	p.add(Box.createVerticalGlue());
	p.add(start);
	p.add(Box.createVerticalGlue());
	p.add(binstruction);

	p.setBackground(new Color(70,130,180));
	frame.getContentPane().add(p);

	frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
	frame.setVisible(true);
    }
    public static void go(){
	
	ActionListener pauseListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    mgc.pauseB();
		}
	};
	
	ActionListener musicListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
            if (e.getActionCommand() == "Music On"){
                mgc.stopMusic();
                music.setLabel("Music Off");
            }
            else{
		        mgc.playMusic();
                music.setLabel("Music On");
            }
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
	music.addActionListener(musicListener);
	reset.addActionListener(resetListener);
	frame.getContentPane().add(mgc);
	JPanel p = new JPanel(new BorderLayout());
	p.add(BorderLayout.WEST,label);
    
	JPanel p2 = new JPanel(new BorderLayout());
	p.add(BorderLayout.EAST,p2);
	p2.add(BorderLayout.WEST,pause);
	p2.add(BorderLayout.CENTER,music);
	p2.add(BorderLayout.EAST, reset);
	
	frame.getContentPane().add(BorderLayout.SOUTH,p);
	
	JPanel scorePanel = new JPanel(new BorderLayout());
	score.setAlignmentX(Component.CENTER_ALIGNMENT);
	scorePanel.add(BorderLayout.WEST,score);
	scorePanel.add(BorderLayout.CENTER,level);
	JPanel sp2 = new JPanel(new BorderLayout());
	highscore.setAlignmentX(Component.RIGHT_ALIGNMENT);
	sp2.add(BorderLayout.EAST,highscore);
	scorePanel.add(BorderLayout.EAST,sp2);
	
	frame.getContentPane().add(BorderLayout.NORTH,scorePanel);
	
	mgc.setMainFrame(frame);
	mgc.setLabel(label);
	mgc.setPauseButton(pause);
	mgc.setMusicButton(music);
	mgc.setScoreLabel(score);
	mgc.setLevelLabel(level);
	mgc.setHighScoreBoard(board);
	// to make sure that grids go left to right
	frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
        
	frame.setVisible(true);
    }
    
    /**
     *Adds instruction to the screen
     */
    public static void addInstruction(){
	    File file = new File("instructions.txt");
	    try {
	        BufferedReader br = new BufferedReader(
						        new InputStreamReader(
                                new FileInputStream(file)));
	        String line;
	        while((line = br.readLine()) != null){
		        text.append(line + "\n");
	        }
	        br.close();
	    } catch	(IOException e) {
            e.printStackTrace();
        }
    }

}
