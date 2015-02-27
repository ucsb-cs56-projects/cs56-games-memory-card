package edu.ucsb.cs56.projects.games.memorycard;
import java.io.*;
import java.awt.Font;
import javax.swing.JTextArea;



/**
* A Highscore display for the Memory Card Game.
*
*  @author Julio Maldonado, Shelby Elgood
*  @version CS56 Winter 2015
*/

public class HighScoreBoard{

    private JTextArea board;
    private int[] scores=new int[10];
    private String[] names=new String[10];

    /** Contructor
	Displays text versions of the top 10 scores that have been achieved by users on a JTextArea object.
    */
    public HighScoreBoard(){
	getHighScores();
    }

    /**Appends rank (from 1-10), name (max 10 char), and score (max 10 char) to display board.
       @return Appends the User's rank (from 1-10), name (max 10 char), and score (max 10 char) to the display board.
    */
	private void getHighScores(){
		File file = new File("highscores.txt");
		try{
			BufferedReader b = new BufferedReader(
					   new InputStreamReader( 
					   new FileInputStream(file)));
			String line;
			int i=0;
			while((line=b.readLine())!=null&&i<10){
			    String[] parse = line.split(","); //splits string line at each ',' and makes String[] parse take the segments as array components
			    scores[i]=Integer.parseInt(parse[1]); //Turns string of a number into Int form
			    names[i]=parse[0];
			    i++;
			}

			b.close();
		} catch(IOException e){
		    e.printStackTrace();
		}
		board = new JTextArea(15,25);
		board.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
		for(int i=0;i<10;i++){
			if(scores[i]==0)
				break;
			board.append((i+1)+". "+names[i]+scores[i]+"\n");

		}
	}

    /**Returns the last score in the array
       @return The lowest score out of the ten.
     */
	public int getLowestScore(){
		return scores[9];
	}

    /**Adds username and score to array
       @param name username for player
       @param score point value achieved by player
       @return Calls update() to update username and score in their respective arrays.
     */
	public void add(String name, int score){
		if(name.length()>20)
		name=name.substring(0,20);
		while(name.length()<=35)//adds spaces for space between name and score
			name+=" ";
		int i=0;
		for(i=0;i<10;i++){
			if(scores[i]<score)
			break;
		}
		if(i>=10)
		return;
		int temp=scores[i];
		scores[i]=score;
		for(int j=i+1;j<10;j++){
			int temp2=scores[j];
			scores[j]=temp;
			temp=temp2;
		}
		String str=names[i];
		names[i]=name;
		for(int j=i+1;j<10;j++){
			String str2=names[j];
			names[j]=str;
			str=str2;
		}
		update();
	}

    /**Updates the highscores file with new highscore and username values
       @return Calls getHighScores() to update the contents.
     */
	private void update(){
		File file = new File("highscores.txt");
		try{
			BufferedWriter writer = new BufferedWriter(
						new FileWriter(file));
			for(int i=0;i<10;i++){
				if(scores[i]==0)
					break;
				writer.write(names[i]+","+scores[i]+"\n");
			}
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		getHighScores();
	}

    /**Returns the display board
       @return The 'board' which displays the highscore listing content.
     */
	public JTextArea getBoard(){
		return board;
	}	
}
