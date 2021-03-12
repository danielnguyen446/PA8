/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Stephen M Boussarov)
 * 
 * This file contains the CellDivide subclass of cell. This
 * concrete subclass is denoted by "+" on the petri dish.
 */

import java.util.*;

/**
 * This class derives from cell. It has two constructors and overrides the 
 * toString and checkApoptosis methods.
 */
public class CellDivide extends Cell implements Divisible
{
    /**constants*/
    private static final int APOP_CONDITION = 3;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int DIVIDE_TO_SIZE = 2;

    /**instance variable*/
    public int direction;		//which direction the cell will divide into

    /**
     * This constructor will call the parent class' constructor to initialize
     * the instance variables
     * 
     * @param currRow, currCol, mass
     */
    public CellDivide (int currRow, int currCol, int mass)
    {
        super (currRow, currCol, mass);
        direction = 1;		//direction should be defaulted to 1
    }

    /**
     * This copy constructor will call the parent class' copy constructor
     * to initialize the instance variables
     * 
     * @param otherCellDivide
     */
    public CellDivide (CellDivide otherCellDivide)
    {
        super (otherCellDivide);
        direction = otherCellDivide.direction;
    }

    /**
     * This method will return the string representation of the current object.
     * 
     * @return +
     */
    public String toString ()
    {
        return "+";
    }

    /**
     * This method checks if the cell should initiate apoptosis or not by 
     * determining the number of neighbors the cell has.
     * 
     * @param cell list neighbors
     * @return whether condition met
     */
    public boolean checkApoptosis (List <Cell> neighbors)
    {
        if (neighbors.size () == APOP_CONDITION)
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
    public Cell newCellCopy ()
    {
        return new CellDivide (this);
    }

    /**
     * Defines where a location the new cell will be divided into. 
     * Depends on direction the cell is facing.
     * 
     * @return array of length 2 that indicates new position
     */
    public int[] getDivision ()
    {
        int[] divideTo = new int[DIVIDE_TO_SIZE];
        
        if (direction == 0)
        {
            //0 is down
            divideTo[0] = currRow - 1;
            divideTo[1] = currCol;
        }
        else if (direction == 1)
        {
            //1 is up
            divideTo[0] = currRow + 1;
            divideTo[1] = currCol;
            
        }
        else if (direction == LEFT)
        {
            //2 is left
            divideTo[0] = currRow;
            divideTo[1] = currCol - 1;
        }
        else if (direction == RIGHT)
        {
            //3 is right
            divideTo[0] = currRow;
            divideTo[1] = currCol + 1;
        }
        
        //cycle through the directions
        if (direction == RIGHT)
        {
            //if direction was right, now point down
            direction = 0;
        }
        else
        {
            //cycle through
            direction = direction + 1;
        }
        return divideTo;
    }
}
