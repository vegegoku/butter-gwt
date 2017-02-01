package com.progressoft.security.authentication.configuration;

import com.progressoft.security.otp.shared.extension.OtpConfiguration;
import org.akab.engine.core.configurations.ConfigurationLoader;
import org.apache.commons.configuration.Configuration;

class PropertiesOtpConfiguration implements OtpConfiguration {

    static final String DEFAULT_CONFIG= "otp-configuration.properties";
    private static final String TIMEOUT = "otp.timeout";
    private static final String DIGITS_COUNT = "otp.digits.count";
    private Configuration configuration;

    public PropertiesOtpConfiguration() {
        configuration=new ConfigurationLoader(DEFAULT_CONFIG).load();
    }

    public PropertiesOtpConfiguration(String fileName) {
        configuration=new ConfigurationLoader(fileName).load();
    }

    public PropertiesOtpConfiguration(String configLocation, String fileName) {
        configuration=new ConfigurationLoader(configLocation, fileName).load();
    }

    @Override
    public int digitsCount() {
        if(configuration.getInt(DIGITS_COUNT, 4)<4)
            throw new InvalidDigitsCountProvidedException();
        return configuration.getInt(DIGITS_COUNT,4);
    }

    @Override
    public int timeout() {
        if(configuration.getInt(TIMEOUT, 30)<0)
            throw new InvalidTimeoutProvidedException();
        return configuration.getInt(TIMEOUT, 30);
    }
}
