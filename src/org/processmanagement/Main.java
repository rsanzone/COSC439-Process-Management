package org.processmanagement;

import java.util.*;
import org.processmanagement.scheduling.*;



/*
 * This will be the main class of the project.
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("1): First in First out.");
		System.out.println("2): Shortest Job First.");
		System.out.println("Choose an algorithm");
		int choice;
		Scanner input = new Scanner(System.in);
		choice = input.nextInt();
		switch(choice){
		case 1: {
			FirstInFirstOut fifoTest = new FirstInFirstOut();
			
			fifoTest.genProcesses();
			fifoTest.start();
			fifoTest.printStats();
			break;
		}
		case 2: {
			ShortestJobFirst sjfTest = new ShortestJobFirst();
			
			sjfTest.genProcesses();
			sjfTest.start();
			sjfTest.printStats();
			break;

			
		}
		default: System.out.println("Invalid choice!!!");
		break;
		}
		
		
		
		
		
	}

}
