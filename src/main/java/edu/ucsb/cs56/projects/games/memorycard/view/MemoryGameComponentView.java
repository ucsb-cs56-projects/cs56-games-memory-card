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
public class MemoryGameComponentView extends JComponent {

    private JButton[] buttons;
    private ArrayList<ImageIcon> imgIcons = new ArrayList<ImageIcon>();
    public JComponent restartB = new JButton("Restart");
    private Icon imgBlank;
    private JButton pauseButton;
    private JButton musicButton;
    private JLabel timeLabel = null;
    private JLabel scoreLabel = null;
    private JLabel levelLabel = null;
    private JTextField text = new JTextField(20);//for inputing name for high score board
    private JFrame inputBoard = new JFrame("ENTER FIRST 20 CHARACTERS OF YOUR NAME");//for inputing name for high score board
    private JLabel textLabel = new JLabel("ENTER YOUR NAME: ");//for inputing name for high score board
    private JFrame mainFrame = null;
    private HighScoreBoard board = null;
    private Clip clip;
    private boolean firstImageFlipped = false;
    private MemoryGameComponent MGCcontroller;

    /**
     * Constructor
     *
     * @param game an object that implements the MemoryGrid interface
     *             to keep track of the moves in each game, ensuring the rules are
     *             followed and detecting when the user has won.
     */
    public MemoryGameComponentView(MemoryGameComponent controller) {
        super();
        this.MGCcontroller = controller;
        timeLabel = new JLabel("Time Remaining");
        scoreLabel = new JLabel("Score");
        levelLabel = new JLabel("Level");
        mainFrame = new JFrame();
        buttons = new JButton[MGCcontroller.getGridSize()];
        loadImageIcons(); // loads the array list of icons and sets imgBlank
        buildTiles(MGCcontroller.getGridSize());
    }
    

    
    /**
     * Pause the timer and the game by showing a dialog box,
     * preventing the user from playing the game until they resume.
     */
    public boolean pauseGame() {
        JOptionPane popup = new JOptionPane("PAUSED");
        Object[] options = {"Resume"};

        int selection = popup.showOptionDialog(
                null,
                "GAME PAUSED",
                "PAUSED",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);
        return (selection == JOptionPane.YES_OPTION);
    }

    /**
     * Play sound from a WAV filepath
     * This is a helper method
     */
    public void playSound(String filepath, boolean music_status) {
        try {
            if (music_status == true) {
                clip = AudioSystem.getClip();
                File test = new File(filepath);
                AudioInputStream audio = AudioSystem.getAudioInputStream(test);
                clip.open(audio);
                clip.start();
            }
            if (music_status == false) {
                clip.stop();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Play sound effect after flipping
     */
    public void playFlipSound() {
        playSound("./src/main/resources/wall_pickup_01.wav", true);
    }

    /**
     * Play sound effect if two cards matched
     */
    public void playMatchSound() {
        playSound("./src/main/resources/training_finished_03.wav", true);
    }

    /**
     * Play sound effect after winning the game
     */
    public void playWinSound() {
        playSound("./src/main/resources/Final Fantasy VII - Victory.wav", true);
    }

    //AudioPlayer MGP = AudioPlayer.player;
    //AudioStream BGM;
    //AudioData MD;

    /**
     * Play background music
     */
    public void playMusic() {
        playSound("./src/main/resources/Hiromi Haneda.wav", true);
    }

    /**
     * Stop background music
     */
    public void stopMusic() {
        //AudioPlayer.player.stop(BGM);
        playSound("./src/main/resources/Hiromi Haneda.wav", false);
    }

    /**
     * Resume the game timer. In other words, recalculate total pause
     * time and start the timer.
     */
    public boolean pauseB() {
        JOptionPane popup = new JOptionPane("PAUSED");
        Object[] options = {"Resume"};

        int selection = popup.showOptionDialog(
                null,
                "GAME PAUSED",
                "PAUSED",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options, options[0]);

        return (selection == JOptionPane.YES_OPTION);
    }

    /**pause() pauses the time
     public void pause() {
     pauseStart = new Date().getTime();
     timer.stop();

     }
     */


    /**
     * Updates Time Label with two parameters
     *
     * @param minutes
     * @param seconds
     */
    public void updateTimeLabel(long minutes, long seconds) {
        String m = " minutes, ";
        if (minutes == 1) m = " minute, ";
        if (minutes > 0) {
            timeLabel.setText("Time Remaining: " + minutes + m + seconds + " seconds");
        } else {
            timeLabel.setText("Time Remaining: " + seconds + " seconds");
        }
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score + " ");
    }

    public void updateLevel(int totalLevels, int currentLevel) {
        levelLabel.setText("Level: " + (currentLevel + 1) + "/" + totalLevels);
    }


    /**
     * setLabel()
     *
     * @param label sets the timeLabel
     */
    public void setLabel(JLabel label) {
        this.timeLabel = label;
    }

    public void setMainFrame(JFrame f) {
        mainFrame = f;
    }

    public void setScoreLabel(JLabel l) {
        scoreLabel = l;
    }

    public void setLevelLabel(JLabel l) {
        levelLabel = l;
    }

    public void setHighScoreBoard(HighScoreBoard h) {
        board = h;
    }

    /**
     * setPauseButton
     *
     * @param b sets the pauseButton
     */
    public void setPauseButton(JButton b) {
        pauseButton = b;
        pauseButton.setEnabled(false);
    }

    /**
     * setMusicButton
     *
     * @param b sets the musicButton
     */
    public void setMusicButton(JButton b) {
        musicButton = b;
        musicButton.setEnabled(false);
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


    /**
     * buildTiles() constructs the tiles
     */
    public void buildTiles(int gridSize) {
        this.removeAll();
        //set layout to a grid of length sqrt(grid size)
        this.setLayout(new GridLayout(0, (int) Math.sqrt(gridSize)));
        buttons = new JButton[gridSize];
        for (int i = 0; i <= (gridSize - 1); i++) {
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
    }

    /**
     * Class ButtonListener implents ActionLister
     * defines the actionPerformed methods for the buttons
     */
    class ButtonListener implements ActionListener {
        private MemoryGrid grid = MGCcontroller.getGrid();
        private Timer timer = MGCcontroller.getTimer();
        private MemoryGameLevel level = MGCcontroller.getLevel();
        private int num;
        private Timer t;

        /**
         * ButtonListener method
         *
         * @param i for the num
         */
        public ButtonListener(int i) {
            super();
            this.num = i;
        }

        @Override
        public void actionPerformed(ActionEvent event) {

            Class classs = this.getClass();
            Icon imgBlank = new ImageIcon(
                    classs.getResource("/images/000.jpg"));

            //if 2 MemoryCards are flipped, flip back over
            if (t != null)
                t.stop();
            flipBack();

            //if no MemoryCards are flipped, flip one
            if (!grid.isOneFlipped()) {
                if (!firstImageFlipped) {
                    MGCcontroller.setStartTime(new Date().getTime());
                    timer.start();
                    firstImageFlipped = true;
                    pauseButton.setEnabled(true);
                    musicButton.setEnabled(true);
                }
                grid.flip(num);
                playFlipSound();
                JButton jb = buttons[num];

                ImageIcon i = imgIcons.get(grid.getVal(num) - 1);
                Image img = i.getImage();
                Image newimg = img.getScaledInstance(jb.getWidth() * 3, jb.getHeight() * 3, java.awt.Image.SCALE_SMOOTH);
                i = new ImageIcon(newimg);

                jb.setIcon(i); //set image according to val
                jb.setDisabledIcon(i);
                if (num != 1) //cheat code
                    jb.setEnabled(false); //make unclickable
                else
                    //cheat code. Needs to override the button so that button is same color as regular button.
                    MGCcontroller.setCheatEnabled(true);
            }

            //if one MemoryCard is flipped, flip other
            //then check if theyre matching
            else {
                boolean cheatEnabled = MGCcontroller.getCheatEnabled();
                if ((num == 1 && cheatEnabled))//cheat code
                {
                    MGCcontroller.setCheatEnabled(false);
                    MGCcontroller.setIsOver(true);
                    endGame();
                    return;
                }
                MGCcontroller.setCheatEnabled(false);//cheat code
                grid.flip(num);
                playFlipSound();
                JButton jb = buttons[num];

                ImageIcon i = imgIcons.get(grid.getVal(num) - 1);
                Image img = i.getImage();
                Image newimg = img.getScaledInstance(jb.getWidth() * 3, jb.getHeight() * 3, java.awt.Image.SCALE_SMOOTH);
                i = new ImageIcon(newimg);
                jb.setIcon(i); //set image according to val
                jb.setDisabledIcon(i);

                jb.setEnabled(false);
                if (grid.flippedEquals(num)) { //if they're matching keep num displayed and set flipped as false
                    MGCcontroller.addGameCounterByOne();
                    grid.flip(num);
                    buttons[grid.getFlipped()].setEnabled(false);
                    grid.flip(grid.getFlipped());
                    playMatchSound();
                    MGCcontroller.setScore(30);
                    updateScore(MGCcontroller.getScore());
                    //check if game is over
                    int gameCounter = MGCcontroller.getGameCounter();
                    if (gameCounter == grid.getSize() / 2) {
                        MGCcontroller.setIsOver(true);
                        endGame();
                    }
                } else {
                    MGCcontroller.setScore(-5);
                    updateScore(MGCcontroller.getScore());
                    // start the flip back timer
                    int delay = level.getFlipTime();
                    ActionListener listener = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            flipBack();
                        }
                    };
                    t = new Timer(delay, listener);
                    t.setRepeats(false);
                    t.start();

                } // end of inner if else

            } // end of outer if else
        }

        public void flipBack() {
            if (grid.isTwoFlipped()) {
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
    }

    /**
     * Starts a new level or restarts the current level
     *
     * @param lvl changes the level of the game
     */
    public void newGame() {
        pauseButton.setEnabled(false);
        musicButton.setEnabled(true);
        firstImageFlipped = false;
        buildTiles(MGCcontroller.getGridSize());
    }

    /**
     * Resets the game
     */
    public void reset() {
        firstImageFlipped = false;
        pauseButton.setEnabled(false);
        musicButton.setEnabled(true);
        updateScore(MGCcontroller.getScore());
        updateLevel(MGCcontroller.getTotalLevels(), MGCcontroller.getCurrentLevel());
        updateTimeLabel(MGCcontroller.getLevel().getSecondsToSolve() / 60,
                        MGCcontroller.getLevel().getSecondsToSolve() % 60);
        newGame();

    }

    /**
     * Ends the game and starts a new game if the user selects new game
     * from a dialog menu.
     */
    public void endGame() {
        long deltaTime = MGCcontroller.endGame();
        int currentLevel = MGCcontroller.getCurrentLevel();
        updateScore(MGCcontroller.getScore());
        updateLevel(MGCcontroller.getTotalLevels(), MGCcontroller.getCurrentLevel());
        MemoryGameLevel level = MGCcontroller.getLevel();
        int score = MGCcontroller.getScore();
        if (deltaTime < level.getSecondsToSolve() && currentLevel < 3) {
            JOptionPane popup = new JOptionPane("Good Job!");
            Object[] options = {"Continue", "Quit"};
            playWinSound();
            int selection = popup.showOptionDialog(
                    null,
                    "-~*´¨¯¨`*·~-.¸-  You beat the level!!  -,.-~*´¨¯¨`*·~-\nScore: " + score,
                    "Good Job!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);

            if (selection == JOptionPane.YES_OPTION) {
                MemoryGameLevel[] levels = MGCcontroller.getLevels();
                long time = levels[currentLevel + 1].getSecondsToSolve();
                updateTimeLabel(time / 60, time % 60);
                MGCcontroller.newGame(currentLevel + 1);
            } else
                System.exit(0);

        } else if (deltaTime < level.getSecondsToSolve() && currentLevel == 3) {
            JOptionPane popup = new JOptionPane("Good Job!");
            Object[] options = {"Play Again?", "Quit"};
            int selection = popup.showOptionDialog(
                    null,
                    "-~*´¨¯¨`*·~-.¸-  You beat the game!!  -,.-~*´¨¯¨`*·~-\nScore: " + score,
                    "Good Job!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);
            if (selection == JOptionPane.YES_OPTION) {
                MemoryGameLevel[] levels = MGCcontroller.getLevels();
                long time = levels[0].getSecondsToSolve();
                updateTimeLabel(time / 60, time % 60);
                MGCcontroller.newGame(0);
                MGCcontroller.setScore(0);
                updateScore(MGCcontroller.getScore());
                updateLevel(MGCcontroller.getTotalLevels(), MGCcontroller.getCurrentLevel());
            } else {
                if (score > board.getLowestScore()) {
                    storeHighScore();
                } else
                    System.exit(0);
            }

        } else {
            while (true) {
                JOptionPane popup = new JOptionPane("Game Over");
                Object[] options = {"Try Again?", "Quit"};
                int selection = popup.showOptionDialog(
                        null,
                        "Please Try Again\nScore: " + score,
                        "Game Over",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null,
                        options, options[0]);
                if (selection == JOptionPane.YES_OPTION) {
                    JOptionPane popup2 = new JOptionPane("Warning");
                    Object[] options2 = {"Continue", "Cancel"};
                    int selection2 = popup2.showOptionDialog(
                            null,
                            "You will lose all of your points if you restart the game\nScore: " + score,
                            "Warning",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE, null,
                            options2, options2[0]);
                    if (selection2 == JOptionPane.YES_OPTION) {
                        long time = level.getSecondsToSolve();
                        updateTimeLabel(time / 60, time % 60);
                        MGCcontroller.newGame(currentLevel);
                        MGCcontroller.setScore(-1 * MGCcontroller.getScore());  // set score to 0
                        break;
                    }
                } else if (score > board.getLowestScore()) {
                    storeHighScore();
                } else
                    System.exit(0);
            }
        }
    }


    /**
     * Loads the imageIcons
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

    public void storeHighScore() {
        JOptionPane popup2 = new JOptionPane("Congratz!");
        Object[] options2 = {"Yes", "No"};
        int score = MGCcontroller.getScore();
        int selection2 = popup2.showOptionDialog(
                null,
                "You have received a high score!\nScore: " + score + "\nWould you like to save the score?",
                "Congratulation!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                options2, options2[0]);
        if (selection2 == JOptionPane.YES_OPTION) {
            JButton b = new JButton("Submit");
            text.setColumns(20);
            ActionListener bListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String n = text.getText();
                    board.add(n, score);
                    inputBoard.dispatchEvent(new WindowEvent(inputBoard, WindowEvent.WINDOW_CLOSING));

                    showHighScoreBoard();
                    mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                }
            };
            b.addActionListener(bListener);

            inputBoard.getContentPane().add(BorderLayout.WEST, textLabel);
            inputBoard.getContentPane().add(BorderLayout.EAST, b);
            inputBoard.getContentPane().add(BorderLayout.CENTER, text);
            inputBoard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inputBoard.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            inputBoard.setSize(300, 100);
            inputBoard.pack();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            inputBoard.setLocation((int) (screenSize.getWidth() / 2 - inputBoard.getSize().getWidth() / 2), (int) (screenSize.getHeight() / 2 - inputBoard.getSize().getHeight() / 2));
            inputBoard.setVisible(true);
            text.requestFocus();
        } else {
            System.exit(0);
        }
    }

    public void showHighScoreBoard() {
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
        scoreboard.setSize(350, 350);
        Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
        scoreboard.setLocation((int) (screenSize2.getWidth() / 2 - scoreboard.getSize().getWidth() / 2), (int) (screenSize2.getHeight() / 2 - scoreboard.getSize().getHeight() / 2));
        scoreboard.setVisible(true);
    }


}
