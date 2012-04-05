package org.processmanagement.processes;

public class Process {
	private int burst;
	private int burstBeforeIO;
	private int ioBeforeBurst;
	private int ioTime;
	private int priority;
	private int arrivalTime;
	private int waitTime = 0;
	private int completionTime = 0;
	Process()
	{
		this.burst = 0;
		this.priority = 0;
		this.arrivalTime = 0;
	}
	Process(int burst)
	{
		this.burst = burst;
		this.priority = 0;
		this.arrivalTime = 0;
	}
	Process(int burst,int arrivalTime)
	{
		this.burst = burst;
		this.priority = 0;
		this.arrivalTime = arrivalTime;
	}
	Process(int burst,int arrivalTime,int priority)
	{
		this.burst = burst;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
	}
	
	public int getBurst() 
	{
		return burst;
	}
	public void setBurst(int burst) 
	{
		this.burst = burst;
	}
	public int getPriority() 
	{
		return priority;
	}
	public void setPriority(int priority) 
	{
		this.priority = priority;
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

}
