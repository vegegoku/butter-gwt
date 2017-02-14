package com.progressoft.security.app.layout.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(GwtMockitoTestRunner.class)
public class LayoutClientModuleTest extends ModuleTestCase{

    private AppLayoutPresenterSpy presenterSpy;
    private LayoutViewSpy viewSpy;
    private FakeAppLayoutContribution fakeAppLayoutContribution;

    @Override
    public void setUp() {

        testModule.configureModule(new AppLayoutModuleConfiguration());
        testModule.configureModule(new FakeForTestModuleConfiguration());

        testModule.replacePresenter(AppLayoutPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new AppLayoutPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AppLayoutPresenter.class.getCanonicalName(), () -> {
            viewSpy=new LayoutViewSpy();
            return viewSpy;
        });

        fakeAppLayoutContribution=testModule.getContribution(FakeAppLayoutContribution.class);

    }

    @Override
    protected void afterRun() {
        Contributions.apply(AuthenticationCompletedExtensionPoint.class,
                (AuthenticationCompletedExtensionPoint) FakeAuthenticationCompletedContext::new);
    }

    @Test
    public void givenAppLayoutModule_whenContributingToAuthenticationCompletedExtensionPointAndContextIsRecieved_thenShowAppLayout() throws Exception {
        assertNotNull(presenterSpy.authenticationCompletedContext);
        assertTrue(presenterSpy.layoutShown);
    }

    @Test
    public void givenAppLayoutModule_whenAnyModuleContributeToAppLayoutExtensionPoint_thenShouldProvideAppLayoutContext() throws Exception {
        assertNotNull(fakeAppLayoutContribution);
        assertNotNull(fakeAppLayoutContribution.getContext());
    }

    @Test
    public void givenAppLayoutModule_whenAnyModuleContributeToAppLayoutExtensionPoint_thenShouldAllowAddingItemsToHeaderPanelThroughTheContext() throws Exception {
        fakeAppLayoutContribution.addHeaderItem(new FakeLayoutItem());
        assertTrue(viewSpy.headerItems.size()>0);
    }

    @Test
    public void givenAppLayoutModule_whenAnyModuleContributeToAppLayoutExtensionPoint_thenShouldAllowAddingItemsToMenuPanelThroughTheContext() throws Exception {
        fakeAppLayoutContribution.addMenuItem(new FakeLayoutItem());
        assertTrue(viewSpy.menuItems.size()>0);
    }

    @Test
    public void givenAppLayoutModule_whenAnyModuleContributeToAppLayoutExtensionPoint_thenShouldAllowShowingContentThroughTheContext() throws Exception {
        fakeAppLayoutContribution.showContent(new FakeLayoutItem());
        assertNotNull(viewSpy.content);
    }

    @Test
    public void givenAppLayoutModule_whenAnyModuleContributeToAppLayoutExtensionPoint_thenShouldAllowShowingContentInRightPanelThroughTheContext() throws Exception {
        fakeAppLayoutContribution.showSideContent(new FakeLayoutItem());
        assertNotNull(viewSpy.sideContent);
    }
}
