package co.s4n.smtp.exceptions;

public class InvalidDNSException extends TechnicalException {

	private static final long serialVersionUID = 4483565495469502503L;
	
	public InvalidDNSException( String message, Throwable cause ) {
		super( message, cause );
	}
}
