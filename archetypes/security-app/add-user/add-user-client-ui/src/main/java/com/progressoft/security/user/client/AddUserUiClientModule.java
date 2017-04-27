package com.progressoft.security.user.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.user.client.views.bundle.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@ClientModule(name="AddUser")
public class AddUserClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AddUserClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing AddUser client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new AddUserModuleConfiguration());
	}
}
