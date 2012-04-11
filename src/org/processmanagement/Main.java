package org.processmanagement;

import java.util.*;

import org.processmanagement.scheduling.*;
import org.processmanagement.processes.Process;

/*
 * This will be the main class of the project.
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int choice;
		boolean keepProcesses = false;
		ArrayList<Process> holder = new ArrayList<Process>();
		
		do{
			System.out.println("0): Quit.");
			System.out.println("1): First in First out.");
			System.out.println("2): Shortest Job First.");
			System.out.println("3): Round Robin.");
			System.out.println("Choose an algorithm");

			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			switch (choice) {
			case 0: {
				System.out.println("Exiting...");
				break;
			}
			case 1: {
				FirstInFirstOut fifoTest = new FirstInFirstOut();
				if(keepProcesses == false)
					fifoTest.genProcesses();
				else
					fifoTest.setPList(holder);
				holder = fifoTest.getPList();
				fifoTest.start();
				System.out.println();
				fifoTest.printStats();
				break;
			}
			case 2: {
				ShortestJobFirst sjfTest = new ShortestJobFirst();
				if(keepProcesses == false)
					sjfTest.genProcesses();
				else
					sjfTest.setPList(holder);
				holder = sjfTest.getPList();
				sjfTest.start();
				System.out.println();
				sjfTest.printStats();
				break;

			}
			case 3: {
				System.out.print("Enter a time quantum: ");
				int quantum = input.nextInt();
				RoundRobin rrTest = new RoundRobin(quantum);
				if(keepProcesses == false)
					rrTest.genProcesses();
				else
					rrTest.setPList(holder);
				holder = rrTest.getPList();
				rrTest.start();
				System.out.println();
				rrTest.printStats();
			}
			default:
				System.out.println("Invalid choice!!!");
				break;
			}
			if(choice!=0)
				keepProcesses = askToKeep();
		}while(choice!=0);
		
	}
	public static boolean askToKeep(){
		Scanner input = new Scanner(System.in);
		boolean result = false;
		System.out.println("Would you like to use this set of processes for another algorithm? (y/n): ");
		char answer = input.next().toLowerCase().charAt(0);
		switch(answer){
		case 'y': {
			result = true;
			break;
		}
		case 'n': {
			result = false;
			break;
		}
		default:
			break;
		}
		return result;
	}

}
