package org.processmanagement.processes;

import java.util.*;

public class ReadyQueue {
	
	final int MAX_PROCESS_NUMBER = 10;
	
	ArrayList<Process> processList = new ArrayList<Process>();
	PRandom rand = new PRandom();
	
	public ArrayList<Process> getQueue(){
		return processList;
	}
	
	public Process randomProcess(){
			
		float burst = rand.randomFloat(0, 100);
		int priority = rand.randomInt(0, 5);
		float arrivalTime = rand.randomFloat(0, 10);
		
		Process proc = new Process(burst, arrivalTime, priority);
		
		return proc;
				
	}
	
	public void populateQueue(){
		
		int processNum = rand.randomInt(1, MAX_PROCESS_NUMBER);
		
		for(int i = 0; i <= processNum; i++){
			processList.add(randomProcess());
		}		
		
	}
	
}
