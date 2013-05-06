package co.s4n.smtp;

import java.util.List;

public class EmailVO {
	
	private final String from;
	private final String to;
	private final List< String > cc;
	private final String subject;
	private final String message;
	
	public EmailVO( String from, String to, List< String > cc, String subject, String message ) {
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
	
	public List< String > cc( ) {
		return cc;
	}

	public Boolean hasCC( ) {
		return ( cc != null || !cc.isEmpty( ) );
	}
}
