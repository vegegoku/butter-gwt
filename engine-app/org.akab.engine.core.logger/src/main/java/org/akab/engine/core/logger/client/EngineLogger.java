package org.akab.engine.core.logger.client;

import com.google.gwt.core.client.EntryPoint;

import java.util.logging.Level;

public class EngineLogger implements EntryPoint {

    private static final java.util.logging.Logger LOGGER= java.util.logging.Logger.getLogger(EngineLogger.class.getName());

    @Override
    public void onModuleLoad() {
        LOGGER.log(Level.INFO, "EngineLogger");
    }
}
