package com.progressoft.security.authentication.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.progressoft.security.authentication.client.contributions.FakeAuthenticationCompletedContribution;
import com.progressoft.security.authentication.client.contributions.FakeAuthenticationContribution;
import com.progressoft.security.authentication.client.requests.UserLoggedInRequest;
import com.progressoft.security.authentication.server.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;
import com.progressoft.security.authentication.shared.extension.Principal;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.client.requests.AuthenticationSampleClientRequest;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;
import com.progressoft.security.authentication.shared.response.AuthenticationResponse;

import org.akab.engine.app.test.TestEntryPointContext;
import org.akab.engine.app.test.TestModule;

import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@RunWith(GwtMockitoTestRunner.class)
public class AuthenticationClientModuleTest {

    private TestModule testModule;
    private AuthenticationPresenterSpy presenterSpy;
    private AuthenticationViewSpy viewSpy;
    private FakeAuthenticationCompletedContribution authenticationCompletedContribution;
    private FakeAuthenticationContribution authenticationContribution;

    @Before
    public void setUp() throws Exception {

        testModule = new TestModule();
        testModule.init(new TestEntryPointContext());
        testModule.configureModule(new AuthenticationModuleConfiguration());
        testModule.configureModule(new AuthenticationTestModuleConfiguration());
        testModule.replacePresenter(AuthenticationPresenter.class.getCanonicalName(), () -> {
            presenterSpy = new AuthenticationPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AuthenticationPresenter.class.getCanonicalName(), () -> {
            viewSpy = new AuthenticationViewSpy();
            return viewSpy;
        });

        authenticationCompletedContribution =
                (FakeAuthenticationCompletedContribution) testModule.contributionsRepository()
                        .findExtensionPointContributions(AuthenticationCompletedExtensionPoint.class).iterator().next();

        authenticationContribution = (FakeAuthenticationContribution) testModule.contributionsRepository()
                .findExtensionPointContributions(AuthenticationExtensionPoint.class).iterator().next();

        testModule.run();
    }

    @Test
    public void givenAuthenticationModule_whenAuthenticationSampleClientRequestIsSent_thenShouldContributeToMainExtensionPoint()
            throws Exception {
        new AuthenticationSampleClientRequest(new MainExtensionPoint() {
            @Override
            public MainContext context() {
                return new MainContext() {
                    @Override
                    public void appendElementToRoot(Element e) {

                    }

                    @Override
                    public void appendWidgetToRoot(IsWidget w) {
                        assertNotNull(w);
                    }
                };
            }
        }).send();

        assertTrue(presenterSpy.isContributionCompleted());
        assertEquals("Hello world! from Authentication contribution request", viewSpy.getWelcomeMessage());
    }

    private void sendLoggedInRequest(AssertStrategy strategy) {
        new UserLoggedInRequest() {
            @Override
            protected void process(AuthenticationPresenter presenter, AuthenticationRequest serverRequest,
                                   AuthenticationResponse response) {
                super.process(presenter, serverRequest, response);
                strategy.assertResponse(response);
            }

            @Override
            public String getKey() {
                return UserLoggedInRequest.class.getCanonicalName();
            }
        }.send();
    }

    private interface AssertStrategy {
        void assertResponse(AuthenticationResponse response);
    }

    @Test
    public void givenUserLoggedInRequest_whenThereIsNoUserLoggedIN_thenShouldRecieveREsponseWithNegativeValue()
            throws Exception {
        ServerAuthenticationContext.authenticationHolder = new InMemoryAuthenticationHolder(null);
        sendLoggedInRequest(response -> assertFalse(response.isLoggedIn()));
    }

    @Test
    public void givenUserLoggedInRequest_whenThereIsUserLoggedIN_thenShouldRecieveREsponseWithPositiveValue()
            throws Exception {
        ServerAuthenticationContext.authenticationHolder = new InMemoryAuthenticationHolder(principal());
        sendLoggedInRequest(response -> assertTrue(response.isLoggedIn()));
    }

    private Principal principal() {
        return new Principal() {
        };
    }

    @Test
    public void givenUserLoggedInRequest_whenThereNoAuthenticationHolder_thenShouldRecieveResponseWithNegativeValue()
            throws Exception {
        sendLoggedInRequest(response -> assertFalse(response.isLoggedIn()));
    }

    @Test
    public void givenUserLoggedInRequest_WhenUserIsLoggedIn_thenShouldApplyAuthenticationCompletedContributions()
            throws Exception {
        ServerAuthenticationContext.authenticationHolder = new InMemoryAuthenticationHolder(principal());
        new UserLoggedInRequest().send();

        assertNotNull(authenticationCompletedContribution.getContext());
        assertNotNull(authenticationCompletedContribution.getContext().getPrincipal());
    }

    @Test
    public void givenAuthenticationModule_WhenModuleStarts_ThenAuthenticationPresenterShouldApplyContributionsForAuthenticationExtensionPoint()
            throws Exception {
        assertNotNull(authenticationContribution.getContext());
    }

    @Test
    public void givenContributionRegistersAuthenticationProvider_WhenStarts_ThenAuthenticationProviderRegistryShouldHaveTheProvider()
            throws Exception {
        assertEquals()

    }

    @Ignore
    @Test
    public void givenUserLoggedInRequest_WhenUserIsNotLoggedIn_ThenShouldGetNextAuthenticationChainAndStart()
            throws Exception {


    }
}
