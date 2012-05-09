package org.processmanagement.processes;

import java.util.*;
import javax.swing.JOptionPane;
//import java.lang.Math;

public class PRandom {
	final int MAX_PROCESS_NUMBER = 200;
	/**
	 * Generates a random integer in range between min and max
	 * 
	 * @param min lower limit
	 * @param max upper limit
	 * @return the random integer
	 */
	public int randomInt(int min, int max)
	{
		//new random object
		Random rand = new Random();
		//generate a random number between min and max both being
		//inclusive
		int randomNum = rand.nextInt(max - min + 1) + min;
		
		return randomNum;
	}	

	public ArrayList<Process> genProcesses(int num) {
		// create a random num between 1 and MAX_PROCESS_NUMBER
		int processNum = num;
		// int processNum = randomInt(1, MAX_PROCESS_NUMBER);
		ArrayList<Process> processList = new ArrayList<Process>();
		// populate a list with the generated number of processes
		for (int i = 1; i <= processNum; i++) {
			processList.add(randomProcess(i));
		}
		JOptionPane.showMessageDialog(null, processNum+" processes have been generated!", "Message", JOptionPane.INFORMATION_MESSAGE);
		return processList;
	}
	public ArrayList<Process> genProcesses_randNum() {
		//create a random num between 1 and MAX_PROCESS_NUMBER
		int processNum = randomInt(1, MAX_PROCESS_NUMBER);
		ArrayList<Process> processList = new ArrayList<Process>();
                String allProc="";
		// populate a list with the generated number of processes
		for (int i = 1; i <= processNum; i++) {
			processList.add(randomProcess(i));
		}
		allProc+=("\n"+processNum+" processes have been generated!");
                JOptionPane.showMessageDialog(null, allProc, "Message", JOptionPane.INFORMATION_MESSAGE);
		return processList;
	}
	private Process randomProcess(int count){
		//set random data
		int arrivalTime = randomInt(0, 10);
		String name = "P"+count;
		
		int numSeg = randomInt(1, 10);
		ArrayList<Integer> burst = new ArrayList<Integer>();
		for(int i = 0;i<numSeg;i++){
			if(numSeg > 1)//IO bound process
				burst.add(randomInt(1,25));
			else//cpu bound process
				burst.add(randomInt(50,500));
		}
		ArrayList<Integer> ioTime = new ArrayList<Integer>();
		//there will be one less ioTime than burstTime
		for(int i = 1;i<numSeg;i++){
			ioTime.add(randomInt(1,5));
		}
		
		//generate new process
		Process proc = new Process(burst,arrivalTime,ioTime,name);
		
		//return the process
		return proc;
	}
}
