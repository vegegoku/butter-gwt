#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;


@ClientModule(name="${module}")
public class ${module}ClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(${module}ClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing ${module} client module ...");
		new ModuleConfigurator().configureModule(new ${module}ClientModuleConfiguration());
	}
}