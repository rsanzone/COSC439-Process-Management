package org.processmanagement;

import org.processmanagement.scheduling.FirstInFirstOutCom;

public class MainComplex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FirstInFirstOutCom fifoTest = new FirstInFirstOutCom();
		fifoTest.genProcesses();
		fifoTest.start();

	}

}
