package com.progressoft.security.layout.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.layout.client.presenters.AuthenticationLayoutPresenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class AuthenticationLayoutClientModuleTest extends ModuleTestCase{

    private AuthenticationLayoutPresenterSpy presenterSpy;
    private AuthenticationLayoutViewSpy viewSpy;

    @Override
    public void setUp() {

        testModule.configureModule(new AuthenticationLayoutModuleConfiguration());

        testModule.replacePresenter(AuthenticationLayoutPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new AuthenticationLayoutPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AuthenticationLayoutPresenter.class.getCanonicalName(), () -> {
            viewSpy=new AuthenticationLayoutViewSpy();
            return viewSpy;
        });


    }

    @Test
    public void givenAuthenticationLayoutModule_whenContributingToMainExtensionPoint_thenAuthenticationLayoutShouldBeShown() throws Exception {
        assertTrue(presenterSpy.viewAddedToRoot);
    }

    @Test
    public void givenAuthenticationLayoutModule_whenContributingToAuthenticationCompletedExtensionPoint_thenShouldObtainAuthenticationCompletedContextAndHideView() throws Exception {
        Contributions.apply(AuthenticationCompletedExtensionPoint.class,
                (AuthenticationCompletedExtensionPoint) FakeAuthenticationCompletedContext::new);
        assertNotNull(presenterSpy.authenticationCompletedContext);
    }


}
