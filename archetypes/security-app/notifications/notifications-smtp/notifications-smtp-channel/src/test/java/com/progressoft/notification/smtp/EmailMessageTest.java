package com.progressoft.notification.smtp;

import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpMessage;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import com.progressoft.notification.configuration.SmtpConfiguration;
import com.progressoft.notification.configuration.SmtpConfigurationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EmailMessageTest {

    private SmtpServer server;
    private SmtpConfiguration configuration;

    @Before
    public void setUp() throws Exception {
        ServerOptions serverOptions = new ServerOptions();
        serverOptions.port = 2025;
        serverOptions.threaded = false;
        server = SmtpServerFactory.startServer();
        configuration = SmtpConfigurationContext.configure("smtp-configuration.properties");
    }

    private EmailMessage make(String subject, String body) {
        return new EmailMessage(subject, body);
    }

    @Test
    public void canCreateMessage() throws Exception {
        make("subject", "body");
    }

    @Test(expected = EmailMessage.InvalidSubjectProvidedException.class)
    public void givenAMessage_whenSubjectIsNull_thenShouldThrowException() throws Exception {
        make(null, "body");
    }

    @Test(expected = EmailMessage.InvalidSubjectProvidedException.class)
    public void givenAMessage_whenSubjectEmpty_thenShouldThrowException() throws Exception {
        make("", "body");
    }

    @Test(expected = EmailMessage.InvalidSubjectProvidedException.class)
    public void givenAMessage_whenSubjectIsSpaces_thenShouldThrowException() throws Exception {
        make("   ", "body");
    }

    @Test(expected = EmailMessage.InvalidBodyProvidedException.class)
    public void givenAMessage_whenBodyIsNull_thenShouldThrowException() throws Exception {
        make("subject", null);
    }

    @Test(expected = EmailMessage.InvalidBodyProvidedException.class)
    public void givenAMessage_whenBodyIsEmpty_thenShouldThrowException() throws Exception {
        make("subject", "");
    }

    @Test(expected = EmailMessage.InvalidBodyProvidedException.class)
    public void givenAMessage_whenBodyIsSpaces_thenShouldThrowException() throws Exception {
        make("subject", "   ");
    }

    @Test(expected = EmailMessage.InvalidFromAddressException.class)
    public void givenEmailMessage_whenSendMessageWithInvalidFrom_thenShouldThrowException() throws Exception {
        make("subject", "body").send("invalidFromEmail", new ArrayList<String>() {{
            add("to");
        }});
    }

    @Test
    public void givenEmailMessage_whenSendMessageWithInvalidTo_thenShouldReturnFailedRecipientErrorList()
            throws Exception {
        Collection<String> failedRecipients = make("subject", "body").send("me@test.com", new ArrayList<String>() {{
            add("invalidToEmail");
            add("anotherInvalidToEmail");
        }});
        assertTrue(failedRecipients.contains("invalidToEmail"));
        assertTrue(failedRecipients.contains("anotherInvalidToEmail"));
    }

    @Test
    public void givenEmailMessage_whenSendMessageWithFromValidEmailToValidEmail_thenEmailShouldBeSentByTheServer()
            throws Exception {
        Collection<String> failedRecipients =
                make("subject", "message body goes here").send("me@test.com", new ArrayList<String>() {{
                    add("someOneElse@test.com");
                }});
        SmtpMessage message = (SmtpMessage) server.getReceivedEmail().next();
        assertTrue(failedRecipients.isEmpty());
        assertEquals(1, server.getReceivedEmailSize());
        assertTrue(message.getBody().contains("message body goes here"));
        assertEquals("subject", message.getHeaderValue("Subject"));
        assertEquals("me@test.com", message.getHeaderValue("From"));
        assertEquals("someOneElse@test.com", message.getHeaderValue("To"));
    }

    @Test
    public void givenEmailMessage_whenSendMessageWithFromValidEmailToListOfValidEmails_thenEmailsShouldBeSentByTheServer()
            throws Exception {
        Collection<String> failedRecipients =
                make("subject", "message body goes here").send("me@test.com", new ArrayList<String>() {{
                    add("someOneElse@test.com");
                    add("anotherOne@test.com");
                }});
        Iterator<SmtpMessage> iterator = server.getReceivedEmail();
        SmtpMessage firstMessage = iterator.next();

        assertTrue(failedRecipients.isEmpty());
        assertEquals(1, server.getReceivedEmailSize());
        assertTrue(firstMessage.getBody().contains("message body goes here"));
        assertEquals("subject", firstMessage.getHeaderValue("Subject"));
        assertEquals("me@test.com", firstMessage.getHeaderValue("From"));
        assertEquals("someOneElse@test.com, anotherOne@test.com", firstMessage.getHeaderValue("To"));
    }

    @Test
    public void givenEmailMessage_whenSendMessageWithFromValidEmailToListOfValidEmailsAndInvalidEmail_thenEmailsShouldBeSentByTheServerAndFailedRecipientShouldNotBeEmpty()
            throws Exception {
        Collection<String> failedRecipients =
                make("subject", "message body goes here").send("me@test.com", new ArrayList<String>() {{
                    add("someOneElse@test.com");
                    add("anotherOne@test.com");
                    add("invalidEmail");
                }});
        Iterator<SmtpMessage> iterator = server.getReceivedEmail();
        SmtpMessage firstMessage = iterator.next();

        assertFalse(failedRecipients.isEmpty());
        assertTrue(failedRecipients.contains("invalidEmail"));
        assertEquals(1, server.getReceivedEmailSize());
        assertTrue(firstMessage.getBody().contains("message body goes here"));
        assertEquals("subject", firstMessage.getHeaderValue("Subject"));
        assertEquals("me@test.com", firstMessage.getHeaderValue("From"));
        assertEquals("someOneElse@test.com, anotherOne@test.com", firstMessage.getHeaderValue("To"));
    }

    @After
    public void tearDown() throws Exception {
        try {
            closeServer();
        } catch (Exception e) {
            closeServer();
        }
    }

    private synchronized void closeServer() {
        while (!server.isStopped())
            server.stop();
    }
}
