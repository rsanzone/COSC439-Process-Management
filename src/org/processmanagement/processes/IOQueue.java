package org.processmanagement.processes;

import java.util.ArrayList;

public class IOQueue {
	ArrayList<Process> processList;
	public IOQueue(){
		processList = new ArrayList<Process>();
	}
	public void addProcess(Process process){
		processList.add(process);
	}
	
}
