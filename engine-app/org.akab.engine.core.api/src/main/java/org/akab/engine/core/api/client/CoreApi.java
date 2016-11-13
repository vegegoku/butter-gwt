package org.akab.engine.core.api.client;

import com.google.gwt.core.client.EntryPoint;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreApi implements EntryPoint {

    private static final Logger LOGGER=Logger.getLogger(CoreApi.class.getName());

    public void onModuleLoad() {
        LOGGER.log(Level.INFO, "CoreApi");
    }
}
