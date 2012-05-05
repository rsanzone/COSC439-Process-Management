package org.processmanagement.scheduling;

import java.util.ArrayList;

import org.processmanagement.fileio.FileManager;
import org.processmanagement.processes.PRandom;
import org.processmanagement.processes.Process;

/**
 * General scheduler class that is used as a baseline for the other scheduling methods * 
 *
 */
public class Scheduler {
	
	
	//Tools
	PRandom rand = new PRandom();
	FileManager manager = new FileManager();
	int freeAt = 0;
	
	//List Variables
	//processes will be taken from the process list and put into the ReadyQueue;
	ArrayList<Process> pList;
	ArrayList<Process> readyQueue = new ArrayList<Process>();
	
	//stat related variables
	int totalBurst = 0;
	int totalWait = 0;
	int totalComp = 0;
	int size = 0;
	
	public Scheduler(){
		pList = new ArrayList<Process>();
		readyQueue = new ArrayList<Process>();
	}
	/**
	 * generate random processes for the queue
	 */
	public void genProcesses(){
		pList = rand.genProcesses();
	}
	/*public void saveProcesses(){
		manager.savePList(pList);
	}
	public void genProcessesFromList(){
		pList = manager.loadPList();
	}*/
	/**
	 * Print out various stats calculated by the simulation.
	 */
	public void printStats(){
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("Total Burst Time: " + totalBurst);
		System.out.println("Average Burst Time: " + (float)(totalBurst/size));
		System.out.println("Average Wait Time: " + (float)(totalWait/size));
		System.out.println("Average Completion Time: " + (float)(totalComp/size));
	}
	public void printProcesses(){
		for(Process p:pList){
			System.out.println(p.getName()+": ");
			System.out.println("	Arrival Time = "+p.getArrivalTime());
			//will need to print out each index
			System.out.println("	Burst Time = "+p.getBurst());
			System.out.println("    IO Time = "+p.getIoTime());
		}
	}
	public int getNextArrivalTime(ArrayList<Process> list){
		int time = list.get(0).getArrivalTime();
		for(Process p: list){
			if (p.getArrivalTime()<time)
				time = p.getArrivalTime();
		}
		return time;
	}
	public ArrayList<Process> getReadyQueue(){
		return this.readyQueue;
	}
	public void setReadyQueue(ArrayList<Process> queue){
		this.readyQueue = queue;
	}
	public ArrayList<Process> getPList(){
		return this.pList;
	}
	public void setPList(ArrayList<Process> queue){
		this.pList = queue;
	}
	public int getTotalWaitTime(){
		return totalWait;
	}
	public int getTotalCompTime(){
		return totalComp;
	}
	public int getSize(){
		return size;
	}

}
