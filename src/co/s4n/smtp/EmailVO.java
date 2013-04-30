package co.s4n.smtp;

public class EmailVO {
	
	private final String from;
	private final String to;
	private final String subject;
	private final String message;
	
	public EmailVO( String from, String to, String subject, String message ) {
		super( );
		this.from = from;
		this.to = to;
		this.message = message;
		this.subject = subject;
	}

	public String from( ) {
		return from;
	}

	public String to( ) {
		return to;
	}

	public String message( ) {
		return message;
	}
	
	public String subject( ) {
		return subject;
	}
}
