package org.akab.rafa.hello.world.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.History.RequestFromPath;
import org.akab.engine.core.api.client.History.TokenizedPath;
import org.akab.engine.core.api.client.InitialTaskRegistry;
import org.akab.engine.core.api.client.History.PathToRequestMapperRegistry;
import org.akab.engine.core.api.client.InitializeTask;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.api.client.mvp.ViewRegistry;
import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.View;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.request.RequestRegistry;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import org.akab.rafa.hello.world.client.presenters.HelloWorldPresenter;
import org.akab.rafa.hello.world.client.presenters.DefaultHelloWorldPresenter;
import org.akab.rafa.hello.world.client.requests.HelloWorldSampleClientRequest;
import org.akab.rafa.hello.world.client.requests.SampleServerRequest;
import org.akab.rafa.hello.world.client.views.DefaultHelloWorldView;
import org.akab.rafa.hello.world.client.contributions.HelloWorldContributionToMain;


@ClientModule(name = "HelloWorld")
public class HelloWorldClientModule implements EntryPoint {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(HelloWorldClientModule.class);

    public void onModuleLoad() {

        LOGGER.info("Initializing HelloWorld client module ...!");

        new ModuleConfigurator().configureModule(new ModuleConfiguration() {
            @Override
            public void registerPresenters(PresenterRegistry registry) {
                registry.registerPresenter(new LazyPresenterLoader(HelloWorldPresenter.class.getCanonicalName(),
                                                   DefaultHelloWorldPresenter.class.getCanonicalName()) {
                                               @Override
                                               protected Presentable make() {
                                                   return new DefaultHelloWorldPresenter();
                                               }
                                           }
                );
            }

            @Override
            public void registerRequests(RequestRegistry registry) {
                registry.registerRequest(HelloWorldSampleClientRequest.class.getCanonicalName(),
                        HelloWorldPresenter.class.getCanonicalName());
                registry.registerRequest(SampleServerRequest.class.getCanonicalName(),
                        HelloWorldPresenter.class.getCanonicalName());

            }

            @Override
            public void registerViews(ViewRegistry registry) {
                registry.registerView(new LazyViewLoader(HelloWorldPresenter.class.getCanonicalName()) {
                    @Override
                    protected View make() {
                        return new DefaultHelloWorldView();
                    }
                });
            }

            @Override
            public void registerContributions(ContributionsRegistry registry) {
                registry.registerContribution(MainExtensionPoint.class, new HelloWorldContributionToMain());
            }

            @Override
            public void registerInitialTasks(InitialTaskRegistry registry) {

                registry.registerInitialTask(new InitializeTask() {
                    @Override
                    public void execute() {
                        new SampleServerRequest().send();
                    }
                });

            }

            @Override
            public void registerPathMappers(PathToRequestMapperRegistry registry) {
                registry.registerMapper("somePath", new RequestFromPath() {
                    @Override
                    public Request buildRequest(TokenizedPath path) {

                        return new HelloWorldSampleClientRequest(HelloWorldContributionToMain.extensionPoint,
                                path.getParameter("welcomeMessage"));
                    }
                });
            }
        });
    }
}
