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
		Collections.sort(readyQueue, new Comparator<Process>() {
			public int compare(Process process1, Process process2) {
				 return process1.getArrivalTime() - process2.getArrivalTime();
			}
		});
	}
	
	/**
	 * Method that starts the round robin scheduler simulation.
	 */			
	public void start(){
		for(Process p:pList){
			readyQueue.add(p);
			totalBurst += p.getBurst();
		}
		size = pList.size();
		printProcesses();
		sortQueue();
		
		System.out.println("Running Processes...");
		rr();
		//step through the queue and process each job for the specified time slice
		printStats();
	}
	public void rr(){
		int elapsedBurst = 0;
		
		// this process will continue to loop until all of the process have been
		// completed
		while (!readyQueue.isEmpty()) {
			boolean idle = true;
			for (int i = 0; i < readyQueue.size(); i++) {
				// initialize the current process
				Process p = readyQueue.get(i);
				if (p.getArrivalTime() <= elapsedBurst) {
					idle = false;
					System.out.print(elapsedBurst + "---[" + p.getName()
							+ "]---");
					if (p.getRemainingBurst() > quantum) {// the job will not be
															// finished on
						// this pass
						elapsedBurst += quantum;
						p.decRemainingBurst(quantum);
						p.setWaitTime(elapsedBurst - p.getElapsedBurst());

					} else {
						// the job has completely finished

						// updates the current elapsed burst time
						elapsedBurst += p.getRemainingBurst();
						// sets the remaining burst for the process to 0
						p.setRemainingBurst(0);
						// update the wait time for the process
						p.setWaitTime(elapsedBurst - p.getElapsedBurst());
						// updates the total wait time for the scheduler
						totalWait += p.getWaitTime();
						// updates the total completion time for the scheduler
						totalComp += elapsedBurst;
						// removes the process from the queue
						readyQueue.remove(i);
						i--;
					}
				}
			}
			if(idle){
				System.out.print(elapsedBurst + "---[IDLE]---");
				elapsedBurst = getNextArrivalTime();
			}
		}
		System.out.println(elapsedBurst);
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

}
