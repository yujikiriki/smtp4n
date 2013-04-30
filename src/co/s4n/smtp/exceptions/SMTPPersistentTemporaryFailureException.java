package co.s4n.smtp.exceptions;

public class SMTPPersistentTemporaryFailureException extends TechnicalException {

	private static final long serialVersionUID = 3367791676886679888L;

	public SMTPPersistentTemporaryFailureException( String message, Throwable cause ) {
		super( message, cause );
	}

}
