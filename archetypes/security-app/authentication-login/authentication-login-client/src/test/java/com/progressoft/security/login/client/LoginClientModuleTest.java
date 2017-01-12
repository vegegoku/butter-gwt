package com.progressoft.security.login.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.login.client.requests.LoginSampleClientRequest;
import com.progressoft.security.login.shared.request.LoginRequest;
import com.progressoft.security.login.shared.response.LoginResponse;

import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.app.test.ModuleTestCase;

@RunWith(GwtMockitoTestRunner.class)
public class LoginClientModuleTest extends ModuleTestCase{

    private LoginPresenterSpy presenterSpy;
    private LoginViewSpy viewSpy;

    @Override
    public void setUp() {

        testModule.configureModule(new LoginModuleConfiguration());

        testModule.replacePresenter(LoginPresenter.class.getCanonicalName(), () -> {
            presenterSpy=new LoginPresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(LoginPresenter.class.getCanonicalName(), () -> {
            viewSpy=new LoginViewSpy();
            return viewSpy;
        });
    }

    @Test
    public void givenLoginModule_whenLoginSampleClientRequestIsSent_thenShouldContributeToMainExtensionPoint() throws Exception {
        new LoginSampleClientRequest(new MainExtensionPoint() {
            @Override
            public MainContext context() {
                return new MainContext() {
                    @Override
                    public void appendElementToRoot(Element e) {

                    }

                    @Override
                    public void appendWidgetToRoot(IsWidget w) {
                        assertNotNull(w);
                    }
                };
            }
        }).send();

        assertTrue(presenterSpy.isContributionCompleted());
        assertEquals("Hello world! from Login contribution request", viewSpy.getWelcomeMessage());
    }

    @Test
    public void givenLoginClientModule_whenLoginServerRequestIsSent_thenServerMessageShouldBeRecieved()
            throws Exception {

        new LoginServerRequest(){
            @Override
            protected void process(LoginPresenter presenter, LoginRequest serverArgs, LoginResponse response) {
                super.process(presenter, serverArgs, response);
                assertEquals("Server message",response.getServerMessage());
            }

            @Override
            public String getKey() {
                return LoginServerRequest.class.getCanonicalName();
            }
        }.send();
    }
}
