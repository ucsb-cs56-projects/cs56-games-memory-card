package edu.ucsb.cs56.projects.games.memorycard;
        
/**
 * Class MemoryCard that makes/controls objects for cards
 */

/**
 * @author Shelby Elgood and Julio Maldonado 
 * @version CS56 Winter 2015
 * Class representing a MemoryCard object
 */


public class MemoryCard{
    /**
     * int for value of card
     */
    int val;
    /**
     * boolean representing if card is flipped or not
     */
    boolean flipped;
    
    /**	
     *Default Donstructor for MemoryCard (sets flipped as false)
     */
    public MemoryCard(){
        flipped = false;
    }
    
    /**
     *Constructor to set value (flipped still set as false)
	@param value - int to set value equal to
     */
    public MemoryCard(int tVal){
        flipped = false;
        val = tVal;
    }
    
    /**
     *Method that determinds if card is flipped
	@return boolean representing if card is flipped 
     */
    public boolean isFlipped(){
        return flipped;
    }
    
    /**
     *Method that changes value of boolean flipped (doesnt actually flip card)
     */
    public void flip(){
	flipped = flipped ? false : true;
    }
    
    /**
     *Method that gets value
     * @return value of card
     */
    public int getVal(){
        return val;
    }
    
    /**
     *Method to set value
     *@param value
     */
    public void setVal(int value){
        val=value;
    }
    
    /**
     *Equals method to check if values are equal
     *@return boolean representing if equal or not
     */
    public boolean Equals(Object o){
        final MemoryCard second = (MemoryCard) o;
        return(this.getVal()==second.getVal());
    }
}
