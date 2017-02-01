package com.progressoft.notification.smtp;

import com.dumbster.smtp.MailMessage;
import com.dumbster.smtp.ServerOptions;
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

public class EmailMessageTest {

    private SmtpServer server;
    private SmtpConfiguration configuration;

    @Before
    public void setUp() throws Exception {
        ServerOptions serverOptions = new ServerOptions();
        serverOptions.port = 2025;
        serverOptions.threaded = false;
        server = SmtpServerFactory.startServer(serverOptions);
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

        MailMessage message = server.getMessages()[0];
        assertTrue(failedRecipients.isEmpty());
        assertEquals(1, server.getEmailCount());
        assertTrue(message.getBody().contains("message body goes here"));
        assertEquals("subject", message.getHeaderValues("Subject")[0]);
        assertEquals("me@test.com", message.getHeaderValues("From")[0]);
        assertEquals("someOneElse@test.com", message.getHeaderValues("To")[0]);
    }

    @Test
    public void givenEmailMessage_whenSendMessageWithFromValidEmailToListOfValidEmails_thenEmailsShouldBeSentByTheServer()
            throws Exception {
        Collection<String> failedRecipients =
                make("subject", "message body goes here").send("me@test.com", new ArrayList<String>() {{
                    add("someOneElse@test.com");
                    add("anotherOne@test.com");
                }});
        MailMessage message = server.getMessages()[0];

        assertTrue(failedRecipients.isEmpty());
        assertEquals(1, server.getEmailCount());
        assertTrue(message.getBody().contains("message body goes here"));
        assertEquals("subject", message.getHeaderValues("Subject")[0]);
        assertEquals("me@test.com", message.getHeaderValues("From")[0]);
        assertEquals("someOneElse@test.com, anotherOne@test.com", message.getHeaderValues("To")[0]);
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
        MailMessage message = server.getMessages()[0];

        assertFalse(failedRecipients.isEmpty());
        assertTrue(failedRecipients.contains("invalidEmail"));
        assertEquals(1, server.getEmailCount());
        assertTrue(message.getBody().contains("message body goes here"));
        assertEquals("subject", message.getHeaderValues("Subject")[0]);
        assertEquals("me@test.com", message.getHeaderValues("From")[0]);
        assertEquals("someOneElse@test.com, anotherOne@test.com", message.getHeaderValues("To")[0]);
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
