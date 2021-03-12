/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: none
 * 
 * This file contains the Movable interface. The method contained will be 
 * overridden by each cell class depending on behavior
 */
 
import java.util.*;

/**
 * This class stores the getMove abstract method. Each movable cell will
 * override the getMove class depending on its individual behavior.
 */
public interface Movable 
{
    /**
     * This is an abstract method.
     */
    public abstract int[] getMove();
}
