package com.progressoft.security.authentication.configuration.loader;

import com.progressoft.security.authentication.configuration.PropertiesAuthenticationConfigurationLoader;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AuthenticationConfigurationLoaderTest {

    @Test(expected = AuthenticationConfigurationLoader.InvalidConfigurationProvidedException.class)
    public void givenNonExistConfigurationFile_whenCallingLoad_shouldThrowException() throws Exception {
        new PropertiesAuthenticationConfigurationLoader("this-file-does-not-exists.properties").load();
    }

    @Test(expected = AuthenticationConfigurationLoader.InvalidConfigurationProvidedException.class)
    public void givenEmptyConfigurationFile_whenLoadingConfiguration_shouldThrowException() throws Exception {
        new PropertiesAuthenticationConfigurationLoader("empty-configuration.properties").load();
    }

    @Test(expected = AuthenticationConfigurationLoader.InvalidConfigurationProvidedException.class)
    public void givenConfigurationFileWithEmptyRootChainValue_whenLoadingConfigurationAndFetchTheRootChain_shouldThrowException() throws Exception {
        new PropertiesAuthenticationConfigurationLoader("invalid-configuration.properties").load().rootAuthenticationChain();
    }

    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationAndFetchTheRootChain_shouldReturnTheRootChain() throws Exception {
        assertNotNull(new PropertiesAuthenticationConfigurationLoader("authentication-config.properties").load().rootAuthenticationChain());
    }
    @Test
    public void givenValidConfigurationFile_whenLoadingConfigurationAndFetchThedefaultTenant_shouldReturnTheDefaultTenant() throws Exception {
        assertNotNull(new PropertiesAuthenticationConfigurationLoader("authentication-config.properties").load().defaultTenant());
    }



}
