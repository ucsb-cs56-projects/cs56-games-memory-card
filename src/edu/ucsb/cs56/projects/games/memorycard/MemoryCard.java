package edu.ucsb.cs56.projects.games.memorycard;
        
/**
* makes objects for cards
*/

public class MemoryCard{
    /**
    * int for value of card
    */
    int val;
    boolean flipped;
    
    /**	
    *default constructor for MemoryCard (sets flipped as false)
    */
    public MemoryCard(){
        flipped = false;
    }
    
    /**
    *constructor to set value (flipped still set as false)
    */
    public MemoryCard(int tVal){
        flipped = false;
        val = tVal;
    }
    
    /**
    *returns whether card is flipped
    */
    public boolean isFlipped(){
        return flipped;
    }
    
    /**
    *changes value of flipped (doesnt actually flip card)
    */
    public void flip(){
	flipped = flipped ? false : true;
    }
    
    /**
    *returns value
    */
    public int getVal(){
        return val;
    }
    
    /**
    *Method to set value
    */
    public void setVal(int value){
        val=value;
    }
    
    /**
    *Equals method to check if values are equal
    */
    public boolean Equals(Object o){
        final MemoryCard second = (MemoryCard) o;
        return(this.getVal()==second.getVal());
    }
}
