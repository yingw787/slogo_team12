package exceptions;

public class NotEnoughParametersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotEnoughParametersException() { super(); }
	public NotEnoughParametersException(String message) { super(message); }
	public NotEnoughParametersException(String message, Throwable cause) { super(message, cause); }
	public NotEnoughParametersException(Throwable cause) { super(cause); }
	
}
