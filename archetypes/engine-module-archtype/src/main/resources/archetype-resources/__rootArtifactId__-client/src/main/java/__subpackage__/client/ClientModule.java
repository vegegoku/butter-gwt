#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.InitialTaskRegistry;
import org.akab.engine.core.api.client.History.PathToRequestMapperRegistry;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.api.client.mvp.ViewRegistry;
import org.akab.engine.core.api.client.request.RequestRegistry;
import ${package}.${subpackage}.client.presenters.${module}Presenter;
import ${package}.${subpackage}.client.presenters.Default${module}Presenter;
import ${package}.${subpackage}.client.requests.${module}SampleClientRequest;
import ${package}.${subpackage}.client.views.Default${module}View;
import ${package}.${subpackage}.client.contributions.${module}ContributionToMain;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import java.util.logging.Logger;

public class ClientModule implements EntryPoint {

	private static final Logger LOGGER=Logger.getLogger(ClientModule.class.getName());

	public void onModuleLoad() {

		LOGGER.info("Initializing ${module} client module ...");
		new ModuleConfigurator().configureModule(new ModuleConfiguration() {
			@Override
			public void registerPresenters(PresenterRegistry registry) {
				registry.registerPresenter(${module}Presenter.class.getCanonicalName(), new Default${module}Presenter());
			}

			@Override
			public void registerRequests(RequestRegistry registry) {
				registry.registerRequest(${module}SampleClientRequest.class.getCanonicalName(), ${module}Presenter.class.getCanonicalName());
			}

			@Override
			public void registerViews(ViewRegistry registry) {
				registry.registerView(${module}Presenter.class.getCanonicalName(), new Default${module}View());
			}

			@Override
			public void registerContributions(ContributionsRegistry registry) {
				registry.registerContribution(MainExtensionPoint.class, new ${module}ContributionToMain());
			}

			@Override
			public void registerInitialTasks(InitialTaskRegistry registry) {

			}

			@Override
			public void registerPathMappers(PathToRequestMapperRegistry registry) {

			}
		});
	}
}
