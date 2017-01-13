package com.progressoft.security.layout.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.layout.client.views.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.layout.client.views.AuthenticationLayoutBundle;

@ClientModule(name="AuthenticationLayout")
public class AuthenticationLayoutClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AuthenticationLayoutClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing AuthenticationLayout client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new AuthenticationLayoutModuleConfiguration());
	}
}
