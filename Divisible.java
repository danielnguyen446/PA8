/** 
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: none
 * 
 * This file contains the Divisible interface. The method contained will be 
 * overridden by each cell class depending on behavior
 */
 
import java.util.*;

/**
 * This class stores the getDivision abstract method. Each divisible cell will
 * override the getDivision class depending on its individual behavior.
 */
public interface Divisible 
{
    public abstract int[] getDivision();
}
