package com.progressoft.security.authentication.client;

import com.google.gwtmockito.GwtMockitoTestRunner;

import com.progressoft.security.authentication.client.contributions.FakeAuthenticationCompletedContribution;
import com.progressoft.security.authentication.client.contributions.FakeAuthenticationContribution;
import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;
import com.progressoft.security.authentication.client.requests.ChainCompletedSuccessfullyRequest;
import com.progressoft.security.authentication.client.requests.UserLoggedInRequest;
import com.progressoft.security.authentication.server.registry.WebAuthenticationHolder;
import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.app.test.ModuleTestCase;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;
import com.progressoft.security.authentication.shared.response.AuthenticationResponse;

@RunWith(GwtMockitoTestRunner.class)
public class AuthenticationClientModuleTest extends ModuleTestCase {


    private AuthenticationPresenterSpy presenterSpy;
    private AuthenticationViewSpy viewSpy;
    private FakeAuthenticationCompletedContribution authenticationCompletedContribution;
    private FakeAuthenticationContribution authenticationContribution;

    @Override
    public void setUp() {


        testModule.configureModule(new AuthenticationModuleConfiguration());
        testModule.configureModule(new AuthenticationTestModuleConfiguration(){
            @Override
            public void registerContributions(ContributionsRegistry registry) {
                registry.registerContribution(AuthenticationExtensionPoint.class, new FakeAuthenticationContribution());
                registry.registerContribution(AuthenticationCompletedExtensionPoint.class, new FakeAuthenticationCompletedContribution());
            }
        });
        testModule.replacePresenter(AuthenticationPresenter.class.getCanonicalName(), () -> {
            presenterSpy = new AuthenticationPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AuthenticationPresenter.class.getCanonicalName(), () -> {
            viewSpy = new AuthenticationViewSpy();
            return viewSpy;
        });

        authenticationCompletedContribution = testModule.getContribution(FakeAuthenticationCompletedContribution.class);
        authenticationContribution = testModule.getContribution(FakeAuthenticationContribution.class);

        testEntryPointContext.setHttpRequest(httpRequest);
        testEntryPointContext.setHttpServletResponse(httpResponse);
        ServerAuthenticationContext.reset();

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
    public void givenUserLoggedInRequest_whenThereIsNoUserLoggedIN_thenShouldReceiveResponseWithNegativeValue()
            throws Exception {
        session.setAttribute("principle", null);
        sendLoggedInRequest(response -> assertFalse(response.isLoggedIn()));
    }

    @Test
    public void givenUserLoggedInRequest_whenThereIsUserLoggedIN_thenShouldReceiveREspouseWithPositiveValue()
            throws Exception {
        session.setAttribute("principle", principal());
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
        Principal p = principal();
        ServerAuthenticationContext.hold(new WebAuthenticationHolder(session, p));
        new UserLoggedInRequest().send();

        assertNotNull(authenticationCompletedContribution.getContext());
        assertNotNull(authenticationCompletedContribution.getContext().getPrincipal());
    }

    @Test//
    public void givenAuthenticationModule_WhenModuleStarts_ThenAuthenticationPresenterShouldApplyContributionsForAuthenticationExtensionPoint()
            throws Exception {
        assertNotNull(authenticationContribution.getContext());
    }

    @Test
    public void givenContributionRegistersAuthenticationProvider_WhenStarts_ThenAuthenticationProviderRegistryShouldHaveTheProvider()
            throws Exception {
        assertNotNull(AuthenticationProviderRegistry.get(FakeAuthenticationContribution.LOGIN));
    }

    @Test
    public void givenUserLoggedInRequest_WhenUserIsNotLoggedIn_ThenShouldGetRootAuthenticationChainAndStart()
            throws Exception {

        assertNotNull(presenterSpy.getRootChain());
        assertNotNull(authenticationContribution.getContext());
        assertNotNull(authenticationContribution.getProvider().isStarted());
    }

    @Test
    public void givenAuthenticationProvider_whenProviderAuthenticationCompletedSuccessfully_thenAuthenticationContextShouldBeInformed()
            throws Exception {
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(new Principal() {
        });
        assertTrue(presenterSpy.getFakeLoginChainCompletedSuccessfully());
        assertNotNull(presenterSpy.getRootChainSuccessContext());
    }

    @Test
    public void givenAuthenticationProvider_whenProviderAuthenticationFailed_thenAuthenticationContextShouldBeInformed()
            throws Exception {
        authenticationContribution.getProvider().chainAuthenticationFailed();
        assertTrue(presenterSpy.getFakeLoginChainFailed());
        assertNotNull(presenterSpy.getFailedChainContext());
    }

    @Test
    public void givenAuthenticationProvider_whenAuthenticationFailed_thenAuthenticationProcessShouldRestart()
            throws Exception {
        authenticationContribution.getProvider().chainAuthenticationFailed();
        assertTrue(presenterSpy.getFakeLoginChainFailed());
        assertNotNull(presenterSpy.getFailedChainContext());
        assertEquals(2, presenterSpy.getAuthenticationCallCounter());
    }

    @Test
    public void givenASingleAuthenticationProvider_whenProviderAuthenticationIsCompletedSuccessfully_thenAuthenticationShouldBeCompletedAndAllContributionsShouldBeCalled()
            throws Exception {
        Principal p = new Principal() {
        };
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);

        assertNotNull(authenticationCompletedContribution.getContext().getPrincipal());

    }

    @Test(expected = ChainCompletedSuccessfullyRequest.InvalidPrincipal.class)

    public void givenASingleAuthenticationProvider_whenProviderAuthenticationIsCompletedSuccessfullyAndReturnsNullPrincipal_thenAuthenticationThrowException()
            throws Exception {
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(null);

    }

    @Test
    public void givenASingleAuthenticationProvider_whenAuthenticationIsCompletedSuccessfullyAndReturnedPrincipalHasNoMoreChains_thenAuthenticationShouldBeCompletedAndAllContributionsShouldBeCalled()
            throws Exception {
        Principal p = new Principal() {
        };
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);

        assertNotNull(authenticationCompletedContribution.getContext().getPrincipal());
    }

}
