package com.progressoft.security.app.layout.client;

import com.google.gwtmockito.GwtMockitoTestRunner;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import org.akab.engine.core.api.client.extension.Contributions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


import org.akab.engine.core.test.ModuleTestCase;

@RunWith(GwtMockitoTestRunner.class)
public class LayoutClientModuleTest extends ModuleTestCase{

    private AppLayoutPresenterSpy presenterSpy;
    private LayoutViewSpy viewSpy;

    @Override
    public void setUp() {

        testModule.configureModule(new AppLayoutModuleConfiguration());

        testModule.replacePresenter(AppLayoutPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new AppLayoutPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AppLayoutPresenter.class.getCanonicalName(), () -> {
            viewSpy=new LayoutViewSpy();
            return viewSpy;
        });

    }

    @Test
    public void givenAppLayoutModule_whenContributingToAuthenticationCompletedExtensionPointAndContextIsRecieved_thenShowAppLayout() throws Exception {
        Contributions.apply(AuthenticationCompletedExtensionPoint.class,
                (AuthenticationCompletedExtensionPoint) FakeAuthenticationCompletedContext::new);
        assertNotNull(presenterSpy.authenticationCompletedContext);
        assertTrue(presenterSpy.layoutShown);
    }


}
