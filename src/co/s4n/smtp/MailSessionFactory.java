package co.s4n.smtp;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.URLName;

public class MailSessionFactory {

	static public Session buildSession( URLName hostname, String localhost ) {
		Properties mailSessionProps = System.getProperties( );
		mailSessionProps.put( "mail.smtp.host", hostname );
		mailSessionProps.put( "mail.smtp.localhost", localhost );
		mailSessionProps.put( "mail.mime.charset", "UTF-8" );
		mailSessionProps.put( "mail.smtp.connectiontimeout", 30000 );
		mailSessionProps.put( "mail.smtp.timeout", 30000 );
		return Session.getInstance( mailSessionProps );
	}
}
