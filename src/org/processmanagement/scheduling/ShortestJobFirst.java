package org.processmanagement.scheduling;

import java.util.Collections;
import java.util.Comparator;

import org.processmanagement.processes.Process;

public class ShortestJobFirst extends Scheduler{

	/**
	 * Sorting class to sort queue by burst time
	 */
	private void sortQueue(){
		//java sort method with a comparator class to compare arrival times
		Collections.sort(queue.getQueue(), new Comparator<Process>() {
			public int compare(Process process1, Process process2) {
				 return process1.getBurst() - process2.getBurst();
			}
		});
	}
	
	/**
	 * Method that starts the shortest job first scheduler simulation.
	 */			
	public void start(){
		
		sortQueue();
		
		System.out.println("Running Processes...");
		//step through the queue and simulate the burt time for each process
		for(Process curProcess : super.queue.getQueue()){
			//set the burst for the current process
			int curBurst = curProcess.getBurst();
			//set wait time
			curProcess.setWaitTime(totalBurst);
			//system time used in the simulation of burst time.
			
			/*
			long initTime = System.currentTimeMillis();
			 
			long curTime = System.currentTimeMillis();
			
			//loop until a certian amount of time elapses
			while((curTime - initTime) <= (long)curBurst){
				//update time
				curTime = System.currentTimeMillis();
			}*/
			
			//update total burst time
			totalBurst += curBurst;
			//set completion time when process is done running
			curProcess.setCompletionTime(totalBurst);
			
			totalWait += curProcess.getWaitTime();
			totalComp += curProcess.getCompletionTime();
			
		}
		
	}
}
