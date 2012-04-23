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
		finishedList.clear();
		
		Process curProcess = processList.get(0);
		int IOTime = curProcess.getIoSegment();
		curProcess.setArrivalTime(elapsedBurst + IOTime);
		finishedList.add(curProcess);
		processList.remove(curProcess);
		
		
		
	}
	public ArrayList<Process> getFinished(){
		return finishedList;
	}
	
}
