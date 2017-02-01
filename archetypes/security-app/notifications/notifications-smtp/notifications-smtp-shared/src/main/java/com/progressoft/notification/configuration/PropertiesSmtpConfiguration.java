package com.progressoft.notification.configuration;


import org.akab.engine.core.configurations.ConfigurationLoader;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConversionException;

import static java.util.Objects.*;

public class PropertiesSmtpConfiguration implements SmtpConfiguration {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 25;

    private static final String HOST = "smtp.host";
    private static final String PORT = "smtp.port";
    private static final String USER = "smtp.user";
    private static final String SECRET = "smtp.secret";
    private static final String SYSTEM_EMAIL = "smtp.system.email";
    private static final String AUTHENTICATION_REQUIRED = "smtp.authentication.required";

    private Configuration configuration;

    public PropertiesSmtpConfiguration(String fileName) {
        configuration = new ConfigurationLoader(fileName).load();
    }

    @Override
    public String host() {
        return validatedHost(configuration.getString(HOST, DEFAULT_HOST));
    }

    private String validatedHost(String host) {
        if(isNull(host) || host.trim().isEmpty())
            throw new InvalidSmtpHostProvidedException();
        return host;
    }

    @Override
    public int port() {
        try {
            return validatedPort(configuration.getInt(PORT, DEFAULT_PORT));
        }catch (ConversionException e){
            throw new InvalidSmtpPortProvidedException();
        }
    }

    private int validatedPort(int port) {
        if(isNull(port))
            throw new InvalidSmtpPortProvidedException();
        return port;
    }

    @Override
    public String userName() {
        if(authenticationRequired())
            return validatedUser(configuration.getString(USER, ""));
        return configuration.getString(USER, "");
    }

    private String validatedUser(String user) {
        if(isNull(user) || user.trim().isEmpty())
            throw new InvalidSmtpUserProvidedException();
        return user;
    }

    @Override
    public String secret() {
        if(authenticationRequired())
            return validatedSecret(configuration.getString(SECRET, ""));
        return configuration.getString(SECRET, "");
    }

    @Override
    public String systemEmail() {
        return validatedSystemEmail(configuration.getString(SYSTEM_EMAIL, ""));
    }

    private String validatedSystemEmail(String systemEmail) {
        if(isNull(systemEmail) || systemEmail.trim().isEmpty())
            throw new InvalidSmtpSystemEmailProvidedException();
        return systemEmail;
    }

    private String validatedSecret(String secret) {
        if(isNull(secret) || secret.trim().isEmpty())
            throw new InvalidSmtpSecretProvidedException();
        return secret;
    }

    @Override
    public boolean authenticationRequired() {
        try {
            return configuration.getBoolean(AUTHENTICATION_REQUIRED, false);
        }catch (ConversionException e){
            throw new InvalidSmtpAuthenticationRequiredProvidedException();
        }
    }
}
