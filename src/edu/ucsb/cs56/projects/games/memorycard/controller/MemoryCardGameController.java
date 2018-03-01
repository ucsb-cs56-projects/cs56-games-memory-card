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
* @author Annan Zhang, Shihua Lu
* @version CS56 Winter 2018
* Edited Professor Phill Conrad's code from Lab06
*/
public class MemoryCardGameController{
    MemoryCardGameView view = new MemoryCardGameView();
    
    public void mainGUI(){
        view.mainGUI();
    }
    
}
