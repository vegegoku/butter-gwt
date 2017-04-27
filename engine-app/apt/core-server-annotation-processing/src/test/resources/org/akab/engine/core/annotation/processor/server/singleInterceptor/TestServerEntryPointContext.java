package org.akab.engine.core.annotation.processor.server.singleInterceptor;

import org.akab.engine.core.api.shared.server.ServerEntryPointContext;

public class TestServerEntryPointContext implements ServerEntryPointContext{
    @Override
    public String getName() {
        return TestServerEntryPointContext.class.getCanonicalName();
    }
}
