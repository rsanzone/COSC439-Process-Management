package org.processmanagement;

import java.util.*;

import org.processmanagement.scheduling.*;
import org.processmanagement.fileio.FileManager;
import org.processmanagement.processes.PRandom;
import org.processmanagement.processes.Process;

/*
 * This will be the main class of the project.
 */
public class Main {

	/**
	 * @param args
	 */
	
	static ArrayList<Process> holder = new ArrayList<Process>();
	
	public static void main(String[] args) {
		menu1();
	}
	public static void printMenu1(){
		System.out.println("Choose an option...");
		System.out.println("0): Quit.");
		System.out.println("1): Load Process List.");
		System.out.println("2): Create New Process List.");
		System.out.println("3): Run a large batch coparison.");
	}
	public static void menu1(){
		boolean quit = false;
		int choice;
		Scanner input = new Scanner(System.in);
		do{
			printMenu1();
			choice = input.nextInt();
			switch (choice) {
			case 0: {
				quit = false;
				System.out.println("Exiting...");
				break;
			}
			case 1: {
				FileManager manager = new FileManager();
				holder = copy(manager.loadPList());
				break;
			}
			case 2: {
				PRandom random = new PRandom();
				holder = copy(random.genProcesses(5));
				System.out.println("Would you like to save this Process List? (y/n)");
				char answer = input.next().toLowerCase().charAt(0);
				if(answer == 'y'){
					FileManager manager = new FileManager();
					//manager.savePList(holder);
				}
				break;
			}
			case 3: {
				float fifoWaitTime = 0;
				float fifoCompTime = 0;
				float sjfWaitTime = 0;
				float sjfCompTime = 0;
				float rrWaitTime = 0;
				float rrCompTime = 0;
				
				PRandom random = new PRandom();
				System.out.println("How many simulations would you like to run? ");
				int num = input.nextInt();
				for(int i = 0;i<num;i++){
					ArrayList<Process> list = random.genProcesses_randNum();
					
					FirstInFirstOut fifo = new FirstInFirstOut();
					fifo.setPList(copy(list));
					fifo.start();
					fifoWaitTime += fifo.getTotalWaitTime()/fifo.getSize();
					fifoCompTime += fifo.getTotalCompTime()/fifo.getSize();
					
					ShortestJobFirst sjf = new ShortestJobFirst();
					sjf.setPList(copy(list));
					sjf.start();
					sjfWaitTime += sjf.getTotalWaitTime()/sjf.getSize();
					sjfCompTime += sjf.getTotalCompTime()/sjf.getSize();
					
					RoundRobin rr = new RoundRobin(random.randomInt(1, 20));
					rr.setPList(copy(list));
					rr.start();
					rrWaitTime += rr.getTotalWaitTime()/rr.getSize();
					rrCompTime += rr.getTotalCompTime()/rr.getSize();
				}
				System.out.println("After "+num+" simulations, the stats were as follows: ");
				System.out.println("First In First Out: Avg Wait Time = "+fifoWaitTime/num+" Avg Turnaround Time = "+fifoCompTime/num);
				System.out.println("Shortest Job First: Avg Wait Time = "+sjfWaitTime/num+" Avg Turnaround Time = "+sjfCompTime/num);
				System.out.println("Round Robin:        Avg Wait Time = "+rrWaitTime/num+" Avg Turnaround Time = "+rrCompTime/num);
				break;
			}
			default:
				break;
			}
			if(choice!=3)
				menu2();
		}while(!quit);
	}
	public static void printMenu2(){
		System.out.println("0): Go Back.");
		System.out.println("1): First in First out.");
		System.out.println("2): Shortest Job First.");
		System.out.println("3): Round Robin.");
		System.out.println("Choose an algorithm");
	}
	public static void menu2(){
		int choice;
		do{
			printMenu2();
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			switch (choice) {
			case 0: {
				System.out.println("Exiting...");
				break;
			}
			case 1: {
				FirstInFirstOut fifoTest = new FirstInFirstOut();
				fifoTest.setPList(copy(holder));
				fifoTest.start();
				break;
			}
			case 2: {
				ShortestJobFirst sjfTest = new ShortestJobFirst();
				sjfTest.setPList(copy(holder));
				sjfTest.start();
				break;

			}
			case 3: {
				System.out.print("Enter a time quantum: ");
				int quantum = input.nextInt();

				RoundRobin rrTest = new RoundRobin(quantum);
				rrTest.setPList(copy(holder));
				rrTest.start();
				break;
			}

			default:
				System.out.println("Invalid choice!!!");
				break;
			}
		}while(choice!=0);
	}
	public static ArrayList<Process> copy(ArrayList<Process> origin){
		ArrayList<Process> copy = new ArrayList<Process>();
		for(Process p:origin){
			copy.add(p.deepCopy());
		}
		return copy;
	}

}
