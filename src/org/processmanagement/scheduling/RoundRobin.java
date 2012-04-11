package org.processmanagement.scheduling;

import java.util.Collections;
import java.util.Comparator;

import org.processmanagement.processes.Process;

public class RoundRobin extends Scheduler {
	
	private int quantum;
	
	public RoundRobin(int quantum){
		this.setQuantum(quantum);
	}
	
	private void sortQueue(){
		//java sort method with a comparator class to compare arrival times
		Collections.sort(queue.getQueue(), new Comparator<Process>() {
			public int compare(Process process1, Process process2) {
				 return process1.getArrivalTime() - process2.getArrivalTime();
			}
		});
	}
	
	/**
	 * Method that starts the round robin scheduler simulation.
	 */			
	public void start(){
		
		sortQueue();
		
		System.out.println("Running Processes...");
		//step through the queue and process each job for the specified time slice
		int curBurst = 0;
		
		//gets the sum of all the bursts in the ready queue
		for(Process p: queue.getQueue()){
			totalBurst += p.getBurst();
		}
		//this process will continue to loop until all of the process have been completed
		
		while (!queue.getQueue().isEmpty()) {
			for (int i = 0; i < queue.getQueue().size(); i++) {
				// initialize the current process
				Process p = queue.get(i);
				if (p.getRemainingBurst() > quantum) {// the job will not be finished on
												// this pass
					curBurst += quantum;
					p.decRemainingBurst(quantum);
					p.setWaitTime(curBurst - p.getElapsedBurst());

				} else {
					// the job has completely finished
					
					//updates the current elapsed burst time
					curBurst += p.getRemainingBurst();
					//sets the remaining burst for the process to 0
					p.setRemainingBurst(0);
					//update the wait time for the process
					p.setWaitTime(curBurst - p.getElapsedBurst());
					//updates the total wait time for the scheduler
					totalWait += p.getWaitTime();
					//updates the total completion time for the scheduler
					totalComp += curBurst;
					//removes the process from the queue
					queue.getQueue().remove(i);
					i--;
				}

			}
		}

	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

}
