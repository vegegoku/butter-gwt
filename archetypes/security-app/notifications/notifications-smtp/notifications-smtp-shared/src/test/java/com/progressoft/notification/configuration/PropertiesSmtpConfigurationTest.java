package com.progressoft.notification.configuration;

import org.junit.Test;
import static org.junit.Assert.*;

public class PropertiesSmtpConfigurationTest {


    private SmtpConfiguration load(String fileName){
        return SmtpConfigurationContext.configure(fileName);
    }

    @Test
    public void givenEmptySmtpConfigurationFile_whenLoadingConfigurations_thenDefaultConfigurationsShouldBeLoaded()
            throws Exception {
        SmtpConfiguration configuration=SmtpConfigurationContext.configure("empty-smtp-configuration.properties");
        assertEquals(PropertiesSmtpConfiguration.DEFAULT_HOST, configuration.host());
        assertEquals(PropertiesSmtpConfiguration.DEFAULT_PORT, configuration.port());
        assertFalse(configuration.authenticationRequired());
        assertEquals("", configuration.userName());
        assertEquals("", configuration.secret());
    }

    @Test(expected = SmtpConfiguration.InvalidSmtpHostProvidedException.class)
    public void givenSmtpConfigurationFileWithInvalidHost_whenLoadingConfigurations_thenShouldThrowException()
            throws Exception {
        load("invalid-smtp-host-configuration.properties").host();
    }

    @Test(expected = SmtpConfiguration.InvalidSmtpPortProvidedException.class)
    public void givenSmtpConfigurationFileWithInvalidPort_whenLoadingConfigurations_thenShouldThrowException()
            throws Exception {
        load("invalid-smtp-port-configuration.properties").port();
    }

    @Test(expected = SmtpConfiguration.InvalidSmtpSystemEmailProvidedException.class)
    public void givenSmtpConfigurationFileWithInvalidSystemEmail_whenLoadingConfigurations_thenShouldThrowException()
            throws Exception {
        load("invalid-smtp-system-email-configuration.properties").systemEmail();
    }

    @Test(expected = SmtpConfiguration.InvalidSmtpAuthenticationRequiredProvidedException.class)
    public void givenSmtpConfigurationFileWithInvalidAuthenticationRequired_whenLoadingConfigurations_thenShouldThrowException()
            throws Exception {
        load("invalid-smtp-authentication-required-configuration.properties").authenticationRequired();
    }

    @Test(expected = SmtpConfiguration.InvalidSmtpUserProvidedException.class)
    public void givenSmtpConfigurationFileWithInvalidUserWhileAuthenticationRequiredIsTrue_whenLoadingConfigurations_thenShouldThrowException()
            throws Exception {
        load("invalid-smtp-user-configuration.properties").userName();
    }

    @Test
    public void givenSmtpConfigurationFileWithInvalidUserWhileAuthenticationRequiredIsFalse_whenLoadingConfigurations_thenShouldRetrieveUser()
            throws Exception {
        load("invalid-smtp-user-required-false-configuration.properties").userName();
    }

    @Test(expected = SmtpConfiguration.InvalidSmtpSecretProvidedException.class)
    public void givenSmtpConfigurationFileWithInvalidSecretWhileAuthenticationRequiredIsTrue_whenLoadingConfigurations_thenShouldThrowException()
            throws Exception {
        load("invalid-smtp-secret-configuration.properties").secret();
    }

    @Test
    public void givenSmtpConfigurationFileWithInvalidSecretWhileAuthenticationRequiredIsFalse_whenLoadingConfigurations_thenShouldRetrieveSecret()
            throws Exception {
        load("invalid-smtp-secret-required-false-configuration.properties").secret();
    }

    @Test
    public void givenValidSmtpConfigurationFile_whenLoadingConfigurations_thenConfigurationsShouldBeLoaded()
            throws Exception {
        SmtpConfiguration configuration=load("valid-smtp-configuration.properties");
        assertEquals("127.0.0.1", configuration.host());
        assertEquals(25, configuration.port());
        assertTrue(configuration.authenticationRequired());
        assertEquals("User", configuration.userName());
        assertEquals("Secret", configuration.secret());
        assertEquals("system@mail.com", configuration.systemEmail());
    }

}
