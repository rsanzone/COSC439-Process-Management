package org.processmanagement.fileio;

@SuppressWarnings("serial")
public class segmentException extends Throwable {
	String mistake;

	public segmentException() {
		super();
		mistake = "unknown";
	}

	public segmentException(String err) {
		super(err); // call super class constructor
		mistake = err; // save message
	}

	public String getError() {
		return mistake;
	}

}
