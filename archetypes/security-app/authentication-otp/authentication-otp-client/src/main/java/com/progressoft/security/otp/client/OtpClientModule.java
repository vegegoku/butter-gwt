package com.progressoft.security.otp.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.security.otp.client.views.Bundle;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@ClientModule(name="Otp")
public class OtpClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(OtpClientModule.class);

	@Override
	public void onModuleLoad() {
		LOGGER.info("Initializing Otp client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new OtpModuleConfiguration());
	}
}
