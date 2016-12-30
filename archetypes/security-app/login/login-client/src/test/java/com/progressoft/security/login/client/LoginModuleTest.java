package com.progressoft.security.login.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.login.client.requests.LoginSampleClientRequest;
import com.progressoft.security.login.client.requests.LoginServerRequest;
import com.progressoft.security.login.shared.request.LoginRequest;
import com.progressoft.security.login.shared.response.LoginResponse;
import org.akab.engine.app.test.TestEntryPointContext;
import org.akab.engine.app.test.TestModule;
import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
public class LoginModuleTest{

    private ClientApp clientApp;
    private TestModule testModule;
    private LoginPresenterSpy loginPresenterSpy;
    private LoginViewSpy loginViewSpy;

    @Before
    public void setUp() throws Exception {

        testModule=new TestModule();
        clientApp= testModule.init(new TestEntryPointContext());
        testModule.configureModule(new LoginModuleConfiguration());
        loginPresenterSpy=new LoginPresenterSpy();
        loginViewSpy=new LoginViewSpy();
        testModule.replacePresenter(LoginPresenter.class.getCanonicalName(), loginPresenterSpy);
        testModule.replaceView(LoginPresenter.class.getCanonicalName(), loginViewSpy);

    }

    @Test
    public void testNothing() throws Exception {
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


        assertNotNull(loginPresenterSpy.getMainExtensionPoint());

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


        System.out.println(loginViewSpy.getDescription());
        assertNotNull(loginViewSpy.getDescription());

    }
}
