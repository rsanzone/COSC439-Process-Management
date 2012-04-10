package org.processmanagement.processes;

public class Process {
	//default important data
	private int burst;
	private int priority;
	private int arrivalTime;
	//data only important for IO processes
	private int remainingBurst;
	private int burstSegment; //burst time before going to IO
	private int ioTime;//total time spent in IO
	private int ioSegment;//time spent in IO before returning to ready queue
	
	private int waitTime = 0;
	private int completionTime = 0;
	Process()
	{
		this.burst = 0;
		this.priority = 0;
		this.arrivalTime = 0;
		this.burstSegment = 0;
		this.ioTime = 0;
		this.ioSegment = 0;
		this.remainingBurst = 0;
	}
	Process(int burst,int arrivalTime,int priority)
	{
		this.burst = burst;
		this.remainingBurst = burst;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
	}
	Process(int burst, int arrivalTime, int priority, int burstSegment, int ioTime, int ioSegment){
		this.burst = burst;
		this.remainingBurst = burst;
		this.arrivalTime = arrivalTime;
		this.priority = priority;
		this.burstSegment = burstSegment;
		this.ioTime = ioTime;
		this.ioSegment = ioSegment;
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
	public int getRemainingBurst() {
		return remainingBurst;
	}
	public void setRemainingBurst(int remainingBurst) {
		this.remainingBurst = remainingBurst;
	}
	public void decRemainingBurst(int amount){
		this.remainingBurst -= amount;
	}
	public int getElapsedBurst() {
		return this.burst - this.remainingBurst;
	}

}
