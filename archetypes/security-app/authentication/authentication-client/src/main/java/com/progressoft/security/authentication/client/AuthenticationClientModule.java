package com.progressoft.security.authentication.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.authentication.client.views.AuthenticationBundle;

@ClientModule(name="Authentication")
public class AuthenticationClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AuthenticationClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Authentication client module ...");
		AuthenticationBundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new AuthenticationModuleConfiguration());
	}
}
