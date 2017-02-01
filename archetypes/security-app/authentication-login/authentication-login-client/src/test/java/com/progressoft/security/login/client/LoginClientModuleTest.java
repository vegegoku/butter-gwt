package com.progressoft.security.login.client;

import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.configurations.AuthenticationConfiguration;
import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutExtensionPoint;
import com.progressoft.security.login.client.contributions.LoginAuthenticationContribution;
import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.repository.InMemoryUserRepository;
import com.progressoft.security.repository.RepositoryContext;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class LoginClientModuleTest extends ModuleTestCase {

    public static final String REQUIRED = "Required";
    public static final String SOMETHING = "something";
    private LoginPresenterSpy presenterSpy;
    private LoginViewSpy viewSpy;
    private LoginAuthenticationContribution loginAuthenticationContribution;
    private FakeAuthenticationContext fakeAuthenticationContext;


    @Override
    public void setUp() {
        GwtMockito.useProviderForType(MaterialTextBox.class, type -> new FakeMaterialTextBox());

        fakeAuthenticationContext = new FakeAuthenticationContext();
        testModule.configureModule(new LoginModuleConfiguration());

        testModule.replacePresenter(LoginPresenter.class.getCanonicalName(), () -> {
            presenterSpy = new LoginPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(LoginPresenter.class.getCanonicalName(), () -> {
            viewSpy = new LoginViewSpy();
            return viewSpy;
        });

        loginAuthenticationContribution = testModule.getContribution(LoginAuthenticationContribution.class);
        initServerAuthenticationConfigurations();
        Contributions.apply(AuthenticationLayoutExtensionPoint.class, (AuthenticationLayoutExtensionPoint) () -> view -> {
        });
        RepositoryContext.withUserRepository(new InMemoryUserRepository());
    }

    private void initServerAuthenticationConfigurations() {
        ServerAuthenticationContext.withConfigurationLoader(() -> new AuthenticationConfiguration() {
            @Override
            public String rootAuthenticationChain() {
                return "LOGIN";
            }

            @Override
            public String defaultTenant() {
                return "TENANT";
            }
        });
    }

    private void applyAuthenticationContributions(final AuthenticationContext context) {
        Contributions.apply(AuthenticationExtensionPoint.class, (AuthenticationExtensionPoint) () -> context);
    }

    @Test
    public void givenLoginModule_whenAuthenticationExtensionPointContributionsApplied_thenShouldReceiveTheAuthenticationContext() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        assertNotNull(presenterSpy.getContext());
    }

    @Test(expected = LoginAuthenticationContribution.InvalidAuthenticationContextRecieved.class)
    public void givenLoginModule_whenAuthenticationExtensionPointContributionsAppliedAndRecievedContextIsNull_thenShouldThrowException() throws Exception {
        applyAuthenticationContributions(null);
    }

    @Test
    public void givenLoginModule_whenAuthenticationExtensionPointContributionsApplied_thenLoginAuthenticationProviderShouldBeRegistered() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        assertEquals(FakeAuthenticationContext.PROVIDER_REGISTERED, presenterSpy.getContext().calls.toString());
    }

    @Test
    public void givenLoginModule_whenLoginProviderBeginMethodIsCalled_thenLoginDialogShouldBeShown() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        assertTrue(viewSpy.isLoginDialogVisible());
    }


    @Test
    public void givenLoginModule_whenLoginDialogIsShown_thenUserNameAndPasswordFieldsShouldBeEmpty() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        viewSpy.setUserName(SOMETHING);
        viewSpy.setPassword(SOMETHING);
        fakeAuthenticationContext.provider.begin();
        assertEquals("", viewSpy.getUserName());
        assertEquals("", viewSpy.getPassword());
    }

    @Test
    public void givenLoginModule_whenLoginDialogIsShown_thenTenantFieldValueShouldBeDefaultTenant() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        assertEquals("TENANT", viewSpy.getTenant());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndUserNameIsEmpty_thenShouldMarkUserNameFieldAsRequired() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.clickLogin();
        assertEquals(REQUIRED, ((FakeMaterialTextBox) viewSpy.getUserNameField()).getError());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndPasswordIsEmpty_thenShouldMarkPasswordFieldAsRequired() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.clickLogin();
        assertEquals(REQUIRED, ((FakeMaterialTextBox) viewSpy.getPasswordField()).getError());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndTenantIsEmpty_thenShouldMarkTenantFieldAsRequired() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setTenant("");
        viewSpy.clickLogin();
        assertEquals(REQUIRED, ((FakeMaterialTextBox) viewSpy.getTenantField()).getError());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndCredentialsAreValidAndTheUserIsNotFound_thenShouldShowErrorMessage() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setUserName("NOT_FOUND_USER");
        viewSpy.setPassword(SOMETHING);
        viewSpy.clickLogin();
        assertEquals("Bad credentials", viewSpy.getErrorMessage());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndCredentialsAreValidAndTheUserIsFoundAndPasswordIsWrong_thenShouldShowErrorMessage() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setUserName("FOUND_USER");
        viewSpy.setPassword("WRONG_PASSWORD");
        viewSpy.clickLogin();
        assertEquals("Bad credentials", viewSpy.getErrorMessage());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndCredentialsAreValidAndTheUserIsFoundAndTenantIsWrong_thenShouldShowErrorMessage() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setUserName("FOUND_USER");
        viewSpy.setPassword("SECRET");
        viewSpy.setTenant("WRONG_TENANT");
        viewSpy.clickLogin();
        assertEquals("Bad credentials", viewSpy.getErrorMessage());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndCredentialsAreValid_thenShouldCompleteTheChainSuccessfully() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setUserName("FOUND_USER");
        viewSpy.setPassword("SECRET");
        viewSpy.setTenant("TENANT");
        viewSpy.clickLogin();
        assertEquals(FakeAuthenticationContext.PROVIDER_REGISTERED + FakeAuthenticationContext.CHAIN_COMPLETED, fakeAuthenticationContext.calls.toString());
    }


}
