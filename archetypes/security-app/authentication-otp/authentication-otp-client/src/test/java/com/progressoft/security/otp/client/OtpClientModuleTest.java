package com.progressoft.security.otp.client;

import com.dumbster.smtp.ServerOptions;
import com.dumbster.smtp.SmtpServer;
import com.dumbster.smtp.SmtpServerFactory;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.authentication.server.filter.SessionContextFilter;
import com.progressoft.security.authentication.server.shared.AuthenticationProcessContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutExtensionPoint;
import com.progressoft.security.model.otp.OtpHolder;
import com.progressoft.security.otp.client.contributions.OtpAuthenticationContribution;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import com.progressoft.security.repository.FakePrincipal;
import com.progressoft.security.repository.InMemoryUserRepository;
import com.progressoft.security.repository.RepositoryContext;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import com.progressoft.security.uimessages.shared.extension.UiMessagesExtensionPoint;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class OtpClientModuleTest extends ModuleTestCase {

    public static final String INVALID_OTP = "INVALID_OTP";
    private OtpPresenterSpy presenterSpy;
    private OtpViewSpy viewSpy;
    private FakeAuthenticationContext fakeAuthenticationContext;
    private FakeAuthenticationLayoutContext fakeAuthenticationLayoutContext;
    private FakeUiMessageContext fakeUiMessageContext;
    private SmtpServer smtpServer;

    private void startSmtpServer() {
        try {
            ServerOptions serverOptions = new ServerOptions();
            serverOptions.port = 3025;
            serverOptions.threaded = false;
            smtpServer = SmtpServerFactory.startServer(serverOptions);
            while (!smtpServer.isReady())
                smtpServer = SmtpServerFactory.startServer(serverOptions);
        } catch (RuntimeException e) {
            startSmtpServer();
        }
    }

    @Override
    public void setUp() {
        GwtMockito.useProviderForType(MaterialTextBox.class, type -> new FakeMaterialTextBox());
        startSmtpServer();
        fakeAuthenticationContext = new FakeAuthenticationContext();
        fakeAuthenticationLayoutContext = new FakeAuthenticationLayoutContext();
        fakeUiMessageContext=new FakeUiMessageContext();
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
        filterChain.addFilter(new SessionContextFilter());

    }

    private void applyAuthenticationContributions(final AuthenticationContext context) {
        Contributions.apply(AuthenticationExtensionPoint.class, (AuthenticationExtensionPoint) () -> context);
    }

    private void applyAuthenticationLayoutContribution(final AuthenticationLayoutContext context) {
        Contributions.apply(AuthenticationLayoutExtensionPoint.class, (AuthenticationLayoutExtensionPoint) () -> context);
    }

    private void applyUiMessagesContextContribution(final UiMessagesContext context) {
        Contributions.apply(UiMessagesExtensionPoint.class, (UiMessagesExtensionPoint) () -> context);
    }

    private void initializeOtpHolder() {
        AuthenticationProcessContext.get().setProperty("OTP_HOLDER", new OtpHolder() {
            @Override
            public boolean verify(String code) {
                return !INVALID_OTP.equals(code);
            }

            @Override
            public void sendEmail(String email) {
            }
        });
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
        session.setAttribute("authentication", new FakePrincipal("FOUND_USER", "TENANT"));
        applyAuthenticationContributions(fakeAuthenticationContext);
        applyAuthenticationLayoutContribution(fakeAuthenticationLayoutContext);
        applyUiMessagesContextContribution(fakeUiMessageContext);
        fakeAuthenticationContext.provider.begin();
        assertTrue(presenterSpy.otpCodeGenerated);
        assertTrue(viewSpy.isOtpDialogVisible());
    }

    @Test
    public void givenOtpModule_WhenOtpDialogShownAndVerifyCodeButtonIsPressedWithNoOtpValueTyped_ThenShouldShowErrorMessage() throws Exception {
        session.setAttribute("authentication", new FakePrincipal("FOUND_USER", "TENANT"));
        applyAuthenticationContributions(fakeAuthenticationContext);
        applyAuthenticationLayoutContribution(fakeAuthenticationLayoutContext);
        applyUiMessagesContextContribution(fakeUiMessageContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setOtpCode(null);
        viewSpy.clickVerify();
        assertTrue(viewSpy.isOtpFieldInvalid());
    }

    @Test
    public void givenOtpModule_WhenOtpDialogIsShownAndVerifyCodePressedWithInvalidOtpValue_ThenShouldShowErrorMessage() throws Exception {
        session.setAttribute("authentication", new FakePrincipal("FOUND_USER", "TENANT"));
        applyAuthenticationContributions(fakeAuthenticationContext);
        applyAuthenticationLayoutContribution(fakeAuthenticationLayoutContext);
        applyUiMessagesContextContribution(fakeUiMessageContext);
        fakeAuthenticationContext.provider.begin();
        initializeOtpHolder();
        viewSpy.setOtpCode(INVALID_OTP);
        viewSpy.clickVerify();
        assertTrue(presenterSpy.errorMessageShown);
        assertEquals("Invalid OTP code", presenterSpy.errorDescription);
    }

    @Test
    public void givenOtpModule_WhenOtpDialogIsShownAndVerifyCodePressedWithValidOtpValue_ThenShouldCompleteTheChainSuccessfully() throws Exception {
        session.setAttribute("authentication", new FakePrincipal("FOUND_USER", "TENANT"));
        applyAuthenticationContributions(fakeAuthenticationContext);
        applyAuthenticationLayoutContribution(fakeAuthenticationLayoutContext);
        fakeAuthenticationContext.provider.begin();
        initializeOtpHolder();
        viewSpy.setOtpCode("VALID_OTP");
        viewSpy.clickVerify();
        assertEquals(FakeAuthenticationContext.PROVIDER_REGISTERED + FakeAuthenticationContext.CHAIN_COMPLETED, fakeAuthenticationContext.calls.toString());
    }

    @After
    public void tearDown() throws Exception {
        if (Objects.isNull(smtpServer))
            return;
        try {
            closeServer();
        } catch (Exception e) {
            closeServer();
        }
    }

    private synchronized void closeServer() {
        while (!smtpServer.isStopped())
            smtpServer.stop();
    }

}
