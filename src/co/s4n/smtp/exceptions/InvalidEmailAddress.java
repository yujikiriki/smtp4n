package co.s4n.smtp.exceptions;

public class InvalidEmailAddress extends TechnicalException {
	
	private static final long serialVersionUID = -2582200698697811863L;
	
	public InvalidEmailAddress( String message, Throwable cause ) {
		super( message, cause );
	}
}
