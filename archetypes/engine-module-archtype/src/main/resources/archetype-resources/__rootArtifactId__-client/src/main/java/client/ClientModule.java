#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.InitialTaskRegistry;
import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.api.client.mvp.PresenterRegistry;
import org.akab.engine.core.api.client.mvp.ViewRegistry;
import org.akab.engine.core.api.client.request.RequestRegistry;

import java.util.logging.Logger;

public class ClientModule implements EntryPoint {

	private static final Logger LOGGER=Logger.getLogger(ClientModule.class.getName());

	public void onModuleLoad() {

		LOGGER.info("${artifactId} is being initialized");
		new ModuleConfigurator().configureModule(new ModuleConfiguration() {
			@Override
			public void registerPresenters(PresenterRegistry registry) {

			}

			@Override
			public void registerRequests(RequestRegistry registry) {

			}

			@Override
			public void registerViews(ViewRegistry registry) {

			}

			@Override
			public void registerContributions(ContributionsRegistry registry) {

			}

			@Override
			public void registerInitialTasks(InitialTaskRegistry registry) {

			}
		});
	}
}
