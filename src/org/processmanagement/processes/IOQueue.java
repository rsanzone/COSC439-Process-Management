package org.processmanagement.processes;

import java.util.ArrayList;

public class IOQueue {
	private ArrayList<Process> processList;
	private ArrayList<Process> finishedList = new ArrayList<Process>();
	public IOQueue(){
		processList = new ArrayList<Process>();
	}
	public void addProcess(Process process){
		processList.add(process);
	}
	public ArrayList<Process> getProcesses(){
		return processList;
	}
	public void calcIO(int elapsedBurst){
		//make sure finished list is empty
		finishedList.clear();
		
		/* TODO 
		 * Not sure if a arraylist is needed here. It isn't for FIFO but not sure about the other 
		 * algorithms. Currently the IO only works with one process at a time, hence using
		 * index 0 to get the process. 
		 * 
		 * Leaving in place just in case for now
		 */
		Process curProcess = processList.get(0);
		
		int IOTime = curProcess.getIoSegment();//get IO time
		if(curProcess.getIoTime() >= IOTime){
			curProcess.setIoTime(curProcess.getIoTime() - IOTime);
		}
		else{
			curProcess.setIoTime(0);
		}
		curProcess.setArrivalTime(elapsedBurst + IOTime);//calc the new arrival time for process using IOTime
		
		finishedList.add(curProcess);//add process to finishedList and remove form processList.
		processList.remove(curProcess);

	}
	public ArrayList<Process> getFinished(){
		return finishedList;
	}
	
}
