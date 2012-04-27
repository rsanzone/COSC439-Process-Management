package org.processmanagement;

import org.processmanagement.scheduling.FirstInFirstOutCom;
import org.processmanagement.scheduling.ShortestJobFirstCom;

public class MainComplex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShortestJobFirstCom fifoTest = new ShortestJobFirstCom();
		fifoTest.genProcesses();
		fifoTest.start();

	}

}
