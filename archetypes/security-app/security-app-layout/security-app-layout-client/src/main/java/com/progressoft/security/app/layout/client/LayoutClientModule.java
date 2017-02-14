package com.progressoft.security.app.layout.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.app.layout.client.views.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;


@ClientModule(name="AppLayout")
public class LayoutClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(LayoutClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Layout client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new AppLayoutModuleConfiguration());
	}
}
