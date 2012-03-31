package org.processmanagement.processes;

import java.util.*;
import java.lang.Math;

public class PRandom {

	/**
	 * Generates a random integer in range between min and max
	 * 
	 * @param min lower limit
	 * @param max upper limit
	 * @return the random integer
	 */
	public int randomInt(int min, int max)
	{
		//new random object
		Random rand = new Random();
		//generate a random number between min and max both being
		//inclusive
		int randomNum = rand.nextInt(max - min + 1) + min;
		
		return randomNum;
		
	}
	
	/**
	 * Generates a random float value rounded to the nearest 10th
	 * 
	 * @param min float value
	 * @param max float value
	 * @return rounded float number
	 */
	
	
	
}
