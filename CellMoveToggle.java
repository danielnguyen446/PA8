/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Stephen M Boussarov) 
 * 
 * This file contains the CellMoveToggle subclass of CellMoveUp. This
 * concrete subclass is denoted by "T" or "t" on the petri dish.
 */

import java.util.*;

/**
 * This class derives from CellMoveUp. It has two constructors and overrides the 
 * toString and checkApoptosis methods.
 */
public class CellMoveToggle extends CellMoveUp 
{
    /**constants*/
    private static final int APOP2 = 2;
    private static final int APOP5 = 5;
    private static final int MOVE_TO_SIZE = 2;
    
    /**instance variable*/
    public boolean toggled;
    
    /**
     * This constructor will call the parent class' constructor to initialize
     * the instance variables
     * 
     * @param currRow, currCol, mass
     */
    public CellMoveToggle(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        toggled = true;  //toggled should be defaulted to true
    }
    
    /**
     * This copy constructor will call the parent class' copy constructor
     * to initialize the instance variables
     * 
     * @param otherCellMoveToggle
     */
    public CellMoveToggle(CellMoveToggle otherCellMoveToggle)
    {
        super(otherCellMoveToggle);
        toggled = otherCellMoveToggle.toggled;
    }
    
    /**
     * This method will return the string representation of the current object.
     * 
     * @return T for true and t for false
     */
    public String toString()
    {
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
     * This method checks if the cell should initiate apoptosis or not by 
     * determining the number of neighbors the cell has.
     * 
     * @param cell list neighbors
     * @return whether condition met
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        if(neighbors.size()<APOP2 || neighbors.size()>APOP5)
        {
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
        return new CellMoveToggle(this);
    }
    
    /**
     * Defines how this cell can move. Returns an array of length 2 that
     * indicates new position. Movement depends on toggled.
     * 
     * @return array of length 2 that indicates new position
     */
    public int[] getMove()
    {
        int[] moveTo = new int[MOVE_TO_SIZE];
        
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