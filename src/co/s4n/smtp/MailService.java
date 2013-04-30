package co.s4n.smtp;

import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.s4n.smtp.exceptions.InvalidDNSException;
import co.s4n.smtp.exceptions.InvalidEmailAddress;
import co.s4n.smtp.exceptions.SMTPPermanentFailureException;
import co.s4n.smtp.exceptions.SMTPPersistentTemporaryFailureException;
import co.s4n.smtp.exceptions.SMTPServerConnectionException;

import com.sun.mail.smtp.SMTPTransport;

public class MailService {
	
//-----------------------
//      Services 
//-----------------------

	public void send( EmailVO message ) throws SMTPPermanentFailureException, SMTPPersistentTemporaryFailureException, SMTPServerConnectionException, InvalidDNSException, InvalidEmailAddress {

		Vector< URLName > mxServers = DnsResolver.getMXRecordsForHost( chopHostNameFrom( message.to( ) ) );
		Session session = MailSessionFactory.buildSession( mxServers.get( 0 ), chopHostNameFrom( message.from( ) ) );
		MimeMessage mimeMessage = createNewMimeMessage( session, message );
		InternetAddress[ ] recipient = verifyEmail( message );

		try 
		{
			URLName outgoingMailServer = mxServers.get( 0 );
			Properties props = session.getProperties( );
			props.put( "mail.smtp.from", message.from( ) );
			connectAndSend( mimeMessage, recipient, session.getTransport( outgoingMailServer ) );
		}
		catch ( MessagingException me ) 
		{
			handleDeliveryNotificationStatus( me );
		}
	}

	
//-----------------------
//   Private methods
//-----------------------
	
	/**
	 * Con base en el tipo de error retornado por el servidor SMTP, se generan excepciones distintas 
	 * @param me
	 * @throws SMTPPermanentFailureException
	 * @throws SMTPPersistentTemporaryFailureException
	 */
	private void handleDeliveryNotificationStatus( MessagingException me ) throws SMTPPermanentFailureException, SMTPPersistentTemporaryFailureException {
		String exMessage = me.getMessage( );
		if ( '5' == exMessage.charAt( 0 ) )
			throw new SMTPPermanentFailureException( exMessage, me );
		else if ( '4' == exMessage.charAt( 0 ) )
			throw new SMTPPersistentTemporaryFailureException( exMessage, me );
		else {
			System.out.println( "=====================================" );
			System.out.println( exMessage );
			System.out.println( "=====================================" );
			throw new RuntimeException( me );
		}
	}

	/**
	 * @param mimeMessage
	 * @param recipient
	 * @param transport
	 * @return TRUE si el correo fue enviado con éxito. FALSE para cualquier otro caso.
	 * @throws SMTPServerConnectionException
	 * @throws MessagingException
	 * @throws SMTPPersistentTemporaryFailureException 
	 * @throws SMTPPermanentFailureException 
	 */
	private void connectAndSend( MimeMessage mimeMessage, InternetAddress[ ] recipient, Transport transport ) throws SMTPServerConnectionException, MessagingException, SMTPPermanentFailureException, SMTPPersistentTemporaryFailureException {
		try 
		{
			transport.connect( );
			transport.sendMessage( mimeMessage, recipient );
			if ( transport instanceof SMTPTransport ) {
				String response = ( ( SMTPTransport ) transport ).getLastServerResponse( );
				if ( response != null )
					System.out.println( "Respuesta del servidor: " + response );
			}
		}
		catch ( MessagingException me ) 
		{
			handleDeliveryNotificationStatus( me );
			throw new SMTPServerConnectionException( "No se pudo establecer conexión con el servidor SMTP " + " ", me );
		}
		finally 
		{
			if ( transport != null ) {
				transport.close( );
				transport = null;
			}
		}
	}

	/**
	 * Verifica la validez de una dirección de correo electrónico
	 * @param message
	 * @return La dirección valida
	 * @throws InvalidEmailAddress
	 */
	private InternetAddress[ ] verifyEmail( EmailVO message ) throws InvalidEmailAddress {
		try 
		{
			return new InternetAddress[] { new InternetAddress( message.to( ) ) };
		}
		catch ( AddressException e ) 
		{
			throw new InvalidEmailAddress( "La dirección de correo electrónico " + message.to( ) + " es inválida.", e );
		}
	}

	/**
	 * Obtiene el nombre de host de una dirección de correo electrónico
	 * @param emailAddress
	 * @return El nombre de dominio
	 */
	private String chopHostNameFrom( String emailAddress ) {
		return emailAddress.substring( emailAddress.lastIndexOf( "@" ) + 1 );
	}

	/**
	 * Traduce el VO a un mensaje MIME válido.
	 * @param session
	 * @param message
	 * @return Un mensaje MIME
	 * @throws InvalidEmailAddress 
	 */
	private MimeMessage createNewMimeMessage( Session session, EmailVO message ) throws InvalidEmailAddress {
		try
		{
			MimeMessage mime = new MimeMessage( session );
			mime.setFrom( new InternetAddress( message.from( ) ) );
			mime.addRecipient( Message.RecipientType.TO, new InternetAddress( message.to( ) ) );
			mime.setSubject( message.subject( ) );
			mime.setText( message.message( ) );
			return mime;
		}
		catch( AddressException e ) 
		{
			throw new InvalidEmailAddress( "La dirección de correo electrónico " + message.to( ) + " o "  + " es inválida.", e );
		}
		catch( MessagingException e )
		{
			throw new RuntimeException( e );
		}
	}
}
