package org.processmanagement.scheduling;

import java.util.ArrayList;

import org.processmanagement.processes.PRandom;
import org.processmanagement.processes.Process;

/**
 * General scheduler class that is used as a baseline for the other scheduling methods * 
 *
 */
public class Scheduler {
	
	//processes will be taken from the process list and put into the ReadyQueue;
	ArrayList<Process> pList;
	ArrayList<Process> readyQueue = new ArrayList<Process>();
	PRandom rand = new PRandom();
	
	int totalBurst = 0;
	int totalWait = 0;
	int totalComp = 0;
	
	public Scheduler(){
		pList = new ArrayList<Process>();
		readyQueue = new ArrayList<Process>();
	}
	/**
	 * generate random processes for the queue
	 */
	public void genProcesses(){
		pList = rand.genProcesses();
		System.out.println(readyQueue.size() + " processes have been generated!"+'\n');
		
		//later on processes will only be added at the appropriate arrival time
		for(Process p:pList){
			readyQueue.add(p);
		}
		
	}
	/**
	 * Print out various stats calculated by the simulation.
	 */
	public void printStats(){
		for(int i = 0;i<pList.size();i++){
			Process curProcess = pList.get(i);
			System.out.println("P"+(i+1)+": ");
			System.out.println("	Arrival Time = "+curProcess.getArrivalTime());
			System.out.println("	Burst Time = "+curProcess.getBurst());
			System.out.println("	Wait Time = "+curProcess.getWaitTime());
			System.out.println("	Completion Time = "+curProcess.getCompletionTime());
		}
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("Total Burst Time: " + totalBurst);
		System.out.println("Average Burst Time: " + (totalBurst/pList.size()));
		System.out.println("Average Wait Time: " + (totalWait/pList.size()));
		System.out.println("Average Completion Time: " + (totalComp/pList.size()));
	}
	public ArrayList<Process> getReadyQueue(){
		return this.readyQueue;
	}
	public void setReadyQueue(ArrayList<Process> queue){
		this.readyQueue = queue;
	}
	public ArrayList<Process> getPList(){
		return this.pList;
	}
	public void setPList(ArrayList<Process> queue){
		this.pList = queue;
	}

}
