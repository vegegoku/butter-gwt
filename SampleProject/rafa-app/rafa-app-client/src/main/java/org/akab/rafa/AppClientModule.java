package org.akab.rafa;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import org.akab.engine.core.api.client.ClientApp;

import java.util.logging.Logger;

public class AppClientModule implements EntryPoint {

	private static final Logger LOGGER= Logger.getLogger(AppClientModule.class.getName());

	public void onModuleLoad() {
		ClientApp.make().run();
		LOGGER.info("Application client side have been initialized.");
		History.fireCurrentHistoryState();
	}
}
