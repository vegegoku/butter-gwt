package com.progressoft.security.authentication.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.authentication.client.contributions.*;
import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.app.test.ModuleTestCase;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Deque;
import java.util.LinkedList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class AuthenticationClientModuleMultiProvidersTest extends ModuleTestCase{


    private AuthenticationPresenterSpy presenterSpy;
    private AuthenticationViewSpy viewSpy;
    private FakeAuthenticationCompletedContribution authenticationCompletedContribution;
    private FakeAuthenticationContribution rootAuthenticationContribution;
    private SecondFakeAuthenticationContribution secondFakeAuthenticationContribution;
    private ThirdFakeAuthenticationContribution thirdFakeAuthenticationContribution;

    @Override
    protected void setUp() {
        testModule.configureModule(new AuthenticationModuleConfiguration());
        testModule.configureModule(new MultiProviderModuleConfiguration(){
            @Override
            public void registerContributions(ContributionsRegistry registry) {
                registry.registerContribution(AuthenticationCompletedExtensionPoint.class, new FakeAuthenticationCompletedContribution());
                registry.registerContribution(AuthenticationExtensionPoint.class, new FakeAuthenticationContribution());
                registry.registerContribution(AuthenticationExtensionPoint.class, new SecondFakeAuthenticationContribution());
                registry.registerContribution(AuthenticationExtensionPoint.class, new ThirdFakeAuthenticationContribution());
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
        rootAuthenticationContribution = testModule.getContribution(FakeAuthenticationContribution.class);
        secondFakeAuthenticationContribution=testModule.getContribution(SecondFakeAuthenticationContribution.class);
        thirdFakeAuthenticationContribution=testModule.getContribution(ThirdFakeAuthenticationContribution.class);

        testEntryPointContext.setHttpRequest(httpRequest);
        testEntryPointContext.setHttpServletResponse(httpResponse);
        FakeAuthenticationProvider.ORDER=0;
        ServerAuthenticationContext.reset();
    }

    @Test
    public void givenAuthenticationContribution_whenNoUserIsLoggedIn_thenAuthenticationContributionShouldStart() throws Exception {
        assertTrue(rootAuthenticationContribution.getProvider().isStarted());
    }

    @Test
    public void givenSuccessRootAuthenticationChain_WhenPrincipalContainsMoreChainsAndHistNextIsNotRegistered_thenShouldShowErrorMessage() throws Exception {
        Principal p = new Principal() {
            @Override
            public Deque<String> chains(){
                return new LinkedList<String>(){{
                    add("NOT_REGISTERED");
                }};
            }
        };
        session.setAttribute(isA(String.class), eq(p));
        expect(session.getAttribute("principle")).andReturn(p).anyTimes();
        replay(session);
        rootAuthenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);
        assertEquals("Authentication failed", viewSpy.getErrorMessage());
    }

    @Test
    public void givenSuccessRootAuthenticationChain_WhenPrincipalContainsChainRegistered_thenShouldStartThatChain() throws Exception {
        Principal p = new Principal() {
            @Override
            public Deque<String> chains(){
                return new LinkedList<String>(){{
                    add(SecondFakeAuthenticationContribution.SECOND_CHAIN);
                }};
            }
        };
        session.setAttribute(isA(String.class), eq(p));
        expect(session.getAttribute("principle")).andReturn(p).anyTimes();
        replay(session);
        rootAuthenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);
        assertTrue(secondFakeAuthenticationContribution.getProvider().isStarted());
    }

    @Test
    public void givenSuccessRootAuthenticationChain_WhenPrincipalContains2ChainsRegistered_thenBothChainsShouldStartInOrderAndAuthenticationShouldComplete() throws Exception {
        Principal p = new Principal() {
            private Deque<String> chains=new LinkedList<String>(){{
                add(SecondFakeAuthenticationContribution.SECOND_CHAIN);
                add(ThirdFakeAuthenticationContribution.THIRD_CHAIN);
            }};
            @Override
            public Deque<String> chains(){
                return chains;
            }
        };
        session.setAttribute(isA(String.class), eq(p));
        expect(session.getAttribute("principle")).andReturn(p).anyTimes();
        replay(session);
        rootAuthenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);
        secondFakeAuthenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);
        thirdFakeAuthenticationContribution.getProvider().chainAuthenticationCompletedSuccessfully(p);
        assertTrue(secondFakeAuthenticationContribution.getProvider().isStarted());
        assertTrue(thirdFakeAuthenticationContribution.getProvider().isStarted());
        assertEquals(1,rootAuthenticationContribution.getProvider().callOrder);
        assertEquals(2,secondFakeAuthenticationContribution.getProvider().callOrder);
        assertEquals(3,thirdFakeAuthenticationContribution.getProvider().callOrder);
        assertNotNull(authenticationCompletedContribution.getContext());
    }




}
