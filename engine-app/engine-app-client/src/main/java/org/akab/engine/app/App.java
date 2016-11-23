package org.akab.engine.app;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import org.akab.engine.core.api.client.ClientApp;

import java.util.logging.Logger;

public class App implements EntryPoint {

	private static final Logger LOGGER= Logger.getLogger(App.class.getName());

	public void onModuleLoad() {
		LOGGER.info("App");
		ClientApp.make().run();

	}
}
