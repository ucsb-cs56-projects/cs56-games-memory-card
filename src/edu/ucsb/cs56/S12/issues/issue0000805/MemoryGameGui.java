package edu.ucsb.cs56.S12.issues.issue0000805;
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

/**
 *
 * @author Bryce McGaw and Jonathan Yau
 * Edited Professor Phill Conrad's code from Lab06
 * @author Mathew Glodack, Christina Morris
 * @version Edited, CS56, 5/7/13, S13
 */
public class MemoryGameGui {

    static JFrame frame = new JFrame();
    static MemoryGrid grid = new MemoryGrid();
    static MemoryGameComponent mgc = new MemoryGameComponent(grid);
    //static JButton restartB = new JButton("Restart");
    static RestartButtonHandler RBHandler;


    /** main method to open JFrame 
     *
     */
    
    public static void main (String[] args) {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//restartB.addActionListener(RBHandler);
	
	frame.getContentPane().add(mgc);
	//frame.getContentPane().add(restartB);
	
	// to make sure that grids go left to right
	
	frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	frame.setSize(300,300);
	frame.setVisible(true);
    }

    private class RestartButtonHandler implements ActionListener{

	public void actionPerformed(ActionEvent e){

	    grid = new MemoryGrid();
	    mgc = new MemoryGameComponent(grid);
	    
	    frame.getContentPane().add(mgc);
	    //frame.getContentPane().add(restartB);
	    
	}
    }

}
