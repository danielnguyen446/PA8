/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Ashley Kou, Emma Yuan, Joona Kim,
 * Stephen M Boussarov)
 * 
 * This file contains the class to display the petri dish that contains
 * all of the cells in the current iteration. Each space on the dish can 
 * contain either a living cell or an empty space.
 */

import java.util.*;

/**
 * This class contains methods to initialize and create the structure of the 
 * petri dish.
 */
public class PetriDish
{
    /** Constants */
    private static final int locArSize = 2;
    
    /** Instance variables*/
    public Cell[][] dish;   //represents the current iteration
    public List<Movable> movables;  //list of all movable cells
    public List<Divisible> divisibles;  //list of all divisible cells
    
    /**
     * This constructor initializes the board.
     */
    public PetriDish(String[][] board)
    {
        //iterate through the 2d array of strings by row and column
        for(int row=0; row<board.length; row++)
        {
            for(int column=0; column<board[row].length; column++)
            {
                //create a temporary string array called petri
                String[] petri = board[row][column].trim().split(" ");
                //petri will hold type and mass
                
                if(petri.length==1)
                {
                    //fixes null
                    continue;
                }
                
                String CELL_TYPE = petri[0];
                int MASS = Integer.parseInt(petri[1]);
                
                //initialize the dish
                dish = new Cell[row][column];
                
                //create the dish depending on the class/type of cells
                if (CELL_TYPE.equals("CellMoveUp"))
                {
                    //CellMoveUp
                    dish[row][column] = new CellMoveUp(row, column, MASS);
                }
                else if (CELL_TYPE.equals("CellStationary"))
                {
                    //CellStationary
                    dish[row][column] = new CellStationary(row, column, MASS);
                }
                else if (CELL_TYPE.equals("CellDivide"))
                {
                    //CellDivide
                    dish[row][column] = new CellDivide(row, column, MASS);
                }
                else if (CELL_TYPE.equals("CellMoveToggle"))
                {
                    //CellMoveToggle
                    dish[row][column] = new CellMoveToggle(row, column, MASS);
                }
                else if (CELL_TYPE.equals("CellMoveDiagonal"))
                {
                    //CellMoveDiagonal
                    dish[row][column] = new CellMoveDiagonal(row, 
                    column, MASS);
                }
                else if (CELL_TYPE.equals("CellMoveToggleChild"))
                {
                    //CellMoveToggleChild
                    dish[row][column] = new CellMoveToggleChild(row, 
                    column, MASS);
                }
            }
        }
    }
    
    /**
     * Make a list of cells neighboring the input location
     * 
     * @param row, column
     * @return Cell List
     */
    public List<Cell> getNeighborsOf(int row, int col)
    {
        //declare the list
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        
        //if row or col out of bounds, return null
        if(row>dish.length || row<0)
        {
            //row is out of bounds
            return null;
        }
        if(col>dish[row].length || col<0)
        {
            //col is out of bounds
            return null;
        }
        
        //check above
        if(dish[row+1][col]!=null)
        {
            neighbors.add(dish[row+1][col]);
        }
        //check below
        if(dish[row-1][col]!=null)
        {
            neighbors.add(dish[row-1][col]);
        }
        //check right
        if(dish[row][col+1]!=null)
        {
            neighbors.add(dish[row][col+1]);
        }
        //check left
        if(dish[row][col-1]!=null)
        {
            neighbors.add(dish[row][col-1]);
        }
        //check above and right
        if(dish[row+1][col+1]!=null)
        {
            neighbors.add(dish[row+1][col+1]);
        }
        //check below and right
        if(dish[row-1][col+1]!=null)
        {
            neighbors.add(dish[row-1][col+1]);
        }
        //check above and left
        if(dish[row+1][col-1]!=null)
        {
            neighbors.add(dish[row+1][col-1]);
        }
        //check below and left
        if(dish[row-1][col-1]!=null)
        {
            neighbors.add(dish[row-1][col-1]);
        }
        
        return neighbors;
    }
    
    /**
     * Move all movable cells
     */
    public void move()
    {
        int [] location;   //array to hold location
        
        Cell[][] newDish = new Cell[dish.length][dish[0].length];
        
        for(int i=0; i<movables.size(); i++)
        {
            Cell movingCell = (Cell) movables.get(i); //create a cell object
            
            location = movingCell.getMove(); //call getMove
            
            //account for wrapping row
            if(location[0]<0)
            {
                location[0] = dish.length-1;
            }
            if(location[0]==dish.length)
            {
                location[0] = 0;
            }
            
            //account for wrapping column
            if(location[1]<0)
            {
                location[1] = dish[i].length-1;
            }
            if(location[1]==dish[i].length)
            {
                location[1] = 0;
            }
            
            //If Movable cells move into non-Movable cells, non-Movable dies
            if(!(dish[location[0]][location[1]] instanceof Movable))
            {
                //call apoptosis on the old cell
                dish[location[0]][location[1]].apoptosis();
                
                //put the movable cell in
                newDish[location[0]][location[1]] = movingCell;
                
                //sets new location
                movingCell.setCurrRow(location[0]);
                movingCell.setCurrCol(location[1]);
            }
            
            //If multiple movable cells move onto each other,
            if(dish[location[0]][location[1]] instanceof Movable)
            {
                //the largest movable cell stays
                
                //if the new one is greater, 
                if(movingCell.compareTo(dish[location[0]][location[1]]) > 0)
                {
                    //call apoptosis on the old cell
                    dish[location[0]][location[1]].apoptosis();
                    
                    //put the movable cell in
                    newDish[location[0]][location[1]] = movingCell;
                }
                //If there is a tie in the largest masses
                else if(movingCell.compareTo(dish[location[0]][location[1]]) 
                == 0)
                {
                    //all the cells at that position die.
                    
                    //call apoptosis on both
                    movingCell.apoptosis();
                    dish[location[0]][location[1]].apoptosis();
                }
                else    //if the new one is less massive
                {
                    //call apoptosis on the new one
                    movingCell.apoptosis(); 
                    //keep the old one
                    newDish[location[0]][location[1]] = 
                    dish[location[0]][location[1]];
                }
            }
        }
        
        //edit the dish using the new dish
        for(int row=0; row<dish.length;row++)
        {
            for(int col=0; col<dish[row].length;col++)
            {
                dish[row][col] = newDish[row][col];
            }
        }
    }
    
    /**
     * Divide all divisible cells
     */
    public void divide()
    {
       	int [] location;   //array to hold location
       	int [] ogLocation;  //remember old cell's location
        
        Cell[][] newDish = new Cell[dish.length][dish[0].length];
        
        for(int i=0; i<divisibles.size(); i++)
        {
            Cell ogCell =(Cell) divisibles.get(i);  //remember the old cell
            Cell dividingCell = (Cell) divisibles.get(i); //create a cell
            
            location = dividingCell.getDivision(); //call getDivision
            
            //remember the original cell's location
            ogLocation[0] = ogCell.currRow;
            ogLocation[1] = ogCell.currRow;
            
            //account for wrapping row
            if(location[0]<0)
            {
                location[0] = dish.length-1;
            }
            if(location[0]==dish.length)
            {
                location[0] = 0;
            }
            
            //account for wrapping column
            if(location[1]<0)
            {
                location[1] = dish[i].length-1;
            }
            if(location[1]==dish[i].length)
            {
                location[1] = 0;
            }
            
            //divide into empty cell. Otherwise don’t divide.
            if((dish[location[0]][location[1]] == null))
            {
                //put the copy cell in
                newDish[location[0]][location[1]] = dividingCell;
                
                //sets new location
                dividingCell.setCurrRow(location[0]);
                dividingCell.setCurrCol(location[1]);
            }
            
            //If multiple Divisible cells on same position after divided 
                //Divisible cell with the largest mass stay
                //other cells at that position die 
                //and call their apoptosis() methods
                
                //If tie in the largest masses, all cells at that position die.
                //and call their apoptosis methods
        }
        
        //edit the dish using the new dish
        for(int row=0; row<dish.length;row++)
        {
            for(int col=0; col<dish[row].length;col++)
            {
                dish[row][col] = newDish[row][col];
            }
        }
    }
    
    /**
     * Spawn and despawn cells at the same time.
     */
    public void update()
    {
        //set dead cell locations to null
        
        //if there are 2 or 3 spaces around an empty cell, 
            //spawn a deep copy of the first cell from getNeighborsOf()
        
    }
  
    /**
    * This method handles each iteration of the dish. Move, then divide, then update.
    */  
    public void iterate()
    {
        move();
        divide();
        update();
    }

}