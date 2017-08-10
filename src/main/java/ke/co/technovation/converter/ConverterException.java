package ke.co.technovation.converter;

public class ConverterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2067113606317706726L;
	private Throwable throwable;
	private String message;

	public Throwable getThrowable() {
		return throwable;
	}


	public String getMessage() {
		return message;
	}


	public ConverterException(String message) {
		this.message = message;
	}
	
	public ConverterException(Throwable e) {
		this.throwable = e;
	}
	
	
	public ConverterException(String message, Throwable e) {
		this.message = message;
		this.throwable = e;
	}

}
