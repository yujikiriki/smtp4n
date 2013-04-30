package co.s4n.smtp.exceptions;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 8148467631177147490L;

	public TechnicalException( String message, Throwable cause ) {
		super( message, cause );
	}
}
