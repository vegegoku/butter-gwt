package com.progressoft.security.uimessages.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.uimessages.client.views.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.uimessages.client.views.UiMessagesBundle;

@ClientModule(name="UiMessages")
public class UiMessagesClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(UiMessagesClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing UiMessages client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new UiMessagesModuleConfiguration());
	}
}
