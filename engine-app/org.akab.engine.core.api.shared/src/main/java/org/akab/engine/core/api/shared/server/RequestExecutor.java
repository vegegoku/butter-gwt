package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;

public interface RequestExecutor<E extends ServerEntryPointContext> {

    Response executeRequest(ServerArgs arguments,  E entryPointContext);

}
