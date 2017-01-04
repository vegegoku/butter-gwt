package com.progressoft.security.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.security.login.client.views.LoginBundle;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;


@ClientModule(name="Login")
public class LoginClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(LoginClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Login client module ...");
		LoginBundle.LOGIN_BUNDLE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new LoginModuleConfiguration());
	}
}
