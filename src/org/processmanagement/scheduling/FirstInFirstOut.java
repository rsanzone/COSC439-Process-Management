package org.processmanagement.scheduling;


import java.util.Collections;
import java.util.Comparator;

import org.processmanagement.processes.Process;

public class FirstInFirstOut extends Scheduler {
	
	/**
	 * Sorting class to sort queue by arrival time
	 */
	private void sortQueue(){
		//java sort method with a comparator class to compare arrival times
		Collections.sort(readyQueue, new Comparator<Process>() {
			public int compare(Process process1, Process process2) {
				 return process1.getArrivalTime() - process2.getArrivalTime();
			}
		});
	}
	/**
	 * Method that starts the first in first out scheduler simulation.
	 */			
	public void start(){
		for(Process p:pList){
			readyQueue.add(p.deepCopy());
			totalBurst += p.getBurst();
		}
		printProcesses();
		size = pList.size();
		sortQueue();
		System.out.println("Running Processes...");
		//step through the queue and simulate the burst time for each process
		fifo();
		printStats();
		
	}
	public void fifo(){
		int elapsedBurst = 0;
		for(Process curProcess : readyQueue){
			if(curProcess.getArrivalTime()>elapsedBurst){
				System.out.print(elapsedBurst + "---[IDLE]---");
				elapsedBurst = curProcess.getArrivalTime();
			}
			System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");
			//set wait time
			curProcess.setWaitTime(elapsedBurst - curProcess.getArrivalTime());
			//update the elapsed
			elapsedBurst += curProcess.getBurst();
			//set completion time when process is done running
			curProcess.setCompletionTime(elapsedBurst - curProcess.getArrivalTime());
			
			totalWait += curProcess.getWaitTime();
			totalComp += curProcess.getCompletionTime();
		}
		System.out.print(elapsedBurst);
	}
}
