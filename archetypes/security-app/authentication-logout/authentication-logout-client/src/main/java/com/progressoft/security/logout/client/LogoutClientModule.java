package com.progressoft.security.logout.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.logout.client.views.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.logout.client.views.LogoutBundle;

@ClientModule(name="Logout")
public class LogoutClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(LogoutClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Logout client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new LogoutModuleConfiguration());
	}
}
