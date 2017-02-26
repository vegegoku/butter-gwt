package com.progressoft.security.userinfo.client;

import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(GwtMockitoTestRunner.class)
public class UserInfoClientModuleTest extends ModuleTestCase{

    private UserInfoPresenterSpy presenterSpy;
    private UserInfoViewSpy viewSpy;
    private FakeAuthenticationCompletedContext fakeAuthenticationCompletedContext;
    private FakeAppLayoutContext fakeAppLayoutContext;

    @Override
    public void setUp() {

        testModule.configureModule(new UserInfoModuleConfiguration());

        testModule.replacePresenter(UserInfoPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new UserInfoPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(UserInfoPresenter.class.getCanonicalName(), () -> {
            viewSpy=new UserInfoViewSpy();
            return viewSpy;
        });
        fakeAuthenticationCompletedContext=new FakeAuthenticationCompletedContext();
        fakeAppLayoutContext=new FakeAppLayoutContext();
    }

    @Override
    protected void afterRun() {
        Contributions.apply(AuthenticationCompletedExtensionPoint.class,
                (AuthenticationCompletedExtensionPoint) () -> fakeAuthenticationCompletedContext);

        Contributions.apply(AppLayoutExtensionPoint.class, new AppLayoutExtensionPoint() {
            @Override
            public AppLayoutContext context() {
                return fakeAppLayoutContext;
            }
        });
    }

    @Test
    public void givenUserInfoModule_whenContributingToAuthenticationCompletionExtensionPoint_thenShouldReceiveAuthenticationCompletionContext() throws Exception {
        assertNotNull(presenterSpy.getAuthenticationCompletedContext());
    }

    @Test
    public void givenUserInfoModule_whenContributingToAppLayoutExtensionPoint_thenShouldReceiveAppLayoutContext() throws Exception {
        assertNotNull(presenterSpy.getAppLayoutContext());
    }

    @Test
    public void givenUserInfoModule_whenAppLayoutContextIsReceived_thenShouldShowTheUserNameOnTheHeader() throws Exception {
        assertNotNull(fakeAppLayoutContext.getUserNameHeaderItem());
    }
}
