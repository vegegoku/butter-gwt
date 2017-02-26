package com.progressoft.security.userinfo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import org.akab.engine.core.api.client.ModuleConfigurator;
import org.akab.engine.core.api.client.annotations.ClientModule;
import com.progressoft.security.userinfo.client.views.Bundle;

import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import com.progressoft.security.userinfo.client.views.UserInfoBundle;

@ClientModule(name="UserInfo")
public class UserInfoClientModule implements EntryPoint {

	private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(UserInfoClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing UserInfo client module ...");
		Bundle.INSTANCE.style().ensureInjected();
		new ModuleConfigurator().configureModule(new UserInfoModuleConfiguration());
	}
}
