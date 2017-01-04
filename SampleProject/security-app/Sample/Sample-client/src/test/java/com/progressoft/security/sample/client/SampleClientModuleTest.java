package com.progressoft.security.sample.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.progressoft.security.sample.client.presenters.SamplePresenter;
import com.progressoft.security.sample.client.requests.SampleSampleClientRequest;
import com.progressoft.security.sample.client.requests.SampleServerRequest;
import com.progressoft.security.sample.shared.request.SampleRequest;
import com.progressoft.security.sample.shared.response.SampleResponse;

import org.akab.engine.app.test.TestEntryPointContext;
import org.akab.engine.app.test.TestModule;
import org.akab.engine.app.test.TestPresenterFactory;
import org.akab.engine.app.test.TestViewFactory;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.client.mvp.view.View;

import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@RunWith(GwtMockitoTestRunner.class)
public class SampleClientModuleTest{

    private TestModule testModule;
    private SamplePresenterSpy presenterSpy;
    private SampleViewSpy viewSpy;

    @Before
    public void setUp() throws Exception {

        testModule=new TestModule();
        testModule.init(new TestEntryPointContext());
        testModule.configureModule(new SampleModuleConfiguration());

        testModule.replacePresenter(SamplePresenter.class.getCanonicalName(), new TestPresenterFactory() {
            @Override
            public Presentable make() {
                presenterSpy=new SamplePresenterSpy();
                return presenterSpy;
            }
        });

        testModule.replaceView(SamplePresenter.class.getCanonicalName(), new TestViewFactory() {
            @Override
            public View make() {
                viewSpy=new SampleViewSpy();
                return viewSpy;
            }
        });
    }

    @Test
    public void givenSampleModule_whenSampleSampleClientRequestIsSent_thenShouldContributeToMainExtensionPoint() throws Exception {
//        new SampleSampleClientRequest(new MainExtensionPoint() {
//            @Override
//            public MainContext context() {
//                return new MainContext() {
//                    @Override
//                    public void appendElementToRoot(Element e) {
//
//                    }
//
//                    @Override
//                    public void appendWidgetToRoot(IsWidget w) {
//                        assertNotNull(w);
//                    }
//                };
//            }
//        }).send();

//        assertTrue(presenterSpy.isContributionCompleted());
        testModule.run();
        assertEquals("Server message", presenterSpy.getMessage());
//        assertEquals("Hello world! from Sample contribution request", viewSpy.getWelcomeMessage());
    }

    @Test
    @Ignore
    public void givenSampleClientModule_whenSampleServerRequestIsSent_thenServerMessageShouldBeRecieved()
            throws Exception {

        new SampleServerRequest(){
            @Override
            protected void process(SamplePresenter presenter, SampleRequest serverArgs, SampleResponse response) {
                super.process(presenter, serverArgs, response);
                assertEquals("Server message",response.getServerMessage());
            }

            @Override
            public String getKey() {
                return SampleServerRequest.class.getCanonicalName();
            }
        }.send();
    }
}
