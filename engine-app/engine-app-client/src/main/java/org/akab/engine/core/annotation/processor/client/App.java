package org.akab.engine.core.annotation.processor.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.api.client.ClientApp;

import java.util.logging.Logger;

public class App implements EntryPoint {

	private static final Logger LOGGER= Logger.getLogger(App.class.getName());

	public void onModuleLoad() {
		LOGGER.info("App");
		ClientApp.make().run();


	}
}
