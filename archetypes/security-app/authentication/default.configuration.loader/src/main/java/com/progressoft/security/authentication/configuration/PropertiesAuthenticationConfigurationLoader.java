package com.progressoft.security.authentication.configuration;

import com.google.auto.service.AutoService;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@AutoService(AuthenticationConfigurationLoader.class)
public class PropertiesAuthenticationConfigurationLoader implements AuthenticationConfigurationLoader {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(PropertiesAuthenticationConfigurationLoader.class);
    private static final String ROOT_CHAIN = "root.chain";

    private String path;
    private Properties props = new Properties();
    private AuthenticationConfiguration configuration;

    public PropertiesAuthenticationConfigurationLoader() {
        this.path = "authentication-config.properties";
    }

    public PropertiesAuthenticationConfigurationLoader(String path) {
        this.path = path;
    }

    private class PropertiesAuthenticationConfiguration implements AuthenticationConfiguration {

        private final String rootChain;

        private PropertiesAuthenticationConfiguration(String rootChain) {
            if (Objects.isNull(rootChain) || rootChain.isEmpty())
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
        if (Objects.isNull(configuration))
            loadConfiguration();
        return configuration;
    }

    private void loadConfiguration() {
        loadPropertiesFile();
        if (props.isEmpty())
            throw new AuthenticationConfigurationLoader.InvalidConfigurationProvidedException();
        configuration = new PropertiesAuthenticationConfiguration(props.getProperty(ROOT_CHAIN));
    }

    private Properties loadPropertiesFile() {
        try (InputStream resourceStream = getClassLoader().getResourceAsStream(path)) {
            if (Objects.isNull(resourceStream))
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
