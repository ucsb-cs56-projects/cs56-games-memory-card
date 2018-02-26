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
import javax.swing.text.DefaultCaret;
/**
*
* @author Bryce McGaw and Jonathan Yau
* @author Ryan Halbrook and Yun Suk Chang
* @author Mathew Glodack, Christina Morris
* @author Xiaohe He, Shaoyi Zhang
* @author Hyemin Yoo
* @version CS56 Fall 2016
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
    static HighScoreBoard highscoreBoard = new HighScoreBoard();
    static JButton start = new JButton("Start");
    static JButton resume = new JButton("Resume");
    static JButton beginner = new JButton("Beginner");
    static JButton intermediate = new JButton("Intermediate");
    static JButton advanced = new JButton("Advanced");
    static JButton binstruction = new JButton("instruction");
    static JButton menu = new JButton("Menu");
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static ActionListener highscoreListener = new ActionListener(){
	    public void actionPerformed(ActionEvent e){
  
//clear up the frame
          frame.getContentPane().removeAll();
          frame.getContentPane().revalidate();
          frame.getContentPane().repaint();
          //go();
      //}
       
   frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
    frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
    frame.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	

      //JTextArea textArea;
   JPanel upperPanel = new JPanel();


    
JTextArea textArea = highscoreBoard.getBoard();

   
    upperPanel.setBackground(Color.gray);
    textArea.setEditable(false);

    frame.add(textArea);

    frame.pack();
    frame.setVisible(true);


	ActionListener menuListener = new ActionListener(){
	      public void actionPerformed(ActionEvent e){
		  menu();
	      }
       };

   // register events with the buttons
   
   menu.addActionListener(menuListener);

   //JPanel sp2 = new JPanel(new BorderLayout());
   //sp2.add(menu);
   //upperPanel.add(BorderLayout.EAST,menu);
   frame.add(BorderLayout.NORTH,menu);

    //frame.getContentPane().add(BorderLayout.NORTH,upperPanel);
   //frame.add(upperPanel);
   
   // to make sure that grids go left to right
   frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
   //frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
        
   frame.setVisible(true);


   //frame.setVisible(true);


    }
   };

 static ActionListener instructionListener = new ActionListener(){
	    public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
	/*	
	text.setLineWrap(true);
		text.setEditable(false);
	
		DefaultCaret caret=(DefaultCaret)text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);		
	*/

   frame.getContentPane().removeAll();
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
 frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)((screenSize.getHeight())/2 - frame.getSize().getHeight()/2));
      frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
      frame.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//JPanel panel = new JPanel();

		JTextArea instructionText = getInstructions();
		instructionText.setEditable(false);

		DefaultCaret caret=(DefaultCaret)instructionText.getCaret();		
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);		
		
		JScrollPane scroller = new JScrollPane(instructionText);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        scroller.setPreferredSize(new Dimension(WINDOW_SIZE/2, WINDOW_SIZE/2));
		
		//frame.add(scroller);
		panel.add(scroller);
		
		

		//panel.getContentPane().add(panel);
		//frame.setSize(350,350);
		panel.setSize(350,350);
		//frame.add(instructionText);
		frame.add(panel);
		menu.addActionListener((event) -> menu());
		frame.add(BorderLayout.NORTH, menu);
		frame.pack();
		frame.setVisible(true);
    
	        
	    }
	};



    // the menu page
    public static void menu() {
	ActionListener resumeListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    //clear up the frame
		    frame.getContentPane().removeAll();
		    frame.getContentPane().revalidate();
		    frame.getContentPane().repaint();
		    go();
		    mgc.resume();
		}
	    };
	ActionListener startListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    //clear up the frame
		    frame.getContentPane().removeAll();
		    frame.getContentPane().revalidate();
		    frame.getContentPane().repaint();
		    go();
		    mgc.reset();
		}
	    };

	ActionListener beginnerListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    //clear up the frame
		    frame.getContentPane().removeAll();
		    frame.getContentPane().revalidate();
		    frame.getContentPane().repaint();
		    go();
		    mgc.setLevels(mgc.loadLevelSet1());
		    mgc.reset();
		}
	    };

	ActionListener intermediateListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    //clear up the frame
		    frame.getContentPane().removeAll();
		    frame.getContentPane().revalidate();
		    frame.getContentPane().repaint();
		    go();
		    mgc.setLevels(mgc.loadLevelSet2());
		    mgc.reset();
		}
	    };	

	ActionListener advancedListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		    //clear up the frame
		    frame.getContentPane().removeAll();
		    frame.getContentPane().revalidate();
		    frame.getContentPane().repaint();
		    go();
		    mgc.setLevels(mgc.loadLevelSet3());
		    mgc.reset();
		}
	    };
	
	frame.getContentPane().removeAll();
	frame.getContentPane().revalidate();
	frame.getContentPane().repaint();
	start.addActionListener(startListener);
	resume.addActionListener(resumeListener);
	highscore.addActionListener(highscoreListener);
	binstruction.addActionListener(instructionListener);
	beginner.addActionListener(beginnerListener);
	intermediate.addActionListener(intermediateListener);
	advanced.addActionListener(advancedListener);
	
	
	JPanel p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	
	//change the font of the buttons
	Font bigFont = new Font("serif", Font.BOLD, 60);
	start.setFont(bigFont);
	beginner.setFont(bigFont);
	intermediate.setFont(bigFont);
	advanced.setFont(bigFont);
	resume.setFont(bigFont);
	highscore.setFont(bigFont);
	binstruction.setFont(bigFont);

	//set the buttons
	highscore.setAlignmentX(Component.CENTER_ALIGNMENT);
	start.setAlignmentX(Component.CENTER_ALIGNMENT);
	beginner.setAlignmentX(Component.CENTER_ALIGNMENT);
	intermediate.setAlignmentX(Component.CENTER_ALIGNMENT);
	advanced.setAlignmentX(Component.CENTER_ALIGNMENT);
	resume.setAlignmentX(Component.CENTER_ALIGNMENT);
	binstruction.setAlignmentX(Component.CENTER_ALIGNMENT);

	p.add(highscore);
	p.add(Box.createVerticalGlue());
	//p.add(start);
	p.add(beginner);
	p.add(intermediate);
	p.add(advanced);
	//p.add(resume);
	p.add(Box.createVerticalGlue());
	p.add(binstruction);

	p.setBackground(new Color(70,130,180));
	frame.getContentPane().add(p);
    }
	
    
    /** main method to open JFrame 
     *
     */
    
    public static void main (String[] args) {
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	instruction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setSize(WINDOW_SIZE, WINDOW_SIZE);

	// go to the menu
	menu();

	// set the window to the middle of the screen
	frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
	frame.setVisible(true);
    }

    // the actual game
    public static void go(){

	frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
	
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
		    
        	    if(selection == JOptionPane.YES_OPTION){
			mgc.reset();
		    }
		    else
			mgc.resume();
		}
	    };

	ActionListener menuListener = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    mgc.pauseTimer();
		    menu();
		}
	    };

	// register events with the buttons
	pause.addActionListener(pauseListener);
	music.addActionListener(musicListener);
	reset.addActionListener(resetListener);
	menu.addActionListener(menuListener);

	// the game
	frame.getContentPane().add(mgc);
	JPanel p = new JPanel(new BorderLayout());
	p.add(BorderLayout.WEST,label);

	// buttons on the bottom
	JPanel p2 = new JPanel(new BorderLayout());
	p.add(BorderLayout.EAST,p2);
	p2.add(BorderLayout.WEST,pause);
	p2.add(BorderLayout.CENTER,music);
	p2.add(BorderLayout.EAST, reset);
	
	frame.getContentPane().add(BorderLayout.SOUTH,p);

	// information on the top
	JPanel scorePanel = new JPanel(new BorderLayout());
	scorePanel.add(BorderLayout.WEST, score);
	scorePanel.add(BorderLayout.CENTER, level);
	frame.pack();

	// menu button on the upper right
	JPanel sp2 = new JPanel(new BorderLayout());
	sp2.add(menu);
	scorePanel.add(BorderLayout.EAST,sp2);
	
	frame.getContentPane().add(BorderLayout.NORTH,scorePanel);
	
	mgc.setMainFrame(frame);
	mgc.setLabel(label);
	mgc.setPauseButton(pause);
	mgc.setMusicButton(music);
	mgc.setScoreLabel(score);
	mgc.setLevelLabel(level);
	mgc.setHighScoreBoard(highscoreBoard);
	//mgc.resume();
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
	    //JTextArea instructionTextArea = new JTextArea();
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
    public static JTextArea getInstructions(){
	    File file = new File("instructions.txt");
	    JTextArea instructionTextArea = new JTextArea();
	    try {
	        BufferedReader br = new BufferedReader(
						        new InputStreamReader(
                                new FileInputStream(file)));
	        String line;
	        while((line = br.readLine()) != null){
		        instructionTextArea.append(line + "\n");
	        }
	        br.close();
	    } catch	(IOException e) {
		e.printStackTrace();
	    }
	    return instructionTextArea;
    }

}
