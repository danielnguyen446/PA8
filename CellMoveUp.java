/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Stephen M Boussarov)
 * 
 * This file contains the CellMoveUp subclass of cell. This
 * concrete subclass is denoted by "^" on the petri dish.
 */

import java.util.*;

/**
 * This class derives from cell. It has two constructors and overrides the 
 * toString and checkApoptosis methods.
 */
public class CellMoveUp extends Cell implements Movable
{
    /**constants*/
    private static final int APOP_CONDITION = 4;
    
    /**
     * This constructor will call the parent class' constructor to initialize
     * the instance variables
     * 
     * @param currRow, currCol, mass
     */
    public CellMoveUp(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
    }
    
    /**
     * This copy constructor will call the parent class' copy constructor
     * to initialize the instance variables
     */
    public CellMoveUp(CellMoveUp otherCellMoveUp)
    {
        super(otherCellMoveUp);
    }
    
    /**
     * This method will return the string representation of the current object.
     * 
     * @return ^
     */
    public String toString()
    {
        return "^";
    }
    
    /**
     * This method checks if the cell should initiate apoptosis or not by 
     * determining the number of neighbors the cell has.
     * 
     * @param cell list neighbors
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        if(neighbors.size()!=APOP_CONDITION)
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
        return new CellMoveUp(this);
    }
    
    /**
     * Defines how this cell can move. Returns an array of length 2 that
     * indicates new position. This cell moves up.
     * 
     * @return array of length 2 that indicates new position
     */
    public int[] getMove()
    {
        int moveTo[] = new int[]{currRow+1, currCol};   //go up a row
        
        return moveTo;
    }
}