package org.processmanagement.scheduling;


import java.util.Collections;
import java.util.Comparator;

import org.processmanagement.processes.IOQueue;
import org.processmanagement.processes.Process;
import org.processmanagement.processes.ProcessComplex;

public class FirstInFirstOutCom extends SchedulerCom {
	IOQueue ioQueue = new IOQueue();
	/**
	 * Sorting class to sort queue by arrival time
	 */
	private void sortQueue(){
		//java sort method with a comparator class to compare arrival times
		Collections.sort(readyQueue, new Comparator<ProcessComplex>() {
			public int compare(ProcessComplex process1, ProcessComplex process2) {
				 return process1.getArrivalTime() - process2.getArrivalTime();
			}
		});
	}
	/**
	 * Method that starts the first in first out scheduler simulation.
	 */			
	public void start(){
		for(ProcessComplex p:pList){
			readyQueue.add(p.deepCopy());
			totalBurst += p.getNextBurst();
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
			//get the first process in the queue
			ProcessComplex curProcess = readyQueue.get(0); //uses index 0 because processes will be continually removed
													//so 0 will be next process

			if(curProcess.getArrivalTime()>elapsedBurst){//find idle time
				System.out.print(elapsedBurst + "---[IDLE]---");
				elapsedBurst = curProcess.getArrivalTime();
			}
			if(curProcess.getBurst().size()==1){ //job will finish

				System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");//print nat section
				//calc the current wait time for the current burst section of the process
				int curWait = curProcess.getWaitTime() + (elapsedBurst - curProcess.getArrivalTime());
				curProcess.setWaitTime(curWait);//update total wait time for process
				
				elapsedBurst += curProcess.getNextBurst();
			
				curProcess.setCompletionTime(elapsedBurst - curProcess.getArrivalTime());
				curProcess.getBurst().remove(0);
				readyQueue.remove(curProcess);//remove the process
				
				//update totals
				totalWait += curProcess.getWaitTime();
				totalComp += curProcess.getCompletionTime();
				
			}
			else{//job will not finish within the current burst segment
				System.out.print(elapsedBurst + "---[" + curProcess.getName() + "]---");//print nat
				
				int curWait = curProcess.getWaitTime() + (elapsedBurst - curProcess.getArrivalTime());//current segment wait
				curProcess.setWaitTime(curWait); //set total process wait
			
				elapsedBurst += curProcess.getNextBurst(); 
				
				curProcess.getBurst().remove(0);
				
				sendToIO(curProcess, elapsedBurst);//sent the process to the IOQueue
				//make sure there is more than one element in the queue before removing
			}
			sortQueue();
		
		}
		System.out.print(elapsedBurst);
	}
	
	public void sendToIO(ProcessComplex curProcess, int elapsedBurst){
		curProcess.setArrivalTime(elapsedBurst+curProcess.getNextIoTime());
		curProcess.getIoTime().remove(0);
	}
	

}
