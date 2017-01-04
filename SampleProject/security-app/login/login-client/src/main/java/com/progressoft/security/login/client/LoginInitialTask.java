package com.progressoft.security.login.client;

import org.akab.engine.core.api.client.InitializeTask;
import org.akab.engine.core.api.client.annotations.InitialTask;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@InitialTask
public class LoginInitialTask implements InitializeTask {
    public static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(LoginInitialTask.class);
    @Override
    public void execute() {
        LOGGER.info("Helloooooooooooooooooooooooooo");
    }
}
