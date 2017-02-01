package com.progressoft.security.otp.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.otp.client.views.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.otp.client.views.OtpBundle;

@ClientModule(name="Otp")
public class OtpClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(OtpClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Otp client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new OtpModuleConfiguration());
	}
}
