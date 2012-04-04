package org.processmanagement.processes;

import java.util.*;

public class ReadyQueue {
	
	final int MAX_PROCESS_NUMBER = 100;
	
	ArrayList<Process> processList = new ArrayList<Process>();
	PRandom rand = new PRandom();
	
	/**
	 * 
	 * @return Returns the processList object used for storing processes
	 */
	public ArrayList<Process> getQueue(){
		return processList;
	}
	public void setQueue(ArrayList<Process> queue){
		this.processList = queue;
	}
	/**
	 * Generates a random process object. Using the PRandom class, all of the data in the process is randomly generated.
	 * 
	 * @return the randomly generated process
	 */
	private Process randomProcess(){
			
		//set random data
		int burst = rand.randomInt(0, 100);
		int priority = rand.randomInt(0, 5);
		int arrivalTime = rand.randomInt(0, 10);
		
		//generate a new process
		Process proc = new Process(burst, arrivalTime, priority);
		
		//return the process
		return proc;
				
	}
	
	/**
	 * Populates a processList with a random number, between 1 and MAX_PROCESS_NUMBER, of processes
	 */
	public void populateQueue(){
		
		//create a random num between 1 and MAX_PROCESS_NUMBER
		int processNum = rand.randomInt(1, MAX_PROCESS_NUMBER);
		
		//populate a list with the generated number of processes
		for(int i = 0; i <= processNum; i++){
			processList.add(randomProcess());
		}		
		
	}
	
}
