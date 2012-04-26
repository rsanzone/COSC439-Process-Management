package org.processmanagement.processes;

import java.util.ArrayList;

public class ProcessComplex {
	//default important data
	private ArrayList<Integer> burst = new ArrayList<Integer>();
	private int arrivalTime;
	private String name;
	//total time spent in IO
	private ArrayList<Integer> ioTime = new ArrayList<Integer>();
	
	private int waitTime = 0;
	private int completionTime = 0;
	ProcessComplex()
	{
		burst = new ArrayList<Integer>();
		ioTime = new ArrayList<Integer>();
		this.arrivalTime = 0;
		this.name = null;
	}
	public ProcessComplex(ArrayList<Integer> burst,int arrivalTime,String name)
	{
		for(Integer b:burst){
			this.burst.add(b);
		}
		this.arrivalTime = arrivalTime;
		this.name = name;
	}
	ProcessComplex(ArrayList<Integer> burst, int arrivalTime, ArrayList<Integer> ioTime, String name){
		for(Integer b:burst){
			this.burst.add(b);
		}
		for(Integer i:ioTime){
			this.ioTime.add(i);
		}
		this.arrivalTime = arrivalTime;
		this.name = name;
		
	}
	public ProcessComplex deepCopy(){
		ProcessComplex copy = new ProcessComplex(burst,arrivalTime,ioTime,name);
		return copy;
	}
	public ArrayList<Integer> getBurst(){
		return burst;
	}
	public Integer getNextBurst() 
	{
		return burst.get(0);
	}
	public void setBurst(ArrayList<Integer> burst) 
	{
		for(Integer b:burst){
			this.burst.add(b);
		}
	}
	public int getArrivalTime() 
	{
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}
	public int getWaitTime(){
		return waitTime;
	}
	public void setWaitTime(int waitTime){
		this.waitTime = waitTime;
	}
	public int getCompletionTime(){
		return completionTime;
	}
	public void setCompletionTime(int completionTime){
		this.completionTime = completionTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getIoTime(){
		return this.ioTime;
	}
	public Integer getNextIoTime() {
		return ioTime.get(0);
	}
	public void setIoTime(ArrayList<Integer> ioTime) {
		for(Integer i:ioTime){
			this.ioTime.add(i);
		}
	}

}
