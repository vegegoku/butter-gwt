package com.progressoft.security.otp.server.usecase;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.progressoft.notification.configuration.SmtpConfigurationContext;
import com.progressoft.security.authentication.configuration.PropertiesOtpConfigurationLoader;
import com.progressoft.security.otp.shared.extension.OtpConfiguration;
import com.progressoft.security.repository.FakePrincipal;
import com.progressoft.security.repository.InMemoryUserRepository;
import com.progressoft.security.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SendOtpUseCaseTest {


    private SendOtpUseCase sendOtpUseCase;
    private OtpConfiguration configuration;
    private UserRepository userRepository;
    private SimpleSmtpServer server;

    @Before
    public void setUp() throws Exception {
        configuration = new PropertiesOtpConfigurationLoader("otp-configure.properties").load();
        userRepository = new InMemoryUserRepository();
        sendOtpUseCase = new SendOtpUseCase(userRepository, configuration);
        server = SimpleSmtpServer.start(3025);
    }

    @Test(expected = SendOtpUseCase.InvalidUserRepositoryProvidedException.class)
    public void creatingSendOtpUseCaseWithNullUserRepository_thenShouldThrowException() throws Exception {
        new SendOtpUseCase(null, configuration);
    }

    @Test(expected = SendOtpUseCase.InvalidOtpConfigurationProvidedException.class)
    public void creatingSendOtpUseCaseWithNullConfiguration_thenShouldThrowException() throws Exception {
        new SendOtpUseCase(userRepository, null);
    }

    @Test
    public void givenSendOtpUseCase_thenCanSendOtpForPrincipal() throws Exception {
        sendOtpUseCase.sendOtp(new FakePrincipal("FOUND_USER", "TENANT"));
    }

    @Test(expected = SendOtpUseCase.CantSendOtpForNullPrincipalException.class)
    public void givenSendOtpUseCase_whenSendOtpForNullPrincipal_thenShouldThrowException() throws Exception {
        sendOtpUseCase.sendOtp(null);
    }

    @Test(expected = SendOtpUseCase.UserNotFoundException.class)
    public void givenSendOtpUseCase_whenSendOtpForPrincipalThatIsNotFound_thenShouldThrowException() throws Exception {
        sendOtpUseCase.sendOtp(new FakePrincipal("NOT_FOUND_USER", "TENANT"));
    }

    @Test
    public void givenSendOtpUseCase_whenSendOtpForPrincipalOfExistingUser_thenSenddOtpHolderShouldNotBeNull() throws Exception {
        assertNotNull(sendOtpUseCase.sendOtp(new FakePrincipal("FOUND_USER", "TENANT")));
    }

    @Test
    public void givenSendOtpUseCase_whenSendOtpForPrincipalOfExistingUser_thenAnOtpEmailShouldBeSent() throws Exception {
        SmtpConfigurationContext.configure("smtp-configuration.properties");
        sendOtpUseCase.sendOtp(new FakePrincipal("FOUND_USER", "TENANT"));
        SmtpMessage message = (SmtpMessage) server.getReceivedEmail().next();
        assertEquals(1, server.getReceivedEmailSize());
        assertEquals("Verification code", message.getHeaderValue("Subject"));
        assertEquals("found.user@mail.com", message.getHeaderValue("To"));
        assertEquals("system@mail.com", message.getHeaderValue("From"));
    }

    @After
    public void tearDown() throws Exception {
        if (Objects.isNull(server))
            return;
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
