package com.progressoft.security.authentication.server.registry;

import com.progressoft.security.authentication.server.AuthenticationConfigurator;
import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfigurationLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class AuthenticationConfiguratorTest {

    @Test(expected = AuthenticationConfigurator.InvalidConfigurationLoaders.class)
    public void givenAuthenticationConfigurator_whenLoadersIsNull_thenShouldThrowException() throws Exception {
        new AuthenticationConfigurator(null);
    }

    @Test(expected = AuthenticationConfigurator.InvalidConfigurationLoaders.class)
    public void givenAuthenticationConfigurator_whenLoadersIsEmpty_thenShouldThrowException() throws Exception {
        new AuthenticationConfigurator(new HashSet<>());
    }

    @Test(expected = AuthenticationConfigurator.MoreThanOneAuthenticationLoaderFound.class)
    public void givenAuthenticationConfigurator_whenMoreThanOneLoaderExist_thenShouldThrowException() throws Exception {
        new AuthenticationConfigurator(new HashSet<AuthenticationConfigurationLoader>(){{
            add(() -> null);

            add(() -> null);
        }});
    }

    @Test
    public void givenAuthenticationConfigurator_whenLoadersHasSingleLoader_thenServerAuthenticationContextShouldHoldThatLoader() throws Exception {
        new AuthenticationConfigurator(new HashSet<AuthenticationConfigurationLoader>(){{
            add(() -> new AuthenticationConfiguration() {
                @Override
                public String rootAuthenticationChain() {
                    return "ROOT_CHAIN";
                }

                @Override
                public String defaultTenant() {
                    return "something";
                }
            });
        }});

        assertNotNull(ServerAuthenticationContext.configuration());
    }


}
