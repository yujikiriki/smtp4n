package co.s4n.smtp;

import java.util.LinkedList;
import java.util.List;

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
			List< String > ccs = new LinkedList<>( );
			ccs.add( "yujikiriki@seven4n.com" );
			ccs.add( "josegarcia@seven4n.com" );
			mailService.send( new EmailVO( "yujikiriki@enterpisy.co", "yujikiriki@s4n.co", ccs, "Prueba HTML", "<html><body><p>Hola <b>mundo</b></p></body></html>" ) );
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
