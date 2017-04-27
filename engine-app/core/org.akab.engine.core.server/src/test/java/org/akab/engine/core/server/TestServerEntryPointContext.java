package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.ServerEntryPointContext;

public class TestServerEntryPointContext implements ServerEntryPointContext{

    private final String entryContextParameter="-entry-point-parameter";

    public String getEntryContextParameter() {
        return entryContextParameter;
    }

    @Override
    public String getName() {
        return TestServerEntryPointContext.class.getCanonicalName();
    }
}
