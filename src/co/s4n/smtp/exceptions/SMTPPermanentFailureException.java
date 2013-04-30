package co.s4n.smtp.exceptions;

public class SMTPPermanentFailureException extends TechnicalException {

	private static final long serialVersionUID = -5478635051829568026L;

	public SMTPPermanentFailureException( String message, Throwable cause ) {
		super( message, cause );
	}
}
