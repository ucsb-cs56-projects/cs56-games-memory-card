package edu.ucsb.cs56.S12.issues.issue0000805;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
* makes the grid (ArrayList of MemoryCards) for the game
* @author Mathew Glodack, Christina Morris
* @version CS556, S13, 5/8/13
*/

public class MemoryGrid{
    ArrayList<MemoryCard> memGrid = new ArrayList<MemoryCard>();
    int size=16;
    boolean isOver=false;

    /**
    *default constructor makes new MemoryGrid
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

    /**
    *@return the size of the grid (total number of MemoryCard objects)
    */
    public int getSize(){
        return this.size;
    }

    /**
    *checks if array contains 2 of same value
    *used for testing
    *@param temp check to see if temp memory card is in the memorycard arraylist twice
    */
    public boolean contains2(MemoryCard temp){
        int count=0;
        for(MemoryCard comp: memGrid){
            if(comp.Equals(temp))    
                count++;
        }
        return (count==2);
    }
    
    /**
    *checks array if one object is flipped
    *@return boolean true if one card is flipped, false otherwise
    */
    public boolean isOneFlipped(){
        int count=0;
        for(MemoryCard comp: memGrid){
            if(comp.isFlipped()==true)
                count++;
        }
        return (count==1);
    }
    
    /**
    *checks array to see if 2 objects are flipped
    *@return boolean true if two cards are flipped, false otherwise
    */
    public boolean isTwoFlipped(){
        int count=0;
        for(MemoryCard comp: memGrid){
            if(comp.isFlipped()==true)
                count++;
        }
        return(count==2);
    }
    
    /**
    *checks if two MemoryCards are equal
    *@param i the location in the arraylist of the memorycard that is being compared
    *@return boolean true if the indexed card is equal to the memory card that is being compared
    */
    public boolean flippedEquals(int i){
       MemoryCard temp = memGrid.get(i);
       MemoryCard comp = memGrid.get(this.getFlipped(i));
       return comp.Equals(temp);
    }
       
    /**
    *@return retval the position of the flipped MemoryCard
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
    
    /**
    *@return retval the position of the flipped MemoryCard that is not index i
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
    
    /**
    *@return boolean true if the came is over, false if the game is not over
    */
    public boolean isOver(){
        return isOver;
    }
    
    /**
    *@return value of MemoryCard[i]
    */
    public int getVal(int i){
        MemoryCard temp = memGrid.get(i);
        return temp.getVal();
    }
    
    /**
    *flips MemoryCard[i]
    *@param i location in memory grid that should be flipped
    */
    public void flip(int i){
        MemoryCard temp = memGrid.get(i);
        temp.flip();
    }
}
