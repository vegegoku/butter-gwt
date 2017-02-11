package com.progressoft.security.uimessages.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.uimessages.client.presenters.UiMessagesPresenter;
import org.akab.engine.core.test.ModuleTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class UiMessagesClientModuleTest extends ModuleTestCase {

    private UiMessagesPresenterSpy presenterSpy;
    private UiMessagesViewSpy viewSpy;
    private FakeUiMessagesContribution fakeUiMessagesContribution;

    @Override
    public void setUp() {

        testModule.configureModule(new UiMessagesModuleConfiguration());
        testModule.configureModule(new FakeModuleModuleConfiguration());

        testModule.replacePresenter(UiMessagesPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new UiMessagesPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(UiMessagesPresenter.class.getCanonicalName(), () -> {
            viewSpy=new UiMessagesViewSpy();
            return viewSpy;
        });

        fakeUiMessagesContribution=testModule.getContribution(FakeUiMessagesContribution.class);
    }

    @Test
    public void givenUiMessagesModule_whenContributingToUiMessagesExtensionPoint_shouldObtainUiMessagesContext() throws Exception {
        assertNotNull(fakeUiMessagesContribution.getUiMessagesContext());
    }

    @Test
    public void givenUiMessagesContext_WhenCallingShowErrorMessage_thenErrorMessageShouldBeShown() throws Exception {
        fakeUiMessagesContribution.getUiMessagesContext().showError("error message", "error details");
        assertTrue(viewSpy.errorMessageVisible);
        assertEquals("error message", viewSpy.errorMessage);
        assertEquals("error details", viewSpy.errorDescription);
    }

}
