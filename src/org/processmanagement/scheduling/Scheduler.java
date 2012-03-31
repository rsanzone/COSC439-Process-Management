package org.processmanagement.scheduling;

import org.processmanagement.processes.Process;
import org.processmanagement.processes.ReadyQueue;

public class Scheduler {
	ReadyQueue queue;
	int totalBurst = 0;
	int avgWait = 0;
	int avgComp = 0;
	public Scheduler(){
		queue = new ReadyQueue();
		
	}
	public void genProcesses(){
		queue.populateQueue();
		System.out.println(queue.getQueue().size() + " processes have been generated!"+'\n');
	}
	public void printStats(){
		for(int i = 0;i<queue.getQueue().size();i++){
			Process curProcess = queue.getQueue().get(i);
			System.out.println("P"+(i+1)+": Burst Time = "+curProcess.getBurst());
		}
		System.out.println();
		System.out.println("Total Burst Time: " + totalBurst);
		System.out.println("Average Burst Time: " + (totalBurst/queue.getQueue().size()));
	}

}
