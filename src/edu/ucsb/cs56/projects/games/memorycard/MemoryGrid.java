package edu.ucsb.cs56.projects.games.memorycard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
* makes the grid (ArrayList of MemoryCards) for the game
* @author Mathew Glodack, Christina Morris
* @version CS56, S13, 5/8/13
*/

public class MemoryGrid{
    ArrayList<MemoryCard> memGrid = new ArrayList<MemoryCard>();
    int size=16;
    boolean isOver=false;
    
    /**
     * default constructor makes new MemoryGrid
     */
    public MemoryGrid(){
        for(int i=1; i<((size/2)+1); i++){
            MemoryCard temp  = new MemoryCard(i);
	    MemoryCard temp2 = new MemoryCard(i);
            memGrid.add(temp);
	    memGrid.add(temp2);
        }   
	Collections.shuffle(memGrid);     //shuffles the in order ArrayList
    }
    
    /**Method getSize()
     * @return the size of the grid (total number of MemoryCard objects)
     */
    public int getSize(){
        return this.size;
    }
    
    /**Method contains2
     * Checks if array contains 2 of same value
     * @param temp check to see if temp memory card is in the memorycard arraylist twice
     */
    public boolean contains2(MemoryCard temp){
        int count=0;
        for(MemoryCard comp: memGrid){
            if(comp.Equals(temp))    
                count++;
        }
        return (count==2);
    }
    
    /**Method isOneFlipped()
     * Checks array if one object is flipped
     * @return boolean true if one card is flipped, false otherwise
     */
    public boolean isOneFlipped(){
        int count=0;
        for(MemoryCard comp: memGrid){
            if(comp.isFlipped()==true)
                count++;
        }
        return (count==1);
    }
    
    /**Method is TwoFlipped()
     * Checks array to see if 2 objects are flipped
     * @return boolean true if two cards are flipped, false otherwise
     */
    public boolean isTwoFlipped(){
        int count=0;
        for(MemoryCard comp: memGrid){
            if(comp.isFlipped()==true)
                count++;
        }
        return(count==2);
    }
    
    /**Method flippedEquals(int i)
     * Checks if two MemoryCards are equal
     * @param i the location in the arraylist of the memorycard that is being compared
     * @return boolean true if the indexed card is equal to the memory card that is being compared
     */
    public boolean flippedEquals(int i){
	MemoryCard temp = memGrid.get(i);
	MemoryCard comp = memGrid.get(this.getFlipped(i));
	return comp.Equals(temp);
    }
    
    /**Method getFlipped()
     * @return retval the position of the flipped MemoryCard
     */
    public int getFlipped(){
        int retVal=-1, count=-1;
        for(MemoryCard comp: memGrid){
            count++;
            if(comp.isFlipped()==true)
                retVal=count;
        }
        return retVal;
    }
    
    /** Method getFlipped(int i)
     * @return retval the position of the flipped MemoryCard that is not index i
     */
    public int getFlipped(int i){
        int retVal=-1, count=-1;
        for(MemoryCard comp: memGrid){
            count++;
            if( (comp.isFlipped()==true) && (memGrid.get(i)!=comp))
                retVal=count;
        }
        return retVal;
    }
    
    /**Method isOver()
     * @return boolean true if the came is over, false if the game is not over
     */
    public boolean isOver(){
        return isOver;
    }
    
    /**Methd getVal(int i)
     * @return value of MemoryCard[i]
     */
    public int getVal(int i){
        MemoryCard temp = memGrid.get(i);
        return temp.getVal();
    }
    
    /**Method flip(int i)
     * flips MemoryCard[i]
     * @param i location in memory grid that should be flipped
     */
    public void flip(int i){
        MemoryCard temp = memGrid.get(i);
        temp.flip();
    }
}
