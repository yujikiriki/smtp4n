package co.s4n.smtp.exceptions;

public class SMTPServerConnectionException extends TechnicalException {
	
	private static final long serialVersionUID = -7070910053232016900L;

	public SMTPServerConnectionException( String message, Throwable cause ) {
		super( message, cause );
	}
}
