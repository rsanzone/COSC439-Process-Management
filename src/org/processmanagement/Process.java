package org.processmanagement;

public class Process {
	private float burst;
	private int priority;
	private float arrivalTime;
	Process()
	{
		this.burst = 0;
		this.priority = 0;
		this.arrivalTime = 0;
	}
	Process(float burst)
	{
		this.burst = burst;
		this.priority = 0;
		this.arrivalTime = 0;
	}
	Process(float burst,float arrivalTime)
	{
		this.burst = burst;
		this.priority = 0;
		this.arrivalTime = arrivalTime;
	}
	Process(float burst,float arrivalTime,int priority)
	{
		this.burst = burst;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
	}
	
	public float getBurst() 
	{
		return burst;
	}
	public void setBurst(float burst) 
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
	public float getArrivalTime() 
	{
		return arrivalTime;
	}
	public void setArrivalTime(float arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}

}
