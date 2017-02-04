package com.progressoft.notification.smtp;


import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.validator.routines.EmailValidator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Collection;
import java.util.LinkedList;

import static com.progressoft.notification.configuration.SmtpConfigurationContext.configure;
import static java.util.Objects.isNull;

public class EmailMessage {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(EmailMessage.class);

    private final EmailValidator emailValidator = EmailValidator.getInstance(true, true);
    private final String subject;
    private final String body;
    private final LinkedList<String> failedRecipients = new LinkedList<>();
    private Email email = new HtmlEmail();

    public EmailMessage(String subject, String body) {
        if (isNull(subject) || subject.trim().isEmpty())
            throw new InvalidSubjectProvidedException();
        if (isNull(body) || body.trim().isEmpty())
            throw new InvalidBodyProvidedException();
        this.subject = subject;
        this.body = body;
        configureEmail();
    }

    private void configureEmail() {
        email.setHostName(configure().host());
        email.setSmtpPort(configure().port());
        if (configure().authenticationRequired())
            email.setAuthenticator(new PasswordAuthenticator(configure().userName(), configure().secret()));
    }

    public Collection<String> send(String from, Collection<String> to) {
        if (!emailValidator.isValid(from))
            throw new InvalidFromAddressException();
        addRecipients(to);

        if (failedRecipients.size() < to.size())
            sendMail(from);
        return copy(failedRecipients);
    }

    private void addRecipients(Collection<String> to) {
        to.stream().filter(this::validateRecipient).forEach(t -> {
            try {
                email.addTo(t);
            } catch (EmailException e) {
                LOGGER.debug("failed to add to recipient: ", e);
                failedRecipients.add(t);
            }
        });
    }

    private void sendMail(String from) {
        try {
            email.setFrom(from);
            email.setMsg(body);
            email.setSubject(subject);
            email.send();
        } catch (EmailException e) {
            LOGGER.error("failed to send email: ", e);
            throw new FailedToSendEmailException();
        }
    }

    private boolean validateRecipient(String recipient) {
        if (!emailValidator.isValid(recipient)) {
            failedRecipients.add(recipient);
            return false;
        }
        return true;
    }

    private Collection<String> copy(LinkedList<String> sourceList) {
        LinkedList<String> copy = new LinkedList<>();
        sourceList.forEach(copy::add);
        return copy;
    }

    private class PasswordAuthenticator extends Authenticator {
        private final PasswordAuthentication authentication;

        private PasswordAuthenticator(String userName, String password) {
            this.authentication = new PasswordAuthentication(userName, password);
        }

        public PasswordAuthentication getAuthentication() {
            return authentication;
        }
    }

    public class InvalidSubjectProvidedException extends RuntimeException {
    }

    public class InvalidBodyProvidedException extends RuntimeException {
    }

    public class InvalidFromAddressException extends RuntimeException {
    }

    public class FailedToSendEmailException extends RuntimeException {
    }
}
