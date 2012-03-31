package org.processmanagement.scheduling;


import org.processmanagement.processes.Process;

public class Fifo extends Scheduler {
	
	public void start(){
		int count = 0;
		
		for(int i=0; i<queue.getQueue().size(); i++){
			Process curProcess = super.queue.getQueue().get(count);
			float curBurst = curProcess.getBurst();
			
			//System.out.println("Running Process " + i);
			//System.out.println("Process " + i + " Burst time: " + curProcess.getBurst());
			//System.out.println("Process "+ i + " Burst time: (long) " + curProcess.getBurst());
			
			long initTime = System.currentTimeMillis();
			long curTime = System.currentTimeMillis();
			
			while((curTime - initTime) <= (long)curBurst){
				curTime = System.currentTimeMillis();
			}
			totalBurst += curBurst;
			count++;
		}
		
	}
}
