package com.progressoft.notification.configuration;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class SmtpConfigurationContext {
    private static final Map<String, SmtpConfiguration> configurations = new HashMap<>();
    private static final String DEFAULT_CONFIG = "smtp-configuration.properties";

    private SmtpConfigurationContext() {
    }

    public static SmtpConfiguration configure() {
        return configure(DEFAULT_CONFIG);
    }

    public static SmtpConfiguration configure(String configurationFile) {
        if (isNull(configurations.get(configurationFile)))
            loadNewConfiguration(configurationFile);
        return configurations.get(configurationFile);
    }

    private static void loadNewConfiguration(String configurationFile) {
        configurations.put(configurationFile, new PropertiesSmtpConfiguration(configurationFile));
    }
}
