package com.progressoft.security.authentication.configuration;

import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import org.akab.engine.core.configurations.ConfigurationLoader;
import org.apache.commons.configuration.Configuration;

class PropertiesAuthenticationConfiguration implements AuthenticationConfiguration {

    static final String DEFAULT_CONFIG= "authentication-config.properties";
    private static final String ROOT_CHAIN = "root.chain";
    private static final String DEFAULT_TENANT = "default.tenant";
    private Configuration configuration;

    public PropertiesAuthenticationConfiguration() {
        configuration=new ConfigurationLoader(DEFAULT_CONFIG).load();
    }

    public PropertiesAuthenticationConfiguration(String fileName) {
        configuration=new ConfigurationLoader(fileName).load();
    }

    public PropertiesAuthenticationConfiguration(String configLocation, String fileName) {
        configuration=new ConfigurationLoader(configLocation, fileName).load();
    }

    @Override
    public String rootAuthenticationChain() {
        return configuration.getString(ROOT_CHAIN);
    }

    @Override
    public String defaultTenant() {
        return configuration.getString(DEFAULT_TENANT, "");
    }
}
