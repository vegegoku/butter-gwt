package com.progressoft.security.user.client;

import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.user.client.presenters.AddUserPresenter;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.test.ModuleTestCase;
import org.akab.engine.material.test.FakeMaterialTextBox;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class AddUserClientModuleTest extends ModuleTestCase{

    private AddUserPresenterSpy presenterSpy;
    private AddUserViewSpy viewSpy;
    private FakeAppLayoutContext fakeAppLayoutContext;

    @Override
    public void setUp() {

        GwtMockito.useProviderForType(MaterialTextBox.class, type -> new FakeMaterialTextBox());

        testModule.configureModule(new AddUserModuleConfiguration());

        testModule.replacePresenter(AddUserPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new AddUserPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(AddUserPresenter.class.getCanonicalName(), () -> {
            viewSpy=new AddUserViewSpy();
            return viewSpy;
        });
        fakeAppLayoutContext=new FakeAppLayoutContext();
    }

    @Override
    protected void onConfigurationCompleted() {
        Contributions.apply(AppLayoutExtensionPoint.class, new AppLayoutExtensionPoint() {
            @Override
            public AppLayoutContext context() {
                return fakeAppLayoutContext;
            }
        });
    }

    @Test
    public void givenAddUserModule_whenContributingToApplicationLayoutExtensionPoint_thenShouldReceiveApplicationLayoutContext() throws Exception {
        assertNotNull(presenterSpy.getAppLayoutContext());
    }

    @Test
    public void givenAddUserModule_whenApplicationLayoutContextReceived_thenShouldAddTheAddNewUserButtonToUi() throws Exception {
        assertNotNull(fakeAppLayoutContext.getAddNewUserButton());
    }

    @Test
    public void givenAddUserModule_whenAddButtonIsClicked_thenShouldShowAddUserForm() throws Exception {
        viewSpy.clickAddButton();
        assertNotNull(fakeAppLayoutContext.getUserForm());
    }

    @Test
    public void givenAddUserModule_whenAddUserFormIsShownAndThenWeClickCreate_thenShouldShowErrorOnRequiredFields() throws Exception {
        viewSpy.clickCreateUser();
        assertEquals("Required", viewSpy.getUserNameField().getError());
    }
}
