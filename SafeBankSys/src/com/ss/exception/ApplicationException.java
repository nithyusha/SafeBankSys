/**
 * 
 */
package com.ss.exception;


public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2180348972548317896L;

	/**
	 * 
	 */
	public ApplicationException() {
		super();
 
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		System.out.println(arg0);
		System.out.println(arg1);

	}

	/**
	 * @param arg0
	 */
	public ApplicationException(String arg0) {
		super(arg0);
		System.out.println(arg0);

	}

	/**
	 * @param arg0
	 */
	public ApplicationException(Throwable arg0) {
		super(arg0);
		System.out.println(arg0);
 	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + getMessage() + ", " + getClass() + "]";
	}

}
