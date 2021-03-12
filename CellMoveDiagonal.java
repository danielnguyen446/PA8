/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Stephen M Boussarov)
 * 
 * This file contains the CellMoveDiagonal subclass of CellMoveUp. This
 * concrete subclass is denoted by "/"  or "\" on the petri dish. 
 */

import java.util.*;

/**
 * This class derives from CellMoveUp. It has two constructors and overrides the 
 * toString and checkApoptosis methods.
 */
public class CellMoveDiagonal extends CellMoveUp 
{
    /**Constants*/
    private static final int APOP3 = 3;
    private static final int MOVE_TO_SIZE = 2;
    private static final int NUM_MOVE_COUNT = 4;
    
    /** instance variables*/
    public boolean orientedRight;   //true is right orientation, false is left
    public int diagonalMoves;    //count the number of moves made
    
    /**
     * This constructor will call the parent class' constructor to initialize
     * the instance variables
     * 
     * @param currRow, currCol, mass
     */
    public CellMoveDiagonal(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
        orientedRight = true;   //orientedRight should be defaulted to true
        diagonalMoves = 0;  //diagonalMoves should be defaulted to 0
    }
    
    /**
     * This copy constructor will call the parent class' copy constructor
     * to initialize the instance variables
     * 
     * @param otherCellMoveDiagonal
     */
    public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal)
    {
        super(otherCellMoveDiagonal);
        orientedRight = otherCellMoveDiagonal.orientedRight;
        diagonalMoves = otherCellMoveDiagonal.diagonalMoves;
    }
    
    /**
     * This method will return the string representation of the current object.
     * 
     * @return / if oriented right. \ if oriented left
     */
    public String toString()
    {
        if (orientedRight == true)
        {
            return "/";
        }
        else
        {
            return "\\";
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
        if(neighbors.size()>APOP3)
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
        return new CellMoveDiagonal(this);
    }
    
    /**
     * Defines how this cell can move. Returns an array of length 2 that
     * indicates new position. Movement depends orientation
     * 
     * @return array of length 2 that indicates new position
     */
    public int[] getMove()
    {
        int[] moveTo = new int[MOVE_TO_SIZE];
        
        if(orientedRight == true)
        {
            //move up and right
            moveTo[0] = currRow + 1;
            moveTo[1] = currCol + 1;
        }
        else
        {
            //move up and left
            moveTo[0] = currRow + 1;
            moveTo[1] = currCol - 1;
        }
        
        //increment diagonalMoves
        diagonalMoves = diagonalMoves + 1;
        
        //if number of moves is multiple of 4, flip orientation
        if(diagonalMoves%NUM_MOVE_COUNT == 0)
        {
            if(orientedRight == true)
            {
                orientedRight = false;
            }
            else
            {
                orientedRight = true;
            }
        }
        
        return moveTo;
    }
}