package org.processmanagement.scheduling;

import java.util.*;

import org.processmanagement.processes.*;
import org.processmanagement.processes.Process;

public class Fifo {
	ArrayList<Process> queue = new ArrayList<Process>();
	float totalBurst = 0.0f;
	
	
	public void loadQueue(ReadyQueue queue){
		this.queue = queue.getQueue();
	}
	
	
	public void start(){
		int count = 0;
		
		for(int i=0; i<queue.size(); i++){
			Process curProcess = queue.get(count);
			float curBurst = curProcess.getBurst();
			
			System.out.println("Running Process " + i);
			System.out.println("Process " + i + " Burst time: " + curProcess.getBurst());
			System.out.println("Process "+ i + " Burst time: (long) " + curProcess.getBurst());
			
			long initTime = System.currentTimeMillis();
			long curTime = System.currentTimeMillis();
			
			while((curTime - initTime) <= (long)curBurst){
				curTime = System.currentTimeMillis();
			}
			totalBurst += curBurst;
			count++;
		}
		
	}
	
	public void getStats(){
		System.out.println("Total Burst Time: " + totalBurst);
		System.out.println("Average Burst Time: " + (totalBurst/(float)queue.size()));
	}
	

}
