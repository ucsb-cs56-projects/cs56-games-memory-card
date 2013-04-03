package edu.ucsb.cs56.S12.issues.issue0000805;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
* makes the grid (ArrayList of MemoryCards) for the game
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
    *returns the size of the grid (total number of MemoryCard objects)
    */
    public int getSize(){
        return this.size;
    }

    /**
    *checks if array contains 2 of same value
    *used for testing
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
    */
    public boolean flippedEquals(int i){
       MemoryCard temp = memGrid.get(i);
       MemoryCard comp = memGrid.get(this.getFlipped(i));
       return comp.Equals(temp);
    }
       
    /**
    *returns the position of the flipped MemoryCard
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
    *returns the position of the flipped MemoryCard that is not index i
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
    *returns whether or not game is over
    */
    public boolean isOver(){
        return isOver;
    }
    
    /**
    *returns value of MemoryCard[i]
    */
    public int getVal(int i){
        MemoryCard temp = memGrid.get(i);
        return temp.getVal();
    }
    
    /**
    *flips MemoryCard[i]
    */
    public void flip(int i){
        MemoryCard temp = memGrid.get(i);
        temp.flip();
    }
}
