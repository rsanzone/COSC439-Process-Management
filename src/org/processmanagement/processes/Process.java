package org.processmanagement.processes;

public class Process {
	//default important data
	private int burst;
	private int arrivalTime;
	private String name;
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
		this.arrivalTime = 0;
		this.setBurstSegment(0);
		this.setIoTime(0);
		this.setIoSegment(0);
		this.remainingBurst = 0;
	}
	Process(int burst,int arrivalTime,String name)
	{
		this.burst = burst;
		this.remainingBurst = burst;
		this.arrivalTime = arrivalTime;
		this.setName(name);
	}
	Process(int burst, int arrivalTime, int burstSegment, int ioTime, int ioSegment, String name){
		this.burst = burst;
		this.remainingBurst = burst;
		this.arrivalTime = arrivalTime;
		this.setBurstSegment(burstSegment);
		this.setIoTime(ioTime);
		this.setIoSegment(ioSegment);
		this.setName(name);
	}
	public Process deepCopy(){
		Process copy = new Process(burst,arrivalTime,burstSegment,ioTime,ioSegment,name);
		return copy;
	}
	
	public int getBurst() 
	{
		return burst;
	}
	public void setBurst(int burst) 
	{
		this.burst = burst;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBurstSegment() {
		return burstSegment;
	}
	public void setBurstSegment(int burstSegment) {
		this.burstSegment = burstSegment;
	}
	public int getIoTime() {
		return ioTime;
	}
	public void setIoTime(int ioTime) {
		this.ioTime = ioTime;
	}
	public int getIoSegment() {
		return ioSegment;
	}
	public void setIoSegment(int ioSegment) {
		this.ioSegment = ioSegment;
	}

}
