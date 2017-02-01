package com.progressoft.security.otp.client;

import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.authentication.server.filter.UserSessionContextFilter;
import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.otp.client.contributions.OtpAuthenticationContribution;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import com.progressoft.security.repository.FakePrincipal;
import com.progressoft.security.repository.InMemoryUserRepository;
import com.progressoft.security.repository.RepositoryContext;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class OtpClientModuleTest extends ModuleTestCase {

    private OtpPresenterSpy presenterSpy;
    private OtpViewSpy viewSpy;
    private FakeAuthenticationContext fakeAuthenticationContext;
    private SmtpServer smtpServer;

    @Override
    public void setUp() {
        ServerOptions serverOptions = new ServerOptions();
        serverOptions.port = 2025;
        serverOptions.threaded = false;
        smtpServer = SmtpServerFactory.startServer(serverOptions);
        fakeAuthenticationContext = new FakeAuthenticationContext();
        testModule.configureModule(new OtpModuleConfiguration());

        testModule.replacePresenter(OtpPresenter.class.getCanonicalName(), () -> {
            presenterSpy = new OtpPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(OtpPresenter.class.getCanonicalName(), () -> {
            viewSpy = new OtpViewSpy();
            return viewSpy;
        });
        RepositoryContext.withUserRepository(new InMemoryUserRepository());
        filterChain.addFilter(new UserSessionContextFilter());
    }

    private void applyAuthenticationContributions(final AuthenticationContext context) {
        Contributions.apply(AuthenticationExtensionPoint.class, (AuthenticationExtensionPoint) () -> context);
    }

    @Test
    public void givenOtpModule_whenAuthenticationExtensionPointContributionsApplied_thenShouldReceiveTheAuthenticationContext()
            throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        assertNotNull(presenterSpy.getContext());
    }

    @Test(expected = OtpAuthenticationContribution.InvalidAuthenticationContextRecieved.class)
    public void givenOtpModule_whenAuthenticationExtensionPointContributionsAppliedAndRecievedContextIsNull_thenShouldThrowException()
            throws Exception {
        applyAuthenticationContributions(null);
    }

    @Test
    public void givenOtpModule_whenAuthenticationExtensionPointContributionsApplied_thenLoginAuthenticationProviderShouldBeRegistered()
            throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        assertEquals(FakeAuthenticationContext.PROVIDER_REGISTERED, presenterSpy.getContext().calls.toString());
    }

    @Test
    public void givenOtpModule_whenOtpProviderBeginMethodIsCalled_thenOtpCodeShouldBeGeneratedAndOtpDialogShouldBeShown()
            throws Exception {
        session.setAttribute("principal", new FakePrincipal("FOUND_USER", "TENANT"));
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        assertTrue(presenterSpy.otpCodeGenerated);
        assertTrue(viewSpy.isOtpDialogVisible());
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

}
