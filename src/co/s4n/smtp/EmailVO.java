package co.s4n.smtp;

public class EmailVO {
	
	private final String from;
	private final String to;
	private final String cc;
	private final String subject;
	private final String message;
	
	public EmailVO( String from, String to, String cc, String subject, String message ) {
		super( );
		this.from = from;
		this.to = to;
		this.message = message;
		this.subject = subject;
		this.cc = cc;
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
	
	public String cc( ) {
		return cc;
	}

	public Boolean hasCC( ) {
		return ( cc != null && cc != "" );
	}
}
