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
	public String start(){
		for(Process p:pList){
			readyQueue.add(p.deepCopy());
			for(int b : p.getBurst()){
				totalBurst += b;
			}
		}
		size = pList.size();
		printProcesses();
		sortQueue();
		System.out.println("Running Processes...");
		String finalStr=rr();
                return finalStr;
		//step through the queue and process each job for the specified time slice
	}
	public String rr(){
		int elapsedBurst = 0;
                String result = "";
		// this process will continue to loop until all of the process have been
		// completed
		while (!readyQueue.isEmpty()) {
			boolean idle = true;
			for (int i = 0; i < readyQueue.size(); i++) {
				// initialize the current process
				Process p = readyQueue.get(i);
				if (p.getArrivalTime() <= elapsedBurst) {
					idle = false;
					result+=(elapsedBurst + "---[" + p.getName()
							+ "]---");
				
					if(p.getCurBurst() <= quantum || readyQueue.size() == 1) {//current burst segment will finish or there is only one process left
						
						// update the wait time for the process
						int curWait = p.getWaitTime() + (elapsedBurst - p.getArrivalTime());
						p.setWaitTime(curWait);//update total wait time for process
						
						// updates the current elapsed burst time
						elapsedBurst += p.getCurBurst();
						// sets the remaining burst for the process to 0
						p.decCurBurst(p.getCurBurst());
						
						if(p.getBurst().size() == 1){//job will finish
							// updates the total wait time for the scheduler
							totalWait += p.getWaitTime();
							// updates the total completion time for the scheduler
							p.setCompletionTime(elapsedBurst - p.getFArrivalTime());
							totalComp += p.getCompletionTime();
							// removes the process from the queue
							readyQueue.remove(i);
							i--;
						
						}else{
							int lastBurst = p.getCurBurst();
							p.getBurst().remove(0);
							
							sendToIO(p, elapsedBurst, lastBurst);//sent the process to the IOQueue
						}
					}

					else if (p.getCurBurst()> quantum) {//current burst segment will not be finished
														// this pass
						int curWait = p.getWaitTime() + (elapsedBurst - p.getArrivalTime());
						p.setWaitTime(curWait);//update total wait time for process

						elapsedBurst += quantum;
						p.decCurBurst(quantum);

					} 
				}
			}
			if(idle){
				result+=(elapsedBurst + "---[IDLE]---");
				elapsedBurst = getNextArrivalTime(readyQueue);
			}
			sortQueue();
		}
		result+=("Elapsed Burst = "+elapsedBurst);
                return result;
	}
	
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	//this method simulates the process being put through IO
	public void sendToIO(Process curProcess, int elapsedBurst, int lastBurst){
		int delay = 0;
		if(elapsedBurst<freeAt){//IO is not immediately available
			delay = freeAt - elapsedBurst;
		}
		curProcess.setArrivalTime(elapsedBurst+curProcess.getCurIoTime()+delay);
		freeAt += curProcess.getCurIoTime();
		curProcess.getIoTime().remove(0);
	}
	
}


