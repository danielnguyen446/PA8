/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Manshi Yang, Joona Kim)
 * 
 * This file contains the class to store the information of each cell.
 * Regardless of the type of cell, all cells have a current location and mass.
 */

import java.util.*;

/**
 * This class stores the information of each cell. It initializes variables and
 * handles the status of the cell, which includes death and movement.
 */
public abstract class Cell implements Comparable <Cell> 
{
    /**instance variables*/
    public int currRow; //row value of the cell
    public int currCol; //column value of the cell
    public int mass;    //mass of the cell

    /**
     * This constructor initializes all instance variables
     * Sets all negative values to zero
     * 
     * @param currRow, currCol, mass
     */
    public Cell(int currRow, int currCol, int mass)
    {
        if(currRow<=0)  //validity check before initializing
        {
            this.currRow = currRow;
        }
        else
        {
            this.currRow = 0;
        }
        
        if(currCol<=0)  //validity check before initializing
        {
            this.currCol = currCol;
        }
        else
        {
            this.currCol = 0;
        }
        
        if(mass<=0) //validity check before initializing
        {
            this.mass = mass;
        }
        else
        {
            this.mass = 0;
        }
    }
    
    /**
     * This copy constructor initializes all instance variables by
     * using the getter methods.
     * 
     * @param otherCell
     */
    public Cell(Cell otherCell)
    {
        currRow = otherCell.getCurrRow();
        currCol = otherCell.getCurrCol();
        mass = otherCell.getMass();
    }
    
    /**
     * This method handles cell death. Gets called when apoptosis happens.
     */
    public void apoptosis()
    {
        this.currRow = -1;
        this.currCol = -1;
        this.mass = -1;
    }
    
    /**
     * This method returns the value of currRow
     * 
     * @return currRow
     */
    public int getCurrRow()
    {
        return this.currRow;
    }
    
    /**
     * This method returns the value of currCol
     * 
     * @return currCol
     */
    public int getCurrCol()
    {
        return this.currCol;
    }
    
    /**
     * This method returns the value of mass
     * 
     * @return mass
     */
    public int getMass()
    {
        return this.mass;
    }
    
    /**
     * This setter class is a mutator that sets current row to the new row
     * 
     * @param newRow
     * @return true if successfully set
     */
    public boolean setCurrRow(int newRow)
    {
        if(newRow>=0)   //check validity before returning true
        {
            this.currRow = newRow;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * This setter class is a mutator that sets current column to new column
     * 
     * @param newCol
     * @return true if successfully set
     */
    public boolean setCurrCol(int newCol)
    {
        if(newCol>=0)   //check validity before returning true
        {
            this.currCol = newCol;
            return true;
        }    
        else
        {
            return false;
        }
    }
    
    /**
     * This setter class is a mutator that sets current mass to new mass
     * 
     * @param newMass
     * @return true if successfully set
     */
    public boolean setMass(int newMass)
    {
        if(newMass>=0)   //check validity before returning true
        {
            this.mass = newMass;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * This method checks if the cell should initiate apoptosis or not.
     * 
     * @param cell list neighbors
     */
    public abstract boolean checkApoptosis(List<Cell> neighbors);
    
    /**
     * Determines which cell has larger mass
     * 
     * @param otherCell
     * @return whether this cell has larger mass than otherCell
     */
    public int compareTo(Cell otherCell)
    {
        
        if(this.mass>otherCell.mass)
        {
            //this cell has greater mass than otherCell
            return 1;
        }
        else if(this.mass==otherCell.mass)
        {
            //equal mass
            return 0;
        }
        else
        {
            //this cell has less mass than otherCell
            return -1;
        }
    }
    
    /**
     * This method will return a deep copy of the calling object.
     */
    public abstract Cell newCellCopy();
}
