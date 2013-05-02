package co.s4n.smtp;

import co.s4n.smtp.exceptions.InvalidDNSException;
import co.s4n.smtp.exceptions.InvalidEmailAddress;
import co.s4n.smtp.exceptions.SMTPPermanentFailureException;
import co.s4n.smtp.exceptions.SMTPPersistentTemporaryFailureException;
import co.s4n.smtp.exceptions.SMTPServerConnectionException;
import junit.framework.TestCase;

public class TestMailService extends TestCase {

	public void testSend( ) {
		MailService mailService = new MailService( );
		try 
		{
			mailService.send( new EmailVO( "yujikiriki@enterpisy.co", "josegarcia@s4n.co", "aaaa", "Prueba HTML", "<html><body><p>Hola <b>papi</b></p></body></html>" ) );
		}
		catch ( SMTPPermanentFailureException e ) {
			e.printStackTrace( );
			fail( );
		}
		catch ( SMTPPersistentTemporaryFailureException e ) {
			e.printStackTrace( );
			fail( );
		}
		catch ( SMTPServerConnectionException e ) {
			e.printStackTrace( );
			fail( );
		}
		catch ( InvalidDNSException e ) {
			e.printStackTrace( );
			fail( );
		}
		catch ( InvalidEmailAddress e ) {
			e.printStackTrace( );
			fail( );
		}
	}

}
