package com.progressoft.security.authentication.client;

import com.google.gwtmockito.GwtMockitoTestRunner;

import com.progressoft.security.authentication.client.contributions.FakeAuthenticationCompletedContribution;
import com.progressoft.security.authentication.client.contributions.FakeAuthenticationContribution;
import com.progressoft.security.authentication.client.contributions.FakeRootChainContribution;
import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;
import com.progressoft.security.authentication.client.requests.ChainCompletedSuccessfullyRequest;
import com.progressoft.security.authentication.client.requests.UserLoggedInRequest;
import com.progressoft.security.authentication.server.filter.UserSessionContextFilter;
import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.extension.RootChainCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.test.ModuleTestCase;
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
    private FakeRootChainContribution fakeRootChainContribution;

    @Override
    public void setUp() {


        testModule.configureModule(new AuthenticationModuleConfiguration());
        testModule.configureModule(new AuthenticationTestModuleConfiguration(){
            @Override
            public void registerContributions(ContributionsRegistry registry) {
                registry.registerContribution(AuthenticationExtensionPoint.class, new FakeAuthenticationContribution());
                registry.registerContribution(AuthenticationCompletedExtensionPoint.class, new FakeAuthenticationCompletedContribution());
                registry.registerContribution(RootChainCompletedExtensionPoint.class, new FakeRootChainContribution());
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
        fakeRootChainContribution=testModule.getContribution(FakeRootChainContribution.class);

        testEntryPointContext.setHttpRequest(httpRequest);
        testEntryPointContext.setHttpServletResponse(httpResponse);
        attributes.clear();
        filterChain.addFilter(new UserSessionContextFilter());
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
        sendLoggedInRequest(response -> assertFalse(response.isLoggedIn()));
    }

    @Test
    public void givenUserLoggedInRequest_whenThereIsUserLoggedIN_thenShouldReceiveREspouseWithPositiveValue()
            throws Exception {

        setSessionPrincipal(principal());
        sendLoggedInRequest(response -> assertTrue(response.isLoggedIn()));
    }

    private Principal principal() {
        return new Principal() {
            @Override
            public String getUserName() {
                return "";
            }

            @Override
            public String getTenant() {
                return "";
            }
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
        setSessionPrincipal(principal());

        new UserLoggedInRequest().send();

        assertNotNull(authenticationCompletedContribution.getContext());
        assertNotNull(authenticationCompletedContribution.getContext().getPrincipal());
    }

    private void setSessionPrincipal(Principal principal) {
        session.setAttribute("principal", principal);
    }

    @Test
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
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(principal());
        assertTrue(presenterSpy.getFakeLoginChainCompletedSuccessfully());
        assertNotNull(presenterSpy.getRootChainSuccessContext());
        assertNotNull(fakeRootChainContribution.getContext().principal());
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
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(principal());
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
        authenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(principal());
        assertNotNull(authenticationCompletedContribution.getContext().getPrincipal());
    }

}
