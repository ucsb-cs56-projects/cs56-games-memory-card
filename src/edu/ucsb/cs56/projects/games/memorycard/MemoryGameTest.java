package edu.ucsb.cs56.projects.games.memorycard;

import org.junit.Test;
import static org.junit.Assert.*;

/**
	The test class MemoryGameTest - tests the MemoryGame class
	@author Bryce McGaw and Jonathan Yau
	
	Minor changes made by Ryan Halbrook for cs56 proj1, Spring 2013
	I only made minor changes in this file because I added a parameter 
	to the MemoryGrid constructor. I just put in a grid size of 16 to
	all constructors to make this compile. A good change would be to test 
	other parameters.
	
	@version 1.0.0 5/14/2012
	@see MemoryGame.java
*/
public class MemoryGameTest
{
	@Test public void testConstructor01()
	{
              //counter to check if count at end is (size/2)-1 ((size/2) nums)
                int count=0;
		// make new grid
                MemoryGrid test1 = new MemoryGrid(16);
		// test that not more than 2 squares share the same num
                MemoryCard testMem = new MemoryCard();
                for(int i=1; i<(test1.size/2); i++){
                    testMem.setVal(i);
                    if(test1.contains2(testMem)){
                        count++;
                    }
                }
                assertEquals(count,((test1.size/2)-1));
                    
	}
        
        @Test public void testRandom()
        {
            int i=0,j=0;  //int to increment for each similar value
            //two grids to compare
            MemoryGrid test1 = new MemoryGrid(16);
            MemoryGrid test2 = new MemoryGrid(16);
            MemoryCard temp2 = test2.memGrid.get(j);
            for(MemoryCard temp: test1.memGrid){
                if(temp.getVal()==temp2.getVal())
                    i++;
		  j++;

		  if(j<test1.size)
                    temp2 = test2.memGrid.get(j);

            }
            assertFalse(i==test1.size/2);
        }
        
        @Test public void testEquals()
        {
            //two memory cards initialized to see if Equals works
            MemoryCard t1 = new MemoryCard(4);
            MemoryCard t2 = new MemoryCard(4);
            assertTrue(t2.Equals(t1));
        }
	
	@Test public void testisOneFlipped()
        {
            MemoryGrid t1 = new MemoryGrid(16);
            t1.memGrid.get(4).flip();
            assertTrue(t1.isOneFlipped());
        }
        
        @Test public void testisTwoFlipped()
        {
            MemoryGrid t1 = new MemoryGrid(16);
            t1.memGrid.get(1).flip();
            t1.memGrid.get(6).flip();
            assertTrue(t1.isTwoFlipped());
        }
        
        @Test public void testgetFlipped()
        {
            MemoryGrid t1 = new MemoryGrid(16);
            t1.memGrid.get(13).flip();
            assertEquals(t1.getFlipped(), 13);
        }
        
        @Test public void testgetFlipped2()
        {
            MemoryGrid t1 = new MemoryGrid(16);
            t1.memGrid.get(10).flip();
            t1.memGrid.get(4).flip();
            assertEquals(t1.getFlipped(10), 4);
        }
        
        @Test public void testisOver()
        {
            MemoryGrid t1 = new MemoryGrid(16);
            t1.isOver=true;
            assertEquals(t1.isOver(), true);
        }
}
