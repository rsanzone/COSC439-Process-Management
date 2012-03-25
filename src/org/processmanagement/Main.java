package org.processmanagement;

import java.util.*;
import org.processmanagement.processes.*;
import org.processmanagement.processes.Process;


/*
 * This will be the main class of the project.
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadyQueue test = new ReadyQueue();
		ArrayList<Process> list = new ArrayList<Process>();
		
		test.populateQueue();
		
		list = test.getQueue();
		
		int count = 1;
		for(Process item : list){
			System.out.println("Process Number: " + count);
			System.out.println("Arrival Time: " + item.getArrivalTime());
			System.out.println("Burst Time: " + item.getBurst());
			System.out.println("Priority: " + item.getPriority());
			System.out.println();
			count++;
		}
		
	}

}
