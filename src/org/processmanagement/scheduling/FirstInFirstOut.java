package org.processmanagement.scheduling;


import java.util.Collections;
import java.util.Comparator;

import org.processmanagement.processes.IOQueue;
import org.processmanagement.processes.Process;

public class FirstInFirstOut extends Scheduler {
	IOQueue ioQueue = new IOQueue();
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
		
		while(!readyQueue.isEmpty()){
			if(!ioQueue.getProcesses().isEmpty()){	
				updateQueue(elapsedBurst);
			}
			Process curProcess = readyQueue.get(0);

			if(curProcess.getArrivalTime()>elapsedBurst){
				System.out.print(elapsedBurst + "---[IDLE]---");
				elapsedBurst = curProcess.getArrivalTime();
			}
			if(curProcess.getRemainingBurst() <= curProcess.getBurstSegment()){ //job will finish

				System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");
				int curWait = curProcess.getWaitTime() + (elapsedBurst - curProcess.getArrivalTime());
				curProcess.setWaitTime(curWait);
				elapsedBurst += curProcess.getRemainingBurst();
			
				curProcess.setCompletionTime(elapsedBurst - curProcess.getInitialArrival());
				curProcess.setRemainingBurst(0);
				readyQueue.remove(curProcess);
				
				totalWait += curProcess.getWaitTime();
				totalComp += curProcess.getCompletionTime();
				
			}
			else{//job will not finish within the current burst segment

				System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");
				int curWait = curProcess.getWaitTime() + (elapsedBurst - curProcess.getArrivalTime());
				curProcess.setWaitTime(curWait);
				elapsedBurst += curProcess.getBurstSegment();
				curProcess.setRemainingBurst(curProcess.getRemainingBurst()-curProcess.getBurstSegment());
				sendToIO(curProcess, elapsedBurst);
				readyQueue.remove(curProcess);

			}
			

			

			
		}
		System.out.print(elapsedBurst);
	}
	public void updateQueue(int elapsedBurst){
		ioQueue.calcIO(elapsedBurst);
		Process p = ioQueue.getFinished().get(0);
		if(p.getRemainingBurst() > 0)
			readyQueue.add(p);
		
		sortQueue();
	}
	public void sendToIO(Process curProcess, int elapsedBurst){
		ioQueue.addProcess(curProcess);
		
	}
	

}
