package org.processmanagement;

import org.processmanagement.fileio.FileManager;
import org.processmanagement.scheduling.FirstInFirstOutCom;
import org.processmanagement.scheduling.RoundRobinCom;
import org.processmanagement.scheduling.ShortestJobFirstCom;

public class MainComplex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int quantum = 10;
		RoundRobinCom fifoTest = new RoundRobinCom(quantum);
		fifoTest.genProcesses();
		FileManager manager = new FileManager();
		manager.savePList(fifoTest.getPList());
		fifoTest.start();

	}

}
