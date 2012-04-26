package org.processmanagement.processes;

import java.util.*;
//import java.lang.Math;

public class PRandomComplex {
	final int MAX_PROCESS_NUMBER = 200;
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

	public ArrayList<ProcessComplex> genProcesses() {
		// create a random num between 1 and MAX_PROCESS_NUMBER
		System.out.println("How many processes should be generated?: ");
		Scanner input = new Scanner(System.in);
		int processNum = input.nextInt();
		// int processNum = randomInt(1, MAX_PROCESS_NUMBER);
		ArrayList<ProcessComplex> processList = new ArrayList<ProcessComplex>();
		// populate a list with the generated number of processes
		for (int i = 1; i <= processNum; i++) {
			processList.add(randomProcess(i));
		}
		System.out.println(processNum+" processes have been generated!");
		return processList;
	}
	public ArrayList<ProcessComplex> genProcesses_randNum() {
		//create a random num between 1 and MAX_PROCESS_NUMBER
		int processNum = randomInt(1, MAX_PROCESS_NUMBER);
		ArrayList<ProcessComplex> processList = new ArrayList<ProcessComplex>();
		// populate a list with the generated number of processes
		for (int i = 1; i <= processNum; i++) {
			processList.add(randomProcess(i));
		}
		System.out.println(processNum+" processes have been generated!");
		return processList;
	}
	private ProcessComplex randomProcess(int count){
		//set random data
		int arrivalTime = randomInt(0, 10);
		String name = "P"+count;
		
		int numSeg = randomInt(1, 10);
		ArrayList<Integer> burst = new ArrayList<Integer>();
		for(int i = 0;i<numSeg;i++){
			burst.add(randomInt(1,25));
		}
		ArrayList<Integer> ioTime = new ArrayList<Integer>();
		//there will be one less ioTime than burstTime
		for(int i = 1;i<numSeg;i++){
			ioTime.add(randomInt(1,5));
		}
		
		//generate new process
		ProcessComplex proc = new ProcessComplex(burst,arrivalTime,ioTime,name);
		
		//return the process
		return proc;
	}
}
