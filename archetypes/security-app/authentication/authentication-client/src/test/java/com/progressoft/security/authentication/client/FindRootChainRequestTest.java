package com.progressoft.security.authentication.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.client.requests.ApplyAuthenticationContributionsRequest;
import com.progressoft.security.authentication.client.requests.FindRootAuthenticationChainRequest;
import com.progressoft.security.authentication.configuration.PropertiesAuthenticationConfigurationLoader;
import com.progressoft.security.authentication.server.ServerAuthenticationContext;
import org.akab.engine.app.test.ModuleTestCase;
import org.akab.engine.core.api.client.InitialTaskRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class FindRootChainRequestTest extends ModuleTestCase {

    private AuthenticationPresenterSpy presenterSpy;
    private AuthenticationViewSpy viewSpy;

    @Override
    protected void setUp() {
        testModule.configureModule(new AuthenticationModuleConfiguration() {
            @Override
            public void registerInitialTasks(InitialTaskRegistry registry) {
            }
        });
        testModule.configureModule(new AuthenticationTestModuleConfiguration());
        testModule.replacePresenter(AuthenticationPresenter.class.getCanonicalName(), () -> {
            presenterSpy = new AuthenticationPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AuthenticationPresenter.class.getCanonicalName(), () -> {
            viewSpy = new AuthenticationViewSpy();
            return viewSpy;
        });

    }

    @Test
    public void givenInvalidConfiguration_WhenFindRootAuthenticationChain_ThenShouldShowErrorMessage()
            throws Exception {
        ServerAuthenticationContext.withConfigurationLoader(new PropertiesAuthenticationConfigurationLoader("invalidPath"));
        new FindRootAuthenticationChainRequest().send();

        assertNull(presenterSpy.getRootChain());
        assertEquals("Authentication failed", viewSpy.getErrorMessage());
    }

    @Test
    public void givenValidConfiguration_WhenFindRootAuthenticationChain_ThenShouldStartAuthentication()
            throws Exception {
        new ApplyAuthenticationContributionsRequest().send();
        new FindRootAuthenticationChainRequest().send();

        assertNotNull(presenterSpy.getRootChain());
    }
}
