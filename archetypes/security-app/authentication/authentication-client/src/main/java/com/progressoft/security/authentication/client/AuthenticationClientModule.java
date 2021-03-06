package com.progressoft.security.authentication.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.security.authentication.client.views.Bundle;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;


@ClientModule(name="Authentication")
public class AuthenticationClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AuthenticationClientModule.class);

	@Override
	public void onModuleLoad() {
		LOGGER.info("Initializing Authentication client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new AuthenticationModuleConfiguration());
	}
}
