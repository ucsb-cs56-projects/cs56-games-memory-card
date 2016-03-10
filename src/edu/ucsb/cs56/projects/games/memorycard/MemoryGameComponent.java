package edu.ucsb.cs56.projects.games.memorycard;

import java.awt.*;
import java.awt.event.*; // for ActionListener and ActionEvent
import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Math;
import java.io.*;
import sun.audio.*;
/**
* A Swing component for playing the Memory Card Game
@author Bryce McGaw and Jonathan Yau (with some of Phill Conrad's code as a basis)
@author Ryan Halbrook and Yun Suk Chang
@author Mathew Glodack, Christina Morris
@version CS56 Spring 2013
@see MemoryGrid
*/
public class MemoryGameComponent extends JComponent implements ActionListener
{
    
    private JButton []                   buttons;
    private ArrayList<ImageIcon>         imgIcons = new ArrayList<ImageIcon>();
    public  JComponent                   restartB = new JButton("Restart");
    private Icon                         imgBlank;
    private JButton	                  pauseButton;
    private JButton                   musicButton;
    private JLabel                       timeLabel = null;
    private JLabel	                    scoreLabel = null;
    private Timer                           timer; // used to get an event every 250 ms to
                                                   // update the time remaining display
    private MemoryGrid                       grid;
    private int                      currentLevel;
    private MemoryGameLevel[]              levels;
    private MemoryGameLevel                  level = new MemoryGameLevel(36, 100, 2000);
    
    private boolean              firstImageFlipped = false;
    private int                        gameCounter = 0;
    private long                         startTime = 0; // used to calculate the total game time.
    private boolean                   cheatEnabled = false; // Cheat code related.
    private boolean	                        isOver = false; // Cheat code related.
    private int 	                         score = 0; 
    private JTextField                        text = new JTextField(20);//for inputing name for high score board
    private JFrame 	                    inputBoard = new JFrame("ENTER FIRST 20 CHARACTERS OF YOUR NAME");//for inputing name for high score board
    private JLabel	                     textLabel = new JLabel("ENTER YOUR NAME: ");//for inputing name for high score board
    private JFrame	                     mainFrame = null;
    private HighScoreBoard                   board = null;
    // For pausing. pausing just stops the timer and the play
    // time is computed as final time minus start time.
    // Therefore, this total pause time is used to
    // adjust the elapsed time to the actual play time.
    private long pauseTime = 0;
    private long pauseStart;
    
    /** Constructor
	
	@param game an object that implements the MemoryGrid interface
	to keep track of the moves in each game, ensuring the rules are
	followed and detecting when the user has won.
    */
    public MemoryGameComponent(MemoryGrid game) {
	super(); 
	timeLabel = new JLabel("Time Remaining");
	scoreLabel = new JLabel("Score");
	mainFrame=new JFrame();
	timer = new Timer(250, this);
	this.grid = game;
        int gridSize = grid.getSize();
	buttons= new JButton[gridSize];
	
	loadLevelSet1();
	loadImageIcons(); // loads the array list of icons and sets imgBlank
	buildTiles();
	startTime = new Date().getTime();
    }
    
    /** 
	Pause the timer and the game by showing a dialog box,
	preventing the user from playing the game until they resume.
    */
    public void pauseGame() {
	pauseTimer();
        JOptionPane popup = new JOptionPane("PAUSED");
        Object[] options= {"Resume"};
	
        int selection=popup.showOptionDialog(
					     null,
					     "GAME PAUSED",
					     "PAUSED",
					     JOptionPane.OK_CANCEL_OPTION,
					     JOptionPane.INFORMATION_MESSAGE, null,
					     options, options[0]);
	
        if(selection==JOptionPane.YES_OPTION)
	    {
		resume();
	    }
    }
    /**
       Pause just the game timer.
    */
    public void pauseTimer() {
	pauseStart = new Date().getTime();
	timer.stop();
	
    }
    
    /** Play sound from a WAV filepath
        This is a helper method
    */
    public void playSound(String filepath){
        AudioPlayer myPlyaer = AudioPlayer.player;
        AudioStream sound;
        try{
            InputStream test = new FileInputStream(filepath);
            sound = new AudioStream(test);
            AudioPlayer.player.start(sound);
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error){
            System.out.print(error.toString());
        }      
    }
    
    /** Play sound effect after flipping
     */
    public void playFlipSound(){
        playSound("./resource/wall_pickup_01.wav");
    }
    /** Play sound effect if two cards matched
     */
    public void playMatchSound(){
        playSound("./resource/training_finished_03.wav");
    }
    /** Play sound effect after winning the game
     */
    public void playWinSound(){
        playSound("./resource/Final Fantasy VII - Victory.wav");
    }
    
    AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;
    
    /** Play background music
     */
    public void playMusic(){       

        System.out.println("Music button Pressed!");
        //ContinuousAudioDataStream loop = null;
        try{
            InputStream test = new FileInputStream("./resource/Hiromi Haneda.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);         
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error){
            System.out.print(error.toString());
        }
        //MGP.start(loop);
    }
    
    /** Stop background music
    */
    public void stopMusic(){
        System.out.println("Music should pause!");
        AudioPlayer.player.stop(BGM);
    }
    
    /**
       Resume the game timer. In other words, recalculate total pause
       time and start the timer.
    */
    public void pauseB() {
	pauseStart = new Date().getTime();
	timer.stop();
        JOptionPane popup = new JOptionPane("PAUSED");
        Object[] options = {"Resume"};
	
        int selection = popup.showOptionDialog(
					       null,
					       "GAME PAUSED",
					       "PAUSED",
					       JOptionPane.OK_CANCEL_OPTION,
					       JOptionPane.INFORMATION_MESSAGE, null,
					       options, options[0]);
	
        if(selection==JOptionPane.YES_OPTION)
	    {
		resume();
	    }
    }
    /**pause() pauses the time
     */
    public void pause() {
	pauseStart = new Date().getTime();
	timer.stop();
	
    }
    
    /** resume() resumes the game
     */
    public void resume() {
	long currentTime = new Date().getTime();
	pauseTime += currentTime - pauseStart;
	timer = new Timer(1000, this);
	timer.start();
    }
    
    /**Updates Time Label with two parameters
     *@param minutes
     *@param seconds
     */
    private void updateTimeLabel(long minutes, long seconds) {
	String m = " minutes, ";
	if (minutes == 1) m = " minute, ";
	if (minutes > 0) {
	    timeLabel.setText("Time Remaining: " + minutes + m + seconds + " seconds");
	} else {
	    timeLabel.setText("Time Remaining: " + seconds + " seconds");
	}
    }
    private void updateScore(){
   	scoreLabel.setText("Score: "+score);
    }
    /**
       Callback from the timer. This is used to update the time remaining
       label and to check if the game has ended as a result of the level
       time running out.
    */
    public void actionPerformed(ActionEvent e) {
	long finalTime = new Date().getTime();
	long deltaTime = (long)((finalTime - startTime) / 1000.0);
	long timeRemaining = (long)(level.getSecondsToSolve() - deltaTime + pauseTime / 1000.0);
	
	if (timeRemaining < 0) {
	    endGame();
	}
	if (timeRemaining < 0)
	    timeRemaining = 0;
	
        updateTimeLabel(timeRemaining / 60, timeRemaining % 60);
	updateScore();
    }
    
    /**setLabel()
     *@param label sets the timeLabel
     */
    public void setLabel(JLabel label) {
	this.timeLabel = label;
    }	
    public void setMainFrame(JFrame f){
	mainFrame = f;
    }
    public void setScoreLabel(JLabel l){
	scoreLabel = l;
    }
    public void setHighScoreBoard(HighScoreBoard h){
	board=h;
    }
    
    /**setPauseButton
     *@param b sets the pauseButton
     */
    public void setPauseButton(JButton b){
	pauseButton = b;
	pauseButton.setEnabled(false);
    }
    
    /**setMusicButton
     *@param b sets the musicButton
     */
    public void setMusicButton(JButton b){
        musicButton = b;
        musicButton.setEnabled(false);
    }
    
    /**
     * Loads a basic set of levels for the game
     */
    private void loadLevelSet1() {
	levels = new MemoryGameLevel[4];
	levels[0] = new MemoryGameLevel(16, 75, 1500);
	levels[1] = new MemoryGameLevel(16, 40, 750);
	levels[2] = new MemoryGameLevel(36, 300, 1000);
	levels[3] = new MemoryGameLevel(36, 150, 500);
	currentLevel = 0;
	level = levels[currentLevel];
    }
    
    // The first 8 images. These will be used
    // for a 4 by 4 game.
    private String[] images8 = {
	"/images/200.jpg", "/images/201.jpg",
	"/images/202.jpg", "/images/203.jpg",
	"/images/204.jpg", "/images/205.jpg",
	"/images/206.jpg", "/images/207.jpg",
    };
    // The next ten images. These, in addition
    // to the first 8 will be used for a
    // 6 by 6 game.
    private String[] images10 = {
	"/images/208.jpg", "/images/209.jpg",
	"/images/210.jpg", "/images/211.jpg",
	"/images/212.jpg", "/images/213.jpg",
	"/images/214.jpg", "/images/215.jpg",
        "/images/216.jpg", "/images/217.jpg",
        
    };
    
    
    /**buildTiles() constructs the tiles
     */
    public void buildTiles() {
	this.removeAll();
	int gridSize = grid.getSize();
	
	//set layout to a grid of length sqrt(grid size)
	this.setLayout(new GridLayout(0,(int)Math.sqrt(gridSize)));
	buttons = new JButton[gridSize];
	for(int i=0; i<=(gridSize-1); i++) {
	    //initially all buttons are blank
	    JButton jb = new JButton(imgBlank);
	    
	    buttons[i] = jb;
	    jb.addActionListener(new ButtonListener(i));
	    
	    //get rid of annoying boxes appearing around icon next to clicked icon
	    jb.setFocusPainted(false);
	    
	    this.add(jb);
	}
	this.repaint();
	this.validate();
	startTime = new Date().getTime();
    }
    
    /**Class ButtonListener implents ActionLister
     *defines the actionPerformed methods for the buttons
     */
    class ButtonListener implements ActionListener {
	private int num;
	private Timer t;
	
	/**ButtonListener method
	 *@param i for the num
	 */
	public ButtonListener(int i) {
	    super();
	    this.num = i;
	}
	
        @Override
	public void actionPerformed (ActionEvent event) {
	    
	    Class classs = this.getClass();
	    Icon imgBlank = new ImageIcon(
					  classs.getResource("/images/000.jpg"));
	    
	    //if 2 MemoryCards are flipped, flip back over
	    flipBack();
	    
            //if no MemoryCards are flipped, flip one
            if (!grid.isOneFlipped()){
		if (!firstImageFlipped) {
		    startTime = (new Date().getTime());
		    timer.start();
		    firstImageFlipped = true;
		    pauseButton.setEnabled(true);
		    musicButton.setEnabled(true);
		}
		grid.flip(num);
		playFlipSound();
		JButton jb = buttons[num];
		
		ImageIcon i = imgIcons.get(grid.getVal(num)-1);
		Image img = i.getImage();
		Image newimg = img.getScaledInstance(jb.getWidth()*3, jb.getHeight()*3, java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(newimg);
		
		jb.setIcon(i); //set image according to val
		jb.setDisabledIcon(i);
		if(num != 1) //cheat code
		    jb.setEnabled(false); //make unclickable
                else
		    //cheat code. Needs to override the button so that button is same color as regular button.
		    cheatEnabled = true;	
	    }
	    
            //if one MemoryCard is flipped, flip other
            //then check if theyre matching
            else{
		if((num==1 && cheatEnabled))//cheat code
		    {
			cheatEnabled = false;
			isOver = true;
			endGame();
			return;
		    }
		cheatEnabled = false;//cheat code
		grid.flip(num);
		playFlipSound();
		JButton jb = buttons[num];
		
		ImageIcon i = imgIcons.get(grid.getVal(num)-1);
		Image img = i.getImage();
		Image newimg = img.getScaledInstance(jb.getWidth()*3, jb.getHeight()*3, java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(newimg);
		jb.setIcon(i); //set image according to val
		jb.setDisabledIcon(i);
		
                jb.setEnabled(false);
                if (grid.flippedEquals(num)){ //if they're matching keep num displayed and set flipped as false
                    gameCounter++;
                    grid.flip(num); 
		    buttons[grid.getFlipped()].setEnabled(false);
                    grid.flip(grid.getFlipped());
                    playMatchSound();
                    score += 30;
                    //check if game is over
                    if(gameCounter == grid.getSize()/2){
			isOver = true;
			endGame();
                    }
                } else {
		    score -= 5;
		    // start the flip back timer
		    int delay = level.getFlipTime();
		    ActionListener listener = new ActionListener() {
			    public void actionPerformed(ActionEvent e) { flipBack();}
			};
		    t = new Timer(delay, listener);
		    t.setRepeats(false);
		    t.start();
		    
		} // end of inner if else
		
            } // end of outer if else
	}
    }
    
    /**Starts a new level or restarts the current level
     *@param lvl changes the level of the game
     */
    public void newGame(int lvl) {
	gameCounter = 0;
	if (currentLevel < levels.length) {
	    currentLevel = lvl;
	    level = levels[currentLevel];
	}
	int gridSize = level.getGridSize();
	grid = new MemoryGrid(gridSize);
	buildTiles();
	if (timer != null) timer.stop();
	
	firstImageFlipped = false;
	pauseButton.setEnabled(false);
	musicButton.setEnabled(true);
    }
    
    /**Resets the game
     *
     */
    public void reset() {
	pauseTime = 0;
	score=0;
	updateScore();
	updateTimeLabel(level.getSecondsToSolve() / 60, 
			level.getSecondsToSolve() % 60);	    
 	newGame(currentLevel);
	firstImageFlipped = false;
	pauseButton.setEnabled(false);
	musicButton.setEnabled(true);
    }
    
    /**
       Ends the game and starts a new game if the user selects new game
       from a dialog menu.
    */
    public void endGame() {
	
	timer.stop();
	long finalTime = new Date().getTime();
	long deltaTime = (long)((finalTime - startTime) / 1000.0) - pauseTime / 1000;
	pauseTime = 0;
        grid.isOver = true;
	if(deltaTime < level.getSecondsToSolve())
	    score += (level.getSecondsToSolve()-deltaTime)*((2*currentLevel+1)-(currentLevel+1)/2);
	
	updateScore();
        
	if (deltaTime < level.getSecondsToSolve() && currentLevel<3) {
	    JOptionPane popup = new JOptionPane("Good Job!");
	    Object[] options= {"Continue","Quit"};
	    playWinSound();
	    int selection=popup.showOptionDialog(
						 null,
						 "-~*´¨¯¨`*·~-.¸-  You beat the level!!  -,.-~*´¨¯¨`*·~-\nScore: "+score,
						 "Good Job!",
						 JOptionPane.YES_NO_OPTION,
						 JOptionPane.INFORMATION_MESSAGE, null,
						 options, options[0]);
	    
            if(selection==JOptionPane.YES_OPTION)
		{
		    long time = levels[currentLevel+1].getSecondsToSolve();
		    updateTimeLabel(time / 60, time % 60);
		    newGame(currentLevel+1);
		}
	    else	
		System.exit(0);
	    
	}
        else if(deltaTime < level.getSecondsToSolve()&&currentLevel==3){
	    JOptionPane popup = new JOptionPane("Good Job!");
	    Object[] options = {"Play Again?","Quit"};
	    int selection = popup.showOptionDialog(
						   null,
						   "-~*´¨¯¨`*·~-.¸-  You beat the game!!  -,.-~*´¨¯¨`*·~-\nScore: "+score,
						   "Good Job!",
						   JOptionPane.YES_NO_OPTION,
						   JOptionPane.INFORMATION_MESSAGE, null,
						   options, options[0]);
	    if(selection == JOptionPane.YES_OPTION)
		{
		    long time = levels[0].getSecondsToSolve();
		    updateTimeLabel(time / 60, time % 60);
		    newGame(0);
		    score = 0;
		    updateScore();
		}
	    else{
		if(score > board.getLowestScore())
		    {
			storeHighScore();		
		    }
		else
		    System.exit(0);
	    }
	    
	} 
        else {
	    while(true){
		JOptionPane popup = new JOptionPane("Game Over");
		Object[] options = {"Try Again?","Quit"};
		int selection = popup.showOptionDialog(
						       null,
						       "Please Try Again\nScore: "+score,
						       "Game Over",
						       JOptionPane.YES_NO_OPTION,
						       JOptionPane.INFORMATION_MESSAGE, null,
						       options, options[0]);
		if(selection == JOptionPane.YES_OPTION)
		    {
			JOptionPane popup2 = new JOptionPane("Warning");
			Object[] options2 = {"Continue","Cancel"};
			int selection2 = popup2.showOptionDialog(
								 null,
								 "You will lose all of your points if you restart the game\nScore: "+score,
								 "Warning",
								 JOptionPane.YES_NO_OPTION,
								 JOptionPane.WARNING_MESSAGE, null,
								 options2, options2[0]);
			if(selection2 == JOptionPane.YES_OPTION)
			    {	
				long time = level.getSecondsToSolve();
				updateTimeLabel(time / 60, time % 60);
				newGame(currentLevel);
				score=0;
				break;
			    }	
		    }
		else
		    if(score>board.getLowestScore())
			{
			    storeHighScore();		
			}
		    else
			System.exit(0);	
	    }
	}
    }
    
    /**
       If two cards are showing, flips them back over
    */
    public void flipBack() {
	
	if(grid.isTwoFlipped()){
	    JButton jb = buttons[grid.getFlipped()];
	    jb.setEnabled(true);
	    jb.setIcon(imgBlank);
	    grid.flip(grid.getFlipped());
	    jb = buttons[grid.getFlipped()];
	    jb.setEnabled(true);
	    jb.setIcon(imgBlank);
	    grid.flip(grid.getFlipped());
	    //playFlipSound();
	}
    }
    
    /**Loads the imageIcons
     *
     */
    public void loadImageIcons() {
	//get the current classloader (needed for getResource method.. )
	// (which is required for jws to work)
	//ClassLoader classLoader = this.getClass().getClassLoader();
	Class classs = this.getClass();
	//load Icons
	for (String image : images8) {
	    imgIcons.add(new ImageIcon(classs.getResource(image)));
	}
	for (String image : images10) {
	    imgIcons.add(new ImageIcon(classs.getResource(image)));
	}
	imgBlank = new ImageIcon(classs.getResource("/images/000.jpg"));
    }
    public void storeHighScore(){			
	JOptionPane popup2 = new JOptionPane("Congratz!");
	Object[] options2 = {"Yes","No"};
	int selection2 = popup2.showOptionDialog(
						 null,
						 "You have recieved a high score!\nScore: "+score+"\nWould you like to save the score?",
						 "Congratulation!",
						 JOptionPane.YES_NO_OPTION,
						 JOptionPane.INFORMATION_MESSAGE, null,
						 options2, options2[0]);
	if(selection2 == JOptionPane.YES_OPTION)
	    {
		JButton b = new JButton("Submit");
		text.setColumns(20);
		ActionListener bListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    String n = text.getText();
			    board.add(n,score);
			    inputBoard.dispatchEvent(new WindowEvent(inputBoard,WindowEvent.WINDOW_CLOSING));
			    
			    showHighScoreBoard();
			    mainFrame.dispatchEvent(new WindowEvent(mainFrame,WindowEvent.WINDOW_CLOSING));
			}
		    };
		b.addActionListener(bListener);
		
		inputBoard.getContentPane().add(BorderLayout.WEST,textLabel);
		inputBoard.getContentPane().add(BorderLayout.EAST,b);
		inputBoard.getContentPane().add(BorderLayout.CENTER,text);
		inputBoard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputBoard.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		inputBoard.setSize(300, 100);
		inputBoard.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		inputBoard.setLocation((int)(screenSize.getWidth()/2 - inputBoard.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - inputBoard.getSize().getHeight()/2));
		inputBoard.setVisible(true);
		text.requestFocus();
	    }
	else{
	    System.exit(0);
	}
    }
    public void showHighScoreBoard(){
	JFrame scoreboard = new JFrame("High Score Board");
	scoreboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
