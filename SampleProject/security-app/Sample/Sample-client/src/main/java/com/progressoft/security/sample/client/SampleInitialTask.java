package com.progressoft.security.sample.client;

import com.progressoft.security.sample.client.requests.SampleServerRequest;
import org.akab.engine.core.api.client.InitializeTask;
import org.akab.engine.core.api.client.annotations.InitialTask;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@InitialTask
public class SampleInitialTask implements InitializeTask {

    public static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(SampleInitialTask.class);
    @Override
    public void execute() {
        LOGGER.info(">> executing initial task : ");
        new SampleServerRequest().send();
    }
}
