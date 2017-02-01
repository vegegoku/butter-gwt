package com.progressoft.security.authentication.configuration.loader;

import com.progressoft.security.authentication.configuration.PropertiesAuthenticationConfigurationLoader;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AuthenticationConfigurationLoaderTest {

    @Test(expected = AuthenticationConfiguration.InvalidConfigurationProvidedException.class)
    public void givenEmptyConfigurationFile_whenLoadingConfiguration_shouldThrowException() throws Exception {
        new PropertiesAuthenticationConfigurationLoader("empty-configuration.properties").load();
    }

    @Test(expected = AuthenticationConfiguration.InvalidConfigurationProvidedException.class)
    public void givenConfigurationFileWithEmptyRootChainValue_whenLoadingConfigurationAndFetchTheRootChain_shouldThrowException() throws Exception {
        new PropertiesAuthenticationConfigurationLoader("invalid-configuration.properties").load().rootAuthenticationChain();
    }

    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationAndFetchTheRootChain_shouldReturnTheRootChain() throws Exception {
        assertNotNull(new PropertiesAuthenticationConfigurationLoader("authentication-config.properties").load().rootAuthenticationChain());
    }
    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationAndFetchTheDefaultTenant_shouldReturnTheDefaultTenant() throws Exception {
        assertNotNull(new PropertiesAuthenticationConfigurationLoader("authentication-config.properties").load().defaultTenant());
    }



}
