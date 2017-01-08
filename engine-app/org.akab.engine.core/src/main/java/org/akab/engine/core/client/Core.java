package org.akab.engine.core.client;

import com.google.gwt.core.client.EntryPoint;
import org.akab.engine.core.client.app.CoreModule;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public class Core implements EntryPoint {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(Core.class);

    @Override
    public void onModuleLoad() {

        CoreModule.init();

        LOGGER.info("Init engine Core...");
    }
}
