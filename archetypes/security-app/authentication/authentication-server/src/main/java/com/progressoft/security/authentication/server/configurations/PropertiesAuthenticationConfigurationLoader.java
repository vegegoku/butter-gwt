package com.progressoft.security.authentication.server.configurations;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertiesAuthenticationConfigurationLoader implements AuthenticationConfigurationLoader {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(PropertiesAuthenticationConfigurationLoader.class);

    private String path;
    private Properties props=new Properties();

    public PropertiesAuthenticationConfigurationLoader() {
        this.path="authentication-config.properties";
    }

    public PropertiesAuthenticationConfigurationLoader(String path) {
        this.path = path;
    }

    private class PropertiesAuthenticationConfiguration implements AuthenticationConfiguration {

        private final String rootChain;

        private PropertiesAuthenticationConfiguration(String rootChain) {
            if(Objects.isNull(rootChain) || rootChain.isEmpty())
                throw new AuthenticationConfigurationLoader.InvalidConfigurationProvidedException();
            this.rootChain = rootChain;
        }

        @Override
        public String rootAuthenticationChain() {
            return rootChain;
        }
    }

    @Override
    public AuthenticationConfiguration load() {
        loadPropertiesFile();
        if(props.isEmpty())
            throw new AuthenticationConfigurationLoader.InvalidConfigurationProvidedException();

        return new PropertiesAuthenticationConfiguration(props.getProperty(AuthenticationConfiguration.ROOT_CHAIN));
    }

    private Properties loadPropertiesFile() {
        try(InputStream resourceStream = getClassLoader().getResourceAsStream(path)) {
            if(Objects.isNull(resourceStream))
                throw new AuthenticationConfigurationLoader.InvalidConfigurationProvidedException();
            props.load(resourceStream);
            return props;
        } catch (IOException e) {
            LOGGER.error("Could not find root authentication chain", e);
            return new Properties();
        }

    }

    private ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
