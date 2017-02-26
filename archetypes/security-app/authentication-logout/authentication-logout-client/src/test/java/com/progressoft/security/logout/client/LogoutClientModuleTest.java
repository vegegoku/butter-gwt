package com.progressoft.security.logout.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.authentication.server.authentication.WebSessionHolder;
import com.progressoft.security.authentication.server.filter.SessionContextFilter;
import com.progressoft.security.authentication.server.shared.UserSessionContext;
import com.progressoft.security.logout.client.presenters.LogoutPresenter;
import com.progressoft.security.repository.FakePrincipal;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class LogoutClientModuleTest extends ModuleTestCase{

    private LogoutPresenterSpy presenterSpy;
    private LogoutViewSpy viewSpy;
    private FakeAppLayoutContext fakeAppLayoutContext;

    @Override
    public void setUp() {

        testModule.configureModule(new LogoutModuleConfiguration());

        testModule.replacePresenter(LogoutPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new LogoutPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(LogoutPresenter.class.getCanonicalName(), () -> {
            viewSpy=new LogoutViewSpy();
            return viewSpy;
        });
        fakeAppLayoutContext =new FakeAppLayoutContext();
        filterChain.addFilter(new SessionContextFilter());
    }

    @Override
    protected void afterRun() {
        Contributions.apply(AppLayoutExtensionPoint.class, (AppLayoutExtensionPoint) () -> fakeAppLayoutContext);
    }

    @Test
    public void givenLogoutModule_whenContributingToAppLayoutModule_thenShouldReceiveTheAppLayoutContext()
            throws Exception {
        assertNotNull(presenterSpy.appLayoutContext);
    }

    @Test
    public void givenLogoutModule_whenContributingToAppLayoutModuleAndAppLayoutContextIsReceived_thenLogoutButtonShouldBeAddedToTheLayoutHeader()
            throws Exception {
        assertNotNull(fakeAppLayoutContext.logoutButton);
    }

    @Test
    public void givenLogoutModule_whenContributingToAppLayoutModuleAndAppLayoutContextIsReceived_thenLogoutMenuItemShouldBeAddedToTheLayoutMenu()
            throws Exception {
        assertNotNull(fakeAppLayoutContext.logoutMenu);
    }

    @Test
    public void givenLogoutModule_whenClickingTheLogoutButton_thenTheUserMustBeLoggedOutAndWindowShouldBeReloaded()
            throws Exception {
        session.setAttribute(UserSessionContext.PRINCIPAL, new FakePrincipal("FOUND_USER", "TENANT"));
        UserSessionContext.setContext(WebSessionHolder.makeUserSessionHolder(httpRequest.getSession()));
        viewSpy.clickHeaderLogout();
        assertTrue(UserSessionContext.get().isEmpty());
        assertNull(session.getAttribute(UserSessionContext.PRINCIPAL));
    }

    @Test
    public void givenLogoutModule_whenClickingTheLogoutMenu_thenTheUserMustBeLoggedOutAndWindowShouldBeReloaded()
            throws Exception {
        session.setAttribute(UserSessionContext.PRINCIPAL, new FakePrincipal("FOUND_USER", "TENANT"));
        UserSessionContext.setContext(WebSessionHolder.makeUserSessionHolder(httpRequest.getSession()));
        viewSpy.clickMenuLogout();
        assertTrue(UserSessionContext.get().isEmpty());
        assertNull(session.getAttribute(UserSessionContext.PRINCIPAL));
    }
}
