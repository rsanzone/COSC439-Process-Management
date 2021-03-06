package org.processmanagement.processes;

import java.util.ArrayList;

public class Process {
	//default important data
	private ArrayList<Integer> burst = new ArrayList<Integer>();
	private int arrivalTime;
	private int fArrivalTime;
	private String name;
	//total time spent in IO
	private ArrayList<Integer> ioTime = new ArrayList<Integer>();
	
	private int waitTime = 0;
	private int completionTime = 0;
	private int totalBurst = 0;
	private int originCurBurst;
	public Process()
	{
		burst = new ArrayList<Integer>();
		ioTime = new ArrayList<Integer>();
		this.arrivalTime = 0;
		this.name = null;
	}
	public Process(ArrayList<Integer> burst,int arrivalTime,String name)
	{
		for(Integer b:burst){
			this.burst.add(b);
			totalBurst += b;
		}
		this.arrivalTime = arrivalTime;
		this.fArrivalTime = arrivalTime;
		this.name = name;
	}
	public Process(ArrayList<Integer> burst, int arrivalTime, ArrayList<Integer> ioTime, String name){
		for(Integer b:burst){
			this.burst.add(b);
			totalBurst += b;
		}
		for(Integer i:ioTime){
			this.ioTime.add(i);
		}
		this.arrivalTime = arrivalTime;
		this.fArrivalTime = arrivalTime;
		this.name = name;
		this.setOriginCurBurst(this.getCurBurst());
		
	}
	public Process deepCopy(){
		Process copy = new Process(burst,arrivalTime,ioTime,name);
		return copy;
	}
	public ArrayList<Integer> getBurst(){
		return burst;
	}
	public Integer getCurBurst() 
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
	public Integer getCurIoTime() {
		return ioTime.get(0);
	}
	public void setIoTime(ArrayList<Integer> ioTime) {
		for(Integer i:ioTime){
			this.ioTime.add(i);
		}
	}
	public void decCurBurst(int amount){
		burst.set(0, burst.get(0)-amount);
	}
	public void decCurIo(){
		ioTime.set(0, ioTime.get(0)-1);
	}
	public int getTotalBurst(){
		return totalBurst;
	}
	public int getFArrivalTime(){
		return this.fArrivalTime;
	}
	public int getOriginCurBurst() {
		return originCurBurst;
	}
	public void setOriginCurBurst(int originCurBurst) {
		this.originCurBurst = originCurBurst;
	}

}
