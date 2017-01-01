package com.progressoft.security.sample.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.sample.client.views.bundle.SampleBundle;

@ClientModule(name="Sample")
public class SampleClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(SampleClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Sample client module ...");
		SampleBundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new SampleModuleConfiguration());
	}
}
