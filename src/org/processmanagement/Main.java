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
	static boolean fileLoaded;
	static ArrayList<Process> holder = new ArrayList<Process>();
	
	public static void main(String[] args) {
		menu1();
	}
	public static void menu2(){
		int choice;
		do{
			fileLoaded = false;
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
	public static void printMenu2(){
		System.out.println("0): Go Back.");
		System.out.println("1): First in First out.");
		System.out.println("2): Shortest Job First.");
		System.out.println("3): Round Robin.");
		System.out.println("Choose an algorithm");
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
				holder = copy(random.genProcesses());
				System.out.println("Would you like to save this Process List? (y/n)");
				char answer = input.next().toLowerCase().charAt(0);
				if(answer == 'y'){
					FileManager manager = new FileManager();
					manager.savePList(holder);
				}
				break;
			}
			default:
				break;
			}
			menu2();
		}while(!quit);
	}
	public static void printMenu1(){
		System.out.println("Choose an option...");
		System.out.println("0): Quit.");
		System.out.println("1): Load Process List.");
		System.out.println("2): Create New Process List.");
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
	public static ArrayList<Process> copy(ArrayList<Process> origin){
		ArrayList<Process> copy = new ArrayList<Process>();
		for(Process p:origin){
			copy.add(p.deepCopy());
		}
		return copy;
	}

}
