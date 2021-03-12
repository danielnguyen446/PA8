/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Stephen M Boussarov, Joona Kim)
 * 
 * This file contains the CellStationary subclass of cell. This
 * concrete subclass is denoted by "." on the petri dish.
 */

import java.util.*;

/**
 * This class derives from cell. It has two constructors and overrides the 
 * toString and checkApoptosis methods. 
 */
public class CellStationary extends Cell
{
    /**constants*/
    private static final int APOP3 = 3;
    private static final int APOP7 = 7;

    
    /**
     * This constructor will call the parent class' constructor to initialize
     * the instance variables
     * 
     * @param currRow, currCol, mass
     */
    public CellStationary(int currRow, int currCol, int mass)
    {
        super(currRow, currCol, mass);
    }
    
    /**
     * This copy constructor will call the parent class' copy constructor
     * to initialize the instance variables
     * 
     * @param otherCellStationary
     */
    public CellStationary(CellStationary otherCellStationary)
    {
        super(otherCellStationary);
    }
    
    /**
     * This method will return the string representation of the current object.
     * 
     * @return .
     */
    public String toString()
    {
        return ".";
    }
    
    /**
     * This method checks if the cell should initiate apoptosis or not by 
     * determining the number of neighbors the cell has.
     * 
     * @param Cell list neighbors
     * @return whether the conditions are met
     */
    public boolean checkApoptosis(List<Cell> neighbors)
    {
        if(neighbors.size()<=APOP7 && neighbors.size()>=APOP3)
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
        return new CellStationary(this);
    }
}