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
			//get the first process in the queue
			Process curProcess = readyQueue.get(0); //uses index 0 because processes will be continually removed
													//so 0 will be next process

			if(curProcess.getArrivalTime()>elapsedBurst){//find idle time
				System.out.print(elapsedBurst + "---[IDLE]---");
				elapsedBurst = curProcess.getArrivalTime();
			}
			if(curProcess.getRemainingBurst() <= curProcess.getBurstSegment()){ //job will finish

				System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");//print nat section
				//calc the current wait time for the current burst section of the process
				int curWait = curProcess.getWaitTime() + (elapsedBurst - curProcess.getArrivalTime());
				curProcess.setWaitTime(curWait);//update total wait time for process
				
				elapsedBurst += curProcess.getRemainingBurst();
			
				curProcess.setCompletionTime(elapsedBurst - curProcess.getInitialArrival());
				curProcess.setRemainingBurst(0);//set remaining burst to 0 since process is finished
				
				readyQueue.remove(curProcess);//remove the process
				
				//update totals
				totalWait += curProcess.getWaitTime();
				totalComp += curProcess.getCompletionTime();
				
			}
			else{//job will not finish within the current burst segment
				System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");//print nat
				
				int curWait = curProcess.getWaitTime() + (elapsedBurst - curProcess.getArrivalTime());//current segment wait
				curProcess.setWaitTime(curWait); //set total process wait
			
				elapsedBurst += curProcess.getBurstSegment(); 
				
				curProcess.setRemainingBurst(curProcess.getRemainingBurst()-curProcess.getBurstSegment());
				
				sendToIO(curProcess, elapsedBurst);//sent the process to the IOQueue
				//make sure there is more than one element in the queue before removing
				if(readyQueue.size()>1)//this is to account for there only be one remaining process that has burst and IO left.
					readyQueue.remove(curProcess);//remove process

			}
		
		}
		System.out.print(elapsedBurst);
	}
	public void updateQueue(int elapsedBurst){
		//calculate the IO times
		ioQueue.calcIO(elapsedBurst);
		Process p = ioQueue.getFinished().get(0);
		//make sure the queue does not have a duplicate
		//make sure process has burst left before reading to queue
		if(!readyQueue.contains(p)){
			if(p.getRemainingBurst() > 0)
				readyQueue.add(p);
		}
		//resort queue
		sortQueue();
	}
	public void sendToIO(Process curProcess, int elapsedBurst){
		ioQueue.addProcess(curProcess);//add process to IO
		
	}
	

}
