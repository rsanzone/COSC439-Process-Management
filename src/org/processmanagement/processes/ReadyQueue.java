package org.processmanagement.processes;

import java.util.*;

public class ReadyQueue {
	
	final int MAX_PROCESS_NUMBER = 100;
	
	ArrayList<Process> processList = new ArrayList<Process>();
	PRandom rand = new PRandom();
	private int numOfProcesses;
	
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
	public int size(){
		return processList.size();
	}
	public Process get(int index){
		return processList.get(index);
	}
	public ReadyQueue deepCopy(){
		ReadyQueue copy = new ReadyQueue();
		for(Process p: this.processList){
			copy.processList.add(p);
		}
		copy.setNumOfProcesses(this.numOfProcesses);
		return copy;
	}
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
	private Process randomIOProcess(){
		//set random data
		//(int burst, int arrivalTime, int priority, int burstBeforeIO, int ioTime)
		int burst = rand.randomInt(0, 100);
		int priority = rand.randomInt(0, 5);
		int arrivalTime = rand.randomInt(0, 10);
		int burstSegment = rand.randomInt(1, burst/5);//can't be larger than 1/5 the total burst
		int ioTime = rand.randomInt(1,5);//small maximum io time seems realistic
		int ioSegment = rand.randomInt(1, ioTime/5);//can't be larger than 1/5 the total IO time
		
		//generate new process
		Process proc = new Process(burst,arrivalTime,priority,burstSegment,ioTime,ioSegment);
		
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
		setNumOfProcesses(processList.size());
		
	}
	public int getNumOfProcesses() {
		return numOfProcesses;
	}
	public void setNumOfProcesses(int numOfProcesses) {
		this.numOfProcesses = numOfProcesses;
	}
	
}
