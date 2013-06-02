package edu.ucsb.cs56.projects.games.memorycard;
import java.io.*;
import javax.swing.JTextArea;
public class HighScoreBoard{
	private JTextArea board;
	private int[] scores=new int[10];
	private String[] names=new String[10];
	public HighScoreBoard(){
		getHighScores();

	}
	private void getHighScores(){
		File file = new File("highscores.txt");
		try{
			BufferedReader b = new BufferedReader(
					   new InputStreamReader( 
					   new FileInputStream(file)));
			String line;
			int i=0;
			while((line=b.readLine())!=null&&i<10){
				String[] parse = line.split("/");
				scores[i]=Integer.parseInt(parse[1]);
				names[i]=parse[0];
				i++;
			}
			
			b.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		board = new JTextArea(15,25);
		for(int i=0;i<10;i++){
			if(scores[i]==0)
				break;
			board.append((i+1)+". "+names[i]+"\t\t"+scores[i]+"\n");

		}
	}
	public int getLowestScore(){
		return scores[9];
	}
	public void add(String name, int score){
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
	private void update(){
		File file = new File("highscores.txt");
		try{
			BufferedWriter writer = new BufferedWriter(
						new FileWriter(file));
			for(int i=0;i<10;i++){
				if(scores[i]==0)
					break;
				writer.write(names[i]+"/"+scores[i]+"\n");
			}
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		getHighScores();
	}
	public JTextArea getBoard(){
		return board;
	}	
}
