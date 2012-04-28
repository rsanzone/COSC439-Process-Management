package org.processmanagement;

import org.processmanagement.fileio.FileManager;
import org.processmanagement.scheduling.FirstInFirstOutCom;
import org.processmanagement.scheduling.ShortestJobFirstCom;

public class MainComplex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShortestJobFirstCom fifoTest = new ShortestJobFirstCom();
		fifoTest.genProcesses();
		FileManager manager = new FileManager();
		manager.savePList(fifoTest.getPList());
		fifoTest.start();

	}

}
