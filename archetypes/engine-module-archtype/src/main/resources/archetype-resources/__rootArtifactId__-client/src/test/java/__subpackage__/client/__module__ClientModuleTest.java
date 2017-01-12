#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import ${package}.${subpackage}.client.presenters.${module}Presenter;
import ${package}.${subpackage}.client.requests.${module}SampleClientRequest;
import ${package}.${subpackage}.client.requests.${module}ServerRequest;
import ${package}.${subpackage}.shared.request.${module}Request;
import ${package}.${subpackage}.shared.response.${module}Response;

import org.akab.engine.app.test.TestEntryPointContext;
import org.akab.engine.app.test.TestModule;
import org.akab.engine.app.test.TestPresenterFactory;
import org.akab.engine.app.test.TestViewFactory;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.client.mvp.view.View;

import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.app.test.ModuleTestCase;

@RunWith(GwtMockitoTestRunner.class)
public class ${module}ClientModuleTest extends ModuleTestCase{

    private ${module}PresenterSpy presenterSpy;
    private ${module}ViewSpy viewSpy;

    @Override
    public void setUp() {

        testModule.configureModule(new ${module}ModuleConfiguration());

        testModule.replacePresenter(${module}Presenter.class.getCanonicalName(), () -> {
            presenterSpy=new ${module}PresenterSpy();
            return presenterSpy;
        });

        testModule.replaceView(${module}Presenter.class.getCanonicalName(), () -> {
            viewSpy=new ${module}ViewSpy();
            return viewSpy;
        });
    }

    @Test
    public void given${module}Module_when${module}SampleClientRequestIsSent_thenShouldContributeToMainExtensionPoint() throws Exception {
        new ${module}SampleClientRequest(new MainExtensionPoint() {
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
        assertEquals("Hello world! from ${module} contribution request", viewSpy.getWelcomeMessage());
    }

    @Test
    public void given${module}ClientModule_when${module}ServerRequestIsSent_thenServerMessageShouldBeRecieved()
            throws Exception {

        new ${module}ServerRequest(){
            @Override
            protected void process(${module}Presenter presenter, ${module}Request serverArgs, ${module}Response response) {
                super.process(presenter, serverArgs, response);
                assertEquals("Server message",response.getServerMessage());
            }

            @Override
            public String getKey() {
                return ${module}ServerRequest.class.getCanonicalName();
            }
        }.send();
    }
}
