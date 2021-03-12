/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Stephen M Boussarov)
 * 
 * This file contains the CellMoveToggleChild subclass of CellMoveToggle.
 */

import java.util.*;


/**
 * This class derives from CellMoveToggle. It has two constructors 
 * and overrides the checkApoptosis method.
 */
public class CellMoveToggleChild extends CellMoveToggle
{
    /**constants*/
    private static final int APOP2 = 2;
    private static final int APOP5 = 5;
    private static final int APOP10 = 10;
    private static final int MOVE_TO_SIZE = 2;
    
    /**instance variables*/
    public static int numAlive;  //instances of this cell type currently alive
    
    /**
     * This constructor will call the parent class' constructor to initialize
     * the instance variables. It will also increment numAlive by 1
     * 
     * @param currRow, currCol, mass
     */
    public CellMoveToggleChild(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        numAlive+=1;
    }
    
    /**
     * This copy constructor will call the parent class' copy constructor
     * to initialize the instance variables
     * 
     * @param otherCCellMoveToggleChild
     */
    public CellMoveToggleChild(CellMoveToggleChild otherCCellMoveToggleChild)
    {
        super(otherCCellMoveToggleChild);
        numAlive = otherCCellMoveToggleChild.numAlive;
    }
    
  
    /**
     * This method will return the string representation of the current object.
     * 
     * @return T or t depending on toggle
     */
    public String toString()
    {
        //does not override CellMoveToggle's toString
        if (toggled = true)
        {
            return "T";
        }
        else
        {
            return "t";
        }
    }
    
    /** 
     * This method calls apoptosis method and decrements numAlive
     */
    public void apoptosis()
    {
        super.apoptosis();
        numAlive = numAlive-1;
    }
    
    /**
     * This method checks if the cell should initiate apoptosis or not by 
     * determining the number of neighbors the cell has.
     * 
     * @param cell list neighbors
     * @return true if conditions met.
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        /** 
         * Override apoptosis() to not only call its parent's apoptosis() 
         * method but to also decrement numAlive by 1.
         */
        if((neighbors.size()<APOP2 || neighbors.size()>APOP5) 
        && numAlive<APOP10)
        {
            //if less than 2 or greater than 5 neighbors and less than 10 alive
            return true;
            
        }
        else
        {
            return false;
        }
    }
    
    /**
     * This method will return a deep copy of the calling object.
     *
     * @return Cell type
     */
    public Cell newCellCopy()
    {
        return super.newCellCopy();
    }
    
    /**
     * Defines how this cell can move. Returns an array of length 2 that
     * indicates new position. Does not override CellMoveToggle's getMove
     * 
     * @return array of length 2 that indicates new position
     */
    public int[] getMove()
    {
        int[] moveTo = new int[MOVE_TO_SIZE];
        
        //do the same as CellMoveToggle
        if (toggled == true)
        {
            //move up
            moveTo[0] = currRow + 1; 
            moveTo[1] = currCol;
        }
        else
        {
            //don't move
            moveTo[0] = currRow;
            moveTo[1] = currCol;
        }
        
        //flip the toggle
        if(toggled == true)
        {
            //true becomes false
            toggled = false;
        }
        else
        {
            //false becomes true
            toggled = true;
        }
        
        return moveTo;
    }
    
}