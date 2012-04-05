package org.processmanagement.scheduling;

import org.processmanagement.processes.Process;
import org.processmanagement.processes.ReadyQueue;

/**
 * General scheduler class that is used as a baseline for the other scheduling methods * 
 *
 */
public class Scheduler {
	
	public ReadyQueue queue;
	int totalBurst = 0;
	int totalWait = 0;
	int totalComp = 0;
	
	public Scheduler(){
		queue = new ReadyQueue();
	}
	/**
	 * generate random processes for the queue
	 */
	public void genProcesses(){
		queue.populateQueue();
		System.out.println(queue.getQueue().size() + " processes have been generated!"+'\n');
	}
	/**
	 * Print out various stats calculated by the simulation.
	 */
	public void printStats(){
		for(int i = 0;i<queue.getQueue().size();i++){
			Process curProcess = queue.getQueue().get(i);
			System.out.println("P"+(i+1)+": ");
			System.out.println("	Arrival Time = "+curProcess.getArrivalTime());
			System.out.println("	Burst Time = "+curProcess.getBurst());
			System.out.println("	Wait Time = "+curProcess.getWaitTime());
			System.out.println("	Completion Time = "+curProcess.getCompletionTime());
		}
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("Total Burst Time: " + totalBurst);
		System.out.println("Average Burst Time: " + (totalBurst/queue.getQueue().size()));
		System.out.println("Average Wait Time: " + (totalWait/queue.getQueue().size()));
		System.out.println("Average Completion Time: " + (totalComp/queue.getQueue().size()));
	}

}
