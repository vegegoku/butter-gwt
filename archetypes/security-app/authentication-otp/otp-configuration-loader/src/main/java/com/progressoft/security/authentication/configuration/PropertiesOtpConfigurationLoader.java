package com.progressoft.security.authentication.configuration;

import com.google.auto.service.AutoService;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import com.progressoft.security.otp.shared.extension.OtpConfiguration;
import com.progressoft.security.otp.shared.extension.OtpConfigurationLoader;

import static java.util.Objects.isNull;

@AutoService(OtpConfigurationLoader.class)
public class PropertiesOtpConfigurationLoader implements OtpConfigurationLoader {

    private String path;
    private OtpConfiguration configuration;

    public PropertiesOtpConfigurationLoader() {
        this.path = PropertiesOtpConfiguration.DEFAULT_CONFIG;
    }

    public PropertiesOtpConfigurationLoader(String path) {
        this.path = path;
    }

    @Override
    public OtpConfiguration load() {
        if(isNull(configuration))
            configuration=new PropertiesOtpConfiguration(path);
       return configuration;
    }

}