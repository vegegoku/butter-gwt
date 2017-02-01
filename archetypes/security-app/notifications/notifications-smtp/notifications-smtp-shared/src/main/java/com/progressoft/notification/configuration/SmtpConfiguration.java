package com.progressoft.notification.configuration;

public interface SmtpConfiguration {

    String host();
    int port();
    String userName();
    String secret();
    String systemEmail();
    boolean authenticationRequired();

    class InvalidSmtpHostProvidedException extends RuntimeException{}

    class InvalidSmtpPortProvidedException extends RuntimeException{}

    class InvalidSmtpAuthenticationRequiredProvidedException extends RuntimeException{}

    class InvalidSmtpUserProvidedException extends RuntimeException{}

    class InvalidSmtpSecretProvidedException extends RuntimeException{}

    class InvalidSmtpSystemEmailProvidedException extends RuntimeException{}
}
