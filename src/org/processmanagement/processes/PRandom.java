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
	public float randomFloat(float min, float max)
	{
		Random rand = new Random();
		
		//create a random float between min and max
		float randomNum = rand.nextFloat() * (max - min) + min;
		//round the random number to the 10th decimal place
		float roundedNum = (float)Math.round(randomNum * 10) / 10;
		
		return roundedNum;
	}
	
	
}
