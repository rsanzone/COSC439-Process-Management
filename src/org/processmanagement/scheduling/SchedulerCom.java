package org.processmanagement.scheduling;

import java.util.ArrayList;

import org.processmanagement.fileio.FileManager;
import org.processmanagement.processes.PRandom;
import org.processmanagement.processes.PRandomComplex;
import org.processmanagement.processes.Process;
import org.processmanagement.processes.ProcessComplex;

/**
 * General scheduler class that is used as a baseline for the other scheduling methods * 
 *
 */
public class SchedulerCom {
	
	//processes will be taken from the process list and put into the ReadyQueue;
	ArrayList<ProcessComplex> pList;
	ArrayList<ProcessComplex> readyQueue = new ArrayList<ProcessComplex>();
	PRandomComplex rand = new PRandomComplex();
	FileManager manager = new FileManager();
	
	int totalBurst = 0;
	int totalWait = 0;
	int totalComp = 0;
	int size = 0;
	
	public SchedulerCom(){
		pList = new ArrayList<ProcessComplex>();
		readyQueue = new ArrayList<ProcessComplex>();
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
		for(ProcessComplex p:pList){
			System.out.println(p.getName()+": ");
			System.out.println("	Arrival Time = "+p.getArrivalTime());
			//will need to print out each index
			System.out.println("	Burst Time = "+p.getBurst());
			System.out.println("    IO Time = "+p.getIoTime());
		}
	}
	public int getNextArrivalTime(ArrayList<ProcessComplex> list){
		int time = list.get(0).getArrivalTime();
		for(ProcessComplex p: list){
			if (p.getArrivalTime()<time)
				time = p.getArrivalTime();
		}
		return time;
	}
	public ArrayList<ProcessComplex> getReadyQueue(){
		return this.readyQueue;
	}
	public void setReadyQueue(ArrayList<ProcessComplex> queue){
		this.readyQueue = queue;
	}
	public ArrayList<ProcessComplex> getPList(){
		return this.pList;
	}
	public void setPList(ArrayList<ProcessComplex> queue){
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
