package org.processmanagement;

import java.util.*;
import org.processmanagement.processes.*;
import org.processmanagement.processes.Process;
import org.processmanagement.scheduling.*;



/*
 * This will be the main class of the project.
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadyQueue test = new ReadyQueue();
		Fifo fifoTest = new Fifo();
		//ArrayList<Process> list = new ArrayList<Process>();
		
		test.populateQueue();
		fifoTest.loadQueue(test);
		
		fifoTest.start();
		fifoTest.getStats();
		
		
		
	}

}
