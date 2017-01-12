package com.progressoft.security.login.client;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;

import com.google.gwtmockito.fakes.FakeProvider;
import com.progressoft.security.authentication.shared.extension.*;
import com.progressoft.security.login.client.contributions.LoginAuthenticationContribution;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.extension.Contributions;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

import com.progressoft.security.login.client.presenters.LoginPresenter;
import org.akab.engine.app.test.ModuleTestCase;

@RunWith(GwtMockitoTestRunner.class)
public class LoginClientModuleTest extends ModuleTestCase{

    private LoginPresenterSpy presenterSpy;
    private LoginViewSpy viewSpy;
    private LoginAuthenticationContribution loginAuthenticationContribution;
    private FakeAuthenticationContext fakeAuthenticationContext;


    @Override
    public void setUp() {
        GwtMockito.useProviderForType(MaterialTextBox.class, type -> new FakeMaterialTextBox());

        fakeAuthenticationContext= new FakeAuthenticationContext();
        testModule.configureModule(new LoginModuleConfiguration());

        testModule.replacePresenter(LoginPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new LoginPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(LoginPresenter.class.getCanonicalName(), () -> {
            viewSpy=new LoginViewSpy();
            return viewSpy;
        });

        loginAuthenticationContribution=testModule.getContribution(LoginAuthenticationContribution.class);


    }

    private void applyAuthenticationContributions(final AuthenticationContext context) {
        Contributions.apply(AuthenticationExtensionPoint.class, new AuthenticationExtensionPoint() {
            @Override
            public AuthenticationContext context() {
                return context;
            }
        });
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
        viewSpy.setUserName("something");
        viewSpy.setPassword("something");
        fakeAuthenticationContext.provider.begin();
        assertEquals("", viewSpy.getUserName());
        assertEquals("", viewSpy.getPassword());
    }

    @Test
    public void givenLoginModule_whenLoginDialogIsShown_thenTenantFieldValueShouldBeDefaultTenant() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        assertEquals("System", viewSpy.getTenant());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndUserNameIsEmpty_thenShouldMarkUserNameFieldAsRequired() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.clickLogin();
        assertEquals("Required", ((FakeMaterialTextBox)viewSpy.getUserNameField()).getError());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndPasswordIsEmpty_thenShouldMarkPasswordFieldAsRequired() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.clickLogin();
        assertEquals("Required", ((FakeMaterialTextBox)viewSpy.getPasswordField()).getError());
    }

    @Test
    public void givenLoginModule_whenLoginButtonIsClickedAndTenantIsEmpty_thenShouldMarkTenantFieldAsRequired() throws Exception {
        applyAuthenticationContributions(fakeAuthenticationContext);
        fakeAuthenticationContext.provider.begin();
        viewSpy.setTenant("");
        viewSpy.clickLogin();
        assertEquals("Required", ((FakeMaterialTextBox)viewSpy.getTenantField()).getError());
    }


}
